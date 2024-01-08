#SQL语句解析

Owner: alibaba

Repo: druid

Labels: 

## fengkuok (21 Aug 2012)

目前druid的sql解析部分可否支持解析出select语句的内容啊？没有找到对应的文档和测试用例。

例如语句：
String sql = "SELECT *,id,count(distinct id),count(2) as c,max(1) FROM MY_TABLE1, MY_TABLE2, (SELECT \* FROM MY_TABLE3) LEFT OUTER JOIN MY_TABLE4  WHERE ID = (SELECT MAX(ID) FROM MY_TABLE5) AND ID2 IN (SELECT \* FROM MY_TABLE6)";

我想得到所有函数，表名，条件等等，就像jsqlparser一样。请问druid的SQL解析能否实现？

谢谢。


## xiusiyan (21 Aug 2012)

当然能实现，parser后，读语法树的内容。

On Aug 21, 2012, at 11:56 AM, Feng Kuok notifications@github.com wrote:

> @wenshao
> 
> 目前druid的sql解析部分可否支持解析出select语句的内容啊？没有找到对应的文档和测试用例。
> 
> 例如语句：
> String sql = "SELECT *,id,count(distinct id),count(2) as c,max(1) FROM MY_TABLE1, MY_TABLE2, (SELECT \* FROM MY_TABLE3) LEFT OUTER JOIN MY_TABLE4 WHERE ID = (SELECT MAX(ID) FROM MY_TABLE5) AND ID2 IN (SELECT \* FROM MY_TABLE6)";
> 
> 我想得到所有函数，表明，条件等等，就像jsqlparser一样。请问druid的SQL解析能否实现？
> 
> 谢谢。
> 
> —
> Reply to this email directly or view it on GitHub.


## fengkuok (21 Aug 2012)

@xiusiyan 

能不能给出文档或者单元测试用例啊？

```
@Test
public void test_2() throws Exception {
    String sql = "select * from tb order by id asc,name desc";

    MySqlStatementParser parser = new MySqlStatementParser(sql);
    SQLSelectStatement selectStatement =  parser.parseSelect();
    System.out.println(selectStatement.getSelect().getOrderBy());
}
```

这段代码竟然获取到的OrderBy为null，版本：0.2.5


## wenshao (22 Aug 2012)

例子在这里：
在mysql中，orderBy在MySqlSelectQueryBlock中，你看这里的例子：
https://github.com/AlibabaTech/druid/blob/master/src/test/java/com/alibaba/druid/bvt/sql/mysql/MySqlSelectTest_7.java


## fengkuok (23 Aug 2012)

多谢！大体上已经明白怎么下手了。

另外，我觉得API太让使用者汗颜了，
com.alibaba.druid.sql.ast.statement.SQLSelect.getOrderBy()
和
MySqlSelectQueryBlock queryBlock = (MySqlSelectQueryBlock) select.getQuery();
queryBlock.getOrderBy()，

这两者之前有什么区别，或者说我用起来的时候怎么也不会想到还得需要强转（莫非是API演变中的历史原因？）。感觉这样对使用者不够友好。

@wenshao @xiusiyan 
多谢解答，后续使用中遇到问题还会继续问你们的！


