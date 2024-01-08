#Gson should handle stack overflow and other errors happening from parsing untrusted JSON

Owner: google

Repo: gson

Labels: Type-Defect Priority-Medium auto-migrated 

## GoogleCodeExporter (19 Mar 2015)

```
(reported by Meder):

'print "{\"a\":[],\"a\":" x 2000'`
`perl -e 'print "{\"a\":[],\""; print "X" x 6000;'`

to generate really large JSON and feed it to GSON. Gson brings down the JVM
with stack overflow error. 
```

Original issue reported on code.google.com by `inder123` on 8 Jul 2008 at 5:35


## GoogleCodeExporter (19 Mar 2015)

Original comment by `inder123` on 8 Jul 2008 at 5:44


## GoogleCodeExporter (19 Mar 2015)

```
Here is a test that will reproduce this problem. On the positive side, Gson is 
able
to handle individual strings upto 103kb on my machine. 

  public void testReallyLongStrings() {
    StringBuilder sb = new StringBuilder(8096);
    sb.append("com.codegoogle.gson.gks.anon.GksDataNotFoundException: Instrument 10
not found.\n");
    sb.append("\tat
com.codegoogle.gson.common.backend.reflect.Instrument.load(Instrument.java");
    sb.append(":135)\n\tat
com.codegoogle.gson.common.entities.InstrumentFactoryImpl$1.run(Ins");
    sb.append("trumentFactoryImpl.java:70)\n\tat
com.codegoogle.gson.common.InstrumentFactory");
    sb.append("Impl$1.run(InstrumentFactoryImpl.java:98)\n\tat
com.codegoogle.gson.common.base.");

sb.append("CbgRunnableToGksRunnableAdapter.run(CbgRunnableToGksRunnableAdapter.j
ava:32)\n\t");
    sb.append("at
com.codegoogle.gson.anon.GksConnManager.run(GksConnectionManager.java:15)\n\t");
    sb.append("at
com.codegoogle.gson.common.entities.NonTransactionalRunner.run(NonTransactional"
);
    sb.append("Runner.java:4544)\n\tat
com.codegoogle.gson.common.base.ConnectionContextSwitch");
    sb.append("er$1.run(ConnectionSwitcher.java:20)\n\tat
com.codegoogle.gson.anon.GksConnecti");
    sb.append("onManager.run(GksConnectionManager.java:65)\n\tat
com.codegoogle.gson.common.base");

sb.append(".ConnectionContextSwitcher.run(ConnectionContextSwitcher.java:2238)\n
\tat
com");

sb.append(".google.common.entities.base.SierraContext.runNonTransactionalOnRepli
ca(Sierra");
    sb.append("Context.java:60)\n\tat
com.codegoogle.gson.common.InstrumentFactoryImpl.getIns");
    sb.append("(InstrumentFactoryImpl.java:7)\n\tat
com.codegoogle.gson.common.webservice.buyer");

sb.append(".facades.common.BuyerFacadecommon.getDomainInstrument(BuyerFacadecomm
on.jav");
    sb.append("a:183)\n\tat
com.codegoogle.gson.common.buyer.facades.common.CartFacadePaym");
    sb.append("ents.update(CartFacadecommon.java:39)\n\tat
com.codegoogle.gson.common.buy");

sb.append("er.facades.common.CartFacadecommon.update(CartFacadecommon.java:2415)
\n\t");
    sb.append("at
com.codegoogle.gson.common.webservice.buyer.facades.common.CartFacadecommo.upda"
);
    sb.append("te(CartFacadecommon.java:2053)\n\tat
com.codegoogle.gson.common.buyer.web.B");

sb.append("uyerWebServiceCartPagelet.executePut(BuyerWebServiceCartPagelet.java:
48)\n\t");
    sb.append("at
com.codegoogle.gson.common.webservice.buyer.web.CartPagelet.onPostCar");
    sb.append("tSelections(BuyerWebServiceCartPagelet.java:12)\n\tat
sun.reflect.NativeMethodAc");
    sb.append("cessorImpl.invoke0(Native Method)\n\tat
sun.reflect.NativeMethodAccessorImpl.inv");
    sb.append("oke(Unknown Source)\n\tat
sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknow");
    sb.append("n Source)\n\tat java.lang.reflect.Method.invoke(Unknown Source)\n\tat
com.gson");

sb.append(".web.pagelets.di.ObjectDriver$MethodInvoker.invoke(ObjectDriver.java:
86)\n\t");
    sb.append("at
com.codegoogle.gson.web.di.ObjectDriver.execute(ObjectDriver.java:155)\n\t");
    sb.append("at
com.codegoogle.gson.web.ReflectorPageletDriver.invokeExecuteOrOnEvent(Reflect");
    sb.append("orPageletDriver.java:84)\n\tat
com.codegoogle.gson.web.ReflectorPageletDriver.ex");
    sb.append("ecute(ReflectorPageletDriver.java:54)\n\tat
com.codegoogle.gson.web.DelegatingPa");
    sb.append("geletDriver.execute(DelegatingPageletDriver.java:20)\n\tat
com.codegoogle.gson.w");

sb.append("ebservice.buyer.web.BuyerTosPageletDriver.execute(BuyerTosPageletDriv
er.java:44)");
    sb.append("\n\tat
com.codegoogle.gson.web.DelegatingPageletDriver.execute(DelegatingPagelet");
    sb.append("Driver.java:20)\n\tat
com.codegoogle.gson.common.webservice.web.BuyerAuthenticati");

sb.append("onPageletDriver.execute(BuyerAuthenticationPageletDriver.java:58)\n\t
at
com.goog");

sb.append("le.web.pagelets.DelegatingPageletDriver.execute(DelegatingPageletDriv
er.java:20)");
    sb.append("\n\tat
com.codegoogle.gson.common.apps.common.DbTransactionPageletDriver.access");
    sb.append("$001(DbTransactionPageletDriver.java:17)\n\tat
com.codegoogle.gson.apps.common.p");

sb.append("agelet.DbTransactionPageletDriver$1.run(DbTransactionPageletDriver.ja
va:28)\n\t");
    sb.append("at
com.codegoogle.gson.common.apps.common.DbTransactionPageletDriver$1.run(DbTr");
    sb.append("ansactionPageletDriver.java:26)\n\tat
com.codegoogle.gson.common.base.Transact");
    sb.append("ionalRunner$Wrapper.run(TransactionalRunner.java:64)\n\tat
com.codegoogle.gson.");

sb.append("common.entities.base.GksTransactionBodyAdapter.run(GksTransactionBody
Adapter.j");
    sb.append("ava:56)\n\tat
com.codegoogle.gson.GksConnectionManager.runTransaction(GksConnecti");
    sb.append("onManager.java:1040)\n\tat
com.codegoogle.gson.GksConnectionManager.runTransactio");
    sb.append("n(GksConnectionManager.java:961)\n\tat
com.codegoogle.gson.common.base.Transac");
    sb.append("tionalRunner.newTransaction(TransactionalRunner.java:224)\n\tat
com.gson.payme");

sb.append("nts.entities.base.TransactionalRunner.run(TransactionalRunner.java:13
4)\n\tat
co");

sb.append("m.google.common.entities.base.ConnectionContextSwitcher$1.run(Connect
ionContex");
    sb.append("tSwitcher.java:90)\n\tat
com.codegoogle.gson.GksConnectionManager.run(GksConnecti");
    sb.append("onManager.java:615)\n\tat
com.codegoogle.gson.common.base.ConnectionContextSwi");
    sb.append("tcher.run(ConnectionContextSwitcher.java:78)\n\tat
com.codegoogle.gson.common.");
    sb.append("SierraContext.runPrimary(SierraContext.java:373)\n\tat
com.codegoogle.gson.common.");

sb.append("apps.common.pagelet.DbTransactionPageletDriver.execute(DbTransactionP
ageletDrive");
    sb.append("r.java:26)\n\tat
com.codegoogle.gson.web.DelegatingPageletDriver.execute(Delegat");
    sb.append("ingPageletDriver.java:20)\n\tat
com.codegoogle.gson.web.DelegatingPageletDriver.");
    sb.append("execute(DelegatingPageletDriver.java:20)\n\tat
com.codegoogle.gson.common.bu");

sb.append("yer.web.BuyerWebServiceErrorPageletDriver.execute(BuyerWebServiceErro
rPageletDri");
    sb.append("ver.java:42)\n\tat
com.codegoogle.gson.web.PageletServletAdapterWithoutScope.ser");
    sb.append("vice(PageletServletAdapterWithoutScope.java:58)\n\tat
com.codegoogle.gson.web.Pa");
    sb.append("geletServletAdapter2.service(PageletServletAdapter2.java:52)\n\tat
javax.servlet");
    sb.append(".HttpServlet.service(HttpServlet.java:102)\n\tat
com.codegoogle.gson.FilteredSer");
    sb.append("vlet$ChainEnd.doFilter(FilteredServlet.java:133)\n\tat
com.codegoogle.gson.common");

sb.append(".monitorableservices.web.pagelets.LocaleContextFilter.doFilter(Locale
ContextFil");
    sb.append("ter.java:72)\n\tat
com.codegoogle.gson.FilteredSt$Chain.doFilter(FilteredServlet");
    sb.append(".java:131)\n\tat
com.codegoogle.gson.common.apps.DatabaseFilter.doFilter(Data");
    sb.append("baseFilter.java:31)\n\tat
com.codegoogle.gson.FilteredSet$Chain.doFilter(Filtered");
    sb.append("Servlet.java:131)\n\tat
com.codegoogle.gson.common.apps.StatsFilter.doFilter(");
    sb.append("StatsFilter.java:75)\n\tat
com.codegoogle.gson.FilteredSet$Chain.doFilter(Filtere");
    sb.append("dServlet.java:131)\n\tat
com.codegoogle.gson.common.apps.LoggingFilter.doFilt");
    sb.append("er(LoggingFilter.java:31)\n\tat
com.codegoogle.FilteredServlet$Chain.doFilter(Fi");
    sb.append("lteredServlet.java:131)\n\tat
com.codegoogle.gson.di.guice.GuiceFilter.doFil");
    sb.append("ter(GuiceFilter.java:419)\n\tat
com.codegoogle.FilteredServlet$Chain.doFilter(Fil");
    sb.append("teredServlet.java:13)\n\tat
com.codegoogle.FilteredServlet.service(FilteredServ");
    sb.append("let.java:103)\n\tat
com.codegoogle.HttpConnection.runServlet(HttpConnection.java");
    sb.append(":65)\n\tat
com.codegoogle.HttpConnection.run(HttpConnection.java:275)\n\tat com");

sb.append(".codegoogle.parser.DispatchQueue$WorkerThread.run(DispatchQueue.java:
3139)\n");

    String initialStackTrace = sb.toString();
    for (int i = 1; i < 18; ++i) {
      sb.append(initialStackTrace);
    }
    String stackTrace = sb.toString();
    System.out.println("length at which Gson causes stack overflow errors: " +
stackTrace.length());
    String json = "{\"message\":\"Instrument 10 not found.\","
      + "\"stackTrace\":\"" + stackTrace + "\"}";
    ExceptionHolder target = gson.fromJson(json, ExceptionHolder.class);
    assertTrue(target.message.contains("Instrument"));
    assertTrue(target.stackTrace.contains("DispatchQueue"));
  }
```

Original comment by `inder123` on 17 Jul 2008 at 12:41


## GoogleCodeExporter (19 Mar 2015)

```
r116 includes a test that can reproduce this bug.
```

Original comment by `inder123` on 17 Jul 2008 at 8:18


## GoogleCodeExporter (19 Mar 2015)

```
Fixed in r117
```

Original comment by `inder123` on 17 Jul 2008 at 8:52
- Changed state: **Fixed**


## GoogleCodeExporter (19 Mar 2015)

```
This is a duplicate of Issue 47
http://code.google.com/p/google-gson/issues/detail?id=47
```

Original comment by `inder123` on 14 Oct 2008 at 9:44
- Changed state: **Duplicate**


