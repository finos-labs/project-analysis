#demo run exception 

Owner: apache

Repo: incubator-seata

Labels: 

## oceanos (10 Jan 2019)

Exception in thread "main" com.alibaba.dubbo.rpc.RpcException: Failed to invoke the method deduct in the service com.alibaba.fescar.tm.dubbo.StorageService. No provider available for the service com.alibaba.fescar.tm.dubbo.StorageService from registry 224.5.6.7:1234 on the consumer 192.168.10.1 using the dubbo version 2.5.3. Please check if the providers have been started and registered.

## sharajava (11 Jan 2019)

Sorry about the misleading. Registry on multicast should work with -Djava.net.preferIPv4Stack=true, will update desc about demo later.

## maxwell1389 (12 Jan 2019)

Also show the same error, how to fix? @sharajava 

## hezudaopp (16 Jan 2019)

> Also show the same error, how to fix? @sharajava

me too.

## zhou6675021 (21 Jan 2019)

Exception in thread "main" com.alibaba.dubbo.rpc.RpcException: Failed to invoke the method deduct in the service com.alibaba.fescar.tm.dubbo.StorageService. No provider available for the service com.alibaba.fescar.tm.dubbo.StorageService from registry 224.5.6.7:1234 on the consumer 192.168.175.1 using the dubbo version 2.5.3. Please check if the providers have been started and registered.
/also me

## zhou6675021 (21 Jan 2019)

![image](https://user-images.githubusercontent.com/23535473/51464078-be3a2d00-1d9f-11e9-808c-3446cdfcd740.png),It doesn't work.


## qusthuang (22 Jan 2019)

multicast has problem.I changed zookeeper registry,so it works! <dubbo:registry protocol="zookeeper" address="127.0.0.1:2181" />

muticast will create cache file in ~/.dubbo/dubbo-registry-224.5.6.7.cache which contains the provider info ,such as below : 
"com.alibaba.fescar.tm.dubbo.AccountService=dubbo\://172.28.1.213\:20881/com.alibaba.fescar.tm.dubbo.AccountService?anyhost\=true&application\=dubbo-demo-account-service&dubbo\=2.5.3&interface\=com.alibaba.fescar.tm.dubbo.AccountService&methods\=debit&pid\=9853&side\=provider&timeout\=10000&timestamp\=1548150369444
com.alibaba.fescar.tm.dubbo.OrderService=dubbo\://172.28.1.213\:20883/com.alibaba.fescar.tm.dubbo.OrderService?anyhost\=true&application\=dubbo-demo-order-service&dubbo\=2.5.3&interface\=com.alibaba.fescar.tm.dubbo.OrderService&methods\=create&pid\=9855&side\=provider&timeout\=10000&timestamp\=1548150393579"

we can see the storageService is not in file.






## xuqinglei (26 Jan 2019)

if i use zookeeper to registry,should i need to add the -Djava.net.preferIPv4Stack=true on the vm options?

## xuqinglei (26 Jan 2019)

when I use zookeeper to registry service

Exception in thread "main" com.alibaba.dubbo.rpc.RpcException: Failed to invoke the method deduct in the service com.alibaba.fescar.tm.dubbo.StorageService. Tried 3 times of the providers [172.16.50.81:20882] (1/1) from the registry 172.16.50.16:2181 on the consumer 172.16.50.81 using the dubbo version 2.5.3. Last error is: Invoke remote method timeout. method: deduct, provider: dubbo://172.16.50.81:20882/com.alibaba.fescar.tm.dubbo.StorageService?anyhost=true&application=dubbo-demo-app&check=false&dubbo=2.5.3&interface=com.alibaba.fescar.tm.dubbo.StorageService&methods=deduct&pid=922512&side=consumer&timeout=10000&timestamp=1548473268651, cause: Waiting server-side response timeout. start time: 2019-01-26 11:28:09.404, end time: 2019-01-26 11:28:19.405, client elapsed: 0 ms, server elapsed: 10001 ms, timeout: 10000 ms, request: Request [id=2, version=2.0.0, twoway=true, event=false, broken=false, data=RpcInvocation [methodName=deduct, parameterTypes=[class java.lang.String, int], arguments=[C00321, 2], attachments={path=com.alibaba.fescar.tm.dubbo.StorageService, TX_XID=220.250.64.225:8091:64540387, interface=com.alibaba.fescar.tm.dubbo.StorageService, version=0.0.0, timeout=10000}]], channel: /172.16.50.81:1372 -> /172.16.50.81:20882

Are there any friends who have the same problem?

## lees5488 (28 Jan 2019)

> multicast has problem.I changed zookeeper registry,so it works! <dubbo:registry protocol="zookeeper" address="127.0.0.1:2181" />
> 
> muticast will create cache file in ~/.dubbo/dubbo-registry-224.5.6.7.cache which contains the provider info ,such as below :
> "com.alibaba.fescar.tm.dubbo.AccountService=dubbo://172.28.1.213:20881/com.alibaba.fescar.tm.dubbo.AccountService?anyhost=true&application=dubbo-demo-account-service&dubbo=2.5.3&interface=com.alibaba.fescar.tm.dubbo.AccountService&methods=debit&pid=9853&side=provider&timeout=10000&timestamp=1548150369444
> com.alibaba.fescar.tm.dubbo.OrderService=dubbo://172.28.1.213:20883/com.alibaba.fescar.tm.dubbo.OrderService?anyhost=true&application=dubbo-demo-order-service&dubbo=2.5.3&interface=com.alibaba.fescar.tm.dubbo.OrderService&methods=create&pid=9855&side=provider&timeout=10000&timestamp=1548150393579"
> 
> we can see the storageService is not in file.

i used this method which you supported, it seem does not effective,
windows10/jdk8
![image](https://user-images.githubusercontent.com/24403402/51821010-98b6a180-2312-11e9-82a6-c54dcb3ff0c0.png)
zookeeper
![image](https://user-images.githubusercontent.com/24403402/51821052-bedc4180-2312-11e9-987f-14861961bd59.png)


## binzhaomobile (10 Feb 2019)

I met the same trouble and solved it by disabling the VMWare Virtual Network Adapter, or disconnect the Vpn Connection. Please refer to the following document:
https://blog.csdn.net/liuhaitao729/article/details/81073608

## leizhiyuan (16 May 2019)

because of no update for a long time, I will close the issue, if you have other questions, please reopen it or create a new one

