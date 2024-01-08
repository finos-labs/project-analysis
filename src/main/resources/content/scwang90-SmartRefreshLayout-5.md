#体验问题

Owner: scwang90

Repo: SmartRefreshLayout

Labels: 

## jtyang (06 Jul 2017)

下载demo体验了下，有点体验不好的是，经典风格正在刷新时列表还可往上滚一点点，等刷新结束列表顶部那点也还是被隐藏了。另外下拉的时候有时候会卡死一会，刷新中ui完全不见了，只有等刷新成功ui才恢复正常。

## scwang90 (06 Jul 2017)

你好，谢谢你的反馈。

## 第一个问题：

Demo中并没设置在刷新的时候进制滚动列表，所以在刷新的时候，还是可以去上下的滚动列表。这个是操作习惯问题，有的人希望刷新的时候，还可以滚动列表，有的人希望刷新时，禁止任何滑动操作。

SmartRefreshLayout 提供了如下属性

|名称-name|格式-format|描述-description|
|:---:|:---:|:---:|
|srlDisableContentWhenRefresh|boolean|是否在刷新的时候禁止内容的一切手势操作（默认false）|

可以在xml中设置为true
```xml
    <com.scwang.smartrefresh.layout.SmartRefreshLayout
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:srlDisableContentWhenRefresh="true">
        <ListView
            android:layout_width="match_parent"
            android:layout_height="match_parent"/>
    </com.scwang.smartrefresh.layout.SmartRefreshLayout>
```
也可以在代码中设置true
```java
final RefreshLayout refreshLayout = (RefreshLayout) findViewById(R.id.smartLayout);
refreshLayout.setsrlDisableContentWhenRefresh(true);
```

这样在刷新的时候列表就不能滚动了

## 第二个问题

应该是bug问题，我需要你帮我确认一下，你的手机型号，系统版本，有没有什么不寻常的手势操作，这个问题除了 金典风格 其他的Header 有吗？

## jtyang (06 Jul 2017)

第一个问题我觉得是bug，刷新中状态可以滚动是没问题的，主要是感觉刷新中列表去滚动会有在闪的感觉；第二个问题三星-J710F Android 6.0.1，没有什么不寻常操作，就是正常去下拉刷新。其他head暂时没发现问题。

## scwang90 (06 Jul 2017)

第二个问题我在我的手机和模拟器上都没有出现，我想知道这个问题出现的频繁吗？有多少的概率会出现这个问题？

## jtyang (06 Jul 2017)

多拉几次的话还是挺容易出现的。

## scwang90 (06 Jul 2017)

谢谢，我会想办法解决的

## ysmintor (07 Jul 2017)

Demo改下图标和appname吧，太原始了。

## scwang90 (07 Jul 2017)

@ysmintor  收到，图标不好找，如合适的推荐一下。

## Chenayi (07 Jul 2017)

在刷新中的时候，用户如果往上拉，我觉得触发刷新完成收回去比较好，1如果动不了，用户体验不好，2如果能继续往下拉，就会触发加载更多的事件，两个事件就会冲突，数据可能就会混乱

