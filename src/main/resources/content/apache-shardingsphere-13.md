#Insert 语句 没有写列名 进行了全路由

Owner: apache

Repo: shardingsphere

Labels: type: enhancement 

## hanahmily (23 Feb 2016)

1. 执行 insert into table_name values (11,11,....)，sharding-jdbc会在所有分片上执行。
2. 执行 insert 语句的列名中没有分片字段

以上两种应改完直接抛出异常


