#jobThread 线程问题

Owner: xuxueli

Repo: xxl-job

Labels: 

## gaoyaming (21 Feb 2017)

jobThread  每个任务一直有一个线程，当任务执行时间比较长，执行次数比较频繁时，会产生任务积压延迟。

## xuxueli (24 Feb 2017)

你好，目前同一个任务在执行器是通过队列保证串行执行模式，后续任务在阻塞等待执行。
后面针对并发调度会提供一系列供选择的策略：串行调度队列（默认）、并行、忽略、覆盖；
谢谢反馈啊 ：）

## waterWang (24 Mar 2017)

看了jetty的架构，然后看executor。所有的执行器是使用handler注册到jetty，然后让jetty管理？

## nutguo (12 Jun 2017)

当job数量达到一定数量的时候，每个执行器上的都会产生大量的线程，所以是否应该在triggerQueue的size为0的时候，销毁线程。
我看到在todo list 有一项是 “任务线程轮空30次后自动销毁，降低低频任务的无效线程消耗”，可以解决这个问题，不知道作者什么时候完成啊

## xuxueli (14 Jun 2017)

@nutguo @waterWang 感谢关注啊：）
这个问题，在v1.8.0快照版本release的时候会上线修复掉的，已经在排期是实现了哈

