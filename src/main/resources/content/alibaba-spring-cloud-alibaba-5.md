#1.X 不支持spring boot 1.3.8.Release 版本， FilterRegistrationBean找不到

Owner: alibaba

Repo: spring-cloud-alibaba

Labels: question sentinel 

## maxiaolong (05 Sept 2018)

java.lang.IllegalStateException: Could not evaluate condition on org.springframework.cloud.alibaba.sentinel.custom.SentinelAutoConfiguration#sentinelResourceAspect due to org/springframework/boot/web/servlet/FilterRegistrationBean not found. Make sure your own configuration does not rely on that class. This can also happen if you are @ComponentScanning a springframework package (e.g. if you put a @ComponentScan in the default package by mistake)
	at org.springframework.boot.autoconfigure.condition.SpringBootCondition.matches(SpringBootCondition.java:55) ~[spring-boot-autoconfigure-1.3.8.RELEASE.jar:1.3.8.RELEASE]
	at org.springframework.context.annotation.ConditionEvaluator.shouldSkip(ConditionEvaluator.java:102) ~[spring-context-4.2.8.RELEASE.jar:4.2.8.RELEASE]
	at org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader.loadBeanDefinitionsForBeanMethod(ConfigurationClassBeanDefinitionReader.java:178) ~[spring-context-4.2.8.RELEASE.jar:4.2.8.RELEASE]
	at org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader.loadBeanDefinitionsForConfigurationClass(ConfigurationClassBeanDefinitionReader.java:140) ~[spring-context-4.2.8.RELEASE.jar:4.2.8.RELEASE]
	at org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader.loadBeanDefinitions(ConfigurationClassBeanDefinitionReader.java:116) ~[spring-context-4.2.8.RELEASE.jar:4.2.8.RELEASE]
	at org.springframework.context.annotation.ConfigurationClassPostProcessor.processConfigBeanDefinitions(ConfigurationClassPostProcessor.java:333) ~[spring-context-4.2.8.RELEASE.jar:4.2.8.RELEASE]
	at org.springframework.context.annotation.ConfigurationClassPostProcessor.postProcessBeanDefinitionRegistry(ConfigurationClassPostProcessor.java:243) ~[spring-context-4.2.8.RELEASE.jar:4.2.8.RELEASE]
	at org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanDefinitionRegistryPostProcessors(PostProcessorRegistrationDelegate.java:273) ~[spring-context-4.2.8.RELEASE.jar:4.2.8.RELEASE]
	at org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(PostProcessorRegistrationDelegate.java:98) ~[spring-context-4.2.8.RELEASE.jar:4.2.8.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.invokeBeanFactoryPostProcessors(AbstractApplicationContext.java:678) ~[spring-context-4.2.8.RELEASE.jar:4.2.8.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:520) ~[spring-context-4.2.8.RELEASE.jar:4.2.8.RELEASE]
	at org.springframework.boot.context.embedded.EmbeddedWebApplicationContext.refresh(EmbeddedWebApplicationContext.java:118) ~[spring-boot-1.3.8.RELEASE.jar:1.3.8.RELEASE]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:760) [spring-boot-1.3.8.RELEASE.jar:1.3.8.RELEASE]
	at org.springframework.boot.SpringApplication.createAndRefreshContext(SpringApplication.java:360) [spring-boot-1.3.8.RELEASE.jar:1.3.8.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:306) [spring-boot-1.3.8.RELEASE.jar:1.3.8.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1185) [spring-boot-1.3.8.RELEASE.jar:1.3.8.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1174) [spring-boot-1.3.8.RELEASE.jar:1.3.8.RELEASE]
	at com.weimob.springbootdemo.ServiceApplication.main(ServiceApplication.java:33) [classes/:na]
Caused by: java.lang.NoClassDefFoundError: org/springframework/boot/web/servlet/FilterRegistrationBean
	at java.lang.Class.getDeclaredMethods0(Native Method) ~[na:1.8.0_151]
	at java.lang.Class.privateGetDeclaredMethods(Class.java:2701) ~[na:1.8.0_151]
	at java.lang.Class.getDeclaredMethods(Class.java:1975) ~[na:1.8.0_151]
	at org.springframework.util.ReflectionUtils.getDeclaredMethods(ReflectionUtils.java:612) ~[spring-core-4.2.8.RELEASE.jar:4.2.8.RELEASE]
	at org.springframework.util.ReflectionUtils.doWithMethods(ReflectionUtils.java:524) ~[spring-core-4.2.8.RELEASE.jar:4.2.8.RELEASE]
	at org.springframework.util.ReflectionUtils.doWithMethods(ReflectionUtils.java:510) ~[spring-core-4.2.8.RELEASE.jar:4.2.8.RELEASE]
	at org.springframework.util.ReflectionUtils.getUniqueDeclaredMethods(ReflectionUtils.java:570) ~[spring-core-4.2.8.RELEASE.jar:4.2.8.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.getTypeForFactoryMethod(AbstractAutowireCapableBeanFactory.java:683) ~[spring-beans-4.2.8.RELEASE.jar:4.2.8.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.determineTargetType(AbstractAutowireCapableBeanFactory.java:627) ~[spring-beans-4.2.8.RELEASE.jar:4.2.8.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.predictBeanType(AbstractAutowireCapableBeanFactory.java:597) ~[spring-beans-4.2.8.RELEASE.jar:4.2.8.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.isFactoryBean(AbstractBeanFactory.java:1445) ~[spring-beans-4.2.8.RELEASE.jar:4.2.8.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.isFactoryBean(AbstractBeanFactory.java:975) ~[spring-beans-4.2.8.RELEASE.jar:4.2.8.RELEASE]
	at org.springframework.boot.autoconfigure.condition.BeanTypeRegistry$OptimizedBeanTypeRegistry.addBeanTypeForNonAliasDefinition(BeanTypeRegistry.java:320) ~[spring-boot-autoconfigure-1.3.8.RELEASE.jar:1.3.8.RELEASE]
	at org.springframework.boot.autoconfigure.condition.BeanTypeRegistry$OptimizedBeanTypeRegistry.addBeanType(BeanTypeRegistry.java:309) ~[spring-boot-autoconfigure-1.3.8.RELEASE.jar:1.3.8.RELEASE]
	at org.springframework.boot.autoconfigure.condition.BeanTypeRegistry$OptimizedBeanTypeRegistry.getNamesForType(BeanTypeRegistry.java:290) ~[spring-boot-autoconfigure-1.3.8.RELEASE.jar:1.3.8.RELEASE]
	at org.springframework.boot.autoconfigure.condition.OnBeanCondition.collectBeanNamesForType(OnBeanCondition.java:183) ~[spring-boot-autoconfigure-1.3.8.RELEASE.jar:1.3.8.RELEASE]
	at org.springframework.boot.autoconfigure.condition.OnBeanCondition.getBeanNamesForType(OnBeanCondition.java:172) ~[spring-boot-autoconfigure-1.3.8.RELEASE.jar:1.3.8.RELEASE]
	at org.springframework.boot.autoconfigure.condition.OnBeanCondition.getMatchingBeans(OnBeanCondition.java:140) ~[spring-boot-autoconfigure-1.3.8.RELEASE.jar:1.3.8.RELEASE]
	at org.springframework.boot.autoconfigure.condition.OnBeanCondition.getMatchOutcome(OnBeanCondition.java:114) ~[spring-boot-autoconfigure-1.3.8.RELEASE.jar:1.3.8.RELEASE]
	at org.springframework.boot.autoconfigure.condition.SpringBootCondition.matches(SpringBootCondition.java:47) ~[spring-boot-autoconfigure-1.3.8.RELEASE.jar:1.3.8.RELEASE]
	... 17 common frames omitted
Caused by: java.lang.ClassNotFoundException: org.springframework.boot.web.servlet.FilterRegistrationBean
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381) ~[na:1.8.0_151]
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424) ~[na:1.8.0_151]
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:335) ~[na:1.8.0_151]
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357) ~[na:1.8.0_151]
	... 37 common frames omitted

## flystar32 (07 Sept 2018)

the version of spring-cloud-commons in branch 1.x is 1.3.3.RELEASE, the corresponding version of spring cloud is Edgware, and the corresponding version of spring boot is 1.5.x.

1.x 分支使用的 spring-cloud-commons 版本是1.3.3.RELEASE，对应的 spring boot 版本是 1.5.x，spring cloud 版本是Edgware，需要升级到对应的版本才能使用。

