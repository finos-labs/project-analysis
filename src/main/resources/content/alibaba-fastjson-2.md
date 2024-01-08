#使用WriteClassName进行序列化的json串, 在无该类的环境下无法进行反序列化

Owner: alibaba

Repo: fastjson

Labels: 

## zhaoyao (30 Jan 2012)

版本1.1.14


## wenshao (02 Feb 2012)

这个问题的确存在，但这个是否算一个bug呢？


## zhaoyao (03 Feb 2012)

不是很清楚@type 是否是一个合法的键值, 如果规范允许这种key出现, 并且使用其他json库序列化出来这样一个key, 而fastjson无法解析它, 那么应该是一个bug吧.


## wenshao (03 Feb 2012)

用这个版本试试看：http://code.alibabatech.com/mvn/snapshots/com/alibaba/fastjson/1.1.16-SNAPSHOT，看是否满足你的需求。


## zhaoyao (06 Feb 2012)

可以正常解析了, 但是解析后会把@type扔掉

``` javascript
 {
        "a": "a", 
        "b": "b", 
        "c": "c", 
        "id": 1, 
        "condition": [
                {
                        "requirement": "5", 
                        "@type": "x.y.z.SomeType"
                }
        ], 
        "icon": "1.png"
}
```

读取后就变成了

``` javascript
 {
        "a": "a", 
        "b": "b", 
        "c": "c", 
        "id": 1, 
        "condition": [
                {
                        "requirement": "5"
                }
        ], 
        "icon": "1.png"
}
```


## wenshao (07 Feb 2012)

已经改好了，请你再测试一下，谢谢支持 ：）


