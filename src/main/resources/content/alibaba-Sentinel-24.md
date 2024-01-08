#关于Sentinel 关于动态规则问题

Owner: alibaba

Repo: Sentinel

Labels: kind/question 

## hone-liu (02 Aug 2018)

用了下dashboard 和  sentinel-dubbo-adapter,  目前的dashboard 是利用 推的方式将rule 推送到服务器节点的sentinel，同时wiki 中也有提到通过实现 DataSource 接口来实现规则持久化存储，原文描述如下：

> 虽然目前控制台使用内存态直接设置客户端规则，但实现 DataSource 接口是更加可靠的做法。当我们通过控制台设置规则后，控制台可以将规则推送到统一的规则中心，客户端监听规则中心实时获取变更

从运维的角度，相当于在控制台 和  sentinel 客户端之间又多了一个 rule 的数据源。而且这个数据源是和控制台 、client 都存在依赖关系。  如果是一个高可用服务提供者，就面临着我必须对 rule 数据源做高可用设计，否则一旦rule 数据源宕机后，sentinel 控制台和 client 都会受到影响。
能否考虑 sentinel 控制台，rule 数据源和 client 之间只做单向依赖，减少运维风险？

## suhuaguo (02 Aug 2018)

按照部署规范来的话，基础设施组件的数据库是和业务分开的，主要主从即可。规则中心，一般是配置中心。

## sczyh30 (13 Sept 2018)

More information can be found in the blog post: [在生产环境中使用 Sentinel 控制台](https://github.com/alibaba/Sentinel/wiki/%E5%9C%A8%E7%94%9F%E4%BA%A7%E7%8E%AF%E5%A2%83%E4%B8%AD%E4%BD%BF%E7%94%A8-Sentinel-%E6%8E%A7%E5%88%B6%E5%8F%B0)

