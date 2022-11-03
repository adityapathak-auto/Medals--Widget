(ns form-app.subs
  (:require
   [re-frame.core :as re-frame]
   [day8.re-frame.http-fx]
   [ajax.core :as ajax]
   
   ))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::show-twirly
 (fn [db]
   (:show-twirly db))
 )

(re-frame/reg-sub
 ::countries
 (fn [db]
   (:countries db)))

(re-frame/reg-sub
 ::sort
 (fn [db]
   (:sort db)))