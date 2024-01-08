#获取当前播放时间怎么得到啊

Owner: CarGuo

Repo: GSYVideoPlayer

Labels: 

## as11051105 (24 Nov 2016)

RT，根据获取当前不同的播放时间来触发逻辑，有对应的方法么？

## CarGuo (24 Nov 2016)

内部有getCurrentPositionWhenPlaying和 getDuration()接口，不过都是protected属性，我开放一下吧。不过需要在加载到数据的时候才可以获取到，我更新下版本吧··

## CarGuo (24 Nov 2016)

你好，1.2.2已经更新getCurrentPositionWhenPlaying和 getDuration()已经开放了，增加了VideoAllCallBack的onPrepared接口。

推荐如DEMO中的SampleListener一样继承VideoAllCallBack然后再重载你需要的接口，可以在onPrepared里面获取到总时长，在你需要的时候getCurrentPositionWhenPlaying获取当期时长。

## as11051105 (24 Nov 2016)

谢谢！

## Lsy1992 (25 Nov 2016)

获取当前的播放时间 ，莫非是要做广告 ε=ε=ε=┏(゜ロ゜;)┛

## CarGuo (25 Nov 2016)

@Lsy1992 哈哈哈

## CarGuo (25 Nov 2016)

如果可以请关闭issue，(＾－＾)V

