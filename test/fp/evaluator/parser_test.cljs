(ns fp.evaluator.parser-test
  (:require
   [cljs.test :refer [deftest is are testing]]
   [fp.evaluator.parser :refer [parse]]))

(deftest objects
  (are [exp act] (= exp (parse act))
    {:string "T" :type :bool :val true} "T"
    {:string "F" :type :bool :val false} "F"

    {:string "3" :type :number :val 3} "3"
    {:string "1.5" :type :number :val 1.5} "1.5"

    {:string "AB3" :type :symbol} "AB3"

    {:string "⊥" :type :undefined} "⊥"
    {:string "∅" :type :empty} "∅"

    {:string "<AB,1,2.3>"
     :sequence [{:string "AB" :type :symbol}
                {:string "1" :type :number :val 1}
                {:string "2.3" :type :number :val 2.3}]}
    "<AB,1,2.3>"

    {:string "<A, <<B>, C>, D>"
     :sequence [{:string "A" :type :symbol}
           {:string "<<B>, C>"
            :sequence [{:string "<B>"
                        :sequence [{:string "B" :type :symbol}]}
                       {:string "C" :type :symbol}]}
           {:string "D" :type :symbol}]}
    "<A, <<B>, C>, D>"))

(deftest functions
  (are [exp act] (= exp (parse act))
    {:string "1 : <A, B, C>"
     :application {:operator {:string "1" :type :number :val 1}
                   :operand {:string "<A, B, C>"
                             :sequence [{:string "A" :type :symbol}
                                        {:string "B" :type :symbol}
                                        {:string "C" :type :symbol}]}}}
    "1 : <A, B, C>"

    {:string "+ : <1, 2>"
     :application {:operator {:string "+" :type :symbol}
                   :operand {:string "<1, 2>"
                             :sequence [{:string "1" :type :number :val 1}
                                        {:string "2" :type :number :val 2}]}}}
    "+ : <1, 2>"

    {:string "- : <9, 2>"
     :application {:operator {:string "-" :type :symbol}
                   :operand {:string "<9, 2>"
                             :sequence [{:string "9" :type :number :val 9}
                                        {:string "2" :type :number :val 2}]}}}
    "- : <9, 2>"))
