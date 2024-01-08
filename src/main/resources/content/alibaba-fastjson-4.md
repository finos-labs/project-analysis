#NEED a util/JSONStringer class

Owner: alibaba

Repo: fastjson

Labels: 

## ashtoash (29 May 2012)

如题


## wenshao (29 May 2012)

能不能把问题描述清楚一些？


## ashtoash (31 May 2012)

就是在构造一些不跟任何bean关联的json的时候用的工具类,有object(),key(),value(),endObject()之类的方法.
没有这个类的话直接用JSONObject构造挺不方便的,又不想为了用这个类再引入另一个json包.

觉得应该有这么个类的啊,难道是名字不同我没找到?


## wenshao (01 Jun 2012)

新增加了JSONWriter，不知道是不是你所需要的，测试代码看这里：
https://github.com/AlibabaTech/fastjson/blob/master/src/test/java/com/alibaba/json/bvt/JSONWriterTest.java


## wenshao (01 Jun 2012)

最新版的快照在这里：http://code.alibabatech.com/mvn/snapshots/com/alibaba/fastjson/1.1.20-SNAPSHOT/


## ashtoash (01 Jun 2012)

恩,没错就是这个 thumb


