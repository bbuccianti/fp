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
    {:type :bool :val true}  (evaluate (parse "atom : 5"))
    {:type :bool :val false} (evaluate (parse "atom : <A, B, C>"))
    {:type :undefined}       (evaluate (parse "atom : ⊥"))

    {:type :bool :val true}  (evaluate (parse "eq : <A, A>"))
    {:type :bool :val false} (evaluate (parse "eq : <A, 7>"))
    {:type :undefined}       (evaluate (parse "eq : <A, B, C>"))

    {:type :bool :val true}  (evaluate (parse "null : ∅"))
    {:type :bool :val false} (evaluate (parse "null : <A, 7>"))
    {:type :undefined}       (evaluate (parse "null : ⊥"))))

(deftest arithmetic
  (are [exp act] (= exp act)
    {:type :number :val 3}   (evaluate (parse "+ : <1, 2>"))
    {:type :undefined}       (evaluate (parse "+ : <1, A>"))
    {:type :undefined}       (evaluate (parse "+ : <1, 2, 3>"))

    {:type :number :val 7}   (evaluate (parse "- : <9, 2>"))
    {:type :undefined}       (evaluate (parse "- : <9, A>"))
    {:type :undefined}       (evaluate (parse "- : <1, 2, 3>"))

    {:type :number :val 14}  (evaluate (parse "× : <2, 7>"))
    {:type :undefined}       (evaluate (parse "× : <A, 2>"))
    {:type :undefined}       (evaluate (parse "× : <1, 2, 3>"))

    {:type :number :val 5}   (evaluate (parse "÷ : <10, 2>"))
    {:type :undefined}       (evaluate (parse "÷ : <1, 0>"))
    {:type :undefined}       (evaluate (parse "÷ : <1, A>"))
    {:type :undefined}       (evaluate (parse "÷ : <1, 2, 3>"))))

(deftest logic
  (are [exp act] (= exp act)
    {:type :bool :val false} (evaluate (parse "and : <T, F>"))
    {:type :undefined}       (evaluate (parse "and : <1, 0>"))

    {:type :bool :val true}  (evaluate (parse "or : <T, F>"))

    {:type :bool :val false} (evaluate (parse "not : T"))
    {:type :bool :val true}  (evaluate (parse "not : F"))
    {:type :undefined}       (evaluate (parse "not : 2"))))
