(ns fp.evaluator.lexer-test
  (:require
   [cljs.test :refer [deftest are]]
   [fp.evaluator.lexer :refer [lex]]))

(deftest objects
  (are [exp act] (= exp (lex act))
    {:string "⊥" :type :undefined}
    "⊥"

    {:string "∅" :type :empty}
    "∅"

    '({:string "<" :type :open-seq}
      {:string "T" :type :boolean}
      {:string "," :type :comma}
      {:string "F" :type :boolean}
      {:string ">" :type :close-seq})
    "<T,F>"

    '({:string "<" :type :open-seq}
      {:string "AB" :type :symbol}
      {:string "," :type :comma}
      {:string "1" :type :number}
      {:string "," :type :comma}
      {:string "2.3" :type :number}
      {:string ">" :type :close-seq})
    "<AB,1,2.3>"

    '({:string "(" :type :open-cond}
      {:string "not" :type :symbol}
      {:string "∘" :type :composition}
      {:string "atom" :type :symbol}
      {:string "→" :type :right}
      {:string "1" :type :number}
      {:string ";" :type :semicolon}
      {:string "id" :type :symbol}
      {:string ")" :type :close-cond})
    "(not ∘ atom → 1; id)"

    '({:string "+" :type :symbol}
      {:string ":" :type :application}
      {:string "<" :type :open-seq}
      {:string "1" :type :number}
      {:string "," :type :comma}
      {:string "2" :type :number}
      {:string ">" :type :close-seq})
    "+:<1,2>"

    '({:string "/" :type :insertion}
      {:string "×" :type :symbol}
      {:string "∘" :type :composition}
      {:string "α" :type :to-all}
      {:string "÷" :type :symbol}
      {:string ":" :type :application}
      {:string "<" :type :open-seq}
      {:string "<" :type :open-seq}
      {:string "6" :type :number}
      {:string "," :type :comma}
      {:string "3" :type :number}
      {:string ">" :type :close-seq}
      {:string "," :type :comma}
      {:string "<" :type :open-seq}
      {:string "4" :type :number}
      {:string "," :type :comma}
      {:string "2" :type :number}
      {:string ">" :type :close-seq}
      {:string "," :type :comma}
      {:string "<" :type :open-seq}
      {:string "8" :type :number}
      {:string "," :type :comma}
      {:string "4" :type :number}
      {:string ">" :type :close-seq}
      {:string ">" :type :close-seq})
    "/× ∘ α÷ : <<6,3>,<4,2>,<8,4>>"

    '({:string "[" :type :open-bra}
      {:string "tl" :type :symbol}
      {:string "," :type :comma}
      {:string "tlr" :type :symbol}
      {:string "]" :type :close-bra}
      {:string ":" :type :application}
      {:string "<" :type :open-seq}
      {:string "A" :type :symbol}
      {:string "," :type :comma}
      {:string "B" :type :symbol}
      {:string "," :type :comma}
      {:string "C" :type :symbol}
      {:string ">" :type :close-seq})
    "[tl, tlr] : <A,B,C>"

    '({:string "[" :type :open-bra}
      {:string "id" :type :symbol}
      {:string "," :type :comma}
      {:string "‾1‾" :type :constant}
      {:string "]" :type :close-bra}
      {:string ":" :type :application}
      {:string "3" :type :number})
    "[id, ‾1‾]: 3"

    '({:string "[" :type :open-bra}
      {:string "id" :type :symbol}
      {:string "," :type :comma}
      {:string "‾-1‾" :type :constant}
      {:string "]" :type :close-bra}
      {:string ":" :type :application}
      {:string "3" :type :number})
    "[id, ‾-1‾]: 3"))
