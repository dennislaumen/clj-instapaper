(ns Instapapure.core
  (:require  [clj-http.client :as c]))

(defn auth
  [username password]
  (c/post "https://www.instapaper.com/api/authenticate"
    {:basic-auth [username password]}))

