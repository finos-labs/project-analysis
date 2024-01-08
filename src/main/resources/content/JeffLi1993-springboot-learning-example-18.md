#springboot-dubbo 启动client报错

Owner: JeffLi1993

Repo: springboot-learning-example

Labels: 

## AhaEdgar (22 Aug 2017)

2017-08-22 21:37:55.754  INFO 5632 --- [           main] c.a.d.r.zookeeper.ZookeeperRegistry      :  [DUBBO] Register: consumer://192.168.0.103/org.spring.springboot.dubbo.CityDubboService?application=consumer&category=consumers&check=false&dubbo=2.5.3&interface=org.spring.springboot.dubbo.CityDubboService&methods=findCityByName&pid=5632&revision=1.0.0&side=consumer&timestamp=1503409075549&version=1.0.0, dubbo version: 2.5.3, current host: 192.168.0.103

2017-08-22 21:37:55.863  INFO 5632 --- [           main] c.a.d.r.zookeeper.ZookeeperRegistry      :  [DUBBO] Subscribe: consumer://192.168.0.103/org.spring.springboot.dubbo.CityDubboService?application=consumer&category=providers,configurators,routers&dubbo=2.5.3&interface=org.spring.springboot.dubbo.CityDubboService&methods=findCityByName&pid=5632&revision=1.0.0&side=consumer&timestamp=1503409075549&version=1.0.0, dubbo version: 2.5.3, current host: 192.168.0.103

2017-08-22 21:37:56.145  INFO 5632 --- [           main] c.a.d.r.zookeeper.ZookeeperRegistry      :  [DUBBO] Notify urls for subscribe url consumer://192.168.0.103/org.spring.springboot.dubbo.CityDubboService?application=consumer&category=providers,configurators,routers&dubbo=2.5.3&interface=org.spring.springboot.dubbo.CityDubboService&methods=findCityByName&pid=5632&revision=1.0.0&side=consumer&timestamp=1503409075549&version=1.0.0, urls: [dubbo://192.168.0.103:20880/org.spring.springboot.dubbo.CityDubboService?anyhost=true&application=provider&dubbo=2.5.3&interface=org.spring.springboot.dubbo.CityDubboService&methods=findCityByName&pid=7696&revision=1.0.0&side=provider&timestamp=1503409067126&version=1.0.0, empty://192.168.0.103/org.spring.springboot.dubbo.CityDubboService?application=consumer&category=configurators&dubbo=2.5.3&interface=org.spring.springboot.dubbo.CityDubboService&methods=findCityByName&pid=5632&revision=1.0.0&side=consumer&timestamp=1503409075549&version=1.0.0, route://0.0.0.0/org.spring.springboot.dubbo.CityDubboService?category=routers&dynamic=false&enabled=true&force=true&name=org.spring.springboot.dubbo.CityDubboService:1.0.0 blackwhitelist&priority=0&router=condition&rule=consumer.host+%21%3D+192.168.202.213+%3D%3E+false&runtime=false&version=1.0.0], dubbo version: 2.5.3, current host: 192.168.0.103
2017-08-22 21:37:57.788 ERROR 5632 --- [           main] c.a.d.remoting.transport.AbstractClient  :  [DUBBO] Failed to start NettyClient DESKTOP-VC6DHRI/192.168.0.103 connect to the server /192.168.0.103:20880 (check == false, ignore and retry later!), cause: client(url: dubbo://192.168.0.103:20880/org.spring.springboot.dubbo.CityDubboService?anyhost=true&application=consumer&check=false&codec=dubbo&dubbo=2.5.3&heartbeat=60000&interface=org.spring.springboot.dubbo.CityDubboService&methods=findCityByName&pid=5632&revision=1.0.0&side=consumer&timestamp=1503409075549&version=1.0.0) failed to connect to server /192.168.0.103:20880, error message is:connection timed out, dubbo version: 2.5.3, current host: 192.168.0.103


**# # com.alibaba.dubbo.remoting.RemotingException: client(url: dubbo://192.168.0.103:20880/org.spring.springboot.dubbo.CityDubboService?anyhost=true&application=consumer&check=false&codec=dubbo&dubbo=2.5.3&heartbeat=60000&interface=org.spring.springboot.dubbo.CityDubboService&methods=findCityByName&pid=5632&revision=1.0.0&side=consumer&timestamp=1503409075549&version=1.0.0) failed to connect to server /192.168.0.103:20880, error message is:connection timed out**
	at com.alibaba.dubbo.remoting.transport.netty.NettyClient.doConnect(NettyClient.java:124) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.remoting.transport.AbstractClient.connect(AbstractClient.java:280) ~[dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.remoting.transport.AbstractClient.<init>(AbstractClient.java:103) ~[dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.remoting.transport.netty.NettyClient.<init>(NettyClient.java:61) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.remoting.transport.netty.NettyTransporter.connect(NettyTransporter.java:37) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.remoting.Transporter$Adpative.connect(Transporter$Adpative.java) [na:2.5.3]
	at com.alibaba.dubbo.remoting.Transporters.connect(Transporters.java:67) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.remoting.exchange.support.header.HeaderExchanger.connect(HeaderExchanger.java:37) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.remoting.exchange.Exchangers.connect(Exchangers.java:102) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol.initClient(DubboProtocol.java:378) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol.getSharedClient(DubboProtocol.java:344) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol.getClients(DubboProtocol.java:321) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol.refer(DubboProtocol.java:303) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.rpc.protocol.ProtocolListenerWrapper.refer(ProtocolListenerWrapper.java:65) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper.refer(ProtocolFilterWrapper.java:62) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.rpc.Protocol$Adpative.refer(Protocol$Adpative.java) [na:2.5.3]
	at com.alibaba.dubbo.registry.integration.RegistryDirectory.toInvokers(RegistryDirectory.java:395) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.registry.integration.RegistryDirectory.refreshInvoker(RegistryDirectory.java:224) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.registry.integration.RegistryDirectory.notify(RegistryDirectory.java:195) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.registry.support.AbstractRegistry.notify(AbstractRegistry.java:449) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.registry.support.FailbackRegistry.doNotify(FailbackRegistry.java:273) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.registry.support.FailbackRegistry.notify(FailbackRegistry.java:259) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.registry.zookeeper.ZookeeperRegistry.doSubscribe(ZookeeperRegistry.java:170) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.registry.support.FailbackRegistry.subscribe(FailbackRegistry.java:189) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.registry.integration.RegistryDirectory.subscribe(RegistryDirectory.java:133) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.registry.integration.RegistryProtocol.doRefer(RegistryProtocol.java:271) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.registry.integration.RegistryProtocol.refer(RegistryProtocol.java:254) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.rpc.protocol.ProtocolListenerWrapper.refer(ProtocolListenerWrapper.java:63) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper.refer(ProtocolFilterWrapper.java:60) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.rpc.Protocol$Adpative.refer(Protocol$Adpative.java) [na:2.5.3]
	at com.alibaba.dubbo.config.ReferenceConfig.createProxy(ReferenceConfig.java:392) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.config.ReferenceConfig.init(ReferenceConfig.java:300) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.config.ReferenceConfig.get(ReferenceConfig.java:138) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.config.spring.AnnotationBean.refer(AnnotationBean.java:302) [dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.config.spring.AnnotationBean.postProcessBeforeInitialization(AnnotationBean.java:233) [dubbo-2.5.3.jar:2.5.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization(AbstractAutowireCapableBeanFactory.java:409) [spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1620) [spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:555) [spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:483) [spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:306) [spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:230) [spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:302) [spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:197) [spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:761) [spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:866) [spring-context-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:542) [spring-context-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.boot.context.embedded.EmbeddedWebApplicationContext.refresh(EmbeddedWebApplicationContext.java:122) [spring-boot-1.5.1.RELEASE.jar:1.5.1.RELEASE]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:737) [spring-boot-1.5.1.RELEASE.jar:1.5.1.RELEASE]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:370) [spring-boot-1.5.1.RELEASE.jar:1.5.1.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:314) [spring-boot-1.5.1.RELEASE.jar:1.5.1.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1162) [spring-boot-1.5.1.RELEASE.jar:1.5.1.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1151) [spring-boot-1.5.1.RELEASE.jar:1.5.1.RELEASE]
	at org.spring.springboot.ClientApplication.main(ClientApplication.java:20) [classes/:na]
Caused by: java.net.ConnectException: connection timed out
	at org.jboss.netty.channel.socket.nio.NioClientSocketPipelineSink$Boss.processConnectTimeout(NioClientSocketPipelineSink.java:371) ~[netty-3.2.5.Final.jar:na]
	at org.jboss.netty.channel.socket.nio.NioClientSocketPipelineSink$Boss.run(NioClientSocketPipelineSink.java:283) ~[netty-3.2.5.Final.jar:na]
	at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source) ~[na:1.8.0_77]
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source) ~[na:1.8.0_77]
	at java.lang.Thread.run(Unknown Source) ~[na:1.8.0_77]

2017-08-22 21:37:57.862  WARN 5632 --- [           main] c.a.d.r.c.r.condition.ConditionRouter    :  [DUBBO] The current consumer in the service blacklist. consumer: 192.168.0.103, service: org.spring.springboot.dubbo.CityDubboService:1.0.0, dubbo version: 2.5.3, current host: 192.168.0.103
2017-08-22 21:37:57.865 ERROR 5632 --- [           main] com.alibaba.dubbo.common.logger.Logger   :  [DUBBO] Failed to init remote service reference at filed cityDubboService in class org.spring.springboot.dubbo.CityDubboConsumerService, cause: Failed to check the status of the service org.spring.springboot.dubbo.CityDubboService. No provider available for the service org.spring.springboot.dubbo.CityDubboService:1.0.0 from the url zookeeper://127.0.0.1:2181/com.alibaba.dubbo.registry.RegistryService?anyhost=true&application=consumer&check=false&dubbo=2.5.3&interface=org.spring.springboot.dubbo.CityDubboService&methods=findCityByName&pid=5632&revision=1.0.0&side=consumer&timestamp=1503409075549&version=1.0.0 to the consumer 192.168.0.103 use dubbo version 2.5.3, dubbo version: 2.5.3, current host: 192.168.0.103

java.lang.IllegalStateException: Failed to check the status of the service org.spring.springboot.dubbo.CityDubboService. No provider available for the service org.spring.springboot.dubbo.CityDubboService:1.0.0 from the url zookeeper://127.0.0.1:2181/com.alibaba.dubbo.registry.RegistryService?anyhost=true&application=consumer&check=false&dubbo=2.5.3&interface=org.spring.springboot.dubbo.CityDubboService&methods=findCityByName&pid=5632&revision=1.0.0&side=consumer&timestamp=1503409075549&version=1.0.0 to the consumer 192.168.0.103 use dubbo version 2.5.3
	at com.alibaba.dubbo.config.ReferenceConfig.createProxy(ReferenceConfig.java:420) ~[dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.config.ReferenceConfig.init(ReferenceConfig.java:300) ~[dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.config.ReferenceConfig.get(ReferenceConfig.java:138) ~[dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.config.spring.AnnotationBean.refer(AnnotationBean.java:302) ~[dubbo-2.5.3.jar:2.5.3]
	at com.alibaba.dubbo.config.spring.AnnotationBean.postProcessBeforeInitialization(AnnotationBean.java:233) ~[dubbo-2.5.3.jar:2.5.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization(AbstractAutowireCapableBeanFactory.java:409) [spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1620) [spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:555) [spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:483) [spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:306) [spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:230) [spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:302) [spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:197) [spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:761) [spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:866) [spring-context-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:542) [spring-context-4.3.6.RELEASE.jar:4.3.6.RELEASE]
	at org.springframework.boot.context.embedded.EmbeddedWebApplicationContext.refresh(EmbeddedWebApplicationContext.java:122) [spring-boot-1.5.1.RELEASE.jar:1.5.1.RELEASE]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:737) [spring-boot-1.5.1.RELEASE.jar:1.5.1.RELEASE]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:370) [spring-boot-1.5.1.RELEASE.jar:1.5.1.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:314) [spring-boot-1.5.1.RELEASE.jar:1.5.1.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1162) [spring-boot-1.5.1.RELEASE.jar:1.5.1.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1151) [spring-boot-1.5.1.RELEASE.jar:1.5.1.RELEASE]
	at org.spring.springboot.ClientApplication.main(ClientApplication.java:20) [classes/:na]


能注册到zookeeper里，但是client报错，请问有人有这样的情况吗@JeffLi1993

## helloworlde (25 Aug 2017)

192.168.0.103:20880, error message is:connection timed out;
# Time out
网络问题不是你程序的问题

## JeffLi1993 (08 Apr 2018)

ok

