(ns re-frame-with-firebase.views
  (:require
   [re-frame.core :as re-frame]
   [re-frame-with-firebase.firebase.init :refer [sign-out google-sign-in]]
   [re-frame-with-firebase.subs :as subs]))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        user (re-frame/subscribe [::subs/user])
        loading (re-frame/subscribe [::subs/user-loading?])]
    [:div
     [:h1
      "Hello from " @name]
     (when (and @loading (not @user))
       [:div "Loading..."])
     (if @user
       [:div
        [:div "The logged in user is" @user]
        [:button {:on-click #(sign-out)}  "Log out"]]
       [:div
        [:div "You are not logged in"]
        [:button {:on-click #(google-sign-in)}  "Login"]])]))
