#客户端连接控制台端口问题

Owner: alibaba

Repo: Sentinel

Labels: kind/bug 

## mushang8923 (01 Aug 2018)

<!-- Here is for bug reports and feature requests ONLY! 

If you're looking for help, please check our mail list and the Gitter room.
-->

## Issue Description

Type: *bug report* or *feature request*

### Describe what happened (or what feature you want)
同一台机器 （springboot+dubbo）
consumer ：-Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=bos-web-provider -Dcsp.sentinel.api.port=8719
provider ： -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=bos-app-provider  -Dcsp.sentinel.api.port=8720
引入包
    <dependency>
      <groupId>com.alibaba.csp</groupId>
      <artifactId>sentinel-dubbo-adapter</artifactId>
      <version>${sentinel.adapter.version}</version>
    </dependency>
    <dependency>
      <groupId>com.alibaba.csp</groupId>
      <artifactId>sentinel-transport-simple-http</artifactId>
      <version>${sentinel.http.version}</version>
    </dependency>
通过控制台显示，只显示了1个应用的信息，机器端口号为8719
如果不设置-Dcsp.sentinel.api.port=8720，能显示两个，但是配置限流规则后，flowRules只有在provider端能看到，consumer端flowRules为空
### Describe what you expected to happen
通过端口，针对不同的应用配置对应的生效的限流规则

### How to reproduce it (as minimally and precisely as possible)

1. 
2. 
3. 

### Tell us your environment


### Anything else we need to know?





## sczyh30 (01 Aug 2018)

Thanks for reporting!

