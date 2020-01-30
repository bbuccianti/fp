(ns fp.state
  (:require
   [reagent.core :as r]))

(defonce counter (r/atom 0))

(defonce out (r/atom []))

(defonce config (r/atom {:history? false
                         :menu? false}))
