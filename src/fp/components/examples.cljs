(ns fp.components.examples
  (:require
   [re-frame.core :as rf]
   [fp.semantic :as ui]))

(rf/reg-sub
 :examples/predicates?
 (fn [db _]
   (get-in db [:man :predicates?])))

(rf/reg-event-db
 :examples/toggle-predicates!
 (fn [db _]
   (update-in db [:man :predicates?] not)))

(defn make-card [{:keys [header meta examples]}]
  [:> ui/card
   [:> ui/card-content
    [:> ui/card-header header]
    [:> ui/card-meta meta]
    [:> ui/card-description
     {:textAlign "center"}
     (doall
      (for [text examples]
        [:pre text]))]]])

(defn predicates []
  (let [enabled? (rf/subscribe [:examples/predicates?])]
    (when @enabled?
      [:> ui/grid
       {:columns 3
        :centered true
        :style {:padding-top "2em"}}
       [:> ui/header
        {:textAlign "center"
         :as "h2"
         :content "Predicados"}]
       [:> ui/grid-row
        [:> ui/grid-column
         [make-card {:header "atom"
                     :meta "Verifica si el operando es un átomo"
                     :examples ["atom: 5        => T"
                                "atom: <A,B,C>  => F"]}]]
        [:> ui/grid-column
         [make-card {:header "eq"
                     :meta "Son iguales los componentes de la secuencia?"
                     :examples ["eq: <A,A>    => T"
                                "eq: <A,7>    => F"
                                "eq: <A,B,C>  => ⊥"]}]]
        [:> ui/grid-column
         [make-card {:header "null"
                     :meta "Verifica si el operando es la secuencia vacía"
                     :examples ["null: ∅      => T"
                                "null: <A,7>  => F"]}]]]])))

(defn toggler-bar []
  (let [predicates? (rf/subscribe [:examples/predicates?])]
    [:> ui/container
     [:> ui/button
      {:content "Predicados"
       :toggle true
       :color (if @predicates? "red" "blue")
       :onClick #(rf/dispatch [:examples/toggle-predicates!])}]]))

(defn man []
  [:> ui/container
   [toggler-bar]
   [predicates]])
