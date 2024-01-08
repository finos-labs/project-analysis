#请问JSONObject是否保证序列化后按照key的字典序排序？

Owner: alibaba

Repo: fastjson

Labels: 

## zeutrap (02 Feb 2013)

看实现是hashmap，但测试结果却貌似是字典序？
我的测试是压入n个字符。

这个特性是否存在？主要是目前碰到需要这个特性的场景，如果有的话，就不用再加工了。谢谢！


## wenshao (06 Feb 2013)

看com.alibaba.fastjson.JSON的源码：

```
  features |= com.alibaba.fastjson.serializer.SerializerFeature.SortField.getMask();
```

确定是！


## wu560130911 (09 Sept 2016)

{"extendsParams":{"sign":"MqYc+gEUxAUwrbLHkTdNH0WO9TTk/myKNfldLMtgd9hL6YIJKDYRcH9LAhSEN3ft4EknDvPwiNr6\r\nOpgX/XEz3LW7n7lNwQNzDgpaklON4IIv6Ous3AXFIqENbK3sES9ZW/qgARTf7YPwzXg650Myf9Eo\r\nI21zTAto9uYvoYXF/4s=","result":"2000","usercode":"A923397","value":"50","orderno":"1473389201734","datetime":"20160909110314","billid":"R160909110939456","mode":"q","version":"1.0","info":"支付成功","accountvalue":"48"},"payGate":"Ofpay"}

{"extendsParams":{"sign":"MqYc+gEUxAUwrbLHkTdNH0WO9TTk/myKNfldLMtgd9hL6YIJKDYRcH9LAhSEN3ft4EknDvPwiNr6\r\nOpgX/XEz3LW7n7lNwQNzDgpaklON4IIv6Ous3AXFIqENbK3sES9ZW/qgARTf7YPwzXg650Myf9Eo\r\nI21zTAto9uYvoYXF/4s=","result":"2000","usercode":"A923397","value":"50","orderno":"1473389201734","datetime":"20160909110314","billid":"R160909110939456","accountvalue":"48","info":"支付成功","version":"1.0","mode":"q"},"payGate":"Ofpay"}

没有一定是按照字典序，两个结果不一样啊


