#抽取mysql packet协议包为独立工程，方便重用

Owner: alibaba

Repo: canal

Labels: enhancement 

## agapple (18 Feb 2013)

mysql packet介绍：模拟mysql登录，select/update语句与mysql进行通讯


## agapple (18 Feb 2013)

fix issue 5
    1. 新增了driver project,提供了MysqlConnector,MysqlQueryExector,MysqlUpdateExector
    2. 修改原先的parse的包依赖，重构MysqlConnection，使用新的MysqlConnector


