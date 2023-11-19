(ns re-frame-with-firebase.firebase.init
  (:require
   [re-frame.core :refer [dispatch]]
   [re-frame-with-firebase.events :as events]
   ["firebase/app" :as firebase]
   ["firebase/auth" :refer [GoogleAuthProvider getAuth signInWithPopup onAuthStateChanged signOut]]))

(defn store-user [user]
  (let [user-to-store (if user {:uid (.-uid user)
                                :name (.-displayName user)} nil)]
    (dispatch [::events/set-user user-to-store])))

(defn init []
  (when (zero? (alength (firebase/getApps)))
    (println "initalise firebase")
    (firebase/initializeApp #js {:apiKey ""
                                 :authDomain ""
                                 :projectId ""
                                 :storageBucket ""
                                 :messagingSenderId ""
                                 :appId ""
                                 :measurementId ""})
    (onAuthStateChanged (getAuth) (fn [user]
                                    (store-user user)))))


(defn google-sign-in []
  (let [provider (GoogleAuthProvider.)
        auth (getAuth)]
    (signInWithPopup auth provider)))

(defn sign-out []
  (signOut (getAuth)))

(comment
  (google-sign-in)
  (init))

