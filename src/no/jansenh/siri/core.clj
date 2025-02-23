(ns no.jansenh.siri.core
  (:require [aero.core :as aero]
            [clojure.java.io :as io]
            [clojure.pprint :as pprint]
            [compojure.core :as comp]
            [compojure.route :as route]
            [no.jansenh.siri.utilities.time-date-utilities :as dtutils]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.response :as response])
  (:gen-class))

(defonce server (atom nil))

(defn read-config []
  (aero/read-config (io/resource "config.edn")))

(comp/defroutes app-routes

  (comp/GET "/" [] {:status 200
                    :body "<h1>no.jansenh.SIRI</h1>"
                    :headers {"Content-Type" "text/html; charset=UTF-8"}})

  (comp/ANY "/echo" req
    {:status 200
     :body (with-out-str (pprint/pprint req)) ;; TODO: make a comp
     :headers {"Content-Type" "text/plain"}})

  (comp/POST "/siri/et" {body :body}
    (let [raw-body (slurp body)]
      (println "Received raw body:")
      (println raw-body)
      (response/response "Raw body received and printed successfully")))

  (route/not-found {:status 404
                    :body "Not found."
                    :headers {"Content-Type" "text/plain"}}))

(def app
  (wrap-defaults app-routes site-defaults))

(defn start-server []
  (reset! server
          (jetty/run-jetty
           (fn [req] (app-routes req))
           {:port 3001
            :join? false})))

(defn stop-server []
  (when-some [s @server]
    (.stop s)
    (reset! server nil)))


(defn -main
  "Main entry point to the application."
  [& args]
  (do
    (println (str "Application started " (dtutils/current-timestamp)))
    (start-server)))


(comment

  ;; ---------------------------------------------------------------------------
  ;;
  ;; Development playbook, do not remove.
  ;;

  ;; Nifty way to get hold of what is evolving in the config map.
  (keys (read-config))
  (-main)
  (start-server)
  (stop-server)

  ;; ---

  (defroutes app-routes

    (route/not-found "Not Found"))

  (def app
    ))
