#无法进入后台，

Owner: halo-dev

Repo: halo

Labels: help wanted 

## Lunaticzy (15 May 2018)

你好，我使用宝塔面板的nginx做反代部署在服务器上，但无法进入后台，每次输入密码后，有登录成功的提示，但无法使用进入后台，我也根据文档在nginx配置文件中加入了，proxy_redirect default;但并无效果，我该怎么去解决这个问题，希望能解答我得疑惑。感谢，:smile:

## ruibaby (15 May 2018)

宝塔默认的配置文件中有：
```bash
    #REWRITE-START URL重写规则引用,修改后将导致面板设置的伪静态规则失效
    include /www/server/panel/vhost/rewrite/xxx.conf;
    #REWRITE-END
```
把include那一行注释，重启Nginx即可。

## Lunaticzy (15 May 2018)

感谢感谢，:smile:

## ruibaby (15 May 2018)

@9527tech 可以了吗？

## Lunaticzy (15 May 2018)

嗯嗯，已经行了;但我无法理解，那个文件没东西，但为什么能影响正常使用，请dalao为我解答一些 感谢，

## ruibaby (15 May 2018)

@9527tech 我也无法理解，我也看过那个文件，确实没东西，但是加上它的话就不行。所以请下面的dalao来回答吧。

## Lunaticzy (15 May 2018)

@ruibaby 好吧，感谢dalao百忙之中回答我的问题，

