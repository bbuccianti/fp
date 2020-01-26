// Compiled by ClojureScript 1.10.520 {:target :nodejs}
goog.provide('pjstadig.print');
goog.require('cljs.core');
goog.require('cljs.pprint');
goog.require('pjstadig.macro');
goog.require('goog.string.StringBuffer');
pjstadig.print.rprint = (function pjstadig$print$rprint(s){
return cljs.core._write.call(null,cljs.core._STAR_out_STAR_,s);
});
pjstadig.print.clear = (function pjstadig$print$clear(){
cljs.core._STAR_print_fn_STAR_.call(null,cljs.core.str.cljs$core$IFn$_invoke$arity$1(pjstadig.print._STAR_sb_STAR_));

return pjstadig.print._STAR_sb_STAR_.clear();
});
pjstadig.print.with_pretty_writer = (function pjstadig$print$with_pretty_writer(f){
var _STAR_sb_STAR__orig_val__32874 = pjstadig.print._STAR_sb_STAR_;
var _STAR_out_STAR__orig_val__32875 = cljs.core._STAR_out_STAR_;
var _STAR_sb_STAR__temp_val__32876 = (new goog.string.StringBuffer());
var _STAR_out_STAR__temp_val__32877 = cljs.pprint.get_pretty_writer.call(null,(new cljs.core.StringBufferWriter(pjstadig.print._STAR_sb_STAR_)));
pjstadig.print._STAR_sb_STAR_ = _STAR_sb_STAR__temp_val__32876;

cljs.core._STAR_out_STAR_ = _STAR_out_STAR__temp_val__32877;

try{return f.call(null);
}finally {cljs.core._STAR_out_STAR_ = _STAR_out_STAR__orig_val__32875;

pjstadig.print._STAR_sb_STAR_ = _STAR_sb_STAR__orig_val__32874;
}});
pjstadig.print.convert_event = (function pjstadig$print$convert_event(p__32879){
var map__32880 = p__32879;
var map__32880__$1 = (((((!((map__32880 == null))))?(((((map__32880.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__32880.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__32880):map__32880);
var event = map__32880__$1;
var actual = cljs.core.get.call(null,map__32880__$1,new cljs.core.Keyword(null,"actual","actual",107306363));
var expected = cljs.core.get.call(null,map__32880__$1,new cljs.core.Keyword(null,"expected","expected",1583670997));
var diffs = (cljs.core.truth_((function (){var and__4120__auto__ = cljs.core.seq_QMARK_.call(null,actual);
if(and__4120__auto__){
var and__4120__auto____$1 = cljs.core.seq.call(null,actual);
if(and__4120__auto____$1){
var and__4120__auto____$2 = cljs.core._EQ_.call(null,new cljs.core.Symbol(null,"not","not",1044554643,null),cljs.core.first.call(null,actual));
if(and__4120__auto____$2){
var and__4120__auto____$3 = cljs.core.seq_QMARK_.call(null,cljs.core.second.call(null,actual));
if(and__4120__auto____$3){
var and__4120__auto____$4 = cljs.core.seq.call(null,cljs.core.second.call(null,actual));
if(and__4120__auto____$4){
var and__4120__auto____$5 = new cljs.core.PersistentHashSet(null, new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Symbol("clojure.core","=","clojure.core/=",-1788080406,null),null,new cljs.core.Symbol("cljs.core","=","cljs.core/=",-1891498332,null),null,new cljs.core.Symbol(null,"=","=",-1501502141,null),null], null), null).call(null,cljs.core.first.call(null,cljs.core.second.call(null,actual)));
if(cljs.core.truth_(and__4120__auto____$5)){
return ((2) < cljs.core.count.call(null,cljs.core.second.call(null,actual)));
} else {
return and__4120__auto____$5;
}
} else {
return and__4120__auto____$4;
}
} else {
return and__4120__auto____$3;
}
} else {
return and__4120__auto____$2;
}
} else {
return and__4120__auto____$1;
}
} else {
return and__4120__auto__;
}
})())?(function (){var a = cljs.core.nth.call(null,cljs.core.second.call(null,actual),(1));
var more = cljs.core.drop.call(null,(2),cljs.core.second.call(null,actual));
return cljs.core.map.call(null,cljs.core.vector,more,cljs.core.map.call(null,((function (a,more,map__32880,map__32880__$1,event,actual,expected){
return (function (p1__32878_SHARP_){
return cljs.core.take.call(null,(2),pjstadig.macro.diff.call(null,a,p1__32878_SHARP_));
});})(a,more,map__32880,map__32880__$1,event,actual,expected))
,more));
})():null);
var expected__$1 = ((cljs.core.seq.call(null,diffs))?cljs.core.nth.call(null,cljs.core.second.call(null,actual),(1)):expected);
return cljs.core.assoc.call(null,event,new cljs.core.Keyword(null,"diffs","diffs",-1720136241),diffs,new cljs.core.Keyword(null,"expected","expected",1583670997),expected__$1);
});

//# sourceMappingURL=print.js.map
