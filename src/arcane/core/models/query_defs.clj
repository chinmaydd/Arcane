(ns arcane.core.models.query-defs
  (:require [environ.core :refer [env]]
            [yesql.core :refer [defqueries]]))

(defqueries "arcane/core/models/arcane_queries.sql" {:connection (env :database-url)})