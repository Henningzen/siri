(ns no.jansenh.siri.utilities.file-utilities
  (:require [clojure.java.io :as io]))


(defn write-to-data-file
  "Takes string content and writes to a file in the projects /data folder."
  [filename content]
  (let [file-path (io/file "data" filename)]
    (io/make-parents file-path)
    (spit file-path content)))
