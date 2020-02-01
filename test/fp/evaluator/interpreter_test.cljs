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
  (are [exp act] (= (parse exp) (-> act parse evaluate))
    "T"  "atom : 5"
    "F"  "atom : <A, B, C>"
    "⊥"  "atom : ⊥"

    "T"  "eq : <A, A>"
    "F"  "eq : <A, 7>"
    "⊥"  "eq : <A, B, C>"

    "T"  "null : ∅"
    "F"  "null : <A, 7>"
    "⊥"  "null : ⊥"))

(deftest arithmetic
  (are [exp act] (= (parse exp) (-> act parse evaluate))
    "3"   "+ : <1, 2>"
    "⊥"   "+ : <1, A>"
    "⊥"   "+ : <1, 2, 3>"

    "7"   "- : <9, 2>"
    "⊥"   "- : <9, A>"
    "⊥"   "- : <1, 2, 3>"

    "14"  "× : <2, 7>"
    "⊥"   "× : <A, 2>"
    "⊥"   "× : <1, 2, 3>"

    "5"   "÷ : <10, 2>"
    "⊥"   "÷ : <1, 0>"
    "⊥"   "÷ : <1, A>"
    "⊥"   "÷ : <1, 2, 3>"))

(deftest logic
  (are [exp act] (= (parse exp) (-> act parse evaluate))
    "F"  "and : <T, F>"
    "⊥"  "and : <1, 0>"

    "T"  "or : <T, F>"

    "F"  "not : T"
    "T"  "not : F"
    "⊥"  "not : 2"))

(deftest sequences
  (are [exp act] (= exp (-> act parse evaluate))
    (parse "⊥")     "length : A"
    {:type :number :val 0} "length : ∅"
    {:type :number :val 3} "length : <2, A, 7>"

    {:sequence [{:string "7" :type :number :val 7}
                {:type :symbol :string "A"}
                {:string "2" :type :number :val 2}]}
    "reverse : <2, A, 7>"

    (parse "⊥")    "trans: A"
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
    (parse "⊥")    "rotl: 1"

    (parse "<D,A,B,C>")   "rotr: <A,B,C,D>"
    {:type :empty}        "rotr: ∅"
    (parse "<y>")         "rotr: <y>"))

(deftest functional-forms
  (are [exp act] (= (parse exp) (-> act parse evaluate))
    "B"                    "1 ∘ tl: <A, B, C>"
    "<<B, C>, <A, B>>"     "[tl, tlr] : <A,B,C>"
    "A"                    "(not ∘ atom → 1; id) : <A, B, C>"
    "4"                    "‾4 : T"
    "<3, 1>"               "[id, ‾1]: 3"
    "4"                    "+ ∘ [id, ‾1]: 3"
    "6"                    "/+:<1,2,3>"
    "<A,4>"                "α 1 : <<A,B,C>,<4,5,6>>"
    "8"                    "+ ∘ α × : <<1,2>,<2,3>>"
    "20"                   "/+ ∘ α×:<<1,2>,<3,4>,<2,3>>"))
