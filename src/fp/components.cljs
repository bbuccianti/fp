(ns fp.components
  (:require
   [fp.semantic :as ui]))

(defn input-bar [in out]
  [:> ui/container
   {:style {:margin "20px 0"}}
   [:> ui/input
    {:placeholder "Inserte una expresión..."
     :fluid true
     :size "huge"
     :value @in
     :onChange (fn [e v] (reset! in (.-value v)))
     :action
     {:content "Evaluar"
      :on-click #(do
                   (swap! out conj @in)
                   (reset! in ""))}}]])

(defn output-segments [out]
  [:> ui/container
   (for [o @out]
     ^{:key (gensym "out")}
     [:div o])])
