#Error:Execution failed for task ':app:tinkerProcessDebugManifest'. > tinkerId is not set!!!

Owner: Tencent

Repo: tinker

Labels: 

## xyxzfj (25 Sept 2016)

Error:Execution failed for task ':app:tinkerProcessDebugManifest'.

> tinkerId is not set!!!

when added the line at the end of app/build.gradle:

```
apply plugin: 'com.tencent.tinker.patch'
```

<img width="1072" alt="screen shot 2016-09-25 at 16 50 20" src="https://cloud.githubusercontent.com/assets/1221283/18814016/37e0afc8-8340-11e6-9538-4ed04f4f1f7a.png">


## bigbigbigbigbig (25 Sept 2016)

add TINKER_ID=myTINKER_ID to gradle.properties file


## shwenzhang (25 Sept 2016)

you must add tinkerId like tinker-android-sample


