(ns re-frame-with-firebase.events
  (:require
   [re-frame.core :as re-frame]
   [re-frame-with-firebase.db :as db]))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::set-user
 (fn [db [event-name user]]
   (assoc db :user user :user-loading? false)))
