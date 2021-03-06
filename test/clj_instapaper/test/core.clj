(ns clj-instapaper.test.core
  (:use clj-http.fake)
  (:use clj-instapaper.core)
  (:use midje.sweet))

(with-fake-routes {
  "https://www.instapaper.com/api/authenticate"
  (fn [request]
    (let [auth-header ((request :headers) "authorization") 
          nate-weiner "Basic TmF0ZTpXZWluZXI=" 
          rich-ziade "Basic UmljaDpaaWFkZQ=="
          nate "Basic TmF0ZTo="]
      (cond
        (or (= auth-header nate-weiner) (= auth-header nate))
          {:status 403 :headers {} :body "Invalid username or password."}
        (= auth-header rich-ziade)
          {:status 500 :headers {} :body "The service encountered an error. Please try again later."}
        :else
          {:status 200 :headers {} :body "OK"})))}

  (fact "Authentication"
    (authenticate "Marco" "Arment") => true
    (authenticate "Nate" "Weiner") => false 
    (authenticate "Rich" "Ziade") => throws
    (authenticate "Marco") => true)
    (authenticate "Nate") => false)

