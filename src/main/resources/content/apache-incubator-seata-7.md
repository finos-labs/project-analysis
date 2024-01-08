#请问能够支持http请求的分布式事务

Owner: apache

Repo: incubator-seata

Labels: 

## shiyuan2he (10 Jan 2019)

第一版支不支持http请求的分布式事务



## slievrly (10 Jan 2019)

支持的，第一版本还未与各微服务框架深度集成，在某些框架中需要用户将fescar事务的上下文传递到provider上然后利用fescar的api做下事务的绑定，provider就加可以加入到fescar的分布式事务中。

