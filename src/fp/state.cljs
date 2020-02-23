(ns fp.state
  (:require
   [reagent.core :as r]))

(defonce input (r/atom ""))

(defonce counter (r/atom 0))

(defonce out (r/atom []))

(defonce config (r/atom {:history? true
                         :menu? false
                         :special-chars? true}))
