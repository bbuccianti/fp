// Compiled by ClojureScript 1.10.520 {:target :nodejs}
goog.provide('kaocha.cljs.cognitect.transit');
goog.require('cljs.core');
goog.require('com.cognitect.transit');
goog.require('com.cognitect.transit.types');
goog.require('com.cognitect.transit.eq');
goog.require('goog.math.Long');
cljs.core.UUID.prototype.cljs$core$IEquiv$ = cljs.core.PROTOCOL_SENTINEL;

cljs.core.UUID.prototype.cljs$core$IEquiv$_equiv$arity$2 = (function (this$,other){
var this$__$1 = this;
if((other instanceof cljs.core.UUID)){
return (this$__$1.uuid === other.uuid);
} else {
if((other instanceof com.cognitect.transit.types.UUID)){
return (this$__$1.uuid === other.toString());
} else {
return false;

}
}
});
cljs.core.UUID.prototype.cljs$core$IComparable$ = cljs.core.PROTOCOL_SENTINEL;

cljs.core.UUID.prototype.cljs$core$IComparable$_compare$arity$2 = (function (this$,other){
var this$__$1 = this;
if((((other instanceof cljs.core.UUID)) || ((other instanceof com.cognitect.transit.types.UUID)))){
return cljs.core.compare.call(null,this$__$1.toString(),other.toString());
} else {
throw (new Error(["Cannot compare ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(this$__$1)," to ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(other)].join('')));
}
});

com.cognitect.transit.types.UUID.prototype.cljs$core$IComparable$ = cljs.core.PROTOCOL_SENTINEL;

com.cognitect.transit.types.UUID.prototype.cljs$core$IComparable$_compare$arity$2 = (function (this$,other){
var this$__$1 = this;
if((((other instanceof cljs.core.UUID)) || ((other instanceof com.cognitect.transit.types.UUID)))){
return cljs.core.compare.call(null,this$__$1.toString(),other.toString());
} else {
throw (new Error(["Cannot compare ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(this$__$1)," to ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(other)].join('')));
}
});
goog.math.Long.prototype.cljs$core$IEquiv$ = cljs.core.PROTOCOL_SENTINEL;

goog.math.Long.prototype.cljs$core$IEquiv$_equiv$arity$2 = (function (this$,other){
var this$__$1 = this;
return this$__$1.equiv(other);
});

com.cognitect.transit.types.UUID.prototype.cljs$core$IEquiv$ = cljs.core.PROTOCOL_SENTINEL;

com.cognitect.transit.types.UUID.prototype.cljs$core$IEquiv$_equiv$arity$2 = (function (this$,other){
var this$__$1 = this;
if((other instanceof cljs.core.UUID)){
return cljs.core._equiv.call(null,other,this$__$1);
} else {
return this$__$1.equiv(other);
}
});

com.cognitect.transit.types.TaggedValue.prototype.cljs$core$IEquiv$ = cljs.core.PROTOCOL_SENTINEL;

com.cognitect.transit.types.TaggedValue.prototype.cljs$core$IEquiv$_equiv$arity$2 = (function (this$,other){
var this$__$1 = this;
return this$__$1.equiv(other);
});
goog.math.Long.prototype.cljs$core$IHash$ = cljs.core.PROTOCOL_SENTINEL;

goog.math.Long.prototype.cljs$core$IHash$_hash$arity$1 = (function (this$){
var this$__$1 = this;
return com.cognitect.transit.eq.hashCode(this$__$1);
});

com.cognitect.transit.types.UUID.prototype.cljs$core$IHash$ = cljs.core.PROTOCOL_SENTINEL;

com.cognitect.transit.types.UUID.prototype.cljs$core$IHash$_hash$arity$1 = (function (this$){
var this$__$1 = this;
return cljs.core.hash.call(null,this$__$1.toString());
});

com.cognitect.transit.types.TaggedValue.prototype.cljs$core$IHash$ = cljs.core.PROTOCOL_SENTINEL;

com.cognitect.transit.types.TaggedValue.prototype.cljs$core$IHash$_hash$arity$1 = (function (this$){
var this$__$1 = this;
return com.cognitect.transit.eq.hashCode(this$__$1);
});
com.cognitect.transit.types.UUID.prototype.cljs$core$IPrintWithWriter$ = cljs.core.PROTOCOL_SENTINEL;

com.cognitect.transit.types.UUID.prototype.cljs$core$IPrintWithWriter$_pr_writer$arity$3 = (function (uuid,writer,_){
var uuid__$1 = this;
return cljs.core._write.call(null,writer,["#uuid \"",cljs.core.str.cljs$core$IFn$_invoke$arity$1(uuid__$1.toString()),"\""].join(''));
});
kaocha.cljs.cognitect.transit.opts_merge = (function kaocha$cljs$cognitect$transit$opts_merge(a,b){
var seq__31484_31488 = cljs.core.seq.call(null,cljs.core.js_keys.call(null,b));
var chunk__31485_31489 = null;
var count__31486_31490 = (0);
var i__31487_31491 = (0);
while(true){
if((i__31487_31491 < count__31486_31490)){
var k_31492 = cljs.core._nth.call(null,chunk__31485_31489,i__31487_31491);
var v_31493 = (b[k_31492]);
(a[k_31492] = v_31493);


var G__31494 = seq__31484_31488;
var G__31495 = chunk__31485_31489;
var G__31496 = count__31486_31490;
var G__31497 = (i__31487_31491 + (1));
seq__31484_31488 = G__31494;
chunk__31485_31489 = G__31495;
count__31486_31490 = G__31496;
i__31487_31491 = G__31497;
continue;
} else {
var temp__5720__auto___31498 = cljs.core.seq.call(null,seq__31484_31488);
if(temp__5720__auto___31498){
var seq__31484_31499__$1 = temp__5720__auto___31498;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__31484_31499__$1)){
var c__4550__auto___31500 = cljs.core.chunk_first.call(null,seq__31484_31499__$1);
var G__31501 = cljs.core.chunk_rest.call(null,seq__31484_31499__$1);
var G__31502 = c__4550__auto___31500;
var G__31503 = cljs.core.count.call(null,c__4550__auto___31500);
var G__31504 = (0);
seq__31484_31488 = G__31501;
chunk__31485_31489 = G__31502;
count__31486_31490 = G__31503;
i__31487_31491 = G__31504;
continue;
} else {
var k_31505 = cljs.core.first.call(null,seq__31484_31499__$1);
var v_31506 = (b[k_31505]);
(a[k_31505] = v_31506);


var G__31507 = cljs.core.next.call(null,seq__31484_31499__$1);
var G__31508 = null;
var G__31509 = (0);
var G__31510 = (0);
seq__31484_31488 = G__31507;
chunk__31485_31489 = G__31508;
count__31486_31490 = G__31509;
i__31487_31491 = G__31510;
continue;
}
} else {
}
}
break;
}

return a;
});

/**
* @constructor
 * @implements {kaocha.cljs.cognitect.transit.Object}
*/
kaocha.cljs.cognitect.transit.MapBuilder = (function (){
});
kaocha.cljs.cognitect.transit.MapBuilder.prototype.init = (function (node){
var self__ = this;
var _ = this;
return cljs.core.transient$.call(null,cljs.core.PersistentArrayMap.EMPTY);
});

kaocha.cljs.cognitect.transit.MapBuilder.prototype.add = (function (m,k,v,node){
var self__ = this;
var _ = this;
return cljs.core.assoc_BANG_.call(null,m,k,v);
});

kaocha.cljs.cognitect.transit.MapBuilder.prototype.finalize = (function (m,node){
var self__ = this;
var _ = this;
return cljs.core.persistent_BANG_.call(null,m);
});

kaocha.cljs.cognitect.transit.MapBuilder.prototype.fromArray = (function (arr,node){
var self__ = this;
var _ = this;
return cljs.core.PersistentArrayMap.fromArray.call(null,arr,true,true);
});

kaocha.cljs.cognitect.transit.MapBuilder.getBasis = (function (){
return cljs.core.PersistentVector.EMPTY;
});

kaocha.cljs.cognitect.transit.MapBuilder.cljs$lang$type = true;

kaocha.cljs.cognitect.transit.MapBuilder.cljs$lang$ctorStr = "kaocha.cljs.cognitect.transit/MapBuilder";

kaocha.cljs.cognitect.transit.MapBuilder.cljs$lang$ctorPrWriter = (function (this__4374__auto__,writer__4375__auto__,opt__4376__auto__){
return cljs.core._write.call(null,writer__4375__auto__,"kaocha.cljs.cognitect.transit/MapBuilder");
});

/**
 * Positional factory function for kaocha.cljs.cognitect.transit/MapBuilder.
 */
kaocha.cljs.cognitect.transit.__GT_MapBuilder = (function kaocha$cljs$cognitect$transit$__GT_MapBuilder(){
return (new kaocha.cljs.cognitect.transit.MapBuilder());
});


/**
* @constructor
 * @implements {kaocha.cljs.cognitect.transit.Object}
*/
kaocha.cljs.cognitect.transit.VectorBuilder = (function (){
});
kaocha.cljs.cognitect.transit.VectorBuilder.prototype.init = (function (node){
var self__ = this;
var _ = this;
return cljs.core.transient$.call(null,cljs.core.PersistentVector.EMPTY);
});

kaocha.cljs.cognitect.transit.VectorBuilder.prototype.add = (function (v,x,node){
var self__ = this;
var _ = this;
return cljs.core.conj_BANG_.call(null,v,x);
});

kaocha.cljs.cognitect.transit.VectorBuilder.prototype.finalize = (function (v,node){
var self__ = this;
var _ = this;
return cljs.core.persistent_BANG_.call(null,v);
});

kaocha.cljs.cognitect.transit.VectorBuilder.prototype.fromArray = (function (arr,node){
var self__ = this;
var _ = this;
return cljs.core.PersistentVector.fromArray.call(null,arr,true);
});

kaocha.cljs.cognitect.transit.VectorBuilder.getBasis = (function (){
return cljs.core.PersistentVector.EMPTY;
});

kaocha.cljs.cognitect.transit.VectorBuilder.cljs$lang$type = true;

kaocha.cljs.cognitect.transit.VectorBuilder.cljs$lang$ctorStr = "kaocha.cljs.cognitect.transit/VectorBuilder";

kaocha.cljs.cognitect.transit.VectorBuilder.cljs$lang$ctorPrWriter = (function (this__4374__auto__,writer__4375__auto__,opt__4376__auto__){
return cljs.core._write.call(null,writer__4375__auto__,"kaocha.cljs.cognitect.transit/VectorBuilder");
});

/**
 * Positional factory function for kaocha.cljs.cognitect.transit/VectorBuilder.
 */
kaocha.cljs.cognitect.transit.__GT_VectorBuilder = (function kaocha$cljs$cognitect$transit$__GT_VectorBuilder(){
return (new kaocha.cljs.cognitect.transit.VectorBuilder());
});

/**
 * Return a transit reader. type may be either :json or :json-verbose.
 * opts may be a map optionally containing a :handlers entry. The value
 * of :handlers should be map from string tag to a decoder function of one
 * argument which returns the in-memory representation of the semantic transit
 * value. If a :default handler is provided, it will be used when no matching
 * read handler can be found.
 */
kaocha.cljs.cognitect.transit.reader = (function kaocha$cljs$cognitect$transit$reader(var_args){
var G__31512 = arguments.length;
switch (G__31512) {
case 1:
return kaocha.cljs.cognitect.transit.reader.cljs$core$IFn$_invoke$arity$1((arguments[(0)]));

break;
case 2:
return kaocha.cljs.cognitect.transit.reader.cljs$core$IFn$_invoke$arity$2((arguments[(0)]),(arguments[(1)]));

break;
default:
throw (new Error(["Invalid arity: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(arguments.length)].join('')));

}
});

kaocha.cljs.cognitect.transit.reader.cljs$core$IFn$_invoke$arity$1 = (function (type){
return kaocha.cljs.cognitect.transit.reader.call(null,type,null);
});

kaocha.cljs.cognitect.transit.reader.cljs$core$IFn$_invoke$arity$2 = (function (type,opts){
return com.cognitect.transit.reader(cljs.core.name.call(null,type),kaocha.cljs.cognitect.transit.opts_merge.call(null,({"handlers": cljs.core.clj__GT_js.call(null,cljs.core.merge.call(null,new cljs.core.PersistentArrayMap(null, 6, ["$",(function (v){
return cljs.core.symbol.call(null,v);
}),":",(function (v){
return cljs.core.keyword.call(null,v);
}),"set",(function (v){
return cljs.core.into.call(null,cljs.core.PersistentHashSet.EMPTY,v);
}),"list",(function (v){
return cljs.core.into.call(null,cljs.core.List.EMPTY,v.reverse());
}),"cmap",(function (v){
var i = (0);
var ret = cljs.core.transient$.call(null,cljs.core.PersistentArrayMap.EMPTY);
while(true){
if((i < v.length)){
var G__31514 = (i + (2));
var G__31515 = cljs.core.assoc_BANG_.call(null,ret,(v[i]),(v[(i + (1))]));
i = G__31514;
ret = G__31515;
continue;
} else {
return cljs.core.persistent_BANG_.call(null,ret);
}
break;
}
}),"with-meta",(function (v){
return cljs.core.with_meta.call(null,(v[(0)]),(v[(1)]));
})], null),cljs.core.dissoc.call(null,new cljs.core.Keyword(null,"handlers","handlers",79528781).cljs$core$IFn$_invoke$arity$1(opts),new cljs.core.Keyword(null,"default","default",-1987822328)))), "defaultHandler": new cljs.core.Keyword(null,"default","default",-1987822328).cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"handlers","handlers",79528781).cljs$core$IFn$_invoke$arity$1(opts)), "mapBuilder": (new kaocha.cljs.cognitect.transit.MapBuilder()), "arrayBuilder": (new kaocha.cljs.cognitect.transit.VectorBuilder()), "prefersStrings": false}),cljs.core.clj__GT_js.call(null,cljs.core.dissoc.call(null,opts,new cljs.core.Keyword(null,"handlers","handlers",79528781)))));
});

kaocha.cljs.cognitect.transit.reader.cljs$lang$maxFixedArity = 2;

/**
 * Read a transit encoded string into ClojureScript values given a
 * transit reader.
 */
kaocha.cljs.cognitect.transit.read = (function kaocha$cljs$cognitect$transit$read(r,str){
return r.read(str);
});

/**
* @constructor
 * @implements {kaocha.cljs.cognitect.transit.Object}
*/
kaocha.cljs.cognitect.transit.KeywordHandler = (function (){
});
kaocha.cljs.cognitect.transit.KeywordHandler.prototype.tag = (function (v){
var self__ = this;
var _ = this;
return ":";
});

kaocha.cljs.cognitect.transit.KeywordHandler.prototype.rep = (function (v){
var self__ = this;
var _ = this;
return v.fqn;
});

kaocha.cljs.cognitect.transit.KeywordHandler.prototype.stringRep = (function (v){
var self__ = this;
var _ = this;
return v.fqn;
});

kaocha.cljs.cognitect.transit.KeywordHandler.getBasis = (function (){
return cljs.core.PersistentVector.EMPTY;
});

kaocha.cljs.cognitect.transit.KeywordHandler.cljs$lang$type = true;

kaocha.cljs.cognitect.transit.KeywordHandler.cljs$lang$ctorStr = "kaocha.cljs.cognitect.transit/KeywordHandler";

kaocha.cljs.cognitect.transit.KeywordHandler.cljs$lang$ctorPrWriter = (function (this__4374__auto__,writer__4375__auto__,opt__4376__auto__){
return cljs.core._write.call(null,writer__4375__auto__,"kaocha.cljs.cognitect.transit/KeywordHandler");
});

/**
 * Positional factory function for kaocha.cljs.cognitect.transit/KeywordHandler.
 */
kaocha.cljs.cognitect.transit.__GT_KeywordHandler = (function kaocha$cljs$cognitect$transit$__GT_KeywordHandler(){
return (new kaocha.cljs.cognitect.transit.KeywordHandler());
});


/**
* @constructor
 * @implements {kaocha.cljs.cognitect.transit.Object}
*/
kaocha.cljs.cognitect.transit.SymbolHandler = (function (){
});
kaocha.cljs.cognitect.transit.SymbolHandler.prototype.tag = (function (v){
var self__ = this;
var _ = this;
return "$";
});

kaocha.cljs.cognitect.transit.SymbolHandler.prototype.rep = (function (v){
var self__ = this;
var _ = this;
return v.str;
});

kaocha.cljs.cognitect.transit.SymbolHandler.prototype.stringRep = (function (v){
var self__ = this;
var _ = this;
return v.str;
});

kaocha.cljs.cognitect.transit.SymbolHandler.getBasis = (function (){
return cljs.core.PersistentVector.EMPTY;
});

kaocha.cljs.cognitect.transit.SymbolHandler.cljs$lang$type = true;

kaocha.cljs.cognitect.transit.SymbolHandler.cljs$lang$ctorStr = "kaocha.cljs.cognitect.transit/SymbolHandler";

kaocha.cljs.cognitect.transit.SymbolHandler.cljs$lang$ctorPrWriter = (function (this__4374__auto__,writer__4375__auto__,opt__4376__auto__){
return cljs.core._write.call(null,writer__4375__auto__,"kaocha.cljs.cognitect.transit/SymbolHandler");
});

/**
 * Positional factory function for kaocha.cljs.cognitect.transit/SymbolHandler.
 */
kaocha.cljs.cognitect.transit.__GT_SymbolHandler = (function kaocha$cljs$cognitect$transit$__GT_SymbolHandler(){
return (new kaocha.cljs.cognitect.transit.SymbolHandler());
});


/**
* @constructor
 * @implements {kaocha.cljs.cognitect.transit.Object}
*/
kaocha.cljs.cognitect.transit.ListHandler = (function (){
});
kaocha.cljs.cognitect.transit.ListHandler.prototype.tag = (function (v){
var self__ = this;
var _ = this;
return "list";
});

kaocha.cljs.cognitect.transit.ListHandler.prototype.rep = (function (v){
var self__ = this;
var _ = this;
var ret = [];
var seq__31516_31520 = cljs.core.seq.call(null,v);
var chunk__31517_31521 = null;
var count__31518_31522 = (0);
var i__31519_31523 = (0);
while(true){
if((i__31519_31523 < count__31518_31522)){
var x_31524 = cljs.core._nth.call(null,chunk__31517_31521,i__31519_31523);
ret.push(x_31524);


var G__31525 = seq__31516_31520;
var G__31526 = chunk__31517_31521;
var G__31527 = count__31518_31522;
var G__31528 = (i__31519_31523 + (1));
seq__31516_31520 = G__31525;
chunk__31517_31521 = G__31526;
count__31518_31522 = G__31527;
i__31519_31523 = G__31528;
continue;
} else {
var temp__5720__auto___31529 = cljs.core.seq.call(null,seq__31516_31520);
if(temp__5720__auto___31529){
var seq__31516_31530__$1 = temp__5720__auto___31529;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__31516_31530__$1)){
var c__4550__auto___31531 = cljs.core.chunk_first.call(null,seq__31516_31530__$1);
var G__31532 = cljs.core.chunk_rest.call(null,seq__31516_31530__$1);
var G__31533 = c__4550__auto___31531;
var G__31534 = cljs.core.count.call(null,c__4550__auto___31531);
var G__31535 = (0);
seq__31516_31520 = G__31532;
chunk__31517_31521 = G__31533;
count__31518_31522 = G__31534;
i__31519_31523 = G__31535;
continue;
} else {
var x_31536 = cljs.core.first.call(null,seq__31516_31530__$1);
ret.push(x_31536);


var G__31537 = cljs.core.next.call(null,seq__31516_31530__$1);
var G__31538 = null;
var G__31539 = (0);
var G__31540 = (0);
seq__31516_31520 = G__31537;
chunk__31517_31521 = G__31538;
count__31518_31522 = G__31539;
i__31519_31523 = G__31540;
continue;
}
} else {
}
}
break;
}

return com.cognitect.transit.tagged("array",ret);
});

kaocha.cljs.cognitect.transit.ListHandler.prototype.stringRep = (function (v){
var self__ = this;
var _ = this;
return null;
});

kaocha.cljs.cognitect.transit.ListHandler.getBasis = (function (){
return cljs.core.PersistentVector.EMPTY;
});

kaocha.cljs.cognitect.transit.ListHandler.cljs$lang$type = true;

kaocha.cljs.cognitect.transit.ListHandler.cljs$lang$ctorStr = "kaocha.cljs.cognitect.transit/ListHandler";

kaocha.cljs.cognitect.transit.ListHandler.cljs$lang$ctorPrWriter = (function (this__4374__auto__,writer__4375__auto__,opt__4376__auto__){
return cljs.core._write.call(null,writer__4375__auto__,"kaocha.cljs.cognitect.transit/ListHandler");
});

/**
 * Positional factory function for kaocha.cljs.cognitect.transit/ListHandler.
 */
kaocha.cljs.cognitect.transit.__GT_ListHandler = (function kaocha$cljs$cognitect$transit$__GT_ListHandler(){
return (new kaocha.cljs.cognitect.transit.ListHandler());
});


/**
* @constructor
 * @implements {kaocha.cljs.cognitect.transit.Object}
*/
kaocha.cljs.cognitect.transit.MapHandler = (function (){
});
kaocha.cljs.cognitect.transit.MapHandler.prototype.tag = (function (v){
var self__ = this;
var _ = this;
return "map";
});

kaocha.cljs.cognitect.transit.MapHandler.prototype.rep = (function (v){
var self__ = this;
var _ = this;
return v;
});

kaocha.cljs.cognitect.transit.MapHandler.prototype.stringRep = (function (v){
var self__ = this;
var _ = this;
return null;
});

kaocha.cljs.cognitect.transit.MapHandler.getBasis = (function (){
return cljs.core.PersistentVector.EMPTY;
});

kaocha.cljs.cognitect.transit.MapHandler.cljs$lang$type = true;

kaocha.cljs.cognitect.transit.MapHandler.cljs$lang$ctorStr = "kaocha.cljs.cognitect.transit/MapHandler";

kaocha.cljs.cognitect.transit.MapHandler.cljs$lang$ctorPrWriter = (function (this__4374__auto__,writer__4375__auto__,opt__4376__auto__){
return cljs.core._write.call(null,writer__4375__auto__,"kaocha.cljs.cognitect.transit/MapHandler");
});

/**
 * Positional factory function for kaocha.cljs.cognitect.transit/MapHandler.
 */
kaocha.cljs.cognitect.transit.__GT_MapHandler = (function kaocha$cljs$cognitect$transit$__GT_MapHandler(){
return (new kaocha.cljs.cognitect.transit.MapHandler());
});


/**
* @constructor
 * @implements {kaocha.cljs.cognitect.transit.Object}
*/
kaocha.cljs.cognitect.transit.SetHandler = (function (){
});
kaocha.cljs.cognitect.transit.SetHandler.prototype.tag = (function (v){
var self__ = this;
var _ = this;
return "set";
});

kaocha.cljs.cognitect.transit.SetHandler.prototype.rep = (function (v){
var self__ = this;
var _ = this;
var ret = [];
var seq__31541_31545 = cljs.core.seq.call(null,v);
var chunk__31542_31546 = null;
var count__31543_31547 = (0);
var i__31544_31548 = (0);
while(true){
if((i__31544_31548 < count__31543_31547)){
var x_31549 = cljs.core._nth.call(null,chunk__31542_31546,i__31544_31548);
ret.push(x_31549);


var G__31550 = seq__31541_31545;
var G__31551 = chunk__31542_31546;
var G__31552 = count__31543_31547;
var G__31553 = (i__31544_31548 + (1));
seq__31541_31545 = G__31550;
chunk__31542_31546 = G__31551;
count__31543_31547 = G__31552;
i__31544_31548 = G__31553;
continue;
} else {
var temp__5720__auto___31554 = cljs.core.seq.call(null,seq__31541_31545);
if(temp__5720__auto___31554){
var seq__31541_31555__$1 = temp__5720__auto___31554;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__31541_31555__$1)){
var c__4550__auto___31556 = cljs.core.chunk_first.call(null,seq__31541_31555__$1);
var G__31557 = cljs.core.chunk_rest.call(null,seq__31541_31555__$1);
var G__31558 = c__4550__auto___31556;
var G__31559 = cljs.core.count.call(null,c__4550__auto___31556);
var G__31560 = (0);
seq__31541_31545 = G__31557;
chunk__31542_31546 = G__31558;
count__31543_31547 = G__31559;
i__31544_31548 = G__31560;
continue;
} else {
var x_31561 = cljs.core.first.call(null,seq__31541_31555__$1);
ret.push(x_31561);


var G__31562 = cljs.core.next.call(null,seq__31541_31555__$1);
var G__31563 = null;
var G__31564 = (0);
var G__31565 = (0);
seq__31541_31545 = G__31562;
chunk__31542_31546 = G__31563;
count__31543_31547 = G__31564;
i__31544_31548 = G__31565;
continue;
}
} else {
}
}
break;
}

return com.cognitect.transit.tagged("array",ret);
});

kaocha.cljs.cognitect.transit.SetHandler.prototype.stringRep = (function (){
var self__ = this;
var v = this;
return null;
});

kaocha.cljs.cognitect.transit.SetHandler.getBasis = (function (){
return cljs.core.PersistentVector.EMPTY;
});

kaocha.cljs.cognitect.transit.SetHandler.cljs$lang$type = true;

kaocha.cljs.cognitect.transit.SetHandler.cljs$lang$ctorStr = "kaocha.cljs.cognitect.transit/SetHandler";

kaocha.cljs.cognitect.transit.SetHandler.cljs$lang$ctorPrWriter = (function (this__4374__auto__,writer__4375__auto__,opt__4376__auto__){
return cljs.core._write.call(null,writer__4375__auto__,"kaocha.cljs.cognitect.transit/SetHandler");
});

/**
 * Positional factory function for kaocha.cljs.cognitect.transit/SetHandler.
 */
kaocha.cljs.cognitect.transit.__GT_SetHandler = (function kaocha$cljs$cognitect$transit$__GT_SetHandler(){
return (new kaocha.cljs.cognitect.transit.SetHandler());
});


/**
* @constructor
 * @implements {kaocha.cljs.cognitect.transit.Object}
*/
kaocha.cljs.cognitect.transit.VectorHandler = (function (){
});
kaocha.cljs.cognitect.transit.VectorHandler.prototype.tag = (function (v){
var self__ = this;
var _ = this;
return "array";
});

kaocha.cljs.cognitect.transit.VectorHandler.prototype.rep = (function (v){
var self__ = this;
var _ = this;
var ret = [];
var seq__31566_31570 = cljs.core.seq.call(null,v);
var chunk__31567_31571 = null;
var count__31568_31572 = (0);
var i__31569_31573 = (0);
while(true){
if((i__31569_31573 < count__31568_31572)){
var x_31574 = cljs.core._nth.call(null,chunk__31567_31571,i__31569_31573);
ret.push(x_31574);


var G__31575 = seq__31566_31570;
var G__31576 = chunk__31567_31571;
var G__31577 = count__31568_31572;
var G__31578 = (i__31569_31573 + (1));
seq__31566_31570 = G__31575;
chunk__31567_31571 = G__31576;
count__31568_31572 = G__31577;
i__31569_31573 = G__31578;
continue;
} else {
var temp__5720__auto___31579 = cljs.core.seq.call(null,seq__31566_31570);
if(temp__5720__auto___31579){
var seq__31566_31580__$1 = temp__5720__auto___31579;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__31566_31580__$1)){
var c__4550__auto___31581 = cljs.core.chunk_first.call(null,seq__31566_31580__$1);
var G__31582 = cljs.core.chunk_rest.call(null,seq__31566_31580__$1);
var G__31583 = c__4550__auto___31581;
var G__31584 = cljs.core.count.call(null,c__4550__auto___31581);
var G__31585 = (0);
seq__31566_31570 = G__31582;
chunk__31567_31571 = G__31583;
count__31568_31572 = G__31584;
i__31569_31573 = G__31585;
continue;
} else {
var x_31586 = cljs.core.first.call(null,seq__31566_31580__$1);
ret.push(x_31586);


var G__31587 = cljs.core.next.call(null,seq__31566_31580__$1);
var G__31588 = null;
var G__31589 = (0);
var G__31590 = (0);
seq__31566_31570 = G__31587;
chunk__31567_31571 = G__31588;
count__31568_31572 = G__31589;
i__31569_31573 = G__31590;
continue;
}
} else {
}
}
break;
}

return ret;
});

kaocha.cljs.cognitect.transit.VectorHandler.prototype.stringRep = (function (v){
var self__ = this;
var _ = this;
return null;
});

kaocha.cljs.cognitect.transit.VectorHandler.getBasis = (function (){
return cljs.core.PersistentVector.EMPTY;
});

kaocha.cljs.cognitect.transit.VectorHandler.cljs$lang$type = true;

kaocha.cljs.cognitect.transit.VectorHandler.cljs$lang$ctorStr = "kaocha.cljs.cognitect.transit/VectorHandler";

kaocha.cljs.cognitect.transit.VectorHandler.cljs$lang$ctorPrWriter = (function (this__4374__auto__,writer__4375__auto__,opt__4376__auto__){
return cljs.core._write.call(null,writer__4375__auto__,"kaocha.cljs.cognitect.transit/VectorHandler");
});

/**
 * Positional factory function for kaocha.cljs.cognitect.transit/VectorHandler.
 */
kaocha.cljs.cognitect.transit.__GT_VectorHandler = (function kaocha$cljs$cognitect$transit$__GT_VectorHandler(){
return (new kaocha.cljs.cognitect.transit.VectorHandler());
});


/**
* @constructor
 * @implements {kaocha.cljs.cognitect.transit.Object}
*/
kaocha.cljs.cognitect.transit.UUIDHandler = (function (){
});
kaocha.cljs.cognitect.transit.UUIDHandler.prototype.tag = (function (v){
var self__ = this;
var _ = this;
return "u";
});

kaocha.cljs.cognitect.transit.UUIDHandler.prototype.rep = (function (v){
var self__ = this;
var _ = this;
return v.uuid;
});

kaocha.cljs.cognitect.transit.UUIDHandler.prototype.stringRep = (function (v){
var self__ = this;
var this$ = this;
return this$.rep(v);
});

kaocha.cljs.cognitect.transit.UUIDHandler.getBasis = (function (){
return cljs.core.PersistentVector.EMPTY;
});

kaocha.cljs.cognitect.transit.UUIDHandler.cljs$lang$type = true;

kaocha.cljs.cognitect.transit.UUIDHandler.cljs$lang$ctorStr = "kaocha.cljs.cognitect.transit/UUIDHandler";

kaocha.cljs.cognitect.transit.UUIDHandler.cljs$lang$ctorPrWriter = (function (this__4374__auto__,writer__4375__auto__,opt__4376__auto__){
return cljs.core._write.call(null,writer__4375__auto__,"kaocha.cljs.cognitect.transit/UUIDHandler");
});

/**
 * Positional factory function for kaocha.cljs.cognitect.transit/UUIDHandler.
 */
kaocha.cljs.cognitect.transit.__GT_UUIDHandler = (function kaocha$cljs$cognitect$transit$__GT_UUIDHandler(){
return (new kaocha.cljs.cognitect.transit.UUIDHandler());
});


/**
* @constructor
*/
kaocha.cljs.cognitect.transit.WithMeta = (function (value,meta){
this.value = value;
this.meta = meta;
});

kaocha.cljs.cognitect.transit.WithMeta.getBasis = (function (){
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol(null,"value","value",1946509744,null),new cljs.core.Symbol(null,"meta","meta",-1154898805,null)], null);
});

kaocha.cljs.cognitect.transit.WithMeta.cljs$lang$type = true;

kaocha.cljs.cognitect.transit.WithMeta.cljs$lang$ctorStr = "kaocha.cljs.cognitect.transit/WithMeta";

kaocha.cljs.cognitect.transit.WithMeta.cljs$lang$ctorPrWriter = (function (this__4374__auto__,writer__4375__auto__,opt__4376__auto__){
return cljs.core._write.call(null,writer__4375__auto__,"kaocha.cljs.cognitect.transit/WithMeta");
});

/**
 * Positional factory function for kaocha.cljs.cognitect.transit/WithMeta.
 */
kaocha.cljs.cognitect.transit.__GT_WithMeta = (function kaocha$cljs$cognitect$transit$__GT_WithMeta(value,meta){
return (new kaocha.cljs.cognitect.transit.WithMeta(value,meta));
});


/**
* @constructor
 * @implements {kaocha.cljs.cognitect.transit.Object}
*/
kaocha.cljs.cognitect.transit.WithMetaHandler = (function (){
});
kaocha.cljs.cognitect.transit.WithMetaHandler.prototype.tag = (function (v){
var self__ = this;
var _ = this;
return "with-meta";
});

kaocha.cljs.cognitect.transit.WithMetaHandler.prototype.rep = (function (v){
var self__ = this;
var _ = this;
return com.cognitect.transit.tagged("array",[v.value,v.meta]);
});

kaocha.cljs.cognitect.transit.WithMetaHandler.prototype.stringRep = (function (v){
var self__ = this;
var _ = this;
return null;
});

kaocha.cljs.cognitect.transit.WithMetaHandler.getBasis = (function (){
return cljs.core.PersistentVector.EMPTY;
});

kaocha.cljs.cognitect.transit.WithMetaHandler.cljs$lang$type = true;

kaocha.cljs.cognitect.transit.WithMetaHandler.cljs$lang$ctorStr = "kaocha.cljs.cognitect.transit/WithMetaHandler";

kaocha.cljs.cognitect.transit.WithMetaHandler.cljs$lang$ctorPrWriter = (function (this__4374__auto__,writer__4375__auto__,opt__4376__auto__){
return cljs.core._write.call(null,writer__4375__auto__,"kaocha.cljs.cognitect.transit/WithMetaHandler");
});

/**
 * Positional factory function for kaocha.cljs.cognitect.transit/WithMetaHandler.
 */
kaocha.cljs.cognitect.transit.__GT_WithMetaHandler = (function kaocha$cljs$cognitect$transit$__GT_WithMetaHandler(){
return (new kaocha.cljs.cognitect.transit.WithMetaHandler());
});

/**
 * Return a transit writer. type maybe either :json or :json-verbose.
 *   opts is a map with the following optional keys:
 * 
 *  :handlers  - a map of type constructors to handler instances. Can optionally
 *               provide a :default write handler which will be used if no
 *               matching handler can be found.
 *  :transform - a function of one argument returning a transformed value. Will
 *               be invoked on a value before it is written.
 */
kaocha.cljs.cognitect.transit.writer = (function kaocha$cljs$cognitect$transit$writer(var_args){
var G__31592 = arguments.length;
switch (G__31592) {
case 1:
return kaocha.cljs.cognitect.transit.writer.cljs$core$IFn$_invoke$arity$1((arguments[(0)]));

break;
case 2:
return kaocha.cljs.cognitect.transit.writer.cljs$core$IFn$_invoke$arity$2((arguments[(0)]),(arguments[(1)]));

break;
default:
throw (new Error(["Invalid arity: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(arguments.length)].join('')));

}
});

kaocha.cljs.cognitect.transit.writer.cljs$core$IFn$_invoke$arity$1 = (function (type){
return kaocha.cljs.cognitect.transit.writer.call(null,type,null);
});

kaocha.cljs.cognitect.transit.writer.cljs$core$IFn$_invoke$arity$2 = (function (type,opts){
var keyword_handler = (new kaocha.cljs.cognitect.transit.KeywordHandler());
var symbol_handler = (new kaocha.cljs.cognitect.transit.SymbolHandler());
var list_handler = (new kaocha.cljs.cognitect.transit.ListHandler());
var map_handler = (new kaocha.cljs.cognitect.transit.MapHandler());
var set_handler = (new kaocha.cljs.cognitect.transit.SetHandler());
var vector_handler = (new kaocha.cljs.cognitect.transit.VectorHandler());
var uuid_handler = (new kaocha.cljs.cognitect.transit.UUIDHandler());
var meta_handler = (new kaocha.cljs.cognitect.transit.WithMetaHandler());
var handlers = cljs.core.merge.call(null,cljs.core.PersistentHashMap.fromArrays([cljs.core.PersistentHashMap,cljs.core.Cons,cljs.core.PersistentArrayMap,cljs.core.NodeSeq,cljs.core.PersistentQueue,cljs.core.IndexedSeq,cljs.core.Keyword,cljs.core.EmptyList,cljs.core.LazySeq,cljs.core.Subvec,cljs.core.PersistentQueueSeq,cljs.core.ArrayNodeSeq,cljs.core.ValSeq,kaocha.cljs.cognitect.transit.WithMeta,cljs.core.PersistentArrayMapSeq,cljs.core.PersistentVector,cljs.core.List,cljs.core.RSeq,cljs.core.PersistentHashSet,cljs.core.PersistentTreeMap,cljs.core.KeySeq,cljs.core.ChunkedSeq,cljs.core.PersistentTreeSet,cljs.core.ChunkedCons,cljs.core.Symbol,cljs.core.UUID,cljs.core.Range,cljs.core.PersistentTreeMapSeq],[map_handler,list_handler,map_handler,list_handler,list_handler,list_handler,keyword_handler,list_handler,list_handler,vector_handler,list_handler,list_handler,list_handler,meta_handler,list_handler,vector_handler,list_handler,list_handler,set_handler,map_handler,list_handler,list_handler,set_handler,list_handler,symbol_handler,uuid_handler,list_handler,list_handler]),(((typeof cljs !== 'undefined') && (typeof cljs.core !== 'undefined') && (typeof cljs.core.Eduction !== 'undefined'))?cljs.core.PersistentArrayMap.createAsIfByAssoc([cljs.core.Eduction,list_handler]):null),(((typeof cljs !== 'undefined') && (typeof cljs.core !== 'undefined') && (typeof cljs.core.Repeat !== 'undefined'))?cljs.core.PersistentArrayMap.createAsIfByAssoc([cljs.core.Repeat,list_handler]):null),(((typeof cljs !== 'undefined') && (typeof cljs.core !== 'undefined') && (typeof cljs.core.MapEntry !== 'undefined'))?cljs.core.PersistentArrayMap.createAsIfByAssoc([cljs.core.MapEntry,vector_handler]):null),new cljs.core.Keyword(null,"handlers","handlers",79528781).cljs$core$IFn$_invoke$arity$1(opts));
return com.cognitect.transit.writer(cljs.core.name.call(null,type),kaocha.cljs.cognitect.transit.opts_merge.call(null,({"objectBuilder": ((function (keyword_handler,symbol_handler,list_handler,map_handler,set_handler,vector_handler,uuid_handler,meta_handler,handlers){
return (function (m,kfn,vfn){
return cljs.core.reduce_kv.call(null,((function (keyword_handler,symbol_handler,list_handler,map_handler,set_handler,vector_handler,uuid_handler,meta_handler,handlers){
return (function (obj,k,v){
var G__31593 = obj;
G__31593.push(kfn.call(null,k),vfn.call(null,v));

return G__31593;
});})(keyword_handler,symbol_handler,list_handler,map_handler,set_handler,vector_handler,uuid_handler,meta_handler,handlers))
,["^ "],m);
});})(keyword_handler,symbol_handler,list_handler,map_handler,set_handler,vector_handler,uuid_handler,meta_handler,handlers))
, "handlers": (function (){var x31594 = cljs.core.clone.call(null,handlers);
x31594.forEach = ((function (x31594,keyword_handler,symbol_handler,list_handler,map_handler,set_handler,vector_handler,uuid_handler,meta_handler,handlers){
return (function (f){
var coll = this;
var seq__31595 = cljs.core.seq.call(null,coll);
var chunk__31596 = null;
var count__31597 = (0);
var i__31598 = (0);
while(true){
if((i__31598 < count__31597)){
var vec__31605 = cljs.core._nth.call(null,chunk__31596,i__31598);
var k = cljs.core.nth.call(null,vec__31605,(0),null);
var v = cljs.core.nth.call(null,vec__31605,(1),null);
if(cljs.core._EQ_.call(null,new cljs.core.Keyword(null,"default","default",-1987822328),k)){
f.call(null,v,"default");
} else {
f.call(null,v,k);
}


var G__31612 = seq__31595;
var G__31613 = chunk__31596;
var G__31614 = count__31597;
var G__31615 = (i__31598 + (1));
seq__31595 = G__31612;
chunk__31596 = G__31613;
count__31597 = G__31614;
i__31598 = G__31615;
continue;
} else {
var temp__5720__auto__ = cljs.core.seq.call(null,seq__31595);
if(temp__5720__auto__){
var seq__31595__$1 = temp__5720__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__31595__$1)){
var c__4550__auto__ = cljs.core.chunk_first.call(null,seq__31595__$1);
var G__31616 = cljs.core.chunk_rest.call(null,seq__31595__$1);
var G__31617 = c__4550__auto__;
var G__31618 = cljs.core.count.call(null,c__4550__auto__);
var G__31619 = (0);
seq__31595 = G__31616;
chunk__31596 = G__31617;
count__31597 = G__31618;
i__31598 = G__31619;
continue;
} else {
var vec__31608 = cljs.core.first.call(null,seq__31595__$1);
var k = cljs.core.nth.call(null,vec__31608,(0),null);
var v = cljs.core.nth.call(null,vec__31608,(1),null);
if(cljs.core._EQ_.call(null,new cljs.core.Keyword(null,"default","default",-1987822328),k)){
f.call(null,v,"default");
} else {
f.call(null,v,k);
}


var G__31620 = cljs.core.next.call(null,seq__31595__$1);
var G__31621 = null;
var G__31622 = (0);
var G__31623 = (0);
seq__31595 = G__31620;
chunk__31596 = G__31621;
count__31597 = G__31622;
i__31598 = G__31623;
continue;
}
} else {
return null;
}
}
break;
}
});})(x31594,keyword_handler,symbol_handler,list_handler,map_handler,set_handler,vector_handler,uuid_handler,meta_handler,handlers))
;

return x31594;
})(), "unpack": ((function (keyword_handler,symbol_handler,list_handler,map_handler,set_handler,vector_handler,uuid_handler,meta_handler,handlers){
return (function (x){
if((x instanceof cljs.core.PersistentArrayMap)){
return x.arr;
} else {
return false;
}
});})(keyword_handler,symbol_handler,list_handler,map_handler,set_handler,vector_handler,uuid_handler,meta_handler,handlers))
}),cljs.core.clj__GT_js.call(null,cljs.core.dissoc.call(null,opts,new cljs.core.Keyword(null,"handlers","handlers",79528781)))));
});

kaocha.cljs.cognitect.transit.writer.cljs$lang$maxFixedArity = 2;

/**
 * Encode an object into a transit string given a transit writer.
 */
kaocha.cljs.cognitect.transit.write = (function kaocha$cljs$cognitect$transit$write(w,o){
return w.write(o);
});
/**
 * Construct a read handler. Implemented as identity, exists primarily
 * for API compatiblity with transit-clj
 */
kaocha.cljs.cognitect.transit.read_handler = (function kaocha$cljs$cognitect$transit$read_handler(from_rep){
return from_rep;
});
/**
 * Creates a transit write handler whose tag, rep,
 * stringRep, and verboseWriteHandler methods
 * invoke the provided fns.
 */
kaocha.cljs.cognitect.transit.write_handler = (function kaocha$cljs$cognitect$transit$write_handler(var_args){
var G__31625 = arguments.length;
switch (G__31625) {
case 2:
return kaocha.cljs.cognitect.transit.write_handler.cljs$core$IFn$_invoke$arity$2((arguments[(0)]),(arguments[(1)]));

break;
case 3:
return kaocha.cljs.cognitect.transit.write_handler.cljs$core$IFn$_invoke$arity$3((arguments[(0)]),(arguments[(1)]),(arguments[(2)]));

break;
case 4:
return kaocha.cljs.cognitect.transit.write_handler.cljs$core$IFn$_invoke$arity$4((arguments[(0)]),(arguments[(1)]),(arguments[(2)]),(arguments[(3)]));

break;
default:
throw (new Error(["Invalid arity: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(arguments.length)].join('')));

}
});

kaocha.cljs.cognitect.transit.write_handler.cljs$core$IFn$_invoke$arity$2 = (function (tag_fn,rep_fn){
return kaocha.cljs.cognitect.transit.write_handler.call(null,tag_fn,rep_fn,null,null);
});

kaocha.cljs.cognitect.transit.write_handler.cljs$core$IFn$_invoke$arity$3 = (function (tag_fn,rep_fn,str_rep_fn){
return kaocha.cljs.cognitect.transit.write_handler.call(null,tag_fn,rep_fn,str_rep_fn,null);
});

kaocha.cljs.cognitect.transit.write_handler.cljs$core$IFn$_invoke$arity$4 = (function (tag_fn,rep_fn,str_rep_fn,verbose_handler_fn){
if((typeof kaocha !== 'undefined') && (typeof kaocha.cljs !== 'undefined') && (typeof kaocha.cljs.cognitect !== 'undefined') && (typeof kaocha.cljs.cognitect.transit !== 'undefined') && (typeof kaocha.cljs.cognitect.transit.t_kaocha$cljs$cognitect$transit31626 !== 'undefined')){
} else {

/**
* @constructor
 * @implements {cljs.core.IMeta}
 * @implements {kaocha.cljs.cognitect.transit.Object}
 * @implements {cljs.core.IWithMeta}
*/
kaocha.cljs.cognitect.transit.t_kaocha$cljs$cognitect$transit31626 = (function (tag_fn,rep_fn,str_rep_fn,verbose_handler_fn,meta31627){
this.tag_fn = tag_fn;
this.rep_fn = rep_fn;
this.str_rep_fn = str_rep_fn;
this.verbose_handler_fn = verbose_handler_fn;
this.meta31627 = meta31627;
this.cljs$lang$protocol_mask$partition0$ = 393216;
this.cljs$lang$protocol_mask$partition1$ = 0;
});
kaocha.cljs.cognitect.transit.t_kaocha$cljs$cognitect$transit31626.prototype.cljs$core$IWithMeta$_with_meta$arity$2 = (function (_31628,meta31627__$1){
var self__ = this;
var _31628__$1 = this;
return (new kaocha.cljs.cognitect.transit.t_kaocha$cljs$cognitect$transit31626(self__.tag_fn,self__.rep_fn,self__.str_rep_fn,self__.verbose_handler_fn,meta31627__$1));
});

kaocha.cljs.cognitect.transit.t_kaocha$cljs$cognitect$transit31626.prototype.cljs$core$IMeta$_meta$arity$1 = (function (_31628){
var self__ = this;
var _31628__$1 = this;
return self__.meta31627;
});

kaocha.cljs.cognitect.transit.t_kaocha$cljs$cognitect$transit31626.prototype.tag = (function (o){
var self__ = this;
var _ = this;
return self__.tag_fn.call(null,o);
});

kaocha.cljs.cognitect.transit.t_kaocha$cljs$cognitect$transit31626.prototype.rep = (function (o){
var self__ = this;
var _ = this;
return self__.rep_fn.call(null,o);
});

kaocha.cljs.cognitect.transit.t_kaocha$cljs$cognitect$transit31626.prototype.stringRep = (function (o){
var self__ = this;
var _ = this;
if(cljs.core.truth_(self__.str_rep_fn)){
return self__.str_rep_fn.call(null,o);
} else {
return null;
}
});

kaocha.cljs.cognitect.transit.t_kaocha$cljs$cognitect$transit31626.prototype.getVerboseHandler = (function (){
var self__ = this;
var _ = this;
if(cljs.core.truth_(self__.verbose_handler_fn)){
return self__.verbose_handler_fn.call(null);
} else {
return null;
}
});

kaocha.cljs.cognitect.transit.t_kaocha$cljs$cognitect$transit31626.getBasis = (function (){
return new cljs.core.PersistentVector(null, 5, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol(null,"tag-fn","tag-fn",242055482,null),new cljs.core.Symbol(null,"rep-fn","rep-fn",-1724891035,null),new cljs.core.Symbol(null,"str-rep-fn","str-rep-fn",-1179615016,null),new cljs.core.Symbol(null,"verbose-handler-fn","verbose-handler-fn",547340594,null),new cljs.core.Symbol(null,"meta31627","meta31627",-1677181448,null)], null);
});

kaocha.cljs.cognitect.transit.t_kaocha$cljs$cognitect$transit31626.cljs$lang$type = true;

kaocha.cljs.cognitect.transit.t_kaocha$cljs$cognitect$transit31626.cljs$lang$ctorStr = "kaocha.cljs.cognitect.transit/t_kaocha$cljs$cognitect$transit31626";

kaocha.cljs.cognitect.transit.t_kaocha$cljs$cognitect$transit31626.cljs$lang$ctorPrWriter = (function (this__4374__auto__,writer__4375__auto__,opt__4376__auto__){
return cljs.core._write.call(null,writer__4375__auto__,"kaocha.cljs.cognitect.transit/t_kaocha$cljs$cognitect$transit31626");
});

/**
 * Positional factory function for kaocha.cljs.cognitect.transit/t_kaocha$cljs$cognitect$transit31626.
 */
kaocha.cljs.cognitect.transit.__GT_t_kaocha$cljs$cognitect$transit31626 = (function kaocha$cljs$cognitect$transit$__GT_t_kaocha$cljs$cognitect$transit31626(tag_fn__$1,rep_fn__$1,str_rep_fn__$1,verbose_handler_fn__$1,meta31627){
return (new kaocha.cljs.cognitect.transit.t_kaocha$cljs$cognitect$transit31626(tag_fn__$1,rep_fn__$1,str_rep_fn__$1,verbose_handler_fn__$1,meta31627));
});

}

return (new kaocha.cljs.cognitect.transit.t_kaocha$cljs$cognitect$transit31626(tag_fn,rep_fn,str_rep_fn,verbose_handler_fn,cljs.core.PersistentArrayMap.EMPTY));
});

kaocha.cljs.cognitect.transit.write_handler.cljs$lang$maxFixedArity = 4;

/**
 * Construct a tagged value. tag must be a string and rep can
 * be any transit encodeable value.
 */
kaocha.cljs.cognitect.transit.tagged_value = (function kaocha$cljs$cognitect$transit$tagged_value(tag,rep){
return com.cognitect.transit.types.taggedValue(tag,rep);
});
/**
 * Returns true if x is a transit tagged value, false otherwise.
 */
kaocha.cljs.cognitect.transit.tagged_value_QMARK_ = (function kaocha$cljs$cognitect$transit$tagged_value_QMARK_(x){
return com.cognitect.transit.types.isTaggedValue(x);
});
/**
 * Construct a transit integer value. Returns JavaScript number if
 *   in the 53bit integer range, a goog.math.Long instance if above. s
 *   may be a string or a JavaScript number.
 */
kaocha.cljs.cognitect.transit.integer = (function kaocha$cljs$cognitect$transit$integer(s){
return com.cognitect.transit.types.intValue(s);
});
/**
 * Returns true if x is an integer value between the 53bit and 64bit
 *   range, false otherwise.
 */
kaocha.cljs.cognitect.transit.integer_QMARK_ = (function kaocha$cljs$cognitect$transit$integer_QMARK_(x){
return com.cognitect.transit.types.isInteger(x);
});
/**
 * Construct a big integer from a string.
 */
kaocha.cljs.cognitect.transit.bigint = (function kaocha$cljs$cognitect$transit$bigint(s){
return com.cognitect.transit.types.bigInteger(s);
});
/**
 * Returns true if x is a transit big integer value, false otherwise.
 */
kaocha.cljs.cognitect.transit.bigint_QMARK_ = (function kaocha$cljs$cognitect$transit$bigint_QMARK_(x){
return com.cognitect.transit.types.isBigInteger(x);
});
/**
 * Construct a big decimal from a string.
 */
kaocha.cljs.cognitect.transit.bigdec = (function kaocha$cljs$cognitect$transit$bigdec(s){
return com.cognitect.transit.types.bigDecimalValue(s);
});
/**
 * Returns true if x is a transit big decimal value, false otherwise.
 */
kaocha.cljs.cognitect.transit.bigdec_QMARK_ = (function kaocha$cljs$cognitect$transit$bigdec_QMARK_(x){
return com.cognitect.transit.types.isBigDecimal(x);
});
/**
 * Construct a URI from a string.
 */
kaocha.cljs.cognitect.transit.uri = (function kaocha$cljs$cognitect$transit$uri(s){
return com.cognitect.transit.types.uri(s);
});
/**
 * Returns true if x is a transit URI value, false otherwise.
 */
kaocha.cljs.cognitect.transit.uri_QMARK_ = (function kaocha$cljs$cognitect$transit$uri_QMARK_(x){
return com.cognitect.transit.types.isURI(x);
});
/**
 * Construct a UUID from a string.
 */
kaocha.cljs.cognitect.transit.uuid = (function kaocha$cljs$cognitect$transit$uuid(s){
return com.cognitect.transit.types.uuid(s);
});
/**
 * Returns true if x is a transit UUID value, false otherwise.
 */
kaocha.cljs.cognitect.transit.uuid_QMARK_ = (function kaocha$cljs$cognitect$transit$uuid_QMARK_(x){
var or__4131__auto__ = com.cognitect.transit.types.isUUID(x);
if(cljs.core.truth_(or__4131__auto__)){
return or__4131__auto__;
} else {
return (x instanceof cljs.core.UUID);
}
});
/**
 * Construct a transit binary value. s should be base64 encoded
 * string.
 */
kaocha.cljs.cognitect.transit.binary = (function kaocha$cljs$cognitect$transit$binary(s){
return com.cognitect.transit.types.binary(s);
});
/**
 * Returns true if x is a transit binary value, false otherwise.
 */
kaocha.cljs.cognitect.transit.binary_QMARK_ = (function kaocha$cljs$cognitect$transit$binary_QMARK_(x){
return com.cognitect.transit.types.isBinary(x);
});
/**
 * Construct a quoted transit value. x should be a transit
 * encodeable value.
 */
kaocha.cljs.cognitect.transit.quoted = (function kaocha$cljs$cognitect$transit$quoted(x){
return com.cognitect.transit.types.quoted(x);
});
/**
 * Returns true if x is a transit quoted value, false otherwise.
 */
kaocha.cljs.cognitect.transit.quoted_QMARK_ = (function kaocha$cljs$cognitect$transit$quoted_QMARK_(x){
return com.cognitect.transit.types.isQuoted(x);
});
/**
 * Construct a transit link value. x should be an IMap instance
 * containing at a minimum the following keys: :href, :rel. It
 * may optionall include :name, :render, and :prompt. :href must
 * be a transit URI, all other values are strings, and :render must
 * be either :image or :link.
 */
kaocha.cljs.cognitect.transit.link = (function kaocha$cljs$cognitect$transit$link(x){
return com.cognitect.transit.types.link(x);
});
/**
 * Returns true if x a transit link value, false if otherwise.
 */
kaocha.cljs.cognitect.transit.link_QMARK_ = (function kaocha$cljs$cognitect$transit$link_QMARK_(x){
return com.cognitect.transit.types.isLink(x);
});
/**
 * For :transform. Will write any metadata present on the value.
 */
kaocha.cljs.cognitect.transit.write_meta = (function kaocha$cljs$cognitect$transit$write_meta(x){
if((((!((x == null))))?(((((x.cljs$lang$protocol_mask$partition0$ & (131072))) || ((cljs.core.PROTOCOL_SENTINEL === x.cljs$core$IMeta$))))?true:false):false)){
var m = cljs.core._meta.call(null,x);
if((!((m == null)))){
return (new kaocha.cljs.cognitect.transit.WithMeta(cljs.core._with_meta.call(null,x,null),m));
} else {
return x;
}
} else {
return x;
}
});

//# sourceMappingURL=transit.js.map
