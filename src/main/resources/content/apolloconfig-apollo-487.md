#请问下数据中心 cluster 是指什么？

Owner: apolloconfig

Repo: apollo

Labels: 

## yllions (14 Dec 2016)

“首先查找运行时cluster的配置（通过apollo.cluster指定），如果没有找到，则查找数据中心cluster的配置
，如果还是没有找到，则返回默认cluster的配置” 这里的数据中心cluster 是指什么？


## nobodyiam (14 Dec 2016)

是指`server.properties`中的`idc`属性，可以参考下面的链接：

https://github.com/ctripcorp/apollo/wiki/Java%E5%AE%A2%E6%88%B7%E7%AB%AF%E4%BD%BF%E7%94%A8%E6%8C%87%E5%8D%97#124-%E5%8F%AF%E9%80%89%E8%AE%BE%E7%BD%AE

