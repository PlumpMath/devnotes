

(def testds {:e [{:c :blah :x 1 :y 2}
                     {:c :foo :x 2 :y 5}]
     :e1 [{:c :blah :x 1 :z 2}
              {:c :foo :x 5 :z 12}]})

=> (assoc-in t [:e 0] {:c :doo :x 1 :y 5})
{:e [{:c :doo, :x 1, :y 5} {:c :foo, :x 2, :y 5}], :e1 [{:c :blah, :x 1, :z 2} {:c :foo, :x 5, :z 12}]}
;; yay! 

=> (assoc-in t [:e 1] {:c :bing :x 1 :y 5})
{:e [{:c :blah, :x 1, :y 2} {:c :bing, :x 1, :y 5}], :e1 [{:c :blah, :x 1, :z 2} {:c :foo, :x 5, :z 12}]}
;; oops :-(

pcbe.core> (type (gensym))
clojure.lang.Symbol
pcbe.core> (type (:e))
IllegalArgumentException Wrong number of args passed to keyword: :e  clojure.lang.Keyword.throwArity (Keyword.java:97)
pcbe.core> (type :e)
clojure.lang.Keyword
pcbe.core> (type (keyword (gensym)))
clojure.lang.Keyword
pcbe.core>

;; Alternative option for ants data structure. 

(def t (atom {:e1 {:position {:x 1 :y 2 :a 0 }
                   :ai {:foo :barfn}}
              :e2 {:position {:x 1 :y 3 :a 0}
                   :ai {:foo :barfn}}
              }))

;; main url
https://spicevan.com 

;; Kits Server (front-end)
https://v3.spicevan.com

;; Websocket server endpoint North Van (5th street) -- where Andrea 
wss://v5.spicevan.com:443

gitolite - admin for git projects
pcbe - price compare back end
figreag - front end (re-frame)
database is the database

git clone git@v5.spicevan.com:pcbe.git
git clone git@v5.spicevan.com:pcbe-web.git
git clone git@v5.spicevan.com:gitolite-admin.git

Serve with Nginx: 

/etc/nginx/

qa user is where the app is deployed on v3 and v5 

Redmine password file password:
FACy9J5LM
