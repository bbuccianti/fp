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
      (cond
        ;; SELECTORS
        (= op "tl")
        {:sequence (vec (rest sqc))}

        (= op "tlr")
        {:sequence (-> sqc reverse rest reverse vec)}

        (= op "id")
        operand

        ;; PREDICATES
        (= op "atom")
        {:type :bool :val (not (contains? operand :sequence))}

        (= op "eq")
        (if (= 2 (count sqc))
          {:type :bool
           :val (apply = (map :string sqc))}
          {:type :undefined})

        (= op "null")
        {:type :bool :val (= (:type operand) :empty)}

        ;; ARITHMETICS
        (or (= op "+") (= op "-") (= op "×"))
        (if (and (= 2 (count sqc))
                 (every? (partial = :number) (map :type sqc)))
          {:type :number
           :val (apply (case op
                         "+" +
                         "-" -
                         "×" *)
                       (map :val sqc))}
          {:type :undefined})

        (= op "÷")
        (if (and (= 2 (count sqc))
                 (every? (partial = :number) (map :type sqc))
                 (not= 0 (:val (last sqc))))
          {:type :number :val (apply / (map :val sqc))}
          {:type :undefined})

        ;; LOGIC

        (or (= op "and") (= op "or"))
        (if (and (= 2 (count sqc))
                 (every? (partial = :bool) (mapv :type sqc)))
          {:type :bool
           :val ((case op
                   "and" every?
                   "or" some)
                 identity (mapv :val sqc))}
          {:type :undefined})

        (= op "not")
        (if (= :bool (:type operand))
          {:type :bool :val (not (:val operand))}
          {:type :undefined}))

      :else parsed-map)))
