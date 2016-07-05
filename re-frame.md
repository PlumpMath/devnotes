# Re-frame Notes

## ToDos 

### Datatable

* Try out data-tables (done)
* Try to get data into data tables from websocket

### Homegrown solution

* Figure out how to make gridlines
* Figure out how to line up the cells
* Figure out how to get the grid to update based on a search 

## Setup 

(set! re-com.box/debug true)

### Recipes 

## Creating Multiple Components from Data

Creating multiple components is easy with for (list comprehensions):

## Data Tables

http://datatables.net/manual/data/

Should use client side processing (less than 10,000 items)

Main data source must always be an array

Each item in the array will define a ROW to be displayed.

Data tables can use 3 JS data types as the data source:
- Arrays
- Objects
- Instances i.e. `new MyClass()`

Creating JS objects from CLJS : 

`(def js-object (clj->js {:a 1 :b [1 2 3] :c #{"d" true :e nil}}))`

Will produce: 

``` javascript
{
  "a": 1,
  "b": [1, 2, 3],
  "c": [null, "d", "e", true]
}
```

Recursively transforms ClojureScript values to JavaScript.
* sets/vectors/lists become Arrays, 
* Keywords and Symbol become Strings,
* Maps become Objects

You can also create JavaScript objects with the #js reader literal:

`(def js-object #js {:a 1 :b 2})`

which generates the code:

`namespace.core.js_object = {"b": (2), "a": (1)};`

When working with #js you need to be cautious, because this literal also won’t transform inner structures (it’s shallow):

`(def js-object #js {:a 1 :b [1 2 3] :c {"d" true :e nil}})`

will create such object:

``` javascript
{
  "c": cljs.core.PersistentArrayMap, 
  "b": cljs.core.PersistentVector, 
  "a": 1
}

```

to solve this you need to add #js before every ClojureScript structure:

    (def js-object #js {:a 1 :b #js [1 2 3] :c #js ["d" true :e nil]})

JavaScript object:

    {
      "c": {
        "e": null,
        "d": true
      },
      "b": [1, 2, 3 ],
      "a": 1
    }

There are situations when we need to convert JavaScript object or array into ClojureScript data structure. 

We can do this by using js->clj function that:
"Recursively transforms JavaScript arrays into ClojureScript vectors, and JavaScript objects into ClojureScript maps. 

With option ‘:keywordize-keys true’ will convert object fields from
strings to keywords.

    (def my-array (js->clj (.-globalArray js/window)))
    (def first-item (get my-array 0)) ;; 1
    
    (def my-obj (js->clj (.-globalObject js/window)))
    (def a (get my-obj "a")) ;; 1

as the function doc states we can use :keywordize-keys true to convert string keys of created map to keywords:

    (def my-obj-2 (js->clj (.-globalObject js/window) :keywordize-keys true))
    (def a-2 (:a my-obj-2)) ;; 1

## Slack Channel Discussion:

danthedev [7:09 PM] 
Ok. the "Scales Up" section of the bootstrap article answers my question perfectly

[7:10] 
Dispatch the request event from the init function, then  fetch the data inside an event handler

[7:15] 
This is my first venture into abstractions above Reagent, so I guess I'm still guilty of thinking in terms of React components managing their own lifecycles and dependencies

[7:16] 
With re-frame, the components know what data they need (in the form of a subscription), but they don't care about how it ends up there. That's someone else's responsibility.

mikethompson [7:16 PM] 
Yes, that's right.

[7:17] 
Components simply render the current state of `app-db`
For the user to see.(edited)
it is event handlers which know what to do when an event happens.
How should `app-db` be mutated?  What further data is required (from a server)?
