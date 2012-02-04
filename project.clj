;; common dependencies:
(def deps '[[joda-time "2.0"]])

;; project definition with additional dependencies:
(defproject clj-time "0.3.5-SNAPSHOT"
  :description "A date and time library for Clojure, wrapping Joda Time."
  :dev-dependencies [[utilize "0.1.2"]
                     [lein-multi "1.1.0"]]
  :dependencies ~(conj deps '[org.clojure/clojure "1.2.1"])
  :multi-deps {"1.3" ~(conj deps '[org.clojure/clojure "1.3.0"])
               "1.4B" ~(conj deps '[org.clojure/clojure "1.4.0-beta1"])})
