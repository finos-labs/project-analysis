#server应用收集埋点信息时，缺少连接验证机制

Owner: apache

Repo: skywalking

Labels: 

## wu-sheng (23 Dec 2015)

服务端应具有客户端校验机制，防止网络异常包或非法数据包影响处理结果。
实现机制待讨论。


## wu-sheng (14 Jul 2016)

- 使用自定义协议包，以及协议包校验和。保证协议包的合理性，防止collector-server的运行时故障。


