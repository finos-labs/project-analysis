#为什么会出现乱码？

Owner: alibaba

Repo: druid

Labels: 

## xiegengcai (23 Jul 2012)

org.springframework.dao.DuplicateKeyException: PreparedStatementCallback; SQL [update t_application set update_time=now(), app_name=?, en_name=?, version=?, developer=?, catalog_id=?, size=?, platform=?, support_languages=?, price=?, description=?, permission_desc=?, status=?, images=?, features=?, en_features=? where id=?]; Duplicate entry 'æ�•é±¼è¾¾äºº' for key 2; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry 'æ�•é±¼è¾¾äºº' for key 2
    at org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator.doTranslate(SQLErrorCodeSQLExceptionTranslator.java:241)
    at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:72)
    at org.springframework.jdbc.core.JdbcTemplate.execute(JdbcTemplate.java:603)
    at org.springframework.jdbc.core.JdbcTemplate.update(JdbcTemplate.java:812)
    at org.springframework.jdbc.core.JdbcTemplate.update(JdbcTemplate.java:868)
    at org.springframework.jdbc.core.JdbcTemplate.update(JdbcTemplate.java:876)
    at cn.com.carit.market.dao.impl.app.ApplicationDaoImpl.update(ApplicationDaoImpl.java:245)
    at cn.com.carit.market.service.impl.app.ApplicationServiceImpl.saveOrUpdate(ApplicationServiceImpl.java:84)
    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
    at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    at java.lang.reflect.Method.invoke(Method.java:616)
    at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:318)
    at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:183)
    at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:150)
    at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:110)
    at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:172)
    at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:202)
    at $Proxy15.saveOrUpdate(Unknown Source)
    at cn.com.carit.market.web.controller.app.ApplicationController.save(ApplicationController.java:145)
    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
    at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    at java.lang.reflect.Method.invoke(Method.java:616)
    at org.springframework.web.bind.annotation.support.HandlerMethodInvoker.invokeHandlerMethod(HandlerMethodInvoker.java:176)
    at org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter.invokeHandlerMethod(AnnotationMethodHandlerAdapter.java:436)
    at org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter.handle(AnnotationMethodHandlerAdapter.java:424)
    at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:923)
    at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:852)
    at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:882)
    at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:789)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:641)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:722)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:305)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:210)
    at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:88)
    at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:76)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:243)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:210)
    at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:225)
    at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:169)
    at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:472)
    at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:168)
    at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:98)
    at org.apache.catalina.valves.AccessLogValve.invoke(AccessLogValve.java:927)
    at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:118)
    at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:407)
    at org.apache.coyote.http11.AbstractHttp11Processor.process(AbstractHttp11Processor.java:999)
    at org.apache.coyote.AbstractProtocol$AbstractConnectionHandler.process(AbstractProtocol.java:565)
    at org.apache.tomcat.util.net.JIoEndpoint$SocketProcessor.run(JIoEndpoint.java:307)
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1110)
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:603)
    at java.lang.Thread.run(Thread.java:636)
Caused by: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry 'æ�•é±¼è¾¾äºº' for key 2
    at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
    at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:57)
    at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
    at java.lang.reflect.Constructor.newInstance(Constructor.java:532)
    at com.mysql.jdbc.Util.handleNewInstance(Util.java:411)
    at com.mysql.jdbc.Util.getInstance(Util.java:386)
    at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:1039)
    at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:4098)
    at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:4030)
    at com.mysql.jdbc.MysqlIO.sendCommand(MysqlIO.java:2490)
    at com.mysql.jdbc.MysqlIO.sqlQueryDirect(MysqlIO.java:2651)
    at com.mysql.jdbc.ConnectionImpl.execSQL(ConnectionImpl.java:2677)
    at com.mysql.jdbc.PreparedStatement.executeInternal(PreparedStatement.java:2134)
    at com.mysql.jdbc.PreparedStatement.executeUpdate(PreparedStatement.java:2434)
    at com.mysql.jdbc.PreparedStatement.executeUpdate(PreparedStatement.java:2352)
    at com.mysql.jdbc.PreparedStatement.executeUpdate(PreparedStatement.java:2337)
    at com.alibaba.druid.filter.FilterChainImpl.preparedStatement_executeUpdate(FilterChainImpl.java:2667)
    at com.alibaba.druid.filter.FilterAdapter.preparedStatement_executeUpdate(FilterAdapter.java:1060)
    at com.alibaba.druid.filter.FilterEventAdapter.preparedStatement_executeUpdate(FilterEventAdapter.java:487)
    at com.alibaba.druid.filter.FilterChainImpl.preparedStatement_executeUpdate(FilterChainImpl.java:2665)
    at com.alibaba.druid.filter.FilterAdapter.preparedStatement_executeUpdate(FilterAdapter.java:1060)
    at com.alibaba.druid.filter.FilterEventAdapter.preparedStatement_executeUpdate(FilterEventAdapter.java:487)
    at com.alibaba.druid.filter.FilterChainImpl.preparedStatement_executeUpdate(FilterChainImpl.java:2665)
    at com.alibaba.druid.filter.FilterAdapter.preparedStatement_executeUpdate(FilterAdapter.java:1060)
    at com.alibaba.druid.filter.FilterChainImpl.preparedStatement_executeUpdate(FilterChainImpl.java:2665)
    at com.alibaba.druid.proxy.jdbc.PreparedStatementProxyImpl.executeUpdate(PreparedStatementProxyImpl.java:104)
    at com.alibaba.druid.pool.DruidPooledPreparedStatement.executeUpdate(DruidPooledPreparedStatement.java:200)
    at org.springframework.jdbc.core.JdbcTemplate$2.doInPreparedStatement(JdbcTemplate.java:818)
    at org.springframework.jdbc.core.JdbcTemplate$2.doInPreparedStatement(JdbcTemplate.java:1)
    at org.springframework.jdbc.core.JdbcTemplate.execute(JdbcTemplate.java:587)
    ... 50 more


## wenshao (24 Jul 2012)

乱码应该是操作系统或者页面展示的原因，和Druid无关


## xiegengcai (24 Jul 2012)

就是日志乱码，结果集和插入的数据都没问题，系统和数据库编码都是UTF-8
在 2012-7-24 上午9:50，"温高铁" <
reply@reply.github.com

> 写道：
> 
> 乱码应该是操作系统或者页面展示的原因，和Druid无关
> 
> ---
> 
> Reply to this email directly or view it on GitHub:
> https://github.com/AlibabaTech/druid/issues/6#issuecomment-7196791


## wenshao (01 Sept 2012)

这是与druid无关问题


