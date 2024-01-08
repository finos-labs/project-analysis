#sharding insert简单测试异常提示?

Owner: apache

Repo: shardingsphere

Labels: status: invalid 

## bwzhang2011 (10 Mar 2016)

Hi, 做sharding-jdbc单元测试的时候遇到了提示

com.dangdang.ddframe.rdb.sharding.exception.SQLParserException: Sharding columns DO NOT exist in insert column names
    at com.dangdang.ddframe.rdb.sharding.parser.visitor.basic.mysql.MySQLInsertVisitor.visit(MySQLInsertVisitor.java:45)

我这边的单元测试(构建shardingRule)时没有配置DatabaseShardingStrategy或TableShardingStrategy
——经测试select、update以及delete均没有问题

代码为：
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;

import com.dangdang.ddframe.rdb.sharding.api.DatabaseType;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.router.SQLExecutionUnit;
import com.dangdang.ddframe.rdb.sharding.router.SQLRouteEngine;
import com.dangdang.ddframe.rdb.sharding.router.SQLRouteResult;

public class TestShardingRule {

```
@Test
public void testShardingRule1() {
    ShardingRule sr1 = createShardingRule1();

    // 构建routerEngine
    SQLRouteEngine routerEngine = new SQLRouteEngine(sr1, DatabaseType.MySQL);
```

//      String testQuerySQL = "select o.column1, o.column2 from t_order o where o.cloumn3 = 'aa'";
//
//      SQLRouteResult routerResult1 = routerEngine.route(testQuerySQL, Collections.emptyList());
//
//      List<SQLExecutionUnit> executionUnitList1 = routerResult1.getExecutionUnits();
//
//      for (SQLExecutionUnit executionUnit : executionUnitList1) {
//          System.out.println("query sql unit:" + executionUnit.getSql());
//      }

```
    String testInsertSQL = "insert into t_order(column1, column2, column3) values('aa', 'bb', 'cc')";

    SQLRouteResult routerResult2 = routerEngine.route(testInsertSQL, Collections.emptyList());

    List<SQLExecutionUnit> executionUnitList2 = routerResult2.getExecutionUnits();

    for (SQLExecutionUnit executionUnit : executionUnitList2) {
        System.out.println("insert unit sql:" + executionUnit.getSql());
    }
```

//      String testUpdateSQL = "update t_order t set t.column1 = 'aa' where t.column2 = 'bb' and t.column3 = 'cc'";
//
//      SQLRouteResult routerResult3 = routerEngine.route(testUpdateSQL, Collections.emptyList());
//
//      List<SQLExecutionUnit> executionUnitList3 = routerResult3.getExecutionUnits();
//
//      for (SQLExecutionUnit executionUnit : executionUnitList3) {
//          System.out.println("update unit sql:" + executionUnit.getSql());
//      }

//      String testDeleteSQL = "delete from t_order t where t.column2 = 'bb' and t.column3 = 'cc'";
//
//      SQLRouteResult routerResult4 = routerEngine.route(testDeleteSQL, Collections.emptyList());
//
//      List<SQLExecutionUnit> executionUnitList4 = routerResult4.getExecutionUnits();
//
//      for (SQLExecutionUnit executionUnit : executionUnitList4) {
//          System.out.println("delete unit sql:" + executionUnit.getSql());
//      }
    }

```
private ShardingRule createShardingRule1() {
    return new ShardingRule(createDataSourceRule(), Arrays.asList(createTableRule()));
}

private DataSourceRule createDataSourceRule() {
    Map<String, DataSource> result = new HashMap<>(2);
    result.put("ds0", null);
    result.put("ds1", null);
    return new DataSourceRule(result);
}

private TableRule createTableRule() {
    return new TableRule("t_order", Arrays.asList("t_order_0", "t_order_1", "t_order_2"),
            createDataSourceRule());
}
```

}

我想确认的是，是否insert的操作必须要指明Sharding columns麽


## bwzhang2011 (10 Mar 2016)

补充：我升级了DRUID的依赖到1.0.17，参考https://github.com/dangdangdotcom/sharding-jdbc/issues/24 的改动。尚不清楚是否是因为druid依赖的改动造成的，若不是那么insert部分是否有强制需指明sharding column的要求


## bwzhang2011 (11 Mar 2016)

@terrymanu, 我尝试了包含有transaction的分支（1.0.1-SNAPSHOT），然后编译这个版本的sharding-jdbc-core，发现测试是通过的。但切换到1.0.1的正式版则抛出上面的错误了。


## hanahmily (14 Mar 2016)

请更新到最新发布版1.1.0，目前master分支不会报该错误了


## bwzhang2011 (14 Mar 2016)

@hanahmily, 感谢更新回复（以及不断改进），后续将更新到1.1.0版本


