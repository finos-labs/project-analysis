#反序列化时出现NullPointerException

Owner: alibaba

Repo: fastjson

Labels: 

## sunng87 (09 Dec 2011)

你好，我在使用fastjson作Clojure RPC的序列化支持，我通过

``` clojure
(JSON/toJSONString {:a 1} (into-array Serializer/WriteClassName))
```

得到JSON字符串

``` json
{"@type":"clojure.lang.PersistentArrayMap",{"@type":"clojure.lang.Keyword","name":"a","sym":{"name":"a"}}:1}
```

然后通过parse进行反序列化遇到这个空指针异常

No message.
  [Thrown class com.alibaba.fastjson.JSONException]

Restarts:
 0: [QUIT] Quit to the SLIME top level
 1: [CAUSE1] Invoke debugger on cause   [Thrown class java.lang.NullPointerException]

Backtrace:
  0: com.alibaba.fastjson.parser.deserializer.DefaultObjectDeserializer.deserialze(DefaultObjectDeserializer.java:349)
  1: com.alibaba.fastjson.parser.deserializer.DefaultObjectDeserializer.deserialze(DefaultObjectDeserializer.java:251)
  2: com.alibaba.fastjson.parser.DefaultJSONParser.parseObject(DefaultJSONParser.java:251)
  3: com.alibaba.fastjson.parser.AbstractJSONParser.parse(AbstractJSONParser.java:157)
  4: com.alibaba.fastjson.parser.AbstractJSONParser.parse(AbstractJSONParser.java:135)
  5: com.alibaba.fastjson.JSON.parse(JSON.java:87)
  6: com.alibaba.fastjson.JSON.parse(JSON.java:78)
  7: slacker.serialization.fastjson$parse_string.invoke(fastjson.clj:6)
  8: user$eval1853.invoke(NO_SOURCE_FILE:1)
  9: clojure.lang.Compiler.eval(Compiler.java:5424)
 --more--

我使用的是maven仓库的1.1.9版本。


## wenshao (09 Dec 2011)

问题已经重现，正在处理中，由于对Clojure不熟悉，需要花一些时间学习Clojure


## sunng87 (10 Dec 2011)

我看了一下clojure的keyword类并不是一个标准的JavaBean，没有构造函数，没有setter，所以我猜在反序列化的时候可能会存在问题。不知道这个NPE是否是这个原因？
不知道fastjson有没有自定义serializer和deserializer的途径，类似

``` java
public interface Serializer<T> {
    public JSONObject serialize (T o);
    public T deserialize(JSONObject o);
}
```


## wenshao (10 Dec 2011)

有的，你自己实现com.alibaba.fastjson.parser.deserializer.AutowiredObjectDeserializer和com.alibaba.fastjson.serializer.AutowiredObjectSerializer，然后配置在下面这两个路径中：
META-INF/services/com.alibaba.fastjson.parser.deserializer.AutowiredObjectDeserializer
META-INF/services/com.alibaba.fastjson.serializer.AutowiredObjectSerializer

这个机制类似与jdk自动的ServiceLoader。fastjson内部的一些Serializer和Deserializer也是通过这种机制实现的。


## sunng87 (10 Dec 2011)

On Sat 10 Dec 2011 06:14:38 PM CST, 温少 wrote:

> 有的，你自己实现com.alibaba.fastjson.parser.deserializer.AutowiredObjectDeserializer和com.alibaba.fastjson.serializer.AutowiredObjectSerializer，然后配置在下面这两个路径中：
> META-INF/services/com.alibaba.fastjson.parser.deserializer.AutowiredObjectDeserializer
> META-INF/services/com.alibaba.fastjson.serializer.AutowiredObjectSerializer
> 
> 这个机制类似与jdk自动的ServiceLoader。fastjson内部的一些Serializer和Deserializer也是通过这种机制实现的。
> 
> ---
> 
> Reply to this email directly or view it on GitHub:
> https://github.com/AlibabaTech/fastjson/issues/1#issuecomment-3090154

谢谢，我去了解一下！

## 

Sun Ning
Software developer
Nanjing, China (N32°3'42'' E118°46'40'')
http://about.me/sunng/bio


## zhaoyao (12 Jan 2012)

不知道这个和我遇到的是不是同一个问题 版本1.1.9

``` java
public class FieldMap extends HashMap<String, Object> {

    public FieldMap field(String field, Object val) {
        this.put(field, val);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T field(String field) {
        return (T) this.get(field);
    }

    public static FieldMap ofInstance(Object ins) {
        Preconditions.checkNotNull(ins);
        FieldMap fieldMap = new FieldMap();
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(ins);
        for (PropertyDescriptor descriptor : beanWrapper.getPropertyDescriptors()) {
            String propertyName = descriptor.getName();
            fieldMap.field(propertyName, beanWrapper.getPropertyValue(propertyName));
        }

        return fieldMap;
    }
}
```

``` java
JSON.toJSONString( m, SerializerFeature.WriteClassName );
```

Exception in thread "main" com.alibaba.fastjson.JSONException
            amt com.alibaba.fastjson.parser.deserializer.DefaultObjectDeserializer.deserialze(DefaultObjectDeserializer.java:349)
            at com.alibaba.fastjson.parser.deserializer.DefaultObjectDeserializer.deserialze(DefaultObjectDeserializer.java:251)
            at com.alibaba.fastjson.parser.DefaultJSONParser.parseObject(DefaultJSONParser.java:251)
            at com.alibaba.fastjson.parser.AbstractJSONParser.parse(AbstractJSONParser.java:157)
            at com.alibaba.fastjson.parser.AbstractJSONParser.parse(AbstractJSONParser.java:135)
            at com.alibaba.fastjson.JSON.parse(JSON.java:87)
            at com.alibaba.fastjson.JSON.parse(JSON.java:78)
            at com.matrixjoy.salon.module.payment.PaymentServiceImpl.main(PaymentServiceImpl.java:158)
            at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
            at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
            at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
            at java.lang.reflect.Method.invoke(Method.java:597)
            at com.intellij.rt.execution.application.AppMain.main(AppMain.java:120)
Caused by: java.lang.NullPointerException
            at com.alibaba.fastjson.parser.deserializer.DefaultObjectDeserializer.parseObject(DefaultObjectDeserializer.java:164)
            at com.alibaba.fastjson.parser.deserializer.DefaultObjectDeserializer.deserialze(DefaultObjectDeserializer.java:344)
            ... 12 more


## wenshao (18 Jan 2012)

这个bug 1.1.14已经修复了。


