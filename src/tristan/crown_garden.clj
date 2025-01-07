(ns tristan.crown-garden
  (:require [clojure.pprint :as pprint])
  (:gen-class))

(def garden-size [10 10])

(def types [:wall :garden])
(defrecord GardenTile [ttype tprint-debug])
(defrecord Garden [garden width height])

(defn garden-print-tile
  [garden-tile]
  (case (:ttype garden-tile)
    :wall "#"
    "+"))

(def garden-wall (GardenTile. :wall garden-print-tile))

(def garden-get-col
  [col width heigth]
  (for [row (range height)]
    (+ width (* row col))))

(defn create-empty-garden
  [width height]
  (let [size (* width height)
        garden (make-array GardenTile size)]
    (for [tile garden]
      (assoc tile :ttype :wall))))

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

