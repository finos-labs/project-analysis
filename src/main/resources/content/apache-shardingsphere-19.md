#sum和avg函数，不加别名不执行merger，加了空指针异常

Owner: apache

Repo: shardingsphere

Labels: type: bug 

## tong549761863 (26 Feb 2016)

AccumulationAggregationUnit和AvgAggregationUnit类的doMerger函数的入参在查询结果为空的时候，为null，需要加非空判断


