(ns clj-time.coerce-test
  (:refer-clojure :exclude [extend])
  (:use clojure.test)
  (:use (clj-time core coerce))
  (:import java.util.Date java.sql.Timestamp))

(deftest test-from-date
  (let [dt (from-long 893462400000)
        d  (to-date dt)]
    (is (instance? Date d))
    (is (= dt (from-date d)))))

(deftest test-from-long
  (is (= (date-time 1998 4 25) (from-long 893462400000))))

(deftest test-from-string
  (is (= (from-string "1998-04-25T00:00:00.000Z")
         (date-time 1998 4 25))))

(deftest test-to-date
  (is (nil? (to-date nil)))
  (is (= (Date. 893462400000) (to-date (date-time 1998 4 25))))
  (is (= (Date. 893462400000) (to-date (Date. 893462400000))))
  (is (= (Date. 893462400000) (to-date (Timestamp. 893462400000)))))

(deftest test-to-date-time
  (is (nil? (to-date-time nil)))
  (is (= (date-time 1998 4 25) (to-date-time (date-time 1998 4 25))))
  (is (= (date-time 1998 4 25) (to-date-time (Date. 893462400000))))
  (is (= (date-time 1998 4 25) (to-date-time (Timestamp. 893462400000)))))

(deftest test-to-long
  (is (nil? (to-long nil)))
  (is (= 893462400000 (to-long (date-time 1998 4 25))))
  (is (= 893462400000 (to-long (Date. 893462400000))))
  (is (= 893462400000 (to-long (Timestamp. 893462400000)))))

(deftest test-to-string
  (is (nil? (to-string nil)))
  (is (= "1998-04-25T00:00:00.000Z" (to-string (date-time 1998 4 25))))
  (is (= "1998-04-25T00:00:00.000Z" (to-string (Date. 893462400000))))
  (is (= "1998-04-25T00:00:00.000Z" (to-string (Timestamp. 893462400000)))))
