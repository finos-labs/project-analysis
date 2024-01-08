#有支付接口么?

Owner: linlinjava

Repo: litemall

Labels: 新功能 

## wangxinxu411 (30 Mar 2018)

微信，或者支付宝接口

## linlinjava (30 Mar 2018)

你好，微信小程序有支付API，但是由于本人没有商户支付能力，因此只是简单的模拟。如果你有的话，可以可以在官网看相关文档，然后修改litemall-wx-api模块里面的WxPayController，和litemall-wx模块里面的src/pages/shopping/checkout/checkout.js里面的submitOrder方法。

## lzdn (04 Apr 2018)

我准备先学习一下作者的代码。

## linlinjava (25 Apr 2018)

你好，现在看来我说的有点简单了。
支付接口虽然有微信支付，但是没有商户支付权限，订单支付和退款的操作没有很好地实现和测试。

## linlinjava (06 May 2018)

你好，刚刚实现了微信支付功能， 516d19da6bf9a2616d6b8f0753148c23d742dd41 和  8ab026529ea464e47c3a6025f60b47e7f7aaeed5。

你需要
1. litemall-wx-api的properties文件里面相应的支付信息和支付响应链接地址。
2. litemall-wx的配置文件里面设置appid。

存在问题是：
1. 仅仅支持微信支付，不支持退款操作。
2. 微信支付功能很简单，可能也会有问题，下面还会持续完善。

## linlinjava (12 May 2018)

支持微信支付。

不支持微信自动退款，退款操作设计流程：
1. 管理员登录微信支付平台实行退款；
2. 管理员登录litemall管理后台点击退款成功。

