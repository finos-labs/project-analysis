#请问一下，是不是拦截到已认证的token就可以任意访问单点登录的子系统呢

Owner: shuzheng

Repo: zheng

Labels: 

## a466350665 (22 Feb 2017)

像这样，子系统URL+“？token=” + token
![image](https://cloud.githubusercontent.com/assets/19278040/23201321/0fdda798-f914-11e6-9c23-cd7d2a245dfa.png)


## shuzheng (24 Feb 2017)

是的，目前正在改进：
1、认证成功，带回生成和子系统sessionId绑定的code，有效期5分钟；
2、子系统使用code+系统id+secret换取token；
3、子系统验证token。

## shuzheng (24 Feb 2017)

回跳地址backurl也要做安全校验

## a466350665 (24 Feb 2017)

好的  期待

## chenbuer (07 May 2018)

现在这里面的token都写成了code：
![image](https://user-images.githubusercontent.com/7947945/39712475-c36300f6-5255-11e8-9378-7939c8f61926.png)
这个有什么规范或者约定吗？一会儿code，一会儿token。

谢谢

## xiaoguobiao (08 May 2018)

这个应该就是一个变量名吧

On 8 May 2018 at 00:22, chenbuer <notifications@github.com> wrote:

> 现在这里面的token都写成了code：
> [image: image]
> <https://user-images.githubusercontent.com/7947945/39712475-c36300f6-5255-11e8-9378-7939c8f61926.png>
> 这个有什么规范或者约定吗？一会儿code，一会儿token。
>
> 谢谢
>
> —
> You are receiving this because you are subscribed to this thread.
> Reply to this email directly, view it on GitHub
> <https://github.com/shuzheng/zheng/issues/5#issuecomment-387120329>, or mute
> the thread
> <https://github.com/notifications/unsubscribe-auth/AEyRxm99CSZj4wMR4Bhr5sLjS06IY3e4ks5twHTEgaJpZM4MITJD>
> .
>


