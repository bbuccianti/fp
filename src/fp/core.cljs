(ns fp.core
  (:require
   [goog.dom :as gdom]
   [reagent.core :as r]
   [fp.state :as state]
   [fp.semantic :as ui]
   [fp.components.input :refer [readline]]
   [fp.components.output :refer [screen]]
   [fp.components.config :refer [sidebar]]))

(defn app []
  (fn []
    [:> ui/container
     {:id "container"}
     [sidebar]
     [screen]
     [readline state/input]]))

(defn mount-app []
  (when-let [el (gdom/getElement "app")]
    (r/render-component [app] el)))

(defn on-js-reload []
  (mount-app))

(mount-app)
