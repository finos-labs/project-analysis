#cannal.properties里的配制选项是否轻微错误?

Owner: alibaba

Repo: canal

Labels: bug 

## wenzhihong2003 (23 Feb 2013)

在deployer工程里的canal.properties里的

canal.address= 

是否应该改为

canal.ip=

因为在 CanalConstants 这个类里是如下定义的:

public static final String CANAL_IP                          = ROOT + "." + "ip";


## zavakid (23 Feb 2013)

眼神真敏锐啊！
确实是这样的，代码中有这样的逻辑：

``` java
ip = getProperty(properties, CanalConstants.CANAL_IP);
if (StringUtils.isEmpty(ip)) {
  ip = AddressUtils.getHostIp();
}
```

所以一直没有别发现。

@wenzhihong2003  要不你来改下，然后做一次 pull request？


## wenzhihong2003 (24 Feb 2013)

#15 


## zavakid (24 Feb 2013)

多谢：）


