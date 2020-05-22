(ns fp.components.config
  (:require
   [re-frame.core :as rf]
   [fp.semantic :as ui]))

(rf/reg-sub
 :config/special-chars?
 (fn [db _]
   (:special-chars? db)))

(rf/reg-event-db
 :config/toggle-specials-chars!
 (fn [db _]
   (update db :special-chars? not)))

(rf/reg-sub
 :config/menu?
 (fn [db _]
   (:menu? db)))

(rf/reg-event-db
 :config/toggle-menu!
 (fn [db _]
   (update db :menu? not)))

(rf/reg-sub
 :config/history?
 (fn [db _]
   (:history? db)))

(rf/reg-event-db
 :config/toggle-history!
 (fn [db _]
   (update db :history? not)))

(defn toggle-sidebar []
  (let [on (rf/subscribe [:config/menu?])]
    [:> ui/button
     {:toggle true
      :attached "bottom"
      :color (if @on "red" "blue")
      :onClick #(rf/dispatch [:config/toggle-menu!])}
     [:> ui/icon
      {:name (if @on "close" "bars")
       :fitted true}]]))

(defn toggle-specials []
  (let [specials? (rf/subscribe [:config/special-chars?])]
    [:> ui/button
     {:toggle true
      :attached "bottom"
      :active @specials?
      :onClick #(rf/dispatch [:config/toggle-specials-chars!])}
     [:> ui/icon
      {:name "keyboard outline"
       :fitted true}]]))

(defn sidebar []
  (let [menu? (rf/subscribe [:config/menu?])
        special-chars? (rf/subscribe [:config/special-chars?])
        history? (rf/subscribe [:config/history?])]
    [:> ui/sidebar
     {:as ui/segment
      :animation "push"
      :direction "right"
      :visible @menu?}
     [:> ui/message
      {:content "v0.5.0"}]
     [:> ui/checkbox
      {:label "Historia"
       :toggle true
       :checked @history?
       :onClick #(rf/dispatch [:config/toggle-history!])
       :style {:margin-bottom "15px"}}]
     [:> ui/checkbox
      {:label "Caracteres especiales"
       :toggle true
       :checked @special-chars?
       :onClick #(rf/dispatch [:config/toggle-specials-chars!])}]]))
