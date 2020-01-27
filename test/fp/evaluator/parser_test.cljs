(ns fp.evaluator.parser-test
  (:require
   [cljs.test :refer [deftest is are testing]]
   [fp.evaluator.parser :refer [parse]]))

(deftest objects
  (are [exp act] (= exp act)
    {:string "T" :type :bool :val true} (parse "T")
    {:string "F" :type :bool :val false} (parse "F")

    {:string "3" :type :number :val 3} (parse "3")
    {:string "1.5" :type :number :val 1.5} (parse "1.5")

    {:string "AB3" :type :symbol} (parse "AB3")

    {:string "⊥" :type :undefined} (parse "⊥")
    {:string "∅" :type :empty} (parse "∅")

    {:string "<AB,1,2.3>"
     :sequence [{:string "AB" :type :symbol}
                {:string "1" :type :number :val 1}
                {:string "2.3" :type :number :val 2.3}]}
    (parse "<AB,1,2.3>")

    {:string "<A, <<B>, C>, D>"
     :sequence [{:string "A" :type :symbol}
           {:string "<<B>, C>"
            :sequence [{:string "<B>"
                        :sequence [{:string "B" :type :symbol}]}
                       {:string "C" :type :symbol}]}
           {:string "D" :type :symbol}]}
    (parse "<A, <<B>, C>, D>")))

(deftest functions
  (are [exp act] (= exp act)
    {:string "1 : <A, B, C>"
     :application {:operator {:string "1" :type :number :val 1}
                   :operand {:string "<A, B, C>"
                             :sequence [{:string "A" :type :symbol}
                                        {:string "B" :type :symbol}
                                        {:string "C" :type :symbol}]}}}
    (parse "1 : <A, B, C>")

    {:string "+ : <1, 2>"
     :application {:operator {:string "+" :type :symbol}
                   :operand {:string "<1, 2>"
                             :sequence [{:string "1" :type :number :val 1}
                                        {:string "2" :type :number :val 2}]}}}
    (parse "+ : <1, 2>")

    {:string "- : <9, 2>"
     :application {:operator {:string "-" :type :symbol}
                   :operand {:string "<9, 2>"
                             :sequence [{:string "9" :type :number :val 9}
                                        {:string "2" :type :number :val 2}]}}}
    (parse "- : <9, 2>")))
