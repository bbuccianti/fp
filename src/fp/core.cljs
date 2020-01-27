(ns fp.core
  (:require
   [goog.dom :as gdom]
   [reagent.core :as r]
   [fp.semantic :as ui]))

(defn app []
  (let [in (r/atom "")
        out (r/atom [])]
    (fn []
      [:> ui/container {:style {:min-height "100vh"}}
       [:> ui/container
        (for [output @out]
          ^{:key (gensym "out")}
          [:div output])]
       [:> ui/container {:style {:position "fixed"
                                 :bottom "10px"}}
        [:> ui/input {:placeholder "Inserte una expresión..."
                      :fluid true
                      :size "huge"
                      :value @in
                      :onChange (fn [e v] (reset! in (.-value v)))
                      :action
                      {:content "Evaluar"
                       :on-click #(do
                                    (swap! out conj @in)
                                    (reset! in ""))}}]]])))

(defn mount-app []
  (when-let [el (gdom/getElement "app")]
    (r/render-component [app] el)))

(defn on-js-reload []
  (mount-app))
