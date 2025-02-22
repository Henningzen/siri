(ns no.jansenh.siri.utilities.time-date-utilities
  (:import [java.time ZonedDateTime ZoneId]
           [java.time.format DateTimeFormatter]))


;; Define the formatter as a static variable (constant)
(def timestamp-formatter
  (DateTimeFormatter/ofPattern "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"))


(defn current-timestamp []
  (.format (ZonedDateTime/now) timestamp-formatter))


(defn future-timestamp [timestamp seconds]
  (let [zoned-date-time (ZonedDateTime/parse timestamp timestamp-formatter)]
    (.format (.plusSeconds zoned-date-time seconds) timestamp-formatter)))

;; Example Usage
(defn -main []
  (let [now (current-timestamp)]
    (println "Current Timestamp: " now)
    (println "Timestamp 100 seconds later: "
             (future-timestamp now 100))))

