#FlushConsumeQueueService 报越界错误

Owner: apache

Repo: rocketmq

Labels: 

## alynlin (13 Mar 2018)

When the broker sets mapedFileSizeConsumeQueue=300000 and the total number of messages sent by the broker reaches this value, the following error occurs in the storeerror.log

2018-03-12 15:16:36 WARN FlushConsumeQueueService - Offset for /opt/rocketmq/store/consumequeue/xxx_xxxx_test/6/00000000000000000000 not matched. Request offset: 300000, index: 1, mappedFileSize: 300000, mappedFiles count: 1 2018-03-12 15:16:36 WARN FlushConsumeQueueService - findMappedFileByOffset failure. java.lang.ArrayIndexOutOfBoundsException: 1 at java.util.concurrent.CopyOnWriteArrayList.get(CopyOnWriteArrayList.java:387) ~[na:1.8.0_121] at java.util.concurrent.CopyOnWriteArrayList.get(CopyOnWriteArrayList.java:396) ~[na:1.8.0_121] at org.apache.rocketmq.store.MappedFileQueue.findMappedFileByOffset(MappedFileQueue.java:478) [rocketmq-store-4.2.0.jar:4.2.0] at org.apache.rocketmq.store.MappedFileQueue.flush(MappedFileQueue.java:427) [rocketmq-store-4.2.0.jar:4.2.0] at org.apache.rocketmq.store.ConsumeQueue.flush(ConsumeQueue.java:324) [rocketmq-store-4.2.0.jar:4.2.0] at org.apache.rocketmq.store.DefaultMessageStore$FlushConsumeQueueService.doFlush(DefaultMessageStore.java:1664) [rocketmq-store-4.2.0.jar:4.2.0] at org.apache.rocketmq.store.DefaultMessageStore$FlushConsumeQueueService.run(DefaultMessageStore.java:1684) [rocketmq-store-4.2.0.jar:4.2.0] at java.lang.Thread.run(Thread.java:745) [na:1.8.0_121]

