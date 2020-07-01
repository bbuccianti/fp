(ns fp.state
  (:require
   [reagent.core :as rg]
   [re-frame.core :as rf]))

(def db {:input ""
         :selector-input ""
         :index 0
         :output []
         :lang :es
         :selector? false
         :config {:history? true
                  :menu? false
                  :examples? true
                  :special-chars? true}
         :man {:selectors? false
               :predicates? false
               :arithmetics? false
               :logics? false
               :functions? false
               :definitions? false}
         :helpers [:write-expression :special-chars :docs
                   :report!]
         :user-defs {}})

(rf/reg-event-db
 :initialize-db
 (fn [old _]
   (merge old db)))
