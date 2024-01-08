#dubbo当做dao层使用，mvc作为service层的疑问？

Owner: shuzheng

Repo: zheng

Labels: 

## chenjunqi (20 Jun 2017)

引用[dubbo.io](http://dubbo.io/User+Guide-zh.htm)中的原文
服务接口尽可能大粒度，每个服务方法应代表一个功能，而不是某功能的一个步骤，否则将面临分布式事务问题，Dubbo暂未提供分布式事务支持。
服务接口建议以业务场景为单位划分，并对相近业务做抽象，防止接口数量爆炸
不建议使用过于抽象的通用接口，如：Map query(Map)，这样的接口没有明确语义，会给后期维护带来不便。

# Q1：
zheng的项目目前将dubbo作为dao层，采用velocity自动生成一系列的样板代码，简化了不少工作量。
看示例项目，是将service层放到了spring mvc层。通常我都是在业务层（service）的粒度做事务控制，而在zheng的dubbo层做事务控制就显得粒度太大了。

# Q2 ：
基于 Q1 我的本地修正是在将那些dubbo暴露的dao服务，再抽取一个composite的层，组合各类服务暴露给 spring mvc使用。这样就能将 原先在mvc的层，划到 dubbo中处理。但这样会导致生成代码时候会丢弃我自定义的service类。



## shuzheng (25 Jun 2017)

你说的对，service接口尽量是一个事务操作，zheng项目里面的service只是包含了dao的所有方法而已

