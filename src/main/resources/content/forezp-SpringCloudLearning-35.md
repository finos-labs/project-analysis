#启动报错，史上最简单的SpringCloud教程 | 第五篇: 路由网关(zuul)(Finchley版本) 

Owner: forezp

Repo: SpringCloudLearning

Labels: 

## 437865981 (15 Nov 2018)

Description:

The bean 'proxyRequestHelper', defined in class path resource [org/springframework/cloud/netflix/zuul/ZuulProxyAutoConfiguration$NoActuatorConfiguration.class], could not be registered. A bean with that name has already been defined in class path resource [org/springframework/cloud/netflix/zuul/ZuulProxyAutoConfiguration$EndpointConfiguration.class] and overriding is disabled.

Action:

Consider renaming one of the beans or enabling overriding by setting spring.main.allow-bean-definition-overriding=true


配置都是按照教程来的呀
加上 spring.main.allow-bean-definition-overriding=true 依然报错

## drduan (19 Nov 2018)

第五章
failed to read artifact descriptor for com.netflix.zuul:zuul-core:jar:1.3.0

## 437865981 (19 Nov 2018)

是版本问题 ，用的2.1.0的，改成教程中的2.0.3的就可以了

## sunweiguo (25 Nov 2018)

请问是如何解决了，不降版本的话

## sunweiguo (25 Nov 2018)

奥，貌似现在还不支持2.1.x版本

## LuckRabbit123 (20 Mar 2019)

这个问题是SpringCloud与SpringBoot版本冲突了,这两个是独立的开发的,所以为了避免该冲突,建议查看一下官网对其支持的Boot

## ywhs (28 Mar 2019)

这个问题现在可以不通过对springboot降级来解决，把Finchley.RELEASE修改为Greenwich.RELEASE即可解决，Finchley.RELEASE仅对2.0支持，Greenwich.RELEASE对2.1支持，在springcloud官网中有对应关系.

![image](https://user-images.githubusercontent.com/34649300/55128906-316d7000-5150-11e9-925a-707d7fdff443.png#pic_center)

[查看对应关系的springcloud官网](https://spring.io/projects/spring-cloud)


## ywhs (28 Mar 2019)

> 这个问题现在可以不通过对springboot降级来解决，把Finchley.RELEASE修改为Greenwich.RELEASE即可解决，Finchley.RELEASE仅对2.0支持，Greenwich.RELEASE对2.1支持，在springcloud官网中有对应关系.
> 
> ![image](https://user-images.githubusercontent.com/34649300/55128906-316d7000-5150-11e9-925a-707d7fdff443.png#pic_center)
> 
> [查看对应关系的springcloud官网](https://spring.io/projects/spring-cloud)

[Maven仓库](https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-dependencies)

