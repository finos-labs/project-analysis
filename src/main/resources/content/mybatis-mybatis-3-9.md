#Missing TypeHandler returns null return than throwing an exception

Owner: mybatis

Repo: mybatis-3

Labels: bug 

## voidmain (08 Mar 2013)

I run into an issue where I mapped a result in a resultMap to Joda's DateTime but didn't register a TypeHandler. Rather than failing with a new error message, it looks like the org.apache.ibatis.executor.resultset.FastResultSetHandler class is returning null in the getPropertyMappingValue method (somewhere near line 316). 

This code should be more like this:

``` java
  protected Object getPropertyMappingValue(ResultSet rs, MetaObject metaResultObject, ResultMapping propertyMapping, ResultLoaderMap lazyLoader, String columnPrefix) throws SQLException {
    final TypeHandler<?> typeHandler = propertyMapping.getTypeHandler();
    if (propertyMapping.getNestedQueryId() != null) {
      return getNestedQueryMappingValue(rs, metaResultObject, propertyMapping, lazyLoader, columnPrefix);
    } else if (typeHandler != null) {
      final String column = prependPrefix(propertyMapping.getColumn(), columnPrefix);
      return typeHandler.getResult(rs, column);
    }
    throw new TypeHandlerException("Unknown type " + propertyMapping.getJavaType() + ". You need to register a TypeHandler for this type for MyBatis to correctly convert the result.");
  }
```

Instead, it looks like this (notice the return null at the end):

``` java

  protected Object getPropertyMappingValue(ResultSet rs, MetaObject metaResultObject, ResultMapping propertyMapping, ResultLoaderMap lazyLoader, String columnPrefix) throws SQLException {
    final TypeHandler<?> typeHandler = propertyMapping.getTypeHandler();
    if (propertyMapping.getNestedQueryId() != null) {
      return getNestedQueryMappingValue(rs, metaResultObject, propertyMapping, lazyLoader, columnPrefix);
    } else if (typeHandler != null) {
      final String column = prependPrefix(propertyMapping.getColumn(), columnPrefix);
      return typeHandler.getResult(rs, column);
    }
    return null;
  }
```


## emacarron (08 Mar 2013)

In orde to speed up the resolution, could you create a test for this?

http://code.google.com/p/mybatis/wiki/Test


## agustafson (08 Mar 2013)

Personally, if it wasn't possible to find an appropriate mapper for a type then I would expect it to throw an exception. In the case above, your date column would've been null and you would've had no idea why that was the case. Would it not be better to ensure that you have a type handler for every type that you've trying to retrieve?


## emacarron (08 Mar 2013)

Agreed.
That looks indeed a bug because if I don`t recall wrong there should be no mappings without a type handler. If the type is not know it should youse the UnknownTypeHandler. We should first check that.


## amitter77 (10 Jun 2013)

Hi, since I upgraded to mybatis 3.2.2 I have started seeing an issue that I think may be related to this change. I opened up the issue and it is described here: [issue61](https://github.com/mybatis/mybatis-3/issues/61)


