(ns no.jansenh.siri.utilities.xml-utilities
  (:require [clojure.data.xml :as xml]
            [clojure.java.io :as io])
  (:gen-class))


(defn parse-xml-string
  "Parses an XML string into a Clojure data structure.
   Returns meaningful messages if the XML is invalid."
  [xml-str]
  (try
    (xml/parse-str xml-str)
    (catch org.xml.sax.SAXParseException e
      (println "XML Parsing Error: Malformed XML structure!")
      nil)
    (catch Exception e
      (println "Unexpected Error while parsing XML!")
      (println (.getMessage e))
      nil)))


(defn parse-xml-resource-file
  "Parses an XML file from the resources folder given its filename."
  [filename]
  (let [resource-url (io/resource filename)]
    (if resource-url
      (with-open [stream (io/input-stream resource-url)]
        (xml/parse stream))
      (throw (ex-info "File not found in resources folder" {:filename filename})))))

