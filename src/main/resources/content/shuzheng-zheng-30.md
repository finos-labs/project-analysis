#部署ZhengApiRpcServiceApplication 出错

Owner: shuzheng

Repo: zheng

Labels: 

## yovenny (10 May 2017)

按照README."首先启动 zheng-upms-rpc-service(直接运行src目录下的ZhengUpmsRpcServiceApplication#main方法启动) => zheng-upms-server(jetty)，然后按需启动对应子系统xxx的zheng-xxx-rpc-service(main方法) => zheng-xxx-webapp(jetty)"

然后

```java
2017-05-10 13:54:54,404 [DubboShutdownHook] INFO  [com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol] -  [DUBBO] Close dubbo server: /192.168.0.87:20880, dubbo version: 2.5.3, current host: 127.0.0.1
2017-05-10 13:54:54,405 [DubboShutdownHook] INFO  [com.alibaba.dubbo.remoting.transport.AbstractServer] -  [DUBBO] Close NettyServer bind /0.0.0.0:20880, export /192.168.0.87:20880, dubbo version: 2.5.3, current host: 127.0.0.1
2017-05-10 13:54:54,412 [DubboShutdownHook] INFO  [com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol] -  [DUBBO] Unexport service: dubbo://192.168.0.87:20880/com.zheng.api.rpc.api.ApiService?anyhost=true&application=zheng-api-rpc-service&dubbo=2.5.3&interface=com.zheng.api.rpc.api.ApiService&methods=hello&pid=3506&side=provider&timeout=10000&timestamp=1494395693467, dubbo version: 2.5.3, current host: 127.0.0.1
2017-05-10 13:54:54,412 [DubboShutdownHook] INFO  [com.alibaba.dubbo.rpc.protocol.injvm.InjvmProtocol] -  [DUBBO] Unexport service: injvm://127.0.0.1/com.zheng.api.rpc.api.ApiService?anyhost=true&application=zheng-api-rpc-service&dubbo=2.5.3&interface=com.zheng.api.rpc.api.ApiService&methods=hello&pid=3506&side=provider&timeout=10000&timestamp=1494395693467, dubbo version: 2.5.3, current host: 127.0.0.1
```


## yovenny (10 May 2017)


```java
2017-05-10 14:30:55,748 [ZkClient-EventThread-14-127.0.0.1:2181] INFO  [org.I0Itec.zkclient.ZkEventThread] - Terminate ZkClient event thread.
2017-05-10 14:30:55,750 [DubboShutdownHook] INFO  [org.apache.zookeeper.ZooKeeper] - Session: 0x100007cb4c1000b closed
2017-05-10 14:30:55,750 [main-EventThread] INFO  [org.apache.zookeeper.ClientCnxn] - EventThread shut down
```
日志上面还有这部分，是我的zookeeper开启有问题吗？

## yovenny (10 May 2017)

还是说项目原有ucenter,shop,pay,cms的已经自动部署了,但好像pay没看到。。。

## Anayuta (07 Feb 2018)

@yovenny  具体是怎么解决的？

