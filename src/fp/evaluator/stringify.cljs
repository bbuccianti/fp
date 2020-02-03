(ns fp.evaluator.stringify
  (:require
   [clojure.string :as string]
   [clojure.core.match :refer [match]]))

(defn to-string [obj]
  (match [obj]
    [(:or :undefined :empty)] (if (= :empty obj) "∅" "⊥")
    [{:symbol s}] s
    [{:number n}]
    (let [x (.toFixed (js/parseFloat n) 2)]
      (if (.endsWith (str x) ".00") (str (js/parseInt x)) (str x)))
    [{:boolean b}] (if b "T" "F")
    [(sqc :guard vector?)]
    (if (= (count sqc) 0)
      "∅"
      (str "<" (string/join ", " (map to-string sqc)) ">"))))
