(ns fp.components
  (:require
   [goog.dom :as gdom]
   [reagent.core :as r]
   [fp.semantic :as ui]
   [fp.evaluator.parser :refer [parse]]
   [fp.evaluator.interpreter :refer [evaluate]]
   [fp.evaluator.stringify :refer [to-string]]))

(defonce out (r/atom []))

(defn handle-action [in]
  (do
    (swap! out conj (-> @in parse evaluate to-string))
    (reset! in "")
    (.. (gdom/getElement "container") (scrollIntoView false))))

(defn handle-key-pressed [e in]
  (when (= "Enter" (.-key e))
    (handle-action in)))

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

(defn output-segments []
  [:> ui/container
   {:id "output-segments"
    :style {:padding-bottom "90px"
            :padding-top "20px"
            :minHeight "90vh"}}
   (for [o @out]
     ^{:key (gensym "out")}
     [:> ui/segment
      {:content o
       :size "huge"}])])

