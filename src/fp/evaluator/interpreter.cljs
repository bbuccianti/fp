(ns fp.evaluator.interpreter
  (:require [clojure.core.match :refer [match]]))

(defn evaluate [parsed-map]
  (let [operator (get-in parsed-map [:application :operator])
        operand (get-in parsed-map [:application :operand])]
    (declare sqc) ;; avoid awful logs for use of undeclared
    (match [parsed-map]
      [{:composition cmpst}]
      (reduce (fn [sqc f]
                (evaluate {:application {:operator f
                                         :operand sqc}}))
              (:operand cmpst) (:functions cmpst))

      [{:application appli}]
      (let [operator (get appli :operator)
            operand (get appli :operand)]
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

            (and (= op "eq") (= 2 (count sqc)))
            {:type :bool
             :val (apply = (map :string sqc))}

            (= op "null")
            {:type :bool :val (= (:type operand) :empty)}

            ;; ARITHMETICS
            (and (or (= op "+") (= op "-") (= op "×"))
                 (= 2 (count sqc))
                 (every? (partial = :number) (map :type sqc)))
            {:type :number
             :val (apply (case op
                           "+" +
                           "-" -
                           "×" *)
                         (map :val sqc))}

            (and (= op "÷") (= 2 (count sqc))
                 (every? (partial = :number) (map :type sqc))
                 (not= 0 (:val (last sqc))))
            {:type :number :val (apply / (map :val sqc))}

            ;; LOGIC
            (and (or (= op "and") (= op "or"))
                 (= 2 (count sqc))
                 (every? (partial = :bool) (mapv :type sqc)))
            {:type :bool
             :val ((case op
                     "and" every?
                     "or" some)
                   identity (mapv :val sqc))}

            (and (= op "not") (= :bool (:type operand)))
            {:type :bool :val (not (:val operand))}

            ;; SEQUENCES
            (and (= op "length") (not= :symbol (:type operand)))
            {:type :number
             :val (if (= (:type operand) :empty)
                    0
                    (count sqc))}

            (and (= op "reverse") sqc)
            {:sequence (reverse sqc)}

            (and (= op "trans") sqc)
            (let [v (map :sequence sqc)]
              {:sequence (mapv #(into {} {:sequence %})
                               (apply mapv vector v))})

            (and (= op "distl") sqc)
            (let [left (first sqc)
                  right (:sequence (second sqc))]
              {:sequence (mapv #(into {} {:sequence %})
                               (mapv vector (repeat left) right))})

            (and (= op "distr") sqc)
            (let [left (:sequence (first sqc))
                  right (second sqc)]
              {:sequence (mapv #(into {} {:sequence %})
                               (mapv vector left (repeat right)))})

            (= op "apndl")
            (let [left (first sqc)
                  right (:sequence (second sqc))]
              {:sequence (into [left] right)})

            (= op "apndr")
            (let [left (:sequence (first sqc))
                  right (second sqc)]
              {:sequence (reverse (into [right] (reverse left)))})

            (and (= op "rotl") (or (= :empty (:type operand)) sqc))
            (if (= :empty (:type operand))
              {:type :empty}
              {:sequence (conj (vec (rest sqc)) (first sqc))})

            (and (= op "rotr") (or (= :empty (:type operand)) sqc))
            (if (= :empty (:type operand))
              {:type :empty}
              {:sequence (into [(last sqc)] (butlast sqc))})

            :else {:type :undefined})

          :else "Error"))

      :else "Error")))
