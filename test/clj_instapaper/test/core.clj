(ns clj-instapaper.test.core
  (:use clj-http.fake)
  (:use clj-instapaper.core)
  (:use midje.sweet))

(with-fake-routes {
  "https://www.instapaper.com/api/authenticate"
  (fn [request]
    (let [auth-header ((request :headers) "Authorization") marco-arment "Basic TWFyY286QXJtZW50" nate-weiner "Basic TmF0ZTpXZWluZXI=" rich-ziade "Basic UmljaDpaaWFkZQ=="]
      (cond
        (= auth-header marco-arment)
          {:status 200 :headers {} :body "OK"}
        (= auth-header nate-weiner)
          {:status 403 :headers {} :body "Invalid username or password."}
        (= auth-header rich-ziade)
          {:status 500 :headers {} :body "The service encountered an error. Please try again later."}
        :else
          {:status 503 :headers {} :body "Service Unavailable"})))
  }

  (fact "Authentication"
    (authenticate "Marco" "Arment") => true
    (authenticate "Nate" "Weiner") => (throws "Invalid username or password.")
    (authenticate "Rich" "Ziade") => (throws "The service encountered an error. Please try again later.")
    (authenticate "Tim" "Cook") => throws))

