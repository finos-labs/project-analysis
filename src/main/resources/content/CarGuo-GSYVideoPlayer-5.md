#全屏的时候可不可以把statusbar给隐藏掉

Owner: CarGuo

Repo: GSYVideoPlayer

Labels: 

## IdioticMadman (29 Nov 2016)

如题。。。

## CarGuo (29 Nov 2016)

ListVideoUtil的有setHideStatusBar和setHideActionBar，在全屏的时候就会隐藏。注意它的全屏布局记得放在最外层。
video的startWindowFullscreen也有对应的哟！

## IdioticMadman (29 Nov 2016)

可以直接小窗播放么

## CarGuo (29 Nov 2016)

目前只有showSmallVideo和hideSmallVideo接口

## IdioticMadman (29 Nov 2016)

如果要直接小窗播放的话，要咋做？不是放在一个listview或者recycleview里面

## CarGuo (29 Nov 2016)

你直接把视频StandardGSYVideoPlayer做成小窗口的视频播放器就可以，要可以移动的话参考SmallVideoTouch这个作为父布局。

## CarGuo (29 Nov 2016)

如果可以了，请close，thx <(￣︶￣)>

## IdioticMadman (29 Nov 2016)

好嘞，谢谢！

