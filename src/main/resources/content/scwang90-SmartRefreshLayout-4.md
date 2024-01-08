#希望加上侧滑删除以及分割线，如果可以，分割线最好友paddingleft、paddright，方便适用不同场景

Owner: scwang90

Repo: SmartRefreshLayout

Labels: 

## Cashdispenser (06 Jul 2017)

像这样的分割线
DividerDecoration divider = new DividerDecoration.Builder(context)
                .setHeight(R.dimen.default_divider_height)
                .setLeftPadding(R.dimen.dp_55)
                .setRightPadding(R.dimen.dp_15)
                .setColorResource(R.color.color_e8)
                .build();
Recycleview.addItemDecoration(divider);

## scwang90 (06 Jul 2017)

谢谢你的反馈，我已经把分割线列入开发计划，之后的版本将会出现。
不过，SmartRefreshLayout 的开发初衷是：实现强大的刷新框架，集成各种各样的Header
所以侧滑删除这样与刷新无关的功能不会在SmartRefreshLayout中添加，
如果你的项目需要使用到 侧滑删除功能，我建议你使用 [AndroidSwipeLayout](https://github.com/daimajia/AndroidSwipeLayout) 。这是一个成熟强大稳定的侧滑控件。

## ysmintor (06 Jul 2017)

分割线就是RecyclerView的东西， @scwang90 建议不要集成。侧滑删除已经有很多库实现了。

## appdev (06 Jul 2017)

这个只是刷新框架 建议不要集成 专注一件事情。 @scwang90 

## scwang90 (07 Jul 2017)

谢谢 @ysmintor  谢谢 @huclengyue  其实我自己也在纠结，有你们两个的指点我更加明确啦

## fg2q1q3q (10 Jul 2017)

“哈哈，这个已经有了，只是你没仔细看而已”，手动滑稽，O(∩_∩)O~

与刷新无关的千万不要加，我不希望我又多了一个臃肿的库

## scwang90 (12 Jul 2017)

@fg2q1q3q  谢谢，我也是这么想的

