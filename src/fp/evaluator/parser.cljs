(ns fp.evaluator.parser
  (:require
   [clojure.core.match :refer [match]]
   [clojure.string :as string]
   [cljs.reader :refer [read-string]]
   [fp.evaluator.lexer :refer [lex]]))

(declare parse)

(defn replace-brackets [s]
  (-> s (string/replace #"\[" "<") (string/replace #"\]" ">")))

(defn parse-sequence [s]
  (let [replaced (-> s (string/replace #"<" "[") (string/replace #">" "]"))
        readed (read-string replaced)]
    (mapv (comp parse lex replace-brackets str) readed)))

(defn parse [lexed-map]
  (match [lexed-map]
    [[{:type :undefined}]] :undefined
    [[{:type :empty}]] :empty
    [[{:type :boolean :string b}]] {:boolean (= b "T")}
    [[{:type :symbol :string s}]] {:symbol s}
    [[{:type :number :string n}]] {:number (js/parseFloat n)}
    [[{:type :constant :string s}]]
    {:constant (js/parseFloat (string/replace s "‾" ""))}

    [[{:type :open-seq} & r]]
    (parse-sequence (apply str (interpose "," (map :string lexed-map))))

    [(appli :guard #(some #{:application} (map :type lexed-map)))]
    (let [parts (partition-by #(= :application (:type %)) lexed-map)
          [left _ right] parts]
      {:application
       {:operators (-> left vec parse vector)
        :operands (-> right vec parse)}})

    [(condi :guard #(some #{:semicolon} (map :type lexed-map)))]
    (let [parts (partition-by #(= :right (:type %)) (butlast (rest condi)))
          [left _ right] parts
          [t _ f] (partition-by #(= :semicolon (:type %)) right)]
      {:condition [(parse left)]
       :true (-> t vec parse)
       :false (-> f vec parse)})

    [[{:type :open-cond} {:type :symbol :string "while"} & r]]
    (let [elements (butlast r)]
      {:while {:predicate (-> r butlast butlast butlast rest
                              vec parse)
               :function (-> r butlast last vector parse)}})

    [(compo :guard #(some #{:composition} (map :type lexed-map)))]
    (let [parts (partition-by #(= :composition (:type %)) compo)
          clean (remove #(= :composition (:type (first %))) parts)]
      {:composition (rseq (mapv (comp parse vec) clean))})

    [[{:type :open-cond} {:type :symbol :string "bu"} & r]]
    {:bu (mapv (comp parse vector) (butlast r))}

    [[{:type :open-bra} & r]]
    {:construction (mapv (comp parse vector) (butlast r))}

    [[{:type :insertion} & r]]
    {:insertion (mapv (comp parse vector) r)}

    [[{:type :to-all} & r]]
    {:to-all (mapv (comp parse vector) r)}

    :else lexed-map))
