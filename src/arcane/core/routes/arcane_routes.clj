(ns arcane.core.routes.arcane-routes
  (:require [ring.util.response :as response]
            [compojure.core :refer :all]
            [arcane.core.views.arcane-layout :refer [common-layout
                                                                 read-response
                                                                 add-response-form
                                                                 edit-response]]
            [arcane.core.models.query-defs :as query]))

(defn display-response [response response-id]
  (if (not= (and response-id (Integer. response-id)) (:id response))
    (read-response response)
    (edit-response response)))

(defn post-route [request]
  (let [name  (get-in request [:params :name])
        phone (get-in request [:params :phone])
        email (get-in request [:params :email])
        place (get-in request [:params :place])
        secret (get-in request [:params :secret])
        ]
    (query/insert-response<! {:name name :phone phone :email email :place place :secret secret})
    (response/redirect "/")))

(defn get-route [request]
  (let [response-id (get-in request [:params :response-id])]
    (common-layout
      (for [response (query/all-responses)]
        (display-response response response-id))
      (add-response-form))))

(defn delete-route [request]
  (let [response-id (get-in request [:params :response-id])]
    (query/delete-response<! {:id (Integer. response-id)})
    (response/redirect "/")))

(defn update-route [request]
  (let [response-id (get-in request [:params :id])
        name       (get-in request [:params :name])
        phone      (get-in request [:params :phone])
        email      (get-in request [:params :email])
        place (get-in request [:params :place])
        secret (get-in request [:params :secret])
        ]
    (query/update-response<! {:name name :phone phone :email email :place place :secret secret :id (Integer. response-id)})
    (response/redirect "/")))

(defroutes arcane-routes
  (GET  "/"                   [] get-route)
  (POST "/post"               [] post-route)
  (GET  "/edit/:response-id"   [] get-route)
  (POST "/edit/:response-id"   [] update-route)
  (POST "/delete/:response-id" [] delete-route))