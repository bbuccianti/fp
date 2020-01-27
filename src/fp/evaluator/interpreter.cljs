(ns fp.evaluator.interpreter
  (:require [clojure.core.match :refer [match]]))

(defn evaluate [parsed-map]
  (let [apli (get parsed-map :application)
        operator (get-in parsed-map [:application :operator])
        operand (get-in parsed-map [:application :operand])]
    (match [operator]
      [{:type :number}]
      (get (:sequence operand) (dec (:val operator)))

      [(n :guard #(boolean (re-matches #"\dr" (:string operator))))]
      (let [vctr (vec (reverse (:sequence operand)))
            i (js/parseInt (first (:string operator)))]
        (get vctr (dec i)))

      [{:type :symbol}]
      (case (:string operator)
        "tl" {:sequence (vec (rest (:sequence operand)))}
        "tlr" {:sequence (-> (:sequence operand) reverse rest reverse vec)}
        "id" operand)

      :else parsed-map)))
