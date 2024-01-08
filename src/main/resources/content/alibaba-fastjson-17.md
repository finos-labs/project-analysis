#可不可以增加输出字符串为unicode的SerializerFeature

Owner: alibaba

Repo: fastjson

Labels: 

## jinghui70 (31 Oct 2012)

不设置，输出为

```
{"name":"你好"}
```

设置后，输出为

```
{"name":"\u4f60\u597d"}
```


## wenshao (07 Nov 2012)

已经有了：SerializerFeature.BrowserCompatible


