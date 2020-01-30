(ns fp.components
  (:require
   [goog.dom :as gdom]
   [reagent.core :as r]
   [fp.semantic :as ui]
   [fp.evaluator.parser :refer [parse]]
   [fp.evaluator.interpreter :refer [evaluate]]
   [fp.evaluator.stringify :refer [to-string]]))

(defonce counter (r/atom 0))
(defonce out (r/atom []))
(defonce config (r/atom {:history? false
                         :menu? false}))

(defn handle-action [in]
  (do
    (swap! out conj {:index (swap! counter inc)
                     :command @in
                     :result (-> @in parse evaluate to-string)
                     :visible? false})
    (reset! in "")
    (.. (gdom/getElement "container") (scrollIntoView false))))

(defn handle-key-pressed [e in]
  (when (= "Enter" (.-key e))
    (handle-action in)))

(defn handle-visible [e]
  (let [new-element (update e :visible? not)]
    (swap! out assoc (dec (:index e)) new-element)))

(defn input-bar [in]
  [:> ui/container
   {:id "input-bar"
    :style {:position "sticky"
            :bottom "0"
            :padding-bottom "15px"}}
   [:> ui/input
    {:placeholder "Insertá una expresión!"
     :fluid true
     :size "huge"
     :value @in
     :onKeyPress #(handle-key-pressed % in)
     :onChange #(reset! in (.-value %2))
     :action
     {:content "Evaluar"
      :onClick #(handle-action in)}}]])

(defn visible? [o]
  (if (:history? @config)
    (if (:visible? o) "hidden" "show")
    (if (:visible? o) "show" "hidden")))

(defn output-segments []
  [:> ui/container
   {:id "output-segments"
    :style {:padding-bottom "90px"
            :padding-top "20px"
            :minHeight "90vh"}}
   (doall
    (for [o @out]
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
         :size "huge"}]
       [:> ui/segment
        {:content (str "→ " (:command o))
         :size "huge"
         :color "green"
         :inverted true
         :secondary true
         :style {:visibility (visible? o)}}]]))])

(defn config-button []
  [:> ui/button
   {:compact true
    :onClick #(swap! config update :menu? not)
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
    :visible (:menu? @config)}
   [:> ui/checkbox
    {:label "Historia"
     :toggle true
     :checked (:history? @config)
     :onClick #(swap! config update :history? not)}]])

