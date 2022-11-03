(ns form-app.views
  (:require 
            [form-app.events :as events]
            [form-app.subs :as subs]
            [form-app.utils :refer [sort-medals]]
            [re-frame.core :as re-frame]))

   


(defn display-medals [index {:keys [ code gold silver bronze total]}]
  
  ;; (for [[index item] (map-indexed (fn [index item] [index item]) medals)]
  ;;   (def obj {"gold" 10 "silver" 20 "bronze" 10 "total" 50})

  [:tr
   [:td (+ index 1)]
   [:td [:img {:alt code :width 20 :height 15 :src (str "https://countryflagsapi.com/png/" code)}]]
   [:td gold]
   [:td]
   [:td silver]
   [:td]
   [:td bronze]
   [:td]
   [:td total]]

  )




(defn main-panel []
  (let [sort @(re-frame/subscribe [::subs/sort])
        show-twirly (re-frame/subscribe [::subs/show-twirly])
        countries (sort-medals @(re-frame/subscribe [::subs/countries]) sort)]
    [:div
     
;; [:button {:on-click #(re-frame/dispatch [::events/update-name "aditya"])} "Update Name"]
[:button {:on-click #(re-frame/dispatch [::events/fetch-medals])} "Get Medals Data"]
     [:div.top-div "Medal Count"]
     [:table.tableS.padding-table-columns
      [:thead
       [:tr.trS {:col-span "10"}
        [:td]
        [:td.tdS "Countries"]
        [:td.gold {:on-click #(re-frame/dispatch [::events/change-sort :gold])} ]
        [:td]
        [:td.silver {:on-click #(re-frame/dispatch [::events/change-sort :silver])} ]
        [:td]
        [:td.bronze {:on-click #(re-frame/dispatch [::events/change-sort :bronze])} ]
        [:td]
        [:td {:on-click #(re-frame/dispatch [::events/change-sort :total])} "Total" ]]]
      [:td]
      (when @show-twirly [:img {:width 250 :height 230 :margin 20 :src (str "https://cdn.dribbble.com/users/255512/screenshots/2251246/gifloader.gif")}])
      [:tbody
       #_{:clj-kondo/ignore [:invalid-arity]}
       (map-indexed display-medals countries)]]]))
     