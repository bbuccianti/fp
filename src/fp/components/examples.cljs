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

(rf/reg-sub
 :examples/arithmetics?
 (fn [db _]
   (get-in db [:man :arithmetics?])))

(rf/reg-event-db
 :examples/toggle-arithmetics!
 (fn [db _]
   (update-in db [:man :arithmetics?] not)))

(rf/reg-sub
 :examples/selectors?
 (fn [db _]
   (get-in db [:man :selectors?])))

(rf/reg-event-db
 :examples/toggle-selectors!
 (fn [db _]
   (update-in db [:man :selectors?] not)))

(defn make-card [{:keys [header meta examples]}]
  [:> ui/grid-column
   {:style {:margin-bottom "2em"}}
   [:> ui/card
    [:> ui/card-content
     [:> ui/card-header header]
     [:> ui/card-meta meta]
     [:> ui/card-description
      {:textAlign "center"}
      (doall
       (for [text examples]
         [:pre text]))]]]])

(defn selectors []
  (let [enabled? (rf/subscribe [:examples/selectors?])]
    (when @enabled?
      [:> ui/grid
       {:columns 3
        :centered true
        :style {:padding-top "2em"}}
       [:> ui/header
        {:textAlign "center"
         :as "h2"
         :content "Selectores"}]
       [:> ui/grid-row
        [make-card {:header "s"
                    :meta "Para cualquier entero positivo s"
                    :examples ["1: <A,B,C>   => A"
                               "2: <4,3,2>   => 3"
                               "4: <D,E,F>   => ⊥"]}]
        [make-card {:header "sr"
                    :meta "Para cualquier entero positivo s"
                    :examples ["1r: <A,B,C>   => C"
                               "3r: <4,3,2>   => 4"
                               "4r: <D,E,F>   => ⊥"]}]
        [make-card {:header "tl"
                    :meta "Cola desde la izquierda"
                    :examples ["tl: <A,B,C>   => <B,C>"]}]
        [make-card {:header "tlr"
                    :meta "Cola desde la derecha"
                    :examples ["tlr: <A,B,C>   => <A,B>"]}]
        [make-card {:header "id"
                    :meta "Identidad"
                    :examples ["id: <A,B,C>   => <A,B,C>"]}]]])))

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
        [make-card {:header "atom"
                    :meta "Verifica si el operando es un átomo"
                    :examples ["atom: 5        => T"
                               "atom: <A,B,C>  => F"]}]
        [make-card {:header "eq"
                    :meta "Son iguales los componentes de la secuencia?"
                    :examples ["eq: <A,A>    => T"
                               "eq: <A,7>    => F"
                               "eq: <A,B,C>  => ⊥"]}]
        [make-card {:header "null"
                    :meta "Verifica si el operando es la secuencia vacía"
                    :examples ["null: ∅      => T"
                               "null: <A,7>  => F"]}]]])))

(defn arithmetics []
  (let [enabled? (rf/subscribe [:examples/arithmetics?])]
    (when @enabled?
      [:> ui/grid
       {:columns 4
        :centered true
        :style {:padding-top "2em"}}
       [:> ui/header
        {:textAlign "center"
         :as "h2"
         :content "Aritméticas"}]
       [:> ui/grid-row
        [make-card {:header "+"
                    :meta "Suma"
                    :examples ["+: <2,7>    => 9"
                               "+: <3,A,6>  => ⊥"]}]
        [make-card {:header "-"
                    :meta "Resta"
                    :examples ["-: <9,7>    => 2"
                               "-: <2,C>    => ⊥"]}]
        [make-card {:header "×"
                    :meta "Multiplica"
                    :examples ["×: <3,5>    => 15"
                               "×: <A,2>    =>  ⊥"]}]
        [make-card {:header "÷"
                    :meta "Cociente"
                    :examples ["÷: <10,2>    => 5"
                               "÷: <10,0>    => ⊥"]}]]])))

(defn make-button [{:keys [kw content dispatcher]}]
  (let [enabled? (rf/subscribe [kw])]
    [:> ui/button
     {:content content
      :toggle true
      :color (if @enabled? "grey" "teal")
      :onClick #(rf/dispatch [dispatcher])}]))

(defn toggler-bar []
  (let [predicates? (rf/subscribe [:examples/predicates?])]
    [:> ui/container
     [make-button {:kw :examples/selectors?
                   :content "Selectores"
                   :dispatcher :examples/toggle-selectors!}]
     [make-button {:kw :examples/predicates?
                   :content "Predicados"
                   :dispatcher :examples/toggle-predicates!}]
     [make-button {:kw :examples/arithmetics?
                   :content "Aritméticas"
                   :dispatcher :examples/toggle-arithmetics!}]]))

(defn man []
  [:> ui/container
   [toggler-bar]
   [selectors]
   [predicates]
   [arithmetics]])
