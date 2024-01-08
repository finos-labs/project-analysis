#必须根据where条件找到切分字段？如果一个子条件没有找到，貌似行为不确定，默认不支持查询全部库

Owner: apache

Repo: shardingsphere

Labels: type: enhancement 

## pingww (23 Feb 2016)

// 如果这里没有找到切分字段，也就没有了ConditionContext集
if (!shardingColumns.contains(column.getColumnName())) { 
            return;
}

// 没有了ConditionContext，这里也就为空，后面就不了了之了，
for (SQLExecutionUnit each : sqlRouteResult.getExecutionUnits()) {
            PreparedStatement preparedStatement = generatePrepareStatement(getShardingConnection().getConnection(each.getDataSource()), each.getSql());
            replayMethodsInvovation(preparedStatement);
            setParameters(preparedStatement, parameters);
            result.add(preparedStatement);
        }

如果默认不支持全部分片的查询，建议要抛个异常


## hanahmily (26 Feb 2016)

这里可以增加一个配置。配置没有找到路由规则后的行为。我觉得这个配置需要分级别，应该有全局配置，针对特定逻辑表的配置


## hanahmily (06 Jul 2016)

目前新版本中已经实现如果没有shardingvalue，就返回所有分片这个逻辑了。


