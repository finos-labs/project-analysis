#怎么登陆

Owner: qiurunze123

Repo: miaosha

Labels: 

## huifeidezhipai (21 Dec 2018)

大佬，项目index.html   hello.html   login.html都登陆不了

## qiurunze123 (21 Dec 2018)

哈哈哈  你行不行 localhost:8080/user/do_register 注册  /login/to_login 登陆 


## huifeidezhipai (21 Dec 2018)

登陆了，怎么模拟多个人高并发秒杀呢？

## qiurunze123 (21 Dec 2018)

你可以写一个 数据库创建的脚本测试 创建很多用户 当时我就是这么写的  然后 生成token 然后jemter压测下 具体的 我没写  我过几天补上吧 

## qiurunze123 (22 Dec 2018)

稍微的写了一下  去看如何利用jmeter进行压测 这一问题的解决思路  在代码里面 写了如何生成多个用户的id和token 模拟前端请求  ！！！！  jmeter基础安装 你要研究下！ 其他的都已经更新

## xmt1139057136 (27 Dec 2018)

来个交流群吧！
![image](https://user-images.githubusercontent.com/23303979/50471994-f42f0000-09f1-11e9-9d16-a5b5bed51fb6.png)


## huifeidezhipai (28 Dec 2018)

tokens.txt记录：

1300000000,{"status":"SESSION_ERROR","code":30005,"message":"Session不存在或者已经失效!","data":null,"count":null}
1300000001,{"status":"SESSION_ERROR","code":30005,"message":"Session不存在或者已经失效!","data":null,"count":null}
1300000002,{"status":"SESSION_ERROR","code":30005,"message":"Session不存在或者已经失效!","data":null,"count":null}

## qiurunze123 (28 Dec 2018)

这。。。。。。 你重新下载下呢 你这么给我 我没法 判断问题啊

## huifeidezhipai (28 Dec 2018)

啥都没动，运行UserUtil.java  ,user表生成数据了，txt文件就这样了。。

## qiurunze123 (28 Dec 2018)

先启动项目 在进行 才能生成 还有 项目有的加安全设置了 你要根据实际情况 自己 处理下 才能生成 我要是 给你改了 之后 fork的 就用不了了  我记得 其他的都又写了一份 就剩下 miaoshapath的 好像是 要改改

