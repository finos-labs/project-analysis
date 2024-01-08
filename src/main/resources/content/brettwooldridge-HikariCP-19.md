#NPE in Hibernate when Connection accessed from thread that did not create it

Owner: brettwooldridge

Repo: HikariCP

Labels: bug 

## brettwooldridge (09 Jan 2014)

Hi,

Just bumped up to the latest version (from 1.2.1) and now I'm getting this error, which is causing the transaction to rollback. This was okay on 1.2.1 (no code changes, just a version bump).

-=david=-

java.lang.NullPointerException: null
    at com.zaxxer.hikari.proxy.ConnectionProxy._checkClosed(Unknown Source) ~[HikariCP-1.2.3.jar:na]
    at com.zaxxer.hikari.proxy.ConnectionJavassistProxy.getAutoCommit(ConnectionJavassistProxy.java) ~[na:na]
    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:1.7.0_45]
    at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57) ~[na:1.7.0_45]
    at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:1.7.0_45]
    at java.lang.reflect.Method.invoke(Method.java:606) ~[na:1.7.0_45]
    at org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy$LazyConnectionInvocationHandler.invoke(LazyConnectionDataSourceProxy.java:376) ~[spring-jdbc-4.0.1.BUILD-SNAPSHOT.jar:4.0.1.BUILD-SNAPSHOT]
    at com.sun.proxy.$Proxy21.getAutoCommit(Unknown Source) ~[na:na]
    at org.hibernate.engine.transaction.internal.jdbc.JdbcTransaction.doBegin(JdbcTransaction.java:68) ~[hibernate-core-4.3.0.Final.jar:4.3.0.Final]
    at org.hibernate.engine.transaction.spi.AbstractTransactionImpl.begin(AbstractTransactionImpl.java:162) ~[hibernate-core-4.3.0.Final.jar:4.3.0.Final]
    at org.hibernate.internal.SessionImpl.beginTransaction(SessionImpl.java:1431) ~[hibernate-core-4.3.0.Final.jar:4.3.0.Final]
    at org.hibernate.jpa.internal.TransactionImpl.begin(TransactionImpl.java:61) ~[hibernate-entitymanager-4.3.0.Final.jar:4.3.0.Final]
    at org.springframework.orm.jpa.DefaultJpaDialect.beginTransaction(DefaultJpaDialect.java:67) ~[spring-orm-4.0.1.BUILD-SNAPSHOT.jar:4.0.1.BUILD-SNAPSHOT]
    at org.springframework.orm.jpa.vendor.HibernateJpaDialect.beginTransaction(HibernateJpaDialect.java:110) ~[spring-orm-4.0.1.BUILD-SNAPSHOT.jar:4.0.1.BUILD-SNAPSHOT]
    at org.springframework.orm.jpa.JpaTransactionManager.doBegin(JpaTransactionManager.java:380) ~[spring-orm-4.0.1.BUILD-SNAPSHOT.jar:4.0.1.BUILD-SNAPSHOT]
Wrapped by: org.springframework.transaction.CannotCreateTransactionException: Could not open JPA EntityManager for transaction; nested exception is java.lang.NullPointerException
    at org.springframework.orm.jpa.JpaTransactionManager.doBegin(JpaTransactionManager.java:430) ~[spring-orm-4.0.1.BUILD-SNAPSHOT.jar:4.0.1.BUILD-SNAPSHOT]
    at org.springframework.transaction.support.AbstractPlatformTransactionManager.getTransaction(AbstractPlatformTransactionManager.java:373) ~[spring-tx-4.0.1.BUILD-SNAPSHOT.jar:4.0.1.BUILD-SNAPSHOT]
    at org.springframework.transaction.interceptor.TransactionAspectSupport.createTransactionIfNecessary(TransactionAspectSupport.java:420) ~[spring-tx-4.0.1.BUILD-SNAPSHOT.jar:4.0.1.BUILD-SNAPSHOT]
    at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:257) ~[spring-tx-4.0.1.BUILD-SNAPSHOT.jar:4.0.1.BUILD-SNAPSHOT]
    at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:95) ~[spring-tx-4.0.1.BUILD-SNAPSHOT.jar:4.0.1.BUILD-SNAPSHOT]
    at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) ~[spring-aop-4.0.1.BUILD-SNAPSHOT.jar:4.0.1.BUILD-SNAPSHOT]
    at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:136) ~[spring-tx-4.0.1.BUILD-SNAPSHOT.jar:4.0.1.BUILD-SNAPSHOT]
    at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) ~[spring-aop-4.0.1.BUILD-SNAPSHOT.jar:4.0.1.BUILD-SNAPSHOT]
    at org.springframework.data.jpa.repository.support.LockModeRepositoryPostProcessor$LockModePopulatingMethodIntercceptor.invoke(LockModeRepositoryPostProcessor.java:92) ~[spring-data-jpa-1.5.0.M1.jar:na]
    at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) ~[spring-aop-4.0.1.BUILD-SNAPSHOT.jar:4.0.1.BUILD-SNAPSHOT]
    at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92) ~[spring-aop-4.0.1.BUILD-SNAPSHOT.jar:4.0.1.BUILD-SNAPSHOT]
    at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) ~[spring-aop-4.0.1.BUILD-SNAPSHOT.jar:4.0.1.BUILD-SNAPSHOT]
    at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:207) ~[spring-aop-4.0.1.BUILD-SNAPSHOT.jar:4.0.1.BUILD-SNAPSHOT]
    at com.sun.proxy.$Proxy109.save(Unknown Source) ~[na:na]


## brettwooldridge (15 Jan 2014)

Today HikariCP 1.2.6 was published to the maven repository containing this fix.


