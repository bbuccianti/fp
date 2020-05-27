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

(rf/reg-sub
 :examples/logics?
 (fn [db _]
   (get-in db [:man :logics?])))

(rf/reg-event-db
 :examples/toggle-logics!
 (fn [db _]
   (update-in db [:man :logics?] not)))

(rf/reg-sub
 :examples/sequences?
 (fn [db _]
   (get-in db [:man :sequences?])))

(rf/reg-event-db
 :examples/toggle-sequences!
 (fn [db _]
   (update-in db [:man :sequences?] not)))

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

(defn logics []
  (let [enabled? (rf/subscribe [:examples/logics?])]
    (when @enabled?
      [:> ui/grid
       {:columns 3
        :centered true
        :style {:padding-top "2em"}}
       [:> ui/header
        {:textAlign "center"
         :as "h2"
         :content "Lógicas"}]
       [:> ui/grid-row
        [make-card {:header "and"
                    :meta "Verdadero, si ambos lo son"
                    :examples ["and: <T,F>  => F"
                               "and: <1,0>  => ⊥"]}]
        [make-card {:header "or"
                    :meta "Verdadero, si alguno lo es"
                    :examples ["or: <T,F>    => T"
                               "or: <2,C>    => ⊥"]}]
        [make-card {:header "not"
                    :meta "Verdadero a falso y viceversa"
                    :examples ["not: F    => T"
                               "not: T    => F"
                               "not: 1    => ⊥"]}]]])))

(defn sequences []
  (let [enabled? (rf/subscribe [:examples/sequences?])]
    (when @enabled?
      [:> ui/grid
       {:columns 4
        :centered true
        :style {:padding-top "2em"}}
       [:> ui/header
        {:textAlign "center"
         :as "h2"
         :content "Secuencias"}]
       [:> ui/grid-row
        [make-card {:header "length"
                    :meta "Devuelve la longitud de la secuencia"
                    :examples ["length: <2,A,F>  => 3"
                               "length: ∅        => 0"
                               "length: 2        => ⊥"]}]
        [make-card {:header "reverse"
                    :meta "Devuelve el inverso de la secuencia"
                    :examples ["reverse: <2,A,7>    => <7,A,2>"
                               "reverse: ∅          => ∅"
                               "reverse: x          => ⊥"]}]
        [make-card {:header "trans"
                    :meta "Transpone la matriz dada"
                    :examples ["trans: <<1,2,3>, <A,B,C>>"
                               "=> <<1,A>, <2,B>, <3,C>>"]}]]
       [:> ui/grid-row
        [make-card {:header "distl"
                    :meta "Distribuir desde la izquierda"
                    :examples ["distl: <A, <1,2,3>>"
                               "=>    <<A,1>, <A,2>, <A,3>>"]}]
        [make-card {:header "distr"
                    :meta "Distribuir desde la derecha"
                    :examples ["distl: <<1,2,3>, A>"
                               "=>    <<1,A>, <2,A>, <3,A>>"]}]
        [make-card {:header "apndl"
                    :meta "Concatenar a la izquierda"
                    :examples ["apndl: <<A,B>, <C,D>>"
                               "=>    <<A,B>,C,D>"]}]
        [make-card {:header "apndr"
                    :meta "Concatenar a la derecha"
                    :examples ["apndr: <<A,B>, <C,D>>"
                               "=>    <A,B, <C,D>>"]}]]
       [:> ui/grid-row
        [make-card {:header "rotl"
                    :meta "Rotar hacia la izquierda"
                    :examples ["rotl: <A,B,C,D>"
                               "=>    <B,C,D,A>"]}]
        [make-card {:header "rotr"
                    :meta "Rotar hacia la derecha"
                    :examples ["rotr: <A,B,C,D>"
                               "=>    <D,A,B,C>"]}]]])))

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
                   :dispatcher :examples/toggle-arithmetics!}]
     [make-button {:kw :examples/logics?
                   :content "Lógicas"
                   :dispatcher :examples/toggle-logics!}]
     [make-button {:kw :examples/sequences?
                   :content "Secuencias"
                   :dispatcher :examples/toggle-sequences!}]]))

(defn man []
  (let [enabled? (rf/subscribe [:config/examples?])]
    (when @enabled?
      [:> ui/container
       [toggler-bar]
       [selectors]
       [predicates]
       [arithmetics]
       [logics]
       [sequences]])))
