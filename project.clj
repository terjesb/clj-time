(defproject clj-time/clj-time "0.9.0-SNAPSHOT"
  :description "A date and time library for Clojure, wrapping Joda Time."
  :url "https://github.com/clj-time/clj-time"
  :mailing-list {:name "clj-time mailing list"
                 :archive "https://groups.google.com/forum/?fromgroups#!forum/clj-time"
                 :post "clj-time@googlegroups.com"}
  :license {:name "MIT License"
            :url "http://www.opensource.org/licenses/mit-license.php"
            :distribution :repo}
  :dependencies [[joda-time "2.3"] [org.clojure/clojure "1.6.0"]]
  :min-lein-version "2.0.0"
  :profiles {:dev {:plugins [[codox "0.6.1"]
                             [lein-midje "3.0.0"]
                             [midje-readme "1.0.2"]]
                   :dependencies [[midje "1.5.0"]]
                   :midje-readme {:require "[clj-time.core :as t] [clj-time.predicates :as pr]"}}
             :1.2 {:dependencies [[org.clojure/clojure "1.2.1"]]}
             :1.3 {:dependencies [[org.clojure/clojure "1.3.0"]]}
             :1.4 {:dependencies [[org.clojure/clojure "1.4.0"]]}
             :1.5 {:repositories [["snapshots" "https://oss.sonatype.org/content/repositories/snapshots/"]]
                   :dependencies [[org.clojure/clojure "1.5.1"]]}
             :master {:repositories [["snapshots" "https://oss.sonatype.org/content/repositories/snapshots/"]]
                   :dependencies [[org.clojure/clojure "1.7.0-master-SNAPSHOT"]]}}
  :aliases {"test-all" ["with-profile" "dev,default:dev,1.2,default:dev,1.3,default:dev,1.4,default:dev,1.5,default:dev,master,default" "test"]
            "test-14plus" ["with-profile" "dev,default:dev,1.4,default:dev,1.5,default:dev,master,default" "test"]})
