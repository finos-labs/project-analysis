#zookeeper 分布式锁报错

Owner: xkcoding

Repo: spring-boot-demo

Labels: done question 

## wuyongdec (31 May 2019)

`2019-05-31 17:27:41.247 ERROR 10732 --- [ain-EventThread] org.apache.zookeeper.ClientCnxn          : Error while calling watcher 

java.lang.IncompatibleClassChangeError: Inconsistent constant pool data in classfile for class org/apache/curator/framework/CuratorFramework. Method lambda$postSafeNotify$0(Ljava/lang/Object;)V at index 99 is CONSTANT_MethodRef and should be CONSTANT_InterfaceMethodRef
	at org.apache.curator.framework.CuratorFramework.postSafeNotify(CuratorFramework.java:344) ~[curator-framework-4.1.0.jar:4.1.0]
	at org.apache.curator.framework.recipes.locks.LockInternals$2.process(LockInternals.java:69) ~[curator-recipes-4.1.0.jar:4.1.0]
	at org.apache.curator.framework.imps.NamespaceWatcher.process(NamespaceWatcher.java:77) ~[curator-framework-4.1.0.jar:4.1.0]
	at org.apache.zookeeper.ClientCnxn$EventThread.processEvent(ClientCnxn.java:525) ~[zookeeper-3.5.4-beta.jar:3.5.4-beta-7f51e5b68cf2f80176ff944a9ebd2abbc65e7327]
	at org.apache.zookeeper.ClientCnxn$EventThread.run(ClientCnxn.java:500) ~[zookeeper-3.5.4-beta.jar:3.5.4-beta-7f51e5b68cf2f80176ff944a9ebd2abbc65e7327]
`

## wuyongdec (31 May 2019)

把curator换成
`4.2.0`好了

## xkcoding (31 May 2019)

@wuyongdec

注意 curator 和 zookeeper 的版本对应关系

在我的pom文件里有说明的

https://github.com/xkcoding/spring-boot-demo/blob/715275da6c5b6fe863a2b4fceb3ee25d6ceda0e9/spring-boot-demo-zookeeper/pom.xml#L42-L48


