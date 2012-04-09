(ns clj-time.local-test
  (:use clojure.test clj-time.local
        [utilize.testutils :only (do-at)])
  (:require (clj-time [core :as time] [format :as fmt]))
  (:import org.joda.time.DateTimeZone
           (org.joda.time.format DateTimeFormatter ISODateTimeFormat)
           java.util.Date java.sql.Timestamp))

(deftest test-now
  (is (= (time/from-time-zone (time/date-time 2010 1 1) (time/default-time-zone))
         (do-at (time/from-time-zone (time/date-time 2010 1 1) (time/default-time-zone)) 
                (local-now)))))

(deftest test-from-local-string
  (is (= (from-local-string "1998-04-25T00:00:00.000")
         (time/from-time-zone (time/date-time 1998 4 25) (time/default-time-zone)))))

(deftest test-to-local-date-time
  (is (nil? (to-local-date-time nil)))
  (is (nil? (to-local-date-time "")))
  (is (nil? (to-local-date-time "x")))
  (is (= (time/from-time-zone (time/date-time 1998 4 25) (time/default-time-zone)) (to-local-date-time (time/date-time 1998 4 25))))
  (is (= (time/from-time-zone (time/date-time 1998 4 25) (time/default-time-zone)) (to-local-date-time (Date. 893462400000))))
  (is (= (time/from-time-zone (time/date-time 1998 4 25) (time/default-time-zone)) (to-local-date-time (java.sql.Date. 893462400000))))
  (is (= (time/from-time-zone (time/date-time 1970 1 1) (time/default-time-zone)) (to-local-date-time 0)))
  (is (= (time/from-time-zone (time/date-time 1998 4 25) (time/default-time-zone)) (to-local-date-time 893462400000)))
  (is (= (time/from-time-zone (time/date-time 1998 4 25) (time/default-time-zone)) (to-local-date-time (Timestamp. 893462400000))))
  (is (= (time/from-time-zone (time/date-time 1998 4 25) (time/default-time-zone)) (to-local-date-time "1998-04-25T00:00:00.000"))))

(deftest test-format-local-time
  (is (= (fmt/unparse (ISODateTimeFormat/basicDateTime) (time/from-time-zone (time/date-time 1998 4 25) (time/default-time-zone)))
         (format-local-time (time/from-time-zone (time/date-time 1998 4 25) (time/default-time-zone)) :basic-date-time)))
  (is (= (fmt/unparse (ISODateTimeFormat/basicDateTime) (time/from-time-zone (time/date-time 1998 4 25) (time/default-time-zone)))
         (format-local-time (time/date-time 1998 4 25) :basic-date-time)))
  (is (= (fmt/unparse (ISODateTimeFormat/basicDateTime) (time/from-time-zone (time/date-time 1998 4 25) (time/default-time-zone)))
         (format-local-time (Date. 893462400000) :basic-date-time)))
  (is (= (fmt/unparse (ISODateTimeFormat/basicDateTime) (time/from-time-zone (time/date-time 1998 4 25) (time/default-time-zone)))
         (format-local-time (java.sql.Date. 893462400000) :basic-date-time)))
  (is (= (fmt/unparse (ISODateTimeFormat/basicDateTime) (time/from-time-zone (time/date-time 1970 1 1) (time/default-time-zone)))
         (format-local-time 0 :basic-date-time)))
  (is (= (fmt/unparse (ISODateTimeFormat/basicDateTime) (time/from-time-zone (time/date-time 1998 4 25) (time/default-time-zone)))
         (format-local-time 893462400000 :basic-date-time)))
  (is (= (fmt/unparse (ISODateTimeFormat/basicDateTime) (time/from-time-zone (time/date-time 1998 4 25) (time/default-time-zone)))
         (format-local-time (Timestamp. 893462400000) :basic-date-time)))
  (is (= (fmt/unparse (ISODateTimeFormat/basicDateTime) (time/from-time-zone (time/date-time 1998 4 25) (time/default-time-zone)))
         (format-local-time "1998-04-25T00:00:00.000" :basic-date-time))))

(defmacro when-available
  [sym & body]
  (try
    (and (resolve sym)
         (list* 'do body))
    (catch ClassNotFoundException _#)))

(defmacro when-not-available
  [sym & body]
  (when-not
    (try
      (resolve sym)
      (catch ClassNotFoundException _#
        nil))
    `(do ~@body)))

(deftest test-format-local-time-
  (letfn [(time-zone-fn []  (DateTimeZone/forID "Etc/GMT-7"))
          ( asserts []
            (println (time/default-time-zone))
            (println (format-local-time (time/date-time 1998 4 25) :basic-date-time)))]
    (when-available
     with-redefs
     (with-redefs [time/default-time-zone time-zone-fn]
       (asserts)))
    (when-not-available
     with-redefs
     (binding [time/default-time-zone time-zone-fn]
       (asserts)))))


(comment
America/Sao_Paulo,
(org.joda.time.DateTimeZone/getAvailableIDs)
(.getID (org.joda.time.DateTimeZone/getDefault))
US/Eastern
  )