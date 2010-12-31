(ns clj-time.format-test
  (:refer-clojure :exclude [extend])
  (:use clojure.test)
  (:use (clj-time core format))
  (:import java.util.Locale))

(deftest test-formatter
  (let [fmt (formatter "yyyyMMdd")]
    (is (= (date-time 2010 3 11)
           (parse fmt "20100311")))))

(deftest test-parse
  (let [fmt (formatters :date)]
    (is (= (date-time 2010 3 11)
           (parse fmt "2010-03-11"))))
  (let [fmt (formatters :basic-date-time)]
    (is (= (date-time 2010 3 11 17 49 20 881)
           (parse fmt "20100311T174920.881Z")))))

(deftest test-unparse
  (let [fmt (formatters :date)]
    (is (= "2010-03-11"
           (unparse fmt (date-time 2010 3 11)))))
  (let [fmt (formatters :basic-date-time)]
    (is (= "20100311T174920.881Z"
           (unparse fmt (date-time 2010 3 11 17 49 20 881))))))

(deftest test-formatter-modifiers
  (let [fmt (formatter "YYYY-MM-dd hh:mm z" (time-zone-for-id "America/Chicago"))]
    (is (= "2010-03-11 11:49 CST"
           (unparse fmt (date-time 2010 3 11 17 49 20 881)))))
  (let [fmt (with-zone (formatters :basic-date-time) (time-zone-for-id "America/Chicago"))]
    (is (= "20100311T114920.881-0600"
           (unparse fmt (date-time 2010 3 11 17 49 20 881)))))
  (let [fmt (with-locale (formatters :rfc822) Locale/ITALY)]
    (is (= "gio, 11 mar 2010 17:49:20 +0000"
           (unparse fmt (date-time 2010 3 11 17 49 20 881)))))
  (let [fmt (with-pivot-year (formatter "YY") 2050)]
    (is (= (date-time 2075 1 1)
           (parse fmt "75")))))
