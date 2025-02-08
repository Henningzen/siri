(ns no.jansenh.siri-et-test
  (:require [clojure.test :refer :all]
            [no.jansenh.siri-et :refer :all]))

(deftest verify-tests
  (testing "I verify true"
    (is (= 1 1))))
