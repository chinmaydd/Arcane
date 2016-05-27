(ns arcane.core.views.arcane-layout
  (:require [hiccup.page :refer [html5 include-css]]
            [hiccup.core :refer [html h]]))

(defn edit-response [response]
  (html
    [:div.response
      [:form {:action (str "/edit/" (:id response)) :method "post"}
        [:input {:type "hidden" :name "id" :value (h (:id response))}]
        [:div.column-1
          [:input#name-input {:type "text" :name "name" :placeholder "Name" :value (h (:name response))}]]
        [:div.column-2
          [:input#phone-input {:type "text" :name "phone" :placeholder "Phone" :value (h (:phone response))}]]
        [:div.column-3
          [:input#email-input {:type "text" :name "email" :placeholder "Email" :value (h (:email response))}]]
         [:div.column-4
          [:input#place-input {:type "text" :name "place" :placeholder "Place" :value (h (:place response))}]]
        [:div.button-group
          [:button.button.update {:type "submit"} "Update"]]]
      [:div.clear-row]]))

(defn common-layout [& body]
  (html5
    [:head
     [:title "Arcane"]
     (include-css "/css/arcane.css")
     (include-css "http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css")]
    [:body
     [:div#wrapper
      [:h1#content-title "Arcane"]
      [:div#responses
       [:div.response.header
        [:div.column-1
         [:i.icon-user.icon-style] " Name"]
        [:div.column-2
         [:i.icon-phone.icon-style] " Phone"]
        [:div.column-3
         [:i.icon-envelope.icon-style] " Email"]
        [:div.column-4
         [:i.icon-heart.icon-style] " Place"]
        [:div.clear-row]]
       body]]]))

(defn add-response-form []
  (html
    [:div.response
      [:form {:action "/post" :method "post"}
        [:div.column-1
          [:input#name-input {:type "text" :name "name" :placeholder "Name"}]]
        [:div.column-2
          [:input#phone-input {:type "text" :name "phone" :placeholder "Phone"}]]
        [:div.column-3
          [:input#email-input {:type "text" :name "email" :placeholder "Email"}]]
        [:div.column-4
          [:input#place-input {:type "text" :name "place" :placeholder "Place"}]]
        [:button.button.add {:type "submit"} "Add "]]
        [:div.clear-row]]))

(defn read-response [response]
  (html
    [:div.response
      [:div.response-text
        [:div.column-1 (h (:name response))]
        [:div.column-2 (h (:phone response))]
        [:div.column-3 (h (:email response))]
        [:div.column-4 (h (:place response))]]
      [:div.button-group
        [:form {:action (str "/edit/" (h (:id response))) :method "get"}
          [:button.button.edit {:type "submit"}
            [:i.icon-pencil]]]
        [:form {:action (str "/delete/" (h (:id response))) :method "post"}
          [:button.button.remove {:type "submit"}
            [:i.icon-remove]]]]
      [:div.clear-row]]))