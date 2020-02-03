(ns fp.evaluator.stringify
  (:require
   [clojure.string :as string]
   [clojure.core.match :refer [match]]))

(defn to-string [obj]
  (match [obj]
    [(:or :undefined :empty)] (if (= :empty obj) "∅" "⊥")
    [{:symbol s}] s
    [{:number n}] (str n)
    [{:boolean b}] (if b "T" "F")
    [(sqc :guard vector?)]
    (if (= (count sqc) 0)
      "∅"
      (str "<" (string/join ", " (map to-string sqc)) ">"))))
