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

(defn parse [s]
  (match [s]
    [(xpr :guard #(= "[" (first s)))]
    (parse-sequence xpr :change)

    [(xpr :guard #(= "<" (first s)))]
    (parse-sequence xpr nil)

    [(appli :guard #(boolean (re-find #":" s)))]
    (parse-application appli)

    :else (parse-object s)))
