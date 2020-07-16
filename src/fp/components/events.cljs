(ns fp.components.events
  (:require
   [goog.dom :as gdom]
   [re-frame.core :as rf]
   [fp.state :as state]))

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

(rf/reg-sub
 :examples/functions?
 (fn [db _]
   (get-in db [:man :functions?])))

(rf/reg-event-db
 :examples/toggle-functions!
 (fn [db _]
   (update-in db [:man :functions?] not)))

(rf/reg-sub
 :examples/definitions?
 (fn [db _]
   (get-in db [:man :definitions?])))

(rf/reg-event-db
 :examples/toggle-definitions!
 (fn [db _]
   (update-in db [:man :definitions?] not)))

(rf/reg-event-db
 :examples/disable-all
 (fn [db _]
   (assoc db :man (:man state/db))))
