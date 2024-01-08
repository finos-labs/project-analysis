#你好，能做一个连续播放的demo吗

Owner: CarGuo

Repo: GSYVideoPlayer

Labels: 

## qq294685358 (08 Dec 2016)

我现在有个需求，就是在监听到当前视频播放完成后，自动开始播list里的下一个视频，我自己写了点代码试了这个功能，但是发现播放到第二个视频的时候，只有声音，播放界面是黑的，不知道怎么回事

## qq294685358 (08 Dec 2016)

![image](https://cloud.githubusercontent.com/assets/15881412/21007346/7a91da38-bd77-11e6-8748-5e36f66fa044.png)
这是连播的逻辑，其中的handler是因为如果不设置一个延迟时间的话，就会导致只有声音，但是黑屏。如果设置了比如2秒过后再自动播放，就正常

## CarGuo (08 Dec 2016)

一般我都是延时500毫秒，后面我看看怎么优化。

## qq294685358 (08 Dec 2016)

能说下进入activity直接全屏的方法吗，找不到。。

## CarGuo (08 Dec 2016)

直接全屏？参考detailplayer吧~github主页的各个版本说明可以当做接口文档！如果你是打开activity的时候就横屏全屏的话，直接设置activity为锁定横屏和全屏的style就好啦，不要弄旋转的utils这样。

## qq294685358 (08 Dec 2016)

直接设置横屏的话效果是可以，但是就没办法在横屏下按返回键变成竖屏了,会变成直接退出activity

## CarGuo (08 Dec 2016)

不是很理解。是点击按键就竖屏全屏，还是横屏全屏？如果是竖屏全屏为什么需要返回键返回竖屏？
下方第一个是旋转工具类让 控件返回到竖屏，第二个是旋转工具类模拟点击全屏按键改变状态，第三个是列表全屏的返回非全屏。
```
if (orientationUtils != null) {
    orientationUtils.backToProtVideo();
}

 orientationUtils.resolveByClick();

if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
 }

```

## qq294685358 (09 Dec 2016)

![image](https://cloud.githubusercontent.com/assets/15881412/21036908/473983a6-be04-11e6-9f61-eb2b6d7671cc.png)
能告诉我这个方法是干嘛的吗，为什么写出来了又没有用上去

## CarGuo (09 Dec 2016)

这个是以前旧版本的全屏和退出全屏的方式，demo里的detailplayer。

## qq294685358 (09 Dec 2016)

![image](https://cloud.githubusercontent.com/assets/15881412/21039680/8da74de4-be1a-11e6-894f-7c9b2cf50707.png)
大兄弟，我这集成了你的播放器有个这个BUG，很急，今天内要交差，你能直接加我QQ说吗，比较方便。我QQ 294685358

## qq294685358 (09 Dec 2016)

上面这个BUG是在我进入页面后，点击播放，先点击全屏，然后再退出全屏，就变成这样了

## qq294685358 (09 Dec 2016)

我刚刚试了下，发现是有虚拟按键的手机才会这样，有一台魅族没有虚拟按键，就正常

## CarGuo (09 Dec 2016)

你加首页的那个qq群，然后这个应该是你设置隐藏虚拟按键之后，也设置隐藏actionbar，在全屏的时候。

## CarGuo (09 Dec 2016)

还有看看是不是最新版本喔！

## qq294685358 (09 Dec 2016)

我上午才下的最新版本，我是直接下载你的demo，把你的demo里的library复制到我工程里去的 

## CarGuo (09 Dec 2016)

你试试类似demo里面recyclerView的那种方式吧

