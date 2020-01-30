(ns fp.components.config
  (:require
   [fp.semantic :as ui]
   [fp.state :as state]))

(defn toggle-sidebar []
  [:> ui/button
   {:compact true
    :onClick #(swap! state/config update :menu? not)
    :style {:position "absolute"
            :top "0"
            :left "0"}}
   [:> ui/icon
    {:name "configure"
     :fitted true}]])

(defn sidebar []
  [:> ui/sidebar
   {:as ui/segment
    :textAlign "center"
    :animation "push"
    :direction "right"
    :visible (:menu? @state/config)}
   [:> ui/checkbox
    {:label "Historia"
     :toggle true
     :checked (:history? @state/config)
     :onClick #(swap! state/config update :history? not)}]])
