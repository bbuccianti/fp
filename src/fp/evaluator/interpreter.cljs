(ns fp.evaluator.interpreter
  (:require
   [clojure.core.match :refer [match]]
   [clojure.string :refer [replace]]))

(defn invoke [operator operand]
  (match [operator operand]
    [_ :undefined]
    :undefined

    [{:number n} _]
    (get operand (dec n))

    [{:constant n} _]
    {:number n}

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
      (-> operand rest vec)

      (= "tlr" op)
      (-> operand rseq rest reverse vec)

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
          (reverse (into [(second operand)] (reverse (first operand))))))

      (= "rotl" op)
      (if (not (vector? operand))
        (if (= :empty operand) :empty :undefined)
        (conj (vec (rest operand)) (first operand)))

      (= "rotr" op)
      (if (not (vector? operand))
        (if (= :empty operand) :empty :undefined)
        (into (vector (last operand)) (butlast operand))))

    [{:composition compo} _]
    (reduce (fn [acc f] (invoke f acc)) operand compo)

    [{:construction constr} _]
    (mapv (fn [f sqc] (invoke f sqc)) constr (repeat operand))

    [{:insertion inser} _]
    (reduce (fn [acc more]
              (invoke (first inser) [acc more]))
            (first operand) (rest operand))

    [{:to-all f} _]
    (mapv (fn [op operand]
            (invoke op operand))
          (repeat (first f)) operand)

    [{:condition condi :true t :false f} _]
    (let [x (invoke (first condi) operand)]
      (invoke (if (:boolean x) t f) operand))

    [{:bu bu} _]
    (invoke (first bu) [(second bu) operand])

    :else "Error"))

(defn evaluate [parsed-map]
  (let [operators (get-in parsed-map [:application :operators])
        operands (get-in parsed-map [:application :operands])]
    (reduce (fn [acc f] (invoke f acc)) operands operators)))
