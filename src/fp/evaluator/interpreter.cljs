(ns fp.evaluator.interpreter
  (:require
   [clojure.core.match :refer [match]]
   [clojure.string :refer [replace]]))

(defn invoke [operator operand]
  (match [operator operand]
    [_ :undefined]
    :undefined

    [{:number n} _]
    (if (>= (count operand) n)
      (nth operand (dec n))
      :undefined)

    [{:constant n} _]
    {:number n}

    [{:symbol op} _]
    (cond
      (= "atom" op)
      {:boolean (not (seq? operand))}

      (= "id" op)
      operand

      (= "eq" op)
      (if (= 2 (count operand))
        {:boolean (apply = operand)}
        :undefined)

      (= "null" op)
      {:boolean (= operand :empty)}

      (= "tl" op)
      (let [res (rest operand)]
        (if (empty? res) :empty res))

      (= "tlr" op)
      (let [res (-> operand reverse rest reverse)]
        (if (empty? res) :empty res))

      (boolean (re-matches #"\d+r" op))
      (let [i (-> op (replace "r" "") js/parseInt)]
        (if (>= (count operand) i)
          (nth (reverse operand) (dec i))
          :undefined))

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
        (if (= :empty operand) :empty (reverse operand)))

      (= "trans" op)
      (if (seq? operand)
        (apply map list operand)
        :undefined)

      (= "distl" op)
      (cond
        (not (seq? operand)) :undefined
        (= :empty (second operand)) :empty
        :else (map list
                   (repeat (first operand))
                   (second operand)))

      (= "distr" op)
      (cond
        (not (seq? operand)) :undefined
        (= :empty (first operand)) :empty
        :else (map list
                   (first operand)
                   (repeat (second operand))))

      (= "apndl" op)
      (cond
        (not (seq? operand)) :undefined
        (= :empty (second operand)) [(first operand)]
        :else (into [(first operand)] (second operand)))

      (= "apndr" op)
      (cond
        (not (seq? operand)) :undefined
        (= :empty (first operand)) [(second operand)]
        :else (concat (first operand) [(last operand)]))

      (= "rotl" op)
      (if (not (seq? operand))
        (if (= :empty operand) :empty :undefined)
        (conj (vec (rest operand)) (first operand)))

      (= "rotr" op)
      (if (not (seq? operand))
        (if (= :empty operand) :empty :undefined)
        (into (vector (last operand)) (butlast operand))))

    [{:composition compo} _]
    (reduce (fn [acc f] (invoke f acc)) operand compo)

    [{:construction constr} _]
    (map (fn [f sqc] (invoke f sqc)) constr (repeat operand))

    [{:insertion inser} _]
    (reduce (fn [acc more]
              (invoke (first inser) [acc more]))
            (first operand) (rest operand))

    [{:to-all f} _]
    (map (fn [op operand]
           (invoke op operand))
         (repeat (first f)) operand)

    [{:condition condi :true t :false f} _]
    (let [x (invoke condi operand)]
      (invoke (if (:boolean x) t f) operand))

    [{:bu bu} _]
    (invoke (first bu) [(second bu) operand])

    [{:while w} _]
    (loop [target operand]
      (if (:boolean (invoke (:predicate w) target))
        (recur (invoke (:function w) target))
        target))

    :else "Error"))

(defn evaluate [parsed-map]
  (let [operators (get-in parsed-map [:application :operators])
        operands (get-in parsed-map [:application :operands])]
    (invoke operators operands)))
