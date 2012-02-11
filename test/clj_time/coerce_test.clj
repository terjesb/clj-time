(ns clj-time.coerce-test
  (:refer-clojure :exclude [extend])
  (:use clojure.test)
  (:use (clj-time core coerce))
  (:import java.util.Date))

(deftest test-from-long
  (is (= (date-time 1998 4 25) (from-long 893462400000))))

(deftest test-to-date
  (is (= (Date. 893462400000) (to-date (date-time 1998 4 25)))))

(deftest test-to-date-time
  (is (= (date-time 1998 4 25) (to-date-time (date-time 1998 4 25)))))

(deftest test-to-long
  (is (= 893462400000 (to-long (date-time 1998 4 25)))))

(deftest test-to-string
  (is (= (to-string (date-time 1998 4 25))
	 "1998-04-25T00:00:00.000Z")))

(deftest test-to-from-date
  (let [dt (from-long 893462400000)
        d  (to-date dt)]
    (is (instance? Date d))
    (is (= dt (from-date d)))))

(deftest test-from-string
  (is (= (from-string "1998-04-25T00:00:00.000Z")
	 (date-time 1998 4 25))))
