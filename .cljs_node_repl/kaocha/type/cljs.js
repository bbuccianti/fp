// Compiled by ClojureScript 1.10.520 {:target :nodejs}
goog.provide('kaocha.type.cljs');
goog.require('cljs.core');
goog.require('lambdaisland.glogi');
goog.require('lambdaisland.glogi.console');
goog.require('clojure.string');

/** @define {string} */
goog.define("kaocha.type.cljs.log_level","WARNING");

/** @define {string} */
goog.define("kaocha.type.cljs.root_log_level","WARNING");
lambdaisland.glogi.console.install_BANG_.call(null);
lambdaisland.glogi.set_level.call(null,"",cljs.core.keyword.call(null,clojure.string.lower_case.call(null,kaocha.type.cljs.root_log_level)));

//# sourceMappingURL=cljs.js.map
