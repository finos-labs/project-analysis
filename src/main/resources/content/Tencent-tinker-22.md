#onPatchPackageCheckFail

Owner: Tencent

Repo: tinker

Labels: bug 

## edu-lance (26 Sept 2016)

我创建了一个工程

``` java
...
buildConfig {
   applyMapping = getApplyMappingPath()
   applyResourceMapping = getApplyResourceMappingPath()
   tinkerId = "1.0"
}
...
```

配置和sample基本上一致，只对自己定义的某些东西进行了修改的。
tinkerId 写死为1.0
Application只进行了默认

``` java
@Override
public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        TinkerInstaller.install(this);
}
```

但是始终无法成功，请问还有哪些需要注意的地方吗？

``` java
E/Tinker.UpgradePatch: UpgradePatch tryPatch:onPatchPackageCheckFail
I/Tinker.DefaultPatchReporter: patchReporter: package check failed. path:/storage/emulated/0/patch_signed_7zip.apk, isUpgrade:true, code:-7
```


## shwenzhang (26 Sept 2016)

-7是tinkerId not equal, 请查看oldapk是否已经插入了TINKER_ID字段。请先详细查看文档


## edu-lance (26 Sept 2016)

你好，我在gradle中加入了 tinkerId = "1.0"配置，然后将apk包安装至手机中，这时候我注意到build/intermediates/manifests/中存在

``` java
 <meta-data android:name="TINKER_ID" android:value="1.0"/>
```

然后我修改部分代码，运行tinkerPathDebug生成的补丁包


## shwenzhang (26 Sept 2016)

可以把补丁包解开，查看package_meta.txt中的tinkerid 是多少？主要这边不知道你改动了哪些代码


## edu-lance (26 Sept 2016)

改动如下:

``` java
public void load(View view) {
//       throw new NullPointerException("修复前");
        Toast.makeText(this,"更新后",0).show();

    }
```


## edu-lance (26 Sept 2016)

TINKER_ID={4:1065353216}


## shwenzhang (26 Sept 2016)

新的TinkerId怎么会变成4:1065353？


## edu-lance (26 Sept 2016)

我也觉得奇怪。

``` java
tinker add 1.0 to your AndroidManifest.xml /Users/xiang/develop/studioSpace/Test2/sma/build/intermediates/manifests/full/debug/AndroidManifest.xml
tinker gen AndroidManifest.xml in build/intermediates/tinker_intermediates/AndroidManifest.xml
:sma:tinkerProcessDebugManifest
apply resource mapping file /Users/xiang/develop/studioSpace/Test2/sma/build/bakApk/ is illegal, just ignore
tinker gen resource public.xml in build/intermediates/tinker_intermediates/public.xml
tinker gen resource idx.xml in build/intermediates/tinker_intermediates/idx.xml
```


## edu-lance (26 Sept 2016)

使用ApkTool 查看安装包中manifest是对的。
<meta-data android:name="TINKER_ID" android:value="1.0"/>


## edu-lance (26 Sept 2016)

谢谢。
我把id从1.0改为mytinkerId 可以了。


## shwenzhang (26 Sept 2016)

这个的确有问题，是因为引用的apk-parse库有问题，我已经向那边提了issue


## dodola (26 Sept 2016)

复现了这个问题 Mark一下


## DozenWang (27 Sept 2016)

之前我也遇到了，在tinkerPatch 中加入以下代码，即可解决
packageConfig {
  ....
  configField("TINKER_ID", "1.0")

}


## shwenzhang (27 Sept 2016)

@DozenWang ,这个是比较高级的解决方法哈。我已经给那边提issue了，不过似乎还没答复我，不太想引入太多的源码。


## shwenzhang (14 Oct 2016)

@liuxang please try with 1.7.0


