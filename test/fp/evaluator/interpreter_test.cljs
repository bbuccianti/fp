(ns fp.evaluator.interpreter-test
  (:require
   [cljs.test :refer [deftest is are testing]]
   [fp.evaluator.lexer :refer [lex]]
   [fp.evaluator.parser :refer [parse]]
   [fp.evaluator.interpreter :refer [evaluate]]))

(deftest selectors
  (are [exp act] (= exp (-> act lex parse evaluate))
    [{:symbol "A"} {:symbol "B"} {:symbol "C"}]
    "id: <A, B, C>"

    {:symbol "A"} "1 : <A, B, C>"
    {:symbol "B"} "2 : <A, B, C>"

    nil           "4 : <A, B, C>"
    {:symbol "C"} "1r : <A, B, C>"
    nil           "4r : <A, B, C>"

    [{:symbol "B"} {:symbol "C"}]
    "tl : <A, B, C>"

    [{:symbol "A"} {:symbol "B"}]
    "tlr : <A, B, C>"))

(deftest predicates
  (are [exp act] (= (-> exp lex parse) (-> act lex parse evaluate))
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
  (are [exp act] (= (-> exp lex parse) (-> act lex parse evaluate))
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
  (are [exp act] (= (-> exp lex parse) (-> act lex parse evaluate))
    "F"  "and : <T, F>"
    "⊥"  "and : <1, 0>"

    "T"  "or : <T, F>"

    "F"  "not : T"
    "T"  "not : F"
    "⊥"  "not : 2"))

(deftest sequences
  (are [exp act] (= exp (-> act lex parse evaluate))
    :undefined     "length : A"
    {:number 0}    "length : ∅"
    {:number 3}    "length : <2, A, 7>"

    [{:number 7} {:symbol "A"} {:number 2}]
    "reverse : <2, A, 7>"
    :empty     "reverse: ∅"
    :undefined "reverse: A"

    :undefined    "trans: A"
    (-> "<<1, A>, <2, B>, <3, C>>" lex parse)
    "trans: <<1,2,3>,<A,B,C>>"

    (-> "<<A, 1>, <A, 2>, <A, 3>>" lex parse)
    "distl: <A, <1, 2, 3>>"
    :empty "distl: <y, ∅>"
    :undefined "distl: A"

    (-> "<<1,A>,<2,A>,<3,A>>" lex parse)
    "distr: <<1,2,3>,A>"
    :empty "distr: <∅, a>"
    :undefined "distr: B"

    (-> "<<A,B>,C,D>" lex parse) "apndl: <<A,B>,<C,D>>"
    (-> "<y>" lex parse)         "apndl: <y, ∅>"
    :undefined                   "apndl: C"

    (-> "<A,B,<C,D>>" lex parse) "apndr:<<A,B>,<C,D>>"
    (-> "<y>" lex parse)         "apndr: <∅, y>"
    :undefined                   "apndr: D"

;    (parse "<B,C,D,A>")   "rotl: <A,B,C,D>"
 ;   {:type :empty}        "rotl: ∅"
  ;  (parse "<y>")         "rotl: <y>"
   ; (parse "⊥")    "rotl: 1"

;    (parse "<D,A,B,C>")   "rotr: <A,B,C,D>"
 ;;   {:type :empty}        "rotr: ∅"
   ;; (parse "<y>")         "rotr: <y>"
    ))

#_(deftest functional-forms
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
