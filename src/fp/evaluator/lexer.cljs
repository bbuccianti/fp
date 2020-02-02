(ns fp.evaluator.lexer
  (:require
   [clojure.core.match :refer [match]]
   [clojure.string :refer [split trim replace]]))

(defn str-number? [s]
  (not (js/isNaN s)))

(defn- translate [s]
  (match [s]
    [(:or "⊥" "∅")] (if (= "∅" s) :empty :undefined)
    [(:or "<" ">")] (if (= "<" s) :open-seq :close-seq)
    [(n :guard str-number?)] :number
    [(:or "T" "F")] :boolean
    [":"] :application
    ["/"] :insertion
    ["∘"] :composition
    ["α"] :to-all
    ["×"] :times
    ["÷"] :division
    :else :symbol))

(defn- translator [str-obj]
  {:string str-obj
   :type (translate str-obj)})

(defn lex [s]
  (->> (replace s #"," " ")
       (re-seq #" |[<>]|[A-Za-z0-9.]+|[⊥∅+:/∘α×÷]")
       (map trim)
       (remove empty?)
       (mapv translator)))

;#" |[0-9.]+|<|>[A-Za-z]+|[⊥∅:,;+-∘α×]"
