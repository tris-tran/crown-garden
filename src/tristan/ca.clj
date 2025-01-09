(ns tristan.ca
  (:require [clojure.pprint :as pprint]))

(defrecord Automata [size-x size-y fn-neighbours tiles fn-ruleset])
(defrecord Tile [id coordinates neighbours status])

(defn coordinate-to-id
  [x y size-x]
  (+ y (* x size-x)))

(defn neighborhood-ids
  [x y size-x size-y fn-neighbrohood]
  (let [size (* size-x size-y)]
    (->> (fn-neighbrohood x y)
        (map #(apply (fn [x y] (coordinate-to-id x y size-x)) %))
        (filterv (fn [id] (and (< id size) (> id 0)))))))

(defn create-emtpy-lattice
  [width height tile-creator fn-neighborhood]
  (let [size (* width height)
        lattice-coordinates (for [x (range 0 width)
                                 y (range 0 height)]
                             [x y])]
    (mapv 
      #(apply 
         (fn [x y] 
           (let [index (coordinate-to-id x y width)
                 neighbour-ids (neighborhood-ids x y width height fn-neighborhood)
                 tile-record (Tile. index [x y] neighbour-ids (tile-creator))]
             tile-record)) %) lattice-coordinates)))

(defn create-single-tile
  [x y width fn-neighborhood tile-creator]
  (let [index (coordinate-to-id x y width)
        neighbour-ids (neighborhood-ids x y width height fn-neighborhood)]
    (Tile. index [x y] neighbour-ids (tile-creator))))

(defn neighborhood
  [x y neighbours]
  (map #(apply (fn [nx ny] [(+ x nx) (+ y ny)]) %) neighbours))

(def von-neumann-neighborhood-positions
  [[0 1] [0 -1] [1 0] [-1 0]])

(defn von-neumann-neighborhood
  [x y]
  (let [neighbours von-neumann-neighborhood-positions]
    (neighborhood x y neighbours)))

(def moore-neighborhood-positions
  [[0 1] [0 -1]
   [1 0] [1 1] [1 -1]
   [-1 0] [-1 1] [-1 -1]])

(defn moore-neighborhood
  [x y]
  (let [neighbours moore-neighborhood-positions]
    (neighborhood x y neighbours)))

(defn get-tiles
  [tile-ids automata]
  (let [tiles (:tiles automata)]
    (for [id tile-ids]
      (get-in tiles id))))

(defn apply-ruleset
  [automata]
  (let [size-x (:size-x automata)
        fn-ruleset (:fn-ruleset automata)
        tiles (:tiles automata)]
    (for [tile tiles]
      (let [neighbours-id (:neighbours tile)
            neighbours (get-tiles neighbours-id automata)]
        (fn-ruleset tile neighbours)))))

