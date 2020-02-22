(ns fp.core
  (:require
   [goog.dom :as gdom]
   [reagent.core :as r]
   [fp.semantic :as ui]
   [fp.components.input :refer [readline]]
   [fp.components.output :refer [screen]]
   [fp.components.config :refer [sidebar]]))

(defn app []
  (let [in (r/atom "")]
    (fn []
      [:> ui/container
       {:id "container"}
       [sidebar]
       [screen]
       [readline in]])))

(defn mount-app []
  (when-let [el (gdom/getElement "app")]
    (r/render-component [app] el)))

(defn on-js-reload []
  (mount-app))

(mount-app)
