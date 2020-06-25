(ns fp.evaluator.interpreter-test
  (:require
   [cljs.test :refer [deftest is are testing]]
   [fp.evaluator.lexer :refer [lex]]
   [fp.evaluator.parser :refer [parse]]
   [fp.evaluator.interpreter :refer [evaluate]]))

(deftest selectors
  (are [exp act] (= exp (-> act lex parse evaluate))
    '({:symbol "A"} {:symbol "B"} {:symbol "C"})
    "id: <A, B, C>"

    {:symbol "A"} "1 : <A, B, C>"
    {:symbol "B"} "2 : <A, B, C>"

    :undefined           "4 : <A, B, C>"
    {:symbol "C"}        "1r : <A, B, C>"
    :undefined           "4r : <A, B, C>"

    '({:symbol "B"} {:symbol "C"})
    "tl : <A, B, C>"

    :empty
    "tl: <A>"

    '({:symbol "A"} {:symbol "B"})
    "tlr : <A, B, C>"

    :empty
    "tlr: <A>"))

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
    "⊥"  "null : ⊥"

    "T"  "null ∘ tl: <H>"))

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
  (are [exp act] (= (-> exp lex parse) (-> act lex parse evaluate))
    "⊥"    "length : A"
    "0"    "length : ∅"
    "3"    "length : <2, A, 7>"

    "<7,A,2>" "reverse : <2, A, 7>"
    "∅"       "reverse: ∅"
    "⊥"       "reverse: A"

    "⊥"    "trans: A"
    "<<1, A>, <2, B>, <3, C>>" "trans: <<1,2,3>,<A,B,C>>"

    "<<A, 1>, <A, 2>, <A, 3>>" "distl: <A, <1, 2, 3>>"
    "∅" "distl: <y, ∅>"
    "⊥" "distl: A"

    "<<1,A>,<2,A>,<3,A>>" "distr: <<1,2,3>,A>"
    "∅" "distr: <∅, a>"
    "⊥" "distr: B"

    "<<A,B>,C,D>" "apndl: <<A,B>,<C,D>>"
    "<y>"         "apndl: <y, ∅>"
    "⊥"           "apndl: C"

    "<A,B,<C,D>>" "apndr:<<A,B>,<C,D>>"
    "<y>"         "apndr: <∅, y>"
    "⊥"           "apndr: D"

    "<B,C,D,A>"   "rotl: <A,B,C,D>"
    "∅"           "rotl: ∅"
    "<y>"         "rotl: <y>"
    "⊥"           "rotl: 1"

    "<D,A,B,C>"   "rotr: <A,B,C,D>"
    "∅"           "rotr: ∅"
    "<y>"         "rotr: <y>"
    "⊥"           "rotr: 1"))

(deftest functional-forms
  (are [exp act] (= (-> exp lex parse) (-> act lex parse evaluate))
    "B"                    "1 ∘ tl: <A, B, C>"
    "<<B, C>, <A, B>>"     "[tl, tlr] : <A,B,C>"
    "A"                    "(not ∘ atom → 1; id): <A, B, C>"
    "4"                    "‾4‾ : T"
    "<3, 1>"               "[id, ‾1‾]: 3"
    "<6,<6,6>>"            "[id, [id, id]]: 6"
    "4"                    "+ ∘ [id, ‾1‾]: 3"
    "6"                    "/+:<1,2,3>"
    "<A,4>"                "α 1 : <<A,B,C>,<4,5,6>>"
    "8"                    "+ ∘ α × : <<1,2>,<2,3>>"
    "20"                   "/+ ∘ α×:<<1,2>,<3,4>,<2,3>>"
    "<H>"                  "(while (not ∘ null ∘ tl) tl): <A,B,H>"))

(deftest binary-to-unary
  (are [exp act] (= exp (-> act lex parse evaluate))
    {:number -1}   "(bu - 2): 3"
    {:number -10}  "(bu - 10): 20"
    {:number 3}    "(bu ÷ 18) ∘ (bu + 2): 4"))

(deftest difficult-sequences
  (are [exp act] (= (-> exp lex parse) (-> act lex parse evaluate))
    "<2,3,<4,5>>" "apndr ∘ [[id,‾3‾],[‾4‾,‾5‾]]: 2"))

(deftest very-difficult
  (are [exp act] (= exp (-> act lex parse evaluate))
    {:number 8} "+ ∘ [(bu + 2) ∘ id, id]: 3"
    '({:number 5} {:number 3}) "[(bu + 2) ∘ id, id]: 3"
    '({:number 3} {:number 5}) "[id, (bu + 2) ∘ id]: 3"
    {:number 30} "-∘[(bu × 6) ∘ id,id]∘1∘(while (not∘null∘tl) tl): <10,9,8,7,6>"
    {:number 6}  "(bu + 2) ∘ (bu + 2): 2"
    '({:number 2} {:number 6}) "[id, (bu + 2) ∘ (bu + 2)]: 2"))
