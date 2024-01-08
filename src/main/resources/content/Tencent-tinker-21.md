#patch fail, please check reason

Owner: Tencent

Repo: tinker

Labels: 

## adhere534 (26 Sept 2016)

Load　Patch 提示
patch fail, please check reason
 SampleResultService receive result: 
                                                                                 PatchResult: 
                                                                                 isUpgradePatch:true
                                                                                 isSuccess:false
                                                                                 rawPatchFilePath:/storage/emulated/0/patch_signed_7zip.apk
                                                                                 costTime:146
                                                                                 patchVersion:null
                                                                                 patchTinkerID:null
                                                                                 baseTinkerID:null
                                                                                 Throwable: null


## shwenzhang (26 Sept 2016)

请补充完整的加载日志，使用"Tinker."过滤。请提交符合规范的issue


## adhere534 (26 Sept 2016)

receive a patch file: /storage/emulated/0/patch_signed_7zip.apk, isUpgrade:true, file size:1999
get platform:all
 SampleResultService receive result: 
 PatchResult: 
 isUpgradePatch:true
 isSuccess:false
 rawPatchFilePath:/storage/emulated/0/patch_signed_7zip.apk
 costTime:126
 patchVersion:null
 patchTinkerID:null
 baseTinkerID:null
 Throwable: null


## shwenzhang (26 Sept 2016)

这里是合成失败，需要把合成过程的日志也补上


## adhere534 (26 Sept 2016)

/tinker.sample.android W/Tinker.TinkerLoader: tryLoadPatchFiles:patch dir not exist:/data/data/tinker.sample.android/tinker
/tinker.sample.android D/Tinker.DefaultAppLike: onBaseContextAttached:
/tinker.sample.android I/Tinker.SamplePatchListener: application maxMemory:256
/tinker.sample.android W/Tinker.Tinker: tinker patch directory: /data/data/tinker.sample.android/tinker
/tinker.sample.android I/Tinker.TinkerLoadResult: parseTinkerResult loadCode:-2
/tinker.sample.android W/Tinker.TinkerLoadResult: can't find patch file, is ok, just return
/tinker.sample.android I/Tinker.DefaultLoadReporter: patch load result, path:/data/data/tinker.sample.android/tinker, code:-2, cost:3
/tinker.sample.android W/Tinker.Tinker: tinker load fail!
/tinker.sample.android D/Tinker.DefaultAppLike: onCreate
/tinker.sample.android D/Tinker.DefaultAppLike: onTrimMemory level:15
/tinker.sample.android E/Tinker.MainActivity: i am on onCreate classloader:dalvik.system.PathClassLoader[DexPathList[[zip file "/data/app/tinker.sample.android-1/base.apk
/tinker.sample.android E/Tinker.MainActivity: i am on onCreate string:I am in the base apk
/tinker.sample.android E/Tinker.MainActivity: i am on onResume
/tinker.sample.android W/Tinker.UpgradePatchRetry: onPatchRetryLoad retry info not exist, just return
/tinker.sample.android I/Tinker.SamplePatchListener: receive a patch file: /storage/emulated/0/patch_signed_7zip.apk, isUpgrade:true, file size:1999
/tinker.sample.android I/Tinker.SamplePatchListener: get platform:all


## adhere534 (26 Sept 2016)

receive a patch file: /storage/emulated/0/patch_signed_7zip.apk, isUpgrade:true, file size:4221
get platform:all
SampleResultService receive result: 
PatchResult: 
isUpgradePatch:true
isSuccess:false
rawPatchFilePath:/storage/emulated/0/patch_signed_7zip.apk
costTime:38
patchVersion:null
patchTinkerID:null
baseTinkerID:null
Throwable: null


## adhere534 (26 Sept 2016)

patch.info没有生成
tryLoadPatchFiles:patch info not exist:/data/data/tinker.sample.android/tinker/patch.info

 patchVersion:null
 patchTinkerID:null
 baseTinkerID:null
 Throwable: null
这几个空的 是什么情况？


## shwenzhang (26 Sept 2016)

是在合成的时候错误了，你要看合成过程的日志
'''java
isSuccess:false
'''


## adhere534 (27 Sept 2016)

没看见sample:patch进程起来 
![image](https://cloud.githubusercontent.com/assets/9199894/18857035/242e9450-8493-11e6-84f7-8b3eea9d948e.png)

我用的Github的sample 代码没动 就动了 gradle参数oldAPk 和TinkerId 
然后跑起来就这样了


## shwenzhang (27 Sept 2016)

把Tinker.跟右边only show都去掉看看


## adhere534 (27 Sept 2016)

![image](https://cloud.githubusercontent.com/assets/9199894/18857273/3267ed3a-8495-11e6-9fb2-b9618b78c6c1.png)

如果把Tinker.去掉后 会很多log刷屏


## shwenzhang (27 Sept 2016)

错误码出来了，-7，TINKER_ID不一致。你的tinkerId是不是类似1.0这样短的字符串？现在会有问题，你把他改成长一点的。用到的apk-parser库有问题


## adhere534 (27 Sept 2016)

现在Success了
但是
![image](https://cloud.githubusercontent.com/assets/9199894/18857486/eefacce6-8496-11e6-9263-22951cef140a.png)

怎么还是一个啊


## 7heaven (27 Sept 2016)

:patch 进程并不会在打完补丁后再次启动  只有在接收到补丁文件的时候(TinkerInstaller.onReceiveUpgradePatch或者TinkerInstaller.onReceiveRepairPatch)才会启动:patch进程对补丁进行处理


