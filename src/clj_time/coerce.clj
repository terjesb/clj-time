(ns clj-time.coerce
  "Utilites to coerce Joda DateTime instances to and from various other types.
   For example, to convert a Joda DateTime to and from a Java long:

     => (to-long (date-time 1998 4 25))
     893462400000

     => (from-long 893462400000)
     #<DateTime 1998-04-25T00:00:00.000Z>"
  (:refer-clojure :exclude [extend second])
  (:require [clj-time.core :refer :all]
            [clj-time.format :as time-fmt])
  (:import [java.io Writer]
           [java.sql Timestamp]
           [java.util Date]
           [org.joda.time DateTime DateTimeZone DateMidnight YearMonth
                          LocalDate LocalDateTime]))

(defprotocol ICoerce
  (to-date-time ^org.joda.time.DateTime [obj] "Convert `obj` to a Joda DateTime instance."))

(defn from-long
  "Returns a DateTime instance in the UTC time zone corresponding to the given
   number of milliseconds after the Unix epoch."
  ^org.joda.time.DateTime
  [^Long millis]
  (DateTime. millis ^DateTimeZone utc))

(defn from-epoch
  "Returns a DateTime instance in the UTC time zone
   from given Unix epoch."
  ^org.joda.time.DateTime
  [^Long epoch]
  (from-long (* epoch 1000)))

(defn from-string
  "return DateTime instance from string using
   formatters in clj-time.format, returning first
   which parses"
  ^org.joda.time.DateTime
  [^String s]
  (time-fmt/parse s))

(def data-readers
  "tagged literal support if loader does not find \"data_readers.clj\""
  {'clj-time/date-time from-string})

(defn from-date
  "Returns a DateTime instance in the UTC time zone corresponding to the given
   Java Date object."
  ^org.joda.time.DateTime
  [^java.util.Date date]
  (when date
    (from-long (.getTime date))))

(defn from-sql-date
  "Returns a DateTime instance in the UTC time zone corresponding to the given
   java.sql.Date object."
  ^org.joda.time.DateTime
  [^java.sql.Date sql-date]
  (when sql-date
    (from-long (.getTime sql-date))))

(defn from-sql-time
  "Returns a DateTime instance in the UTC time zone corresponding to the given
   java.sql.Timestamp object."
  ^org.joda.time.DateTime
  [^java.sql.Timestamp sql-time]
  (when sql-time
    (from-long (.getTime sql-time))))

(defn to-long
  "Convert `obj` to the number of milliseconds after the Unix epoch."
  ^Long
  [obj]
  (when-let [dt (to-date-time obj)]
    (.getMillis dt)))

(defn to-epoch
  "Convert `obj` to Unix epoch."
  ^Long
  [obj]
  (when-let [millis (to-long obj)]
    (quot millis 1000)))

(defn to-date
  "Convert `obj` to a Java Date instance."
  ^java.util.Date
  [obj]
  (when-let [dt (to-date-time obj)]
    (Date. (.getMillis dt))))

(defn to-sql-date
  "Convert `obj` to a java.sql.Date instance."
  ^java.sql.Date
  [obj]
  (when-let [dt (to-date-time obj)]
    (java.sql.Date. (.getMillis dt))))

(defn to-sql-time
  "Convert `obj` to a java.sql.Timestamp instance."
  ^java.sql.Timestamp
  [obj]
  (when-let [dt (to-date-time obj)]
    (java.sql.Timestamp. (.getMillis dt))))

(defn to-string
  "Returns a string representation of obj in UTC time-zone
  using (ISODateTimeFormat/dateTime) date-time representation."
  ^String
  [obj]
  (when-let [dt (to-date-time obj)]
    (time-fmt/unparse (:date-time time-fmt/formatters) dt)))

(defn to-edn
  "Convert `obj` to a string representation readable by clojure.edn/read."
  ^String
  [obj]
  (when-let [dt (to-date-time obj)]
    (str "#clj-time/date-time \"" (to-string dt) "\"")))

;; pr and prn support to write edn
(defmethod print-method org.joda.time.DateTime
  [v ^java.io.Writer w]
  (.write w (to-edn v)))

(defn to-timestamp
  "Convert `obj` to a Java SQL Timestamp instance."
  ^java.sql.Timestamp
  [obj]
  (when-let [dt (to-date-time obj)]
    (java.sql.Timestamp. (.getMillis dt))))

(defn to-local-date
  "Convert `obj` to a org.joda.time.LocalDate instance"
  ^org.joda.time.LocalDate
  [obj]
  (when-let [dt (to-date-time obj)]
    (LocalDate. (.getMillis (from-time-zone dt (default-time-zone))))))

(defn to-local-date-time
  "Convert `obj` to a org.joda.time.LocalDateTime instance"
  ^org.joda.time.LocalDateTime
  [obj]
  (when-let [dt (to-date-time obj)]
    (LocalDateTime. (.getMillis (from-time-zone dt (default-time-zone))))))

(defn in-time-zone
  "Convert `obj` into `tz`, return org.joda.time.LocalDate instance."
  ^org.joda.time.LocalDate
  [obj tz]
  (when-let [dt (to-date-time obj)]
    (-> dt
        (to-time-zone tz)
        .toLocalDate)))

(extend-protocol ICoerce
  nil
  (to-date-time [_]
    nil)

  Date
  (to-date-time [date]
    (from-date date))

  java.sql.Date
  (to-date-time [sql-date]
    (from-sql-date sql-date))

  java.sql.Timestamp
  (to-date-time [sql-time]
    (from-sql-time sql-time))

  DateTime
  (to-date-time [date-time]
    date-time)

  DateMidnight
  (to-date-time [date-midnight]
    (.toDateTime date-midnight))

  YearMonth
  (to-date-time [year-month]
    (date-time (year year-month) (month year-month)))

  LocalDate
  (to-date-time [local-date]
    (date-time (year local-date) (month local-date) (day local-date)))

  LocalDateTime
  (to-date-time [local-date-time]
    (date-time (year local-date-time) (month local-date-time) (day local-date-time)
               (hour local-date-time) (minute local-date-time) (second local-date-time)
               (milli local-date-time)))

  Integer
  (to-date-time [integer]
    (from-long (long integer)))

  Long
  (to-date-time [long]
    (from-long long))

  String
  (to-date-time [string]
    (from-string string))

  java.sql.Timestamp
  (to-date-time [timestamp]
    (from-date timestamp)))
