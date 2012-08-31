(ns clj-instapaper.core
  (:require  [clj-http.client :as c]))

(defn auth
  "This method validates an Instapaper username and password. Calling this before adding pages is not necessary. Use this if you want to only check credentials without adding a URL to Instapaper, such as when you first prompt the user for Instapaper credentials in a settings screen or on the first Instapaper request."
  [username password]
  (c/post "https://www.instapaper.com/api/authenticate"
    {:basic-auth [username password]}))

