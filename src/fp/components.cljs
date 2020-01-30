(ns fp.components
  (:require
   [goog.dom :as gdom]
   [reagent.core :as r]
   [fp.semantic :as ui]
   [fp.components.input :refer [repl]]
   [fp.components.output :refer [screen]]
   [fp.components.config :refer [toggle-sidebar sidebar]]))

(defn app []
  (let [in (r/atom "")]
    (fn []
      [:> ui/container
       {:id "container"}
       [toggle-sidebar]
       [sidebar]
       [screen]
       [repl in]])))
