#druid无法读取数据源

Owner: shuzheng

Repo: zheng

Labels: 

## switch513 (20 Apr 2017)

http://upms.zhangshuzheng.cn:1111/druid/datasource.html
配置了druid，无法读出数据源，只提示这个“(*) property for user to setup”

## shuzheng (28 Apr 2017)

因为数据源配在了service，web应用不能直接访问数据源，以后会单独出一个模块来统一监控

## hahaduo (28 Apr 2017)

已经解决，将druid的版本改为1.0.15就没有问题了。

