(ns re-frame-with-firebase.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [re-frame-with-firebase.events :as events]
   [re-frame-with-firebase.views :as views]
   [re-frame-with-firebase.config :as config]
   [re-frame-with-firebase.firebase.init :as fb]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (fb/init)
  (dev-setup)
  (mount-root))
