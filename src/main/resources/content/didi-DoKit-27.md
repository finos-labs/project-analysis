#Android编译失败，Attribute "titleColor" already defined with incompatible format

Owner: didi

Repo: DoKit

Labels: bug 

## android9527 (27 Dec 2018)

```
Attribute "text" already defined with incompatible format.
Message{kind=ERROR, text=Attribute "text" already defined with incompatible format.,
sources=[project/build/intermediates/res/merged/deft/debug/values/values.xml:3043], 
original message=, tool name=Optional.of(AAPT)}

Attribute "titleColor" already defined with incompatible format.
Message{kind=ERROR, text=Attribute "titleColor" already defined with incompatible format., 
sources=[/build/intermediates/res/merged/deft/debug/values/values.xml:3148],
original message=, tool name=Optional.of(AAPT)}
```
资源属性冲突，建议对属性也加上前缀。

## wanglikun7342 (29 Dec 2018)

1.0.5版本已修复，请更新

