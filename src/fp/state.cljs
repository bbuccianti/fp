(ns fp.state
  (:require
   [reagent.core :as rg]
   [re-frame.core :as rf]))

(def db {:input ""
         :index 0
         :output []
         :lang :es
         :config {:history? true
                  :menu? false
                  :examples? true
                  :special-chars? true}
         :man {:selectors? false
               :predicates? false
               :arithmetics? false
               :logics? false
               :functions? false}})

(rf/reg-event-db
 :initialize-db
 (fn [old _]
   (merge old db)))
