(ns fp.components.input
  (:require
   [goog.dom :as gdom]
   [reagent.core :as rg]
   [re-frame.core :as rf]
   [clojure.string :as string :refer [lower-case includes?]]
   [fp.semantic :as ui]
   [fp.state :as state]
   [fp.components.config :as config]
   [fp.translation :refer [trs]]
   [fp.evaluator.lexer :refer [lex]]
   [fp.evaluator.parser :refer [parse]]
   [fp.evaluator.interpreter :refer [evaluate]]
   [fp.evaluator.stringify :refer [to-string]]))

(def list-of-special-chars
  [[:composition "∘"] [:product "×"] [:quotient "÷"] [:over "‾"]
   [:alpha "α"] [:then "→"] [:empty "∅"] [:undef "⊥"]
   [:definition "≡"]])

(rf/reg-sub
 :helper
 (fn [db _]
   (let [cout (count (:output db))
         helpers (count (:helpers db))
         n (mod cout helpers)]
     (get-in db [:helpers n]))))

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

(rf/reg-event-db
 :index/tolast
 (fn [db _]
   (assoc db :index (count (:output db)))))

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

(rf/reg-sub
 :input/selector?
 (fn [db _]
   (:selector? db)))

(rf/reg-event-fx
 :input/show-selector!
 (fn [{:keys [db]} _]
   {:db (assoc db :selector? true)
    :dispatch-later [{:ms 50 :dispatch [:input/selector-focus]}]}))

(rf/reg-event-fx
 :input/selector-focus
 (fn [{:keys [db]} _]
   (let [selector (gdom/getElement "input-selector")]
     (.focus selector)
     {:db db})))

(rf/reg-event-fx
 :input/focus
 (fn [{:keys [db]} _]
   (let [input (gdom/getElement "input")]
     (.focus input)
     {:db db})))

(rf/reg-event-fx
 :input/close-selector!
 (fn [{:keys [db]} _]
   {:db (assoc db :selector? false)
    :dispatch-later [{:ms 50 :dispatch [:input/focus]}
                     {:ms 50 :dispatch [:input/change-selector ""]}]}))

(rf/reg-sub
 :input/selector
 (fn [db _]
   (:selector-input db)))

(rf/reg-event-db
 :input/change-selector
 (fn [db [_ input-string]]
   (assoc db :selector-input input-string)))

(defn handle-action []
  (let [in (rf/subscribe [:input])
        output-count (rf/subscribe [:output-count])
        new-input {:index @output-count
                   :command @in
                   :result (-> @in lex parse evaluate to-string)}]
    (rf/dispatch [:add-output new-input])
    (rf/dispatch [:index/tolast])
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
        #(rf/dispatch [:input/change-selector (.-value (.-target %))])

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
     {:id "input-bar"
      :style {:position "sticky"
              :bottom "0"
              :padding-bottom "10px"
              :z-index 99}}
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
      (doall
       (for [[kw toggle-kw icon color]
             [[:config/special-chars? :config/toggle-specials-chars!
               "keyboard outline" "green"]
              [:config/examples? :config/toggle-examples! "help" "blue"]]]
         ^{:key kw}
         [config/toggle-button kw toggle-kw icon color]))
      [config/language-button]]
     [special-chars]]))
