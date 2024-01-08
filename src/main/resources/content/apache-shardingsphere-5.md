#为什么OR条件下不进行聚合函数处理

Owner: apache

Repo: shardingsphere

Labels: status: invalid status: won't fix 

## pingww (03 Feb 2016)

if (sqlVisitor.getParseContext().isHasOrCondition()) {
            result = new OrParser(sqlStatement, visitor).parse();   //  这里直接丢掉了聚合列的merge处理?
 } else {
            sqlVisitor.getParseContext().mergeCurrentConditionContext();
            result = sqlVisitor.getParseContext().getParsedResult();
 }


## terrymanu (03 Feb 2016)

or的处理是基于不包含or处理器之上的。mergeCurrentConditionContext这个方法不是为了处理merge聚合列，是为了生产和or统一的返回格式


## pingww (17 Feb 2016)

@terrymanu ，我测了一下，有or条件的情况，没有做groupby、avg等分组聚合处理；or的处理是基于不包含or处理器之上的？这个哪里体现的，没有看到啊


## hanahmily (25 Feb 2016)

@pingww 抱歉一开始没有好好理解你的问题。其实sharding-jdbc对or的结果集归并与其他归并方法相同。
`com.dangdang.ddframe.rdb.sharding.jdbc.ShardingPreparedStatement`

``` java
    @Override
    public ResultSet executeQuery() throws SQLException {
        hasExecuted = true;
        setCurrentResultSet(ResultSetFactory.getResultSet(new PreparedStatementExecutor(getContext().getExecutorEngine(), getRoutedPreparedStatements()).executeQuery(), getMergeContext()));
        return getCurrentResultSet();
    }

...

private List<PreparedStatement> routeSQL(final List<Object> parameters) throws SQLException {
        List<PreparedStatement> result = new ArrayList<>();
        SQLRouteResult sqlRouteResult = getContext().getSqlRouteEngine().route(sql, parameters);
        MergeContext mergeContext = sqlRouteResult.getMergeContext();
        mergeContext.setExecutorEngine(getContext().getExecutorEngine());
        setMergeContext(mergeContext);
        for (SQLExecutionUnit each : sqlRouteResult.getExecutionUnits()) {
            PreparedStatement preparedStatement = generatePrepareStatement(getShardingConnection().getConnection(each.getDataSource()), each.getSql());
            replayMethodsInvovation(preparedStatement);
            setParameters(preparedStatement, parameters);
            result.add(preparedStatement);
        }
        return result;
    }
```

也就是不管当前sql是什么，流程都是 SqlRouterEngine-》SQLExecutionUnit -》 ResultSetFactory 。

如果你的示例不能执行归并，那么请把日志级别调整为trace然后发出来，我再进行跟踪。
谢谢


## pingww (17 Mar 2016)

MergeContext 都是在MySQLSelectVisitor里面生成的，OR解析没有使用这个visitor，所以归并上下文为空。 @hanahmily 


