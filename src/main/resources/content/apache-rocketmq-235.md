#mq地址为域名时RebalanceService解析报错

Owner: apache

Repo: rocketmq

Labels: 

## cyejing (08 Mar 2018)

https://github.com/apache/rocketmq/blob/a5d72be665f80487fd81e60604fac5c8e6a971ea/remoting/src/main/java/org/apache/rocketmq/remoting/common/RemotingHelper.java#L56

如地址为: http://xxx:8080/xxx
这段代码不能解析出正确的端口

## devsong (09 Mar 2018)

tcp socket address,like ip:port(eg:127.0.0.1:8080),not http url pattern

## lindzh (09 Mar 2018)

If you want to use http for nameserver discovery,please see here.

```
    public static final String WS_DOMAIN_NAME = System.getProperty("rocketmq.namesrv.domain", DEFAULT_NAMESRV_ADDR_LOOKUP);
    public static final String WS_DOMAIN_SUBGROUP = System.getProperty("rocketmq.namesrv.domain.subgroup", "nsaddr");
    // http://jmenv.tbsite.net:8080/rocketmq/nsaddr
    public static final String WS_ADDR = "http://" + WS_DOMAIN_NAME + ":8080/rocketmq/" + WS_DOMAIN_SUBGROUP;
```

other wise please go to http://rocketmq.apache.org/docs/simple-example/ for message pub/sub usage documentation.

## zhouxinyu (09 Mar 2018)

As @devsong said, this `addr` is a TCP socket address.

And the Apache RocketMQ community is global, we recommend using English as our preferred language~

