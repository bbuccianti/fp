(ns fp.evaluator.interpreter
  (:require
   [clojure.core.match :refer [match]]
   [clojure.string :refer [replace]]))

;; Utilities
(def binary?   #(= 2 (count %)))
(def kempty?   #(= :empty %))
(def ksymbol?  #(contains? % :symbol))
(def kbool?    #(contains? % :boolean))
(def knumber?  #(contains? % :number))
(def math-op   #(if (and (binary? %2) (every? knumber? %2))
                  (apply %1 (map :number %2))
                  :undefined))

(defn wrap-empty [test ret]
  (if (empty? test) :empty ret))

(defn wrap-undefined [pred ret]
  (if pred ret :undefined))

(defn wrap-both [operand pred ret-empty ret]
  (if pred
    (ret operand)
    (if (kempty? operand) ret-empty :undefined)))

(defn wrap-appnd [test ret-empty pred ret]
  (if (kempty? test)
    ret-empty
    (if pred
      (ret test)
      :undefined)))

;; % is always the operand
(def symbols {"atom"    (comp not seq?)
              "id"      identity
              "null"    kempty?
              "eq"      #(wrap-undefined (binary? %) (apply = %))
              "not"     #(wrap-undefined (kbool? %) (not (:boolean %)))

              "and"     #(wrap-undefined
                          (and (binary? %) (every? kbool? %))
                          (every? identity (map :boolean %)))

              "or"      #(wrap-undefined
                          (and (binary? %) (every? kbool? %))
                          (some identity (map :boolean %)))

              "length"  #(wrap-undefined
                          (not (ksymbol? %))
                          (if (kempty? %) 0 (count %)))

              "reverse" #(wrap-undefined
                          (not (ksymbol? %))
                          (if (kempty? %) :empty (reverse %)))

              "trans"   #(wrap-undefined (seq? %) (apply map list %))
              "tl"      #(wrap-empty (rest %) (rest %))
              "tlr"     #(wrap-empty (butlast %) (butlast %))

              "distl"   #(wrap-undefined
                          (seq? %)
                          (if (kempty? (second %))
                            :empty
                            (map list (repeat (first %)) (second %))))

              "distr"   #(wrap-undefined
                          (seq? %)
                          (if (kempty? (first %))
                            :empty
                            (map list (first %) (repeat (second %)))))

              "rotl"    #(wrap-both
                          % (seq? %) :empty
                          (fn [operand] (conj (vec (rest operand))
                                          (first operand))))

              "rotr"    #(wrap-both
                          % (seq? %) :empty
                          (fn [operand]
                            (into (vector (last operand))
                                  (butlast operand))))

              "apndl"   #(wrap-appnd
                          (second %) [(first %)]
                          (and (seq? %) (seq? (first %)))
                          (fn [operand]
                            (into [(first %)] operand)))

              "apndr"   #(wrap-appnd
                          (first %) [(second %)]
                          (and (seq? %) (seq? (first %)))
                          (fn [operand]
                            (concat operand [(last %)])))

              "+"       #(math-op + %)
              "-"       #(math-op - %)
              "×"       #(math-op * %)
              "÷"       #(wrap-undefined (not= 0 (-> % last :number))
                                         (math-op / %))})

(defn apply-symbol [op operand]
  (cond
    (contains? symbols op)
    ((symbols op) operand)

    (boolean (re-matches #"\d+r" op))
    (let [i (-> op (replace "r" "") js/parseInt)]
      (if (>= (count operand) i)
        (nth (reverse operand) (dec i))
        :undefined))))

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
    (let [bool js/Boolean
          number js/Number
          res (apply-symbol op operand)]
      (match [(type res)]
        [bool]   {:boolean res}
        [number] {:number res}
        :else    res))

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
