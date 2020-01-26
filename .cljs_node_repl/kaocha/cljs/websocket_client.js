// Compiled by ClojureScript 1.10.520 {:target :nodejs}
goog.provide('kaocha.cljs.websocket_client');
goog.require('cljs.core');
goog.require('kaocha.cljs.cognitect.transit');
goog.require('kaocha.cljs.websocket');
goog.require('kaocha.type.cljs');
goog.require('pjstadig.print');
goog.require('cljs.pprint');
goog.require('cljs.test');
goog.require('clojure.string');
goog.require('goog.dom');
goog.require('goog.log');
goog.require('goog.object');
goog.require('lambdaisland.glogi');
goog.require('clojure.browser.repl');
goog.require('goog.string.StringBuffer');
lambdaisland.glogi.set_level.call(null,cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core._STAR_ns_STAR_),cljs.core.keyword.call(null,clojure.string.lower_case.call(null,kaocha.type.cljs.log_level)));
kaocha.cljs.websocket_client.socket = null;
kaocha.cljs.websocket_client.record_handler = (function kaocha$cljs$websocket_client$record_handler(type){
return kaocha.cljs.cognitect.transit.write_handler.call(null,cljs.core.constantly.call(null,type),(function (val){
return cljs.core.into.call(null,cljs.core.PersistentArrayMap.EMPTY,val);
}));
});
kaocha.cljs.websocket_client.transit_handlers = cljs.core.merge.call(null,cljs.core.PersistentArrayMap.createAsIfByAssoc([new cljs.core.Keyword(null,"default","default",-1987822328),kaocha.cljs.cognitect.transit.write_handler.call(null,(function (o){
return cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.type.call(null,o));
}),(function (o){
return cljs.core.str.cljs$core$IFn$_invoke$arity$1(o);
})),cljs.core.Var,kaocha.cljs.cognitect.transit.write_handler.call(null,cljs.core.constantly.call(null,"var"),(function (rep){
return cljs.core.meta.call(null,rep);
}))]),(((typeof matcher_combinators !== 'undefined') && (typeof matcher_combinators.model !== 'undefined') && (typeof matcher_combinators.model.Mismatch !== 'undefined'))?cljs.core.PersistentArrayMap.createAsIfByAssoc([matcher_combinators.model.Mismatch,kaocha.cljs.websocket_client.record_handler.call(null,"matcher-combinators.model.Mismatch")]):null),(((typeof matcher_combinators !== 'undefined') && (typeof matcher_combinators.model !== 'undefined') && (typeof matcher_combinators.model.Missing !== 'undefined'))?cljs.core.PersistentArrayMap.createAsIfByAssoc([matcher_combinators.model.Missing,kaocha.cljs.websocket_client.record_handler.call(null,"matcher-combinators.model.Missing")]):null),(((typeof matcher_combinators !== 'undefined') && (typeof matcher_combinators.model !== 'undefined') && (typeof matcher_combinators.model.Unexpected !== 'undefined'))?cljs.core.PersistentArrayMap.createAsIfByAssoc([matcher_combinators.model.Unexpected,kaocha.cljs.websocket_client.record_handler.call(null,"matcher-combinators.model.Unexpected")]):null),(((typeof matcher_combinators !== 'undefined') && (typeof matcher_combinators.model !== 'undefined') && (typeof matcher_combinators.model.InvalidMatcherType !== 'undefined'))?cljs.core.PersistentArrayMap.createAsIfByAssoc([matcher_combinators.model.InvalidMatcherType,kaocha.cljs.websocket_client.record_handler.call(null,"matcher-combinators.model.InvalidMatcherType")]):null),(((typeof matcher_combinators !== 'undefined') && (typeof matcher_combinators.model !== 'undefined') && (typeof matcher_combinators.model.InvalidMatcherContext !== 'undefined'))?cljs.core.PersistentArrayMap.createAsIfByAssoc([matcher_combinators.model.InvalidMatcherContext,kaocha.cljs.websocket_client.record_handler.call(null,"matcher-combinators.model.InvalidMatcherContext")]):null),(((typeof matcher_combinators !== 'undefined') && (typeof matcher_combinators.model !== 'undefined') && (typeof matcher_combinators.model.FailedPredicate !== 'undefined'))?cljs.core.PersistentArrayMap.createAsIfByAssoc([matcher_combinators.model.FailedPredicate,kaocha.cljs.websocket_client.record_handler.call(null,"matcher-combinators.model.FailedPredicate")]):null),(((typeof matcher_combinators !== 'undefined') && (typeof matcher_combinators.model !== 'undefined') && (typeof matcher_combinators.model.TypeMismatch !== 'undefined'))?cljs.core.PersistentArrayMap.createAsIfByAssoc([matcher_combinators.model.TypeMismatch,kaocha.cljs.websocket_client.record_handler.call(null,"matcher-combinators.model.TypeMismatch")]):null));
kaocha.cljs.websocket_client.transit_writer = kaocha.cljs.cognitect.transit.writer.call(null,new cljs.core.Keyword(null,"json","json",1279968570),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"handlers","handlers",79528781),kaocha.cljs.websocket_client.transit_handlers], null));
kaocha.cljs.websocket_client.to_transit = (function kaocha$cljs$websocket_client$to_transit(value){
return kaocha.cljs.cognitect.transit.write.call(null,kaocha.cljs.websocket_client.transit_writer,value);
});
kaocha.cljs.websocket_client.from_transit = (function kaocha$cljs$websocket_client$from_transit(string){
return kaocha.cljs.cognitect.transit.read.call(null,kaocha.cljs.cognitect.transit.reader.call(null,new cljs.core.Keyword(null,"json","json",1279968570)),string);
});
kaocha.cljs.websocket_client.send_BANG_ = (function kaocha$cljs$websocket_client$send_BANG_(message){
if(cljs.core.truth_(kaocha.cljs.websocket.open_QMARK_.call(null,kaocha.cljs.websocket_client.socket))){
} else {
throw (new Error("Assert failed: (ws/open? socket)"));
}

lambdaisland.glogi.log.call(null,"kaocha.cljs.websocket-client",new cljs.core.Keyword(null,"debug","debug",-1608172596),cljs.core.identity.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword("websocket","send","websocket/send",-946873729),message,new cljs.core.Keyword(null,"line","line",212345235),71], null)),null);

if(cljs.core.truth_(kaocha.cljs.websocket.open_QMARK_.call(null,kaocha.cljs.websocket_client.socket))){
return kaocha.cljs.websocket.send_BANG_.call(null,kaocha.cljs.websocket_client.socket,kaocha.cljs.websocket_client.to_transit.call(null,message));
} else {
return null;
}
});
kaocha.cljs.websocket_client.pretty_print_failure = (function kaocha$cljs$websocket_client$pretty_print_failure(m){
var buffer = (new goog.string.StringBuffer());
var _STAR_sb_STAR__orig_val__32884 = pjstadig.print._STAR_sb_STAR_;
var _STAR_out_STAR__orig_val__32885 = cljs.core._STAR_out_STAR_;
var _STAR_sb_STAR__temp_val__32886 = buffer;
var _STAR_out_STAR__temp_val__32887 = cljs.pprint.get_pretty_writer.call(null,(new cljs.core.StringBufferWriter(buffer)));
pjstadig.print._STAR_sb_STAR_ = _STAR_sb_STAR__temp_val__32886;

cljs.core._STAR_out_STAR_ = _STAR_out_STAR__temp_val__32887;

try{var map__32888_32918 = pjstadig.print.convert_event.call(null,m);
var map__32888_32919__$1 = (((((!((map__32888_32918 == null))))?(((((map__32888_32918.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__32888_32918.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__32888_32918):map__32888_32918);
var event_32920 = map__32888_32919__$1;
var type_32921 = cljs.core.get.call(null,map__32888_32919__$1,new cljs.core.Keyword(null,"type","type",1174270348));
var expected_32922 = cljs.core.get.call(null,map__32888_32919__$1,new cljs.core.Keyword(null,"expected","expected",1583670997));
var actual_32923 = cljs.core.get.call(null,map__32888_32919__$1,new cljs.core.Keyword(null,"actual","actual",107306363));
var diffs_32924 = cljs.core.get.call(null,map__32888_32919__$1,new cljs.core.Keyword(null,"diffs","diffs",-1720136241));
var message_32925 = cljs.core.get.call(null,map__32888_32919__$1,new cljs.core.Keyword(null,"message","message",-406056002));
var print_expected_32926 = ((function (map__32888_32918,map__32888_32919__$1,event_32920,type_32921,expected_32922,actual_32923,diffs_32924,message_32925,_STAR_sb_STAR__orig_val__32884,_STAR_out_STAR__orig_val__32885,_STAR_sb_STAR__temp_val__32886,_STAR_out_STAR__temp_val__32887,buffer){
return (function (actual__$1){
pjstadig.print.rprint.call(null,"Expected:\n  ");

cljs.pprint.pprint.call(null,expected_32922,cljs.core._STAR_out_STAR_);

pjstadig.print.rprint.call(null,"Actual:\n  ");

return cljs.pprint.pprint.call(null,actual__$1,cljs.core._STAR_out_STAR_);
});})(map__32888_32918,map__32888_32919__$1,event_32920,type_32921,expected_32922,actual_32923,diffs_32924,message_32925,_STAR_sb_STAR__orig_val__32884,_STAR_out_STAR__orig_val__32885,_STAR_sb_STAR__temp_val__32886,_STAR_out_STAR__temp_val__32887,buffer))
;
if(cljs.core.seq.call(null,diffs_32924)){
var seq__32890_32927 = cljs.core.seq.call(null,diffs_32924);
var chunk__32891_32928 = null;
var count__32892_32929 = (0);
var i__32893_32930 = (0);
while(true){
if((i__32893_32930 < count__32892_32929)){
var vec__32906_32931 = cljs.core._nth.call(null,chunk__32891_32928,i__32893_32930);
var actual_32932__$1 = cljs.core.nth.call(null,vec__32906_32931,(0),null);
var vec__32909_32933 = cljs.core.nth.call(null,vec__32906_32931,(1),null);
var a_32934 = cljs.core.nth.call(null,vec__32909_32933,(0),null);
var b_32935 = cljs.core.nth.call(null,vec__32909_32933,(1),null);
print_expected_32926.call(null,actual_32932__$1);

pjstadig.print.rprint.call(null,"Diff:\n  ");

if(cljs.core.truth_(a_32934)){
pjstadig.print.rprint.call(null,"- ");

cljs.pprint.pprint.call(null,a_32934,cljs.core._STAR_out_STAR_);

pjstadig.print.rprint.call(null,"  + ");
} else {
pjstadig.print.rprint.call(null,"+ ");
}

if(cljs.core.truth_(b_32935)){
cljs.pprint.pprint.call(null,b_32935,cljs.core._STAR_out_STAR_);
} else {
}


var G__32936 = seq__32890_32927;
var G__32937 = chunk__32891_32928;
var G__32938 = count__32892_32929;
var G__32939 = (i__32893_32930 + (1));
seq__32890_32927 = G__32936;
chunk__32891_32928 = G__32937;
count__32892_32929 = G__32938;
i__32893_32930 = G__32939;
continue;
} else {
var temp__5720__auto___32940 = cljs.core.seq.call(null,seq__32890_32927);
if(temp__5720__auto___32940){
var seq__32890_32941__$1 = temp__5720__auto___32940;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__32890_32941__$1)){
var c__4550__auto___32942 = cljs.core.chunk_first.call(null,seq__32890_32941__$1);
var G__32943 = cljs.core.chunk_rest.call(null,seq__32890_32941__$1);
var G__32944 = c__4550__auto___32942;
var G__32945 = cljs.core.count.call(null,c__4550__auto___32942);
var G__32946 = (0);
seq__32890_32927 = G__32943;
chunk__32891_32928 = G__32944;
count__32892_32929 = G__32945;
i__32893_32930 = G__32946;
continue;
} else {
var vec__32912_32947 = cljs.core.first.call(null,seq__32890_32941__$1);
var actual_32948__$1 = cljs.core.nth.call(null,vec__32912_32947,(0),null);
var vec__32915_32949 = cljs.core.nth.call(null,vec__32912_32947,(1),null);
var a_32950 = cljs.core.nth.call(null,vec__32915_32949,(0),null);
var b_32951 = cljs.core.nth.call(null,vec__32915_32949,(1),null);
print_expected_32926.call(null,actual_32948__$1);

pjstadig.print.rprint.call(null,"Diff:\n  ");

if(cljs.core.truth_(a_32950)){
pjstadig.print.rprint.call(null,"- ");

cljs.pprint.pprint.call(null,a_32950,cljs.core._STAR_out_STAR_);

pjstadig.print.rprint.call(null,"  + ");
} else {
pjstadig.print.rprint.call(null,"+ ");
}

if(cljs.core.truth_(b_32951)){
cljs.pprint.pprint.call(null,b_32951,cljs.core._STAR_out_STAR_);
} else {
}


var G__32952 = cljs.core.next.call(null,seq__32890_32941__$1);
var G__32953 = null;
var G__32954 = (0);
var G__32955 = (0);
seq__32890_32927 = G__32952;
chunk__32891_32928 = G__32953;
count__32892_32929 = G__32954;
i__32893_32930 = G__32955;
continue;
}
} else {
}
}
break;
}
} else {
print_expected_32926.call(null,actual_32923);
}

return cljs.core.str.cljs$core$IFn$_invoke$arity$1(pjstadig.print._STAR_sb_STAR_);
}finally {cljs.core._STAR_out_STAR_ = _STAR_out_STAR__orig_val__32885;

pjstadig.print._STAR_sb_STAR_ = _STAR_sb_STAR__orig_val__32884;
}});
kaocha.cljs.websocket_client.cljs_test_msg = (function kaocha$cljs$websocket_client$cljs_test_msg(m){
return new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"type","type",1174270348),new cljs.core.Keyword("cljs.test","message","cljs.test/message",805969520),new cljs.core.Keyword("cljs.test","message","cljs.test/message",805969520),m,new cljs.core.Keyword("cljs.test","testing-contexts","cljs.test/testing-contexts",-805796937),new cljs.core.Keyword(null,"testing-contexts","testing-contexts",-1485646523).cljs$core$IFn$_invoke$arity$1(cljs.test.get_current_env.call(null))], null);
});
cljs.core._add_method.call(null,cljs.test.report,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword("kaocha.type","cljs","kaocha.type/cljs",-1078165948),new cljs.core.Keyword("kaocha.cljs.websocket-client","propagate","kaocha.cljs.websocket-client/propagate",355706059)], null),(function (m){
return kaocha.cljs.websocket_client.send_BANG_.call(null,kaocha.cljs.websocket_client.cljs_test_msg.call(null,m));
}));
cljs.core._add_method.call(null,cljs.test.report,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword("kaocha.type","cljs","kaocha.type/cljs",-1078165948),new cljs.core.Keyword(null,"fail","fail",1706214930)], null),(function (m){
return kaocha.cljs.websocket_client.send_BANG_.call(null,kaocha.cljs.websocket_client.cljs_test_msg.call(null,cljs.core.assoc.call(null,m,new cljs.core.Keyword("kaocha.report","printed-expression","kaocha.report/printed-expression",219822455),kaocha.cljs.websocket_client.pretty_print_failure.call(null,m))));
}));
cljs.core._add_method.call(null,cljs.test.report,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword("kaocha.type","cljs","kaocha.type/cljs",-1078165948),new cljs.core.Keyword(null,"error","error",-978969032)], null),(function (m){
var error = new cljs.core.Keyword(null,"actual","actual",107306363).cljs$core$IFn$_invoke$arity$1(m);
var stacktrace = new cljs.core.Keyword(null,"actual","actual",107306363).cljs$core$IFn$_invoke$arity$1(m).stack;
return kaocha.cljs.websocket_client.send_BANG_.call(null,kaocha.cljs.websocket_client.cljs_test_msg.call(null,cljs.core.assoc.call(null,m,new cljs.core.Keyword("kaocha.report","printed-expression","kaocha.report/printed-expression",219822455),[cljs.core.str.cljs$core$IFn$_invoke$arity$1(clojure.string.trim.call(null,stacktrace)),"\n"].join(''),new cljs.core.Keyword("kaocha.report","error-type","kaocha.report/error-type",2106778117),["js/",cljs.core.str.cljs$core$IFn$_invoke$arity$1(error.name)].join(''),new cljs.core.Keyword(null,"message","message",-406056002),(function (){var or__4131__auto__ = new cljs.core.Keyword(null,"message","message",-406056002).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(or__4131__auto__)){
return or__4131__auto__;
} else {
return error.message;
}
})())));
}));
var seq__32956_32960 = cljs.core.seq.call(null,new cljs.core.PersistentHashSet(null, new cljs.core.PersistentArrayMap(null, 26, [new cljs.core.Keyword("kaocha","end-group","kaocha/end-group",41506019),null,new cljs.core.Keyword("kaocha","end-test","kaocha/end-test",-1055212092),null,new cljs.core.Keyword(null,"begin-test-var","begin-test-var",-908571100),null,new cljs.core.Keyword("kaocha","begin-var","kaocha/begin-var",-1999391545),null,new cljs.core.Keyword("kaocha","end-var","kaocha/end-var",-1961823800),null,new cljs.core.Keyword("kaocha.report","one-arg-eql","kaocha.report/one-arg-eql",-871229912),null,new cljs.core.Keyword("kaocha","begin-test","kaocha/begin-test",-1593529975),null,new cljs.core.Keyword("kaocha.type.var","zero-assertions","kaocha.type.var/zero-assertions",623758603),null,new cljs.core.Keyword("kaocha","pending","kaocha/pending",-1367197266),null,new cljs.core.Keyword(null,"summary","summary",380847952),null,new cljs.core.Keyword(null,"begin-test-suite","begin-test-suite",461205456),null,new cljs.core.Keyword(null,"end-test-suite","end-test-suite",1577385105),null,new cljs.core.Keyword(null,"mismatch","mismatch",103313617),null,new cljs.core.Keyword(null,"end-test-var","end-test-var",984198545),null,new cljs.core.Keyword(null,"fail","fail",1706214930),null,new cljs.core.Keyword("kaocha","deferred","kaocha/deferred",1305043379),null,new cljs.core.Keyword(null,"begin-test-all-vars","begin-test-all-vars",124024339),null,new cljs.core.Keyword(null,"end-run-tests","end-run-tests",267300563),null,new cljs.core.Keyword(null,"end-test-all-vars","end-test-all-vars",548827253),null,new cljs.core.Keyword("matcher-combinators","mismatch","matcher-combinators/mismatch",-1628341451),null,new cljs.core.Keyword(null,"begin-run-tests","begin-run-tests",309363062),null,new cljs.core.Keyword(null,"begin-test-ns","begin-test-ns",-1701237033),null,new cljs.core.Keyword(null,"error","error",-978969032),null,new cljs.core.Keyword(null,"pass","pass",1574159993),null,new cljs.core.Keyword("kaocha","begin-group","kaocha/begin-group",-1094197350),null,new cljs.core.Keyword(null,"end-test-ns","end-test-ns",1620675645),null], null), null));
var chunk__32957_32961 = null;
var count__32958_32962 = (0);
var i__32959_32963 = (0);
while(true){
if((i__32959_32963 < count__32958_32962)){
var t_32964 = cljs.core._nth.call(null,chunk__32957_32961,i__32959_32963);
cljs.core.derive.call(null,t_32964,new cljs.core.Keyword("kaocha.cljs.websocket-client","propagate","kaocha.cljs.websocket-client/propagate",355706059));


var G__32965 = seq__32956_32960;
var G__32966 = chunk__32957_32961;
var G__32967 = count__32958_32962;
var G__32968 = (i__32959_32963 + (1));
seq__32956_32960 = G__32965;
chunk__32957_32961 = G__32966;
count__32958_32962 = G__32967;
i__32959_32963 = G__32968;
continue;
} else {
var temp__5720__auto___32969 = cljs.core.seq.call(null,seq__32956_32960);
if(temp__5720__auto___32969){
var seq__32956_32970__$1 = temp__5720__auto___32969;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__32956_32970__$1)){
var c__4550__auto___32971 = cljs.core.chunk_first.call(null,seq__32956_32970__$1);
var G__32972 = cljs.core.chunk_rest.call(null,seq__32956_32970__$1);
var G__32973 = c__4550__auto___32971;
var G__32974 = cljs.core.count.call(null,c__4550__auto___32971);
var G__32975 = (0);
seq__32956_32960 = G__32972;
chunk__32957_32961 = G__32973;
count__32958_32962 = G__32974;
i__32959_32963 = G__32975;
continue;
} else {
var t_32976 = cljs.core.first.call(null,seq__32956_32970__$1);
cljs.core.derive.call(null,t_32976,new cljs.core.Keyword("kaocha.cljs.websocket-client","propagate","kaocha.cljs.websocket-client/propagate",355706059));


var G__32977 = cljs.core.next.call(null,seq__32956_32970__$1);
var G__32978 = null;
var G__32979 = (0);
var G__32980 = (0);
seq__32956_32960 = G__32977;
chunk__32957_32961 = G__32978;
count__32958_32962 = G__32979;
i__32959_32963 = G__32980;
continue;
}
} else {
}
}
break;
}
cljs.test.update_current_env_BANG_.call(null,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"reporter","reporter",-805360621)], null),cljs.core.constantly.call(null,new cljs.core.Keyword("kaocha.type","cljs","kaocha.type/cljs",-1078165948)));
kaocha.cljs.websocket_client.connect_BANG_ = (function kaocha$cljs$websocket_client$connect_BANG_(port){
return kaocha.cljs.websocket_client.socket = kaocha.cljs.websocket.connect_BANG_.call(null,["ws://localhost:",cljs.core.str.cljs$core$IFn$_invoke$arity$1(port),"/"].join(''),new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"open","open",-1763596448),(function (e){
lambdaisland.glogi.log.call(null,"kaocha.cljs.websocket-client",new cljs.core.Keyword(null,"info","info",-317069002),cljs.core.identity.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"websocket","websocket",-1714963101),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"callback","callback",-705136228),new cljs.core.Keyword(null,"onopen","onopen",1095722859),new cljs.core.Keyword(null,"event","event",301435442),e], null),new cljs.core.Keyword(null,"line","line",212345235),136], null)),null);

return kaocha.cljs.websocket_client.send_BANG_.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"type","type",1174270348),new cljs.core.Keyword("kaocha.cljs.websocket-client","connected","kaocha.cljs.websocket-client/connected",-130444887),new cljs.core.Keyword(null,"browser?","browser?",-195634801),(typeof document !== 'undefined')], null));
}),new cljs.core.Keyword(null,"error","error",-978969032),(function (e){
lambdaisland.glogi.log.call(null,"kaocha.cljs.websocket-client",new cljs.core.Keyword(null,"info","info",-317069002),cljs.core.identity.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"websocket","websocket",-1714963101),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"callback","callback",-705136228),new cljs.core.Keyword(null,"onerror","onerror",781725030),new cljs.core.Keyword(null,"event","event",301435442),e], null),new cljs.core.Keyword(null,"line","line",212345235),142], null)),null);

return cljs.core.prn.call(null,new cljs.core.Keyword(null,"error","error",-978969032),e);
}),new cljs.core.Keyword(null,"message","message",-406056002),(function (e){
lambdaisland.glogi.log.call(null,"kaocha.cljs.websocket-client",new cljs.core.Keyword(null,"info","info",-317069002),cljs.core.identity.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"websocket","websocket",-1714963101),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"callback","callback",-705136228),new cljs.core.Keyword(null,"onmessage","onmessage",-246468312),new cljs.core.Keyword(null,"event","event",301435442),e], null),new cljs.core.Keyword(null,"line","line",212345235),147], null)),null);

return cljs.core.prn.call(null,new cljs.core.Keyword(null,"message","message",-406056002),kaocha.cljs.websocket_client.from_transit.call(null,kaocha.cljs.websocket.message_data.call(null,e)));
}),new cljs.core.Keyword(null,"close","close",1835149582),(function (e){
lambdaisland.glogi.log.call(null,"kaocha.cljs.websocket-client",new cljs.core.Keyword(null,"info","info",-317069002),cljs.core.identity.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"websocket","websocket",-1714963101),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"callback","callback",-705136228),new cljs.core.Keyword(null,"onclose","onclose",-1780819312),new cljs.core.Keyword(null,"event","event",301435442),e], null),new cljs.core.Keyword(null,"line","line",212345235),152], null)),null);

return cljs.core.prn.call(null,new cljs.core.Keyword(null,"close","close",1835149582),e);
})], null));
});
kaocha.cljs.websocket_client.disconnect_BANG_ = (function kaocha$cljs$websocket_client$disconnect_BANG_(){
if(cljs.core.truth_(kaocha.cljs.websocket_client.socket)){
lambdaisland.glogi.log.call(null,"kaocha.cljs.websocket-client",new cljs.core.Keyword(null,"info","info",-317069002),cljs.core.identity.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"msg","msg",-1386103444),"Disconnecting websocket",new cljs.core.Keyword(null,"line","line",212345235),157], null)),null);

return kaocha.cljs.websocket.close_BANG_.call(null,kaocha.cljs.websocket_client.socket);
} else {
return null;
}
});

//# sourceMappingURL=websocket_client.js.map
