(ns form-app.events
  (:require
   [re-frame.core :as re-frame]
   [form-app.db :as db]
   [day8.re-frame.http-fx]
[ajax.core :as ajax]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::update-name
 (fn [db [_ val]]
   (assoc db :name val))
 )

(re-frame/reg-event-fx                            
 ::fetch-medals                 
 (fn [{:keys [db]} _]                    
   {:db   (assoc db :show-twirly true)  
    :http-xhrio {:method          :get
                 :uri             "https://run.mocky.io/v3/232d226b-db85-4528-8907-89837ef1032f"
                 :timeout         8000                                          
                 :response-format (ajax/json-response-format {:keywords? true})  
                 :on-success      [::fetch-medals-success]
                 :on-failure      [:bad-http-result]}}))


 (re-frame/reg-event-db
  ::fetch-medals-success
  (fn [db [_ data]]
    (-> db 
        (assoc :show-twirly false)
        (assoc :countries data)
        )))


(re-frame/reg-event-db
 ::change-sort
 (fn [db [_ selected-sort]]
   (assoc db :sort selected-sort))
 )