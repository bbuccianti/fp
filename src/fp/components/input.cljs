(ns fp.components.input
  (:require
   [goog.dom :as gdom]
   [fp.semantic :as ui]
   [fp.state :as state]
   [fp.evaluator.parser :refer [parse]]
   [fp.evaluator.interpreter :refer [evaluate]]
   [fp.evaluator.stringify :refer [to-string]]))

(defn handle-action [in]
  (swap! state/out conj {:index (swap! state/counter inc)
                         :command @in
                         :result (-> @in parse evaluate to-string)
                         :visible? false})
  (reset! in "")
  (.. (gdom/getElement "container") (scrollIntoView false)))

(defn handle-key-pressed [e in]
  (when (= "Enter" (.-key e))
    (handle-action in)))

(defn repl [in]
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
