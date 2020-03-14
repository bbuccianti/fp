(ns fp.evaluator.parser
  (:require
   [clojure.core.match :refer [match]]
   [clojure.string :refer [replace]]
   [cljs.reader :refer [read-string]]
   [fp.evaluator.lexer :refer [lex]]))

(declare parse)

(defn replace-brackets [s]
  (-> s (replace #"\[" "<") (replace #"\]" ">")))

(defn parse-sequence [s]
  (let [replaced (-> s (replace #"<" "[") (replace #">" "]"))
        readed (read-string replaced)]
    (mapv (comp parse lex replace-brackets str) readed)))

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
  ([v] (group-operators [] v))

  ([acc left]
   (if (not (empty? left))
     (match [left]
       [[{:type :open-bra} & r]]
       (let [i (find-next :close-bra left)]
         (group-operators (conj acc (subvec left 0 (inc i)))
                          (subvec left (inc i))))

       [[{:type :open-cond} & r]]
       (let [i (find-next :close-cond left)]
         (group-operators (conj acc (subvec left 0 (inc i)))
                          (subvec left (inc i))))

       [[(:or {:type :insertion} {:type :to-all}) & r]]
       (group-operators (conj acc (subvec left 0 2))
                        (subvec left 2))

       [[(:or {:type :composition} {:type :symbol}
              {:type :number} {:type :constant}
              {:type :comma}) & r]]
       (group-operators (conj acc (first left)) r))
     acc)))

(defn parse [lexed-map]
  (match [lexed-map]
    [[{:type :undefined}]] :undefined
    [[{:type :empty}]] :empty
    [[{:type :boolean :string b}]] {:boolean (= b "T")}
    [[{:type :symbol :string s}]] {:symbol s}
    [[{:type :number :string n}]] {:number (js/parseFloat n)}
    [[{:type :constant :string s}]]
    {:constant (js/parseFloat (replace s "‾" ""))}

    [[{:type :open-seq} & r]]
    (if (not (some #{:comma} (map :type lexed-map)))
      (parse-sequence
       (apply str (interleave (map :string lexed-map) (repeat ","))))
      (parse-sequence
       (apply str (map :string lexed-map))))

    [(appli :guard #(some #{:application} (map :type lexed-map)))]
    (let [parts (partition-by #(= :application (:type %)) lexed-map)
          [left _ right] parts]
      {:application
       {:operators (->> left vec group-operators parse vector)
        :operands (->> right vec parse)}})

    [(condi :guard #(some #{:semicolon} (map :type (first lexed-map))))]
    (let [parts (partition-by #(= :right (:type %))
                              (-> condi first rest butlast))
          [left _ right] parts
          [t _ f] (partition-by #(= :semicolon (:type %)) right)]
      {:condition (-> left parse vector)
       :true (-> t vec parse)
       :false (-> f vec parse)})

    [[[{:type :open-cond} {:type :symbol :string "while"} & r]]]
    (let [elements (butlast r)]
      {:while {:predicate (-> r butlast butlast butlast rest
                              vec parse)
               :function (-> r butlast last vector parse)}})

    [(compo :guard #(some #{:composition} (map :type lexed-map)))]
    (let [parts (partition-by #(= :composition (:type %)) compo)
          clean (remove #(= :composition (:type (first %))) parts)]
      {:composition (vec (rseq (mapv (comp parse vec) clean)))})

    [(:or [[{:type :open-cond} {:type :symbol :string "bu"} & r]]
          [{:type :open-cond} {:type :symbol :string "bu"} & r])]
    {:bu (mapv (comp parse vector) (butlast r))}

    [[[{:type :open-bra} & r]]]
    (let [groups (group-operators (vec (butlast r)))
          [left _ right] (partition-by #(= :comma (:type %)) groups)]
      {:construction (mapv (comp parse vec) [left right])})

    [[[{:type :insertion} & r]]]
    {:insertion (mapv (comp parse vector) r)}

    [[[{:type :to-all} & r]]]
    {:to-all (mapv (comp parse vector) r)}

    :else (vec lexed-map)))
