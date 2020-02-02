(ns fp.evaluator.stringify-test
  (:require
   [cljs.test :refer [deftest are]]
   [fp.evaluator.stringify :refer [to-string]]))

#_(deftest stringys
  (are [exp act] (= exp (to-string act))
    "A" {:string "A" :type :symbol}
    "1" {:type :number :val 1}
    "T" {:type :bool :val true}
    "F" {:type :bool :val false}
    "⊥" {:type :undefined}
    "∅" {:type :empty}
    "∅" {:sequence []}
    "<A, T, 1>" {:sequence [{:string "A" :type :symbol}
                            {:type :bool :val true}
                            {:type :number :val 1}]}))
