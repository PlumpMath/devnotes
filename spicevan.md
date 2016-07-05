# Spicevan

Notes for Spicevan price comparison engine.

##  Spicevan Main url
https://spicevan.com 

## Front End Server

Kits Server (front-end)
https://v3.spicevan.com

## Backend server 

Websocket server endpoint North Van (5th street -- where Andrea lives)

wss://v5.spicevan.com:443



pcbe - price compare back end
pcbe-web - front end (re-frame)
database is the database

Site is served with Nginx: 

/etc/nginx/

qa user is where the app is deployed on v3 and v5 

Redmine file password:
FACy9J5LM

### Old Repos:

git clone git@v5.spicevan.com:pcbe.git
git clone git@v5.spicevan.com:pcbe-web.git
git clone git@v5.spicevan.com:gitolite-admin.git

## Web Dev Tools Setup 


## Running The Project

### Backend

Log into v5:

`ssh v5`

become Fenton

`sudo su - fenton`

Check status of services:

`sudo systemctl status pcbe`
`sudo systemctl status contdel`

Restart services:

`sudo systemctl start contdel`

Available system commands:

`stop start restart status`

To find out which websocket port the backend is running on:

`fen-profiles.clj` in pcbe project root.
### Frontend 

In Terminal: 

``` clojure
boot cider dev
```

### In Emacs: 

Start CLJ Repl (cider-connect) : 

To Start CLJS Repl

`(start-repl)` at the clojure repl prompt.

then:

Compiles & loads namespace in repl : C-u C-c A-z

## Emacs

Checkout "hide-show" in Emacs.
Checkout "narrowing" and widening in Emacs.

### Cider Keybindings

Start repl: Cmd C Alt j
Jump to symbol : Alt . (alt period)
Compiles Loads and puts cursor in Repl : C-u C-c A-z
Cider interrupt : C-c C-c

### Native Mobile Development

Native Mobile

re-natal : ios clojurescript

## Notes

### Spacial Indexing for Datomic

https://www.youtube.com/watch?v=mmyNlNQ5wf8
