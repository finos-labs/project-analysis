#BUG 3.0.6版本 FileUtil.copy 方法错误 

Owner: dromara

Repo: hutool

Labels: bug 

## xyz327 (09 Jun 2017)

FileUtil [788行](https://github.com/looly/hutool/blob/master/hutool-core/src/main/java/com/xiaoleilu/hutool/io/FileUtil.java#L788)
会错误的把文件也判断成文件夹
FileUtil.internalCopyDir 方法中对于原文件是否为文件夹的的判断有问题
```java
if(src.isDirectory())
```
`src`应该改为`srcFile`
```java
if(srcFile.isDirectory())
```

## looly (10 Jun 2017)

十分感谢。确实为bug

