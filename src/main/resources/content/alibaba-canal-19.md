#mysql metaConnection链接泄漏

Owner: alibaba

Repo: canal

Labels: bug 

## agapple (05 Mar 2013)

运行一段时间后，与mysql之间的数据库链接达到了几千条，且全部都是处于ESTABLISHED状态。

进行jmap dump内存对象，却无法找到SocketChannelImpl的相关实例，说明是被full gc回收了. 


