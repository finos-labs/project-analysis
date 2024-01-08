#希望能提供扩展支持多种数据库的接口

Owner: Tencent

Repo: APIJSON

Labels: Enhancement 增强 

## squallliu (07 Mar 2018)

单一支持mysql是硬伤啊

## TommyLemon (09 Mar 2018)

请问你想要支持哪种呢？目前PostgreSQL发展很猛，有支持的打算

## TommyLemon (09 Mar 2018)

应该不会太麻烦，毕竟只要增加一个针对它的SQLConfig，来把JSON转换为SQL就行
这个是针对MySQL的
https://github.com/TommyLemon/APIJSON/blob/master/APIJSON-Java-Server/APIJSONLibrary/src/main/java/zuo/biao/apijson/server/AbstractSQLConfig.java

## mylike (17 Mar 2018)

推荐使用Nutz的dao或其它将sql封装，实现数据查询，这样天然就支持了各种数据库

## TommyLemon (24 Mar 2018)

@mylike   用Java静态代码封装SQL的库，对于APIJSON来说，灵活性都不够，
[mysql_fdw](https://github.com/EnterpriseDB/mysql_fdw)
或许能用在这方面

## TommyLemon (26 Aug 2018)

Java版Server已支持 PostgreSQL，可通过 "@database": "POSTGRESQL" 指定数据库类型。
https://github.com/TommyLemon/APIJSON/tree/master/APIJSON-Java-Server

C#版(使用SqlSuger)支持 MySQL, Oracle, MS SQL Server, PostgreSQL, SQLite
https://github.com/liaozb/APIJSON.NET

感谢支持，给热心的作者点Star支持下吧^_^

## TommyLemon (15 Sept 2019)

@squallliu @mylike Java版Server已支持 Oracle，提供了 APIJSONBootOracle 工程作为 Demo
https://github.com/APIJSON/APIJSON/tree/master/APIJSON-Java-Server

## TommyLemon (22 Apr 2022)

@squallliu  @mylike 已支持 MySQL 5.7+, PostgreSQL 9.5+, MS SQLServer 2012+, Oracle 12C+, IBM DB2 7.1+, TiDB 2.1+, ClickHouse 21.1+, Hive 3.1.2+, Hadoop 3.1.3+
理论上所有支持 SQL 与 JDBC/ODBC 的软件，都可以用本项目对接 CRUD，待测试:
[Elasticsearch](https://www.elastic.co/cn/what-is/elasticsearch-sql), [OceanBase](https://www.oceanbase.com/docs/oceanbase/V2.2.50/ss-sr-select_daur3l), [Presto](https://prestodb.io/docs/current/admin/function-namespace-managers.html), [Spark](http://spark.apache.org/sql/),[Phoenix](http://phoenix.apache.org/language/index.html#select)(延伸支持 HBase), [Presto/Trino](https://prestodb.io/docs/current/sql/select.html)(延伸支持 Redis, Hive, Kafka, Elasticsearch, Thrift, Cassandra, MySQL, PostgreSQL, Oracle, MongoDB...)

最新版 5.0.0

增强各种功能；腾讯负责人公开称赞；登记万科发起的采筑电商
https://github.com/Tencent/APIJSON/releases/tag/5.0.0

