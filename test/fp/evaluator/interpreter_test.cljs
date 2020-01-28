(ns fp.evaluator.interpreter-test
  (:require
   [cljs.test :refer [deftest is are testing]]
   [fp.evaluator.parser :refer [parse]]
   [fp.evaluator.interpreter :refer [evaluate]]))

(deftest selectors
  (are [exp act] (= exp (-> act parse evaluate))
    {:string "<A, B, C>"
     :sequence [{:string "A" :type :symbol}
                {:string "B" :type :symbol}
                {:string "C" :type :symbol}]}
    "id : <A, B, C>"

    {:string "A" :type :symbol} "1 : <A, B, C>"
    {:string "B" :type :symbol} "2 : <A, B, C>"
    nil "4 : <A, B, C>"

    nil "4r : <A, B, C>"
    {:string "C" :type :symbol} "1r : <A, B, C>"

    {:sequence [{:string "B" :type :symbol} {:string "C" :type :symbol}]}
    "tl : <A, B, C>"

    {:sequence [{:string "A" :type :symbol} {:string "B" :type :symbol}]}
    "tlr : <A, B, C>"))

(deftest predicates
  (are [exp act] (= exp (-> act parse evaluate))
    {:type :bool :val true}  "atom : 5"
    {:type :bool :val false} "atom : <A, B, C>"
    {:type :undefined}       "atom : ⊥"

    {:type :bool :val true}  "eq : <A, A>"
    {:type :bool :val false} "eq : <A, 7>"
    {:type :undefined}       "eq : <A, B, C>"

    {:type :bool :val true}  "null : ∅"
    {:type :bool :val false} "null : <A, 7>"
    {:type :undefined}       "null : ⊥"))

(deftest arithmetic
  (are [exp act] (= exp (-> act parse evaluate))
    {:type :number :val 3}   "+ : <1, 2>"
    {:type :undefined}       "+ : <1, A>"
    {:type :undefined}       "+ : <1, 2, 3>"

    {:type :number :val 7}   "- : <9, 2>"
    {:type :undefined}       "- : <9, A>"
    {:type :undefined}       "- : <1, 2, 3>"

    {:type :number :val 14}  "× : <2, 7>"
    {:type :undefined}       "× : <A, 2>"
    {:type :undefined}       "× : <1, 2, 3>"

    {:type :number :val 5}   "÷ : <10, 2>"
    {:type :undefined}       "÷ : <1, 0>"
    {:type :undefined}       "÷ : <1, A>"
    {:type :undefined}       "÷ : <1, 2, 3>"))

(deftest logic
  (are [exp act] (= exp act)
    {:type :bool :val false} (evaluate (parse "and : <T, F>"))
    {:type :undefined}       (evaluate (parse "and : <1, 0>"))

    {:type :bool :val true}  (evaluate (parse "or : <T, F>"))

    {:type :bool :val false} (evaluate (parse "not : T"))
    {:type :bool :val true}  (evaluate (parse "not : F"))
    {:type :undefined}       (evaluate (parse "not : 2"))))
