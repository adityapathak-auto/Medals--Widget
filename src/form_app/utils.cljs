(ns form-app.utils
  (:require [reagent.core :as r]
           ))


(defn sort-medals [countries,sort-by]
  (sort (fn [countryA countryB] (cond
                                  (> (get countryA sort-by) (get countryB sort-by)) -1
                                  (< (get countryA sort-by) (get countryB sort-by)) 1
                                  :else (case sort-by
                                          :total (if (> (:gold countryA) (:gold countryB)) -1 1)
                                          :gold (if (>  (:silver countryA) (:silver countryB)) -1 1)
                                          :silver (if (> (:gold countryA) (:gold countryB)) -1 1)
                                          :bronze (if (> (:gold countryA) (:gold countryB)) -1 1)))) countries))












