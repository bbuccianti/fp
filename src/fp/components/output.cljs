(ns fp.components.output
  (:require
   [re-frame.core :as rf]
   [clojure.string :refer [split]]
   [fp.translation :refer [trs]]
   [fp.semantic :as ui]))

(defn credits []
  (let [lang (rf/subscribe [:lang])]
    [:> ui/container
     [:> ui/header
      {:textAlign "center"
       :style {:margin "2rem auto"
               :padding-bottom "1rem"
               :width "20%"
               :border-bottom "1px solid black"}}
      (trs [@lang] [:title])]
     [:> ui/ilist
      {:style {:padding-bottom "2rem"
               :border-bottom "1px solid black"}}
      [:> ui/ilist-item
       [:> ui/ilist-content
        [:> ui/ilist-header (trs [@lang] [:paper-backus])]
        [:> ui/ilist-description
         [:a
          {:href "https://dl.acm.org/doi/10.1145/359576.359579"
           :target "_blank"}
          (trs [@lang] [:paper-acm])]
         [:p
          {:style {:display "inline-block"
                   :margin-left "1.5rem"}}
          "John Warner Backus"]]]]]]))

(defn decoration [constant]
  ^{:key (gensym "span")}
  [:span {:style {:textDecoration "overline"}} (rest (butlast constant))])

(defn fix-overline [s]
  (if (re-matches #".*‾-?\d+‾.*" s)
    (butlast
     (interleave (split s #"‾-?\d+‾")
                 (cycle (map decoration (re-seq #"‾-?\d+‾" s)))))
    s))

(defn commands-history []
  (let [history? (rf/subscribe [:config/history?])
        output (rf/subscribe [:output])
        lang (rf/subscribe [:lang])]
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
           :onClick #(rf/dispatch [:update-input (:command o)])}
          [:> ui/button-content
           {:visible true}
           (:index o)]
          [:> ui/button-content
           {:hidden true
            :style {:padding-left "1rem"}}
           [:> ui/icon {:name "copy"}]]]]))
     (when (> (count @output) 0)
       [:> ui/container
        {:textAlign "right"
         :style {:margin-bottom "2em"}}
        ])]))

(defn screen []
  [:> ui/container
   {:id "screen"
    :style {:padding-bottom "2em"
            :padding-top "2em"}}
   [commands-history]])
