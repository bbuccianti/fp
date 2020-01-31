(ns fp.components.config
  (:require
   [fp.semantic :as ui]
   [fp.state :as state]))

(defn toggle-sidebar []
  (let [on (:menu? @state/config)]
    [:> ui/button
     {:toggle true
      :attached "bottom"
      :color (if on "red" "blue")
      :onClick #(swap! state/config update :menu? not)}
     [:> ui/icon
      {:name (if on "close" "bars")
       :fitted true}]]))

(defn toggle-specials []
  [:> ui/button
   {:toggle true
    :attached "bottom"
    :active (:special-chars? @state/config)
    :onClick #(swap! state/config update :special-chars? not)}
   [:> ui/icon
    {:name "keyboard outline"
     :fitted true}]])

(defn sidebar []
  [:> ui/sidebar
   {:as ui/segment
    :animation "push"
    :direction "right"
    :visible (:menu? @state/config)}
   [:> ui/checkbox
    {:label "Historia"
     :toggle true
     :checked (:history? @state/config)
     :onClick #(swap! state/config update :history? not)
     :style {:margin-bottom "15px"}}]
   [:> ui/checkbox
    {:label "Caracteres especiales"
     :toggle true
     :checked (:special-chars? @state/config)
     :onClick #(swap! state/config update :special-chars? not)}]])
