#同一个connection不能同时执行两次查询,ResultSet会报错

Owner: apache

Repo: shardingsphere

Labels: status: invalid 

## pingww (29 Feb 2016)

// 如果同时查询一个数据源上面两个分表，
// 则此处复用了同一个connection，结果集ResultSet会报错，应该新建connection
 if (connectionMap.containsKey(dataSourceName)) {     ???
            return connectionMap.get(dataSourceName);
 }


## hanahmily (29 Feb 2016)

同时查询一个数据源上的两个分表不会报错的。测试用例`com.dangdang.ddframe.rdb.integrate.db.SelectShardingDataBasesOnlyTest`中有测试一个数据源多个分表的情况。如果有问题请给出测试代码。谢谢


## pingww (01 Mar 2016)

不要意思，是我自己动态数据源组内部并发问题，不过一个数据源只有一个连接，多个分表查询并发上不去


