#tinker-sample-android项目找不到SampleApplication类

Owner: Tencent

Repo: tinker

Labels: 

## mwping (26 Sept 2016)

[AndroidManifest.xml](https://github.com/Tencent/tinker/blob/dev/tinker-sample-android/app/src/main/AndroidManifest.xml)

项目里并没有SampleApplication这个类，麻烦确认一下，thx


## shwenzhang (26 Sept 2016)

SampleApplication是运行时自动生成的，请认真查看文档


## CSnowStack (26 Sept 2016)

请问,原本已经有application的话, ApplicationLike要怎么写,原本application里的代码写在哪


## shwenzhang (26 Sept 2016)

把application的代码都移动到ApplicationLike中，参考Sample的做法即可


## CSnowStack (26 Sept 2016)

好的,谢谢


## mwping (26 Sept 2016)

@shwenzhang 看到了，多谢


