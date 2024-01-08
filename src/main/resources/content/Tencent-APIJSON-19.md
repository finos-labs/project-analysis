#可不可以打包成jar

Owner: Tencent

Repo: APIJSON

Labels: Enhancement 增强 

## baikeqin (02 Feb 2018)

可不可以打包成jar，不然去哪里下载jar

## TommyLemon (03 Feb 2018)

@baikeqin 是指maven依赖的fastjson，springboot依赖包，还是说要把APIJSON-Java-Server整个工程打成jar呢？
如果是前者，那是自动下载的，开VPN翻墙的话下载会比较快。
如果是后者，我可以打一个APIJSONLibrary的jar包出来。

## baikeqin (03 Feb 2018)

就是把你这个核心的东西做成jar，其他的东西都是可以在中央仓库下载的

## TommyLemon (03 Feb 2018)

@baikeqin 可以的

## TommyLemon (03 Feb 2018)

其实就是 APIJSONORM，目前是通过依赖工程的方式，APIJSONBoot 和 APIJSONFinal 依赖 APIJSONORM。
将 APIJSONORM 打成 jar 包后，放到 APIJSONBoot 或 APIJSONFinal 的 libs 目录下，就不用依赖 APIJSONORM 工程了。
已经更新到 Github 和码云 Gitee，欢迎使用^_^

## onlyfew (20 Dec 2019)

我自己的springboot的项目，引入APIJSONORM后，运行没问题，但是用maven编译会报错。如何解决呢？

## TommyLemon (22 Dec 2019)

Maven 里面配置依赖这个 jar

## TommyLemon (16 Feb 2020)

@baikeqin @onlyfew APIJSON ORM 已支持 Maven, Gradle 等远程依赖方式，具体见
https://github.com/APIJSON/apijson-orm

