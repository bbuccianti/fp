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

(defn parse-sequence [s flag]
  (let [replaced (-> s (string/replace #"<" "[") (string/replace #">" "]"))
        sq (read-string replaced)]
    {:sequence (mapv (comp parse replace-brackets str) sq)}))

(defn parse-application [s]
  (let [splitted (string/split s #":")]
    {:application {:operator (parse (string/trim (first splitted)))
                   :operand (parse (string/trim (second splitted)))}}))

(defn parse-composition [s]
  (let [splitted (string/split s #":")
        left (string/split (first splitted) #"∘")
        right (string/trim (second splitted))]
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

(defn parse [s]
  (match [s]
    [(cnd :guard #(boolean (re-find #"\(.*→.*;.*\).*:" s)))]
    (parse-condition cnd)

    [(cnstr :guard #(boolean (re-find #"^\[.*\]" s)))]
    (parse-construction cnstr)

    [(xpr :guard #(= "<" (first s)))]
    (parse-sequence xpr nil)

    [(cmp :guard #(boolean (re-find #"∘.*:" s)))]
    (parse-composition cmp)

    [(appli :guard #(boolean (re-find #":" s)))]
    (parse-application appli)

    :else (parse-object s)))
