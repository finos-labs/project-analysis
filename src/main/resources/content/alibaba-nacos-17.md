#Etcd can only provide infrequently write operation,why nacos still use raft ?

Owner: alibaba

Repo: nacos

Labels: 

## wei7202839 (30 Jul 2018)

Etcd's max concrrent write operation is 1000/s ,beacause of raft protocol
why nacos still use raft in service registry and unregistry occasion  ???

https://baijiahao.baidu.com/s?id=1605824836366915364&wfr=spider&for=pc

![image](https://user-images.githubusercontent.com/37545348/43376526-d51b4b80-93ed-11e8-9b83-7fb2af904143.png)


## wei7202839 (31 Jul 2018)

ConfigServer of hsf performs better in service registry scene, why not introduce  it's
certain mechanisms to nacos  ???

## wei7202839 (02 Aug 2018)

I guess nacos-core may play a role  like configserver session layer,
however it's just a guess and nacos-core module  is empty now

