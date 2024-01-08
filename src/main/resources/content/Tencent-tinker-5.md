#多渠道打包怎么处理

Owner: Tencent

Repo: tinker

Labels: 

## bigbigbigbigbig (24 Sept 2016)

1.是否tinkerPatch出来的补丁只能用在同一个oldApk上。如MyApp版本1，有Patch1补丁，现在提交了新版本MyApp版本2到应用市场，有bug又生出了patch2补丁，请问patch2是一定不可以更新到MyApp版本1了？
2.多渠道打包中，oldApk应该怎么处理？例如，使用应用宝的渠道做oldApk，那么tinkerPatchRelease出来的tinkerPatch补丁就无法在别的渠道的更新了，请问正确的方案步骤是如何处理的。


## shwenzhang (25 Sept 2016)

1. 我们用tinkerid作为版本管理，对于第一种情况的确如此。为dex为例，合成时我们要校验旧dex的crc跟checksum。
2. 多渠道是没有问题的，除了一种情况，就是你不同的渠道代码是不一样的。tinker用tinkerid作为版本管理，但是合成时不是以整个apk来做比对，而是里面变化的文件。


