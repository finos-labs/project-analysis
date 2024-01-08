#consistency when executing update

Owner: apache

Repo: shardingsphere

Labels: type: discussion 

## tangdi (26 Jan 2016)

Hi: 
In Sharding-jdbc PreparedStatementExecutor will execute RoutedPreparedStatement list concurrently. Do you have any mechanism to ensure the consistency between multiple datasources when executing updates?  It seems to me that if the connection is set auto-commit, one datasource' failure will not rollback other datasources' execution and therefore the overall consistency is not guaranteed. Do we have to use manual commit when using sharding-jdbc to do update operation? 


## terrymanu (27 Jan 2016)

目前事务只是可用，还没有做跨数据库的事务原子性。我们未来会基于BASE做最终一致性的柔性事务。有什么建议都可以提，多谢


## ivywjhua (01 Feb 2016)

基于BASE做最终一致性的柔性事务? 这个和Notify之类的有什么区别？
可以给个参考链接研究一下么？ @terrymanu 


## terrymanu (01 Feb 2016)

想法还未完全成型。柔性事务可以网上搜下。不过最终我们不一定采用什么方案。等做的时候可以详细讨论


