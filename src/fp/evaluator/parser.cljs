(ns fp.evaluator.parser
  (:require
   [clojure.core.match :refer [match]]
   [clojure.string :as string]
   [cljs.reader :refer [read-string]]))

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

(defn parse-sequence [s]
  (let [open (string/replace s #"<" "[")
        close (string/replace open #">" "]")
        clj-parsed (read-string close)]
    {:string s
     :seq (mapv (comp parse str) clj-parsed)}))

(defn parse [s]
  (match [s]
    [(xpr :guard #(= "<" (first s)))]
    (parse-sequence xpr)

    :else (parse-object s)))
