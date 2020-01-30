(ns fp.evaluator.parser
  (:require
   [clojure.core.match :refer [match]]
   [clojure.string :as string]
   [cljs.reader :refer [read-string]]))

(declare parse)

(defn str-number? [s]
  (not (js/isNaN s)))

(defn parse-object [s]
  (into {:string s}
        (match [s]
          [(:or "T" "F")]
          {:type :bool :val (or (and (= s "T") true) false)}

          [(:or "⊥" "∅")]
          {:type (or (and (= s "∅") :empty) :undefined)}

          [(n :guard str-number?)]
          {:type :number :val (js/parseFloat n)}

          :else {:type :symbol})))

(defn parse-sequence [s flag]
  (let [replaced (-> s (string/replace #"<" "[") (string/replace #">" "]"))
        sq (read-string replaced)]
    {:sequence (mapv (comp parse str) sq)}))

(defn parse-application [s]
  (let [splitted (string/split s #":")]
    {:string s
     :application {:operator (parse (string/trim (first splitted)))
                   :operand (parse (string/trim (second splitted)))}}))

(defn parse-composition [s]
  (let [splitted (string/split s #":")
        left (string/split (first splitted) #"∘")
        right (string/trim (second splitted))]
    {:string s :composition
     {:functions (rseq (mapv (comp parse string/trim) left))
      :operand (parse right)}}))

(defn parse-construction [s]
  (let [spl (string/split s #":")
        right (string/trim (second spl))
        l (string/replace (first spl) "[" "")
        r (string/replace l "]" "")
        left (string/split r #",")]
    {:string s
     :construction {:functions (mapv (comp parse string/trim) left)
                    :operand (parse right)}}))

(defn parse-condition [s]
  (let [spl (string/split s #":")
        right (string/trim (second spl))
        lspl (string/split (first spl) #"→")
        fns (-> lspl first (string/replace "(" "") (string/split #"∘"))
        later (-> lspl second (string/replace ")" "") (string/split #";"))]
    {:string s
     :condition
     {:functions (rseq (mapv (comp parse string/trim) fns))
      :true (-> (first later) string/trim parse)
      :false (-> (second later) string/trim parse)
      :operand (parse right)}}))

(defn parse [s]
  (match [s]
    [(cnd :guard #(boolean (re-find #"\(.*→.*;.*\).*:" s)))]
    (parse-condition cnd)

    [(cnstr :guard #(boolean (re-find #"\[.*\].*:" s)))]
    (parse-construction cnstr)

    [(xpr :guard #(= "[" (first s)))]
    (parse-sequence xpr :change)

    [(xpr :guard #(= "<" (first s)))]
    (parse-sequence xpr nil)

    [(cmp :guard #(boolean (re-find #"∘.*:" s)))]
    (parse-composition cmp)

    [(appli :guard #(boolean (re-find #":" s)))]
    (parse-application appli)

    :else (parse-object s)))
