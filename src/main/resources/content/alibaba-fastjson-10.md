#deserialize public-long field error?

Owner: alibaba

Repo: fastjson

Labels: 

## sunxwell (06 Sept 2012)

package demo;

import com.alibaba.fastjson.JSON;

public class FJDemo {
    public long id;

```
public static void main(String[] args) throws Exception {
    FJDemo se = new FJDemo();
    se.id = 1000;
    String str = JSON.toJSONString(se);
    System.out.println(str);
    se = JSON.parseObject(str, FJDemo.class);
}
```

}

---

{"id":1000}
Exception in thread "main" com.alibaba.fastjson.JSONException: create asm deserializer error, demo.FJDemo
    at com.alibaba.fastjson.parser.ParserConfig.createJavaBeanDeserializer(ParserConfig.java:405)
    at com.alibaba.fastjson.parser.ParserConfig.getDeserializer(ParserConfig.java:344)
    at com.alibaba.fastjson.parser.ParserConfig.getDeserializer(ParserConfig.java:271)
    at com.alibaba.fastjson.parser.DefaultJSONParser.parseObject(DefaultJSONParser.java:468)
    at com.alibaba.fastjson.JSON.parseObject(JSON.java:211)
    at com.alibaba.fastjson.JSON.parseObject(JSON.java:171)
    at com.alibaba.fastjson.JSON.parseObject(JSON.java:292)
    at demo.FJDemo.main(FJDemo.java:13)
Caused by: java.lang.NullPointerException
    at com.alibaba.fastjson.parser.deserializer.ASMDeserializerFactory._batchSet(ASMDeserializerFactory.java:515)
    at com.alibaba.fastjson.parser.deserializer.ASMDeserializerFactory._deserialze(ASMDeserializerFactory.java:370)
    at com.alibaba.fastjson.parser.deserializer.ASMDeserializerFactory.createJavaBeanDeserializer(ASMDeserializerFactory.java:85)
    at com.alibaba.fastjson.parser.ParserConfig.createJavaBeanDeserializer(ParserConfig.java:401)
    ... 7 more

使用的是9月4日从github.com/AlibabaTech/fastjson/下载的源码，不清楚是哪个版本。


## wenshao (08 Sept 2012)

这个问题是使用public field导致的，如果是用public getter或者setter方法就不会存在这个问题。我将会尽快修改。谢谢你的反馈。


## sunxwell (10 Sept 2012)

此问题已修正，动作真快啊！


