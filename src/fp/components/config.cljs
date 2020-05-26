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

(rf/reg-sub
 :config/examples?
 (fn [db _]
   (:examples? db)))

(rf/reg-event-db
 :config/toggle-examples!
 (fn [db _]
   (update db :examples? not)))

(defn toggle-button [kw toggle-kw icon color]
  (let [enabled (rf/subscribe [kw])]
    [:> ui/button
     {:toggle true
      :attached "bottom"
      :color (if @enabled "red" color)
      :onClick #(rf/dispatch [toggle-kw])}
     [:> ui/icon
      {:name icon
       :fitted true}]]))
