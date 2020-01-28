(ns fp.core
  (:require
   [goog.dom :as gdom]
   [reagent.core :as r]
   [fp.semantic :as ui]
   [fp.components :refer [input-bar output-segments]]))

(defn app []
  (let [in (r/atom "")
        out (r/atom [])]
    (fn []
      [:> ui/container {:style {:min-height "100vh"}}
       (output-segments out)
       (input-bar in out)])))

(defn mount-app []
  (when-let [el (gdom/getElement "app")]
    (r/render-component [app] el)))

(defn on-js-reload []
  (mount-app))
