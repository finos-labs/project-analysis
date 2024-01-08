#启动springboot-dubbo-server 报错

Owner: JeffLi1993

Repo: springboot-learning-example

Labels: 

## a931083335 (28 Aug 2017)

2017-08-28 16:26:37.044  INFO 7944 --- [           main] utoConfigurationReportLoggingInitializer : 

Error starting ApplicationContext. To display the auto-configuration report re-run your application with 'debug' enabled.
2017-08-28 16:26:37.050 ERROR 7944 --- [           main] o.s.boot.SpringApplication               : Application startup failed

org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'cityDubboServiceImpl' defined in file [E:\work_space_springboot_dubbo\springboot-learning-example-master\springboot-dubbo-server\target\classes\org\spring\springboot\dubbo\impl\CityDubboServiceImpl.class]: Initialization of bean failed; nested exception is java.lang.NullPointerException
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
Caused by: java.lang.NullPointerException: null
	at com.alibaba.dubbo.config.spring.ServiceBean.afterPropertiesSet(ServiceBean.java:162) ~[dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.config.spring.AnnotationBean.postProcessAfterInitialization(AnnotationBean.java:186) ~[dubbo-2.5.3.jar:2.5.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsAfterInitialization(AbstractAutowireCapableBeanFactory.java:423) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1633) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:555) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	... 15 common frames omitted

## make38324 (18 Sept 2017)

Exception in thread "main" java.lang.NoClassDefFoundError: org/springframework/beans/factory/ListableBeanFactory


