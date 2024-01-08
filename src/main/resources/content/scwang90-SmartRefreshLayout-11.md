#兼容ScrollableLayout

Owner: scwang90

Repo: SmartRefreshLayout

Labels: 

## Chenayi (08 Jul 2017)

作者你好，麻烦您看下能不能兼容一下这个库https://github.com/cpoopc/ScrollableLayout
配合这个库使用的时候，会出现一种情况就是，当下拉出现刷新头部，此时手指不松开，然后上拉，会触发recyclerview的滑动，recyclerviewiew内容会滑上去，此时再下拉就会截掉一部分

## scwang90 (08 Jul 2017)

我先去研究一下 ScrollableLayout

## scwang90 (08 Jul 2017)

另外，你在体验我的Demo的时候，遇到过 [体验问题5](https://github.com/scwang90/SmartRefreshLayout/issues/5) 中的问题描述：“另外下拉的时候有时候会卡死一会，刷新中ui完全不见了，只有等刷新成功ui才恢复正常。”吗？
由于我把我能找到的模拟器和手机都试了一下，都没能发现这个问题，希望有人能帮我确认一下~

## Chenayi (08 Jul 2017)

体验问题5 中的问题描述：“另外下拉的时候有时候会卡死一会，刷新中ui完全不见了，只有等刷新成功ui才恢复正常"
暂时没有遇到过的

## scwang90 (10 Jul 2017)

ScrollableLayout 这个库是支持 android-Ultra-Pull-To-Refresh 的，
主要是通过重写判断方法 并调用 canPtr()
我仿照  android-Ultra-Pull-To-Refresh  的功能添加了一个方法

setRefreshScrollBoundary 方法，设置滚动边界判断，与ScrollableLayout结合的用法类似
~~~java
        refreshLayout.setRefreshScrollBoundary(new RefreshScrollBoundaryAdapter(){
            @Override
            public boolean canPullDown(View content) {
                return !scrollableLayout.canPtr();
            }
        });
~~~



## monkeylwp (23 Jul 2018)

@scwang90  麻烦问一下，为什么只有1.0.2版本中有setRefreshScrollBoundary 方法？后面被删了么？

## MIkeeJY (14 Feb 2019)

@monkeylwp 研究下源码，最新版是 setScrollBoundaryDecider 设置滚动边界
```java
   mRefresh.setScrollBoundaryDecider(new ScrollBoundaryDeciderAdapter() {
            @Override
            public boolean canRefresh(View content) {
                return mScrollableLayout.isCanPullToRefresh();
            }
        });
```

