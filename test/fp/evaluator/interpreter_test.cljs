(ns fp.evaluator.interpreter-test
  (:require
   [cljs.test :refer [deftest is are testing]]
   [fp.evaluator.parser :refer [parse]]
   [fp.evaluator.interpreter :refer [evaluate]]))

(deftest selectors
  (are [exp act] (= exp (-> act parse evaluate))
    {:sequence [{:string "A" :type :symbol}
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
  (are [exp act] (= exp (-> act parse evaluate))
    {:type :bool :val false} "and : <T, F>"
    {:type :undefined}       "and : <1, 0>"

    {:type :bool :val true}  "or : <T, F>"

    {:type :bool :val false} "not : T"
    {:type :bool :val true}  "not : F"
    {:type :undefined}       "not : 2"))

(deftest sequences
  (are [exp act] (= exp (-> act parse evaluate))
    {:type :undefined}     "length : A"
    {:type :number :val 0} "length : ∅"
    {:type :number :val 3} "length : <2, A, 7>"

    {:sequence [{:string "7" :type :number :val 7}
                {:type :symbol :string "A"}
                {:string "2" :type :number :val 2}]}
    "reverse : <2, A, 7>"

    {:type :undefined} "trans: A"
    (parse "<<1, A>, <2, B>, <3, C>>")
    "trans: <<1,2,3>,<A,B,C>>"

    (parse "<<A, 1>, <A, 2>, <A, 3>>") "distl : <A, <1, 2, 3>>"

    (parse "<<1,A>,<2,A>,<3,A>>") "distr: <<1,2,3>,A>"

    (parse "<<A,B>,C,D>") "apndl: <<A,B>,<C,D>>"
    (parse "<y>")         "apndl: <y, ∅>"

    (parse "<A,B,<C,D>>") "apndr:<<A,B>,<C,D>>"
    (parse "<y>")         "apndr: <∅, y>"

    (parse "<B,C,D,A>")   "rotl: <A,B,C,D>"
    {:type :empty}        "rotl: ∅"
    (parse "<y>")         "rotl: <y>"
    {:type :undefined}    "rotl: 1"

    (parse "<D,A,B,C>")   "rotr: <A,B,C,D>"
    {:type :empty}        "rotr: ∅"
    (parse "<y>")         "rotr: <y>"))

(deftest functional-forms
  (are [exp act] (= (parse exp) (-> act parse evaluate))
    "B"                    "1 ∘ tl: <A, B, C>"
    "<<B, C>, <A, B>>"     "[tl, tlr] : <A,B,C>"))
