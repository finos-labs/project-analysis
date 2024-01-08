#删除了分类目录中的默认分类，打开后台的文章相关都会报错

Owner: halo-dev

Repo: halo

Labels: help wanted 

## huanyusun (05 Jun 2018)

1. 后台菜单下的所有文章不能点开
```bash
2018-06-06 00:20:43.100 ERROR 966 --- [XNIO-2 task-4] freemarker.runtime                       : Error executing FreeMarker template

freemarker.core.InvalidReferenceException: The following has evaluated to null or missing:
==> post.postViews  [in template "admin/admin_post.ftl" at line 91, column 110]

----
Tip: It's the step after the last dot that caused this error, not those before it.
----
Tip: If the failing expression is known to legally refer to something that's sometimes null or missing, either specify a default value like myOptionalVar!myDefault, or use <#if myOptionalVar??>when-present<#else>when-missing</#if>. (These only cover the last step of the expression; to cover the whole expression, use parenthesis: (myOptionalVar.foo)!myDefault, (myOptionalVar.foo)??
----

----
FTL stack trace ("~" means nesting-related):
	- Failed at: ${post.postViews}  [in template "admin/admin_post.ftl" at line 91, column 108]
----
	at freemarker.core.InvalidReferenceException.getInstance(InvalidReferenceException.java:134) ~[freemarker-2.3.28.jar:2.3.28]
	at freemarker.core.EvalUtil.coerceModelToTextualCommon(EvalUtil.java:467) ~[freemarker-2.3.28.jar:2.3.28]
	at freemarker.core.EvalUtil.coerceModelToStringOrMarkup(EvalUtil.java:389) ~[freemarker-2.3.28.jar:2.3.28]
	at freemarker.core.EvalUtil.coerceModelToStringOrMarkup(EvalUtil.java:358) ~[freemarker-2.3.28.jar:2.3.28]
	at freemarker.core.DollarVariable.calculateInterpolatedStringOrMarkup(DollarVariable.java:100) ~[freemarker-2.3.28.jar:2.3.28]
	at freemarker.core.DollarVariable.accept(DollarVariable.java:63) ~[freemarker-2.3.28.jar:2.3.28]
	at freemarker.core.Environment.visit(Environment.java:366) [freemarker-2.3.28.jar:2.3.28]
	at freemarker.core.IteratorBlock$IterationContext.executedNestedContentForCollOrSeqListing(IteratorBlock.java:317) [freemarker-2.3.28.jar:2.3.28]
	at freemarker.core.IteratorBlock$IterationContext.executeNestedContent(IteratorBlock.java:271) [freemarker-2.3.28.jar:2.3.28]
	at freemarker.core.IteratorBlock$IterationContext.accept(IteratorBlock.java:242) [freemarker-2.3.28.jar:2.3.28]
	at freemarker.core.Environment.visitIteratorBlock(Environment.java:642) [freemarker-2.3.28.jar:2.3.28]
	at freemarker.core.IteratorBlock.acceptWithResult(IteratorBlock.java:107) [freemarker-2.3.28.jar:2.3.28]
	at freemarker.core.IteratorBlock.accept(IteratorBlock.java:93) [freemarker-2.3.28.jar:2.3.28]
	at freemarker.core.Environment.visit(Environment.java:330) [freemarker-2.3.28.jar:2.3.28]
	at freemarker.core.Environment.visit(Environment.java:372) [freemarker-2.3.28.jar:2.3.28]
	at freemarker.core.Environment.visitAndTransform(Environment.java:495) [freemarker-2.3.28.jar:2.3.28]
	at freemarker.core.CompressedBlock.accept(CompressedBlock.java:42) [freemarker-2.3.28.jar:2.3.28]
	at freemarker.core.Environment.visit(Environment.java:330) [freemarker-2.3.28.jar:2.3.28]
	at freemarker.core.Environment.process(Environment.java:309) [freemarker-2.3.28.jar:2.3.28]
	at freemarker.template.Template.process(Template.java:384) [freemarker-2.3.28.jar:2.3.28]
	at org.springframework.web.servlet.view.freemarker.FreeMarkerView.processTemplate(FreeMarkerView.java:396) [spring-webmvc-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at org.springframework.web.servlet.view.freemarker.FreeMarkerView.doRender(FreeMarkerView.java:309) [spring-webmvc-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at org.springframework.web.servlet.view.freemarker.FreeMarkerView.renderMergedTemplateModel(FreeMarkerView.java:257) [spring-webmvc-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at org.springframework.web.servlet.view.AbstractTemplateView.renderMergedOutputModel(AbstractTemplateView.java:165) [spring-webmvc-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at org.springframework.web.servlet.view.AbstractView.render(AbstractView.java:314) [spring-webmvc-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.render(DispatcherServlet.java:1325) [spring-webmvc-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.processDispatchResult(DispatcherServlet.java:1069) [spring-webmvc-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1008) [spring-webmvc-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925) [spring-webmvc-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974) [spring-webmvc-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:866) [spring-webmvc-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:687) [javax.servlet-api-3.1.0.jar:3.1.0]
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851) [spring-webmvc-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:790) [javax.servlet-api-3.1.0.jar:3.1.0]
	at io.undertow.servlet.handlers.ServletHandler.handleRequest(ServletHandler.java:74) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:129) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123) [druid-1.1.9.jar:1.1.9]
	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at cc.ryanc.halo.security.XssFilter.doFilter(XssFilter.java:34) [halo-latest.jar:latest]
	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200) [spring-web-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) [spring-web-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.FilterHandler.handleRequest(FilterHandler.java:84) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.security.ServletSecurityRoleHandler.handleRequest(ServletSecurityRoleHandler.java:62) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.ServletChain$1.handleRequest(ServletChain.java:64) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.ServletDispatchingHandler.handleRequest(ServletDispatchingHandler.java:36) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.security.SSLInformationAssociationHandler.handleRequest(SSLInformationAssociationHandler.java:132) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.security.ServletAuthenticationCallHandler.handleRequest(ServletAuthenticationCallHandler.java:57) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43) [undertow-core-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.security.handlers.AbstractConfidentialityHandler.handleRequest(AbstractConfidentialityHandler.java:46) [undertow-core-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.security.ServletConfidentialityConstraintHandler.handleRequest(ServletConfidentialityConstraintHandler.java:64) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.security.handlers.AuthenticationMechanismsHandler.handleRequest(AuthenticationMechanismsHandler.java:60) [undertow-core-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.security.CachedAuthenticatedSessionHandler.handleRequest(CachedAuthenticatedSessionHandler.java:77) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.security.handlers.AbstractSecurityContextAssociationHandler.handleRequest(AbstractSecurityContextAssociationHandler.java:43) [undertow-core-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43) [undertow-core-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43) [undertow-core-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.ServletInitialHandler.handleFirstRequest(ServletInitialHandler.java:292) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.ServletInitialHandler.access$100(ServletInitialHandler.java:81) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:138) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:135) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.core.ServletRequestContextThreadSetupAction$1.call(ServletRequestContextThreadSetupAction.java:48) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.core.ContextClassLoaderSetupAction$1.call(ContextClassLoaderSetupAction.java:43) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.ServletInitialHandler.dispatchRequest(ServletInitialHandler.java:272) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.ServletInitialHandler.access$000(ServletInitialHandler.java:81) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.ServletInitialHandler$1.handleRequest(ServletInitialHandler.java:104) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.server.Connectors.executeRootHandler(Connectors.java:336) [undertow-core-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.server.HttpServerExchange$1.run(HttpServerExchange.java:830) [undertow-core-1.4.23.Final.jar:1.4.23.Final]
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) [na:1.8.0_172]
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) [na:1.8.0_172]
	at java.lang.Thread.run(Thread.java:748) [na:1.8.0_172]
```
2.前台的文章打开都会报500错误
```bash
018-06-06 00:26:36.014 ERROR 966 --- [XNIO-2 task-16] io.undertow.request                      : UT005023: Exception handling request to /archives/nginx_gzip

org.springframework.web.util.NestedServletException: Request processing failed; nested exception is java.lang.NullPointerException
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:982) ~[spring-webmvc-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:866) ~[spring-webmvc-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:687) ~[javax.servlet-api-3.1.0.jar:3.1.0]
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851) ~[spring-webmvc-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:790) ~[javax.servlet-api-3.1.0.jar:3.1.0]
	at io.undertow.servlet.handlers.ServletHandler.handleRequest(ServletHandler.java:74) ~[undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:129) ~[undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123) ~[druid-1.1.9.jar:1.1.9]
	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61) ~[undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131) ~[undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at cc.ryanc.halo.security.XssFilter.doFilter(XssFilter.java:38) ~[halo-latest.jar:latest]
	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61) ~[undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131) ~[undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200) ~[spring-web-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) ~[spring-web-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61) ~[undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131) ~[undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.FilterHandler.handleRequest(FilterHandler.java:84) ~[undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.security.ServletSecurityRoleHandler.handleRequest(ServletSecurityRoleHandler.java:62) ~[undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.ServletChain$1.handleRequest(ServletChain.java:64) ~[undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.ServletDispatchingHandler.handleRequest(ServletDispatchingHandler.java:36) ~[undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.security.SSLInformationAssociationHandler.handleRequest(SSLInformationAssociationHandler.java:132) ~[undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.security.ServletAuthenticationCallHandler.handleRequest(ServletAuthenticationCallHandler.java:57) ~[undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43) ~[undertow-core-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.security.handlers.AbstractConfidentialityHandler.handleRequest(AbstractConfidentialityHandler.java:46) ~[undertow-core-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.security.ServletConfidentialityConstraintHandler.handleRequest(ServletConfidentialityConstraintHandler.java:64) ~[undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.security.handlers.AuthenticationMechanismsHandler.handleRequest(AuthenticationMechanismsHandler.java:60) ~[undertow-core-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.security.CachedAuthenticatedSessionHandler.handleRequest(CachedAuthenticatedSessionHandler.java:77) ~[undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.security.handlers.AbstractSecurityContextAssociationHandler.handleRequest(AbstractSecurityContextAssociationHandler.java:43) ~[undertow-core-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43) ~[undertow-core-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43) ~[undertow-core-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.ServletInitialHandler.handleFirstRequest(ServletInitialHandler.java:292) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.ServletInitialHandler.access$100(ServletInitialHandler.java:81) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:138) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:135) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.core.ServletRequestContextThreadSetupAction$1.call(ServletRequestContextThreadSetupAction.java:48) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.core.ContextClassLoaderSetupAction$1.call(ContextClassLoaderSetupAction.java:43) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.ServletInitialHandler.dispatchRequest(ServletInitialHandler.java:272) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.ServletInitialHandler.access$000(ServletInitialHandler.java:81) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.servlet.handlers.ServletInitialHandler$1.handleRequest(ServletInitialHandler.java:104) [undertow-servlet-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.server.Connectors.executeRootHandler(Connectors.java:336) [undertow-core-1.4.23.Final.jar:1.4.23.Final]
	at io.undertow.server.HttpServerExchange$1.run(HttpServerExchange.java:830) [undertow-core-1.4.23.Final.jar:1.4.23.Final]
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) [na:1.8.0_172]
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) [na:1.8.0_172]
	at java.lang.Thread.run(Thread.java:748) [na:1.8.0_172]
Caused by: java.lang.NullPointerException: null
	at cc.ryanc.halo.web.controller.front.FrontArchiveController.getPost(FrontArchiveController.java:124) ~[halo-latest.jar:latest]
	at sun.reflect.GeneratedMethodAccessor98.invoke(Unknown Source) ~[na:na]
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:1.8.0_172]
	at java.lang.reflect.Method.invoke(Method.java:498) ~[na:1.8.0_172]
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209) ~[spring-web-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136) ~[spring-web-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102) ~[spring-webmvc-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:877) ~[spring-webmvc-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:783) ~[spring-webmvc-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87) ~[spring-webmvc-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991) ~[spring-webmvc-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925) ~[spring-webmvc-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974) ~[spring-webmvc-5.0.5.RELEASE.jar:5.0.5.RELEASE]
	... 44 common frames omitted
```

## ruibaby (05 Jun 2018)

并不是因为你删除了默认分类导致的问题。
去数据库执行update halo_post set post_views=0就可以了，还不行就重启一下。
这是因为我很久之前在halo_post表加了个字段，导致之前的文章没有初始值。

