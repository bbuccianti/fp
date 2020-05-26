(ns fp.components.output
  (:require
   [re-frame.core :as rf]
   [clojure.string :refer [split]]
   [fp.semantic :as ui]
   [fp.components.examples :refer [man]]))

(defn decoration [constant]
  ^{:key (gensym "span")}
  [:span {:style {:textDecoration "overline"}} (rest constant)])

(defn fix-overline [s]
  (if (re-matches #".*‾-?\d+.*" s)
    (butlast
     (interleave (split s #"‾-?\d+")
                 (cycle (map decoration (re-seq #"‾-?\d+" s)))))
    s))

(defn commands-history []
  (let [history? (rf/subscribe [:config/history?])
        output (rf/subscribe [:output])]
    [:> ui/container
     (doall
      (for [o @output]
        ^{:key (gensym "out")}
        [:> ui/segment-group
         {:horizontal true}
         [:> ui/segment
          {:content (:result o)
           :size "big"}]
         [:> ui/segment
          {:size "big"
           :compact true
           :color "green"
           :inverted true
           :secondary true
           :style {:visibility (if @history? "show" "hidden")}}
          (fix-overline (str "⇄ " (:command o)))]
         [:> ui/button
          {:animated "fade"
           :attached "right"
           :color "facebook"
           :onClick #(rf/dispatch [:update-input o])}
          [:> ui/button-content
           {:visible true}
           (:index o)]
          [:> ui/button-content
           {:hidden true
            :style {:padding-left "1rem"}}
           [:> ui/icon {:name "copy"}]]]]))]))

(defn screen []
  (let [output (rf/subscribe [:output])
        examples? (rf/subscribe [:config/examples?])]
    [:> ui/container
     {:id "screen"
      :style {:padding-bottom "90px"
              :padding-top "20px"
              :minHeight "90vh"}}
     [commands-history]
     (when (> (count @output) 0)
         [:> ui/button
          {:attach "bottom"
           :compact true
           :content "Reportá errores!"
           :color "blue"
           :floated "right"
           :as "a"
           :target "_blank"
           :href "https://todo.sr.ht/~bbuccianti/fp"}])
     (when @examples?
       [man])]))
