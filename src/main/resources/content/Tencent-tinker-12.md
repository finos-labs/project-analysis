#多渠道打包patch无法更新问题

Owner: Tencent

Repo: tinker

Labels: 

## bigbigbigbigbig (25 Sept 2016)

多渠道打包中,改变的的是AndroidManifest.xml的友盟渠道的变量
<meta-data
        android:name="UMENG_CHANNEL"
        android:value="${UMENG_CHANNEL_VALUE}"/>
我的试验结果是仅仅改变了这一点，别的渠道的apk都无法更新patch,例如，使用应用宝的渠道做oldApk，那么tinkerPatchRelease出来的tinkerPatch补丁只可以更新到应用宝apk，baidu的apk就无法更新patch了，请问有什么方法可以处理这种情况吗。


## shwenzhang (25 Sept 2016)

请将具体的错误码贴出来，不能仅仅描述情况。将合成过程的日志贴出来，用"Tinker."过滤！


## bigbigbigbigbig (25 Sept 2016)

![1e9181e3-6488-418c-a5f0-3c1ed35d9612](https://cloud.githubusercontent.com/assets/17684980/18816056/bbaa5632-8373-11e6-89da-f563bfbddd54.png)
![651ed001-95aa-49f9-b343-ea6a32831472](https://cloud.githubusercontent.com/assets/17684980/18816057/c00bdb2e-8373-11e6-883c-607c2d6fdd97.png)
![92cc7a57-315e-466f-949e-bf6d62d60450](https://cloud.githubusercontent.com/assets/17684980/18816059/c19e5c6e-8373-11e6-86fa-1aa707b6c863.png)
单渠道没有发现问题，现在的问题是如何改变AndroidManifest.xml的渠道变量，而别的渠道同样可以正常更新


## shwenzhang (26 Sept 2016)

dex原始crc不一致，你们不同渠道的代码不一样？这样是不行的


## bigbigbigbigbig (26 Sept 2016)

AndroidManifest.xml也包括代码？只是在打包时在gradle里面替换android:value="${UMENG_CHANNEL_VALUE}"/>
？没办法解决了吗？或有什么在运行时在替换UMENG_CHANNEL_VALUE，打包时值不变？


## shwenzhang (26 Sept 2016)

如果只改变AndroidManfest是没有问题的，你先确认一下两个渠道的classes.dex是不是一样的


## bigbigbigbigbig (26 Sept 2016)

是我打包方式有问题？
productFlavors {
    Umeng {}
        myapp {}
        c360 {}
        wandoujia {}
        baidu {}
        yingyonghui {}
        xiaomi {}
        other {}
        tuiguang {}
        huawei_adv {}
        oppo {}
        jifeng {}
        huangse {}
        vivo {}
        meizu {}
        jinli {}
  }
  productFlavors.all { flavor -> flavor.manifestPlaceholders = [UMENG_CHANNEL_VALUE: name]
  }
请问，正确的方式应该怎样？之前是gradle批量打包，现在要手动改UMENG_CHANNEL_VALUE然后assembleRelease?


## shwenzhang (26 Sept 2016)

你先要确定不同的flavor有没有定制的功能，导致打出来的原始apk中的dex是不同的。你要先清楚出现这个问题的原因是什么？就是两个不同渠道oldapk中的classes.dex是不同的


## szitguy (26 Sept 2016)

我这边也有这个问题，如果有定制功能，classes.dex不一样，就不能使用tinker了吗？还是说要一个个flavor单独打补丁？


## shwenzhang (26 Sept 2016)

是这样的，这里我们有三个解决方法：1. 对于特殊渠道单独发补丁 2. 将特殊的功能拆成单独的dex 3. 所有渠道的代码都是一样，只是通过配置走不同的分支。  
个人认为第三条路是正道


## szitguy (26 Sept 2016)

非常感谢


## bigbigbigbigbig (26 Sept 2016)

我试了下，手动改渠道是可以的patch的，用favor就会改classes.dex,不知道什么原因。🙏感谢解答


## h3r3x3 (26 Sept 2016)

用gradle打多渠道包后，flavor值会写入BuildConfig.java，这个文件是合入dex的，尽管你的初衷只是想改变manifest里面的值。
这样生成的各个包dex都是不一样的，后期打补丁按理是通不过的。
@shwenzhang 同学说的解决方法，第一个解决方案，大多数app估计不太适合，有些人打了200个渠道，buildconfig都不一样，这样每个都维护补丁包也不现实，倒是第二个分dex可以考虑，不过现在大多数app可能只是buildconfig类不一样，那单独把这个类拆分一个包？
还有是不是有可以不把flavor写进buildconfig的方法？


## shwenzhang (26 Sept 2016)

@h3r3x3 ,我的第一点是给某个渠道单独特殊功能的app。事实上，对于渠道我们更建议通过zip comment的方式实现。这样更快，获取也简单


## h3r3x3 (26 Sept 2016)

是的，zip comment确实是快，不过还是有不少用flavor打包的，可以在QA里面说下 :)


## 7heaven (27 Sept 2016)

在zip的comment块写入信息其实不太可靠 之前有遇到过上传应用市场后 comment块被清空的情况


## shwenzhang (27 Sept 2016)

应用市场也是要写code的，所以不能重叠


## h3r3x3 (08 Oct 2016)

@7heaven 哪个市场碰到过？
@shwenzhang 这个不重叠如何避免？按说应用市场是在后面写的，是被覆盖的，貌似没法控制


## shwenzhang (14 Oct 2016)

@bigbigbigbigbig please try with 1.7.0


## nEdAy (07 Sept 2017)

测试已解决 

方法：通过强制在compile${flavorName}${typeName}JavaWithJavac前篡改BuildConfig.java，使所有渠道包打入dex的BuildConfig一致 

相关代码：

```
/**
 * 为了使Tinker可用于非主渠道包，强制更新BuildConfig，保证所有渠道生成的dex一致
 */
android.applicationVariants.all { variant ->
    def flavorName = variant.productFlavors[0].name;
    def typeName = variant.buildType.name;
    tasks.all {
        if ("compile${flavorName}${typeName}JavaWithJavac".equalsIgnoreCase(it.name)) {
            it.doFirst {
                def loggerFilePath = "${buildDir}/generated/source/buildConfig/${flavorName}/${typeName}/com/iflytek/elpmobile/smartlearning/BuildConfig.java"
                def updatedDebug = new File(loggerFilePath).getText('UTF-8')
                        .replaceAll(flavorName, "FLAVOR已被篡改，请通过meta-data获得渠道号")
                new File(loggerFilePath).write(updatedDebug, 'UTF-8')
            }
        }
    }
}
```

