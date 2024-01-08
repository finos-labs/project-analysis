#canal server在处理客户端主动关闭连接时，是否需要关闭instance

Owner: alibaba

Repo: canal

Labels: enhancement 

## agapple (19 Mar 2013)

在canal 1.0.2版本以及以前的版本，在设计的时候，canal server针对instance管理一直都是lazy模式处理.  当canal client断开链接时，canal server会主动关闭instance，等下一次canal client链接后再重新创建. 

先前的考虑：
1. canal server主动关闭instance，主要是考虑是想将canal server作成无状态，下一次canal client可以随机选择一台机器获取数据，新的canal server会重新启动instance，整个HA模型会比较简单. 

改进：
1.  canal server在client退出时不主动关闭instance资源
2.  下一次canal client重新链接后，需要通过Cluster模式(通过zookeeper获取到HA模式中正在运行的canal server的机器信息)，然后与其重新建立链接即可

一个前提，客户端disconnect/connect间隔时间不会很长，尽可能减少资源消耗. 


