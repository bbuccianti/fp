// Compiled by ClojureScript 1.10.520 {:target :nodejs}
goog.provide('fp.core_test');
goog.require('cljs.core');
goog.require('cljs.test');
fp.core_test.adding = (function fp$core_test$adding(){
return cljs.test.test_var.call(null,fp.core_test.adding.cljs$lang$var);
});
fp.core_test.adding.cljs$lang$test = (function (){
try{var values__24391__auto__ = (new cljs.core.List(null,(1),(new cljs.core.List(null,(1),null,(1),null)),(2),null));
var result__24392__auto__ = cljs.core.apply.call(null,cljs.core._EQ_,values__24391__auto__);
if(cljs.core.truth_(result__24392__auto__)){
cljs.test.do_report.call(null,new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"type","type",1174270348),new cljs.core.Keyword(null,"pass","pass",1574159993),new cljs.core.Keyword(null,"expected","expected",1583670997),cljs.core.list(new cljs.core.Symbol(null,"=","=",-1501502141,null),(1),(1)),new cljs.core.Keyword(null,"actual","actual",107306363),cljs.core.cons.call(null,cljs.core._EQ_,values__24391__auto__),new cljs.core.Keyword(null,"message","message",-406056002),null], null));
} else {
cljs.test.do_report.call(null,new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"type","type",1174270348),new cljs.core.Keyword(null,"fail","fail",1706214930),new cljs.core.Keyword(null,"expected","expected",1583670997),cljs.core.list(new cljs.core.Symbol(null,"=","=",-1501502141,null),(1),(1)),new cljs.core.Keyword(null,"actual","actual",107306363),(new cljs.core.List(null,new cljs.core.Symbol(null,"not","not",1044554643,null),(new cljs.core.List(null,cljs.core.cons.call(null,new cljs.core.Symbol(null,"=","=",-1501502141,null),values__24391__auto__),null,(1),null)),(2),null)),new cljs.core.Keyword(null,"message","message",-406056002),null], null));
}

return result__24392__auto__;
}catch (e34992){var t__24436__auto__ = e34992;
return cljs.test.do_report.call(null,new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"type","type",1174270348),new cljs.core.Keyword(null,"error","error",-978969032),new cljs.core.Keyword(null,"expected","expected",1583670997),cljs.core.list(new cljs.core.Symbol(null,"=","=",-1501502141,null),(1),(1)),new cljs.core.Keyword(null,"actual","actual",107306363),t__24436__auto__,new cljs.core.Keyword(null,"message","message",-406056002),null], null));
}});

fp.core_test.adding.cljs$lang$var = new cljs.core.Var(function(){return fp.core_test.adding;},new cljs.core.Symbol("fp.core-test","adding","fp.core-test/adding",-1858552503,null),cljs.core.PersistentHashMap.fromArrays([new cljs.core.Keyword(null,"ns","ns",441598760),new cljs.core.Keyword(null,"name","name",1843675177),new cljs.core.Keyword(null,"file","file",-1269645878),new cljs.core.Keyword(null,"end-column","end-column",1425389514),new cljs.core.Keyword(null,"column","column",2078222095),new cljs.core.Keyword(null,"line","line",212345235),new cljs.core.Keyword(null,"end-line","end-line",1837326455),new cljs.core.Keyword(null,"arglists","arglists",1661989754),new cljs.core.Keyword(null,"doc","doc",1913296891),new cljs.core.Keyword(null,"test","test",577538877)],[new cljs.core.Symbol(null,"fp.core-test","fp.core-test",-431124528,null),new cljs.core.Symbol(null,"adding","adding",1447994221,null),"/home/benja/src/github/bbuccianti/fp/test/fp/core_test.cljs",16,1,4,4,cljs.core.List.EMPTY,null,(cljs.core.truth_(fp.core_test.adding)?fp.core_test.adding.cljs$lang$test:null)]));

//# sourceMappingURL=core_test.js.map
