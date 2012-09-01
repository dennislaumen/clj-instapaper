(ns clj-instapaper.test.core
  (:use clj-http.fake)
  (:use clj-instapaper.core)
  (:use midje.sweet))

(with-fake-routes {
  "https://www.instapaper.com/api/authenticate"
  (fn [request]
    (let [auth-header ((request :headers) "Authorization") nate-weiner "Basic TmF0ZTpXZWluZXI=" rich-ziade "Basic UmljaDpaaWFkZQ=="]
      (cond
        (= auth-header nate-weiner)
          {:status 403 :headers {} :body "Invalid username or password."}
        (= auth-header rich-ziade)
          {:status 500 :headers {} :body "The service encountered an error. Please try again later."}
        :else
          {:status 200 :headers {} :body "OK"})))
  }

  (fact "Authentication"
    (authenticate "Marco" "Arment") => true
    (authenticate "Nate" "Weiner") => false 
    (authenticate "Rich" "Ziade") => throws))

