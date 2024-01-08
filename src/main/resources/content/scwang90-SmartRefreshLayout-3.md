#有没有介绍自定义header的资料 ？

Owner: scwang90

Repo: SmartRefreshLayout

Labels: 

## yanxinmiao (29 Jun 2017)

看了看自定义header的源码，一头雾水， 有些时用的SVG 路径 ？

有没有自定义header方面的介绍 。

## scwang90 (29 Jun 2017)

会有的，不过还需要些时间，这个项目刚刚建立一个星期左右，文档也正在不断完善中，你可以先看看 RefreshHeader 这接口，只要实现了它就可以自定义Header了，如果你急于写自己的header，有疑问的可以先发邮件问我，我的邮箱 scwang90@hotmail.com，也可以就在这里问我，我看到了会回复你的。

## scwang90 (29 Jun 2017)

是的，就是SVG路径，路径写在代码中，可以节省了图片资源。
自定义Header 有三种实现方式：
第一种：继承 View 实现 RefreshHeader  重写 onDraw 方法 （要有一定的自定义View基础）
第二种：继承 Layout 实现 RefreshHeader 添加子View （相对简单，参考 ClassicsHeader）
第三中：直接在Xml中 定义header 布局  （最简单，参考：Demo- 实践-餐饮外卖）

## yanxinmiao (30 Jun 2017)

@scwang90 非常感谢，我再看看，有问题再联系你

## scwang90 (20 Jul 2017)

文档已经排版完成

[简单自定义Header](https://github.com/scwang90/SmartRefreshLayout/blob/master/art/md_smart.md)
[自定义Header和Footer](https://github.com/scwang90/SmartRefreshLayout/blob/master/art/md_custom.md)

