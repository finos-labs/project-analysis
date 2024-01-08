#对于某些特殊字符串，使用fastjson序列后的字符串无法使用org.json或者python里面的json进行解析？

Owner: alibaba

Repo: fastjson

Labels: 

## zeutrap (20 Feb 2013)

在使用中发现，如果对使用fastjson序列化后的字符串，用java的其他json包如org.json或者使用python的json包进行解析时，在某些情况下会出现错误。而反过来则不会。比如：[{"Name":"Operator","Value":"中国移动 "}] 中国移动后面的字符为特殊字符。当使用fastjson序列化后的结果在vim中打开显示为“^@”， 而org.json的为"\u001"。


## wenshao (20 Feb 2013)

你可以使用JSON.toJSONString(obj, SerializerFeature.BrowserCompatible)


## zeutrap (20 Feb 2013)

got it. Thx.


## zeutrap (20 Feb 2013)

这样做会将中文字符如“中国移动”也序列化为"\uXXXX"。请问如何才能像org.json一样使得只对于不可见字符如此做，而对于中文等字符保持原样？因为对于可见字符做这样的编码会使得序列化后的数据可读性减低。


## zeutrap (22 Feb 2013)

另外，JSON.toJSONString(obj, SerializerFeature.BrowserCompatible)对于JSONObject中Key的值有不可见字符时也会出错。


## wenshao (23 Jun 2013)

1.1.32版本将会支持！


