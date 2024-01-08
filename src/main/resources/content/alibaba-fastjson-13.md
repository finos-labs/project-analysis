#java bean序列化问题

Owner: alibaba

Repo: fastjson

Labels: 

## ferdiknight (11 Oct 2012)

JSON.parseObject(String text,Class<V> clazz)

方法反序列化时是不是只支持标准格式的setter的java bean？我写一个了一append模式setter的bean实例，属性设置都是null

setter的模式如下，如果需要反序列化这种模式的bean，ParseConfigure该怎么设置呢

``` java
class Test
{
     private String content;
     public Test setContent(String content)
     {
             this.content = content;
             return this;
     }
}

```


## ferdiknight (11 Oct 2012)

跟踪源代码，看到以下方法中的这条语句，将void返回类型以外的方法排除了，这里是否可以去掉该条件，用以兼容builder模式的java bean？

``` java
com.alibaba.fastjson.util.DeserializeBeanInfo.computeSetters(Class<?> clazz)
```

``` java
if (!method.getReturnType().equals(Void.TYPE)) {
                continue;
}
```


## wenshao (07 Nov 2012)

更新最新版本1.1.24，这个问题已经解决


