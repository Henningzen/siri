(ns no.jansenh.siri.utilities.xml-utilities
  (:require [clojure.data.xml :as xml])
  (:gen-class))


(defn parse-xml-string
  "Parses an XML string into a Clojure data structure.
   Returns meaningful messages if the XML is invalid."
  [xml-str]
  (try
    (xml/parse-str xml-str)
    (catch org.xml.sax.SAXParseException e
      (println "XML Parsing Error: Malformed XML structure!")
      (println (.getMessage e))
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


(comment

  ;; Testing the concepts:
  (parse-xml-string "<root><child>Content</child></root>")
  (parse-xml-string "<root><child>Broken XML</child><<</root>")
  (parse-xml-resource-file "keep-me.xml")
  (parse-xml-resource-file "no-exist.xml")

  ;; ---> comment
  )


(comment
  ;; keep for example
  (let [tags (xml/element :foo {:foo-attr "foo value"}
                          (xml/element :bar {:bar-attr "bar value"}
                                       (xml/element :baz {} "The bajas value")))
        file-name (io/resource "keep-me.xml")
        path  "/home/jansenh/dev/siri/data/leave-me.xml"]
    (with-open [out-file (java.io.FileWriter. path)]
      (xml/emit tags out-file)))
  ;; ---> comment
  )
