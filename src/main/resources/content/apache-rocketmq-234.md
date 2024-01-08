#多tag不能消费

Owner: apache

Repo: rocketmq

Labels: 

## uptop (07 Mar 2018)

The issue tracker is **ONLY** used for bug report and feature request. 

Any question or RocketMQ proposal please use our [mailing lists](http://rocketmq.apache.org/about/contact/).

**BUG REPORT**

1. Please describe the issue you observed:

- What did you do (The steps to reproduce)?
producer对一条message打了多个tag


- What did you expect to see?
consumer订阅其中一个tag，期望正常消费

- What did you see instead?
producer正常发送，但consumer一直没有消费到message

2. Please tell us about your environment:
RocketMQ4.1.0 jdk1.8



## zhouxinyu (07 Mar 2018)

Hi, one message could only have one tag, and the consumer could subscribe with multiple tags.

Thanks and the issue tracker is ONLY used for bug report and feature request.

## uptop (09 Mar 2018)

非常感谢回复@zhouxinyu ，看了代码知道一条message不支持打多个tag，但公司有这方面的业务需求，需要对一条message打多个tag，订阅消费，broker filter代码和client filter代码我做了修改，已经支持该功能并测试通过，可以将该功能提交到社区么。

## zhouxinyu (09 Mar 2018)

Of course, any contribution is welcome.
As a new feature, we recommend discussing it in dev list first.
And please check out this feature [filter-by-sql92](http://rocketmq.apache.org/docs/filter-by-sql92-example/)  whether meets your demand.

