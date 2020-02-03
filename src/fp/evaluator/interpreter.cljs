(ns fp.evaluator.interpreter
  (:require
   [clojure.core.match :refer [match]]
   [clojure.string :refer [replace]]))

#_(defn evaluate-application [op operand]
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

#_(defn evaluate [parsed-map]
  (match [parsed-map]
    [{:to-all target}]
    {:sequence
     (map
      (fn [sqc]
        (evaluate {:application {:operator (:function target)
                                 :operand sqc}}))
      (get-in target [:operand :sequence]))}

    [{:insertion ins}]
    (let [sqc (get-in ins [:operand :sequence])
          f (:function ins)]
      (reduce
       (fn [acc nxt]
         (evaluate {:application {:operator f
                                  :operand {:sequence [acc nxt]}}}))
       (first sqc) (rest sqc)))

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
       (match [f]
         [{:to-all appli}]
         (evaluate {:to-all (into appli {:operand sqc})})

         [{:construction constr}]
         (evaluate {:construction
                    {:functions (:functions constr)
                     :operand sqc}})
         [{:insertion ins}]
         (evaluate {:insertion
                    {:function (:function ins)
                     :operand sqc}})

         [(:or {:type :symbol} {:type :number})]
         (evaluate {:application {:operator f :operand sqc}})))
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

(defn evaluate-application [operator operand]
  (match [operator operand]
    [{:number n} _]
    (get operand (dec n))

    [_ :undefined]
    :undefined

    [{:symbol op} _]
    (cond
      (= "atom" op)
      {:boolean (not (vector? operand))}

      (= "id" op)
      operand

      (= "eq" op)
      (if (= 2 (count operand))
        {:boolean (apply = operand)}
        :undefined)

      (= "null" op)
      {:boolean (= operand :empty)}

      (= "tl" op)
      (rest operand)

      (= "tlr" op)
      (-> operand rseq rest vec rseq)

      (boolean (re-matches #"\d+r" op))
      (let [i (-> op (replace "r" ""))]
        (get (vec (rseq operand)) (dec (js/parseInt i))))

      (contains? #{"+" "-" "×"} op)
      (if (and (= 2 (count operand))
               (every? #(contains? % :number) operand))
        {:number (apply (case op "+" + "-" - "×" *) (map :number operand))}
        :undefined)

      (= "÷" op)
      (if (and (= 2 (count operand))
               (every? #(contains? % :number) operand)
               (not= 0 (:number (last operand))))
        {:number (apply / (map :number operand))}
        :undefined)

      (contains? #{"and" "or"} op)
      (if (and (= 2 (count operand))
               (every? #(contains? % :boolean) operand))
        {:boolean ((case op "and" every? "or" some)
                   identity (map :boolean operand))}
        :undefined)

      (= "not" op)
      (if (contains? operand :boolean)
        {:boolean (not (:boolean operand))}
        :undefined)

      (= "length" op)
      (if (contains? operand :symbol)
        :undefined
        {:number (if (= :empty operand) 0 (count operand))})

      (= "reverse" op)
      (if (contains? operand :symbol)
        :undefined
        (if (= :empty operand) :empty (-> operand rseq vec)))

      (= "trans" op)
      (if (vector? operand)
        (apply mapv vector operand)
        :undefined)

      (= "distl" op)
      (if (not (vector? operand))
        :undefined
        (if (= :empty (second operand))
          :empty
          (mapv vector (repeat (first operand)) (second operand))))

      (= "distr" op)
      (if (not (vector? operand))
        :undefined
        (if (= :empty (first operand))
          :empty
          (mapv vector (first operand) (repeat (second operand)))))

      (= "apndl" op)
      (if (not (vector? operand))
        :undefined
        (if (= :empty (second operand))
          [(first operand)]
          (into [(first operand)] (second operand))))

      (= "apndr" op)
      (if (not (vector? operand))
        :undefined
        (if (= :empty (first operand))
          [(second operand)]
          (reverse (into [(second operand)] (reverse (first operand)))))))

    :else "Error"))

(defn evaluate [parsed-map]
  (match [parsed-map]
    [{:application appli}]
    (reduce (fn [acc f] (evaluate-application f acc))
            (:operands appli) (:operators appli))

    :else parsed-map))
