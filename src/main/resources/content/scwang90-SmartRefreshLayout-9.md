#刷新完成回弹时间

Owner: scwang90

Repo: SmartRefreshLayout

Labels: 

## Chenayi (07 Jul 2017)

刷新完成后往往需要停留一下，再回弹回去，可否加个设置刷新成功后回弹时间的参数

## scwang90 (07 Jul 2017)

哈哈，已经有啦，你没看仔细而已
~~~java
public interface RefreshLayout {
    /**
     * 完成刷新
     */
    RefreshLayout finishRefresh();
    /**
     * 完成刷新
     */
    RefreshLayout finishRefresh(int delayed);
}
~~~

## Chenayi (07 Jul 2017)

恩，这个方法我知道，我是希望文本显示的是“刷新完成” ，即刷新完成后停留一下，再收回去。现在是刷新完成立马就收回去了，用户还没看清“刷新完成”的文案。

## Chenayi (07 Jul 2017)

你现在这个方法只是模拟刷新中的时间...

## scwang90 (07 Jul 2017)

哦，你说的是这个呀，你这个提议不错，我会在 金典Header中添加上停留时间这个功能。

不过不会在 RefreshLayout 中添加时间参数的设置，Header完成刷新之后，改停留多久要有 Header 自己决定。用户随意设置会出现问题。

类似的Header已经有啦：CircleHeader

![](https://github.com/scwang90/SmartRefreshLayout/raw/master/art/gif_Circle.gif)

## Chenayi (07 Jul 2017)

恩，好的

