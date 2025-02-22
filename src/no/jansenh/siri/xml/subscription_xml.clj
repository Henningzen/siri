(ns no.jansenh.siri.subscription-xml
  (:require [clojure.data.xml :as xml]
            [no.jansenh.siri.core :as core :ref "read-config"]
            [no.jansenh.siri.utilities.file-utilities :as futils]
            [no.jansenh.siri.utilities.identifier-utilities :as idutils]
            [no.jansenh.siri.utilities.time-date-utilities :as dtutils]))


(defn generate-xml
  "Function that generates the formal SIRI subscription request version 2.0"
  []
  "Document me."
  (let [config (:siri-et-test (core/read-config))
        version (:siri-et-version config)
        current-timestamp (dtutils/current-timestamp)
        receiver-endpoint (:receiver-endpoint config)
        requestor-ref (idutils/generate-uuid)
        message-identifier (idutils/generate-uuid)
        heartbeat-interval (:heartbeat-interval config)
        subscriber-ref (:subscriber-ref config)
        subscription-identifier (:subscription-identifier config)
        initial-termination-time (dtutils/future-timestamp (dtutils/current-timestamp) 300)]
    (xml/element :Siri
                 {:version version
                  :xmlns "http://www.siri.org.uk/siri"
                  :xmlns:ns2 "http://www.ifopt.org.uk/acsb"
                  :xmlns:ns3 "http://www.ifopt.org.uk/ifopt"
                  :xmlns:ns4 "http://datex2.eu/schema/2_0RC1/2_0"}
                 (xml/element :SubscriptionRequest {}
                              (xml/element :RequestTimestamp {} current-timestamp)
                              (xml/element :Address {} receiver-endpoint)
                              (xml/element :RequestorRef {} requestor-ref)
                              (xml/element :MessageIdentifier {} message-identifier)
                              (xml/element :SubscriptionContext {}
                                           (xml/element :HeartbeatInterval {} heartbeat-interval)
                                           (xml/element :EstimatedTimetableSubscriptionRequest {}
                                                        (xml/element :SubscriberRef {} subscriber-ref)
                                                        (xml/element :SubscriptionIdentifier {} subscription-identifier)
                                                        (xml/element :InitialTerminationTime {} initial-termination-time)
                                                        (xml/element :EstimatedTimetableRequest {:version version}
                                                                     (xml/element :RequestTimestamp {} current-timestamp)
                                                                     (xml/element :MessageIdentifier {} message-identifier))))))))


(defn xml-to-string
  [xml-data]
  (xml/emit-str xml-data))


(defn write-to-file
  [file-name xml-string]
  (futils/write-to-data-file file-name xml-string))


(comment
  ;; ---------------------------------------------------------------------------
  ;; Test & validate

  (->> (generate-xml)
       xml-to-string
       (write-to-file "siri-subscrition.xml"))

  ;;---> comment
  )
