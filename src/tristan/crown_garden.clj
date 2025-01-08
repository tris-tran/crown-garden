(ns tristan.crown-garden
  (:require [clojure.pprint :as pprint])
  (:gen-class))

(def garden-size [10 10])

(def types [:wall :garden])
(defrecord GardenTile [id neighbours ttype tprint-debug])
(defrecord Garden [garden width height])

(defn garden-print-tile
  [garden-tile]
  (case (:ttype garden-tile)
    :wall "#"
    "+"))

(defn create-empty-garden
  [width height]
  (let [size (* width height)
        garden-tiles (make-array GardenTile size)]
    (for [tile garden-tiles]
      (assoc tile :ttype :garden))))

(defn garden-get-tile
  [garden x y]
  
(defn garden-print-debug
  [garden width height]
  (doseq [w (range width)]
    (do
      (print "\n")
      (doseq [h (range height)]
        (print "#")))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [garden (apply create-empty-garden garden-size)]
    (pprint/pprint garden)
    (garden-print-debug garden 10 10)))

