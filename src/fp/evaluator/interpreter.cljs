(ns fp.evaluator.interpreter
  (:require [clojure.core.match :refer [match]]))

(defn evaluate-application [op operand]
  (cond
    ;; SELECTORS
    (= op "tl")
    {:sequence (vec (rest (:sequence operand)))}

    (= op "tlr")
    {:sequence (-> (:sequence operand) reverse rest reverse vec)}

    (= op "id")
    operand

    ;; PREDICATES
    (= op "atom")
    {:type :bool :val (not (contains? operand :sequence))}

    (and (= op "eq") (= 2 (count (:sequence operand))))
    {:type :bool :val (apply = (map :string (:sequence operand)))}

    (= op "null")
    {:type :bool :val (= (:type operand) :empty)}

    ;; ARITHMETICS
    (and (or (= op "+") (= op "-") (= op "×"))
         (= 2 (count (:sequence operand)))
         (every? (partial = :number) (map :type (:sequence operand))))
    (let [val (apply (case op "+" + "-" - "×" *)
                     (map :val (:sequence operand)))]
      {:type :number :val val :string (str val)})

    (and (= op "÷") (= 2 (count (:sequence operand)))
         (every? (partial = :number) (map :type (:sequence operand)))
         (not= 0 (:val (last (:sequence operand)))))
    (let [val (apply / (map :val (:sequence operand)))]
      {:type :number :val val :string (str val)})

    ;; LOGIC
    (and (or (= op "and") (= op "or"))
         (= 2 (count (:sequence operand)))
         (every? (partial = :bool) (mapv :type (:sequence operand))))
    {:type :bool
     :val ((case op "and" every? "or" some)
           identity (mapv :val (:sequence operand)))}

    (and (= op "not") (= :bool (:type operand)))
    {:type :bool :val (not (:val operand))}

    ;; SEQUENCES
    (and (= op "length") (not= :symbol (:type operand)))
    {:type :number
     :val (if (= (:type operand) :empty)
            0
            (count (:sequence operand)))}

    (and (= op "reverse") (:sequence operand))
    {:sequence (reverse (:sequence operand))}

    (and (= op "trans") (:sequence operand))
    (let [v (map :sequence (:sequence operand))]
      {:sequence (mapv #(into {} {:sequence %})
                       (apply mapv vector v))})

    (and (= op "distl") (:sequence operand))
    (let [left (first (:sequence operand))
          right (:sequence (second (:sequence operand)))]
      {:sequence (mapv #(into {} {:sequence %})
                       (mapv vector (repeat left) right))})

    (and (= op "distr") (:sequence operand))
    (let [left (:sequence (first (:sequence operand)))
          right (second (:sequence operand))]
      {:sequence (mapv #(into {} {:sequence %})
                       (mapv vector left (repeat right)))})

    (= op "apndl")
    (let [left (first (:sequence operand))
          right (:sequence (second (:sequence operand)))]
      {:sequence (into [left] right)})

    (= op "apndr")
    (let [left (:sequence (first (:sequence operand)))
          right (second (:sequence operand))]
      {:sequence (reverse (into [right] (reverse left)))})

    (and (= op "rotl") (or (= :empty (:type operand)) (:sequence operand)))
    (if (= :empty (:type operand))
      {:type :empty}
      {:sequence (conj (vec (rest (:sequence operand)))
                       (first (:sequence operand)))})

    (and (= op "rotr") (or (= :empty (:type operand)) (:sequence operand)))
    (if (= :empty (:type operand))
      {:type :empty}
      {:sequence (into [(last (:sequence operand))]
                       (butlast (:sequence operand)))})

    :else {:type :undefined}))

(defn evaluate [parsed-map]
  (match [parsed-map]
    [{:condition cnd}]
    (let [result (evaluate {:composition {:functions (:functions cnd)
                                          :operand (:operand cnd)}})
          op (if (:val result) (:true cnd) (:false cnd))]
      (evaluate {:application {:operator op :operand (:operand cnd)}}))

    [{:construction cnst}]
    {:sequence
     (mapv (fn [f o]
             (evaluate {:application {:operator f :operand o}}))
           (:functions cnst) (repeat (:operand cnst)))}

    [{:composition cmpst}]
    (reduce
     (fn [sqc f]
       (if (contains? f :construction)
         (evaluate
          {:construction {:functions (get-in f [:construction :functions])
                          :operand sqc}})
         (evaluate
          {:application {:operator f :operand sqc}})))
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

        [{:type :symbol :string op} _]
        (evaluate-application op operand)

        [{:type :constant} _]
        {:string (str (:val operator))
         :type :number
         :val (:val operator)}

        :else "Error"))

    :else "Error"))
