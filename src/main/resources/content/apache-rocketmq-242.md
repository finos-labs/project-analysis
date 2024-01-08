#new consumer may consume all message

Owner: apache

Repo: rocketmq

Labels: 

## fuyou001 (20 Mar 2018)

@The issue tracker is **ONLY** used for bug report and feature request. 

Any question or RocketMQ proposal please use our [mailing lists](http://rocketmq.apache.org/about/contact/).


**FEATURE REQUEST**
1. Please describe the feature you are requesting.

  assume one MQ Broker cluster,the broker commitLog min offset is zero , a new consumer start ,
the consumer will starting consume  queue's offset zero messages，it's a problem。

2. Provide any additional detail on your proposed use case for this feature.

3. Indicate the importance of this issue to you (blocker, must-have, should-have, nice-to-have). Are you currently using any workarounds to address this issue?

* must-have

4. If there are some sub-tasks using -[] for each subtask and create a corresponding issue to map to the sub task:
 * org.apache.rocketmq.broker.processor.ConsumerManageProcessor#queryConsumerOffset method   refactor

## vongosling (14 Jul 2018)

I could not figure out what you have pointed out, could you please comment more details for me?

