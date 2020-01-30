(ns fp.core
  (:require
   [goog.dom :as gdom]
   [reagent.core :as r]
   [fp.semantic :as ui]
   [fp.components :refer
    [input-bar output-segments config-button sidebar]]))

(defn app []
  (let [in (r/atom "")]
    (fn []
      [:> ui/container
       {:id "container"}
       [config-button]
       [sidebar]
       [output-segments]
       [input-bar in]])))

(defn mount-app []
  (when-let [el (gdom/getElement "app")]
    (r/render-component [app] el)))

(defn on-js-reload []
  (mount-app))
