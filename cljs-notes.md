# ClojureScript Notes

## CLJS Todos

### Classpath

(from "whodidthis" on irc #clojurescript irc channel)

lein cp > "class_path.txt"; java -cp $(cat class_path.txt) clojure.main build.clj (saw this somewhere) 

### Desired Workflow (like Clojure)

What I’d like to be able to do with a Clojurescript REPL:

Figure out the “best” way to start a repl (what does “best” mean in this context? most reliable? most feature rich? simplest?)
Start a repl from the command-line and connect to a running application
Switch to a various namespaces and be able to query different defs in the app
E.g:

### Debugging ClojureScript in Intellij

Evolution of the post I wrote: 
https://github.com/bhauman/lein-figwheel/wiki/Running-figwheel-in-a-Cursive-Clojure-REPL

* Start the figwheel server using the above instructions
* Set a breakpoint in the browser on the line where you want to debug
* Step through the code using the browser debugging controls. 

## ClJS REPL Notes

To require a namespace: 

    shred.core=> (require '[cljs.repl :as r])
    nil
    shred.core=> (r/dir shred.core)
    add-exercise
    app-state
    exercise
    exercise-form
    jtab-component
    labeled-input
    layout
    on-js-reload
    session
    test-layout
    test-state
    tooltip-info
    nil
    shred.core=> 

#### Starting a repl using QuickStart approach

;; Start a repl from terminal.app
=> rlwrap java -cp cljs.jar:src clojure.main repl.clj

Compiling client js ...
Waiting for browser to connect ...

(in Chrome go to: localhost:9000) and in the repl you have start in the terminal you should then see:

Watch compilation log available at: out/watch.log
To quit, type: :cljs/quit

;; Congrats! You've got a repl connected to your browser.

Do how does the CLJS REPL work?

Clojurescript code typed into the repl is compiled to javascript in the JVM running in the terminal
The compiled javascript is sent to your browser for execution
The result is sent back to your terminal and displayed.

;; Let's explore and find which vars are available in the default repl namespace (cljs.user)
;; the function 'dir' can help here....

cljs.user=> (dir cljs.user)
nil

Hurumph. That's not much help... what's happening here?
Like in a normal clojure program, dir is showing us the functions that are defined in namespace cljs.user
and not "all the functions in all namespaces that can be called from the repl". 

If we define a new var in the cljs.user namespace then we can use dir to see that it's there:

cljs.user=> (dir cljs.user)
nil
cljs.user=> (def avariable :foo)
:foo
cljs.user=> (dir cljs.user)
avariable
nil
cljs.user=> avariable
:foo

Great! So how do I access the vars in my application?

;; require the namespace of your application to make it available in repl:
cljs.user=> (require '[hello-world.core :as hello])
nil

;; Now we can explore the vars in the loaded namespace hello-world.core
cljs.user=> (dir hello-world.core)
app-state
bar
conn
foo
init-state
nil

Cool!
Now let's interact with the application that's running in the browser:

cljs.user=> app-state
WARNING: Use of undeclared Var cljs.user/app-state at line 1 <cljs repl>
nil

bleck... why didn't that work? The hint is "cljs.user/app-state". 
The call fails because we're in the cljs.user namespace (the default namespace for the repl) and we're trying to call a var in hello-world.core namespace.
Let's try using the fully qualified name:

cljs.user=> (hello-world.core/app-state)
TypeError: hello_world.core.app_state.call is not a function
TypeError: hello_world.core.app_state.call is not a function
    at eval (eval at <anonymous> (http://localhost:9000/out/clojure/browser/repl.js:42:272), <anonymous>:1:116)
    at eval (eval at <anonymous> (http://localhost:9000/out/clojure/browser/repl.js:42:272), <anonymous>:9:3)
    at eval (eval at <anonymous> (http://localhost:9000/out/clojure/browser/repl.js:42:272), <anonymous>:14:4)
    at http://localhost:9000/out/clojure/browser/repl.js:42:267
    at clojure$browser$repl$evaluate_javascript (http://localhost:9000/out/clojure/browser/repl.js:45:4)
    at Object.callback (http://localhost:9000/out/clojure/browser/repl.js:242:169)
    at goog.messaging.AbstractChannel.deliver (http://localhost:9000/out/goog/messaging/abstractchannel.js:142:13)
    at goog.net.xpc.CrossPageChannel.xpcDeliver (http://localhost:9000/out/goog/net/xpc/crosspagechannel.js:733:12)
    at Function.goog.net.xpc.NativeMessagingTransport.messageReceived_ (http://localhost:9000/out/goog/net/xpc/nativemessagingtransport.js:321:13)
    at Object.goog.events.fireListener (http://localhost:9000/out/goog/events/events.js:741:21)

Crap. Still not working. What's wrong?
The hint is "...hello_world.core.app_state.call is not a function."
It's fails because app_state is an atom and not a function. To get the contents of a var at the repl just use the name without parens: 

cljs.user=> hello-world.core/app-state
#<Atom: {:text "Hello, this is: ", :numbers ["one" "two" "four"]}>

Yay!
Alternatively we could switch to the namespace with "in-ns" and call the function:

cljs.user=> (in-ns hello-world.core)
java.lang.UnsupportedOperationException: nth not supported on this type: Symbol
	at clojure.lang.RT.nthFrom(RT.java:857)
	at clojure.lang.RT.nth(RT.java:807)
	at cljs.repl$fn__4005$self__4011.invoke(repl.clj:600)
	at cljs.repl$repl_STAR_$read_eval_print__4070.invoke(repl.clj:795)
	at cljs.repl$repl_STAR_$fn__4076$fn__4083.invoke(repl.clj:834)
	at cljs.repl$repl_STAR_$fn__4076.invoke(repl.clj:833)
	at cljs.compiler$with_core_cljs.invoke(compiler.clj:951)
	at cljs.repl$repl_STAR_.invoke(repl.clj:799)
	at cljs.repl$repl.doInvoke(repl.clj:915)
	at clojure.lang.RestFn.invoke(RestFn.java:486)
	at user$eval30.invoke(repl.clj:10)
	at clojure.lang.Compiler.eval(Compiler.java:6703)
	at clojure.lang.Compiler.load(Compiler.java:7130)
	at clojure.lang.Compiler.loadFile(Compiler.java:7086)
	at clojure.main$load_script.invoke(main.clj:274)
	at clojure.main$script_opt.invoke(main.clj:336)
	at clojure.main$main.doInvoke(main.clj:420)
	at clojure.lang.RestFn.invoke(RestFn.java:408)
	at clojure.lang.Var.invoke(Var.java:379)
	at clojure.lang.AFn.applyToHelper(AFn.java:154)
	at clojure.lang.Var.applyTo(Var.java:700)
	at clojure.main.main(main.java:37)

Arrgh!! Balls. What's happening?
Turns out that 'in-ns' (unlike the function 'dir') takes a quoted namespace name like so:

cljs.user=> (in-ns 'hello-world.core)
nil
hello-world.core=> 

So how do we know if a function takes a quoted symbol or not? I don't know... I will pose the question to the gods of cljs and report back. 

Anyway, onwards! Let's switch back to cljs.user so we have access to the repl functions

hello-world.core=> (in-ns 'cljs.user)

Let's make a change to our applications app-state:

cljs.user=>(swap! hello-world.core/app-state assoc :player1 "bob")
{:text "Hello, this is: ", :numbers ["one" "two" "four"], :player1 "bob"}

And if we query app-state we see that it did indeed update:

cljs.user=> hello-world.core/app-state
#<Atom: {:text "Hello, this is: ", :numbers ["one" "two" "four"], :player1 "bob"}>

Fun! Now's a good time to see what's happening in the browser.
In Chrome, open devtools (menu item: View -> Developer -> Developer Tools) and select the console tab.
At the prompt start typing 'hello' (without the quotes)

The console should complete "hello_world" for you indicating that it's an object that is "known" to Chrome and one that you can explore. Notice, however, that the original dash in hello-world has been turned into an underscore - this is a feature of the clojurescript compiler and happens to all names that contain dashes. 

The javascript object equivalent of our clojurescript var is:

hello_world.core.app_state 

but it's an object of type "cljs.core.Atom" which we can see if we explore the object in Dev Tools:

hello_world.ore.app_state.__proto__

c…s.c…e.Atom {cljs$core$IPrintWithWriter$: true} ... (elided)

So let's explore what other namespaces we have to play with. This is a surprisingly tricky question and requires a little digging into the internals of clojurescript. While clojurescript does implement a function "all-ns" in cljs.analyzer.api it's for use by the clojurescript compiler and not available from the clojurescript repl. 

Since we now know how to explore what's happening in the browser let's use that approach (after all, it's the environment where our application code is running).

Start typing 'cljs' in the console:

You should see a list of javascript objects that are available in the global javascript namespace. In addition to the functions associated with the "cljs" object we also find:

core
repl
pprint
user

Let's explore the cljs.repl namespace from our clojurescript repl. In your terminal:

cljs.user=> (dir cljs.repl)
apropos
dir
doc
err-out
find-doc
print-doc
pst
source
nil

Here are host of useful functions that are automatically available from the cljs.user namespace. We've already seen dir but they are all useful, e.g. : 

cljs.user=> (doc reduce)
-------------------------
cljs.core/reduce
([f coll] [f val coll])
  f should be a function of 2 arguments. If val is not supplied,
  returns the result of applying f to the first 2 items in coll, then
  applying f to that result and the 3rd item, etc. If coll contains no
  items, f must accept no arguments as well, and reduce returns the
  result of calling f with no arguments.  If coll has only 1 item, it
  is returned and f is not called.  If val is supplied, returns the
  result of applying f to val and the first item in coll, then
  applying f to that result and the 2nd item, etc. If coll contains no
  items, returns val and f is not called.
nil

find-doc is great if you don't quite know where to look:

cljs.user=> (doc find-doc)
-------------------------
cljs.repl/find-doc
([re-string-or-pattern])
Macro
  Prints documentation for any var whose documentation or name
 contains a match for re-string-or-pattern
nil

cljs.user=> (find-doc "reduce")
-------------------------
-kv-reduce
([coll f init])
  Reduces an associative collection and returns the result. f should be
     a function that takes three arguments.
-------------------------
-reduce
([coll f] [coll f start])
  f should be a function of 2 arguments. If start is not supplied,
     returns the result of applying f to the first 2 items in coll, then
     applying f to that result and the 3rd item, etc.
-------------------------


... and a ton of other stuff ...

;; What's available in cljs.core? 
cljs.user=> (dir cljs.core)
*
*1
*2
*3
*clojurescript-version*
*e
*flush-on-newline*
*loaded-libs*
*main-cli-fn*
*print-dup*
*print-fn*
*print-length*
*print-level*
*print-meta*
*print-newline*
*print-readably*
*target*
*unchecked-if*
+
-
->
->>
(... elided ...)

;; whoa billy. That's a lot of stuff.. you've probably figured out that's where the bulk of the clojurescript functions live. 

Ok, so what happens if you want to add a new function to hello-world.core and make it available in the running browser environment? Let's try it:

add a simple function to your namespace, e.g.

(defn bing [n d]
  (/ n d))

Save the file. Because we have the ":watch" option specified in repl.clj the file should be compiled automatically. Now go back to the repl to check if it's in the namespace: 

cljs.user=> (dir hello-world.core)
app-state
bar
bing
conn
foo
init-state
nil

Great! now let's use it:

(hello-world.core/bing 10 5)
TypeError: Cannot read property 'call' of undefined
TypeError: Cannot read property 'call' of undefined
    at eval (eval at <anonymous> (http://localhost:9000/out/clojure/browser/repl.js:42:272), <anonymous>:1:110)
    at eval (eval at <anonymous> (http://localhost:9000/out/clojure/browser/repl.js:42:272), <anonymous>:9:3)
    at eval (eval at <anonymous> (http://localhost:9000/out/clojure/browser/repl.js:42:272), <anonymous>:14:4)
    at http://localhost:9000/out/clojure/browser/repl.js:42:267
    at clojure$browser$repl$evaluate_javascript (http://localhost:9000/out/clojure/browser/repl.js:45:4)
    at Object.callback (http://localhost:9000/out/clojure/browser/repl.js:242:169)
    at goog.messaging.AbstractChannel.deliver (http://localhost:9000/out/goog/messaging/abstractchannel.js:142:13)
    at goog.net.xpc.CrossPageChannel.xpcDeliver (http://localhost:9000/out/goog/net/xpc/crosspagechannel.js:733:12)
    at Function.goog.net.xpc.NativeMessagingTransport.messageReceived_ (http://localhost:9000/out/goog/net/xpc/nativemessagingtransport.js:321:13)
    at Object.goog.events.fireListener (http://localhost:9000/out/goog/events/events.js:741:21)

Bugger.. what happened? If we go back to the browser and inspect the hello_world.core object we see that the bing object doesn't appear: 

<image?>

However, if we refresh the browser and we inspect the var again we see that bing is there:

<image?>

And if we try to access it from the repl everything works as expected:

cljs.user=> (hello-world.core/bing 10 5)
2

But be aware that any in-memory vars that you updated will be blown-away when you refresh. Remember we updated the app-state with a :player1 key? Well, it's not there anymore:

cljs.user=> hello-world.core/app-state
#<Atom: {:text "Hello, this is: ", :numbers ["one" "two" "four"]}>

If you want to update the running in-browser application you'll need "figwheel" which we'll setup and explore in a future post. 

Notes:
*loaded-libs* isn't working as expected:

cljs.user=> (load-namespace 'hello-world.core)
nil
cljs.user=> (hello-world.core/foo 3 5)
WARNING: Use of undeclared Var hello-world.core/foo at line 1 <cljs repl>
15
cljs.user=> (in-ns 'hello-world.core)
nil
hello-world.core=> *loaded-libs*
nil


Checkout Modern CLJS on github. (not updated as of April 3 2015)


## CLJS REPLs

Getting a clojurescript repl going is a bit of a pain in the ass and the state of the art is changing very quickly so this might be quickly irrelevant.

Clojurescript repl runs in some kind of native Javascript environment (browser, phantomjs, node etc.)

### Using Leiningen

lein run -m cljs.repl.{node|nashorn|browser}

## nREPL

A Clojure network REPL that provides a server and client, along with some common APIs of use to IDEs and other tools that may need to evaluate Clojure code in remote environments.

## Piggieback

* nREPL middleware that enables the bootstrap of a Clojurescript REPL on top of a nREPL session.

## Weasel 

ClojureScript browser REPL using WebSockets.
Weasel uses WebSockets to communicate between a ClojureScript REPL, which is typically hosted on nREPL using piggieback, and an environment which can execute compiled ClojureScript, which can be a web browser or any JavaScript environment that supports the WebSocket APIs.

## Figwheel

Pushes Clojurescript code to the client

Features:
* Live code reloading
* Static file server
* Built-in ClojureScript REPL 
* Live CSS reloading
* Heads-up display
* Doesn't load code that is generating warnings
* ...many others


## Austin 

A significant refactoring of the ClojureScript-standard browser-repl environment that's as easy to "configure" and use as a Clojure REPL. Does not run over WebSockets.

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
