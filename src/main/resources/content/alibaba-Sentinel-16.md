#对于新接入的服务，管理控制台左边的菜单树需要刷一会才能出来

Owner: alibaba

Repo: Sentinel

Labels: kind/question area/dashboard 

## 1423302537 (31 Jul 2018)

对于新接入的服务，管理控制台左边的菜单树需要刷一会才能出来，上报时间可以设置的更短一点





## CarpenterLee (01 Aug 2018)

可通过 JVM 参数 `-Dcsp.sentinel.heartbeat.interval.ms` 设置心跳周期，但心跳发送太快会给控制台带来负担。参考：[启动配置项](https://github.com/alibaba/Sentinel/wiki/%E5%90%AF%E5%8A%A8%E9%85%8D%E7%BD%AE%E9%A1%B9)。

