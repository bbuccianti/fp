// Compiled by ClojureScript 1.10.520 {:target :nodejs}
goog.provide('lambdaisland.glogi');
goog.require('cljs.core');
goog.require('goog.log');
goog.require('goog.debug.Logger');
goog.require('goog.debug.Logger.Level');
goog.require('goog.debug.Console');
goog.require('goog.array');
goog.require('clojure.string');
goog.require('goog.object');
goog.require('goog.debug.FancyWindow');
goog.require('goog.debug.DivConsole');
goog.require('goog.debug.LogRecord');
/**
 * Get a logger by name, and optionally set its level.
 */
lambdaisland.glogi.logger = (function lambdaisland$glogi$logger(var_args){
var G__32750 = arguments.length;
switch (G__32750) {
case 1:
return lambdaisland.glogi.logger.cljs$core$IFn$_invoke$arity$1((arguments[(0)]));

break;
case 2:
return lambdaisland.glogi.logger.cljs$core$IFn$_invoke$arity$2((arguments[(0)]),(arguments[(1)]));

break;
default:
throw (new Error(["Invalid arity: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(arguments.length)].join('')));

}
});

lambdaisland.glogi.logger.cljs$core$IFn$_invoke$arity$1 = (function (name){
return goog.log.getLogger(name);
});

lambdaisland.glogi.logger.cljs$core$IFn$_invoke$arity$2 = (function (name,level){
return goog.log.getLogger(name,level);
});

lambdaisland.glogi.logger.cljs$lang$maxFixedArity = 2;

lambdaisland.glogi.root_logger = lambdaisland.glogi.logger.call(null,"");
lambdaisland.glogi.levels = cljs.core.PersistentHashMap.fromArrays([new cljs.core.Keyword(null,"shout","shout",186018337),new cljs.core.Keyword(null,"finest","finest",-1359568890),new cljs.core.Keyword(null,"config","config",994861415),new cljs.core.Keyword(null,"warn","warn",-436710552),new cljs.core.Keyword(null,"trace","trace",-1082747415),new cljs.core.Keyword(null,"debug","debug",-1608172596),new cljs.core.Keyword(null,"all","all",892129742),new cljs.core.Keyword(null,"warning","warning",-1685650671),new cljs.core.Keyword(null,"severe","severe",-1364500238),new cljs.core.Keyword(null,"off","off",606440789),new cljs.core.Keyword(null,"info","info",-317069002),new cljs.core.Keyword(null,"fine","fine",-873037193),new cljs.core.Keyword(null,"error","error",-978969032),new cljs.core.Keyword(null,"finer","finer",974902846)],[goog.debug.Logger.Level.SHOUT,goog.debug.Logger.Level.FINEST,goog.debug.Logger.Level.CONFIG,goog.debug.Logger.Level.WARNING,goog.debug.Logger.Level.FINE,goog.debug.Logger.Level.CONFIG,goog.debug.Logger.Level.ALL,goog.debug.Logger.Level.WARNING,goog.debug.Logger.Level.SEVERE,goog.debug.Logger.Level.OFF,goog.debug.Logger.Level.INFO,goog.debug.Logger.Level.FINE,goog.debug.Logger.Level.SEVERE,goog.debug.Logger.Level.FINER]);
lambdaisland.glogi.level = (function lambdaisland$glogi$level(lvl){
return cljs.core.get.call(null,lambdaisland.glogi.levels,lvl);
});
/**
 * Get the numeric value of a log level (keyword).
 */
lambdaisland.glogi.level_value = (function lambdaisland$glogi$level_value(lvl){
return lambdaisland.glogi.level.call(null,lvl).value;
});
/**
 * Output a log message to the given logger, optionally with an exception to be
 *   logged.
 */
lambdaisland.glogi.log = (function lambdaisland$glogi$log(var_args){
var G__32753 = arguments.length;
switch (G__32753) {
case 3:
return lambdaisland.glogi.log.cljs$core$IFn$_invoke$arity$3((arguments[(0)]),(arguments[(1)]),(arguments[(2)]));

break;
case 4:
return lambdaisland.glogi.log.cljs$core$IFn$_invoke$arity$4((arguments[(0)]),(arguments[(1)]),(arguments[(2)]),(arguments[(3)]));

break;
default:
throw (new Error(["Invalid arity: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(arguments.length)].join('')));

}
});

lambdaisland.glogi.log.cljs$core$IFn$_invoke$arity$3 = (function (name,lvl,message){
return lambdaisland.glogi.log.call(null,name,lvl,message,null);
});

lambdaisland.glogi.log.cljs$core$IFn$_invoke$arity$4 = (function (name,lvl,message,exception){
return goog.log.log(lambdaisland.glogi.logger.call(null,name),lambdaisland.glogi.level.call(null,lvl),message,exception);
});

lambdaisland.glogi.log.cljs$lang$maxFixedArity = 4;

/**
 * Set the level (a keyword) of the given logger, identified by name.
 */
lambdaisland.glogi.set_level = (function lambdaisland$glogi$set_level(name,lvl){
if(cljs.core.contains_QMARK_.call(null,lambdaisland.glogi.levels,lvl)){
} else {
throw (new Error("Assert failed: (contains? levels lvl)"));
}

return lambdaisland.glogi.logger.call(null,name).setLevel(lambdaisland.glogi.level.call(null,lvl));
});
/**
 * Convenience function for setting several levels at one. Takes a map of logger name => level keyword.
 */
lambdaisland.glogi.set_levels = (function lambdaisland$glogi$set_levels(lvls){
return cljs.core.run_BANG_.call(null,lambdaisland.glogi.set_level,lvls);
});
/**
 * Log to the browser console. This uses goog.debug.Console directly,
 *   use [lambdaisland.glogi.console/install!] for a version that plays nicely with
 *   cljs-devtools.
 */
lambdaisland.glogi.enable_console_logging_BANG_ = (function lambdaisland$glogi$enable_console_logging_BANG_(){
var temp__5720__auto___32755 = goog.debug.Console.instance;
if(cljs.core.truth_(temp__5720__auto___32755)){
var instance_32756 = temp__5720__auto___32755;
instance_32756.setCapturing(true);

var instance_32757__$1 = (new goog.debug.Console());
goog.debug.Console.instance = instance_32757__$1;

instance_32757__$1.setCapturing();
} else {
}

return null;
});
/**
 * Log to the browser console if the browser location contains Debug=true.
 */
lambdaisland.glogi.console_autoinstall_BANG_ = (function lambdaisland$glogi$console_autoinstall_BANG_(){
goog.debug.Console.autoInstall();

return null;
});
/**
 * Pop up a browser window which will display log messages. Returns the FancyWindow instance.
 */
lambdaisland.glogi.popup_logger_window_BANG_ = (function lambdaisland$glogi$popup_logger_window_BANG_(){
var G__32758 = (new goog.debug.FancyWindow());
G__32758.setEnabled(true);

return G__32758;
});
/**
 * Log messages to an element on the page. Returns the DivConsole instance.
 */
lambdaisland.glogi.log_to_div_BANG_ = (function lambdaisland$glogi$log_to_div_BANG_(element){
var G__32759 = (new goog.debug.DivConsole(element));
G__32759.setCapturing(true);

return G__32759;
});
/**
 * Add a log handler to the given logger, or to the root logger if no logger is
 *   specified. The handler is a function which receives a map as its argument.
 */
lambdaisland.glogi.add_handler = (function lambdaisland$glogi$add_handler(var_args){
var G__32761 = arguments.length;
switch (G__32761) {
case 1:
return lambdaisland.glogi.add_handler.cljs$core$IFn$_invoke$arity$1((arguments[(0)]));

break;
case 2:
return lambdaisland.glogi.add_handler.cljs$core$IFn$_invoke$arity$2((arguments[(0)]),(arguments[(1)]));

break;
default:
throw (new Error(["Invalid arity: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(arguments.length)].join('')));

}
});

lambdaisland.glogi.add_handler.cljs$core$IFn$_invoke$arity$1 = (function (handler_fn){
return lambdaisland.glogi.add_handler.call(null,"",handler_fn);
});

lambdaisland.glogi.add_handler.cljs$core$IFn$_invoke$arity$2 = (function (name,handler_fn){
return lambdaisland.glogi.logger.call(null,name).addHandler((function (){var G__32762 = (function (record){
return handler_fn.call(null,new cljs.core.PersistentArrayMap(null, 6, [new cljs.core.Keyword(null,"sequenceNumber","sequenceNumber",-543983615),record.sequenceNumber_,new cljs.core.Keyword(null,"time","time",1385887882),record.time_,new cljs.core.Keyword(null,"level","level",1290497552),cljs.core.keyword.call(null,clojure.string.lower_case.call(null,record.level_.name)),new cljs.core.Keyword(null,"message","message",-406056002),record.msg_,new cljs.core.Keyword(null,"logger-name","logger-name",-1988126927),record.loggerName_,new cljs.core.Keyword(null,"exception","exception",-335277064),record.exception_], null));
});
goog.object.set(G__32762,"handler-fn",handler_fn);

return G__32762;
})());
});

lambdaisland.glogi.add_handler.cljs$lang$maxFixedArity = 2;

lambdaisland.glogi.remove_handler = (function lambdaisland$glogi$remove_handler(var_args){
var G__32765 = arguments.length;
switch (G__32765) {
case 1:
return lambdaisland.glogi.remove_handler.cljs$core$IFn$_invoke$arity$1((arguments[(0)]));

break;
case 2:
return lambdaisland.glogi.remove_handler.cljs$core$IFn$_invoke$arity$2((arguments[(0)]),(arguments[(1)]));

break;
default:
throw (new Error(["Invalid arity: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(arguments.length)].join('')));

}
});

lambdaisland.glogi.remove_handler.cljs$core$IFn$_invoke$arity$1 = (function (handler_fn){
return lambdaisland.glogi.remove_handler.call(null,"",handler_fn);
});

lambdaisland.glogi.remove_handler.cljs$core$IFn$_invoke$arity$2 = (function (name,handler_fn){
return lambdaisland.glogi.logger.call(null,name).removeHandler(handler_fn);
});

lambdaisland.glogi.remove_handler.cljs$lang$maxFixedArity = 2;

lambdaisland.glogi.add_handler_once = (function lambdaisland$glogi$add_handler_once(var_args){
var G__32769 = arguments.length;
switch (G__32769) {
case 1:
return lambdaisland.glogi.add_handler_once.cljs$core$IFn$_invoke$arity$1((arguments[(0)]));

break;
case 2:
return lambdaisland.glogi.add_handler_once.cljs$core$IFn$_invoke$arity$2((arguments[(0)]),(arguments[(1)]));

break;
default:
throw (new Error(["Invalid arity: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(arguments.length)].join('')));

}
});

lambdaisland.glogi.add_handler_once.cljs$core$IFn$_invoke$arity$1 = (function (handler_fn){
return lambdaisland.glogi.add_handler_once.call(null,"",handler_fn);
});

lambdaisland.glogi.add_handler_once.cljs$core$IFn$_invoke$arity$2 = (function (name,handler_fn){
if(cljs.core.truth_(cljs.core.some.call(null,cljs.core.comp.call(null,cljs.core.PersistentHashSet.createAsIfByAssoc([handler_fn]),(function (p1__32767_SHARP_){
return goog.object.get(p1__32767_SHARP_,"handler-fn");
})),lambdaisland.glogi.logger.call(null,name).handlers_))){
return null;
} else {
return lambdaisland.glogi.add_handler.call(null,name,handler_fn);
}
});

lambdaisland.glogi.add_handler_once.cljs$lang$maxFixedArity = 2;


//# sourceMappingURL=glogi.js.map
