#!/usr/bin/env boot

#tailrecursion.boot.core/version "2.5.1"

(set-env!
 :project      'playminesweeperonline.com
 :version      "0.1.0-SNAPSHOT"
 :dependencies '[[tailrecursion/boot.task   "2.2.4"]
                 [tailrecursion/hoplon      "5.10.24"]
                 [tailrecursion/boot.notify "2.0.0-SNAPSHOT"]]
 :out-path     "resources/public"
 :src-paths    #{"src"})

;; Static resources (css, images, etc.):
(add-sync! (get-env :out-path) #{"assets"})

(require
 '[tailrecursion.hoplon.boot      :refer :all]
 '[tailrecursion.boot.task.ring   :refer [dev-server]]
 '[tailrecursion.boot.task.notify :refer [hear]])

(deftask dev
  "Build playminesweeperonline.com for development."
  []
  (comp (watch)
        (hear)
        (hoplon {:pretty-print true
                 :prerender    false
                 ;; :source-map   true
                 })
        (dev-server)))

(deftask prod
  "Build playminesweeperonline.com for production."
  []
  (hoplon {:optimizations :advanced}))
