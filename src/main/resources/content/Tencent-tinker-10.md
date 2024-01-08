#请教“调用tinkerPatchDebug, 补丁包与相关日志会保存在/build/outputs/tinkerPatch/”什么意思

Owner: Tencent

Repo: tinker

Labels: 

## silencezwm (25 Sept 2016)

在使用步骤中，第三步：调用tinkerPatchDebug, 补丁包与相关日志会保存在/build/outputs/tinkerPatch/

一直搞不明白要怎么使用才能搞出补丁包，求解，万分感谢。


## ecdsa2008 (25 Sept 2016)

好纠结的问题,想编译个demo 困难重重


## shwenzhang (25 Sept 2016)

请问问题在哪里呢？使用gradle 编译的话，按照文档步骤应该没有问题


## ecdsa2008 (25 Sept 2016)

1s
        oldApk = ${ bakPath } "app-debug-0925-18-11-23.apk"
2s
      tasks.getByName("tinkerPatch${taskName.capitalize()}") {....}
3s
Error:(331, 0) Task with name 'tinkerPatchDebug' not found in project ':app'.

apk已经生成,并且修改了 oldApk的路径,但是编译还是出现了错误.
对gradle机制不是很了解,不知道是我使用的姿势不对,还是有其他原因.


## shwenzhang (25 Sept 2016)

应该是使用方式不对，建议学习gradle基本知识。或使用命令行版本，但是还是推荐使用gradle版本。


