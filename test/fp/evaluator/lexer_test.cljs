(ns fp.evaluator.lexer-test
  (:require
   [cljs.test :refer [deftest are]]
   [fp.evaluator.lexer :refer [lex]]))

(deftest objects
  (are [exp act] (= exp (lex act))
    [{:string "⊥" :type :undefined}]
    "⊥"

    [{:string "∅" :type :empty}]
    "∅"

    [{:string "<" :type :open-seq}
     {:string "T" :type :boolean}
     {:string "F" :type :boolean}
     {:string ">" :type :close-seq}]
    "<T,F>"

    [{:string "<" :type :open-seq}
     {:string "AB" :type :symbol}
     {:string "1" :type :number}
     {:string "2.3" :type :number}
     {:string ">" :type :close-seq}]
    "<AB,1,2.3>"

    [{:string "+" :type :symbol}
     {:string ":" :type :application}
     {:string "<" :type :open-seq}
     {:string "1" :type :number}
     {:string "2" :type :number}
     {:string ">" :type :close-seq}]
    "+:<1,2>"

    [{:string "/" :type :insertion}
     {:string "×" :type :times}
     {:string "∘" :type :composition}
     {:string "α" :type :to-all}
     {:string "÷" :type :division}
     {:string ":" :type :application}
     {:string "<" :type :open-seq}
     {:string "<" :type :open-seq}
     {:string "6" :type :number}
     {:string "3" :type :number}
     {:string ">" :type :close-seq}
     {:string "<" :type :open-seq}
     {:string "4" :type :number}
     {:string "2" :type :number}
     {:string ">" :type :close-seq}
     {:string "<" :type :open-seq}
     {:string "8" :type :number}
     {:string "4" :type :number}
     {:string ">" :type :close-seq}
     {:string ">" :type :close-seq}]
    "/× ∘ α÷ : <<6,3>,<4,2>,<8,4>>"))
