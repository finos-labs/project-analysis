#mysql 5.5.53导入sql报错

Owner: apolloconfig

Repo: apollo

Labels: 

## galenzhao (21 Oct 2016)

ERROR 1293 (HY000): Incorrect table definition; there can be only one TIMESTAMP column with CURRENT_TIMESTAMP in DEFAULT or ON UPDATE clause
ERROR 1146 (42S02): Table 'ApolloPortalDB.serverconfig' doesn't exist
ERROR 1146 (42S02): Table 'ApolloPortalDB.app' doesn't exist
ERROR 1146 (42S02): Table 'ApolloPortalDB.appnamespace' doesn't exist
ERROR 1146 (42S02): Table 'ApolloPortalDB.permission' doesn't exist
ERROR 1146 (42S02): Table 'ApolloPortalDB.role' doesn't exist
ERROR 1146 (42S02): Table 'ApolloPortalDB.rolepermission' doesn't exist
ERROR 1146 (42S02): Table 'ApolloPortalDB.userrole' doesn't exist


## galenzhao (21 Oct 2016)

ERROR 1293 (HY000): Incorrect table definition; there can be only one TIMESTAMP column with CURRENT_TIMESTAMP in DEFAULT or ON UPDATE clause
Query OK, 0 rows affected (0.00 sec)

ERROR 1293 (HY000): Incorrect table definition; there can be only one TIMESTAMP column with CURRENT_TIMESTAMP in DEFAULT or ON UPDATE clause
Query OK, 0 rows affected (0.00 sec)

ERROR 1293 (HY000): Incorrect table definition; there can be only one TIMESTAMP column with CURRENT_TIMESTAMP in DEFAULT or ON UPDATE clause
Query OK, 0 rows affected (0.00 sec)

ERROR 1293 (HY000): Incorrect table definition; there can be only one TIMESTAMP column with CURRENT_TIMESTAMP in DEFAULT or ON UPDATE clause
Query OK, 0 rows affected (0.00 sec)

ERROR 1293 (HY000): Incorrect table definition; there can be only one TIMESTAMP column with CURRENT_TIMESTAMP in DEFAULT or ON UPDATE clause
Query OK, 0 rows affected (0.00 sec)


## nobodyiam (21 Oct 2016)

@galenzhao sorry，之前文档说明有误，因为我们使用了多个on update clause，所以mysql的版本需求是**5.6.5+**，详见[mysql文档说明](http://dev.mysql.com/doc/refman/5.6/en/timestamp-initialization.html)。

Wiki页面已经更新。


## elisayys (26 Aug 2020)

我用的 mariadb ， select `Id`, `AppId`, `Name` from ApolloPortalDB.App;
ERROR 1146 (42S02): Table 'ApolloPortalDB.App' doesn't exist
MariaDB [ApolloPortalDB]> select `NamespaceId`, `Key`, `Value`, `Comment` from ApolloConfigDB.Item;
ERROR 1146 (42S02): Table 'ApolloConfigDB.Item' doesn't exist
也没有啊！ 难道maridb也不行吗？

## nobodyiam (30 Aug 2020)

@elisayys 看看表是不是正确创建了

