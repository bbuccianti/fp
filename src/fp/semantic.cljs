(ns fp.semantic
  (:require
   [cljsjs.semantic-ui-react]
   [goog.object]))

(def semantic-ui js/semanticUIReact)

(defn component
  "Get a component from semantic-ui-react"
  [k & ks]
  (if (seq ks)
    (apply goog.object/getValueByKeys semantic-ui k ks)
    (goog.object/get semantic-ui k)))

(def container (component "Container"))
(def button    (component "Button"))
(def input     (component "Input"))
(def segment   (component "Segment"))
