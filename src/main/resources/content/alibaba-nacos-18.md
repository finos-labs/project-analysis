#Only one instance returned when invoke getAllInstance method

Owner: alibaba

Repo: nacos

Labels: 

## jasonsuzhou (30 Jul 2018)

Test the nacos client:
I register 2 instances with same service name, IP but different port number.
e.g. 
com.mh.nacos010.helloWorldService 127.0.0.1 80
com.mh.nacos010.helloWorldService 127.0.0.1 81

After invoke registerInstance method, I checked the server side, the instance list have been written to 
com.alibaba.nacos.naming.iplist.com.mh.nacos010.helloWorldService
Later I invoke the getAllInstanceMethod, but only one instance returned:
code sample:
```java
String serverAddr = "127.0.0.1:8080";
		String serviceName = "com.mh.nacos010.helloWorldService";
		NamingService naming = NamingFactory.createNamingService(serverAddr);
		List<Instance> list = naming.getAllInstances(serviceName);
		if (list != null && !list.isEmpty()) {
			for (Instance ins : list) {
				System.out.println(ins.getInstanceId());
				System.out.println(ins.getIp());
				System.out.println(ins.getPort());
				System.out.println(ins.getWeight());
				System.out.println(ins.getClass());
				System.out.println(ins.isHealthy());
			}
		}
```
return sample:
```
127.0.0.1-80-DEFAULT-com.mh.nacos010.helloWorldService
127.0.0.1
80
2.0
class com.alibaba.nacos.api.naming.pojo.Instance
true
```
Only port number with 80 was returned.

My question:
Is nacos server already have load balance to provide only one available instance to client?

## nkorange (30 Jul 2018)

@jasonsuzhou getAllInstances always returns all instances of a service, so your test result is weird.
You can try the following steps:
1. Recheck the file on server and see if it is still inconsistent with the results of client.
2. Search the logs on client side in file {user.home}/logs/nacos/naming.log with keywords 'com.mh.nacos010.helloWorldService' and see what comes out.

## jasonsuzhou (31 Jul 2018)

1. The file in the server site contains 2 ips
2. Checked the log below:
	01 2018-07-30 17:12:53.494 INFO [main :c.a.n.c.naming] [] [] [] current ips:(2) dom: com.mh.nacos010.helloWorldService -> [{"instanceId":"192.168.1.1-81-DEFAULT-com.mh.nacos010.helloWorldService","ip":"192.168.1.1","metadata":{},"port":81,"valid":true,"weight":2.0},{"instanceId":"127.0.0.1-81-DEFAULT-com.mh.nacos010.helloWorldService","ip":"127.0.0.1","metadata":{},"port":81,"valid":true,"weight":2.0}]
01 2018-07-30 17:12:53.494 INFO [main :c.a.n.c.naming] [] [] [] current ips:(2) dom: com.mh.nacos010.helloWorldService -> [{"instanceId":"192.168.1.1-81-DEFAULT-com.mh.nacos010.helloWorldService","ip":"192.168.1.1","metadata":{},"port":81,"valid":true,"weight":2.0},{"instanceId":"127.0.0.1-81-DEFAULT-com.mh.nacos010.helloWorldService","ip":"127.0.0.1","metadata":{},"port":81,"valid":true,"weight":2.0}]
	Line 893: 01 2018-07-30 17:18:56.952 ERROR [main :c.a.n.c.naming] [] [] [CALL-SERVER] failed to req API:http://127.0.0.1:8080/nacos/v1/ns/api/srvIPXT. code:404 msg: dom not found: com.mh.nacos010.helloWorldService
	Line 905: 01 2018-07-30 17:18:56.961 ERROR [main :c.a.n.c.naming] [] [] [NA] failed to update dom: com.mh.nacos010.helloWorldService

After above error, log show below:
01 2018-07-30 17:33:05.493 INFO [main :c.a.n.c.naming] [] [] [] current ips:(1) dom: com.mh.nacos010.helloWorldService -> [{"instanceId":"127.0.0.1-80-DEFAULT-com.mh.nacos010.helloWorldService","ip":"127.0.0.1","metadata":{},"port":80,"valid":true,"weight":2.0}]
01 2018-07-30 17:33:05.493 INFO [main :c.a.n.c.naming] [] [] [] current ips:(1) dom: com.mh.nacos010.helloWorldService -> [{"instanceId":"127.0.0.1-80-DEFAULT-com.mh.nacos010.helloWorldService","ip":"127.0.0.1","metadata":{},"port":80,"valid":true,"weight":2.0}]

## jasonsuzhou (31 Jul 2018)

@nkorange Even I restart the server, I can only get one instance.

## nkorange (01 Aug 2018)

@jasonsuzhou execute command: 
`curl '$server_ip:8080/nacos/v1/ns/api/srvIPXT?dom=com.mh.nacos010.helloWorldService'`
See what returns.

## jasonsuzhou (09 Aug 2018)

@nkorange returned as below:
```
{"dom":"com.mh.nacos010.helloWorldService","cacheMillis":1000,"useSpecifiedURL":false,"hosts":[{"valid":true,"marked":false,"metadata":{},"instanceId":"127.0.0.1-81-DEFAULT-com.mh.nacos010.helloWorldService","port":81,"ip":"127.0.0.1","weight":2.0},{"valid":true,"marked":false,"metadata":{},"instanceId":"127.0.0.1-80-DEFAULT-com.mh.nacos010.helloWorldService","port":80,"ip":"127.0.0.1","weight":2.0}],"checksum":"6ec5d4a8dc3a5e124b956892447eeaa51533777250030","lastRefTime":1533777250030,"env":"","clusters":""}
```

## nkorange (30 Aug 2018)

@jasonsuzhou The server result is correct. Please clear all data on client, try again and then paste client log here. Thank you.

## jasonsuzhou (31 Aug 2018)

It is correct now. Thanks.
```
127.0.0.1-81-DEFAULT-com.mh.nacos010.helloWorldService
127.0.0.1
81
2.0
class com.alibaba.nacos.api.naming.pojo.Instance
true
127.0.0.1-80-DEFAULT-com.mh.nacos010.helloWorldService
127.0.0.1
80
2.0
class com.alibaba.nacos.api.naming.pojo.Instance
true
```

