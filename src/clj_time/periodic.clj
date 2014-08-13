(ns clj-time.periodic
  (:require [clj-time.core :as ct])
  (:import [org.joda.time DateTime ReadablePeriod Period]))

(defn periodic-seq
  "Returns an infinite sequence of date-time values growing over specific period"
  [^DateTime start ^ReadablePeriod period-like]
   (let [^Period period (.toPeriod period-like)]
     (map (fn [^long i]
            (ct/plus start (.multipliedBy period i)))
          (iterate inc 0))))
