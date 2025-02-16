(ns no.jansenh.siri.subscription-xml
  (:require [clojure.data.xml :as xml]
            [clojure.java.io :as io]
            [no.jansenh.siri.core :as core :ref "read-config"]
            [no.jansenh.siri.utilities.file-utilities :as futils]))


(defn generate-xml
  []
  "Document me."
  (let [config (:siri-et-test (core/read-config))]
      (xml/element :Siri
                   {:version "2.0"
                    :xmlns "http://www.siri.org.uk/siri"
                    :xmlns:ns2 "http://www.ifopt.org.uk/acsb"
                    :xmlns:ns3 "http://www.ifopt.org.uk/ifopt"
                    :xmlns:ns4 "http://datex2.eu/schema/2_0RC1/2_0"}
                   (xml/element :SubscriptionRequest {}
                                (xml/element :RequestTimestamp {} "2018-09-04T12:11:30.913+02:00")
                                (xml/element :Address {} (:receiver-endpoint config))
                                (xml/element :RequestorRef {} "56c4fae2-b549-4259-b71f-4ff58958eceb")
                                (xml/element :MessageIdentifier {} "99fec1e6-2e4d-4d47-afe1-ea23e505f1b8")
                                (xml/element :SubscriptionContext {}
                                             (xml/element :HeartbeatInterval {} "PT1M"))
                                (xml/element :EstimatedTimetableSubscriptionRequest {}
                                             (xml/element :SubscriberRef {} "ENTUR_DEV")
                                             (xml/element :SubscriptionIdentifier {} "9b89af9b-12a7-4f19-9266-4c6fa0554332")
                                             (xml/element :InitialTerminationTime {} "2018-09-04T13:11:30.913+02:00")
                                             (xml/element :EstimatedTimetableRequest {:version "2.0"}
                                                          (xml/element :RequestTimestamp {} "2018-09-04T12:11:30.913+02:00")
                                                          (xml/element :MessageIdentifier {} "ceef79cf-b813-4f64-9204-9b888b0eb088")))))))


(defn xml-to-string
  "Converts XML data to a formatted string."
  [xml-data]
  (xml/emit-str xml-data))


(defn write-to-file [file-name xml-string]
  (futils/write-to-data-file file-name xml-string))


(defn -main []
  (println "Generated XML:")
  (println (xml-to-string generate-xml)))


(comment
  (write-to-file "siri-subscrition2.xml" (xml-to-string (generate-xml)))
)
