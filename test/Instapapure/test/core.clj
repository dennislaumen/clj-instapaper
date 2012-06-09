(ns Instapapure.test.core
  (:use [Instapapure.core])
  (:use [midje.sweet])
  (:require [clj-http.client])
  (:use clj-http.fake))

(with-fake-routes {
    "https://www.instapaper.com/api/authenticate" (fn [request] {:status 200 :headers {} :body "Welcome!"})
  }
                  
  (fact 
    (auth "marco" "arment") => (contains {:status 200})))
