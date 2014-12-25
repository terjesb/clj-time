(defproject clj-time/clj-time "0.9.0"
  :description "A date and time library for Clojure, wrapping Joda Time."
  :url "https://github.com/clj-time/clj-time"
  :mailing-list {:name "clj-time mailing list"
                 :archive "https://groups.google.com/forum/?fromgroups#!forum/clj-time"
                 :post "clj-time@googlegroups.com"}
  :license {:name "MIT License"
            :url "http://www.opensource.org/licenses/mit-license.php"
            :distribution :repo}
  :dependencies [[joda-time "2.6"] [org.clojure/clojure "1.6.0"]]
  :min-lein-version "2.0.0"
  :profiles {:dev {:plugins [[codox "0.8.10"]]}
             :midje {:dependencies [[midje "1.6.3"]]
                     :plugins      [[lein-midje "3.1.3"]
                                    [midje-readme "1.0.3"]]
                     :midje-readme {:require "[clj-time.core :as t] [clj-time.predicates :as pr]"}}
             :1.7    {:dependencies [[org.clojure/clojure "1.7.0-alpha4"]]}
             :master {:repositories [["snapshots" "https://oss.sonatype.org/content/repositories/snapshots/"]]
                   :dependencies [[org.clojure/clojure "1.7.0-master-SNAPSHOT"]]}}
  :aliases {"test-all" ["with-profile" "dev,master,default,midje:dev,default,midje:dev,1.7,midje" "test"]})
