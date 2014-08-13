(ns clj-time.periodic
  (:require [clj-time.core :as ct])
  (:import [org.joda.time DateTime ReadablePeriod Period]))

(defn periodic-seq
  "Returns an infinite sequence of date-time values growing over specific period"
  ([^DateTime start ^ReadablePeriod period-like]
   (let [^Period period (.toPeriod period-like)]
     (map (fn [i]
            (ct/plus start (.multipliedBy period i)))
          (iterate inc 0))))
  ([^DateTime start ^DateTime end ^ReadablePeriod period-like]
   (let [^Period period (.toPeriod period-like)]
     (->> (iterate inc 0)
          (map (fn [i]
                 (ct/plus start (.multipliedBy period i))))
          (take-while (fn [^DateTime next]
                        (ct/before? next end)))))))
