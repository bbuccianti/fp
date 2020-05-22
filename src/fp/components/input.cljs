(ns fp.components.input
  (:require
   [goog.dom :as gdom]
   [re-frame.core :as rf]
   [fp.semantic :as ui]
   [fp.state :as state]
   [fp.components.config :refer [toggle-sidebar toggle-specials]]
   [fp.evaluator.lexer :refer [lex]]
   [fp.evaluator.parser :refer [parse]]
   [fp.evaluator.interpreter :refer [evaluate]]
   [fp.evaluator.stringify :refer [to-string]]))

(rf/reg-sub
 :input
 (fn [db _]
   (:input db)))

(rf/reg-sub
 :current-input
 (fn [db _]
   (get (:output db) (:index db) "")))

(rf/reg-event-db
 :update-input
 (fn [db [_ new-string]]
   (assoc db :input new-string)))

(rf/reg-sub
 :output-count
 (fn [db _]
   (count (:output db))))

(rf/reg-sub
 :output
 (fn [db _]
   (:output db)))

(rf/reg-event-db
 :add-output
 (fn [db [_ nout]]
   (update db :output conj nout)))

(rf/reg-event-db
 :index
 (fn [db _]
   (:index db)))

(rf/reg-cofx
 :add-output-count
 (fn [cofx _]
   (assoc cofx :output-count (count (get-in cofx [:db :output])))))

(rf/reg-event-fx
 :index/inc
 [(rf/inject-cofx :add-output-count)]
 (fn [{:keys [db output-count]} _]
   (let [new-index (min (inc (:index db)) output-count)]
     {:db (assoc db :index new-index)
      :dispatch-later [{:ms 50
                        :dispatch
                        [:update-input
                         (get-in db [:output new-index :command] "")]}]})))

(rf/reg-event-fx
 :index/dec
 [(rf/inject-cofx :add-output-count)]
 (fn [{:keys [db output-count]} _]
   (let [index (:index db)
         new-index (if (> 0 (dec index))
                     output-count
                     (dec index))]
     {:db (assoc db :index new-index)
      :dispatch-later [{:ms 50
                        :dispatch
                        [:update-input
                         (get-in db [:output new-index :command] "")]}]})))

(defn handle-action []
  (let [in (rf/subscribe [:input])
        output-count (rf/subscribe [:output-count])
        new-input {:index @output-count
                   :command @in
                   :result (-> @in lex parse evaluate to-string)}]
    (rf/dispatch [:add-output new-input])
    (rf/dispatch [:index/inc])
    (rf/dispatch [:update-input ""])
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
  (if (= "Enter" (.-key e))
    (handle-action)))

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
       (for [ch ["∘" "×" "÷" "‾" "α" "→" "∅" "⊥"]]
         ^{:key ch}
         [button-char ch])])))

(defn readline []
  (let [in (rf/subscribe [:input])]
    [:> ui/container
     {:id "input-bar"
      :style {:position "sticky"
              :bottom "0"
              :padding-bottom "10px"
              :zIndex 99}}
     [:> ui/input
      {:placeholder "Insertá una expresión!"
       :fluid true
       :size "huge"
       :input {:autoComplete "off"}
       :value (or @in "")
       :onKeyPress #(handle-key-pressed %)
       :onKeyUp #(handle-history-changes (.-key %))
       :onChange #(handle-change (.-value %2))
       :id "input"
       :action
       {:content "Evaluar"
        :onClick handle-action}}]
     [:> ui/button-group
      {:compact true
       :size "small"}
      [toggle-sidebar]
      [toggle-specials]]
     [special-chars]]))
