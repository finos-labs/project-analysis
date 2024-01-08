#上拉加载完成抖动

Owner: scwang90

Repo: SmartRefreshLayout

Labels: 

## Reginer (11 Jul 2017)

楼主上拉加载做个挺好，不过有的地方我觉得还是可以改进 。

我发现上拉加载完成之后，做了个view的移动操作，我知道楼主是想让新数据露出来一个。但是这样就有个问题了，正在加载的过程中往回滑的话，会跳动。

## scwang90 (11 Jul 2017)

这个抖动目前是去不掉的，除非不要这个功能，两个动画无法完全同步，就会很出现抖动

## Reginer (11 Jul 2017)

这样的设计确实会有个这样的bug，隐藏footer的时候会回弹。


## scwang90 (11 Jul 2017)

如果你不想要 footer 的回弹功能 调用 setEnableLoadmore（false） 就不会有回弹了

## scwang90 (12 Jul 2017)

1.0.2-alpha-3 发布  抖动问题完美解决

## Reginer (12 Jul 2017)

赞

## scwang90 (12 Jul 2017)

1.0.2-alpha-2 有 BUG 
请使用 
1.0.2-alpha-3

## Reginer (12 Jul 2017)

好的  

