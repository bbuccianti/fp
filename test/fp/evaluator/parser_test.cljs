(ns fp.evaluator.parser-test
  (:require
   [cljs.test :refer [deftest is are testing]]
   [fp.evaluator.parser :refer [parse]]))

(deftest objects
  (are [a b] (= a b)
    {:string "T" :type :bool :val true} (parse "T")
    {:string "F" :type :bool :val false} (parse "F")

    {:string "3" :type :number :val 3} (parse "3")
    {:string "1.5" :type :number :val 1.5} (parse "1.5")

    {:string "AB3" :type :symbol} (parse "AB3")

    {:string "⊥" :type :undefined} (parse "⊥")
    {:string "∅" :type :empty} (parse "∅")

    {:string "<AB,1,2.3>"
     :seq [{:string "AB" :type :symbol}
           {:string "1" :type :number :val 1}
           {:string "2.3" :type :number :val 2.3}]}
    (parse "<AB,1,2.3>")))
