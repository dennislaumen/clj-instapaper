(ns clj-instapaper.core
  (:require [clj-http.client :as c]))

(defn authenticate
  "Returns true if the username and password are correct and false if they're 
  not. Exceptions with HTTP response details are thrown all other cases.
  
  This method validates an Instapaper username and password. Calling this
  before adding pages is not necessary. Use this if you want to only check
  credentials without adding a URL to Instapaper, such as when you first prompt
  the user for Instapaper credentials in a settings screen or on the first
  Instapaper request."
  ([username]
    (authenticate username ""))
  ([username password]
    (let [http-response (c/post "https://www.instapaper.com/api/authenticate" 
                                {:basic-auth [username password] 
                                 :throw-exceptions false})]
      (case (http-response :status)
        200 true
        403 false
        throw (new Exception http-response)))))

