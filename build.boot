(set-env!
 :dependencies '[[adzerk/boot-cljs          "1.7.48-3"]
                 [adzerk/boot-reload        "0.3.1"]
                 [org.clojure/clojurescript "1.7.48"]
                 [org.clojure/clojure       "1.7.0"]
                 [hoplon/boot-hoplon        "0.1.5"]
                 [hoplon                    "6.0.0-alpha9"]
                 [pandeiro/boot-http        "0.6.3"]]
 :resource-paths #{"assets"}
 :source-paths #{"src"})

(require
 '[adzerk.boot-cljs   :refer [cljs]]
 '[adzerk.boot-reload :refer [reload]]
 '[pandeiro.boot-http :refer [serve]]
 '[hoplon.boot-hoplon :refer [hoplon prerender]])

(deftask dev
  "Build for local development."
  []
  (comp
   (watch)
   (speak :theme "pillsbury")
   (hoplon)
   (reload)
   (cljs)
   (serve)))

(deftask prod
  "Build for production deployment."
  []
  (comp
   (hoplon)
   (cljs :optimizations :advanced)
   (prerender)))
