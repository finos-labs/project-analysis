#gradle 2.2 sync不过 2.1.0可以

Owner: Tencent

Repo: tinker

Labels: 

## bigbigbigbigbig (24 Sept 2016)

Error:(331, 0) Task with name 'assembleRelease' not found in project ':app'.
<a href="openFile:/Users/big/AndroidStudioProjects/tinker-master/tinker-sample-android/app/build.gradle">Open File</a>


## oasisfeng (25 Sept 2016)

No such issue here with Gradle build plugin 2.2.0 and Gradle 3.0. Build is fine.


## shwenzhang (25 Sept 2016)

open sample with new as window！


## ecdsa2008 (25 Sept 2016)

需要改成2.1.0否则  编译失败.


## dodola (25 Sept 2016)

@llmsparkbbbboy 你可以把sample里app 的gradle代码改成如下

``` gradle
  tasks.all {
        if ("assemble${taskName.capitalize()}".equalsIgnoreCase(it.name)) {
           //........
        }
}
```


