(ns arcane.core.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [arcane.core.routes.arcane-routes :refer [arcane-routes]]
            [arcane.core.models.query-defs :as query]))

(defn init []
  (query/create-responses-table-if-not-exists!))

(defroutes app-routes
  (route/not-found "Not Found"))

(def app
  (-> (routes arcane-routes app-routes)
      (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))))