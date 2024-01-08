#老是报这个aba.druid.pool.DruidDataSource - mysql should not use 'PoolPreparedStatements

Owner: alibaba

Repo: druid

Labels: 

## yangl (04 Sept 2012)

如题，用的最新的0.2.6。求解


## wenshao (04 Sept 2012)

mysql中使用poolPreparedStatements没有意义，所以LOG.ERROR警告你。


## yangl (04 Sept 2012)

直接从参考配置 http://code.alibabatech.com/wiki/pages/viewpage.action?pageId=7669006    拷过去的，哎。mysql忘记把这个改成poolPreparedStatements改成false了


