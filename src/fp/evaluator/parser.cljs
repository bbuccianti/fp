(ns fp.evaluator.parser
  (:require
   [clojure.core.match :refer [match]]
   [clojure.string :as string]
   [cljs.reader :refer [read-string]]))

(declare parse)

(defn str-number? [s]
  (not (js/isNaN s)))

(defn parse-object [s]
  (match [s]
    [(:or "T" "F")]
    {:type :bool :val (= s "T")}

    [(:or "⊥" "∅")]
    {:type (if (= s "∅") :empty :undefined)}

    [(n :guard str-number?)]
    {:string n :type :number :val (js/parseFloat n)}

    [(n :guard #(boolean (re-matches #"‾\d+" s)))]
    (let [x (string/replace n "‾" "")]
      {:string n :type :constant :val (js/parseFloat x)})

    :else {:type :symbol :string s}))

(defn replace-brackets [s]
  (-> s (string/replace #"\[" "<") (string/replace #"\]" ">")))

(defn parse-sequence [s]
  (let [replaced (-> s (string/replace #"<" "[") (string/replace #">" "]"))
        sq (read-string replaced)]
    {:sequence (mapv (comp parse replace-brackets str) sq)}))

(defn parse-application [s]
  (let [splitted (string/split s #":")]
    {:application {:operator (parse (string/trim (first splitted)))
                   :operand (parse (string/trim (second splitted)))}}))

(defn parse-composition [s]
  (let [spl (string/split s #":")
        left (string/split (first spl) #"∘")
        right (string/trim (second spl))]
    {:composition
     {:functions (rseq (mapv (comp parse string/trim) left))
      :operand (parse right)}}))

(defn parse-construction [s]
  (let [spl (string/split s #":")
        l (string/replace (first spl) "[" "")
        r (string/replace l "]" "")
        left (string/split r #",")]
    {:construction
     (into {:functions (mapv (comp parse string/trim) left)}
           (if (second spl) {:operand (parse (string/trim (second spl)))}))}))

(defn parse-condition [s]
  (let [spl (string/split s #":")
        right (string/trim (second spl))
        lspl (string/split (first spl) #"→")
        fns (-> lspl first (string/replace "(" "") (string/split #"∘"))
        later (-> lspl second (string/replace ")" "") (string/split #";"))]
    {:condition
     {:functions (rseq (mapv (comp parse string/trim) fns))
      :true (-> (first later) string/trim parse)
      :false (-> (second later) string/trim parse)
      :operand (parse right)}}))

(defn parse-insertion [s]
  (let [spl (string/split s #":")
        l (string/split (first spl) #"/| ")
        left (remove empty? l)]
    {:insertion
     (into {:function (-> left first string/trim parse)}
           (if (second spl)
             {:operand (-> (second spl) string/trim parse)}))}))

(defn parse-big-application [s]
  (let [spl (string/split s #":")
        l (string/split (first spl) #"α| ")
        left (remove empty? l)]
    {:to-all
     (into {:function (-> (first left) string/trim parse)}
           (if (second spl)
             {:operand (-> (second spl) string/trim parse)}))}))

(defn parse [s]
  (match [s]
    [(condi :guard #(boolean (re-find #"\(.*→.*;.*\).*:" s)))]
    (parse-condition condi)

    [(compo :guard #(boolean (re-find #"∘.*:" s)))]
    (parse-composition compo)

    [(to-all :guard #(boolean (re-find #"α.*:?" s)))]
    (parse-big-application to-all)

    [(const :guard #(boolean (re-find #"^\[.*\]" s)))]
    (parse-construction const)

    [(expr :guard #(= "<" (first s)))]
    (parse-sequence expr)

    [(inser :guard #(boolean (re-find #"/.*:?" s)))]
    (parse-insertion inser)

    [(appli :guard #(boolean (re-find #":" s)))]
    (parse-application appli)

    :else (parse-object s)))
