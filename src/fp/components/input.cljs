(ns fp.components.input
  (:require
   [goog.dom :as gdom]
   [fp.semantic :as ui]
   [fp.state :as state]
   [fp.components.config :refer [toggle-sidebar toggle-specials]]
   [fp.evaluator.lexer :refer [lex]]
   [fp.evaluator.parser :refer [parse]]
   [fp.evaluator.interpreter :refer [evaluate]]
   [fp.evaluator.stringify :refer [to-string]]))

(defn handle-action [in]
  (swap! state/out conj {:index (swap! state/counter inc)
                         :command @in
                         :result (-> @in lex parse evaluate to-string)
                         :visible? false})
  (reset! in "")
  (.. (gdom/getElement "container") (scrollIntoView false)))

(defn insert-char [ch in]
  (let [input (gdom/getElement "input")
        idx (.-selectionStart input)
        left (subs @in 0 idx)
        right (subs @in idx)]
    (reset! in (str left ch right))
    (.. input focus)
    (js/setTimeout #(.setSelectionRange input (inc idx) (inc idx)) 25)))

(defn handle-key-pressed [e in]
  (if (= "Enter" (.-key e))
    (handle-action in)))

(defn handle-change [in new-in]
  (let [input (gdom/getElement "input")
        idx (.-selectionStart input)]
    (reset! in new-in)
    (js/setTimeout #(.setSelectionRange input idx idx) 25)))

(defn button-char [ch in]
  [:> ui/button
   {:content ch
    :onClick #(insert-char ch in)}])

(defn special-chars [in]
  (when (:special-chars? @state/config)
    [:> ui/button-group
     {:compact true
      :basic true
      :floated "right"}
     (for [ch ["∘" "×" "÷" "‾" "α" "→" "∅" "⊥"]]
       ^{:key ch}
       [button-char ch in])]))

(defn readline [in]
  [:> ui/container
   {:id "input-bar"
    :style {:position "sticky"
            :bottom "0"
            :padding-bottom "10px"}}
   [:> ui/input
    {:placeholder "Insertá una expresión!"
     :fluid true
     :size "huge"
     :value @in
     :onKeyPress #(handle-key-pressed % in)
     :onChange #(handle-change in (.-value %2))
     :id "input"
     :action
     {:content "Evaluar"
      :onClick #(handle-action in)}}]
   [:> ui/button-group
    {:compact true
     :size "small"}
    [toggle-sidebar]
    [toggle-specials]]
   [special-chars in]])
