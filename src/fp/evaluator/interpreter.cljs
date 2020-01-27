(ns fp.evaluator.interpreter
  (:require [clojure.core.match :refer [match]]))

(defn evaluate [parsed-map]
  (let [operator (get-in parsed-map [:application :operator])
        operand (get-in parsed-map [:application :operand])]
    (match [operator operand]
      [_ {:type :undefined}]
      {:type :undefined}

      [{:type :number} _]
      (get (:sequence operand) (dec (:val operator)))

      [(n :guard #(boolean (re-matches #"\dr" (:string operator)))) _]
      (let [vctr (-> operand :sequence reverse vec)
            i (-> operator :string first js/parseInt)]
        (get vctr (dec i)))

      [{:type :symbol} _]
      (case (:string operator)
        "tl"
        {:sequence (vec (rest (:sequence operand)))}

        "tlr"
        {:sequence (-> (:sequence operand) reverse rest reverse vec)}

        "id"
        operand

        "atom"
        {:type :bool :val (not (contains? operand :sequence))}

        "eq"
        (let [sqc (:sequence operand)]
          (if (not= 2 (count sqc))
            {:type :undefined}
            {:type :bool :val (= (-> sqc first :string)
                                 (-> sqc second :string))}))

        "null"
        {:type :bool :val (= (:type operand) :empty)})

      :else parsed-map)))
