(ns no.jansenh.siri.parsers.watchdog-notification.clj
  (:require [clojure.data.zip.xml :as zip-xml]
            [no.jansenh.siri.parsers.core :as parsers])
  (:gen-class))

(defn heartbeat-notification->map
  "Given the root XML document of a WatchdogNotification xml, this function
   will return a map of type watchdog-notification map.

  The function is tied to the root element of
  SIRI http://www.siri.org.uk/siri version 2.0 WatchdogNotification.

  The function will return a map of the raw zip-xml edn representation
  of the XML structure."
  [root]
  (let [heartbeat-notification (zip-xml/xml1-> root :Siri :HeartbeatNotification)]
    heartbeat-notification
    {:request-timestamp (zip-xml/xml1-> heartbeat-notification :RequestTimestamp zip-xml/text)
     :producer-ref (zip-xml/xml1-> heartbeat-notification :ProducerRef zip-xml/text)
     :status (zip-xml/xml1-> heartbeat-notification :HeartbeatNotification :Status zip-xml/text)
     :service-started-time (zip-xml/xml1-> heartbeat-notification :ServiceStartedTime zip-xml/text)}))

