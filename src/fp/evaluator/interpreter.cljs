(ns fp.evaluator.interpreter
  (:require [clojure.core.match :refer [match]]))

(defn evaluate [parsed-map]
  (let [operator (get-in parsed-map [:application :operator])
        operand (get-in parsed-map [:application :operand])]
    (declare sqc) ;; avoid awful logs for use of undeclared

    (match [operator operand]
      [_ {:type :undefined}]
      {:type :undefined}

      [{:type :number} _]
      (get (:sequence operand) (dec (:val operator)))

      [(n :guard #(boolean (re-matches #"\dr" (:string operator)))) _]
      (let [vctr (-> operand :sequence reverse vec)
            i (-> operator :string first js/parseInt)]
        (get vctr (dec i)))

      [{:type :symbol :string op} (:or {:sequence sqc} _)]
      (case op
        ;; SELECTORS

        "tl"
        {:sequence (vec (rest sqc))}

        "tlr"
        {:sequence (-> sqc reverse rest reverse vec)}

        "id"
        operand

        ;; PREDICATES

        "atom"
        {:type :bool :val (not (contains? operand :sequence))}

        "eq"
        (if (= 2 (count sqc))
          {:type :bool
           :val (= (-> sqc first :string) (-> sqc second :string))}
          {:type :undefined})

        "null"
        {:type :bool :val (= (:type operand) :empty)}

        ;; ARITHMETICS

        "+"
        (if (and (= 2 (count sqc))
                 (every? (partial = :number) (map :type sqc)))
          {:type :number :val (apply + (map :val sqc))}
          {:type :undefined})

        "-"
        (if (and (= 2 (count sqc))
                 (every? (partial = :number) (map :type sqc)))
          {:type :number :val (apply - (map :val sqc))}
          {:type :undefined})

        "×"
        (if (and (= 2 (count sqc))
                 (every? (partial = :number) (map :type sqc)))
          {:type :number :val (apply * (map :val sqc))}
          {:type :undefined})

        "÷"
        (if (and (= 2 (count sqc))
                 (every? (partial = :number) (map :type sqc))
                 (not= 0 (:val (last sqc))))
          {:type :number :val (apply / (map :val sqc))}
          {:type :undefined}))

      :else parsed-map)))
