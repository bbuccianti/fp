(ns fp.core
  (:require
   [goog.dom :as gdom]
   [reagent.core :as rg]
   [re-frame.core :as rf]
   [fp.semantic :as ui]
   [fp.components.input :refer [readline]]
   [fp.components.output :refer [screen]]
   [fp.components.config :refer [sidebar]]))

(defn app []
  [:> ui/container
   {:id "container"}
   [sidebar]
   [screen]
   [readline]])

(defn mount-app []
  (when-let [el (gdom/getElement "app")]
    (rf/clear-subscription-cache!)
    (rg/render-component [app] el)))

(defn on-js-reload []
  (mount-app))

(defn ^:export main []
  (println "Let's get ready to ramble!")
  (rf/dispatch-sync [:initialize-db])
  (mount-app))
