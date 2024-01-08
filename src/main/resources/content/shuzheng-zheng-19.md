#请教运行zheng-cms-rpc-service maven install出的jar包报错

Owner: shuzheng

Repo: zheng

Labels: 

## greydesolate (28 Mar 2017)

在Idea里可以不报错的run zheng-cms-rpc-service 的main方法。去执行target下的jar包报错

D:\ideaworkspace\zheng\zheng-cms\zheng-cms-rpc-service\target>java -jar zheng-cm
s-rpc-service.jar
2017-03-28 14:45:36,388 [main] INFO  [com.zheng.cms.rpc.ZhengCmsRpcServiceApplic
ation] - >>>>> zheng-cms-rpc-service 正在启动 <<<<<
2017-03-28 14:45:38,202 [main] INFO  [com.alibaba.dubbo.common.logger.LoggerFact
ory] - using logger: com.alibaba.dubbo.common.logger.log4j.Log4jLoggerAdapter
2017-03-28 14:45:41,509 [main] WARN  [org.springframework.context.support.ClassP
athXmlApplicationContext] - Exception encountered during context initialization
- cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDepen
dencyException: Error creating bean with name 'cmsArticleServiceImpl': Unsatisfi
ed dependency expressed through field 'cmsArticleMapper'; nested exception is or
g.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bea
n of type 'com.zheng.cms.dao.mapper.CmsArticleMapper' available: expected at lea
st 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.s
pringframework.beans.factory.annotation.Autowired(required=true)}
Exception in thread "main" org.springframework.beans.factory.UnsatisfiedDependen
cyException: Error creating bean with name 'cmsArticleServiceImpl': Unsatisfied
dependency expressed through field 'cmsArticleMapper'; nested exception is org.s
pringframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean o
f type 'com.zheng.cms.dao.mapper.CmsArticleMapper' available: expected at least
1 bean which qualifies as autowire candidate. Dependency annotations: {@org.spri
ngframework.beans.factory.annotation.Autowired(required=true)}
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanP
ostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.j
ava:588)
        at org.springframework.beans.factory.annotation.InjectionMetadata.inject
(InjectionMetadata.java:88)
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanP
ostProcessor.postProcessPropertyValues(AutowiredAnnotationBeanPostProcessor.java
:366)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBean
Factory.populateBean(AbstractAutowireCapableBeanFactory.java:1264)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBean
Factory.doCreateBean(AbstractAutowireCapableBeanFactory.java:553)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBean
Factory.createBean(AbstractAutowireCapableBeanFactory.java:483)
        at org.springframework.beans.factory.support.AbstractBeanFactory$1.getOb
ject(AbstractBeanFactory.java:306)
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistr
y.getSingleton(DefaultSingletonBeanRegistry.java:230)
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBe
an(AbstractBeanFactory.java:302)
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean
(AbstractBeanFactory.java:197)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.
preInstantiateSingletons(DefaultListableBeanFactory.java:761)
        at org.springframework.context.support.AbstractApplicationContext.finish
BeanFactoryInitialization(AbstractApplicationContext.java:866)
        at org.springframework.context.support.AbstractApplicationContext.refres
h(AbstractApplicationContext.java:542)
        at org.springframework.context.support.ClassPathXmlApplicationContext.<i
nit>(ClassPathXmlApplicationContext.java:139)
        at org.springframework.context.support.ClassPathXmlApplicationContext.<i
nit>(ClassPathXmlApplicationContext.java:83)
        at com.zheng.cms.rpc.ZhengCmsRpcServiceApplication.main(ZhengCmsRpcServiceApplication.java:17)
Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.zheng.cms.dao.mapper.CmsArticleMapper' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.raiseNoMatchingBeanFound(DefaultListableBeanFactory.java:1486)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1104)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1066)
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:585)
        ... 15 more
2017-03-28 14:45:41,576 [DubboShutdownHook] INFO  [com.alibaba.dubbo.config.AbstractConfig] -  [DUBBO] Run shutdown hook now., dubbo version: 2.5.3, current host: 127.0.0.1
2017-03-28 14:45:41,581 [DubboShutdownHook] INFO  [com.alibaba.dubbo.registry.support.AbstractRegistryFactory] -  [DUBBO] Close all registries [], dubbo version: 2.5.3, current host: 127.0.0.1

## shuzheng (02 Apr 2017)

已修复，打好的tar.gz包内包含管理脚本。可直接启动

