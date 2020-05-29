(ns fp.translation
  (:require
   [re-frame.core :as rf]
   [taoensso.tempura :as tempura :refer [tr]]))

(rf/reg-sub
 :lang
 (fn [db _]
   (:lang db)))

(rf/reg-event-db
 :toggle-lang!
 (fn [db _]
   (let [l (if (= :es (:lang db)) :en :es)]
     (assoc db :lang l))))

(def dictionary
  {:en {:eval "Eval"
        :errors "Report bugs!"
        :selectors "Selectors"
        :predicates "Predicates"
        :arithmetics "Arithmetics"
        :logics "Logics"
        :sequences "Sequences"
        :functions "Functional forms"
        :composition "Composition"
        :construction "Construction"
        :constant "Constant n"
        :insert "Insert"
        :applytoall "Apply to all"
        :bu "Binary to unary"
        :condition "Condition"
        :while "While loop"
        :length "Number of elements in a sequence"
        :reverse "Reverse a sequence"
        :trans "Transpose"
        :distl "Distribute from left"
        :distr "Distribute from right"
        :apndl "Append left"
        :apndr "Append right"
        :rotl "Rotate left"
        :rotr "Rotate right"
        :and "True, if both are true"
        :or "True, if one is true"
        :not "True to false, false to true"
        :addition "Addition"
        :subtraction "Subtraction"
        :product "Product"
        :quotient "Quotient"
        :atom "It's an atom?"
        :eq "Equality"
        :null "It's an empty sequence?"
        :s "For any positive integer s"
        :sr "For any positive integer s"
        :tl "Tail from left"
        :tr "Tail from right"
        :id "Returns operand"
        :over "Overline"
        :alpha "Alpha"
        :then "Then"
        :empty "Empty"
        :undef "Undefined"}

   :es {:eval "Evaluar"
        :errors "Reportá errores!"
        :selectors "Selectores"
        :predicates "Predicados"
        :arithmetics "Aritméticas"
        :logics "Lógicas"
        :sequences "Secuencias"
        :functions "Formas funcionales"
        :composition "Composición"
        :construction "Construcción"
        :constant "Constante n"
        :insert "Inserción"
        :applytoall "Aplicación a todos"
        :bu "Binario a unario"
        :condition "Condición"
        :while "Iterar mientras sea verdadero p"
        :length "Devuelve la longitud de la secuencia"
        :reverse "Devuelve el inverso de la secuencia"
        :trans "Transpone la matriz dada"
        :distl "Distribuir desde la izquierda"
        :distr "Distribuir desde la derecha"
        :apndl "Concatenar a la izquierda"
        :apndr "Concatenar a la derecha"
        :rotl "Rotar hacia la izquierda"
        :rotr "Rotar hacia la derecha"
        :and "Verdadero, si ambos lo son"
        :or "Verdadero, si alguno lo es"
        :not "Verdadero a falso y viceversa"
        :addition "Suma"
        :subtraction "Resta"
        :product "Multiplicación"
        :quotient "Cociente"
        :atom "Verifica si es un átomo"
        :eq "Son iguales?"
        :null "Verifica si es la secuencia vacía"
        :s "Para cualquier entero positivo s"
        :sr "Para cualquier entero positivo s"
        :tl "Cola desde la izquierda"
        :tr "Cola desde la derecha"
        :id "Devuelve el operando"
        :over "Superlínea"
        :alpha "Alfa"
        :then "Entonces"
        :empty "Vacío"
        :undef "Indefinido"}})

(def opts {:dict dictionary})
(def trs (partial tr opts))
