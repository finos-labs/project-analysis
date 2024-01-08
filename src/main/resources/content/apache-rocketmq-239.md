#消费端重启，历史消息重复推送问题

Owner: apache

Repo: rocketmq

Labels: 

## amapleleaf (09 Mar 2018)

在集群模式下，只有一个消费端应用，消费端消费完后重启应用之前消费过的数据又被重复推送过来。如果消费完后等一会儿再重启消费端应用就不会有此问题，求解。版本：4.2.0

## amapleleaf (09 Mar 2018)

补充：消费位置设置的是CONSUME_FROM_FIRST_OFFSE. 使用的是push模式

## zhouxinyu (09 Mar 2018)

Please **respect** our [issue template](https://github.com/apache/rocketmq/blob/master/.github/ISSUE_TEMPLATE.md) and remember the issue tracker is **ONLY** used for bug report and feature request. 

Any question or RocketMQ proposal please use our [mailing lists](http://rocketmq.apache.org/about/contact/).

