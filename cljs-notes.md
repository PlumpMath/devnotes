# ClojureScript Notes

Getting a clojurescript repl going is a bit of a pain in the ass.

There are a couple of options for connecting to a brower-repl, all use Chas Emerick's "austin"

* a "headless" setup that uses phantomjs and which provides an execution environment but no access to a browser and DOM.
  - benefits are that it's simple to setup doesn't require going back and forth between command-line, repl and browser.
  - useful if you want a quick repl and you don't need a dom.

* a browser-connected repl that makes your browser the "execution environment" for any code that you're running.
  - useful but not necessarily a whole lot more useful than the headless environment.

* connect to your front end code in a browser and interact with it from the repl.
  - more complicated but this is probably what you want if you're writing a brower-based cljs application.

## Austin Browser Connected Repl Setup

This is the most complicated but probably the most useful setup - hopefully there'll be an automated build script to do this soon.

* In your project's project.clj file add the following to the profiles section:

```
 :profiles {:dev {:repl-options {:init-ns cemerick.austin.bcrepl-sample}
                   :plugins [[com.cemerick/austin "0.1.1"]
                             [lein-cljsbuild "0.3.2"]]
                   :cljsbuild {:builds [{:source-paths ["src/cljs"]
                                         :compiler {:output-to "target/classes/public/app.js"
                                                    :optimizations :simple
                                                    :pretty-print true}}]}}})
```


* In your clojure (server side) code:

require the austin browser-connected-repl-js function like so:

```
(ns cemerick.austin.bcrepl-sample
  (:require [cemerick.austin.repls :refer (browser-connected-repl-js)]
            [net.cgrand.enlive-html :as enlive]
            [compojure.route :refer (resources)]
            [compojure.core :refer (GET defroutes)]
            ring.adapter.jetty
            [clojure.java.io :as io]))
```

* In your template or index.html include the browser connected repl script like so:

```
(enlive/deftemplate page
  (io/resource "index.html")
  []
  [:body] (enlive/append
            (enlive/html [:script (browser-connected-repl-js)])))
```

* In your client side clojurescript code require the austin browswer-connected-repl like so:

```
(ns cemerick.austin.bcrepl-sample
  (:require [clojure.browser.repl]))
```

* Compile your clojurescript code:

```
lein cljsbuild once
```

* Start the clojure repl:

```
lein repl
```

* Start your web server:

```
defn run
  []
  (defonce ^:private server
    (ring.adapter.jetty/run-jetty #'site {:port 8080 :join? false}))
  server)

```
then:

```
(run)
```

* Create a new Austin ClojureScript REPL environment, like so:

```
(def repl-env (reset! cemerick.austin.repls/browser-repl-env
                      (cemerick.austin/repl-env)))
```

* Turn your Clojure REPL into a ClojureScript REPL tied to that REPL environment with:

```
(cemerick.austin.repls/cljs-repl repl-env)
```

* Now that the ClojureScript REPL is ready, you need to load http://localhost:8080, or reload it if you brought it up before the REPL environment was created and reset! into the browser-repl-env atom.


* Once you do that, evaluate some ClojureScript to make sure your shiny new REPL is working, e.g.

```
(js/alert "Salut!")
```

## cljs-start

cljs-start is a leiningen plugin built on top of austin and which starts you project with "batteries included" i.e.:

*


## Austin PhantomJS Repl

Consider using austin to start up a cljs repl with the following:

https://github.com/cemerick/austin

add this to your project.clj:

:profiles {:dev {:plugins [[com.cemerick/austin "0.1.3"]]}}

start the clojure repl with 'lein repl'

then to start a cljs PhantonJS headless repl evaluate:

(cemerick.austin.repls/exec)

to quit:

:cljs/quit

## Austin Chrome REPL

start the clojure repl with 'lein repl'

then do:

(cemerick.austin.repls/exec
         :exec-cmds ["open" "-ga" "/Applications/Google Chrome.app"])


## Koans

http://clojurescriptkoans.com/#lazy-sequences/5

## Setting up a ClojureScript Project

'lein new mies hello-world

## Compile the ClojureScript Code

'lein cljsbuild auto hello-world

## LightTable

Start a browser tab

In the location browse to the project's index.html e.g.

file:///Users/zand/dev/clojurescript/hello-world/index.html

##


# Namespaces

Because ClojureScript namespaces are implemented completely differently
than Clojure, ClojureScript does not support the use or require forms
directly.

Instead, you must use the ns macro. To use clojure.zip in the cljs.user namespace,
then, just do the following:

(ns cljs.user (:use [clojure.zip :only [insert-child]]))

Note that the forms supported in the ClojureScript version of ns are a
subset of those supported in Clojure; specifically, :use clauses must
specify an :only form, and the :require clause must specify an :as form.
