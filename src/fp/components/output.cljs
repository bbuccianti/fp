(ns fp.components.output
  (:require
   [clojure.string :refer [split]]
   [fp.semantic :as ui]
   [fp.state :as state]))

(defn handle-visible [e]
  (let [new-element (update e :visible? not)]
    (swap! state/out assoc (dec (:index e)) new-element)))

(defn visible? [o]
  (if (:history? @state/config)
    (if (:visible? o) "hidden" "show")
    (if (:visible? o) "show" "hidden")))

(defn fix-overline [s]
  (if (re-matches #".*‾\d+.*" s)
    (let [constants (re-seq #"‾\d+" s)
          spl (split s #"‾\d+")]
      (butlast
       (interleave spl
                   (cycle
                    (map (fn [c]
                           [:span {:style {:textDecoration "overline"}}
                            (rest c)])
                         constants)))))
    s))

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
        {:size "big"
         :compact true
         :color "green"
         :inverted true
         :secondary true
         :style {:visibility (visible? o)}}
        (fix-overline (str "⇄ " (:command o)))]]))])
