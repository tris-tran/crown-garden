(ns tristan.ca-test
  (:require [clojure.pprint :as pprint]
            [clojure.test :refer :all]
            [tristan.ca :refer :all]))

(deftest moore-coord
  (let [size-x 10
        size-y 10
        x 6
        y 6]
    (println (format "Printing neighbours for (%s %s)" x y))
    (pprint/pprint (moore-neighborhood x y))))

(deftest empty-lattice
  (let [width 5
        height 5
        tile-creator (fn [] ())
        fn-neighborhood moore-neighborhood
        lattice (create-emtpy-lattice width height tile-creator fn-neighborhood)]
    (println (format "Creating lattice (%sx%s)" width height))
    (pprint/pprint lattice)))




