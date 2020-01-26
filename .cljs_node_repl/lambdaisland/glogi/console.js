// Compiled by ClojureScript 1.10.520 {:target :nodejs}
goog.provide('lambdaisland.glogi.console');
goog.require('cljs.core');
goog.require('lambdaisland.glogi');
goog.require('goog.object');
goog.require('goog.debug.LogBuffer');
goog.require('goog.debug.Console');
lambdaisland.glogi.console.log_method = (function lambdaisland$glogi$console$log_method(p__32775){
var map__32776 = p__32775;
var map__32776__$1 = (((((!((map__32776 == null))))?(((((map__32776.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__32776.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__32776):map__32776);
var level = map__32776__$1;
var value = cljs.core.get.call(null,map__32776__$1,new cljs.core.Keyword(null,"value","value",305978217));
var pred__32778 = ((function (map__32776,map__32776__$1,level,value){
return (function (p1__32774_SHARP_,p2__32773_SHARP_){
return (p2__32773_SHARP_ >= p1__32774_SHARP_);
});})(map__32776,map__32776__$1,level,value))
;
var expr__32779 = value;
if(pred__32778.call(null,lambdaisland.glogi.level_value.call(null,new cljs.core.Keyword(null,"severe","severe",-1364500238)),expr__32779)){
return "error";
} else {
if(pred__32778.call(null,lambdaisland.glogi.level_value.call(null,new cljs.core.Keyword(null,"warning","warning",-1685650671)),expr__32779)){
return "warn";
} else {
if(pred__32778.call(null,lambdaisland.glogi.level_value.call(null,new cljs.core.Keyword(null,"info","info",-317069002)),expr__32779)){
return "info";
} else {
if(pred__32778.call(null,lambdaisland.glogi.level_value.call(null,new cljs.core.Keyword(null,"config","config",994861415)),expr__32779)){
return "log";
} else {
return "log";
}
}
}
}
});
lambdaisland.glogi.console.devtools_installed_QMARK_ = (function lambdaisland$glogi$console$devtools_installed_QMARK_(){
var and__4120__auto__ = (typeof devtools !== 'undefined') && (typeof devtools.core !== 'undefined') && (typeof devtools.core.installed_QMARK_ !== 'undefined');
if(and__4120__auto__){
return devtools.core.installed_QMARK_();
} else {
return and__4120__auto__;
}
});
lambdaisland.glogi.console.format = (function lambdaisland$glogi$console$format(p__32781){
var map__32782 = p__32781;
var map__32782__$1 = (((((!((map__32782 == null))))?(((((map__32782.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__32782.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__32782):map__32782);
var logger_name = cljs.core.get.call(null,map__32782__$1,new cljs.core.Keyword(null,"logger-name","logger-name",-1988126927));
var message = cljs.core.get.call(null,map__32782__$1,new cljs.core.Keyword(null,"message","message",-406056002));
var exception = cljs.core.get.call(null,map__32782__$1,new cljs.core.Keyword(null,"exception","exception",-335277064));
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [["[",cljs.core.str.cljs$core$IFn$_invoke$arity$1(logger_name),"]"].join(''),(cljs.core.truth_(lambdaisland.glogi.console.devtools_installed_QMARK_.call(null))?message:cljs.core.pr_str.call(null,message))], null);
});
if((typeof lambdaisland !== 'undefined') && (typeof lambdaisland.glogi !== 'undefined') && (typeof lambdaisland.glogi.console !== 'undefined') && (typeof lambdaisland.glogi.console.console_log !== 'undefined')){
} else {
lambdaisland.glogi.console.console_log = (function lambdaisland$glogi$console$console_log(p__32784){
var map__32785 = p__32784;
var map__32785__$1 = (((((!((map__32785 == null))))?(((((map__32785.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__32785.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__32785):map__32785);
var record = map__32785__$1;
var logger_name = cljs.core.get.call(null,map__32785__$1,new cljs.core.Keyword(null,"logger-name","logger-name",-1988126927));
var level = cljs.core.get.call(null,map__32785__$1,new cljs.core.Keyword(null,"level","level",1290497552));
var exception = cljs.core.get.call(null,map__32785__$1,new cljs.core.Keyword(null,"exception","exception",-335277064));
var method_name = lambdaisland.glogi.console.log_method.call(null,level);
var method = (function (){var or__4131__auto__ = goog.object.get(console,method_name);
if(cljs.core.truth_(or__4131__auto__)){
return or__4131__auto__;
} else {
return console.log;
}
})();
cljs.core.apply.call(null,method,lambdaisland.glogi.console.format.call(null,record));

if(cljs.core.truth_(exception)){
return method.call(null,["[",cljs.core.str.cljs$core$IFn$_invoke$arity$1(logger_name),"]"].join(''),cljs.core.str.cljs$core$IFn$_invoke$arity$1(exception),"\n",exception.stack);
} else {
return null;
}
});
}
lambdaisland.glogi.console.install_BANG_ = (function lambdaisland$glogi$console$install_BANG_(){
var temp__5720__auto___32787 = goog.debug.Console.instance;
if(cljs.core.truth_(temp__5720__auto___32787)){
var instance_32788 = temp__5720__auto___32787;
instance_32788.setCapturing(false);
} else {
}

if(cljs.core.truth_(goog.debug.LogBuffer.isBufferingEnabled())){
} else {
goog.debug.LogBuffer.CAPACITY = (2);
}

return lambdaisland.glogi.add_handler_once.call(null,lambdaisland.glogi.console.console_log);
});

//# sourceMappingURL=console.js.map
