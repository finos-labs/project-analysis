#改造多线程执行模型

Owner: apache

Repo: shardingsphere

Labels: type: enhancement 

## hanahmily (25 Feb 2016)

原来的实现是每次执行SQL时新建线程池，这样做不够高效。
改造为每个ShardingDataSource对象维护一个线程池。


