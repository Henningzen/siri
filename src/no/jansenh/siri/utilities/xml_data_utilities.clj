(ns no.jansenh.siri.utilities.xml-data-utilities
  (:require [clojure.data.xml :as xml]
            [clojure.java.io :as io]))


(comment

  ;; keep for example
  (let [tags (xml/element :foo {:foo-attr "foo value"}
                          (xml/element :bar {:bar-attr "bar value"}
                                       (xml/element :baz {} "The baz value")))
        path1 "/home/jansenh/dev/siri/resources/mix.xml"
        path  "/home/jansenh/dev/siri/resources/keep-me.xml"]
    (with-open [out-file (java.io.FileWriter. path)]
      (xml/emit tags out-file)))
  ;; ---> comment
  )
