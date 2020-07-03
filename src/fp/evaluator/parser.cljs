(ns fp.evaluator.parser
  (:require
   [clojure.core.match :refer [match]]
   [clojure.string :refer [replace lower-case]]
   [cljs.reader :refer [read-string]]
   [fp.evaluator.lexer :refer [lex]]))

(declare parse)

(defn replace-brackets [s]
  (-> s (replace #"\[" "<") (replace #"\]" ">")))

(defn parse-sequence [s]
  (let [replaced (-> s (replace #"<" "[") (replace #">" "]"))
        readed (read-string replaced)]
    (map (comp parse lex replace-brackets str) readed)))

(defn find-next [close-item m]
  (let [open-item (if (= close-item :close-bra) :open-bra :open-cond)
        kw-set #{close-item open-item}]
    (reduce (fn [counter item]
              (if (= (:type (second item)) open-item)
                (inc counter)
                (if (>= 0 (dec counter))
                  (reduced (first item))
                  (dec counter))))
            0
            (filter #(kw-set (:type (second %)))
                    (map-indexed vector m)))))

(defn group-operators
  ([v] (group-operators () v))

  ([acc left]
   (if (empty? left)
     (reverse acc)
     (match [left]
       [([{:type :open-bra} & r] :seq)]
       (let [i (find-next :close-bra left)]
         (group-operators (conj acc (take (inc i) left)) (drop (inc i) left)))

       [([{:type :open-cond} & r] :seq)]
       (let [i (find-next :close-cond left)]
         (group-operators (conj acc (take (inc i) left)) (drop (inc i) left)))

       [([(:or {:type :insertion} {:type :to-all}) & r] :seq)]
       (group-operators (conj acc (take 2 left)) (drop 2 left))

       ;; TODO: remove this nested approach
       ;; We can have a previous grouping here
       [([(:or ([{:type :open-cond} & rr] :seq)
               ([{:type :open-bra} & rr] :seq)
               ([{:type :insertion} & rr] :seq)
               ([{:type :to-all} & rr] :seq)) & r] :seq)]
       (group-operators (conj acc (first left)) r)

       [([(:or {:type :composition} {:type :symbol}
               {:type :number} {:type :constant}
               {:type :comma} {:type :empty}) & r] :seq)]
       (group-operators (conj acc (first left)) r)))))

(defn parse [lexed]
  (match [lexed]
    [{:type :undefined}] :undefined
    [{:type :empty}] :empty
    [{:type :boolean :string b}] {:boolean (= b "T")}
    [{:type :symbol :string s}] {:symbol s}
    [{:type :number :string n}] {:number (js/parseFloat n)}
    [{:type :constant :string s}]
    {:constant (js/parseFloat (replace s "‾" ""))}

    [([{:type :open-seq} & r] :seq)]
    (if (not (some #{:comma} (map :type lexed)))
      (parse-sequence
       (apply str (interleave (map :string lexed) (repeat ","))))
      (parse-sequence (apply str (map :string lexed))))

    [(appli :guard #(some #{:application} (map :type lexed)))]
    (let [[left _ right] (partition-by #(= :application (:type %)) lexed)]
      {:application
       {:operators (->> left group-operators parse)
        :operands (->> right parse)}})

    [(d :guard #(some #{:definition} (map :type lexed)))]
    (let [[left _ right] (partition-by #(= :definition (:type %)) lexed)]
      (if (= (lower-case (:string (first left))) "def")
        {:definition
         {:symbol (:string (second left))
          :body (parse right)}}))

    [(condi :guard #(some #{:semicolon} (map :type lexed)))]
    (let [parts (partition-by #(= :right (:type %)) lexed)
          [left _ right] parts
          [t _ f] (partition-by #(= :semicolon (:type %)) (butlast right))]
      {:condition (parse (next left))
       :true (parse t)
       :false (parse f)})

    [([{:type :open-cond} {:type :symbol :string "while"} & r] :seq)]
    (let [elements (butlast r)
          grouped (group-operators elements)]
      {:while {:predicate (-> grouped first next butlast parse)
               :function (-> grouped last parse)}})

    [(compo :guard #(some #{:composition} (map :type lexed)))]
    (let [clean (->> compo group-operators
                     (partition-by #(= :composition (:type %)))
                     (remove #(= :composition (:type (first %)))))]
      {:composition (reverse (map parse clean))})

    [([{:type :open-cond} {:type :symbol :string "bu"} & r] :seq)]
    {:bu (map parse (butlast r))}

    [(:or ([{:type :open-bra} & r] :seq)
          ([([{:type :open-bra} & r] :seq)] :seq))]
    (let [groups (group-operators (butlast r))
          [left _ right] (partition-by #(= :comma (:type %)) groups)]
      {:construction (map parse (list left right))})

    [([{:type :insertion} & r] :seq)]
    {:insertion (map parse r)}

    [([{:type :to-all} & r] :seq)]
    {:to-all (map parse r)}

    :else (parse (first lexed))))
