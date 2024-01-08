#BufferGroup中发送功能应增加发送副本

Owner: apache

Repo: skywalking

Labels: 

## wu-sheng (04 Dec 2015)

Line 69: while (!DataSenderFactory.getSender().send(data.toString())) {
发送程序应考虑副本问题，防止服务端重启，造成日志数据丢失。
增强程序的异常处理能力。


## wu-sheng (21 Dec 2015)

1.通过新的DataSenderWithCopies发送器实现该功能。可发送多个副本，保证服务器故障时，消息的可靠性。


## wu-sheng (22 Dec 2015)

增加副本处理机制，至少支持两副本。要求服务端以集群方式部署，否则请关闭副本发送，以免对服务器造成额外负担。


## wu-sheng (22 Dec 2015)

可以根据可用连接数，处理可发送副本。服务端数量减少时，发送副本数顺次减少。


