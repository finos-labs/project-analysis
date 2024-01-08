#滑动到底部自动加载更多的时候，如果最后一项高度较高，会自动多次调用加载更多

Owner: scwang90

Repo: SmartRefreshLayout

Labels: 

## fishwjy (11 Jul 2017)

滑动到底部自动加载更多的时候，如果最后一项高度较高，会自动多次调用加载更多且自动上滑（当然这是为了让最后一项完全显示出来），直到最后一项完全显示出来，自动加载才会停止。
我有录屏但是这里没上传附件的功能
总体来说，这个刷新框架做的相当牛逼

## scwang90 (11 Jul 2017)

谢谢你的反馈，我会好好从处理这个问题，最后一项高度大概是多少dp？我好重现。
如果你是用在项目上，时间紧急的话，建议先设置关闭自动加载更多功能，改为手动上拉先。
setEnableAutoLoadmore(false);

## fishwjy (12 Jul 2017)

[自动加载更多.mp4.zip](https://github.com/scwang90/SmartRefreshLayout/files/1140650/mp4.zip)
这是录制的视频
高度是205dp

## scwang90 (12 Jul 2017)

谢谢，我研究之后会解决这个问题的

## scwang90 (12 Jul 2017)

问题已经解决，请使用 1.0.2-alpha-4 版本

