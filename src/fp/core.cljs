(ns fp.core
  (:require
   [goog.dom :as gdom]
   [reagent.core :as r]
   [fp.semantic :as ui]))

(defn app []
  (fn []
    [:> ui/container {:style {:min-height "100vh"}}
     [:> ui/container]
     [:> ui/container {:style {:position "fixed"
                               :bottom "10px"}}
      [:> ui/input {:placeholder "Inserte una expresión..."
                    :action "Evaluar"
                    :fluid true
                    :size "huge"}]]]))

(defn mount-app []
  (when-let [el (gdom/getElement "app")]
    (r/render-component [app] el)))

(defn on-js-reload []
  (mount-app))
