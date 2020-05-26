(ns fp.state
  (:require
   [reagent.core :as rg]
   [re-frame.core :as rf]))

(def db {:input ""
         :index 0
         :output []
         :history? true
         :menu? false
         :examples? true
         :special-chars? true
         :man {:selectors? true
               :predicates? true
               :arithmetics? true}})

(rf/reg-event-db
 :initialize-db
 (fn [old _]
   (merge old db)))
