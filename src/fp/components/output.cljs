(ns fp.components.output
  (:require
   [fp.semantic :as ui]
   [fp.state :as state]))

(defn handle-visible [e]
  (let [new-element (update e :visible? not)]
    (swap! state/out assoc (dec (:index e)) new-element)))

(defn visible? [o]
  (if (:history? @state/config)
    (if (:visible? o) "hidden" "show")
    (if (:visible? o) "show" "hidden")))

(defn screen []
  [:> ui/container
   {:id "screen"
    :style {:padding-bottom "90px"
            :padding-top "20px"
            :minHeight "90vh"}}
   (doall
    (for [o @state/out]
      ^{:key (gensym "out")}
      [:> ui/segment-group
       {:horizontal true}
       [:> ui/button
        {:content (:index o)
         :attached "left"
         :color "facebook"
         :size "small"
         :onClick #(handle-visible o)}]
       [:> ui/segment
        {:content (:result o)
         :size "big"}]
       [:> ui/segment
        {:content (str "⇄ " (:command o))
         :size "big"
         :compact true
         :color "green"
         :inverted true
         :secondary true
         :style {:visibility (visible? o)}}]]))])
