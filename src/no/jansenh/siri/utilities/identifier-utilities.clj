(ns no.jansenh.siri.utilities.identifier-utilities
  (:import [java.util UUID]))

(defn generate-uuid []
  (str (UUID/randomUUID)))

