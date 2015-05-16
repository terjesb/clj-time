(ns clj-time.instant
    "An optional convenience namespaces that allows key JodaTime types
    to be transparently serialized with the Clojure reader (via instant literals)."
    )


(defmethod print-dup java.util.Date
  [^java.util.Date d ^java.io.Writer out]
  (.write out
          (str "#="
               `(java.util.Date. ~(.getYear d)
                                 ~(.getMonth d)
                                 ~(.getDate d)
                                 ~(.getHours d)
                                 ~(.getMinutes d)
                                 ~(.getSeconds d)))))


(defmethod print-dup org.joda.time.base.AbstractInstant
  [^org.joda.time.base.AbstractInstant d out]
  (print-dup (.toDate d) out))


(defmethod print-dup org.joda.time.base.AbstractPartial
  [^org.joda.time.base.AbstractPartial d out]
  (print-dup (.toDate d) out))
