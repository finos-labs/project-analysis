#对属性命名中第一个单词只有一个字母的属性无法序列化

Owner: alibaba

Repo: fastjson

Labels: 

## fengyie007 (14 Sept 2012)

如User对象中tName属性，它的get方法是 gettName();这种情况下均无法序化。


## ferdiknight (11 Oct 2012)

tName 标准的setter名字应该是getTName啊


## wenshao (07 Nov 2012)

这个不是bug


