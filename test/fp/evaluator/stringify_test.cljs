(ns fp.evaluator.stringify-test
  (:require
   [cljs.test :refer [deftest are]]
   [fp.evaluator.stringify :refer [to-string]]))

(deftest stringys
  (are [exp act] (= exp (to-string act))
    "A" {:symbol "A" :type :symbol}
    "1" {:number 1}
    "T" {:boolean true}
    "F" {:boolean false}
    "⊥" :undefined
    "∅" :empty
    "∅" []
    "<A, T, 1>" [{:symbol "A"} {:boolean true} {:number 1}]
    "sub5 ?" {:error :err/symbol :target "sub5"}))
