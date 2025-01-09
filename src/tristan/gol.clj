(ns tristan.gol
  (:require [tristan.ca :as ca]
            [clojure.pprint :as pprint]))

;; :dead :alive
(defrecord Cell [state])

(def game-size [10 10])

(def alive-cells [[1 1 (Cell. :alive)]
                  [1 2 (Cell. :alive)]
                  [2 1 (Cell. :alive)]
                  [2 2 (Cell. :alive)])

(defn empty-game
  [width height]
  (let [tile-creator (fn [x y] ())
        fn-neighborhood moore-neighborhood]
    (ca/create-empty-lattice width height tile-creator fn-neighborhood)))

   

