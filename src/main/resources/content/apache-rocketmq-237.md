#Spinlock bad performance with high race conditions.

Owner: apache

Repo: rocketmq

Labels: 

## pifuant (09 Mar 2018)

The issue tracker is **ONLY** used for bug report and feature request. 

Any question or RocketMQ proposal please use our [mailing lists](http://rocketmq.apache.org/about/contact/).

**BUG REPORT**

1. Please describe the issue you observed:

- What did you do (The steps to reproduce)?

- What did you expect to see?
Spinlock had well performance with high race conditions.

- What did you see instead?
Spinlock had bad performance with high race conditions.

2. Please tell us about your environment:

3. Other information (e.g. detailed explanation, logs, related issues, suggestions how to fix, etc):
CLH spinlock was a better choice.

**FEATURE REQUEST**

1. Please describe the feature you are requesting.

2. Provide any additional detail on your proposed use case for this feature.

2. Indicate the importance of this issue to you (blocker, must-have, should-have, nice-to-have). Are you currently using any workarounds to address this issue?

4. If there are some sub-tasks using -[] for each subtask and create a corresponding issue to map to the sub task:

- [sub-task1-issue-number](example_sub_issue1_link_here): sub-task1 description here, 
- [sub-task2-issue-number](example_sub_issue2_link_here): sub-task2 description here,
- ...

## zhouxinyu (09 Mar 2018)

Hi,

The config `useReentrantLockWhenPutMessage` can be used to turn off the spin lock.

## pifuant (09 Mar 2018)

@zhoudiqiu ， please take a look at  #238

## alynlin (12 Mar 2018)

When the broker sets mapedFileSizeConsumeQueue=300000 and the total number of messages sent by the broker reaches this value, the following error occurs in the storeerror.log

`2018-03-12 15:16:36 WARN FlushConsumeQueueService - Offset for /opt/rocketmq/store/consumequeue/xxx_xxxx_test/6/00000000000000000000 not matched. Request offset: 300000, index: 1, mappedFileSize: 300000, mappedFiles count: 1
2018-03-12 15:16:36 WARN FlushConsumeQueueService - findMappedFileByOffset failure.
java.lang.ArrayIndexOutOfBoundsException: 1
        at java.util.concurrent.CopyOnWriteArrayList.get(CopyOnWriteArrayList.java:387) ~[na:1.8.0_121]
        at java.util.concurrent.CopyOnWriteArrayList.get(CopyOnWriteArrayList.java:396) ~[na:1.8.0_121]
        at org.apache.rocketmq.store.MappedFileQueue.findMappedFileByOffset(MappedFileQueue.java:478) [rocketmq-store-4.2.0.jar:4.2.0]
        at org.apache.rocketmq.store.MappedFileQueue.flush(MappedFileQueue.java:427) [rocketmq-store-4.2.0.jar:4.2.0]
        at org.apache.rocketmq.store.ConsumeQueue.flush(ConsumeQueue.java:324) [rocketmq-store-4.2.0.jar:4.2.0]
        at org.apache.rocketmq.store.DefaultMessageStore$FlushConsumeQueueService.doFlush(DefaultMessageStore.java:1664) [rocketmq-store-4.2.0.jar:4.2.0]
        at org.apache.rocketmq.store.DefaultMessageStore$FlushConsumeQueueService.run(DefaultMessageStore.java:1684) [rocketmq-store-4.2.0.jar:4.2.0]
        at java.lang.Thread.run(Thread.java:745) [na:1.8.0_121]`

## pifuant (12 Mar 2018)

@alynlin what do you mean?

## vongosling (20 Mar 2018)

@pifuant Could you comment on our issue question at first?

## pifuant (20 Mar 2018)

@vongosling what do you mean?

## vongosling (26 Mar 2018)

@pifuant I have not seen your correct description in your question, could you please provide your question as our instruction template?

## vongosling (26 Mar 2018)

close this issue, welcome to provide issue description next time.

