(ns no.jansenh.siri.core
  (:require [aero.core :as aero]
            [clojure.java.io :as io]
            [no.jansenh.siri.utilities.time-date-utilities :as dtutils])
  (:gen-class))


(defn read-config
  "Get the configuration map."
  []
  (aero/read-config (io/resource "config.edn")))


(defn greet
  "Callable entry point to the application."
  [data]
  (println (str "Hello, " (or (:arg data) "World") "!")))


(defn -main
  "Main entry point to the application."
  [& args]
  ((println (str "Application started " (dtutils/current-timestamp)))
   (greet {:arg (first args)})))


(comment

  ;; ---------------------------------------------------------------------------
  ;;
  ;; Development playbook, do not remove.
  ;;

  ;; Nifty way to get hold of what is evolving in the config map.
  (keys (read-config))


  ;; ---
  )
