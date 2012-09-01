(ns clj-instapaper.core
  (:require  [clj-http.client :as c]))

(defn authenticate
  "This method validates an Instapaper username and password. Calling this before adding pages is not necessary. Use this if you want to only check credentials without adding a URL to Instapaper, such as when you first prompt the user for Instapaper credentials in a settings screen or on the first Instapaper request."
  [username password]
  (let [http-response (c/post "https://www.instapaper.com/api/authenticate" {:basic-auth [username password] :throw-exceptions false})]
    (case (http-response :status)
      200 true
      403 (throw (new Exception "Invalid username or password."))
      500 (throw (new Exception "The service encountered an error. Please try again later."))
      throw (new Exception (str "Unexpected HTTP response: " http-response)))))

