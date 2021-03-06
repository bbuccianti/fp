(defproject fp "0.4.0"
  :license {:name "MIT"}

  :min-lein-version "2.9.1"

  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.597"]
                 [org.clojure/core.async  "0.4.500"]
                 [org.clojure/core.match "1.0.0"]
                 [reagent "0.9.1"]
                 [re-frame "0.12.0"]
                 [com.taoensso/tempura "1.2.1"]
                 [cljsjs/semantic-ui-react "0.88.1-0"]]

  :plugins [[lein-figwheel "0.5.19"]
            [lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]]

  :source-paths ["src"]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]

                :figwheel {:on-jsload "fp.core/on-js-reload"
                           :open-urls ["http://localhost:3449/index.html"]}

                :compiler {:main fp.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/fp.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true
                           :preloads [devtools.preload]}}
               {:id "min"
                :source-paths ["src"]
                :compiler {:output-to "resources/public/js/compiled/fp.js"
                           :main fp.core
                           :optimizations :simple
                           :pretty-print false}}]}

  :figwheel {:css-dirs ["resources/public/css"] ;; watch and update CSS
             :nrepl-port 7888}

  :profiles
  {:dev {:dependencies [[binaryage/devtools "0.9.10"]
                        [figwheel-sidecar "0.5.19"]
                        [cider/piggieback "0.4.2"]
                        [nrepl "0.7.0-alpha3"]]
         :repl-options
         {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}

         :source-paths ["src" "dev" "test"]

         :clean-targets ^{:protect false}
         ["resources/public/js/compiled" :target-path]}

   :kaocha {:dependencies [[lambdaisland/kaocha "0.0-581"]
                           [lambdaisland/kaocha-cljs "0.0-68"]]}}

  :aliases {"kaocha" ["with-profile" "+kaocha" "run"
                      "-m" "kaocha.runner" "unit-cljs"]})
