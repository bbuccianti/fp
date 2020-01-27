(ns fp.evaluator.interpreter-test
  (:require
   [cljs.test :refer [deftest is are testing]]
   [fp.evaluator.parser :refer [parse]]
   [fp.evaluator.interpreter :refer [evaluate]]))

(deftest selectors
  (are [exp act] (= exp act)
    {:string "A" :type :symbol} (evaluate (parse "1 : <A, B, C>"))
    {:string "B" :type :symbol} (evaluate (parse "2 : <A, B, C>"))
    nil (evaluate (parse "4 : <A, B, C>"))
    nil (evaluate (parse "4r : <A, B, C>"))
    {:string "C" :type :symbol} (evaluate (parse "1r : <A, B, C>"))))
