# Clojure Notes

"First, solve the problem. Then, write the code. -John Johnson"

## Todos

Figure out how to run utility code before every nrepl
like doc, cdoc, etc.

## Getting Started with Clojure

### Journey into the Unknown

Clojure is like Lisp with the nasty bits taken out.

Clojure help you reason better about software problems but taking the incidental complexity out of writing programs.

### Hints for the Journey

Try to embrace the Clojure way of doing things. This may be hard at first because you'll want to fall back on the same tools you have collected from other languages.

Read Programming Clojure 2nd Edition. It has a get introduction to get you into the Clojure groove and then introduces you to the important parts of the language step by step.

Learn by doing with short programming tasks. www.4Clojure.com has 153 exercises ranging from elementary to expert that will take you on a guided journey of the language. Highly recommended.

### How to Think in Clojure

Model the world in data and create functions that operate on that data. Think of it like a wedding: The program is the venue, tables and chairs and the data is the people. The guests are what make (or break) the party.

http://res.infoq.com/downloads/pdfdownloads/presentations/LambdaJam2013-DeanWampler-CopiousDatatheKillerAppforFunctionalProgramming.pdf?Expires=1375806522&Signature=eOEZNGV~~8mAStuVeu9Ga2PaEYyjKdHKi3swyXwyiX5GoHPufJVLcEs5Wwz4X84eftJswuuaAQEYCuEqiClYjtrTCcEUlaI4eC7Dr4BZzJfWF6-MJUqodzrpaXv74FLUKuXfp92di~oNr7AR86cE9SqfjTxQDwQXW9uCjwIw3hk_&Key-Pair-Id=APKAIMZVI7QH4C5YKH6Q

#### Leiningen

Get it, use it, love it.

#### Setting up Emacs for Clojure

Download and install emacs (I use EmacsOSX precompiled binary and Emacs prelude)
Setup Emacs for effective clojure use
 * Install clojure-mode, nrepl, paredit-mode, autocomplete, and ac-nrepl
 * Configure the clojure modes according to your taste (my emacs.d file is avail)
Install Leiningen using homebrew

#### Eclipse and CounterClockwise

How to add an existing Leiningen project into a workspace? Gets me every time...

1 Setup a workspace in ~/dev/eclipse
2 clone or create the leiningen project in ~/dev/clojure/project-name
3 in an eclipse workspace whose default folder is DIFFERENT than ~/dev/clojure/

- Create a new project New -> Project... -> General -> Project
- Uncheck "use default location"
- Browse -> Choose the project's working directory (~/dev/clojure/project-name)
- Name the project
- Click finish.

#### Paredit

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

#### Moving Around

Go to the end of the defun            : C-M-e
Go to beginning of defun              : C-M-a

#### Wrapping stuff in Paredit

There are a couple different ways to do it, I prefer using expand region:

foo -> <expand region> -> |foo| -> <type double-quote> -> "foo"
bar -> <expand region> -> |bar| -> <type open-paren> -> (foo)

#### Unwrapping stuff in Paredit

I find I often get into a situation where I need to "unwrap" items in clojure:

(somefunc) -> somefunc
(1 2 (3)) -> (1 2 3)

How to you do that? : paredit-raise-over-sexp (M-r)

Also, you can also kill forward with C-k and then delete the empty container

(take (|foo)) -> (take foo)

To remove double quotes use paredit-splice (M-s):

"|some text" -> some text

### The Clojure REPL

Now that our environment is setup properly we can now explore basic repl use. Once we have a handle on on that we'll go further to explore how we can use the interactive nature of the repl to help us solve problems more effectively.

Once you're got emacs setup correctly and you have created a lein project you can use nrepl to interact with your program thus:

* (prelude) Projectile switch to project: ( C-c p s )
or
* Visit your projects core.clj file ( C-x c-f )
* Jack into the nrepl with nrepl-jack-in: ( M-x nrepl-jack-in <f9>)
* Load the clojure file into nrepl ( C-c C-l )
  * this compiles the file and loads the namespace
* Switch to the namespace in the repl ( C-c M-n )

Why do you need to do all the above? Let's look at an example where I don't jack-into the "correct" repl:

(TODO: add an example of jacking into an "incorrect" repl)

By using projectile to set the project directory we tell nrepl where the project's "project.clj" file is. (TODO: Is this true? How does nrepl know which project.clj file to use?)

### Exploring namespaces in the Repl

One of the most confusing things about clojure when I started was getting a handle on namespaces. While they are very powerful it's easy to get lost and there is a lot of outdated information on the 'net.

When you do nrepl-jack-in nrepl puts you in the "user" namespace.

To get repl tools working in 1.5.1 nrepl:
(apply require clojure.main/repl-requires)

List all namespaces:

(all-ns) -> returns a seq of all loaded namespaces

Show in-place documentation using auto-complete C-c C-d

Regex history search?

## Clojure Documentation and References

(a.k.a How to Get Help)

ClojureDocs.org is indispensable

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

## Functions

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
    (recur (conj result x) (dec x)))
    )
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

# Essential Clojure Library Functions

## Sequences

Most of Clojure's core data structures are built around sequences. It's a wildly useful abstraction and understanding sequences is one of the first things you should get comfortable with.

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

### Changing Sequences

#### Conj

conj returns a new sequence of the same type with arguments added.

Note: the syntax of conj is [coll x] so,

```
(conj #{} #{ #{1 3} #{1 2}})
=> #{#{#{1 3} #{1 2}}}
```

but...

```
(conj #{#{1 3} #{1 2}} #{})
=> #{#{} #{1 3} #{1 2}}
```

### Drop

(drop n coll)

Returns a lazy sequence of all but the first n items in coll.

user=> (drop 2 [1 2 3 4])
(3 4)

user=> (drop -1 [1 2 3 4])
(1 2 3 4)


### Vectors

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


### Maps

(def a [:a :b :c])
(def b [1 2 3])

(interleave a b)
=> (:a 1 :b 2 :c 3)

;; Creates a vector containing two vectors
(vector a b)
=> [[:a :b :c] [1 2 3]]

;; Takes one item from each vector a and b and applies the fn vector to the items.
(map vector a b)
=> ([:a 1] [:b 2] [:c 3])

(into {} (map vector a b))
=> {:a 1, :b 2, :c 3}

### Sets

Sets are functions which means given:

(def a #{0 1 2 3})
(def b #{2 3 4 5})

(a 1) => 1
(a 2) => 2
(a 5) => nil

i.e. The set a will return the arg if the arg is contained in the set

This is very useful with higher order fns:
In the example below a is acting as the predicate function to filter.
For each item in "b" return the item if it's in "a" -> the intersection of the two sets.

(filter a b)
=> (3 2)


## Strings

Strings in Clojure are an area where I find myself having to use the underlying Java functions.

Simple case:

(.toUpperCase "hello") -> "HELLO"

Concatinating String:

(str "foo" "bar")

Example: How to transform "1,2,3,4,5" into a vector [1 2 3 4 5]?

;; Test if a char is a digit
digit? (fn [c] (re-find #"\d" (str c)))

Takes a string and returns a string containing all caps.

```
(fn [x]
  (apply str (filter #(re-find #"[A-Z]" (str %)) (seq x))))
```


## Regular Expressions 


```
#(apply str (re-seq #"[A-Z]" %))
```

Extract the 
```
(apply str (re-seq #"[A-Z]+" "bA1B3Ce "))
```


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

See also:
http://www.learningclojure.com/2010/08/reduce-not-scary.html

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

```
(for [x [0 1 2 3 4 5]            ; binding-form
               :let [y (* x 3)]  ; collection expression
               :when (even? y)]  ; filter expression
           y)
(0 6 12)
```

Take each item, multiply by three and then keep even items:

```
(for [x [0 1 2 3 4 5]            ; x is each element in the set
               :let [y (* x 3)]  ; y is x * 3
               :when (even? y)]  ; keep only those y's that are even
           y)                    ; return the y's
(0 6 12)
```

;; Mapcat can be useful here:

```
(mapcat (fn [[k v]]
                 (for [[k2 v2] v]
                   (concat [k k2] v2)))
         '{:a {:x (1 2) :y (3 4)}
           :b {:x (1 2) :z (5 6)}})

((:a :x 1 2) (:a :y 3 4) (:b :x 1 2) (:b :z 5 6))
```

## Debugging Clojure

First import the clojure.tools.trace library:

```
(ns alpen.core
   (:require [clojure.repl :as r]
             [clojure.tools.trace :as t] :reload-all))
```

or
```
(ns offline-4clojure.p96
   (:use clojure.test)
   (:use clojure.tools.trace))
```

Then you can use:

`(t/trace :tagname (*2 3))`

```
(t/deftrace dropnth2
  "drop the nth item from a sequence"
  [coll n]
  (loop [result [] c coll]
    (if (empty? c)
      (flatten (t/trace :result result))
      (recur
        (conj result (take (dec n) c))
        (drop n c)))))
```

or if the function name is "__", as in: (defn __ [x] (identity x))

`(trace-vars __)`

will give you:

```
(= (__ '(:a (:b nil nil) (:b nil nil))) true)
TRACE t2758: (offline-4clojure.p96/__ (:a (:b nil nil) (:b nil nil)))
TRACE t2758: => true
=> true
(= (__ '(:a (:b nil nil) nil)) false)
TRACE t2761: (offline-4clojure.p96/__ (:a (:b nil nil) nil))
TRACE t2761: => true
=> false
(= (__ '(:a (:b nil nil) (:c nil nil))) false)
TRACE t2764: (offline-4clojure.p96/__ (:a (:b nil nil) (:c nil nil)))
TRACE t2764: => true
=> false
```

What does the output look like?

## Clojure Idioms

Remove nil items from a set you should use:

`(remove nil? my-seq)

but you might also see:

```
(filter identity my-seq)
```
or
```
(keep identity my-seq )
```

# Questions for Eric at Lispcast

## Debugging:

How to debug / trace a function that's not working properly?
clojure.tools.trace works but only for recursive functions (doesn't work for loop / recur)
__ (println #(:foo foo :bar bar)) trick doesn't work in (loop / recur bindings)
Cursive doesn't seem to work for Offline-4Clojure


# Areas to Work On:

## Recursive algorithms.

Powerset kicked my ass. Both the iterative algorithms and the recursive algorithms






