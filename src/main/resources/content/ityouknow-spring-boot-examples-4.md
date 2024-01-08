#Rabbitq 传递对象问题

Owner: ityouknow

Repo: spring-boot-examples

Labels: 

## archerLj (21 Jun 2017)

Rabbitq 部分，传递对象的时候，如[http://www.ityouknow.com/springboot/2016/11/30/springboot(%E5%85%AB)-RabbitMQ%E8%AF%A6%E8%A7%A3.html](http://www.ityouknow.com/springboot/2016/11/30/springboot(%E5%85%AB)-RabbitMQ%E8%AF%A6%E8%A7%A3.html)中所言，如果什么都不配置，会报错：

```
2017-06-21 09:12:42.819  WARN 75067 --- [cTaskExecutor-1] s.a.r.l.ConditionalRejectingErrorHandler : Execution of Rabbit message listener failed.

org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException: Listener threw exception
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.wrapToListenerExecutionFailedExceptionIfNeeded(AbstractMessageListenerContainer.java:871) ~[spring-rabbit-1.6.5.RELEASE.jar:na]
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.doInvokeListener(AbstractMessageListenerContainer.java:781) ~[spring-rabbit-1.6.5.RELEASE.jar:na]
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.invokeListener(AbstractMessageListenerContainer.java:701) ~[spring-rabbit-1.6.5.RELEASE.jar:na]
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer.access$001(SimpleMessageListenerContainer.java:99) [spring-rabbit-1.6.5.RELEASE.jar:na]
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer$1.invokeListener(SimpleMessageListenerContainer.java:191) ~[spring-rabbit-1.6.5.RELEASE.jar:na]
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer.invokeListener(SimpleMessageListenerContainer.java:1213) [spring-rabbit-1.6.5.RELEASE.jar:na]
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.executeListener(AbstractMessageListenerContainer.java:682) ~[spring-rabbit-1.6.5.RELEASE.jar:na]
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer.doReceiveAndExecute(SimpleMessageListenerContainer.java:1191) [spring-rabbit-1.6.5.RELEASE.jar:na]
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer.receiveAndExecute(SimpleMessageListenerContainer.java:1175) [spring-rabbit-1.6.5.RELEASE.jar:na]
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer.access$1200(SimpleMessageListenerContainer.java:99) [spring-rabbit-1.6.5.RELEASE.jar:na]
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer$AsyncMessageProcessingConsumer.run(SimpleMessageListenerContainer.java:1338) [spring-rabbit-1.6.5.RELEASE.jar:na]
	at java.lang.Thread.run(Thread.java:745) [na:1.8.0_111]
Caused by: org.springframework.amqp.AmqpException: No method found for class [B
```

`spring-boot-rabbitmq`这个工程中传递对象测试下来也是报这个错，并且会死循环。

## ityouknow (21 Jun 2017)

如果要传自定义对象(比如User)的话,记得实现Serialize接口,不然会报错:"org.springframework.amqp.AmqpException: No method found for class [B"

## archerLj (21 Jun 2017)

好了，谢谢🙏

