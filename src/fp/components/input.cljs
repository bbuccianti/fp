(ns fp.components.input
  (:require
   [goog.dom :as gdom]
   [reagent.core :as rg]
   [re-frame.core :as rf]
   [clojure.string :as string :refer [lower-case includes? trim]]
   [fp.semantic :as ui]
   [fp.state :as state]
   [fp.components.config :as config]
   [fp.components.events :as events]
   [fp.translation :refer [trs]]
   [fp.evaluator.lexer :refer [lex]]
   [fp.evaluator.parser :refer [parse]]
   [fp.evaluator.interpreter :refer [evaluate]]
   [fp.evaluator.stringify :refer [to-string]]))

(def list-of-special-chars
  [[:composition "∘"] [:product "×"] [:quotient "÷"] [:over "‾"]
   [:alpha "α"] [:then "→"] [:empty "∅"] [:undef "⊥"]
   [:definition "≡"]])

(defn handle-action []
  (let [in (rf/subscribe [:input])
        output-count (rf/subscribe [:output-count])
        new-input {:index @output-count
                   :command @in
                   :result (-> @in lex parse evaluate to-string)}]
    (rf/dispatch [:add-output new-input])
    (rf/dispatch [:index/tolast])
    (rf/dispatch [:update-input ""])
    ;; (rf/dispatch [:examples/disable-all])
    (.. (gdom/getElement "container") (scrollIntoView false))))

(defn insert-char [ch]
  (let [input (gdom/getElement "input")
        in (rf/subscribe [:input])
        idx (.-selectionStart input)
        left (subs @in 0 idx)
        right (subs @in idx)]
    (rf/dispatch [:update-input (str left ch right)])
    (.. input focus)
    (js/setTimeout #(.setSelectionRange input (inc idx) (inc idx)) 25)))

(defn handle-key-pressed [e]
  (case (.-key e)
    "Enter" (handle-action)
    "\\"  (do (.preventDefault e)
              (rf/dispatch [:input/show-selector!]))
    nil))

(defn handle-selector-key-pressed [e [_ ch _]]
  (case (.-key e)
    "Enter" (do
              (insert-char ch)
              (rf/dispatch [:input/close-selector!])
              (.. (gdom/getElement "container") (scrollIntoView false)))
    nil))

(defn handle-change [new-in]
  (let [input (gdom/getElement "input")
        idx (.-selectionStart input)]
    (rf/dispatch [:update-input new-in])
    (js/setTimeout #(.setSelectionRange input idx idx) 25)))

(defn handle-history-changes [key]
  (case key
    "ArrowUp" (rf/dispatch [:index/dec])
    "ArrowDown" (rf/dispatch [:index/inc])
    nil))

(defn button-char [ch]
  [:> ui/button
   {:content ch
    :onClick #(insert-char ch)}])

(defn special-chars []
  (let [enabled? (rf/subscribe [:config/special-chars?])]
    (when @enabled?
      [:> ui/button-group
       {:compact true
        :basic true
        :floated "right"}
       (for [[_ ch] list-of-special-chars]
         ^{:key ch}
         [button-char ch])])))

(defn cleaned [word]
  (string/replace (lower-case word)
                  #"[áéíóú]"
                  {"á" "a" "é" "e" "í" "i" "ó" "o" "ú" "u"}))

(defn filter-content [v input]
  (let [in (lower-case input)]
    (filter
     (fn [[kw ch word]]
       (or (includes? (str kw) in)
           (includes? (lower-case word) in)
           (includes? (cleaned word) in)))
     v)))

(defn selector []
  (let [enabled? (rf/subscribe [:input/selector?])
        lang (rf/subscribe [:lang])
        input (rf/subscribe [:input/selector])
        vec-of-words (map #(conj % (trs [@lang] [(first %)]))
                          list-of-special-chars)]
    [:> ui/dimmer
     {:active @enabled?
      :verticalAlign "top"
      :onClickOutside #(rf/dispatch [:input/close-selector!])
      :style {:padding-top "5em"}}
     [:> ui/segment-group
      [:> ui/input
       {:id "input-selector"
        :size "huge"
        :value @input
        :onKeyUp #(if (= "Escape" (.-key %))
                    (rf/dispatch [:input/close-selector!]))
        :onChange
        #(rf/dispatch [:input/change-selector (trim (.-value (.-target %)))])

        :onKeyPress
        #(handle-selector-key-pressed
          % (first (filter-content vec-of-words @input)))}]
      [:> ui/segment-group
       (doall
        (for [[kw ch word]
              (filter-content vec-of-words @input)]
          ^{:key kw}
          [:> ui/segment
           {:textAlign "left"
            :inverted true
            :style {:visibility "visible"}}
           [:p
            {:style {:display "inline-block"}}
            word]
           [:p
            {:style {:display "inline-block"
                     :font-size "1.2rem"
                     :float "right"}}
            ch]]))]]]))

(defn readline []
  (let [in (rf/subscribe [:input])
        lang (rf/subscribe [:lang])
        helper (rf/subscribe [:helper])]
    [:> ui/container
     {:id "input-bar"}
     [:> ui/input
      {:placeholder (trs [@lang] [@helper])
       :fluid true
       :size "huge"
       :input {:autoComplete "off"}
       :value (or @in "")
       :onKeyPress handle-key-pressed
       :onKeyUp #(handle-history-changes (.-key %))
       :onChange #(handle-change (.-value %2))
       :id "input"
       :action
       {:content (trs [@lang] [:eval])
        :onClick handle-action}}]
     [:> ui/button-group
      {:compact true
       :size "small"}
      [config/language-button]
      (doall
       (for [[kw toggle-kw icon color]
             [[:config/examples? :config/toggle-examples! "help" "blue"]
              [:config/special-chars? :config/toggle-specials-chars!
               "keyboard outline" "green"]]]
         ^{:key kw}
         [config/toggle-button kw toggle-kw icon color]))
      [special-chars]]
     [:> ui/button
      {:attach "bottom"
       :floated "right"
       :compact true
       :content (trs [@lang] [:errors])
       :color "blue"
       :as "a"
       :target "_blank"
       :href "https://todo.sr.ht/~bbuccianti/fp"
       :style {:margin-top "1em"}}]]))
