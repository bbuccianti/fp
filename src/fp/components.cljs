(ns fp.components
  (:require
   [fp.semantic :as ui]
   [fp.evaluator.parser :refer [parse]]
   [fp.evaluator.interpreter :refer [evaluate]]
   [fp.evaluator.stringify :refer [to-string]]))

(defn handle-action [in out]
  (do
    (swap! out conj (-> @in parse evaluate to-string))
    (reset! in "")))

(defn handle-key-pressed [e in out]
  (when (= "Enter" (.-key e))
    (handle-action in out)))

(defn input-bar [in out]
  [:> ui/container
   {:style {:margin "20px 0"}}
   [:> ui/input
    {:placeholder "Insertá una expresión!"
     :fluid true
     :size "huge"
     :value @in
     :onKeyPress #(handle-key-pressed % in out)
     :onChange (fn [_ v] (reset! in (.-value v)))
     :action
     {:content "Evaluar"
      :onClick #(handle-action in out)}}]])

(defn output-segments [out]
  [:> ui/container
   (for [o @out]
     ^{:key (gensym "out")}
     [:div o])])
