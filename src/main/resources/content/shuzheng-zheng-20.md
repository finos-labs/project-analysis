#分配角色权限的时候发现的小BUG

Owner: shuzheng

Repo: zheng

Labels: 

## liyeHHH (28 Mar 2017)

角色管理，分配角色权限的时候，权限很多的话会报这个错：
Data truncation: Data too long for column 'result' at row 1
我把数据库表upms_log的result字段的类型换成longtext就好了，响应结果是不是可以输出短一点？


## shuzheng (28 Mar 2017)

我先改成mediumtext，这个upms_log是临时的，后期会加个日志系统

