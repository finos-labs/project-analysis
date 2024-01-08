#查询Count时，使用getObject()取数会报异常

Owner: apache

Repo: shardingsphere

Labels: type: bug 

## wangda (25 Feb 2016)

**现象：**
SQL： `select count(*) as cnt from tbl;`

如果我使用 `ResultSet.getLong()` 或 `getInt()`都可以。
但是如果我使用 `getObject()`，就会报异常：
`ShardingJdbcException("Unsupported data type:%s", convertType)`

**原因：**
`s-jdbc`在聚合`Merge`时，会根据调用方法的返回值来转换`merge`的结果，当调用`getObject()`取`Number`类型结果时，就需要将`Number`转为`Object`了，现在`s-jdbc`直接报不支持的类型。

**修改方法：**
对于返回类型为`Object`时，直接将`Number`值返回。

代码位置：
`com/dangdang/ddframe/rdb/sharding/merger/common/ResultSetUtil.java`
`convertNumberValue`方法：
我给增加了

```
case "java.lang.Object":
              return value;
```

在我本地测试没有问题了。


## hanahmily (26 Feb 2016)

@wangda 感谢您的bug回复，1.0.1的时候我们会进行修复


