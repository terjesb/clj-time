(ns clj-time.local-test
  (:use clojure.test clj-time.local)
  (:require (clj-time [core :as time]))
  (:import java.util.Date java.sql.Timestamp))

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
  (is (= "19980425T000000.000-0400" (format-local-time (time/date-time 1998 4 25) :basic-date-time)))
  (is (= "19980425T000000.000-0400" (format-local-time (Date. 893462400000) :basic-date-time)))
  (is (= "19980425T000000.000-0400" (format-local-time (java.sql.Date. 893462400000) :basic-date-time)))
  (is (= "19700101T000000.000-0500" (format-local-time 0 :basic-date-time)))
  (is (= "19980425T000000.000-0400" (format-local-time 893462400000 :basic-date-time)))
  (is (= "19980425T000000.000-0400" (format-local-time (Timestamp. 893462400000) :basic-date-time)))
  (is (= "19980425T000000.000-0400" (format-local-time "1998-04-25T00:00:00.000" :basic-date-time))))


(comment
America/Sao_Paulo,
(org.joda.time.DateTimeZone/getAvailableIDs)
(.getID (org.joda.time.DateTimeZone/getDefault))
US/Eastern
  )