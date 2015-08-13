## Changes Between 0.10.0 and 0.11.0

### Joda Time 2.8

[Joda Time](http://www.joda.org/joda-time/) has been [upgraded to `2.8.2`](http://www.joda.org/joda-time/upgradeto282.html).

### Clojure 1.7 by Default

The library now depends on Clojure `1.7.0`.



## Changes Between 0.9.0 and 0.10.0

### clj-time.instant

`clj-time.instant` is a new namespace that, when loaded, makes it possible
for the Clojure reader to serialize Joda Time data types.

### Joda Time 2.7

[Joda Time](http://www.joda.org/joda-time/) has been [upgraded to `2.7`](http://www.joda.org/joda-time/upgradeto270.html).

### `equal?`

A new predicate `equal?` has been added, which can be used like `before?` and
`after?` to assert two instants represent identical points in time regardless of
differences in time zone.

Contributed by [@gws](https://github.com/gws).

### `clj-time.jdbc`

A new namespace `clj-time.jdbc` has been added which extends protocols in
clojure.java.jdbc to allow for easy coercion to and from java.sql.Timestamp
driven by `clj-time.coerce`.

Contributed by [@gws](https://github.com/gws).

## Changes Between 0.8.0 and 0.9.0

### with-default-year

`clj-time.format/with-default-year` is a new function that accepts a formatter
and returns a formatter that uses provided year by default.

Contributed by lummax.

### InTimeUnitProtocol

`clj-time.core/InTimeUnitProtocol` is a new protocol that provides convenient
functions for time unit conversion:

``` clojure
(defprotocol InTimeUnitProtocol
  "Interface for in-<time unit> functions"
  (in-millis  [this] "Return the time in milliseconds.")
  (in-seconds [this] "Return the time in seconds.")
  (in-minutes [this] "Return the time in minutes.")
  (in-hours   [this] "Return the time in hours.")
  (in-days    [this] "Return the time in days.")
  (in-weeks   [this] "Return the time in weeks")
  (in-months  [this] "Return the time in months")
  (in-years   [this] "Return the time in years"))
```

### Clojure 1.2-1.4 Support Dropped

`clj-time` now officially supports Clojure `1.5.1` and later versions.

### Joda Time 2.6

[Joda Time](http://www.joda.org/joda-time/) has been [upgraded to `2.6`](http://www.joda.org/joda-time/upgradeto260.html).
