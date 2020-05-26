(ns fp.components.examples
  (:require
   [re-frame.core :as rf]
   [fp.semantic :as ui]))

(defn man []
  [:> ui/container
   "man"])
