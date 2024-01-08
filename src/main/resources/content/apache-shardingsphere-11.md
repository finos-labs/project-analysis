#count函数在某些情况下返回不正确

Owner: apache

Repo: shardingsphere

Labels: type: bug 

## yjmyzz (19 Feb 2016)

参考示例代码里的场景，求t_order的count总数：

使用mybatis，分成3个库，每个库里分2张表，每个表里插入2条记录，正确的count值应该是12

``` sql
    <select id="queryCount" resultType="int">
        select count(*) from t_order
    </select>
```

仅返回一个int型整数，返回的数值，仅为某条子查询的count值，没有累加，下面是输出的日志：

```
15:00:28,341 <com.dangdang.ddframe.rdb.sharding.parser.SQLParserFactory> DEBUG [main]: Logic SQL: select count(0) from t_order
15:00:28,391 <com.dangdang.ddframe.rdb.sharding.parser.SQLParserFactory> TRACE [main]: Get com.alibaba.druid.sql.ast.statement.SQLSelectStatement SQL Statement
15:00:28,783 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1 visit node: class com.alibaba.druid.sql.ast.statement.SQLSelectStatement
15:00:28,822 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1 visit argument: SELECT COUNT(0)
FROM t_order
15:00:28,822 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2 visit node: class com.alibaba.druid.sql.ast.statement.SQLSelect
15:00:28,823 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2 visit argument: com.alibaba.druid.sql.ast.statement.SQLSelect@1d60737e
15:00:28,823 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3 visit node: class com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock
15:00:28,823 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3 visit argument: com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock@c7242101
15:00:28,856 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4 visit node: class com.alibaba.druid.sql.ast.statement.SQLSelectItem
15:00:28,856 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4 visit argument: com.alibaba.druid.sql.ast.expr.SQLAggregateExpr@640d1fb2
15:00:28,856 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5 visit node: class com.alibaba.druid.sql.ast.expr.SQLAggregateExpr
15:00:28,856 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5 visit argument: com.alibaba.druid.sql.ast.expr.SQLAggregateExpr@640d1fb2
15:00:28,858 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5   6 visit node: class com.alibaba.druid.sql.ast.expr.SQLIntegerExpr
15:00:28,859 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5   6 visit argument: 0
15:00:28,859 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5   6 endVisit node: class com.alibaba.druid.sql.ast.expr.SQLIntegerExpr
15:00:28,859 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5   6 endVisit result: SQLParsedResult(routeContext=RouteContext(tables=[Table(name=t_order, alias=Optional.absent())], sqlBuilder=null), conditionContexts=[], mergeContext=MergeContext(orderByColumns=[], groupByColumns=[], aggregationColumns=[AggregationColumn(expression=COUNT(0), aggregationType=COUNT, alias=Optional.absent(), option=Optional.absent(), derivedColumns=[], index=1)], limit=null))
15:00:28,860 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5   6 endVisit condition: ConditionContext(conditions={})
15:00:28,860 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5   6 endVisit SQL: SELECT COUNT(0
15:00:28,879 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5 endVisit node: class com.alibaba.druid.sql.ast.expr.SQLAggregateExpr
15:00:28,879 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5 endVisit result: SQLParsedResult(routeContext=RouteContext(tables=[Table(name=t_order, alias=Optional.absent())], sqlBuilder=null), conditionContexts=[], mergeContext=MergeContext(orderByColumns=[], groupByColumns=[], aggregationColumns=[AggregationColumn(expression=COUNT(0), aggregationType=COUNT, alias=Optional.absent(), option=Optional.absent(), derivedColumns=[], index=1)], limit=null))
15:00:28,879 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5 endVisit condition: ConditionContext(conditions={})
15:00:28,879 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5 endVisit SQL: SELECT COUNT(0)
15:00:28,879 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4 endVisit node: class com.alibaba.druid.sql.ast.statement.SQLSelectItem
15:00:28,879 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4 endVisit result: SQLParsedResult(routeContext=RouteContext(tables=[Table(name=t_order, alias=Optional.absent())], sqlBuilder=null), conditionContexts=[], mergeContext=MergeContext(orderByColumns=[], groupByColumns=[], aggregationColumns=[AggregationColumn(expression=COUNT(0), aggregationType=COUNT, alias=Optional.absent(), option=Optional.absent(), derivedColumns=[], index=1)], limit=null))
15:00:28,879 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4 endVisit condition: ConditionContext(conditions={})
15:00:28,879 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4 endVisit SQL: SELECT COUNT(0)
15:00:28,882 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3 endVisit node: class com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock
15:00:28,882 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3 endVisit result: SQLParsedResult(routeContext=RouteContext(tables=[Table(name=t_order, alias=Optional.absent())], sqlBuilder=null), conditionContexts=[], mergeContext=MergeContext(orderByColumns=[], groupByColumns=[], aggregationColumns=[AggregationColumn(expression=COUNT(0), aggregationType=COUNT, alias=Optional.absent(), option=Optional.absent(), derivedColumns=[], index=1)], limit=null))
15:00:28,882 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3 endVisit condition: ConditionContext(conditions={})
15:00:28,885 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3 endVisit SQL: SELECT COUNT(0) FROM [Token(t_order)]
15:00:28,885 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2 endVisit node: class com.alibaba.druid.sql.ast.statement.SQLSelect
15:00:28,885 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2 endVisit result: SQLParsedResult(routeContext=RouteContext(tables=[Table(name=t_order, alias=Optional.absent())], sqlBuilder=null), conditionContexts=[], mergeContext=MergeContext(orderByColumns=[], groupByColumns=[], aggregationColumns=[AggregationColumn(expression=COUNT(0), aggregationType=COUNT, alias=Optional.absent(), option=Optional.absent(), derivedColumns=[], index=1)], limit=null))
15:00:28,885 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2 endVisit condition: ConditionContext(conditions={})
15:00:28,885 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2 endVisit SQL: SELECT COUNT(0) FROM [Token(t_order)]
15:00:28,886 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1 endVisit node: class com.alibaba.druid.sql.ast.statement.SQLSelectStatement
15:00:28,886 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1 endVisit result: SQLParsedResult(routeContext=RouteContext(tables=[Table(name=t_order, alias=Optional.absent())], sqlBuilder=null), conditionContexts=[], mergeContext=MergeContext(orderByColumns=[], groupByColumns=[], aggregationColumns=[AggregationColumn(expression=COUNT(0), aggregationType=COUNT, alias=Optional.absent(), option=Optional.absent(), derivedColumns=[], index=1)], limit=null))
15:00:28,886 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1 endVisit condition: ConditionContext(conditions={})
15:00:28,886 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1 endVisit SQL: SELECT COUNT(0) FROM [Token(t_order)]
15:00:28,886 <com.dangdang.ddframe.rdb.sharding.parser.SQLParseEngine> DEBUG [main]: Parsed SQL result: SQLParsedResult(routeContext=RouteContext(tables=[Table(name=t_order, alias=Optional.absent())], sqlBuilder=null), conditionContexts=[ConditionContext(conditions={})], mergeContext=MergeContext(orderByColumns=[], groupByColumns=[], aggregationColumns=[AggregationColumn(expression=COUNT(0), aggregationType=COUNT, alias=Optional.absent(), option=Optional.absent(), derivedColumns=[], index=1)], limit=null))
15:00:28,886 <com.dangdang.ddframe.rdb.sharding.parser.SQLParseEngine> DEBUG [main]: Parsed SQL: SELECT COUNT(0) FROM [Token(t_order)]
15:00:28,904 <com.dangdang.ddframe.rdb.sharding.router.single.SingleTableRouter> TRACE [main]: Before database sharding t_order routes db names: [ds_0, ds_1, ds_2] sharding columns: [user_id] sharding values: []
15:00:28,904 <com.dangdang.ddframe.rdb.sharding.router.single.SingleTableRouter> TRACE [main]: After database sharding t_order result: [ds_2, ds_1, ds_0]
15:00:28,904 <com.dangdang.ddframe.rdb.sharding.router.single.SingleTableRouter> TRACE [main]: Before table sharding t_order routes db names: [DataNode(dataSourceName=ds_0, tableName=t_order_0), DataNode(dataSourceName=ds_1, tableName=t_order_0), DataNode(dataSourceName=ds_2, tableName=t_order_0), DataNode(dataSourceName=ds_0, tableName=t_order_1), DataNode(dataSourceName=ds_1, tableName=t_order_1), DataNode(dataSourceName=ds_2, tableName=t_order_1)] sharding columns: [order_id] sharding values: []
15:00:28,904 <com.dangdang.ddframe.rdb.sharding.router.single.SingleTableRouter> TRACE [main]: After table sharding t_order result: [t_order_0, t_order_1]
15:00:28,907 <com.dangdang.ddframe.rdb.sharding.router.SQLExecutionUnit> DEBUG [main]: route sql to db: [ds_0] sql: [SELECT COUNT(0) FROM t_order_0]
15:00:28,907 <com.dangdang.ddframe.rdb.sharding.router.SQLExecutionUnit> DEBUG [main]: route sql to db: [ds_0] sql: [SELECT COUNT(0) FROM t_order_1]
15:00:28,907 <com.dangdang.ddframe.rdb.sharding.router.SQLExecutionUnit> DEBUG [main]: route sql to db: [ds_1] sql: [SELECT COUNT(0) FROM t_order_0]
15:00:28,907 <com.dangdang.ddframe.rdb.sharding.router.SQLExecutionUnit> DEBUG [main]: route sql to db: [ds_1] sql: [SELECT COUNT(0) FROM t_order_1]
15:00:28,907 <com.dangdang.ddframe.rdb.sharding.router.SQLExecutionUnit> DEBUG [main]: route sql to db: [ds_2] sql: [SELECT COUNT(0) FROM t_order_0]
15:00:28,907 <com.dangdang.ddframe.rdb.sharding.router.SQLExecutionUnit> DEBUG [main]: route sql to db: [ds_2] sql: [SELECT COUNT(0) FROM t_order_1]
15:00:29,084 <com.dangdang.ddframe.rdb.sharding.executor.ExecutorEngine> TRACE [main]: Concurrent execute result success [true, true, true, true, true, true]
15:00:29,088 <com.dangdang.ddframe.rdb.sharding.merger.ResultSetFactory> TRACE [main]: Get 'Aggregate' result set
Feb 22, 2016 3:00:29 PM org.springframework.context.support.ClassPathXmlApplicationContext doClose
INFO: Closing org.springframework.context.support.ClassPathXmlApplicationContext@3d82c5f3: startup date [Mon Feb 22 15:00:24 CST 2016]; root of context hierarchy
count => 2 ***这是我输出的结果
```

但是，如果改成

``` sql
    <select id="queryCount" resultType="int">
        select count(*) order_count from t_order
    </select>
```

写成这样，即：加一个字段别名后，返回结果是正确的，会把各分库、分表子查询的count值累加返回，输出日志如下：

```
15:03:49,055 <com.dangdang.ddframe.rdb.sharding.parser.SQLParserFactory> DEBUG [main]: Logic SQL: select count(0) order_count from t_order
15:03:49,105 <com.dangdang.ddframe.rdb.sharding.parser.SQLParserFactory> TRACE [main]: Get com.alibaba.druid.sql.ast.statement.SQLSelectStatement SQL Statement
15:03:49,505 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1 visit node: class com.alibaba.druid.sql.ast.statement.SQLSelectStatement
15:03:49,540 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1 visit argument: SELECT COUNT(0) AS order_count
FROM t_order
15:03:49,540 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2 visit node: class com.alibaba.druid.sql.ast.statement.SQLSelect
15:03:49,540 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2 visit argument: com.alibaba.druid.sql.ast.statement.SQLSelect@1d60737e
15:03:49,540 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3 visit node: class com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock
15:03:49,540 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3 visit argument: com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock@c7242101
15:03:49,572 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4 visit node: class com.alibaba.druid.sql.ast.statement.SQLSelectItem
15:03:49,572 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4 visit argument: com.alibaba.druid.sql.ast.expr.SQLAggregateExpr@640d1fb2 AS order_count
15:03:49,572 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5 visit node: class com.alibaba.druid.sql.ast.expr.SQLAggregateExpr
15:03:49,572 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5 visit argument: com.alibaba.druid.sql.ast.expr.SQLAggregateExpr@640d1fb2
15:03:49,574 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5   6 visit node: class com.alibaba.druid.sql.ast.expr.SQLIntegerExpr
15:03:49,574 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5   6 visit argument: 0
15:03:49,574 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5   6 endVisit node: class com.alibaba.druid.sql.ast.expr.SQLIntegerExpr
15:03:49,574 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5   6 endVisit result: SQLParsedResult(routeContext=RouteContext(tables=[Table(name=t_order, alias=Optional.absent())], sqlBuilder=null), conditionContexts=[], mergeContext=MergeContext(orderByColumns=[], groupByColumns=[], aggregationColumns=[AggregationColumn(expression=COUNT(0), aggregationType=COUNT, alias=Optional.of(order_count), option=Optional.absent(), derivedColumns=[], index=1)], limit=null))
15:03:49,575 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5   6 endVisit condition: ConditionContext(conditions={})
15:03:49,575 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5   6 endVisit SQL: SELECT COUNT(0
15:03:49,604 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5 endVisit node: class com.alibaba.druid.sql.ast.expr.SQLAggregateExpr
15:03:49,604 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5 endVisit result: SQLParsedResult(routeContext=RouteContext(tables=[Table(name=t_order, alias=Optional.absent())], sqlBuilder=null), conditionContexts=[], mergeContext=MergeContext(orderByColumns=[], groupByColumns=[], aggregationColumns=[AggregationColumn(expression=COUNT(0), aggregationType=COUNT, alias=Optional.of(order_count), option=Optional.absent(), derivedColumns=[], index=1)], limit=null))
15:03:49,604 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5 endVisit condition: ConditionContext(conditions={})
15:03:49,604 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4   5 endVisit SQL: SELECT COUNT(0)
15:03:49,605 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4 endVisit node: class com.alibaba.druid.sql.ast.statement.SQLSelectItem
15:03:49,605 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4 endVisit result: SQLParsedResult(routeContext=RouteContext(tables=[Table(name=t_order, alias=Optional.absent())], sqlBuilder=null), conditionContexts=[], mergeContext=MergeContext(orderByColumns=[], groupByColumns=[], aggregationColumns=[AggregationColumn(expression=COUNT(0), aggregationType=COUNT, alias=Optional.of(order_count), option=Optional.absent(), derivedColumns=[], index=1)], limit=null))
15:03:49,605 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4 endVisit condition: ConditionContext(conditions={})
15:03:49,605 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3   4 endVisit SQL: SELECT COUNT(0) AS order_count
15:03:49,606 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3 endVisit node: class com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock
15:03:49,606 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3 endVisit result: SQLParsedResult(routeContext=RouteContext(tables=[Table(name=t_order, alias=Optional.absent())], sqlBuilder=null), conditionContexts=[], mergeContext=MergeContext(orderByColumns=[], groupByColumns=[], aggregationColumns=[AggregationColumn(expression=COUNT(0), aggregationType=COUNT, alias=Optional.of(order_count), option=Optional.absent(), derivedColumns=[], index=1)], limit=null))
15:03:49,606 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3 endVisit condition: ConditionContext(conditions={})
15:03:49,609 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2   3 endVisit SQL: SELECT COUNT(0) AS order_count FROM [Token(t_order)]
15:03:49,609 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2 endVisit node: class com.alibaba.druid.sql.ast.statement.SQLSelect
15:03:49,609 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2 endVisit result: SQLParsedResult(routeContext=RouteContext(tables=[Table(name=t_order, alias=Optional.absent())], sqlBuilder=null), conditionContexts=[], mergeContext=MergeContext(orderByColumns=[], groupByColumns=[], aggregationColumns=[AggregationColumn(expression=COUNT(0), aggregationType=COUNT, alias=Optional.of(order_count), option=Optional.absent(), derivedColumns=[], index=1)], limit=null))
15:03:49,609 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2 endVisit condition: ConditionContext(conditions={})
15:03:49,610 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1   2 endVisit SQL: SELECT COUNT(0) AS order_count FROM [Token(t_order)]
15:03:49,610 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1 endVisit node: class com.alibaba.druid.sql.ast.statement.SQLSelectStatement
15:03:49,610 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1 endVisit result: SQLParsedResult(routeContext=RouteContext(tables=[Table(name=t_order, alias=Optional.absent())], sqlBuilder=null), conditionContexts=[], mergeContext=MergeContext(orderByColumns=[], groupByColumns=[], aggregationColumns=[AggregationColumn(expression=COUNT(0), aggregationType=COUNT, alias=Optional.of(order_count), option=Optional.absent(), derivedColumns=[], index=1)], limit=null))
15:03:49,610 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1 endVisit condition: ConditionContext(conditions={})
15:03:49,610 <com.dangdang.ddframe.rdb.sharding.parser.visitor.VisitorLogProxy> TRACE [main]:   1 endVisit SQL: SELECT COUNT(0) AS order_count FROM [Token(t_order)]
15:03:49,611 <com.dangdang.ddframe.rdb.sharding.parser.SQLParseEngine> DEBUG [main]: Parsed SQL result: SQLParsedResult(routeContext=RouteContext(tables=[Table(name=t_order, alias=Optional.absent())], sqlBuilder=null), conditionContexts=[ConditionContext(conditions={})], mergeContext=MergeContext(orderByColumns=[], groupByColumns=[], aggregationColumns=[AggregationColumn(expression=COUNT(0), aggregationType=COUNT, alias=Optional.of(order_count), option=Optional.absent(), derivedColumns=[], index=1)], limit=null))
15:03:49,611 <com.dangdang.ddframe.rdb.sharding.parser.SQLParseEngine> DEBUG [main]: Parsed SQL: SELECT COUNT(0) AS order_count FROM [Token(t_order)]
15:03:49,637 <com.dangdang.ddframe.rdb.sharding.router.single.SingleTableRouter> TRACE [main]: Before database sharding t_order routes db names: [ds_0, ds_1, ds_2] sharding columns: [user_id] sharding values: []
15:03:49,638 <com.dangdang.ddframe.rdb.sharding.router.single.SingleTableRouter> TRACE [main]: After database sharding t_order result: [ds_2, ds_1, ds_0]
15:03:49,638 <com.dangdang.ddframe.rdb.sharding.router.single.SingleTableRouter> TRACE [main]: Before table sharding t_order routes db names: [DataNode(dataSourceName=ds_0, tableName=t_order_0), DataNode(dataSourceName=ds_1, tableName=t_order_0), DataNode(dataSourceName=ds_2, tableName=t_order_0), DataNode(dataSourceName=ds_0, tableName=t_order_1), DataNode(dataSourceName=ds_1, tableName=t_order_1), DataNode(dataSourceName=ds_2, tableName=t_order_1)] sharding columns: [order_id] sharding values: []
15:03:49,638 <com.dangdang.ddframe.rdb.sharding.router.single.SingleTableRouter> TRACE [main]: After table sharding t_order result: [t_order_0, t_order_1]
15:03:49,641 <com.dangdang.ddframe.rdb.sharding.router.SQLExecutionUnit> DEBUG [main]: route sql to db: [ds_0] sql: [SELECT COUNT(0) AS order_count FROM t_order_0]
15:03:49,641 <com.dangdang.ddframe.rdb.sharding.router.SQLExecutionUnit> DEBUG [main]: route sql to db: [ds_0] sql: [SELECT COUNT(0) AS order_count FROM t_order_1]
15:03:49,641 <com.dangdang.ddframe.rdb.sharding.router.SQLExecutionUnit> DEBUG [main]: route sql to db: [ds_1] sql: [SELECT COUNT(0) AS order_count FROM t_order_0]
15:03:49,641 <com.dangdang.ddframe.rdb.sharding.router.SQLExecutionUnit> DEBUG [main]: route sql to db: [ds_1] sql: [SELECT COUNT(0) AS order_count FROM t_order_1]
15:03:49,642 <com.dangdang.ddframe.rdb.sharding.router.SQLExecutionUnit> DEBUG [main]: route sql to db: [ds_2] sql: [SELECT COUNT(0) AS order_count FROM t_order_0]
15:03:49,642 <com.dangdang.ddframe.rdb.sharding.router.SQLExecutionUnit> DEBUG [main]: route sql to db: [ds_2] sql: [SELECT COUNT(0) AS order_count FROM t_order_1]
15:03:49,774 <com.dangdang.ddframe.rdb.sharding.executor.ExecutorEngine> TRACE [main]: Concurrent execute result success [true, true, true, true, true, true]
15:03:49,779 <com.dangdang.ddframe.rdb.sharding.merger.ResultSetFactory> TRACE [main]: Get 'Aggregate' result set
15:03:49,826 <com.dangdang.ddframe.rdb.sharding.merger.aggregation.AccumulationAggregationUnit> TRACE [main]: Accumulation result: 2
15:03:49,826 <com.dangdang.ddframe.rdb.sharding.merger.aggregation.AccumulationAggregationUnit> TRACE [main]: Accumulation result: 4
15:03:49,827 <com.dangdang.ddframe.rdb.sharding.merger.aggregation.AccumulationAggregationUnit> TRACE [main]: Accumulation result: 6
15:03:49,827 <com.dangdang.ddframe.rdb.sharding.merger.aggregation.AccumulationAggregationUnit> TRACE [main]: Accumulation result: 8
15:03:49,827 <com.dangdang.ddframe.rdb.sharding.merger.aggregation.AccumulationAggregationUnit> TRACE [main]: Accumulation result: 10
15:03:49,827 <com.dangdang.ddframe.rdb.sharding.merger.aggregation.AccumulationAggregationUnit> TRACE [main]: Accumulation result: 12
Feb 22, 2016 3:03:49 PM org.springframework.context.support.ClassPathXmlApplicationContext doClose
INFO: Closing org.springframework.context.support.ClassPathXmlApplicationContext@3d82c5f3: startup date [Mon Feb 22 15:03:45 CST 2016]; root of context hierarchy
count => 12 *** 这次就对了
```

各位大大，有空看下，谢谢！


## GXY1122 (08 Feb 2023)

请问怎么解决的，感谢~

