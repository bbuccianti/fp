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
    [(:or "[" "]")] (if (= "[" s) :open-bra :close-bra)
    [(:or "(" ")")] (if (= "(" s) :open-cond :close-cond)
    [(n :guard str-number?)] :number
    [(n :guard #(boolean (re-matches #"‾-?\d+" s)))] :constant
    [(:or "T" "F")] :boolean
    [":"] :application
    ["/"] :insertion
    ["∘"] :composition
    ["α"] :to-all
    ["→"] :right
    [","] :comma
    [";"] :semicolon
    :else :symbol))

(defn- translator [str-obj]
  {:string str-obj
   :type (translate str-obj)})

(defn lex [s]
  (->> (re-seq #" |[<>\[\]\(\)]|[A-Za-z0-9.]+|[⊥∅+:/∘α×÷\-→;,]|‾-?\d+" s)
       (map trim)
       (remove empty?)
       (mapv translator)))
