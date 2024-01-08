#dubbo不支持json序列化

Owner: apache

Repo: dubbo

Labels: 

## alexxiang (13 Mar 2014)

使用http协议，序列化采用json，通过抓包，发现客户端发出的http包中Content-Type: application/x-java-serialized-object。2.4.10和2.5.3版本都是这样。客户端的dubbo配置如下： 
<dubbo:protocol name="http" serialization="json" />
<dubbo:reference id="helloService" interface="com.dubbo.demo.HelloService" version="1.0.0"  protocol="http"  url="http://127.0.0.1:9000/http/com.dubbo.demo.HelloService" />


## alexxiang (13 Mar 2014)

<dubbo:protocol name="http" serialization="json" />
<dubbo:reference id="helloService" interface="com.dubbo.demo.HelloService" version="1.0.0"
                protocol="http"   url="http://127.0.0.1:9000/http/com.dubbo.demo.HelloService" />


## alexxiang (13 Mar 2014)

<dubbo:protocol name="http" serialization="json" />


## ralf0131 (15 May 2018)

Dubbo now supports fastjson, please give it a try.

