(ns fp.evaluator.parser-test
  (:require
   [cljs.test :refer [deftest is are testing]]
   [fp.evaluator.parser :refer [parse]]
   [fp.evaluator.lexer :refer [lex]]))

(deftest objects
  (are [exp act] (= exp (-> act lex parse))
    {:boolean true} "T"
    {:boolean false} "F"

    {:number 3} "3"
    {:number 1.5} "1.5"

    {:symbol "AB3"} "AB3"

    :undefined "⊥"
    :empty "∅"

    [{:symbol "AB"} {:number 1} {:number 2.3}]
    "<AB,1,2.3>"

    [{:symbol "A"} [[{:symbol "B"}] {:symbol "C"}] {:symbol "D"}]
    "<A, <<B>, C>, D>"))

#_(deftest functions
  (are [exp act] (= exp (parse act))
    {:application {:operator {:string "1" :type :number :val 1}
                   :operand {:sequence [{:string "A" :type :symbol}
                                        {:string "B" :type :symbol}
                                        {:string "C" :type :symbol}]}}}
    "1 : <A, B, C>"

    {:application {:operator {:string "+" :type :symbol}
                   :operand {:sequence [{:string "1" :type :number :val 1}
                                        {:string "2" :type :number :val 2}]}}}
    "+ : <1, 2>"

    {:application {:operator {:string "-" :type :symbol}
                   :operand {:sequence [{:string "9" :type :number :val 9}
                                        {:string "2" :type :number :val 2}]}}}
    "- : <9, 2>"))

#_(deftest functional-forms
  (are [exp act] (= exp (parse act))
    {:composition {:functions [{:string "tl" :type :symbol}
                               {:string "1" :type :number :val 1}]
                   :operand {:sequence [{:string "A" :type :symbol}
                                        {:string "B" :type :symbol}
                                        {:string "C" :type :symbol}]}}}
    "1 ∘ tl: <A, B, C>"

    {:construction {:functions [{:string "tl" :type :symbol}
                                {:string "tlr" :type :symbol}]
                    :operand {:sequence [{:string "A" :type :symbol}
                                         {:string "B" :type :symbol}
                                         {:string "C" :type :symbol}]}}}
    "[tl, tlr] : <A,B,C>"

    {:condition {:functions [{:string "atom" :type :symbol}
                             {:string "not" :type :symbol}]
                 :true {:string "1" :type :number :val 1}
                 :false {:string "id" :type :symbol}
                 :operand {:sequence [{:string "A" :type :symbol}
                                      {:string "B" :type :symbol}
                                      {:string "C" :type :symbol}]}}}
    "(not ∘ atom → 1; id) : <A, B, C>"

    {:composition
     {:functions [{:construction
                   {:functions [{:string "id" :type :symbol}
                                {:string "‾1" :type :constant :val 1}]}}
                  {:string "+" :type :symbol}]
      :operand {:string "3" :type :number :val 3}}}
    "+ ∘ [id, ‾1]: 3"

    {:insertion
     {:function {:string "+" :type :symbol}
      :operand {:sequence [{:string "1" :type :number :val 1}
                           {:string "2" :type :number :val 2}
                           {:string "3" :type :number :val 3}]}}}
    "/+:<1,2,3>"

    {:to-all
     {:function {:string "1" :type :number :val 1}
      :operand (parse "<<A,B,C>,<4,5,6>>")}}
    "α 1 : <<A,B,C>,<4,5,6>>"

    {:composition
        {:functions [{:to-all {:function {:string "×" :type :symbol}}}
                     {:string "+" :type :symbol}]
         :operand (parse "<<1,2>,<2,3>>")}}
    "+ ∘ α × : <<1,2>,<2,3>>"

    {:composition
     {:functions [{:to-all {:function {:string "×" :type :symbol}}}
                  {:insertion {:function {:string "+" :type :symbol}}}]
      :operand (parse "<<1,2>,<3,4>,<2,3>>")}}
    "/+ ∘ α×:<<1,2>,<3,4>,<2,3>>"))
