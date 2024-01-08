#请问如何在播放前跳转到指定位置?

Owner: CarGuo

Repo: GSYVideoPlayer

Labels: 

## Crystalbye (01 Dec 2016)

指定位置 貌似没暴露这个方法

## CarGuo (01 Dec 2016)

 GSYVideoManager.instance().getMediaPlayer().seekTo(mSeekTimePosition);在prepare之后是可以，不过并不是很好，等会刚好要更新，我加上吧~

## Crystalbye (01 Dec 2016)

好的 刚试了试在onPrepared里调用确实生效了 谢谢

