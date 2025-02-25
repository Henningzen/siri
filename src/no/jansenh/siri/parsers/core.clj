(ns no.jansenh.siri.parsers.core
  (:require [clojure.data.zip.xml :as zip-xml]
            [clojure.java.io :as io]
            [clojure.xml :as xml]
            [clojure.zip :as zip])
  (:gen-class))

(defn read-resource-file->xml
  "Read a file in resources folder and return xml-zip edn."
  [file-name]
  (let [root (-> file-name
                 io/resource
                 io/input-stream
                 xml/parse
                 zip/xml-zip)]
    root))
