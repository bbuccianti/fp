(ns fp.evaluator.stringify
  (:require
   [clojure.string :as string]
   [clojure.core.match :refer [match]]))

(defn to-string [obj]
  (match [obj]
    [(:or {:type :undefined} {:type :empty})]
    (if (= :empty (:type obj)) "∅" "⊥")

    [{:type :symbol}]
    (:string obj)

    [{:type :number}]
    (str (:val obj))

    [{:type :bool}]
    (if (:val obj) "T" "F")

    [{:sequence sqc}]
    (str "<" (string/join ", " (map to-string sqc)) ">")))
