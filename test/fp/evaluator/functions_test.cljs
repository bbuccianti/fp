(ns fp.evaluator.functions-test
  (:require
   [cljs.test :refer [deftest is are testing use-fixtures]]
   [fp.evaluator.lexer :refer [lex]]
   [fp.evaluator.parser :refer [parse]]
   [fp.evaluator.interpreter :refer [evaluate]]
   [fp.evaluator.stringify :refer [to-string]]))

(use-fixtures :each
  {:before (fn []
             (-> "Def sub1 ≡ - ∘ [id, ‾1‾]" lex parse evaluate)
             (-> "Def eq1 ≡ eq ∘ [id, ‾1‾]" lex parse evaluate)
             (-> "Def fact ≡ (eq1 → ‾1‾; × ∘ [id, fact ∘ sub1])"
                 lex parse evaluate))
   :after (fn [])})

(deftest functions
  (are [exp act] (= exp (-> act lex parse evaluate to-string))
    ;;"<2, 4>"    "[id, (bu + 3) ∘ sub1]: 2"
    "1"         "sub1: 2"
    "T"         "eq1: 1"
    "F"         "eq1: 2"
    "T"         "eq1 ∘ sub1: 2"
    "120"       "fact: 5"))
