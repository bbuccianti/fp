(ns fp.evaluator.interpreter-test
  (:require
   [cljs.test :refer [deftest is are testing]]
   [fp.evaluator.parser :refer [parse]]
   [fp.evaluator.interpreter :refer [evaluate]]))

(deftest selectors
  (are [exp act] (= exp act)
    {:string "<A, B, C>"
     :sequence [{:string "A" :type :symbol}
                {:string "B" :type :symbol}
                {:string "C" :type :symbol}]}
    (evaluate (parse "id : <A, B, C>"))

    {:string "A" :type :symbol} (evaluate (parse "1 : <A, B, C>"))
    {:string "B" :type :symbol} (evaluate (parse "2 : <A, B, C>"))
    nil (evaluate (parse "4 : <A, B, C>"))
    nil (evaluate (parse "4r : <A, B, C>"))
    {:string "C" :type :symbol} (evaluate (parse "1r : <A, B, C>"))

    {:sequence [{:string "B" :type :symbol} {:string "C" :type :symbol}]}
    (evaluate (parse "tl : <A, B, C>"))

    {:sequence [{:string "A" :type :symbol} {:string "B" :type :symbol}]}
    (evaluate (parse "tlr : <A, B, C>"))))

(deftest predicates
  (are [exp act] (= exp act)
    {:type :bool :val true} (evaluate (parse "atom : 5"))
    {:type :bool :val false} (evaluate (parse "atom : <A, B, C>"))
    {:type :undefined} (evaluate (parse "atom : ⊥"))

    {:type :bool :val true} (evaluate (parse "eq : <A, A>"))
    {:type :bool :val false} (evaluate (parse "eq : <A, 7>"))
    {:type :undefined} (evaluate (parse "eq : <A, B, C>"))

    {:type :bool :val true} (evaluate (parse "null : ∅"))
    {:type :bool :val false} (evaluate (parse "null : <A, 7>"))
    {:type :undefined} (evaluate (parse "null : ⊥"))))
