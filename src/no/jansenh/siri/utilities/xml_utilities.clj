(ns no.jansenh.siri.utilities.xml-utilities
  (:require [clojure.data.xml :as dxml]
            [clojure.data.zip.xml :as zip-xml]
            [clojure.java.io :as io]
            [clojure.xml :as xml]
            [clojure.zip :as zip]))


(defn xml-parser-string
  "Xml parser, reading any string and returing xxx of valid Xml."
  [s]
  (let [input-xml (java.io.StringReader. s)]
      (xml/parse input-xml)))


(defn xml-parser-resource
  "Xml parser, reading any file from resource folder."
  [file]
  (-> file
      io/resource
      io/file
      xml/parse
      zip/xml-zip))

(defn write-to-data-file [filename content]
  (let [file-path (io/file "data" filename)]
    (io/make-parents file-path) ;; Ensure the parent directories exist
    (spit file-path content)))

(defn -main [& args]
  (let [filename "output.txt"]
    (write-to-data-file filename "Hello, this is a test file!\nThis is another line of text.\n")
    (println "File written successfully.")))

(comment

  (-main)
  (def root (-> "keep-me.xml" io/resource io/file xml/parse zip/xml-zip))
  (xml-parser-resource "keep-me.xml")

  ;; ---> comment
  )

(comment

  (let [file-name (io/make-parents "mix.xml")
        file-path (io/resource file-name)]
    (str file-path))
(ff)

  ;; keep for example
  (let [tags (dxml/element :foo {:foo-attr "foo value"}
                          (dxml/element :bar {:bar-attr "bar value"}
                                       (dxml/element :baz {} "The baz value")))
        file-name (io/resource "keep-me.xml")
        path  "/home/jansenh/dev/siri/resources/keep-me.xml"]
    (with-open [out-file (java.io.FileWriter. path)]
      (dxml/emit tags out-file)))
  ;; ---> comment
  )
