// Compiled by ClojureScript 1.10.520 {:target :nodejs}
goog.provide('cljs.repl');
goog.require('cljs.core');
goog.require('cljs.spec.alpha');
goog.require('goog.string');
goog.require('goog.string.format');
cljs.repl.print_doc = (function cljs$repl$print_doc(p__32556){
var map__32557 = p__32556;
var map__32557__$1 = (((((!((map__32557 == null))))?(((((map__32557.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__32557.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__32557):map__32557);
var m = map__32557__$1;
var n = cljs.core.get.call(null,map__32557__$1,new cljs.core.Keyword(null,"ns","ns",441598760));
var nm = cljs.core.get.call(null,map__32557__$1,new cljs.core.Keyword(null,"name","name",1843675177));
cljs.core.println.call(null,"-------------------------");

cljs.core.println.call(null,(function (){var or__4131__auto__ = new cljs.core.Keyword(null,"spec","spec",347520401).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(or__4131__auto__)){
return or__4131__auto__;
} else {
return [(function (){var temp__5720__auto__ = new cljs.core.Keyword(null,"ns","ns",441598760).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(temp__5720__auto__)){
var ns = temp__5720__auto__;
return [cljs.core.str.cljs$core$IFn$_invoke$arity$1(ns),"/"].join('');
} else {
return null;
}
})(),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"name","name",1843675177).cljs$core$IFn$_invoke$arity$1(m))].join('');
}
})());

if(cljs.core.truth_(new cljs.core.Keyword(null,"protocol","protocol",652470118).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Protocol");
} else {
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m))){
var seq__32559_32591 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m));
var chunk__32560_32592 = null;
var count__32561_32593 = (0);
var i__32562_32594 = (0);
while(true){
if((i__32562_32594 < count__32561_32593)){
var f_32595 = cljs.core._nth.call(null,chunk__32560_32592,i__32562_32594);
cljs.core.println.call(null,"  ",f_32595);


var G__32596 = seq__32559_32591;
var G__32597 = chunk__32560_32592;
var G__32598 = count__32561_32593;
var G__32599 = (i__32562_32594 + (1));
seq__32559_32591 = G__32596;
chunk__32560_32592 = G__32597;
count__32561_32593 = G__32598;
i__32562_32594 = G__32599;
continue;
} else {
var temp__5720__auto___32600 = cljs.core.seq.call(null,seq__32559_32591);
if(temp__5720__auto___32600){
var seq__32559_32601__$1 = temp__5720__auto___32600;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__32559_32601__$1)){
var c__4550__auto___32602 = cljs.core.chunk_first.call(null,seq__32559_32601__$1);
var G__32603 = cljs.core.chunk_rest.call(null,seq__32559_32601__$1);
var G__32604 = c__4550__auto___32602;
var G__32605 = cljs.core.count.call(null,c__4550__auto___32602);
var G__32606 = (0);
seq__32559_32591 = G__32603;
chunk__32560_32592 = G__32604;
count__32561_32593 = G__32605;
i__32562_32594 = G__32606;
continue;
} else {
var f_32607 = cljs.core.first.call(null,seq__32559_32601__$1);
cljs.core.println.call(null,"  ",f_32607);


var G__32608 = cljs.core.next.call(null,seq__32559_32601__$1);
var G__32609 = null;
var G__32610 = (0);
var G__32611 = (0);
seq__32559_32591 = G__32608;
chunk__32560_32592 = G__32609;
count__32561_32593 = G__32610;
i__32562_32594 = G__32611;
continue;
}
} else {
}
}
break;
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m))){
var arglists_32612 = new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_((function (){var or__4131__auto__ = new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(or__4131__auto__)){
return or__4131__auto__;
} else {
return new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m);
}
})())){
cljs.core.prn.call(null,arglists_32612);
} else {
cljs.core.prn.call(null,((cljs.core._EQ_.call(null,new cljs.core.Symbol(null,"quote","quote",1377916282,null),cljs.core.first.call(null,arglists_32612)))?cljs.core.second.call(null,arglists_32612):arglists_32612));
}
} else {
}
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"special-form","special-form",-1326536374).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Special Form");

cljs.core.println.call(null," ",new cljs.core.Keyword(null,"doc","doc",1913296891).cljs$core$IFn$_invoke$arity$1(m));

if(cljs.core.contains_QMARK_.call(null,m,new cljs.core.Keyword(null,"url","url",276297046))){
if(cljs.core.truth_(new cljs.core.Keyword(null,"url","url",276297046).cljs$core$IFn$_invoke$arity$1(m))){
return cljs.core.println.call(null,["\n  Please see http://clojure.org/",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"url","url",276297046).cljs$core$IFn$_invoke$arity$1(m))].join(''));
} else {
return null;
}
} else {
return cljs.core.println.call(null,["\n  Please see http://clojure.org/special_forms#",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"name","name",1843675177).cljs$core$IFn$_invoke$arity$1(m))].join(''));
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Macro");
} else {
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"spec","spec",347520401).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Spec");
} else {
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"REPL Special Function");
} else {
}

cljs.core.println.call(null," ",new cljs.core.Keyword(null,"doc","doc",1913296891).cljs$core$IFn$_invoke$arity$1(m));

if(cljs.core.truth_(new cljs.core.Keyword(null,"protocol","protocol",652470118).cljs$core$IFn$_invoke$arity$1(m))){
var seq__32563_32613 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"methods","methods",453930866).cljs$core$IFn$_invoke$arity$1(m));
var chunk__32564_32614 = null;
var count__32565_32615 = (0);
var i__32566_32616 = (0);
while(true){
if((i__32566_32616 < count__32565_32615)){
var vec__32577_32617 = cljs.core._nth.call(null,chunk__32564_32614,i__32566_32616);
var name_32618 = cljs.core.nth.call(null,vec__32577_32617,(0),null);
var map__32580_32619 = cljs.core.nth.call(null,vec__32577_32617,(1),null);
var map__32580_32620__$1 = (((((!((map__32580_32619 == null))))?(((((map__32580_32619.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__32580_32619.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__32580_32619):map__32580_32619);
var doc_32621 = cljs.core.get.call(null,map__32580_32620__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists_32622 = cljs.core.get.call(null,map__32580_32620__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name_32618);

cljs.core.println.call(null," ",arglists_32622);

if(cljs.core.truth_(doc_32621)){
cljs.core.println.call(null," ",doc_32621);
} else {
}


var G__32623 = seq__32563_32613;
var G__32624 = chunk__32564_32614;
var G__32625 = count__32565_32615;
var G__32626 = (i__32566_32616 + (1));
seq__32563_32613 = G__32623;
chunk__32564_32614 = G__32624;
count__32565_32615 = G__32625;
i__32566_32616 = G__32626;
continue;
} else {
var temp__5720__auto___32627 = cljs.core.seq.call(null,seq__32563_32613);
if(temp__5720__auto___32627){
var seq__32563_32628__$1 = temp__5720__auto___32627;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__32563_32628__$1)){
var c__4550__auto___32629 = cljs.core.chunk_first.call(null,seq__32563_32628__$1);
var G__32630 = cljs.core.chunk_rest.call(null,seq__32563_32628__$1);
var G__32631 = c__4550__auto___32629;
var G__32632 = cljs.core.count.call(null,c__4550__auto___32629);
var G__32633 = (0);
seq__32563_32613 = G__32630;
chunk__32564_32614 = G__32631;
count__32565_32615 = G__32632;
i__32566_32616 = G__32633;
continue;
} else {
var vec__32582_32634 = cljs.core.first.call(null,seq__32563_32628__$1);
var name_32635 = cljs.core.nth.call(null,vec__32582_32634,(0),null);
var map__32585_32636 = cljs.core.nth.call(null,vec__32582_32634,(1),null);
var map__32585_32637__$1 = (((((!((map__32585_32636 == null))))?(((((map__32585_32636.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__32585_32636.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__32585_32636):map__32585_32636);
var doc_32638 = cljs.core.get.call(null,map__32585_32637__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists_32639 = cljs.core.get.call(null,map__32585_32637__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name_32635);

cljs.core.println.call(null," ",arglists_32639);

if(cljs.core.truth_(doc_32638)){
cljs.core.println.call(null," ",doc_32638);
} else {
}


var G__32640 = cljs.core.next.call(null,seq__32563_32628__$1);
var G__32641 = null;
var G__32642 = (0);
var G__32643 = (0);
seq__32563_32613 = G__32640;
chunk__32564_32614 = G__32641;
count__32565_32615 = G__32642;
i__32566_32616 = G__32643;
continue;
}
} else {
}
}
break;
}
} else {
}

if(cljs.core.truth_(n)){
var temp__5720__auto__ = cljs.spec.alpha.get_spec.call(null,cljs.core.symbol.call(null,cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.ns_name.call(null,n)),cljs.core.name.call(null,nm)));
if(cljs.core.truth_(temp__5720__auto__)){
var fnspec = temp__5720__auto__;
cljs.core.print.call(null,"Spec");

var seq__32587 = cljs.core.seq.call(null,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"args","args",1315556576),new cljs.core.Keyword(null,"ret","ret",-468222814),new cljs.core.Keyword(null,"fn","fn",-1175266204)], null));
var chunk__32588 = null;
var count__32589 = (0);
var i__32590 = (0);
while(true){
if((i__32590 < count__32589)){
var role = cljs.core._nth.call(null,chunk__32588,i__32590);
var temp__5720__auto___32644__$1 = cljs.core.get.call(null,fnspec,role);
if(cljs.core.truth_(temp__5720__auto___32644__$1)){
var spec_32645 = temp__5720__auto___32644__$1;
cljs.core.print.call(null,["\n ",cljs.core.name.call(null,role),":"].join(''),cljs.spec.alpha.describe.call(null,spec_32645));
} else {
}


var G__32646 = seq__32587;
var G__32647 = chunk__32588;
var G__32648 = count__32589;
var G__32649 = (i__32590 + (1));
seq__32587 = G__32646;
chunk__32588 = G__32647;
count__32589 = G__32648;
i__32590 = G__32649;
continue;
} else {
var temp__5720__auto____$1 = cljs.core.seq.call(null,seq__32587);
if(temp__5720__auto____$1){
var seq__32587__$1 = temp__5720__auto____$1;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__32587__$1)){
var c__4550__auto__ = cljs.core.chunk_first.call(null,seq__32587__$1);
var G__32650 = cljs.core.chunk_rest.call(null,seq__32587__$1);
var G__32651 = c__4550__auto__;
var G__32652 = cljs.core.count.call(null,c__4550__auto__);
var G__32653 = (0);
seq__32587 = G__32650;
chunk__32588 = G__32651;
count__32589 = G__32652;
i__32590 = G__32653;
continue;
} else {
var role = cljs.core.first.call(null,seq__32587__$1);
var temp__5720__auto___32654__$2 = cljs.core.get.call(null,fnspec,role);
if(cljs.core.truth_(temp__5720__auto___32654__$2)){
var spec_32655 = temp__5720__auto___32654__$2;
cljs.core.print.call(null,["\n ",cljs.core.name.call(null,role),":"].join(''),cljs.spec.alpha.describe.call(null,spec_32655));
} else {
}


var G__32656 = cljs.core.next.call(null,seq__32587__$1);
var G__32657 = null;
var G__32658 = (0);
var G__32659 = (0);
seq__32587 = G__32656;
chunk__32588 = G__32657;
count__32589 = G__32658;
i__32590 = G__32659;
continue;
}
} else {
return null;
}
}
break;
}
} else {
return null;
}
} else {
return null;
}
}
});
/**
 * Constructs a data representation for a Error with keys:
 *  :cause - root cause message
 *  :phase - error phase
 *  :via - cause chain, with cause keys:
 *           :type - exception class symbol
 *           :message - exception message
 *           :data - ex-data
 *           :at - top stack element
 *  :trace - root cause stack elements
 */
cljs.repl.Error__GT_map = (function cljs$repl$Error__GT_map(o){
var base = (function (t){
return cljs.core.merge.call(null,new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"type","type",1174270348),(((t instanceof cljs.core.ExceptionInfo))?new cljs.core.Symbol(null,"ExceptionInfo","ExceptionInfo",294935087,null):(((t instanceof EvalError))?new cljs.core.Symbol("js","EvalError","js/EvalError",1793498501,null):(((t instanceof RangeError))?new cljs.core.Symbol("js","RangeError","js/RangeError",1703848089,null):(((t instanceof ReferenceError))?new cljs.core.Symbol("js","ReferenceError","js/ReferenceError",-198403224,null):(((t instanceof SyntaxError))?new cljs.core.Symbol("js","SyntaxError","js/SyntaxError",-1527651665,null):(((t instanceof URIError))?new cljs.core.Symbol("js","URIError","js/URIError",505061350,null):(((t instanceof Error))?new cljs.core.Symbol("js","Error","js/Error",-1692659266,null):null
)))))))], null),(function (){var temp__5720__auto__ = cljs.core.ex_message.call(null,t);
if(cljs.core.truth_(temp__5720__auto__)){
var msg = temp__5720__auto__;
return new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"message","message",-406056002),msg], null);
} else {
return null;
}
})(),(function (){var temp__5720__auto__ = cljs.core.ex_data.call(null,t);
if(cljs.core.truth_(temp__5720__auto__)){
var ed = temp__5720__auto__;
return new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"data","data",-232669377),ed], null);
} else {
return null;
}
})());
});
var via = (function (){var via = cljs.core.PersistentVector.EMPTY;
var t = o;
while(true){
if(cljs.core.truth_(t)){
var G__32660 = cljs.core.conj.call(null,via,t);
var G__32661 = cljs.core.ex_cause.call(null,t);
via = G__32660;
t = G__32661;
continue;
} else {
return via;
}
break;
}
})();
var root = cljs.core.peek.call(null,via);
return cljs.core.merge.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"via","via",-1904457336),cljs.core.vec.call(null,cljs.core.map.call(null,base,via)),new cljs.core.Keyword(null,"trace","trace",-1082747415),null], null),(function (){var temp__5720__auto__ = cljs.core.ex_message.call(null,root);
if(cljs.core.truth_(temp__5720__auto__)){
var root_msg = temp__5720__auto__;
return new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"cause","cause",231901252),root_msg], null);
} else {
return null;
}
})(),(function (){var temp__5720__auto__ = cljs.core.ex_data.call(null,root);
if(cljs.core.truth_(temp__5720__auto__)){
var data = temp__5720__auto__;
return new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"data","data",-232669377),data], null);
} else {
return null;
}
})(),(function (){var temp__5720__auto__ = new cljs.core.Keyword("clojure.error","phase","clojure.error/phase",275140358).cljs$core$IFn$_invoke$arity$1(cljs.core.ex_data.call(null,o));
if(cljs.core.truth_(temp__5720__auto__)){
var phase = temp__5720__auto__;
return new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"phase","phase",575722892),phase], null);
} else {
return null;
}
})());
});
/**
 * Returns an analysis of the phase, error, cause, and location of an error that occurred
 *   based on Throwable data, as returned by Throwable->map. All attributes other than phase
 *   are optional:
 *  :clojure.error/phase - keyword phase indicator, one of:
 *    :read-source :compile-syntax-check :compilation :macro-syntax-check :macroexpansion
 *    :execution :read-eval-result :print-eval-result
 *  :clojure.error/source - file name (no path)
 *  :clojure.error/line - integer line number
 *  :clojure.error/column - integer column number
 *  :clojure.error/symbol - symbol being expanded/compiled/invoked
 *  :clojure.error/class - cause exception class symbol
 *  :clojure.error/cause - cause exception message
 *  :clojure.error/spec - explain-data for spec error
 */
cljs.repl.ex_triage = (function cljs$repl$ex_triage(datafied_throwable){
var map__32664 = datafied_throwable;
var map__32664__$1 = (((((!((map__32664 == null))))?(((((map__32664.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__32664.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__32664):map__32664);
var via = cljs.core.get.call(null,map__32664__$1,new cljs.core.Keyword(null,"via","via",-1904457336));
var trace = cljs.core.get.call(null,map__32664__$1,new cljs.core.Keyword(null,"trace","trace",-1082747415));
var phase = cljs.core.get.call(null,map__32664__$1,new cljs.core.Keyword(null,"phase","phase",575722892),new cljs.core.Keyword(null,"execution","execution",253283524));
var map__32665 = cljs.core.last.call(null,via);
var map__32665__$1 = (((((!((map__32665 == null))))?(((((map__32665.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__32665.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__32665):map__32665);
var type = cljs.core.get.call(null,map__32665__$1,new cljs.core.Keyword(null,"type","type",1174270348));
var message = cljs.core.get.call(null,map__32665__$1,new cljs.core.Keyword(null,"message","message",-406056002));
var data = cljs.core.get.call(null,map__32665__$1,new cljs.core.Keyword(null,"data","data",-232669377));
var map__32666 = data;
var map__32666__$1 = (((((!((map__32666 == null))))?(((((map__32666.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__32666.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__32666):map__32666);
var problems = cljs.core.get.call(null,map__32666__$1,new cljs.core.Keyword("cljs.spec.alpha","problems","cljs.spec.alpha/problems",447400814));
var fn = cljs.core.get.call(null,map__32666__$1,new cljs.core.Keyword("cljs.spec.alpha","fn","cljs.spec.alpha/fn",408600443));
var caller = cljs.core.get.call(null,map__32666__$1,new cljs.core.Keyword("cljs.spec.test.alpha","caller","cljs.spec.test.alpha/caller",-398302390));
var map__32667 = new cljs.core.Keyword(null,"data","data",-232669377).cljs$core$IFn$_invoke$arity$1(cljs.core.first.call(null,via));
var map__32667__$1 = (((((!((map__32667 == null))))?(((((map__32667.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__32667.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__32667):map__32667);
var top_data = map__32667__$1;
var source = cljs.core.get.call(null,map__32667__$1,new cljs.core.Keyword("clojure.error","source","clojure.error/source",-2011936397));
return cljs.core.assoc.call(null,(function (){var G__32672 = phase;
var G__32672__$1 = (((G__32672 instanceof cljs.core.Keyword))?G__32672.fqn:null);
switch (G__32672__$1) {
case "read-source":
var map__32673 = data;
var map__32673__$1 = (((((!((map__32673 == null))))?(((((map__32673.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__32673.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__32673):map__32673);
var line = cljs.core.get.call(null,map__32673__$1,new cljs.core.Keyword("clojure.error","line","clojure.error/line",-1816287471));
var column = cljs.core.get.call(null,map__32673__$1,new cljs.core.Keyword("clojure.error","column","clojure.error/column",304721553));
var G__32675 = cljs.core.merge.call(null,new cljs.core.Keyword(null,"data","data",-232669377).cljs$core$IFn$_invoke$arity$1(cljs.core.second.call(null,via)),top_data);
var G__32675__$1 = (cljs.core.truth_(source)?cljs.core.assoc.call(null,G__32675,new cljs.core.Keyword("clojure.error","source","clojure.error/source",-2011936397),source):G__32675);
var G__32675__$2 = (cljs.core.truth_(new cljs.core.PersistentHashSet(null, new cljs.core.PersistentArrayMap(null, 2, ["NO_SOURCE_PATH",null,"NO_SOURCE_FILE",null], null), null).call(null,source))?cljs.core.dissoc.call(null,G__32675__$1,new cljs.core.Keyword("clojure.error","source","clojure.error/source",-2011936397)):G__32675__$1);
if(cljs.core.truth_(message)){
return cljs.core.assoc.call(null,G__32675__$2,new cljs.core.Keyword("clojure.error","cause","clojure.error/cause",-1879175742),message);
} else {
return G__32675__$2;
}

break;
case "compile-syntax-check":
case "compilation":
case "macro-syntax-check":
case "macroexpansion":
var G__32676 = top_data;
var G__32676__$1 = (cljs.core.truth_(source)?cljs.core.assoc.call(null,G__32676,new cljs.core.Keyword("clojure.error","source","clojure.error/source",-2011936397),source):G__32676);
var G__32676__$2 = (cljs.core.truth_(new cljs.core.PersistentHashSet(null, new cljs.core.PersistentArrayMap(null, 2, ["NO_SOURCE_PATH",null,"NO_SOURCE_FILE",null], null), null).call(null,source))?cljs.core.dissoc.call(null,G__32676__$1,new cljs.core.Keyword("clojure.error","source","clojure.error/source",-2011936397)):G__32676__$1);
var G__32676__$3 = (cljs.core.truth_(type)?cljs.core.assoc.call(null,G__32676__$2,new cljs.core.Keyword("clojure.error","class","clojure.error/class",278435890),type):G__32676__$2);
var G__32676__$4 = (cljs.core.truth_(message)?cljs.core.assoc.call(null,G__32676__$3,new cljs.core.Keyword("clojure.error","cause","clojure.error/cause",-1879175742),message):G__32676__$3);
if(cljs.core.truth_(problems)){
return cljs.core.assoc.call(null,G__32676__$4,new cljs.core.Keyword("clojure.error","spec","clojure.error/spec",2055032595),data);
} else {
return G__32676__$4;
}

break;
case "read-eval-result":
case "print-eval-result":
var vec__32677 = cljs.core.first.call(null,trace);
var source__$1 = cljs.core.nth.call(null,vec__32677,(0),null);
var method = cljs.core.nth.call(null,vec__32677,(1),null);
var file = cljs.core.nth.call(null,vec__32677,(2),null);
var line = cljs.core.nth.call(null,vec__32677,(3),null);
var G__32680 = top_data;
var G__32680__$1 = (cljs.core.truth_(line)?cljs.core.assoc.call(null,G__32680,new cljs.core.Keyword("clojure.error","line","clojure.error/line",-1816287471),line):G__32680);
var G__32680__$2 = (cljs.core.truth_(file)?cljs.core.assoc.call(null,G__32680__$1,new cljs.core.Keyword("clojure.error","source","clojure.error/source",-2011936397),file):G__32680__$1);
var G__32680__$3 = (cljs.core.truth_((function (){var and__4120__auto__ = source__$1;
if(cljs.core.truth_(and__4120__auto__)){
return method;
} else {
return and__4120__auto__;
}
})())?cljs.core.assoc.call(null,G__32680__$2,new cljs.core.Keyword("clojure.error","symbol","clojure.error/symbol",1544821994),(new cljs.core.PersistentVector(null,2,(5),cljs.core.PersistentVector.EMPTY_NODE,[source__$1,method],null))):G__32680__$2);
var G__32680__$4 = (cljs.core.truth_(type)?cljs.core.assoc.call(null,G__32680__$3,new cljs.core.Keyword("clojure.error","class","clojure.error/class",278435890),type):G__32680__$3);
if(cljs.core.truth_(message)){
return cljs.core.assoc.call(null,G__32680__$4,new cljs.core.Keyword("clojure.error","cause","clojure.error/cause",-1879175742),message);
} else {
return G__32680__$4;
}

break;
case "execution":
var vec__32681 = cljs.core.first.call(null,trace);
var source__$1 = cljs.core.nth.call(null,vec__32681,(0),null);
var method = cljs.core.nth.call(null,vec__32681,(1),null);
var file = cljs.core.nth.call(null,vec__32681,(2),null);
var line = cljs.core.nth.call(null,vec__32681,(3),null);
var file__$1 = cljs.core.first.call(null,cljs.core.remove.call(null,((function (vec__32681,source__$1,method,file,line,G__32672,G__32672__$1,map__32664,map__32664__$1,via,trace,phase,map__32665,map__32665__$1,type,message,data,map__32666,map__32666__$1,problems,fn,caller,map__32667,map__32667__$1,top_data,source){
return (function (p1__32663_SHARP_){
var or__4131__auto__ = (p1__32663_SHARP_ == null);
if(or__4131__auto__){
return or__4131__auto__;
} else {
return new cljs.core.PersistentHashSet(null, new cljs.core.PersistentArrayMap(null, 2, ["NO_SOURCE_PATH",null,"NO_SOURCE_FILE",null], null), null).call(null,p1__32663_SHARP_);
}
});})(vec__32681,source__$1,method,file,line,G__32672,G__32672__$1,map__32664,map__32664__$1,via,trace,phase,map__32665,map__32665__$1,type,message,data,map__32666,map__32666__$1,problems,fn,caller,map__32667,map__32667__$1,top_data,source))
,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"file","file",-1269645878).cljs$core$IFn$_invoke$arity$1(caller),file], null)));
var err_line = (function (){var or__4131__auto__ = new cljs.core.Keyword(null,"line","line",212345235).cljs$core$IFn$_invoke$arity$1(caller);
if(cljs.core.truth_(or__4131__auto__)){
return or__4131__auto__;
} else {
return line;
}
})();
var G__32684 = new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword("clojure.error","class","clojure.error/class",278435890),type], null);
var G__32684__$1 = (cljs.core.truth_(err_line)?cljs.core.assoc.call(null,G__32684,new cljs.core.Keyword("clojure.error","line","clojure.error/line",-1816287471),err_line):G__32684);
var G__32684__$2 = (cljs.core.truth_(message)?cljs.core.assoc.call(null,G__32684__$1,new cljs.core.Keyword("clojure.error","cause","clojure.error/cause",-1879175742),message):G__32684__$1);
var G__32684__$3 = (cljs.core.truth_((function (){var or__4131__auto__ = fn;
if(cljs.core.truth_(or__4131__auto__)){
return or__4131__auto__;
} else {
var and__4120__auto__ = source__$1;
if(cljs.core.truth_(and__4120__auto__)){
return method;
} else {
return and__4120__auto__;
}
}
})())?cljs.core.assoc.call(null,G__32684__$2,new cljs.core.Keyword("clojure.error","symbol","clojure.error/symbol",1544821994),(function (){var or__4131__auto__ = fn;
if(cljs.core.truth_(or__4131__auto__)){
return or__4131__auto__;
} else {
return (new cljs.core.PersistentVector(null,2,(5),cljs.core.PersistentVector.EMPTY_NODE,[source__$1,method],null));
}
})()):G__32684__$2);
var G__32684__$4 = (cljs.core.truth_(file__$1)?cljs.core.assoc.call(null,G__32684__$3,new cljs.core.Keyword("clojure.error","source","clojure.error/source",-2011936397),file__$1):G__32684__$3);
if(cljs.core.truth_(problems)){
return cljs.core.assoc.call(null,G__32684__$4,new cljs.core.Keyword("clojure.error","spec","clojure.error/spec",2055032595),data);
} else {
return G__32684__$4;
}

break;
default:
throw (new Error(["No matching clause: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(G__32672__$1)].join('')));

}
})(),new cljs.core.Keyword("clojure.error","phase","clojure.error/phase",275140358),phase);
});
/**
 * Returns a string from exception data, as produced by ex-triage.
 *   The first line summarizes the exception phase and location.
 *   The subsequent lines describe the cause.
 */
cljs.repl.ex_str = (function cljs$repl$ex_str(p__32688){
var map__32689 = p__32688;
var map__32689__$1 = (((((!((map__32689 == null))))?(((((map__32689.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__32689.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__32689):map__32689);
var triage_data = map__32689__$1;
var phase = cljs.core.get.call(null,map__32689__$1,new cljs.core.Keyword("clojure.error","phase","clojure.error/phase",275140358));
var source = cljs.core.get.call(null,map__32689__$1,new cljs.core.Keyword("clojure.error","source","clojure.error/source",-2011936397));
var line = cljs.core.get.call(null,map__32689__$1,new cljs.core.Keyword("clojure.error","line","clojure.error/line",-1816287471));
var column = cljs.core.get.call(null,map__32689__$1,new cljs.core.Keyword("clojure.error","column","clojure.error/column",304721553));
var symbol = cljs.core.get.call(null,map__32689__$1,new cljs.core.Keyword("clojure.error","symbol","clojure.error/symbol",1544821994));
var class$ = cljs.core.get.call(null,map__32689__$1,new cljs.core.Keyword("clojure.error","class","clojure.error/class",278435890));
var cause = cljs.core.get.call(null,map__32689__$1,new cljs.core.Keyword("clojure.error","cause","clojure.error/cause",-1879175742));
var spec = cljs.core.get.call(null,map__32689__$1,new cljs.core.Keyword("clojure.error","spec","clojure.error/spec",2055032595));
var loc = [cljs.core.str.cljs$core$IFn$_invoke$arity$1((function (){var or__4131__auto__ = source;
if(cljs.core.truth_(or__4131__auto__)){
return or__4131__auto__;
} else {
return "<cljs repl>";
}
})()),":",cljs.core.str.cljs$core$IFn$_invoke$arity$1((function (){var or__4131__auto__ = line;
if(cljs.core.truth_(or__4131__auto__)){
return or__4131__auto__;
} else {
return (1);
}
})()),(cljs.core.truth_(column)?[":",cljs.core.str.cljs$core$IFn$_invoke$arity$1(column)].join(''):"")].join('');
var class_name = cljs.core.name.call(null,(function (){var or__4131__auto__ = class$;
if(cljs.core.truth_(or__4131__auto__)){
return or__4131__auto__;
} else {
return "";
}
})());
var simple_class = class_name;
var cause_type = ((cljs.core.contains_QMARK_.call(null,new cljs.core.PersistentHashSet(null, new cljs.core.PersistentArrayMap(null, 2, ["RuntimeException",null,"Exception",null], null), null),simple_class))?"":[" (",simple_class,")"].join(''));
var format = goog.string.format;
var G__32691 = phase;
var G__32691__$1 = (((G__32691 instanceof cljs.core.Keyword))?G__32691.fqn:null);
switch (G__32691__$1) {
case "read-source":
return format.call(null,"Syntax error reading source at (%s).\n%s\n",loc,cause);

break;
case "macro-syntax-check":
return format.call(null,"Syntax error macroexpanding %sat (%s).\n%s",(cljs.core.truth_(symbol)?[cljs.core.str.cljs$core$IFn$_invoke$arity$1(symbol)," "].join(''):""),loc,(cljs.core.truth_(spec)?(function (){var sb__4661__auto__ = (new goog.string.StringBuffer());
var _STAR_print_newline_STAR__orig_val__32692_32701 = cljs.core._STAR_print_newline_STAR_;
var _STAR_print_fn_STAR__orig_val__32693_32702 = cljs.core._STAR_print_fn_STAR_;
var _STAR_print_newline_STAR__temp_val__32694_32703 = true;
var _STAR_print_fn_STAR__temp_val__32695_32704 = ((function (_STAR_print_newline_STAR__orig_val__32692_32701,_STAR_print_fn_STAR__orig_val__32693_32702,_STAR_print_newline_STAR__temp_val__32694_32703,sb__4661__auto__,G__32691,G__32691__$1,loc,class_name,simple_class,cause_type,format,map__32689,map__32689__$1,triage_data,phase,source,line,column,symbol,class$,cause,spec){
return (function (x__4662__auto__){
return sb__4661__auto__.append(x__4662__auto__);
});})(_STAR_print_newline_STAR__orig_val__32692_32701,_STAR_print_fn_STAR__orig_val__32693_32702,_STAR_print_newline_STAR__temp_val__32694_32703,sb__4661__auto__,G__32691,G__32691__$1,loc,class_name,simple_class,cause_type,format,map__32689,map__32689__$1,triage_data,phase,source,line,column,symbol,class$,cause,spec))
;
cljs.core._STAR_print_newline_STAR_ = _STAR_print_newline_STAR__temp_val__32694_32703;

cljs.core._STAR_print_fn_STAR_ = _STAR_print_fn_STAR__temp_val__32695_32704;

try{cljs.spec.alpha.explain_out.call(null,cljs.core.update.call(null,spec,new cljs.core.Keyword("cljs.spec.alpha","problems","cljs.spec.alpha/problems",447400814),((function (_STAR_print_newline_STAR__orig_val__32692_32701,_STAR_print_fn_STAR__orig_val__32693_32702,_STAR_print_newline_STAR__temp_val__32694_32703,_STAR_print_fn_STAR__temp_val__32695_32704,sb__4661__auto__,G__32691,G__32691__$1,loc,class_name,simple_class,cause_type,format,map__32689,map__32689__$1,triage_data,phase,source,line,column,symbol,class$,cause,spec){
return (function (probs){
return cljs.core.map.call(null,((function (_STAR_print_newline_STAR__orig_val__32692_32701,_STAR_print_fn_STAR__orig_val__32693_32702,_STAR_print_newline_STAR__temp_val__32694_32703,_STAR_print_fn_STAR__temp_val__32695_32704,sb__4661__auto__,G__32691,G__32691__$1,loc,class_name,simple_class,cause_type,format,map__32689,map__32689__$1,triage_data,phase,source,line,column,symbol,class$,cause,spec){
return (function (p1__32686_SHARP_){
return cljs.core.dissoc.call(null,p1__32686_SHARP_,new cljs.core.Keyword(null,"in","in",-1531184865));
});})(_STAR_print_newline_STAR__orig_val__32692_32701,_STAR_print_fn_STAR__orig_val__32693_32702,_STAR_print_newline_STAR__temp_val__32694_32703,_STAR_print_fn_STAR__temp_val__32695_32704,sb__4661__auto__,G__32691,G__32691__$1,loc,class_name,simple_class,cause_type,format,map__32689,map__32689__$1,triage_data,phase,source,line,column,symbol,class$,cause,spec))
,probs);
});})(_STAR_print_newline_STAR__orig_val__32692_32701,_STAR_print_fn_STAR__orig_val__32693_32702,_STAR_print_newline_STAR__temp_val__32694_32703,_STAR_print_fn_STAR__temp_val__32695_32704,sb__4661__auto__,G__32691,G__32691__$1,loc,class_name,simple_class,cause_type,format,map__32689,map__32689__$1,triage_data,phase,source,line,column,symbol,class$,cause,spec))
)
);
}finally {cljs.core._STAR_print_fn_STAR_ = _STAR_print_fn_STAR__orig_val__32693_32702;

cljs.core._STAR_print_newline_STAR_ = _STAR_print_newline_STAR__orig_val__32692_32701;
}
return cljs.core.str.cljs$core$IFn$_invoke$arity$1(sb__4661__auto__);
})():format.call(null,"%s\n",cause)));

break;
case "macroexpansion":
return format.call(null,"Unexpected error%s macroexpanding %sat (%s).\n%s\n",cause_type,(cljs.core.truth_(symbol)?[cljs.core.str.cljs$core$IFn$_invoke$arity$1(symbol)," "].join(''):""),loc,cause);

break;
case "compile-syntax-check":
return format.call(null,"Syntax error%s compiling %sat (%s).\n%s\n",cause_type,(cljs.core.truth_(symbol)?[cljs.core.str.cljs$core$IFn$_invoke$arity$1(symbol)," "].join(''):""),loc,cause);

break;
case "compilation":
return format.call(null,"Unexpected error%s compiling %sat (%s).\n%s\n",cause_type,(cljs.core.truth_(symbol)?[cljs.core.str.cljs$core$IFn$_invoke$arity$1(symbol)," "].join(''):""),loc,cause);

break;
case "read-eval-result":
return format.call(null,"Error reading eval result%s at %s (%s).\n%s\n",cause_type,symbol,loc,cause);

break;
case "print-eval-result":
return format.call(null,"Error printing return value%s at %s (%s).\n%s\n",cause_type,symbol,loc,cause);

break;
case "execution":
if(cljs.core.truth_(spec)){
return format.call(null,"Execution error - invalid arguments to %s at (%s).\n%s",symbol,loc,(function (){var sb__4661__auto__ = (new goog.string.StringBuffer());
var _STAR_print_newline_STAR__orig_val__32696_32705 = cljs.core._STAR_print_newline_STAR_;
var _STAR_print_fn_STAR__orig_val__32697_32706 = cljs.core._STAR_print_fn_STAR_;
var _STAR_print_newline_STAR__temp_val__32698_32707 = true;
var _STAR_print_fn_STAR__temp_val__32699_32708 = ((function (_STAR_print_newline_STAR__orig_val__32696_32705,_STAR_print_fn_STAR__orig_val__32697_32706,_STAR_print_newline_STAR__temp_val__32698_32707,sb__4661__auto__,G__32691,G__32691__$1,loc,class_name,simple_class,cause_type,format,map__32689,map__32689__$1,triage_data,phase,source,line,column,symbol,class$,cause,spec){
return (function (x__4662__auto__){
return sb__4661__auto__.append(x__4662__auto__);
});})(_STAR_print_newline_STAR__orig_val__32696_32705,_STAR_print_fn_STAR__orig_val__32697_32706,_STAR_print_newline_STAR__temp_val__32698_32707,sb__4661__auto__,G__32691,G__32691__$1,loc,class_name,simple_class,cause_type,format,map__32689,map__32689__$1,triage_data,phase,source,line,column,symbol,class$,cause,spec))
;
cljs.core._STAR_print_newline_STAR_ = _STAR_print_newline_STAR__temp_val__32698_32707;

cljs.core._STAR_print_fn_STAR_ = _STAR_print_fn_STAR__temp_val__32699_32708;

try{cljs.spec.alpha.explain_out.call(null,cljs.core.update.call(null,spec,new cljs.core.Keyword("cljs.spec.alpha","problems","cljs.spec.alpha/problems",447400814),((function (_STAR_print_newline_STAR__orig_val__32696_32705,_STAR_print_fn_STAR__orig_val__32697_32706,_STAR_print_newline_STAR__temp_val__32698_32707,_STAR_print_fn_STAR__temp_val__32699_32708,sb__4661__auto__,G__32691,G__32691__$1,loc,class_name,simple_class,cause_type,format,map__32689,map__32689__$1,triage_data,phase,source,line,column,symbol,class$,cause,spec){
return (function (probs){
return cljs.core.map.call(null,((function (_STAR_print_newline_STAR__orig_val__32696_32705,_STAR_print_fn_STAR__orig_val__32697_32706,_STAR_print_newline_STAR__temp_val__32698_32707,_STAR_print_fn_STAR__temp_val__32699_32708,sb__4661__auto__,G__32691,G__32691__$1,loc,class_name,simple_class,cause_type,format,map__32689,map__32689__$1,triage_data,phase,source,line,column,symbol,class$,cause,spec){
return (function (p1__32687_SHARP_){
return cljs.core.dissoc.call(null,p1__32687_SHARP_,new cljs.core.Keyword(null,"in","in",-1531184865));
});})(_STAR_print_newline_STAR__orig_val__32696_32705,_STAR_print_fn_STAR__orig_val__32697_32706,_STAR_print_newline_STAR__temp_val__32698_32707,_STAR_print_fn_STAR__temp_val__32699_32708,sb__4661__auto__,G__32691,G__32691__$1,loc,class_name,simple_class,cause_type,format,map__32689,map__32689__$1,triage_data,phase,source,line,column,symbol,class$,cause,spec))
,probs);
});})(_STAR_print_newline_STAR__orig_val__32696_32705,_STAR_print_fn_STAR__orig_val__32697_32706,_STAR_print_newline_STAR__temp_val__32698_32707,_STAR_print_fn_STAR__temp_val__32699_32708,sb__4661__auto__,G__32691,G__32691__$1,loc,class_name,simple_class,cause_type,format,map__32689,map__32689__$1,triage_data,phase,source,line,column,symbol,class$,cause,spec))
)
);
}finally {cljs.core._STAR_print_fn_STAR_ = _STAR_print_fn_STAR__orig_val__32697_32706;

cljs.core._STAR_print_newline_STAR_ = _STAR_print_newline_STAR__orig_val__32696_32705;
}
return cljs.core.str.cljs$core$IFn$_invoke$arity$1(sb__4661__auto__);
})());
} else {
return format.call(null,"Execution error%s at %s(%s).\n%s\n",cause_type,(cljs.core.truth_(symbol)?[cljs.core.str.cljs$core$IFn$_invoke$arity$1(symbol)," "].join(''):""),loc,cause);
}

break;
default:
throw (new Error(["No matching clause: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(G__32691__$1)].join('')));

}
});
cljs.repl.error__GT_str = (function cljs$repl$error__GT_str(error){
return cljs.repl.ex_str.call(null,cljs.repl.ex_triage.call(null,cljs.repl.Error__GT_map.call(null,error)));
});

//# sourceMappingURL=repl.js.map
