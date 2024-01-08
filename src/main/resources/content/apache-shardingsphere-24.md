#MySQLSelectVisitor与druid最新G.A版不兼容

Owner: apache

Repo: shardingsphere

Labels: status: won't fix 

## bwzhang2011 (01 Mar 2016)

Hi, 当前DRUID的版本最新是1.0.17，而sharding-jdbc使用的是1.0.12，而更新编译后发现MySqlSelectGroupByExpr 这个类已经从druid中移除掉了，这个该如解决（我们一些环境的DRUID版本均是1.0.16等版本，考虑到工程依赖于druid的sql parser处理）；另外，shard-jdbc可否考虑增加针对oracle的支持（计划什么时候增加相关支持）


## terrymanu (01 Mar 2016)

druid版本目前无升级计划，如有需要请先自行patch吧，或者保持和sharding-jdbc相同的druid版本。
由于其他功能优先级更高，目前无oracle支持的计划。


## terrymanu (01 Mar 2016)

druid版本目前无升级计划，如有需要请先自行patch吧，或者保持和sharding-jdbc相同的druid版本。
由于其他功能优先级更高，目前无oracle支持的计划。


## bwzhang2011 (01 Mar 2016)

@terrymanu, 好的，刚才已经指出了我们这边用的DRUID的版本要高于1.0.12（比如1.0.16等）；此外，
我注意到MySQLSelectVisitor类中引用的MySqlSelectGroupByExpr（即1.0.12版本的）与1.0.17（当前G.A版本）的MySqlOrderingExpr内容基本是近似的——即替换成该类后不会报错——从druid的角度来说，该类主要涉及的仍然是order的处理（升序和降序）因此druid才会将其做了替代的（猜测）。后续我会打一个patch的包放到本地的maven(测试一下其他的是否能与当前的DRUID兼容）——shard jdbc也可以多一些分支


