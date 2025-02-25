(ns no.jansenh.siri.parsers.subscription-response
  (:require [clojure.data.zip.xml :as zip-xml]
            [no.jansenh.siri.parsers.core :as parsers])
  (:gen-class))

(defn subscription-response->map
  "Given the root XML document of a SubscriptionResponse xml, this function
   will return a map of type subscription-resoponse maps.

  The function is tied to the root element of
  SIRI http://www.siri.org.uk/siri version 2.0 SubscriptionResponse

  The function will return a map of the raw zip-xml edn representation
  of the XML structure."
  [root]
  (let [subscription-response (zip-xml/xml1-> root :Siri :SubscriptionResponse)]
    {:response-timestamp (zip-xml/xml1-> subscription-response :ResponseTimestamp zip-xml/text)
     :responder-ref (zip-xml/xml1-> subscription-response :ResponderRef zip-xml/text)
     :request-message-ref (zip-xml/xml1-> subscription-response :RequestMessageRef zip-xml/text)
     :response-status {:response-timestamp (zip-xml/xml1-> subscription-response :ResponseStatus :ResponseTimestamp zip-xml/text)
                       :request-message-ref (zip-xml/xml1-> subscription-response :ResponseStatus :RequestMessageRef zip-xml/text)
                       :subscription-ref (zip-xml/xml1-> subscription-response :ResponseStatus :SubscriptionRef zip-xml/text)
                       :status (zip-xml/xml1-> subscription-response :ResponseStatus :Status zip-xml/text)}
     :service-status-time (zip-xml/xml1-> subscription-response :ServiceStartedTime zip-xml/text)}))
