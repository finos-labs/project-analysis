#开启硬件加速后，切换全屏会黑屏一段时间

Owner: CarGuo

Repo: GSYVideoPlayer

Labels: 

## westspy (09 Dec 2016)

设置enableMediaCodec(true)，后发现切换到全屏会黑屏大概一秒多，从全屏返回播放区域也会黑屏

## CarGuo (09 Dec 2016)

hello，硬解码黑屏是因为切换播放的时候生成画面速度问题，不只是全屏， 有些手机在开始播放前一会的时候也会，IJK表示目前无解 。

只是因为有人提了想要简单的硬解码，所以我就把硬解码的接口加上，目前也没有什么办法。所以还是软解吧。

目前硬解使用的场景应该是直接打开一个全屏播放器，或者详情播放那种，类似playAcitity和DetailActivity这种

## Android-Boys (03 Jan 2019)

我也遇到了。按照上面说的改了。还是有黑屏 一闪而过


