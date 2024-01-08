#client在启动时报java.net.UnknownHostException: cat.ctripcorp.com

Owner: apolloconfig

Repo: apollo

Labels: 

## yangl (28 Oct 2016)

[framework-foundation] App ID is set to 100002 by app.id property in properties InputStream.
[framework-foundation] Loading /opt/settings/server.properties
[framework-foundation] JVM system property 'env' is blank. Will try to get environment by OS environment variable 'ENV'.
[framework-foundation] OS environment variable 'ENV' is blank. Will try to get environment by property 'env' from the properties InputStream.
[framework-foundation] Environment is set to [PRO] by property 'env'/'environment' in  server.properties.
[framework-foundation] Data Center is not available from server.properties.
[framework-foundation] Data Center is not available by OS environment variable ci_located_code.
[framework-foundation] Data Center is not available by OS environment variable CI_located_code. It is set to null.
[framework-foundation] http port is not available from JVM system property 'port.http.server
[framework-foundation] http port is not available from OS environment variable 'SERVER_HTTP_PORT. Default http port [0] is used.
[framework-foundation] /opt/settings/deploy.properties does not exist or is not readable.
[framework-foundation] C:/opt/settings/deploy.properties does not exist or is not readable.
/data/appdatas/cat does not exist. Will try to load config from /opt/app/cat now.
Logger file /opt/logs/cat/cat_20161028.log
/opt/app/cat/client.xml does not exist.
client.xml is not available. Will try to get domain by framework foundation.
App ID is set to [100002] by framework foundation.
client.xml is not available. Will try to get env by framework-foundation, and then decide CAT server address.
CAT server address is set to [cat.ctripcorp.com] by environment pro
CAT client message queue size: 1000
CAT client now tries to visit router service. http://cat.ctripcorp.com:80/cat/s/router?domain=100002&ip=172.16.1.18&op=json
java.net.UnknownHostException: cat.ctripcorp.com
    at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:184)
    at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)
    at java.net.Socket.connect(Socket.java:589)
    at sun.net.NetworkClient.doConnect(NetworkClient.java:175)
    at sun.net.www.http.HttpClient.openServer(HttpClient.java:432)
    at sun.net.www.http.HttpClient.openServer(HttpClient.java:527)
    at sun.net.www.http.HttpClient.<init>(HttpClient.java:211)
    at sun.net.www.http.HttpClient.New(HttpClient.java:308)
    at sun.net.www.http.HttpClient.New(HttpClient.java:326)
    at sun.net.www.protocol.http.HttpURLConnection.getNewHttpClient(HttpURLConnection.java:1202)
    at sun.net.www.protocol.http.HttpURLConnection.plainConnect0(HttpURLConnection.java:1138)
    at sun.net.www.protocol.http.HttpURLConnection.plainConnect(HttpURLConnection.java:1032)
    at sun.net.www.protocol.http.HttpURLConnection.connect(HttpURLConnection.java:966)
    at sun.net.www.protocol.http.HttpURLConnection.getInputStream0(HttpURLConnection.java:1546)
    at sun.net.www.protocol.http.HttpURLConnection.getInputStream(HttpURLConnection.java:1474)
    at org.unidal.helper.Urls$UrlIO.openStream(Urls.java:69)
    at org.unidal.helper.Urls$UrlIO.openStream(Urls.java:45)
    at com.dianping.cat.message.io.ChannelManager.loadServerConfig(ChannelManager.java:297)
    at com.dianping.cat.message.io.ChannelManager.<init>(ChannelManager.java:87)
    at com.dianping.cat.message.io.TcpSocketSender.initialize(TcpSocketSender.java:104)
    at com.dianping.cat.message.io.DefaultTransportManager.initialize(DefaultTransportManager.java:57)
    at org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializePhase.execute(InitializePhase.java:33)
    at org.codehaus.plexus.lifecycle.AbstractLifecycleHandler.start(AbstractLifecycleHandler.java:96)
    at org.codehaus.plexus.component.manager.AbstractComponentManager.start(AbstractComponentManager.java:167)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.startComponentLifecycle(XBeanComponentBuilder.java:285)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.build(XBeanComponentBuilder.java:131)
    at org.codehaus.plexus.component.manager.AbstractComponentManager.createComponentInstance(AbstractComponentManager.java:181)
    at org.codehaus.plexus.component.manager.SingletonComponentManager.getComponent(SingletonComponentManager.java:67)
    at org.codehaus.plexus.DefaultComponentRegistry.getComponent(DefaultComponentRegistry.java:358)
    at org.codehaus.plexus.DefaultComponentRegistry.lookup(DefaultComponentRegistry.java:178)
    at org.codehaus.plexus.DefaultPlexusContainer.lookup(DefaultPlexusContainer.java:393)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder$RequirementRecipe.internalCreate(XBeanComponentBuilder.java:404)
    at org.apache.xbean.recipe.AbstractRecipe.create(AbstractRecipe.java:96)
    at org.apache.xbean.recipe.RecipeHelper.convert(RecipeHelper.java:167)
    at org.apache.xbean.recipe.ObjectRecipe.setProperty(ObjectRecipe.java:515)
    at org.apache.xbean.recipe.ObjectRecipe.setProperties(ObjectRecipe.java:383)
    at org.apache.xbean.recipe.ObjectRecipe.internalCreate(ObjectRecipe.java:298)
    at org.apache.xbean.recipe.AbstractRecipe.create(AbstractRecipe.java:96)
    at org.apache.xbean.recipe.AbstractRecipe.create(AbstractRecipe.java:61)
    at org.apache.xbean.recipe.AbstractRecipe.create(AbstractRecipe.java:49)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.createComponentInstance(XBeanComponentBuilder.java:161)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.build(XBeanComponentBuilder.java:125)
    at org.codehaus.plexus.component.manager.AbstractComponentManager.createComponentInstance(AbstractComponentManager.java:181)
    at org.codehaus.plexus.component.manager.SingletonComponentManager.getComponent(SingletonComponentManager.java:67)
    at org.codehaus.plexus.DefaultComponentRegistry.getComponent(DefaultComponentRegistry.java:358)
    at org.codehaus.plexus.DefaultComponentRegistry.lookup(DefaultComponentRegistry.java:178)
    at org.codehaus.plexus.DefaultPlexusContainer.lookup(DefaultPlexusContainer.java:383)
    at com.dianping.cat.Cat.setContainer(Cat.java:596)
    at com.dianping.cat.CatClientModule.execute(CatClientModule.java:32)
    at org.unidal.initialization.AbstractModule.initialize(AbstractModule.java:10)
    at org.unidal.initialization.DefaultModuleInitializer.executeModule(DefaultModuleInitializer.java:59)
    at org.unidal.initialization.DefaultModuleInitializer.execute(DefaultModuleInitializer.java:42)
    at com.dianping.cat.Cat.initialize(Cat.java:183)
    at com.dianping.cat.Cat.initialize(Cat.java:168)
    at com.dianping.cat.Cat.checkAndInitialize(Cat.java:66)
    at com.dianping.cat.Cat.getProducer(Cat.java:155)
    at com.dianping.cat.Cat.newTransaction(Cat.java:556)
    at com.ctrip.framework.apollo.internals.ConfigServiceLocator.updateConfigServices(ConfigServiceLocator.java:112)
    at com.ctrip.framework.apollo.internals.ConfigServiceLocator.tryUpdateConfigServices(ConfigServiceLocator.java:83)
    at com.ctrip.framework.apollo.internals.ConfigServiceLocator.initialize(ConfigServiceLocator.java:64)
    at org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializePhase.execute(InitializePhase.java:33)
    at org.codehaus.plexus.lifecycle.AbstractLifecycleHandler.start(AbstractLifecycleHandler.java:96)
    at org.codehaus.plexus.component.manager.AbstractComponentManager.start(AbstractComponentManager.java:167)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.startComponentLifecycle(XBeanComponentBuilder.java:285)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.build(XBeanComponentBuilder.java:131)
    at org.codehaus.plexus.component.manager.AbstractComponentManager.createComponentInstance(AbstractComponentManager.java:181)
    at org.codehaus.plexus.component.manager.SingletonComponentManager.getComponent(SingletonComponentManager.java:67)
    at org.codehaus.plexus.DefaultComponentRegistry.getComponent(DefaultComponentRegistry.java:358)
    at org.codehaus.plexus.DefaultComponentRegistry.lookup(DefaultComponentRegistry.java:178)
    at org.codehaus.plexus.DefaultPlexusContainer.lookup(DefaultPlexusContainer.java:383)
    at com.ctrip.framework.apollo.internals.RemoteConfigRepository.<init>(RemoteConfigRepository.java:78)
    at com.ctrip.framework.apollo.spi.DefaultConfigFactory.createRemoteConfigRepository(DefaultConfigFactory.java:59)
    at com.ctrip.framework.apollo.spi.DefaultConfigFactory.createLocalConfigRepository(DefaultConfigFactory.java:55)
    at com.ctrip.framework.apollo.spi.DefaultConfigFactory.create(DefaultConfigFactory.java:31)
    at com.ctrip.framework.apollo.internals.DefaultConfigManager.getConfig(DefaultConfigManager.java:38)
    at com.ctrip.framework.apollo.ConfigService.getConfig(ConfigService.java:42)
    at com.ctrip.framework.apollo.ConfigService.getAppConfig(ConfigService.java:33)
    at com.uxin.feerate.domain.manager.impl.CallManagerImplTest.testConf(CallManagerImplTest.java:42)
    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
    at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    at java.lang.reflect.Method.invoke(Method.java:498)
    at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
    at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
    at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
    at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
    at org.springframework.test.context.junit4.statements.RunBeforeTestMethodCallbacks.evaluate(RunBeforeTestMethodCallbacks.java:75)
    at org.springframework.test.context.junit4.statements.RunAfterTestMethodCallbacks.evaluate(RunAfterTestMethodCallbacks.java:86)
    at org.springframework.test.context.junit4.statements.SpringRepeat.evaluate(SpringRepeat.java:84)
    at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
    at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:252)
    at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:94)
    at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
    at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
    at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
    at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
    at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
    at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
    at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:70)
    at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
    at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:191)
    at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
    at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:117)
    at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:42)
    at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:262)
    at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:84)
java.net.UnknownHostException: cat.ctripcorp.com
    at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:184)
    at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)
    at java.net.Socket.connect(Socket.java:589)
    at sun.net.NetworkClient.doConnect(NetworkClient.java:175)
    at sun.net.www.http.HttpClient.openServer(HttpClient.java:432)
    at sun.net.www.http.HttpClient.openServer(HttpClient.java:527)
    at sun.net.www.http.HttpClient.<init>(HttpClient.java:211)
    at sun.net.www.http.HttpClient.New(HttpClient.java:308)
    at sun.net.www.http.HttpClient.New(HttpClient.java:326)
    at sun.net.www.protocol.http.HttpURLConnection.getNewHttpClient(HttpURLConnection.java:1202)
    at sun.net.www.protocol.http.HttpURLConnection.plainConnect0(HttpURLConnection.java:1138)
    at sun.net.www.protocol.http.HttpURLConnection.plainConnect(HttpURLConnection.java:1032)
    at sun.net.www.protocol.http.HttpURLConnection.connect(HttpURLConnection.java:966)
    at sun.net.www.protocol.http.HttpURLConnection.getInputStream0(HttpURLConnection.java:1546)
    at sun.net.www.protocol.http.HttpURLConnection.getInputStream(HttpURLConnection.java:1474)
    at org.unidal.helper.Urls$UrlIO.openStream(Urls.java:69)
    at org.unidal.helper.Urls$UrlIO.openStream(Urls.java:45)
    at com.dianping.cat.message.io.ChannelManager.loadServerConfig(ChannelManager.java:297)
    at com.dianping.cat.message.io.ChannelManager.<init>(ChannelManager.java:87)
    at com.dianping.cat.message.io.TcpSocketSender.initialize(TcpSocketSender.java:104)
    at com.dianping.cat.message.io.DefaultTransportManager.initialize(DefaultTransportManager.java:57)
    at org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializePhase.execute(InitializePhase.java:33)
    at org.codehaus.plexus.lifecycle.AbstractLifecycleHandler.start(AbstractLifecycleHandler.java:96)
    at org.codehaus.plexus.component.manager.AbstractComponentManager.start(AbstractComponentManager.java:167)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.startComponentLifecycle(XBeanComponentBuilder.java:285)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.build(XBeanComponentBuilder.java:131)
    at org.codehaus.plexus.component.manager.AbstractComponentManager.createComponentInstance(AbstractComponentManager.java:181)
    at org.codehaus.plexus.component.manager.SingletonComponentManager.getComponent(SingletonComponentManager.java:67)
    at org.codehaus.plexus.DefaultComponentRegistry.getComponent(DefaultComponentRegistry.java:358)
    at org.codehaus.plexus.DefaultComponentRegistry.lookup(DefaultComponentRegistry.java:178)
    at org.codehaus.plexus.DefaultPlexusContainer.lookup(DefaultPlexusContainer.java:393)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder$RequirementRecipe.internalCreate(XBeanComponentBuilder.java:404)
    at org.apache.xbean.recipe.AbstractRecipe.create(AbstractRecipe.java:96)
    at org.apache.xbean.recipe.RecipeHelper.convert(RecipeHelper.java:167)
    at org.apache.xbean.recipe.ObjectRecipe.setProperty(ObjectRecipe.java:515)
    at org.apache.xbean.recipe.ObjectRecipe.setProperties(ObjectRecipe.java:383)
    at org.apache.xbean.recipe.ObjectRecipe.internalCreate(ObjectRecipe.java:298)
    at org.apache.xbean.recipe.AbstractRecipe.create(AbstractRecipe.java:96)
    at org.apache.xbean.recipe.AbstractRecipe.create(AbstractRecipe.java:61)
    at org.apache.xbean.recipe.AbstractRecipe.create(AbstractRecipe.java:49)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.createComponentInstance(XBeanComponentBuilder.java:161)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.build(XBeanComponentBuilder.java:125)
    at org.codehaus.plexus.component.manager.AbstractComponentManager.createComponentInstance(AbstractComponentManager.java:181)
    at org.codehaus.plexus.component.manager.SingletonComponentManager.getComponent(SingletonComponentManager.java:67)
    at org.codehaus.plexus.DefaultComponentRegistry.getComponent(DefaultComponentRegistry.java:358)
    at org.codehaus.plexus.DefaultComponentRegistry.lookup(DefaultComponentRegistry.java:178)
    at org.codehaus.plexus.DefaultPlexusContainer.lookup(DefaultPlexusContainer.java:383)
    at com.dianping.cat.Cat.setContainer(Cat.java:596)
    at com.dianping.cat.CatClientModule.execute(CatClientModule.java:32)
    at org.unidal.initialization.AbstractModule.initialize(AbstractModule.java:10)
    at org.unidal.initialization.DefaultModuleInitializer.executeModule(DefaultModuleInitializer.java:59)
    at org.unidal.initialization.DefaultModuleInitializer.execute(DefaultModuleInitializer.java:42)
    at com.dianping.cat.Cat.initialize(Cat.java:183)
    at com.dianping.cat.Cat.initialize(Cat.java:168)
    at com.dianping.cat.Cat.checkAndInitialize(Cat.java:66)
    at com.dianping.cat.Cat.getProducer(Cat.java:155)
    at com.dianping.cat.Cat.newTransaction(Cat.java:556)
    at com.ctrip.framework.apollo.internals.ConfigServiceLocator.updateConfigServices(ConfigServiceLocator.java:112)
    at com.ctrip.framework.apollo.internals.ConfigServiceLocator.tryUpdateConfigServices(ConfigServiceLocator.java:83)
    at com.ctrip.framework.apollo.internals.ConfigServiceLocator.initialize(ConfigServiceLocator.java:64)
    at org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializePhase.execute(InitializePhase.java:33)
    at org.codehaus.plexus.lifecycle.AbstractLifecycleHandler.start(AbstractLifecycleHandler.java:96)
    at org.codehaus.plexus.component.manager.AbstractComponentManager.start(AbstractComponentManager.java:167)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.startComponentLifecycle(XBeanComponentBuilder.java:285)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.build(XBeanComponentBuilder.java:131)
    at org.codehaus.plexus.component.manager.AbstractComponentManager.createComponentInstance(AbstractComponentManager.java:181)
    at org.codehaus.plexus.component.manager.SingletonComponentManager.getComponent(SingletonComponentManager.java:67)
    at org.codehaus.plexus.DefaultComponentRegistry.getComponent(DefaultComponentRegistry.java:358)
    at org.codehaus.plexus.DefaultComponentRegistry.lookup(DefaultComponentRegistry.java:178)
    at org.codehaus.plexus.DefaultPlexusContainer.lookup(DefaultPlexusContainer.java:383)
    at com.ctrip.framework.apollo.internals.RemoteConfigRepository.<init>(RemoteConfigRepository.java:78)
    at com.ctrip.framework.apollo.spi.DefaultConfigFactory.createRemoteConfigRepository(DefaultConfigFactory.java:59)
    at com.ctrip.framework.apollo.spi.DefaultConfigFactory.createLocalConfigRepository(DefaultConfigFactory.java:55)
    at com.ctrip.framework.apollo.spi.DefaultConfigFactory.create(DefaultConfigFactory.java:31)
    at com.ctrip.framework.apollo.internals.DefaultConfigManager.getConfig(DefaultConfigManager.java:38)
    at com.ctrip.framework.apollo.ConfigService.getConfig(ConfigService.java:42)
    at com.ctrip.framework.apollo.ConfigService.getAppConfig(ConfigService.java:33)
    at com.uxin.feerate.domain.manager.impl.CallManagerImplTest.testConf(CallManagerImplTest.java:42)
    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
    at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    at java.lang.reflect.Method.invoke(Method.java:498)
    at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
    at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
    at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
    at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
    at org.springframework.test.context.junit4.statements.RunBeforeTestMethodCallbacks.evaluate(RunBeforeTestMethodCallbacks.java:75)
    at org.springframework.test.context.junit4.statements.RunAfterTestMethodCallbacks.evaluate(RunAfterTestMethodCallbacks.java:86)
    at org.springframework.test.context.junit4.statements.SpringRepeat.evaluate(SpringRepeat.java:84)
    at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
    at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:252)
    at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:94)
    at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
    at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
    at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
    at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
    at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
    at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
    at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:70)
    at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
    at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:191)
    at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
    at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:117)
    at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:42)
    at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:262)
    at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:84)
java.lang.NullPointerException
    at com.dianping.cat.message.io.ChannelManager.initChannel(ChannelManager.java:222)
    at com.dianping.cat.message.io.ChannelManager.<init>(ChannelManager.java:100)
    at com.dianping.cat.message.io.TcpSocketSender.initialize(TcpSocketSender.java:104)
    at com.dianping.cat.message.io.DefaultTransportManager.initialize(DefaultTransportManager.java:57)
    at org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializePhase.execute(InitializePhase.java:33)
    at org.codehaus.plexus.lifecycle.AbstractLifecycleHandler.start(AbstractLifecycleHandler.java:96)
    at org.codehaus.plexus.component.manager.AbstractComponentManager.start(AbstractComponentManager.java:167)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.startComponentLifecycle(XBeanComponentBuilder.java:285)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.build(XBeanComponentBuilder.java:131)
    at org.codehaus.plexus.component.manager.AbstractComponentManager.createComponentInstance(AbstractComponentManager.java:181)
    at org.codehaus.plexus.component.manager.SingletonComponentManager.getComponent(SingletonComponentManager.java:67)
    at org.codehaus.plexus.DefaultComponentRegistry.getComponent(DefaultComponentRegistry.java:358)
    at org.codehaus.plexus.DefaultComponentRegistry.lookup(DefaultComponentRegistry.java:178)
    at org.codehaus.plexus.DefaultPlexusContainer.lookup(DefaultPlexusContainer.java:393)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder$RequirementRecipe.internalCreate(XBeanComponentBuilder.java:404)
    at org.apache.xbean.recipe.AbstractRecipe.create(AbstractRecipe.java:96)
    at org.apache.xbean.recipe.RecipeHelper.convert(RecipeHelper.java:167)
    at org.apache.xbean.recipe.ObjectRecipe.setProperty(ObjectRecipe.java:515)
    at org.apache.xbean.recipe.ObjectRecipe.setProperties(ObjectRecipe.java:383)
    at org.apache.xbean.recipe.ObjectRecipe.internalCreate(ObjectRecipe.java:298)
    at org.apache.xbean.recipe.AbstractRecipe.create(AbstractRecipe.java:96)
    at org.apache.xbean.recipe.AbstractRecipe.create(AbstractRecipe.java:61)
    at org.apache.xbean.recipe.AbstractRecipe.create(AbstractRecipe.java:49)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.createComponentInstance(XBeanComponentBuilder.java:161)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.build(XBeanComponentBuilder.java:125)
    at org.codehaus.plexus.component.manager.AbstractComponentManager.createComponentInstance(AbstractComponentManager.java:181)
    at org.codehaus.plexus.component.manager.SingletonComponentManager.getComponent(SingletonComponentManager.java:67)
    at org.codehaus.plexus.DefaultComponentRegistry.getComponent(DefaultComponentRegistry.java:358)
    at org.codehaus.plexus.DefaultComponentRegistry.lookup(DefaultComponentRegistry.java:178)
    at org.codehaus.plexus.DefaultPlexusContainer.lookup(DefaultPlexusContainer.java:383)
    at com.dianping.cat.Cat.setContainer(Cat.java:596)
    at com.dianping.cat.CatClientModule.execute(CatClientModule.java:32)
    at org.unidal.initialization.AbstractModule.initialize(AbstractModule.java:10)
    at org.unidal.initialization.DefaultModuleInitializer.executeModule(DefaultModuleInitializer.java:59)
    at org.unidal.initialization.DefaultModuleInitializer.execute(DefaultModuleInitializer.java:42)
    at com.dianping.cat.Cat.initialize(Cat.java:183)
    at com.dianping.cat.Cat.initialize(Cat.java:168)
    at com.dianping.cat.Cat.checkAndInitialize(Cat.java:66)
    at com.dianping.cat.Cat.getProducer(Cat.java:155)
    at com.dianping.cat.Cat.newTransaction(Cat.java:556)
    at com.ctrip.framework.apollo.internals.ConfigServiceLocator.updateConfigServices(ConfigServiceLocator.java:112)
    at com.ctrip.framework.apollo.internals.ConfigServiceLocator.tryUpdateConfigServices(ConfigServiceLocator.java:83)
    at com.ctrip.framework.apollo.internals.ConfigServiceLocator.initialize(ConfigServiceLocator.java:64)
    at org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializePhase.execute(InitializePhase.java:33)
    at org.codehaus.plexus.lifecycle.AbstractLifecycleHandler.start(AbstractLifecycleHandler.java:96)
    at org.codehaus.plexus.component.manager.AbstractComponentManager.start(AbstractComponentManager.java:167)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.startComponentLifecycle(XBeanComponentBuilder.java:285)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.build(XBeanComponentBuilder.java:131)
    at org.codehaus.plexus.component.manager.AbstractComponentManager.createComponentInstance(AbstractComponentManager.java:181)
    at org.codehaus.plexus.component.manager.SingletonComponentManager.getComponent(SingletonComponentManager.java:67)
    at org.codehaus.plexus.DefaultComponentRegistry.getComponent(DefaultComponentRegistry.java:358)
    at org.codehaus.plexus.DefaultComponentRegistry.lookup(DefaultComponentRegistry.java:178)
    at org.codehaus.plexus.DefaultPlexusContainer.lookup(DefaultPlexusContainer.java:383)
    at com.ctrip.framework.apollo.internals.RemoteConfigRepository.<init>(RemoteConfigRepository.java:78)
    at com.ctrip.framework.apollo.spi.DefaultConfigFactory.createRemoteConfigRepository(DefaultConfigFactory.java:59)
    at com.ctrip.framework.apollo.spi.DefaultConfigFactory.createLocalConfigRepository(DefaultConfigFactory.java:55)
    at com.ctrip.framework.apollo.spi.DefaultConfigFactory.create(DefaultConfigFactory.java:31)
    at com.ctrip.framework.apollo.internals.DefaultConfigManager.getConfig(DefaultConfigManager.java:38)
    at com.ctrip.framework.apollo.ConfigService.getConfig(ConfigService.java:42)
    at com.ctrip.framework.apollo.ConfigService.getAppConfig(ConfigService.java:33)
    at com.uxin.feerate.domain.manager.impl.CallManagerImplTest.testConf(CallManagerImplTest.java:42)
    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
    at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    at java.lang.reflect.Method.invoke(Method.java:498)
    at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
    at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
    at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
    at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
    at org.springframework.test.context.junit4.statements.RunBeforeTestMethodCallbacks.evaluate(RunBeforeTestMethodCallbacks.java:75)
    at org.springframework.test.context.junit4.statements.RunAfterTestMethodCallbacks.evaluate(RunAfterTestMethodCallbacks.java:86)
    at org.springframework.test.context.junit4.statements.SpringRepeat.evaluate(SpringRepeat.java:84)
    at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
    at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:252)
    at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:94)
    at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
    at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
    at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
    at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
    at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
    at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
    at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:70)
    at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
    at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:191)
    at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
    at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:117)
    at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:42)
    at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:262)
    at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:84)
java.lang.NullPointerException
    at com.dianping.cat.message.io.ChannelManager.initChannel(ChannelManager.java:222)
    at com.dianping.cat.message.io.ChannelManager.<init>(ChannelManager.java:100)
    at com.dianping.cat.message.io.TcpSocketSender.initialize(TcpSocketSender.java:104)
    at com.dianping.cat.message.io.DefaultTransportManager.initialize(DefaultTransportManager.java:57)
    at org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializePhase.execute(InitializePhase.java:33)
    at org.codehaus.plexus.lifecycle.AbstractLifecycleHandler.start(AbstractLifecycleHandler.java:96)
    at org.codehaus.plexus.component.manager.AbstractComponentManager.start(AbstractComponentManager.java:167)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.startComponentLifecycle(XBeanComponentBuilder.java:285)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.build(XBeanComponentBuilder.java:131)
    at org.codehaus.plexus.component.manager.AbstractComponentManager.createComponentInstance(AbstractComponentManager.java:181)
    at org.codehaus.plexus.component.manager.SingletonComponentManager.getComponent(SingletonComponentManager.java:67)
    at org.codehaus.plexus.DefaultComponentRegistry.getComponent(DefaultComponentRegistry.java:358)
    at org.codehaus.plexus.DefaultComponentRegistry.lookup(DefaultComponentRegistry.java:178)
    at org.codehaus.plexus.DefaultPlexusContainer.lookup(DefaultPlexusContainer.java:393)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder$RequirementRecipe.internalCreate(XBeanComponentBuilder.java:404)
    at org.apache.xbean.recipe.AbstractRecipe.create(AbstractRecipe.java:96)
    at org.apache.xbean.recipe.RecipeHelper.convert(RecipeHelper.java:167)
    at org.apache.xbean.recipe.ObjectRecipe.setProperty(ObjectRecipe.java:515)
    at org.apache.xbean.recipe.ObjectRecipe.setProperties(ObjectRecipe.java:383)
    at org.apache.xbean.recipe.ObjectRecipe.internalCreate(ObjectRecipe.java:298)
    at org.apache.xbean.recipe.AbstractRecipe.create(AbstractRecipe.java:96)
    at org.apache.xbean.recipe.AbstractRecipe.create(AbstractRecipe.java:61)
    at org.apache.xbean.recipe.AbstractRecipe.create(AbstractRecipe.java:49)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.createComponentInstance(XBeanComponentBuilder.java:161)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.build(XBeanComponentBuilder.java:125)
    at org.codehaus.plexus.component.manager.AbstractComponentManager.createComponentInstance(AbstractComponentManager.java:181)
    at org.codehaus.plexus.component.manager.SingletonComponentManager.getComponent(SingletonComponentManager.java:67)
    at org.codehaus.plexus.DefaultComponentRegistry.getComponent(DefaultComponentRegistry.java:358)
    at org.codehaus.plexus.DefaultComponentRegistry.lookup(DefaultComponentRegistry.java:178)
    at org.codehaus.plexus.DefaultPlexusContainer.lookup(DefaultPlexusContainer.java:383)
    at com.dianping.cat.Cat.setContainer(Cat.java:596)
    at com.dianping.cat.CatClientModule.execute(CatClientModule.java:32)
    at org.unidal.initialization.AbstractModule.initialize(AbstractModule.java:10)
    at org.unidal.initialization.DefaultModuleInitializer.executeModule(DefaultModuleInitializer.java:59)
    at org.unidal.initialization.DefaultModuleInitializer.execute(DefaultModuleInitializer.java:42)
    at com.dianping.cat.Cat.initialize(Cat.java:183)
    at com.dianping.cat.Cat.initialize(Cat.java:168)
    at com.dianping.cat.Cat.checkAndInitialize(Cat.java:66)
    at com.dianping.cat.Cat.getProducer(Cat.java:155)
    at com.dianping.cat.Cat.newTransaction(Cat.java:556)
    at com.ctrip.framework.apollo.internals.ConfigServiceLocator.updateConfigServices(ConfigServiceLocator.java:112)
    at com.ctrip.framework.apollo.internals.ConfigServiceLocator.tryUpdateConfigServices(ConfigServiceLocator.java:83)
    at com.ctrip.framework.apollo.internals.ConfigServiceLocator.initialize(ConfigServiceLocator.java:64)
    at org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializePhase.execute(InitializePhase.java:33)
    at org.codehaus.plexus.lifecycle.AbstractLifecycleHandler.start(AbstractLifecycleHandler.java:96)
    at org.codehaus.plexus.component.manager.AbstractComponentManager.start(AbstractComponentManager.java:167)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.startComponentLifecycle(XBeanComponentBuilder.java:285)
    at org.codehaus.plexus.component.builder.XBeanComponentBuilder.build(XBeanComponentBuilder.java:131)
    at org.codehaus.plexus.component.manager.AbstractComponentManager.createComponentInstance(AbstractComponentManager.java:181)
    at org.codehaus.plexus.component.manager.SingletonComponentManager.getComponent(SingletonComponentManager.java:67)
    at org.codehaus.plexus.DefaultComponentRegistry.getComponent(DefaultComponentRegistry.java:358)
    at org.codehaus.plexus.DefaultComponentRegistry.lookup(DefaultComponentRegistry.java:178)
    at org.codehaus.plexus.DefaultPlexusContainer.lookup(DefaultPlexusContainer.java:383)
    at com.ctrip.framework.apollo.internals.RemoteConfigRepository.<init>(RemoteConfigRepository.java:78)
    at com.ctrip.framework.apollo.spi.DefaultConfigFactory.createRemoteConfigRepository(DefaultConfigFactory.java:59)
    at com.ctrip.framework.apollo.spi.DefaultConfigFactory.createLocalConfigRepository(DefaultConfigFactory.java:55)
    at com.ctrip.framework.apollo.spi.DefaultConfigFactory.create(DefaultConfigFactory.java:31)
    at com.ctrip.framework.apollo.internals.DefaultConfigManager.getConfig(DefaultConfigManager.java:38)
    at com.ctrip.framework.apollo.ConfigService.getConfig(ConfigService.java:42)
    at com.ctrip.framework.apollo.ConfigService.getAppConfig(ConfigService.java:33)
    at com.uxin.feerate.domain.manager.impl.CallManagerImplTest.testConf(CallManagerImplTest.java:42)
    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
    at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    at java.lang.reflect.Method.invoke(Method.java:498)
    at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
    at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
    at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
    at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
    at org.springframework.test.context.junit4.statements.RunBeforeTestMethodCallbacks.evaluate(RunBeforeTestMethodCallbacks.java:75)
    at org.springframework.test.context.junit4.statements.RunAfterTestMethodCallbacks.evaluate(RunAfterTestMethodCallbacks.java:86)
    at org.springframework.test.context.junit4.statements.SpringRepeat.evaluate(SpringRepeat.java:84)
    at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
    at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:252)
    at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:94)
    at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
    at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
    at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
    at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
    at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
    at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
    at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:70)
    at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
    at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:191)
    at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
    at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:117)
    at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:42)
    at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:262)
    at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:84)
CAT client now tries to visit router service. http://cat.ctripcorp.com:80/cat/s/router?domain=100002&ip=172.16.1.18&op=json
java.net.UnknownHostException: cat.ctripcorp.com
    at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:184)
    at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)
    at java.net.Socket.connect(Socket.java:589)
    at sun.net.NetworkClient.doConnect(NetworkClient.java:175)
    at sun.net.www.http.HttpClient.openServer(HttpClient.java:432)
    at sun.net.www.http.HttpClient.openServer(HttpClient.java:527)
    at sun.net.www.http.HttpClient.<init>(HttpClient.java:211)
    at sun.net.www.http.HttpClient.New(HttpClient.java:308)
    at sun.net.www.http.HttpClient.New(HttpClient.java:326)
    at sun.net.www.protocol.http.HttpURLConnection.getNewHttpClient(HttpURLConnection.java:1202)
    at sun.net.www.protocol.http.HttpURLConnection.plainConnect0(HttpURLConnection.java:1138)
    at sun.net.www.protocol.http.HttpURLConnection.plainConnect(HttpURLConnection.java:1032)
    at sun.net.www.protocol.http.HttpURLConnection.connect(HttpURLConnection.java:966)
    at sun.net.www.protocol.http.HttpURLConnection.getInputStream0(HttpURLConnection.java:1546)
    at sun.net.www.protocol.http.HttpURLConnection.getInputStream(HttpURLConnection.java:1474)
    at org.unidal.helper.Urls$UrlIO.openStream(Urls.java:69)
    at org.unidal.helper.Urls$UrlIO.openStream(Urls.java:45)
    at com.dianping.cat.message.io.ChannelManager.loadServerConfig(ChannelManager.java:297)
    at com.dianping.cat.message.io.ChannelManager.routerConfigChanged(ChannelManager.java:364)
    at com.dianping.cat.message.io.ChannelManager.checkServerChanged(ChannelManager.java:122)
    at com.dianping.cat.message.io.ChannelManager.run(ChannelManager.java:378)
    at java.lang.Thread.run(Thread.java:745)
    at org.unidal.helper.Threads$RunnableThread.run(Threads.java:294)
java.net.UnknownHostException: cat.ctripcorp.com
    at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:184)
    at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)
    at java.net.Socket.connect(Socket.java:589)
    at sun.net.NetworkClient.doConnect(NetworkClient.java:175)
    at sun.net.www.http.HttpClient.openServer(HttpClient.java:432)
    at sun.net.www.http.HttpClient.openServer(HttpClient.java:527)
    at sun.net.www.http.HttpClient.<init>(HttpClient.java:211)
    at sun.net.www.http.HttpClient.New(HttpClient.java:308)
    at sun.net.www.http.HttpClient.New(HttpClient.java:326)
    at sun.net.www.protocol.http.HttpURLConnection.getNewHttpClient(HttpURLConnection.java:1202)
    at sun.net.www.protocol.http.HttpURLConnection.plainConnect0(HttpURLConnection.java:1138)
    at sun.net.www.protocol.http.HttpURLConnection.plainConnect(HttpURLConnection.java:1032)
    at sun.net.www.protocol.http.HttpURLConnection.connect(HttpURLConnection.java:966)
    at sun.net.www.protocol.http.HttpURLConnection.getInputStream0(HttpURLConnection.java:1546)
    at sun.net.www.protocol.http.HttpURLConnection.getInputStream(HttpURLConnection.java:1474)
    at org.unidal.helper.Urls$UrlIO.openStream(Urls.java:69)
    at org.unidal.helper.Urls$UrlIO.openStream(Urls.java:45)
    at com.dianping.cat.message.io.ChannelManager.loadServerConfig(ChannelManager.java:297)
    at com.dianping.cat.message.io.ChannelManager.routerConfigChanged(ChannelManager.java:364)
    at com.dianping.cat.message.io.ChannelManager.checkServerChanged(ChannelManager.java:122)
    at com.dianping.cat.message.io.ChannelManager.run(ChannelManager.java:378)
    at java.lang.Thread.run(Thread.java:745)
    at org.unidal.helper.Threads$RunnableThread.run(Threads.java:294)


## nobodyiam (28 Oct 2016)

@yangl, 你是使用了我们Quick Start直接打好的包运行的，还是通过分布式部署指南的文档自己编译运行的？

看了下，应该是cat客户端在运行时没有被禁用，不过不影响程序运行的。


## nobodyiam (28 Oct 2016)

@yangl, 看了下，如果是自己编译的客户端，目前应该是还没有剥离cat的，可能需要一点手工修改的步骤：

1.修改[apollo-client/pom.xml](https://github.com/ctripcorp/apollo/blob/master/apollo-client/pom.xml)，把apollo-buildtools的依赖的scope去掉

~~<scope>test</scope>~~

``` xml
<dependency>
    <groupId>com.ctrip.framework.apollo</groupId>
    <artifactId>apollo-buildtools</artifactId>
</dependency>
```

2.把[apollo-client/src/test/resources/META-INF/plexus/plexus.xml](https://github.com/ctripcorp/apollo/blob/master/apollo-client/src/test/resources/META-INF/plexus/plexus.xml)复制到[apollo-client/src/main/resources/META-INF/](https://github.com/ctripcorp/apollo/tree/master/apollo-client/src/main/resources/META-INF/plexus)下

3.重新编译打包apollo-client


## yangl (28 Oct 2016)

按那个分步式部署方式来的，自己写个例子启动的。
但是也没有获取到配置中心上边设置的kv


## nobodyiam (28 Oct 2016)

如果运行客户端遇到问题，在你的项目中可以配置一下log，如[log4j2.xml](https://github.com/ctripcorp/apollo/blob/master/apollo-demo/src/main/resources/log4j2.xml)，并把apollo的level设置为DEBUG来查看更详细日志信息

``` xml
<logger name="com.ctrip.framework.apollo" additivity="false" level="trace">
    <AppenderRef ref="Async" level="DEBUG"/>
</logger>
```

我看到你的客户端日志里面env=PRO，那么在[build.sh](https://github.com/ctripcorp/apollo/blob/master/scripts/build.sh)是否设置了pro_meta为正确的路径？

``` sh
# meta server url
dev_meta=http://localhost:8080
fat_meta=http://localhost:8080
uat_meta=http://localhost:8080
pro_meta=http://localhost:8080
```

如果pro_meta设置对的话，那么就确认一下你的应用（100002）的配置是否发布了？


## nobodyiam (28 Oct 2016)

@yangl，另外如果是初次使用的话，建议可以先通过[Quick Start](https://github.com/ctripcorp/apollo/wiki/Quick-Start)来体验一下，那里面的包我们都已经事先配置好，基本上可以直接开箱使用的。


## yangl (28 Oct 2016)

嗯，可以了，谢谢。
有时候我想把Eureak下的Instance Info 下的ipAddr    10.25.156.103这个地址暴露成公网地址？在哪能改下呢？


## nobodyiam (30 Oct 2016)

注册服务的时候我们目前就是配置`preferIpAddress: true`，配置文件参见[bootstrap.yml](https://github.com/ctripcorp/apollo/blob/master/apollo-configservice/src/main/resources/bootstrap.yml)。

Eureka的instance默认是把自己本机的IP注册上去的，你说的公网地址是你本机的IP吗？还是另一台gateway或slb的机器？


## yangl (30 Oct 2016)

Eureka的instance所在的机子上的公网IP（eth1），还有一块内网eth0，我把preferIpAddress改成false也一样，还是10.25.156.103，加了个hostname对应的公网ip也一样子！


## nobodyiam (30 Oct 2016)

可以试试`ignoredInterfaces`配置

http://projects.spring.io/spring-cloud/spring-cloud.html#ignore-network-interfaces


## yangl (31 Oct 2016)

谢谢，可以了，我们打算在11月中旬上这个哈！
另有没有交流群组？


## nobodyiam (31 Oct 2016)

@yangl，我建了一个QQ群，我们以后可以通过QQ群讨论~

![tech-support-qq](https://raw.githubusercontent.com/ctripcorp/apollo/master/doc/images/tech-support-qq.png)


