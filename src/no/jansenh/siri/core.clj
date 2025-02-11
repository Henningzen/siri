(ns no.jansenh.siri.core
  (:require [aero.core :as aero])
  (:gen-class))


(defn read-config
  "Get the configuration map."
  []
  (aero/read-config (clojure.java.io/resource "config.edn")))


(defn greet
  "Callable entry point to the application."
  [data]
  (println (str "Hello, " (or (:arg data) "World") "!")))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (greet {:arg (first args)}))


(comment ;; Development playbook, do not remove.

  ;; Nifty way to get hold of what is evolving in the config map.
  (keys (read-config))


  ;; ---
  )
