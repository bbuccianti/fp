// Compiled by ClojureScript 1.10.520 {:target :nodejs}
goog.provide('kaocha.cljs.websocket');
goog.require('cljs.core');
goog.require('goog.object');
goog.require('goog.storage.mechanism.mechanismfactory');
goog.require('goog.Uri');
goog.require('goog.string');
goog.require('goog.net.jsloader');
goog.require('goog.net.XhrIo');
goog.require('goog.log');
goog.require('goog.array');
goog.require('goog.json');
goog.require('goog.html.legacyconversions');
goog.require('goog.userAgent.product');
goog.require('goog.net.WebSocket');
goog.require('goog.debug.Console');
goog.require('goog.Uri.QueryData');
goog.require('goog.Promise');
goog.require('goog.storage.mechanism.HTML5SessionStorage');
kaocha.cljs.websocket.host_env = (((!((goog.nodeGlobalRequire == null))))?new cljs.core.Keyword(null,"node","node",581201198):(((!((goog.global.document == null))))?new cljs.core.Keyword(null,"html","html",-998796897):(((((typeof goog !== 'undefined') && (typeof goog.global !== 'undefined') && (typeof goog.global.navigator !== 'undefined')) && (cljs.core._EQ_.call(null,goog.global.navigator.product,"ReactNative"))))?new cljs.core.Keyword(null,"react-native","react-native",-1543085138):(((((goog.global.document == null)) && ((typeof self !== 'undefined')) && ((!((self.importScripts == null))))))?new cljs.core.Keyword(null,"worker","worker",938239996):null))));
kaocha.cljs.websocket.event_types = new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"open","open",-1763596448),goog.net.WebSocket.EventType.OPENED,new cljs.core.Keyword(null,"close","close",1835149582),goog.net.WebSocket.EventType.CLOSED,new cljs.core.Keyword(null,"error","error",-978969032),goog.net.WebSocket.EventType.ERROR,new cljs.core.Keyword(null,"message","message",-406056002),goog.net.WebSocket.EventType.MESSAGE], null);
kaocha.cljs.websocket.get_websocket_class = (function kaocha$cljs$websocket$get_websocket_class(){
var or__4131__auto__ = goog.object.get(goog.global,"WebSocket");
if(cljs.core.truth_(or__4131__auto__)){
return or__4131__auto__;
} else {
var or__4131__auto____$1 = goog.object.get(goog.global,"FIGWHEEL_WEBSOCKET_CLASS");
if(cljs.core.truth_(or__4131__auto____$1)){
return or__4131__auto____$1;
} else {
var or__4131__auto____$2 = (function (){var and__4120__auto__ = cljs.core._EQ_.call(null,kaocha.cljs.websocket.host_env,new cljs.core.Keyword(null,"node","node",581201198));
if(and__4120__auto__){
try{return require("ws");
}catch (e31447){if((e31447 instanceof Error)){
var e = e31447;
console.log("NODE_WS_NOT_FOUND");

return null;
} else {
throw e31447;

}
}} else {
return and__4120__auto__;
}
})();
if(cljs.core.truth_(or__4131__auto____$2)){
return or__4131__auto____$2;
} else {
var and__4120__auto__ = cljs.core._EQ_.call(null,kaocha.cljs.websocket.host_env,new cljs.core.Keyword(null,"worker","worker",938239996));
if(and__4120__auto__){
return goog.object.get(self,"WebSocket");
} else {
return and__4120__auto__;
}
}
}
}
});
kaocha.cljs.websocket.ensure_websocket = (function kaocha$cljs$websocket$ensure_websocket(thunk){
if(cljs.core.truth_(goog.object.get(goog.global,"WebSocket"))){
return thunk.call(null);
} else {
var temp__5720__auto__ = kaocha.cljs.websocket.get_websocket_class.call(null);
if(cljs.core.truth_(temp__5720__auto__)){
var websocket_class = temp__5720__auto__;
goog.object.set(goog.global,"WebSocket",websocket_class);

var result = thunk.call(null);
goog.object.set(goog.global,"WebSocket",null);

return result;
} else {
return null;
}
}
});
kaocha.cljs.websocket.make_websocket = (function kaocha$cljs$websocket$make_websocket(var_args){
var G__31449 = arguments.length;
switch (G__31449) {
case 0:
return kaocha.cljs.websocket.make_websocket.cljs$core$IFn$_invoke$arity$0();

break;
case 1:
return kaocha.cljs.websocket.make_websocket.cljs$core$IFn$_invoke$arity$1((arguments[(0)]));

break;
default:
throw (new Error(["Invalid arity: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(arguments.length)].join('')));

}
});

kaocha.cljs.websocket.make_websocket.cljs$core$IFn$_invoke$arity$0 = (function (){
return kaocha.cljs.websocket.make_websocket.call(null,true);
});

kaocha.cljs.websocket.make_websocket.cljs$core$IFn$_invoke$arity$1 = (function (reconnect_QMARK_){
return kaocha.cljs.websocket.ensure_websocket.call(null,(function (){
return (new goog.net.WebSocket(reconnect_QMARK_));
}));
});

kaocha.cljs.websocket.make_websocket.cljs$lang$maxFixedArity = 1;

kaocha.cljs.websocket.register_handlers_STAR_ = (function kaocha$cljs$websocket$register_handlers_STAR_(ws,handler_map){
var seq__31451 = cljs.core.seq.call(null,handler_map);
var chunk__31452 = null;
var count__31453 = (0);
var i__31454 = (0);
while(true){
if((i__31454 < count__31453)){
var vec__31461 = cljs.core._nth.call(null,chunk__31452,i__31454);
var type = cljs.core.nth.call(null,vec__31461,(0),null);
var handler_fn = cljs.core.nth.call(null,vec__31461,(1),null);
if(cljs.core.truth_(cljs.core.get.call(null,kaocha.cljs.websocket.event_types,type))){
} else {
throw (new Error(["Assert failed: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(type),"\n","(get event-types type)"].join('')));
}

ws.addEventListener(cljs.core.get.call(null,kaocha.cljs.websocket.event_types,type),handler_fn);


var G__31467 = seq__31451;
var G__31468 = chunk__31452;
var G__31469 = count__31453;
var G__31470 = (i__31454 + (1));
seq__31451 = G__31467;
chunk__31452 = G__31468;
count__31453 = G__31469;
i__31454 = G__31470;
continue;
} else {
var temp__5720__auto__ = cljs.core.seq.call(null,seq__31451);
if(temp__5720__auto__){
var seq__31451__$1 = temp__5720__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__31451__$1)){
var c__4550__auto__ = cljs.core.chunk_first.call(null,seq__31451__$1);
var G__31471 = cljs.core.chunk_rest.call(null,seq__31451__$1);
var G__31472 = c__4550__auto__;
var G__31473 = cljs.core.count.call(null,c__4550__auto__);
var G__31474 = (0);
seq__31451 = G__31471;
chunk__31452 = G__31472;
count__31453 = G__31473;
i__31454 = G__31474;
continue;
} else {
var vec__31464 = cljs.core.first.call(null,seq__31451__$1);
var type = cljs.core.nth.call(null,vec__31464,(0),null);
var handler_fn = cljs.core.nth.call(null,vec__31464,(1),null);
if(cljs.core.truth_(cljs.core.get.call(null,kaocha.cljs.websocket.event_types,type))){
} else {
throw (new Error(["Assert failed: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(type),"\n","(get event-types type)"].join('')));
}

ws.addEventListener(cljs.core.get.call(null,kaocha.cljs.websocket.event_types,type),handler_fn);


var G__31475 = cljs.core.next.call(null,seq__31451__$1);
var G__31476 = null;
var G__31477 = (0);
var G__31478 = (0);
seq__31451 = G__31475;
chunk__31452 = G__31476;
count__31453 = G__31477;
i__31454 = G__31478;
continue;
}
} else {
return null;
}
}
break;
}
});
kaocha.cljs.websocket.register_handlers = (function kaocha$cljs$websocket$register_handlers(ws,handler_map){
return kaocha.cljs.websocket.ensure_websocket.call(null,(function (){
return kaocha.cljs.websocket.register_handlers_STAR_.call(null,ws,handler_map);
}));
});
kaocha.cljs.websocket.open_BANG__STAR_ = (function kaocha$cljs$websocket$open_BANG__STAR_(ws,url){
return ws.open(url);
});
kaocha.cljs.websocket.open_BANG_ = (function kaocha$cljs$websocket$open_BANG_(ws,url){
return kaocha.cljs.websocket.ensure_websocket.call(null,(function (){
return kaocha.cljs.websocket.open_BANG_.call(null,ws,url);
}));
});
kaocha.cljs.websocket.connect_BANG_ = (function kaocha$cljs$websocket$connect_BANG_(var_args){
var G__31480 = arguments.length;
switch (G__31480) {
case 2:
return kaocha.cljs.websocket.connect_BANG_.cljs$core$IFn$_invoke$arity$2((arguments[(0)]),(arguments[(1)]));

break;
case 3:
return kaocha.cljs.websocket.connect_BANG_.cljs$core$IFn$_invoke$arity$3((arguments[(0)]),(arguments[(1)]),(arguments[(2)]));

break;
default:
throw (new Error(["Invalid arity: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(arguments.length)].join('')));

}
});

kaocha.cljs.websocket.connect_BANG_.cljs$core$IFn$_invoke$arity$2 = (function (url,handler_map){
return kaocha.cljs.websocket.connect_BANG_.call(null,url,handler_map,true);
});

kaocha.cljs.websocket.connect_BANG_.cljs$core$IFn$_invoke$arity$3 = (function (url,handler_map,reconnect_QMARK_){
return kaocha.cljs.websocket.ensure_websocket.call(null,(function (){
var ws = (new goog.net.WebSocket(reconnect_QMARK_));
kaocha.cljs.websocket.register_handlers_STAR_.call(null,ws,handler_map);

kaocha.cljs.websocket.open_BANG__STAR_.call(null,ws,url);

return ws;
}));
});

kaocha.cljs.websocket.connect_BANG_.cljs$lang$maxFixedArity = 3;

kaocha.cljs.websocket.send_BANG_ = (function kaocha$cljs$websocket$send_BANG_(ws,msg){
return kaocha.cljs.websocket.ensure_websocket.call(null,(function (){
return ws.send(msg);
}));
});
kaocha.cljs.websocket.open_QMARK_ = (function kaocha$cljs$websocket$open_QMARK_(ws){
return kaocha.cljs.websocket.ensure_websocket.call(null,(function (){
return ws.isOpen();
}));
});
kaocha.cljs.websocket.close_BANG_ = (function kaocha$cljs$websocket$close_BANG_(ws){
return ws.close();
});
kaocha.cljs.websocket.message_data = (function kaocha$cljs$websocket$message_data(event){
return goog.object.get(event,"message");
});

//# sourceMappingURL=websocket.js.map
