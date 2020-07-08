(ns fp.core
  (:require
   [goog.dom :as gdom]
   [reagent.core :as rg]
   [re-frame.core :as rf]
   [fp.semantic :as ui]
   [fp.components.input :refer [readline selector]]
   [fp.components.output :refer [screen credits]]
   [fp.components.examples :refer [man]]))

(defn app []
  [:> ui/container
   {:id "container"}
   [credits]
   [screen]
   [readline]
   [man]
   [selector]])

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
