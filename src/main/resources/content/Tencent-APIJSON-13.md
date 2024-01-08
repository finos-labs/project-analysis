#有没有兴趣

Owner: Tencent

Repo: APIJSON

Labels: 

## wanghaisheng (17 Nov 2017)

https://github.com/Shyam-Chen/Backend-Starter-Kit
做一个类似的基于APIJSON的Backend-Starter-Kit
```
## Directory Structure

```
.
├── flow-typed
├── src
│   ├── document
│   │   └── index.js ...
│   ├── graphql
│   │   └── index.js ...
│   ├── apijson
│   │   └── index.js ...
│   ├── relational
│   │   └── index.js ...
│   ├── rest
│   │   └── index.js ...
│   ├── api.js
│   ├── config.js
│   └── pm2.js
├── test
│   ├── graphql
│   │   └── xxx.spec.js ...
│   └── rest
│       └── xxx.spec.js ...
│   ├── apijson
│       └── xxx.spec.js ...
├── .babelrc
├── .editorconfig
├── .eslintrc
├── .gitattributes
├── .gitignore
├── Dockerfile.dev
├── Dockerfile.prod
├── LICENSE
├── Procfile
├── README.md
├── circle.yml
├── docker-compose.yml
├── package.json
└── yarn.lock
```

```

## TommyLemon (17 Nov 2017)

@wanghaisheng 可以的，请问有语言限制吗？

## wanghaisheng (18 Nov 2017)

@TommyLemon 最好大众化一些呗  什么java  c# nodejs

## TommyLemon (18 Nov 2017)

@wanghaisheng OK，java没问题的。有别的要求吗？

## wanghaisheng (18 Nov 2017)

@TommyLemon mysql sqlserver  oracle

## TommyLemon (18 Nov 2017)

@wanghaisheng 已有对接MySQL的Java后端实现

## TommyLemon (18 Nov 2017)

@wanghaisheng 对了，我需要提供什么？

## Liuqin (18 Nov 2017)

赶紧出个c#版本，我可以参与维护

## TommyLemon (19 Nov 2017)

@Liuqin 有人在做了哈

## wanghaisheng (19 Nov 2017)

@TommyLemon  我现在有这么一个场景 
我们有客户上了一些系统  但是这些系统的供应商配合支持力度有限 数据库可能是oracle 或者sqlserver的  如果想基于原来的数据库做一些新的应用开发 

## TommyLemon (19 Nov 2017)

@wanghaisheng 还没说完？

## wanghaisheng (20 Nov 2017)

@TommyLemon 大概就是这么个意思 说完了

## TommyLemon (20 Nov 2017)

@wanghaisheng APIJSON Server目前对接的数据库是MySQL，如果要切换为其它数据库，需要换一下数据库驱动jar包，然后在SQLConfig类里改一下生成SQL语句的代码

## ysjiang4869 (05 Feb 2018)

建议：sql builder可以是否可以考虑第三方库？目前在用jooq感觉就不错。通过sql builder库完成对不同的数据库方言的支持，如果能提供一个maven包更合适了。

## TommyLemon (09 Mar 2018)

@ysjiang4869 你好，感谢你的建议，我打算新增SQLConfig来支持PostgreSQL等其它数据库。
仔细看了JOOQ，发现它更像MyBatis，Hibernate这种ORM库，都是后端写死的静态代码，对于APIJSON的自动化解析来说，灵活性还不够，不能实现后端不写代码也能提供各种查询“接口”（其实就一个/get，想怎么查都行嘿嘿）。

## TommyLemon (04 Jul 2018)

@Liuqin 
APIJSON-C# Server：
https://github.com/liaozb/APIJSON.NET

## TommyLemon (16 Feb 2020)

@ysjiang4869 感谢建议，APIJSON ORM 已支持 Maven, Gradle 等远程依赖方式，具体见
https://github.com/APIJSON/apijson-orm

## TommyLemon (22 Apr 2022)

> @TommyLemon 我现在有这么一个场景 我们有客户上了一些系统 但是这些系统的供应商配合支持力度有限 数据库可能是oracle 或者sqlserver的 如果想基于原来的数据库做一些新的应用开发

@ysjiang4869  APIJSON 已支持 MySQL 5.7+, PostgreSQL 9.5+, MS SQLServer 2012+, Oracle 12C+, IBM DB2 7.1+, TiDB 2.1+, ClickHouse 21.1+, Hive 3.1.2+, Hadoop 3.1.3+
理论上所有支持 SQL 与 JDBC/ODBC 的软件，都可以用本项目对接 CRUD，待测试:
[Elasticsearch](https://www.elastic.co/cn/what-is/elasticsearch-sql), [OceanBase](https://www.oceanbase.com/docs/oceanbase/V2.2.50/ss-sr-select_daur3l), [Presto](https://prestodb.io/docs/current/admin/function-namespace-managers.html), [Spark](http://spark.apache.org/sql/),[Phoenix](http://phoenix.apache.org/language/index.html#select)(延伸支持 HBase), [Presto/Trino](https://prestodb.io/docs/current/sql/select.html)(延伸支持 Redis, Hive, Kafka, Elasticsearch, Thrift, Cassandra, MySQL, PostgreSQL, Oracle, MongoDB...)

目前最新版 5.0.0：

增强各种功能；腾讯负责人公开称赞；登记万科发起的采筑电商
https://github.com/Tencent/APIJSON/releases/tag/5.0.0

