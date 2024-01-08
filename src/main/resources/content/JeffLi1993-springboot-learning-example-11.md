#springboot-dubbo-server 启动创建bean 出错

Owner: JeffLi1993

Repo: springboot-learning-example

Labels: 

## li7nux (01 Jun 2017)

项目克隆到本地，运行main方法出错，具体如下: 
```
2017-06-01 09:53:16.919  INFO 12772 --- [           main] utoConfigurationReportLoggingInitializer : 

Error starting ApplicationContext. To display the auto-configuration report re-run your application with 'debug' enabled.
2017-06-01 09:53:16.924 ERROR 12772 --- [           main] o.s.boot.SpringApplication               : Application startup failed

org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'cityDubboServiceImpl' defined in file [C:\chai\github\springboot-learning-example\springboot-dubbo-server\target\classes\org\spring\springboot\dubbo\impl\CityDubboServiceImpl.class]: Initialization of bean failed; nested exception is java.lang.IllegalStateException: ModuleConfig.module == null
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:564) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:483) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:306) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:230) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:302) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:197) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:761) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:866) ~[spring-context-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:542) ~[spring-context-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.boot.context.embedded.EmbeddedWebApplicationContext.refresh(EmbeddedWebApplicationContext.java:122) ~[spring-boot-1.5.1.RELEASE.jar:1.5.1.RELEASE]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:737) [spring-boot-1.5.1.RELEASE.jar:1.5.1.RELEASE]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:370) [spring-boot-1.5.1.RELEASE.jar:1.5.1.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:314) [spring-boot-1.5.1.RELEASE.jar:1.5.1.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1162) [spring-boot-1.5.1.RELEASE.jar:1.5.1.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1151) [spring-boot-1.5.1.RELEASE.jar:1.5.1.RELEASE]
	at org.spring.springboot.ServerApplication.main(ServerApplication.java:18) [classes/:na]
Caused by: java.lang.IllegalStateException: ModuleConfig.module == null
	at com.alibaba.dubbo.config.AbstractConfig.appendParameters(AbstractConfig.java:282) ~[dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.config.AbstractConfig.appendParameters(AbstractConfig.java:217) ~[dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.config.ServiceConfig.doExportUrlsFor1Protocol(ServiceConfig.java:357) ~[dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.config.ServiceConfig.doExportUrls(ServiceConfig.java:281) ~[dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.config.ServiceConfig.doExport(ServiceConfig.java:242) ~[dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.config.ServiceConfig.export(ServiceConfig.java:143) ~[dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.config.spring.AnnotationBean.postProcessAfterInitialization(AnnotationBean.java:195) ~[dubbo-2.5.3.jar:2.5.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsAfterInitialization(AbstractAutowireCapableBeanFactory.java:423) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1633) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:555) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	... 15 common frames omitted
Caused by: java.lang.IllegalStateException: ModuleConfig.module == null
	at com.alibaba.dubbo.config.AbstractConfig.appendParameters(AbstractConfig.java:267) ~[dubbo-2.5.3.jar:2.5.3]
	... 24 common frames omitted
```

## JeffLi1993 (05 Jun 2017)

先 maven 构建完全后启动

## li7nux (05 Jun 2017)

原因已找到，是因为我自己编译的`spring-boot-starter-dubbo`有点的问题，删除自己编译的jar包，使用maven仓库的就可以了。

