#Adding spring-security 3.2.0.RC1 dependency to the project prevents application from starting

Owner: spring-projects

Repo: spring-boot

Labels: 

## sshcherbakov (24 Aug 2013)

I have a spring-boot driven application that launches embedded Tomcat.
It starts and works fine.
I add the following dependencies to the project (nothing more):

```
compile "org.springframework.security:spring-security-core:3.2.0.RC1"
compile "org.springframework.security:spring-security-web:3.2.0.RC1"
compile "org.springframework.security:spring-security-config:3.2.0.RC1"
```

And the application crashes on startup with 

```
Caused by: java.lang.ClassNotFoundException: org.springframework.security.config.annotation.web.configurers.SessionCreationPolicy
```

Here is the full exception stack:

```
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration': Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Could not autowire method: public void org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration.setFilterChainProxySecurityConfigurer(java.util.List) throws java.lang.Exception; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'webSecurityConfigurerAdapter': Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Could not autowire field: private org.springframework.boot.actuate.properties.SecurityProperties org.springframework.boot.actuate.autoconfigure.SecurityAutoConfiguration$BoostrapWebSecurityConfigurerAdapter.security; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'org.springframework.actuate.properties.SecurityProperties' defined in class path resource [org/springframework/boot/actuate/autoconfigure/SecurityAutoConfiguration.class]: Instantiation of bean failed; nested exception is org.springframework.beans.factory.BeanDefinitionStoreException: Factory method [public org.springframework.boot.actuate.properties.SecurityProperties org.springframework.boot.actuate.autoconfigure.SecurityAutoConfiguration.securityProperties()] threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/security/config/annotation/web/configurers/SessionCreationPolicy
    at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessPropertyValues(AutowiredAnnotationBeanPostProcessor.java:291) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1139) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:537) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:475) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:299) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:228) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:295) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:195) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:354) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1048) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:943) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:504) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:475) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:299) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:228) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:295) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeansOfType(DefaultListableBeanFactory.java:446) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.boot.context.embedded.EmbeddedWebApplicationContext.getOrderedBeansOfType(EmbeddedWebApplicationContext.java:335) ~[spring-boot-0.5.0.BUILD-SNAPSHOT.jar:0.5.0.BUILD-SNAPSHOT]
    at org.springframework.boot.context.embedded.EmbeddedWebApplicationContext.getServletContextInitializerBeans(EmbeddedWebApplicationContext.java:248) ~[spring-boot-0.5.0.BUILD-SNAPSHOT.jar:0.5.0.BUILD-SNAPSHOT]
    at org.springframework.boot.context.embedded.EmbeddedWebApplicationContext$1.onStartup(EmbeddedWebApplicationContext.java:193) ~[spring-boot-0.5.0.BUILD-SNAPSHOT.jar:0.5.0.BUILD-SNAPSHOT]
    at org.springframework.boot.context.embedded.tomcat.ServletContextInitializerLifecycleListener.lifecycleEvent(ServletContextInitializerLifecycleListener.java:54) ~[spring-boot-0.5.0.BUILD-SNAPSHOT.jar:0.5.0.BUILD-SNAPSHOT]
    at org.apache.catalina.util.LifecycleSupport.fireLifecycleEvent(LifecycleSupport.java:119) ~[tomcat-embed-core-8.0.0-RC1.jar:8.0.0-RC1]
    at org.apache.catalina.util.LifecycleBase.fireLifecycleEvent(LifecycleBase.java:90) [tomcat-embed-core-8.0.0-RC1.jar:8.0.0-RC1]
    at org.apache.catalina.core.StandardContext.startInternal(StandardContext.java:5178) ~[tomcat-embed-core-8.0.0-RC1.jar:8.0.0-RC1]
    at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:150) [tomcat-embed-core-8.0.0-RC1.jar:8.0.0-RC1]
    ... 7 common frames omitted
Caused by: org.springframework.beans.factory.BeanCreationException: Could not autowire method: public void org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration.setFilterChainProxySecurityConfigurer(java.util.List) throws java.lang.Exception; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'webSecurityConfigurerAdapter': Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Could not autowire field: private org.springframework.boot.actuate.properties.SecurityProperties org.springframework.boot.actuate.autoconfigure.SecurityAutoConfiguration$BoostrapWebSecurityConfigurerAdapter.security; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'org.springframework.actuate.properties.SecurityProperties' defined in class path resource [org/springframework/boot/actuate/autoconfigure/SecurityAutoConfiguration.class]: Instantiation of bean failed; nested exception is org.springframework.beans.factory.BeanDefinitionStoreException: Factory method [public org.springframework.boot.actuate.properties.SecurityProperties org.springframework.boot.actuate.autoconfigure.SecurityAutoConfiguration.securityProperties()] threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/security/config/annotation/web/configurers/SessionCreationPolicy
    at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredMethodElement.inject(AutowiredAnnotationBeanPostProcessor.java:604) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:87) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessPropertyValues(AutowiredAnnotationBeanPostProcessor.java:288) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    ... 32 common frames omitted
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'webSecurityConfigurerAdapter': Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Could not autowire field: private org.springframework.boot.actuate.properties.SecurityProperties org.springframework.boot.actuate.autoconfigure.SecurityAutoConfiguration$BoostrapWebSecurityConfigurerAdapter.security; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'org.springframework.actuate.properties.SecurityProperties' defined in class path resource [org/springframework/boot/actuate/autoconfigure/SecurityAutoConfiguration.class]: Instantiation of bean failed; nested exception is org.springframework.beans.factory.BeanDefinitionStoreException: Factory method [public org.springframework.boot.actuate.properties.SecurityProperties org.springframework.boot.actuate.autoconfigure.SecurityAutoConfiguration.securityProperties()] threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/security/config/annotation/web/configurers/SessionCreationPolicy
    at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessPropertyValues(AutowiredAnnotationBeanPostProcessor.java:291) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1139) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:537) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:475) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:299) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:228) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:295) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:195) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.DefaultListableBeanFactory.findAutowireCandidates(DefaultListableBeanFactory.java:931) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:832) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:789) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredMethodElement.inject(AutowiredAnnotationBeanPostProcessor.java:561) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    ... 34 common frames omitted
Caused by: org.springframework.beans.factory.BeanCreationException: Could not autowire field: private org.springframework.boot.actuate.properties.SecurityProperties org.springframework.boot.actuate.autoconfigure.SecurityAutoConfiguration$BoostrapWebSecurityConfigurerAdapter.security; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'org.springframework.actuate.properties.SecurityProperties' defined in class path resource [org/springframework/boot/actuate/autoconfigure/SecurityAutoConfiguration.class]: Instantiation of bean failed; nested exception is org.springframework.beans.factory.BeanDefinitionStoreException: Factory method [public org.springframework.boot.actuate.properties.SecurityProperties org.springframework.boot.actuate.autoconfigure.SecurityAutoConfiguration.securityProperties()] threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/security/config/annotation/web/configurers/SessionCreationPolicy
    at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:517) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:87) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessPropertyValues(AutowiredAnnotationBeanPostProcessor.java:288) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    ... 45 common frames omitted
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'org.springframework.actuate.properties.SecurityProperties' defined in class path resource [org/springframework/boot/actuate/autoconfigure/SecurityAutoConfiguration.class]: Instantiation of bean failed; nested exception is org.springframework.beans.factory.BeanDefinitionStoreException: Factory method [public org.springframework.boot.actuate.properties.SecurityProperties org.springframework.boot.actuate.autoconfigure.SecurityAutoConfiguration.securityProperties()] threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/security/config/annotation/web/configurers/SessionCreationPolicy
    at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:584) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1048) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:943) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:504) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:475) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:299) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:228) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:295) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:195) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.DefaultListableBeanFactory.findAutowireCandidates(DefaultListableBeanFactory.java:931) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:874) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:789) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:489) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    ... 47 common frames omitted
Caused by: org.springframework.beans.factory.BeanDefinitionStoreException: Factory method [public org.springframework.boot.actuate.properties.SecurityProperties org.springframework.boot.actuate.autoconfigure.SecurityAutoConfiguration.securityProperties()] threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/security/config/annotation/web/configurers/SessionCreationPolicy
    at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:188) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:573) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    ... 59 common frames omitted
Caused by: java.lang.NoClassDefFoundError: org/springframework/security/config/annotation/web/configurers/SessionCreationPolicy
    at org.springframework.boot.actuate.properties.SecurityProperties.<init>(SecurityProperties.java:34) ~[spring-boot-actuator-0.5.0.BUILD-SNAPSHOT.jar:0.5.0.BUILD-SNAPSHOT]
    at org.springframework.boot.actuate.autoconfigure.SecurityAutoConfiguration.securityProperties(SecurityAutoConfiguration.java:90) ~[spring-boot-actuator-0.5.0.BUILD-SNAPSHOT.jar:0.5.0.BUILD-SNAPSHOT]
    at org.springframework.boot.actuate.autoconfigure.SecurityAutoConfiguration$$EnhancerByCGLIB$$2fe99fae.CGLIB$securityProperties$0(<generated>) ~[spring-core-4.0.0.BUILD-SNAPSHOT.jar:0.5.0.BUILD-SNAPSHOT]
    at org.springframework.boot.actuate.autoconfigure.SecurityAutoConfiguration$$EnhancerByCGLIB$$2fe99fae$$FastClassByCGLIB$$31fd4fc3.invoke(<generated>) ~[spring-core-4.0.0.BUILD-SNAPSHOT.jar:0.5.0.BUILD-SNAPSHOT]
    at org.springframework.cglib.proxy.MethodProxy.invokeSuper(MethodProxy.java:228) ~[spring-core-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.context.annotation.ConfigurationClassEnhancer$BeanMethodInterceptor.intercept(ConfigurationClassEnhancer.java:334) ~[spring-context-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    at org.springframework.boot.actuate.autoconfigure.SecurityAutoConfiguration$$EnhancerByCGLIB$$2fe99fae.securityProperties(<generated>) ~[spring-core-4.0.0.BUILD-SNAPSHOT.jar:0.5.0.BUILD-SNAPSHOT]
    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:1.7.0_17]
    at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57) ~[na:1.7.0_17]
    at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:1.7.0_17]
    at java.lang.reflect.Method.invoke(Method.java:601) ~[na:1.7.0_17]
    at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:166) ~[spring-beans-4.0.0.BUILD-SNAPSHOT.jar:4.0.0.BUILD-SNAPSHOT]
    ... 60 common frames omitted
Caused by: java.lang.ClassNotFoundException: org.springframework.security.config.annotation.web.configurers.SessionCreationPolicy
    at java.net.URLClassLoader$1.run(URLClassLoader.java:366) ~[na:1.7.0_17]
    at java.net.URLClassLoader$1.run(URLClassLoader.java:355) ~[na:1.7.0_17]
    at java.security.AccessController.doPrivileged(Native Method) ~[na:1.7.0_17]
    at java.net.URLClassLoader.findClass(URLClassLoader.java:354) ~[na:1.7.0_17]
    at java.lang.ClassLoader.loadClass(ClassLoader.java:423) ~[na:1.7.0_17]
    at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:308) ~[na:1.7.0_17]
    at java.lang.ClassLoader.loadClass(ClassLoader.java:356) ~[na:1.7.0_17]
    ... 72 common frames omitted
```


## sshcherbakov (24 Aug 2013)

It works fine with spring-security version 3.1.4.RELEASE


## dsyer (24 Aug 2013)

Maybe there was a package name change for that class between M2 and RC1. Try using the spring-boot-starter-security anyway to protect you from this kind of thing, but we only upgraded to Security 3.2.3.Rc1 on Friday, so your only hope is a snapshot of Boot at this point (M2 coming next week sometime).


## philwebb (24 Aug 2013)

You can also try excluding the SecurityAutoConfiguration

```
@EnableAutoConfigure(exclude=SecurityAutoConfiguration.class)
```


## sshcherbakov (25 Aug 2013)

Thank you guys! I can live with 3.1.4 + spring-security-javaconfig M1 but wanted to take full advantage of latest java config driven spring security. 
The

```
@EnableAutoConfigure(exclude=SecurityAutoConfiguration.class)
```

helps!


## dsyer (25 Aug 2013)

If you use the starter pom you should get 3.2.0. 


## dsyer (18 Sept 2013)

I think this is working now?


## sshcherbakov (18 Sept 2013)

Yes, it does work, checked couple of hours ago. Thanks! 


