(ns no.jansenh.siri.subscription-xml
  (:require [no.jansenh.siri.utilities.xml-utilities :as xutils])
  (:gen-class))


(comment

  (xutils/xml-parser-string"<?xml version=\"1.0\" encoding=\"UTF-8\"?>
                                 <foo><bar><baz>The baz value</baz></bar></foo>")

  (xutils/xml-parser-resource "keep-me.xml")

  ;; ---> comment
)
