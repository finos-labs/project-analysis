#[JaxRS] Add "validation-api" dependency in jetty 

Owner: OpenAPITools

Repo: openapi-generator

Labels: Enhancement: CI/Test Server: Java 

## jmini (13 May 2018)

##### Description

In a generated project (Java Server with JaxRS), you should be able to run a jetty with 

```
mvn clean package jetty:run
```

Stacktrace:

```
WARNING: HK2 service reification failed for [org.glassfish.jersey.server.model.ResourceMethodInvoker$Builder] with an exception:
MultiException stack 1 of 1
java.lang.NoClassDefFoundError: javax/validation/Validator
	at java.lang.ClassLoader.defineClass1(Native Method)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:763)
	at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
	at java.net.URLClassLoader.defineClass(URLClassLoader.java:467)
	at java.net.URLClassLoader.access$100(URLClassLoader.java:73)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:368)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:362)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(URLClassLoader.java:361)
	at org.eclipse.jetty.webapp.WebAppClassLoader.findClass(WebAppClassLoader.java:510)
	at org.eclipse.jetty.webapp.WebAppClassLoader.loadClass(WebAppClassLoader.java:441)
	at org.eclipse.jetty.webapp.WebAppClassLoader.loadClass(WebAppClassLoader.java:403)
	at java.lang.Class.forName0(Native Method)
	at java.lang.Class.forName(Class.java:348)
	at sun.reflect.generics.factory.CoreReflectionFactory.makeNamedType(CoreReflectionFactory.java:114)
	at sun.reflect.generics.visitor.Reifier.visitClassTypeSignature(Reifier.java:125)
	at sun.reflect.generics.tree.ClassTypeSignature.accept(ClassTypeSignature.java:49)
	at sun.reflect.generics.visitor.Reifier.reifyTypeArguments(Reifier.java:68)
	at sun.reflect.generics.visitor.Reifier.visitClassTypeSignature(Reifier.java:138)
	at sun.reflect.generics.tree.ClassTypeSignature.accept(ClassTypeSignature.java:49)
	at sun.reflect.generics.repository.FieldRepository.getGenericType(FieldRepository.java:85)
	at java.lang.reflect.Field.getGenericType(Field.java:247)
	at org.glassfish.hk2.utilities.reflection.ReflectionHelper.resolveField(ReflectionHelper.java:153)
	at org.jvnet.hk2.internal.Utilities.getFieldInjectees(Utilities.java:1861)
	at org.jvnet.hk2.internal.ClazzCreator.initialize(ClazzCreator.java:160)
	at org.jvnet.hk2.internal.ClazzCreator.initialize(ClazzCreator.java:179)
	at org.jvnet.hk2.internal.SystemDescriptor.internalReify(SystemDescriptor.java:723)
	at org.jvnet.hk2.internal.SystemDescriptor.reify(SystemDescriptor.java:678)
	at org.jvnet.hk2.internal.ServiceLocatorImpl.reifyDescriptor(ServiceLocatorImpl.java:458)
	at org.jvnet.hk2.internal.ServiceLocatorImpl.narrow(ServiceLocatorImpl.java:2205)
	at org.jvnet.hk2.internal.ServiceLocatorImpl.igdCacheCompute(ServiceLocatorImpl.java:1150)
	at org.jvnet.hk2.internal.ServiceLocatorImpl.access$400(ServiceLocatorImpl.java:122)
	at org.jvnet.hk2.internal.ServiceLocatorImpl$8.compute(ServiceLocatorImpl.java:1144)
	at org.jvnet.hk2.internal.ServiceLocatorImpl$8.compute(ServiceLocatorImpl.java:1141)
	at org.glassfish.hk2.utilities.cache.internal.WeakCARCacheImpl.compute(WeakCARCacheImpl.java:116)
	at org.jvnet.hk2.internal.ServiceLocatorImpl.internalGetDescriptor(ServiceLocatorImpl.java:1215)
	at org.jvnet.hk2.internal.ServiceLocatorImpl.internalGetService(ServiceLocatorImpl.java:762)
	at org.jvnet.hk2.internal.ServiceLocatorImpl.getService(ServiceLocatorImpl.java:704)
	at org.glassfish.jersey.server.internal.routing.Routing$Builder.buildStage(Routing.java:194)
	at org.glassfish.jersey.server.ApplicationHandler.initialize(ApplicationHandler.java:587)
	at org.glassfish.jersey.server.ApplicationHandler.access$500(ApplicationHandler.java:184)
	at org.glassfish.jersey.server.ApplicationHandler$3.call(ApplicationHandler.java:350)
	at org.glassfish.jersey.server.ApplicationHandler$3.call(ApplicationHandler.java:347)
	at org.glassfish.jersey.internal.Errors.process(Errors.java:315)
	at org.glassfish.jersey.internal.Errors.process(Errors.java:297)
	at org.glassfish.jersey.internal.Errors.processWithException(Errors.java:255)
	at org.glassfish.jersey.server.ApplicationHandler.<init>(ApplicationHandler.java:347)
	at org.glassfish.jersey.servlet.WebComponent.<init>(WebComponent.java:392)
	at org.glassfish.jersey.servlet.ServletContainer.init(ServletContainer.java:177)
	at org.glassfish.jersey.servlet.ServletContainer.init(ServletContainer.java:369)
	at javax.servlet.GenericServlet.init(GenericServlet.java:244)
	at org.eclipse.jetty.servlet.ServletHolder.initServlet(ServletHolder.java:612)
	at org.eclipse.jetty.servlet.ServletHolder.initialize(ServletHolder.java:395)
	at org.eclipse.jetty.servlet.ServletHandler.initialize(ServletHandler.java:871)
	at org.eclipse.jetty.servlet.ServletContextHandler.startContext(ServletContextHandler.java:298)
	at org.eclipse.jetty.webapp.WebAppContext.startWebapp(WebAppContext.java:1349)
	at org.eclipse.jetty.maven.plugin.JettyWebAppContext.startWebapp(JettyWebAppContext.java:296)
	at org.eclipse.jetty.webapp.WebAppContext.startContext(WebAppContext.java:1342)
	at org.eclipse.jetty.server.handler.ContextHandler.doStart(ContextHandler.java:741)
	at org.eclipse.jetty.webapp.WebAppContext.doStart(WebAppContext.java:505)
	at org.eclipse.jetty.maven.plugin.JettyWebAppContext.doStart(JettyWebAppContext.java:365)
	at org.eclipse.jetty.util.component.AbstractLifeCycle.start(AbstractLifeCycle.java:68)
	at org.eclipse.jetty.util.component.ContainerLifeCycle.start(ContainerLifeCycle.java:132)
	at org.eclipse.jetty.util.component.ContainerLifeCycle.doStart(ContainerLifeCycle.java:114)
	at org.eclipse.jetty.server.handler.AbstractHandler.doStart(AbstractHandler.java:61)
	at org.eclipse.jetty.server.handler.ContextHandlerCollection.doStart(ContextHandlerCollection.java:163)
	at org.eclipse.jetty.util.component.AbstractLifeCycle.start(AbstractLifeCycle.java:68)
	at org.eclipse.jetty.util.component.ContainerLifeCycle.start(ContainerLifeCycle.java:132)
	at org.eclipse.jetty.util.component.ContainerLifeCycle.doStart(ContainerLifeCycle.java:114)
	at org.eclipse.jetty.server.handler.AbstractHandler.doStart(AbstractHandler.java:61)
	at org.eclipse.jetty.util.component.AbstractLifeCycle.start(AbstractLifeCycle.java:68)
	at org.eclipse.jetty.util.component.ContainerLifeCycle.start(ContainerLifeCycle.java:132)
	at org.eclipse.jetty.server.Server.start(Server.java:387)
	at org.eclipse.jetty.util.component.ContainerLifeCycle.doStart(ContainerLifeCycle.java:114)
	at org.eclipse.jetty.server.handler.AbstractHandler.doStart(AbstractHandler.java:61)
	at org.eclipse.jetty.server.Server.doStart(Server.java:354)
	at org.eclipse.jetty.maven.plugin.JettyServer.doStart(JettyServer.java:73)
	at org.eclipse.jetty.util.component.AbstractLifeCycle.start(AbstractLifeCycle.java:68)
	at org.eclipse.jetty.maven.plugin.AbstractJettyMojo.startJetty(AbstractJettyMojo.java:534)
	at org.eclipse.jetty.maven.plugin.AbstractJettyMojo.execute(AbstractJettyMojo.java:357)
	at org.eclipse.jetty.maven.plugin.JettyRunMojo.execute(JettyRunMojo.java:167)
	at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo(DefaultBuildPluginManager.java:134)
	at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor.java:208)
	at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor.java:154)
	at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor.java:146)
	at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject(LifecycleModuleBuilder.java:117)
	at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject(LifecycleModuleBuilder.java:81)
	at org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder.build(SingleThreadedBuilder.java:51)
	at org.apache.maven.lifecycle.internal.LifecycleStarter.execute(LifecycleStarter.java:128)
	at org.apache.maven.DefaultMaven.doExecute(DefaultMaven.java:309)
	at org.apache.maven.DefaultMaven.doExecute(DefaultMaven.java:194)
	at org.apache.maven.DefaultMaven.execute(DefaultMaven.java:107)
	at org.apache.maven.cli.MavenCli.execute(MavenCli.java:955)
	at org.apache.maven.cli.MavenCli.doMain(MavenCli.java:290)
	at org.apache.maven.cli.MavenCli.main(MavenCli.java:194)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced(Launcher.java:289)
	at org.codehaus.plexus.classworlds.launcher.Launcher.launch(Launcher.java:229)
	at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode(Launcher.java:415)
	at org.codehaus.plexus.classworlds.launcher.Launcher.main(Launcher.java:356)
Caused by: java.lang.ClassNotFoundException: javax.validation.Validator
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at org.eclipse.jetty.webapp.WebAppClassLoader.findClass(WebAppClassLoader.java:510)
	at org.eclipse.jetty.webapp.WebAppClassLoader.loadClass(WebAppClassLoader.java:441)
	at org.eclipse.jetty.webapp.WebAppClassLoader.loadClass(WebAppClassLoader.java:403)
	... 103 more
```

I had already fixed it in Swagger v3, see https://github.com/swagger-api/swagger-codegen-generators/commit/11b6cfaecbd74d2773ad56a9c63dceaf49f3b0b2. This needs to be ported to this project.


