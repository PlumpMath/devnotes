# Clojure Notes

## Todos

Figure out how to run utility code before every repl in CCW
like doc, cdoc, etc.   

## Getting Started with Clojure

### Journey into the Unknown 
 
Clojure is like Lisp with the nasty bits taken out.

Clojure help you reason better about software problems but taking the incidental complexity out of writing programs.

### Hints for the Journey

Try to embrace the Clojure way of doing things. This may be hard at first because you'll want to fall back on the same tools you have collected from other languages. 

Read Programming Clojure 2nd Edition. It has a get introduction to get you into the Clojure groove and then introduces you to the important parts of the language step by step.

Learn by doing with short programming tasks. www.4Clojure.com has 153 exercises ranging from elementary to expert that will take you on a guided journey of the language. Highly recommended.

;;; Leiningen

Get it, use it, love it.

;;; Eclipse and CounterClockwise

How to add an existing Leiningen project into a workspace? Gets me every time...

1 Setup a workspace in ~/dev/eclipse
2 clone or create the leiningen project in ~/dev/clojure/project-name
3 in an eclipse workspace whose default folder is DIFFERENT than ~/dev/clojure/ 

- Create a new project New -> Project... -> General -> Project
- Uncheck "use default location" 
- Browse -> Choose the project's working directory (~/dev/clojure/project-name)
- Name the project
- Click finish.

;;; Paredit

paredit-close-round-and-newline       : M-) 
paredit-raise-over-sexp               : M-r 
paredit-splice-sexp                   : M-s 
paredit-reindent-defun                : M-q 
paredit-kill                          : C-k
paredit-backward-up                   : C-M-u 
paredit-forward-down                  : C-M-d 
paredit-backward-down                 : C-M-n 
paredit-forward-up                    : C-M-u 
paredit-splice-sexp-killing-backward  : M-up
paredit-forward-slurp                 : C-right
paredit-backward-slup                 : C-left

;;; Moving Around

Go to the end of the defun            : C-M-e
Go to beginning of defun              : C-M-a

;;; Wrapping stuff in Paredit

There are a couple different ways to do it, I prefer using expand region:

foo -> <expand region> -> |foo| -> <type double-quote> -> "foo"
bar -> <expand region> -> |bar| -> <type open-paren> -> (foo)

;;; Unwrapping stuff in Paredit

I find I often get into a situation where I need to "unwrap" items in clojure:

(somefunc) -> somefunc
(1 2 (3)) -> (1 2 3)

How to you do that? : paredit-raise-over-sexp (M-r)

Also, you can also kill forward with C-k and then delete the empty container

(take (|foo)) -> (take foo)

To remove double quotes use paredit-splice (M-s):

"|some text" -> some text




### The Clojure REPL

Using the REPL effectively is important

To get repl stuff working in 1.5.1 nrepl: 
(apply require clojure.main/repl-requires)

## Clojure Documentation and References 
(a.k.a How to Get Help)

ClojureDocs.org is indispensible

The clojure cheatsheet is great:
http://jafingerhut.github.io/cheatsheet-clj-1.3/cheatsheet-tiptip-cdocs-summary.html

Add the cd-client library as a dependency in your ~/.lein/profiles.clj file

This will give you examples in your repl (or you could just switch to your browser)

Using ClojureDocs in offline mode:

(use 'cd-client.core)
(set-local-mode! "/Users/zand/dev/docs/clojuredocs-snapshot-latest.txt")
(cdoc filter)


## Libraries 

Use the ns macro to make libraries available for use in a program

use the use function for quick n' dirty use in the repl:
e.g. (use 'cd-client.core)

### Namespaces

Namespaces can be confusing (write other stuff about repls here)

Get a list of all namespaces:
(all-ns)

Get a sorted list of all public vars in a namespace
(dir ns-name)

e.g. (dir user)

### Functions 


### Anonymous Functions

Anonymous functions are everywhere. 

The shorthand way to write them is #(body) 

The arguments are :

* % = first arg
* %1 = first arg
* %2 = second arg 

From Clojure Docs

Anonymous function literal (#())

 #(...) => (fn [args] (...))

where args are determined by the presence of argument literals taking the 
form %, %n or  %&. % is a synonym for %1, %n designates the nth arg (1-based), 
and %& designates a rest arg. This is not a replacement for fn - idiomatic 
used would be for very short one-off mapping/filter fns and the like. 
#() forms cannot be nested.

## Loops

;adds x to a vector and calls itself again
(loop [result [] x 5] 
  (if (zero? x)
    result
    (recur (conj result x) (dec x))))
;-> [5 4 3 2 1]

## Threading Macros

"->" a.k.a Thread First 

(-> x)
(-> x form)
(-> x form & more)

Threads the expr through the forms. Inserts x as the
second item in the first form, making a list of it if it is not a
list already. If there are more forms, inserts the first form as the
second item in second form, etc.

(-> (Math/sqrt 25) int list)

Can literally be read:

Take the result of (Math/sqrt 25)
Feed it into the function int
Feed that result into the function list

Graphically, this can be viewed as:

(Math/sqrt 25) --5.0--> (int 5.0) --5--> (list 5) 
=> (5)

Which expands into the following s-expression:

(list (int (Math/sqrt 25)))

(from http://blog.fogus.me/2009/09/04/understanding-the-clojure-macro/)

"->>" a.k.a Thread Last

(->> x form)
(->> x form & more)

Threads the expr through the forms. Inserts x as the
last item in the first form, making a list of it if it is not a
list already. If there are more forms, inserts the first form as the
last item in second form, etc.

;; An example of using the "thread-last" macro to get
;; the sum of the first 10 even squares.
user=> (->> (range)
            (map #(* % %))
            (filter even?)
            (take 10)
            (reduce +))
1140

# Useful Clojure Library Functions

## Sequences

### Seq

(seq coll)
Returns a seq on the collection. If the collection is
empty, returns nil. (seq nil) returns nil. seq also works on
Strings, native Java arrays (of reference types) and any objects
that implement Iterable.

user=> (seq '())
nil

user=> (seq '(1))
(1)

user=> (seq "")
nil

user=> (seq "abc")
(\a \b \c)


### Drop

(drop n coll)

Returns a lazy sequence of all but the first n items in coll.

user=> (drop 2 [1 2 3 4])
(3 4)

user=> (drop -1 [1 2 3 4])
(1 2 3 4)

### Vec

(vec coll)
Creates a new vector containing the contents of coll.

user=> (vec '(1 2 3))
[1 2 3]

user=> (vec [1 2 3])
[1 2 3]

user=> (vec '())
[]

user=> (vec nil)
[]

=> ((vec '(1 4 5 6)) 0)
1
=> ((vec '(1 4 5 6)) 3)
6

## Higher Order Functions

### Map 


### Reduce 

clojure.core/reduce
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

from: http://java.ociweb.com/mark/clojure/article.html

The reduce function takes a function of two arguments, an optional value
and a collection. It begins by calling the function with either the value
and the first item in the collection or the first two items in the collection
if the value is omitted. It then calls the function repeatedly with the
previous function result and the next item in the collection until every
item in the collection has been processed. This function is the same as
inject in Ruby and foldl in Haskell.  

Let's say we have the following nested map:

(def person {
  :name "Mark Volkmann"
  :address {
    :street "644 Glen Summit"
    :city "St. Charles"
    :state "Missouri"
    :zip 63304}
  :employer {
    :name "Object Computing, Inc."
    :address {
      :street "12140 Woodcrest Executive Drive, Suite 250"
      :city "Creve Coeur"
      :state "Missouri"
      :zip 63141}}})

so, for the map above the reduce function:

(reduce get person [:employer :address :city])

Does this:

(get person :employer)
(get (:address (person :employer)))
(get (:city (:address (person :employer))))

We could also do this by:

(get-in person [:employer :address :city])
(-> person :employer :address :city)

### Apply

Solution to 4Clojure #43 Reverse Interleave:
(= (__ [1 2 3 4 5 6] 2) '((1 3 5) (2 4 6)))

#(apply map list (partition %2 %1))

How this works:

(partition %2 %1) =  ((1 2) (3 4) (5 6))

apply is using the ((1 2) (3 4) (5 6)) as a variable length
list of additional arguments. Then iteration of the map
is applying the list function to all three of these additional
lists.

(apply map list '((1 2) (3 4) (5 6)))
=> (map list '(1 2) '(3 4) '(5 6))
=> (list 1 3 5) and (list 2 4 6)

You can think of apply as "putting the map list inside the outer
()s"

## List Comprehensions using "For"

(for [binding-form coll-expr filter-expr? ...] expr)
for takes a vector of binding-form/coll-exprs, plus an optional filter-expr,
and then yields a sequence of exprs.

; take each item, multiply by three and then keep even items:

(for [x [0 1 2 3 4 5]            ; binding-form
               :let [y (* x 3)]  ; collection expression
               :when (even? y)]  ; filter expression 
           y)
(0 6 12)

; take each item, multiply by three and then keep even items:

(for [x [0 1 2 3 4 5]            ; x is each element in the set 
               :let [y (* x 3)]  ; y is x * 3
               :when (even? y)]  ; keep only those y's that are even 
           y)                    ; return the y's 
(0 6 12)


## Debugging Clojure

First import the clojure.tools.trace library:

;(ns alpen.core
;   (:require [clojure.repl :as r] 
;             [clojure.tools.trace :as t] :reload-all))

Then you can use:

(t/trace :tagname (*2 3))

(t/deftrace dropnth2
  "drop the nth item from a sequence"
  [coll n]
  (loop [result [] c coll]
    (if (empty? c) 
      (flatten (t/trace :result result))
      (recur
        (conj result (take (dec n) c))
        (drop n c)))))
