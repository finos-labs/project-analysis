#mysql timeStamp默认值0000-00-00 00:00:00 报错

Owner: shuzheng

Repo: zheng

Labels: 

## blitzpan (27 Jul 2017)

mysql5.7.19导入脚本报错。
发现5.7中timestamp类型取值范围：1970-01-01 00:00:00 到 2037-12-31 23:59:59。所以导致默认值0000-00-00 00:00:00 报错。
修改：
`last_login_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '最后登录时间',

## dong4j (07 Sept 2017)

在执行 sql 之前, 先执行 `set session sql_mode='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';`

