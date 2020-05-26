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

(def container        (component "Container"))
(def button           (component "Button"))
(def button-group     (component "Button" "Group"))
(def button-content   (component "Button" "Content"))
(def input            (component "Input"))
(def segment          (component "Segment"))
(def segment-group    (component "Segment" "Group"))
(def sidebar          (component "Sidebar"))
(def sidebar-pushable (component "Sidebar" "Pushable"))
(def sidebar-pusher   (component "Sidebar" "Pusher"))
(def icon             (component "Icon"))
(def checkbox         (component "Checkbox"))
(def message          (component "Message"))
(def message-header   (component "Message" "Header"))
(def message-content  (component "Message" "Content"))
(def grid             (component "Grid"))
(def grid-row         (component "Grid" "Row"))
(def grid-column      (component "Grid" "Column"))
(def card             (component "Card"))
(def card-content     (component "Card" "Content"))
(def card-description (component "Card" "Description"))
(def card-header      (component "Card" "Header"))
(def card-meta        (component "Card" "Meta"))
(def header           (component "Header"))
(def divider          (component "Divider"))
(def checkbox         (component "Checkbox"))
