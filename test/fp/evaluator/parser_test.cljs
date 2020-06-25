(ns fp.evaluator.parser-test
  (:require
   [cljs.test :refer [deftest is are testing]]
   [fp.evaluator.parser :refer [parse group-operators]]
   [fp.evaluator.lexer :refer [lex]]))

(deftest objects
  (are [exp act] (= exp (-> act lex parse))
    {:boolean true} "T"
    {:boolean false} "F"

    {:number 3} "3"
    {:number 1.5} "1.5"

    {:symbol "AB3"} "AB3"

    :undefined "⊥"
    :empty "∅"

    '({:symbol "AB"} {:number 1} {:number 2.3})
    "<AB,1,2.3>"

    '({:symbol "A"} (({:symbol "B"}) {:symbol "C"}) {:symbol "D"})
    "<A, <<B>, C>, D>"))

(deftest functions
  (are [exp act] (= exp (-> act lex parse))
    {:application
     {:operators {:number 1}
      :operands '({:symbol "A"} {:symbol "B"} {:symbol "C"})}}
    "1 : <A, B, C>"

    {:application
     {:operators {:symbol "1r"}
      :operands '({:symbol "A"} {:symbol "B"} {:symbol "C"})}}
    "1r:<A,B,C>"

    {:application
     {:operators {:symbol "+"}
      :operands '({:number 1} {:number 2})}}
    "+ : <1, 2>"

    {:application
     {:operators {:symbol "-"}
      :operands '({:number 9} {:number 2})}}
    "- : <9,2>"))

(deftest functional-forms
  (are [exp act] (= exp (-> act lex parse))
    {:application
     {:operators {:composition '({:symbol "tl"} {:number 1})}
      :operands '({:symbol "A"} {:symbol "B"} {:symbol "C"})}}
    "1 ∘ tl: <A, B, C>"

    {:application
     {:operators {:construction '({:symbol "tl"} {:symbol "tlr"})}
      :operands '({:symbol "A"} {:symbol "B"} {:symbol "C"})}}
    "[tl, tlr] : <A,B,C>"

    {:application
     {:operators {:construction
                  '({:symbol "id"}
                    {:construction ({:symbol "id"} {:symbol "id"})})}
      :operands {:number 6}}}
    "[id, [id, id]]: 6"

    {:application
     {:operators
      {:condition {:composition '({:symbol "atom"} {:symbol "not"})}
       :true {:number 1}
       :false {:symbol "id"}}
      :operands '({:symbol "A"} {:symbol "B"} {:symbol "C"})}}
    "(not ∘ atom → 1; id): <A, B, C>"

    {:application
     {:operators
      {:composition '({:construction ({:symbol "id"} {:constant 1})}
                      {:symbol "+"})}
      :operands {:number 3}}}
    "+ ∘ [id, ‾1‾]: 3"

    {:application
     {:operators {:insertion '({:symbol "+"})}
      :operands '({:number 1} {:number 2} {:number 3})}}
    "/+:<1,2,3>"

    {:application
     {:operators {:to-all '({:number 1})}
      :operands (parse (lex "<<A,B,C>,<4,5,6>>"))}}
    "α 1 : <<A,B,C>,<4,5,6>>"

    {:application
     {:operators {:composition '({:to-all ({:symbol "×"})}
                                 {:symbol "+"})}
      :operands (parse (lex "<<1,2>,<2,3>>"))}}
    "+ ∘ α × : <<1,2>,<2,3>>"

    {:application
     {:operators
      {:composition
       '({:to-all ({:symbol "×"})}
         {:insertion ({:symbol "+"})})}
      :operands (parse (lex "<<1,2>,<3,4>,<2,3>>"))}}
    "/+ ∘ α×:<<1,2>,<3,4>,<2,3>>"

    {:application
     {:operators {:bu '({:symbol "+"} {:number 1})}
      :operands {:number 2}}}
    "(bu + 1): 2"

    {:application
     {:operators {:composition
                  '({:bu ({:symbol "+"} {:number 2})}
                    {:bu ({:symbol "÷"} {:number 2})})}
      :operands {:number 4}}}
    "(bu ÷ 2) ∘ (bu + 2): 4"

    {:application
     {:operators
      {:while {:predicate {:composition '({:symbol "tl"}
                                          {:symbol "null"}
                                          {:symbol "not"})}
               :function {:symbol "tl"}}}
      :operands '({:symbol "A"} {:symbol "B"} {:symbol "H"})}}
    "(while (not ∘ null ∘ tl) tl): <A,B,H>"))

(deftest very-difficult-parsing
  (are [exp act] (= exp (-> act lex parse))
    {:application
     {:operators
      {:construction '({:composition
                        ({:symbol "id"}
                         {:bu ({:symbol "+"} {:number 2})})}
                       {:symbol "id"})}
      :operands {:number 3}}}
    "[(bu + 2) ∘ id, id]: 3"

    {:application
     {:operators
      {:composition
       '({:construction ({:composition
                          ({:symbol "id"}
                           {:bu ({:symbol "+"} {:number 2})})}
                         {:symbol "id"})}
         {:symbol "+"})}
      :operands {:number 3}}}
    "+ ∘ [(bu + 2) ∘ id, id]: 3"))

(deftest grouping-operators
  (are [exp act] (= exp (->> act lex group-operators))
    (list (lex "/-")
          {:string "∘" :type :composition}
          {:string "+" :type :symbol}
          {:string "∘" :type :composition}
          (lex "α ×"))
    "/- ∘ + ∘ α ×"

    (list (lex "(bu + 2)")
          {:string "∘" :type :composition}
          {:string "id" :type :symbol}
          {:string "," :type :comma}
          {:string "id" :type :symbol})
    "(bu + 2) ∘ id, id"

    (list (lex "[(bu + 2) ∘ id, id]"))
    "[(bu + 2) ∘ id, id]"

    (list (lex "(not ∘ atom → 1; id)"))
    "(not ∘ atom → 1; id)"

    (list (lex "(while (not ∘ null ∘ tl) tl)"))
    "(while (not ∘ null ∘ tl) tl)"

    (list (lex "(bu ÷ 2)")
          {:string "∘" :type :composition}
          (lex "(bu + 2)"))
    "(bu ÷ 2) ∘ (bu + 2)"))

(deftest functioning
  (are [exp act] (= exp (-> act lex parse))
    {:definition
     {:symbol "sub1"
      :body {:composition
             '({:construction ({:symbol "id"} {:constant 1})}
               {:symbol "-"})}}}
    "Def sub1 ≡ - ∘ [id, ‾1‾]"))
