#请教 导入Sample Error:(19, 0) CreateProcess error=2, 系统找不到指定的文件。

Owner: Tencent

Repo: tinker

Labels: 

## LuoboDcom (25 Sept 2016)

我是单独IDE窗口导入Simple ,报如下错误。如图：
![image](https://cloud.githubusercontent.com/assets/7316013/18815110/2c7b00e8-8359-11e6-9280-9fccbad4405c.png)


## memorycj (26 Sept 2016)

 @shwenzhang 请教，导入demo的时候出现，![image](https://cloud.githubusercontent.com/assets/16283516/18822956/7ed623f2-83e7-11e6-93eb-e23b4752c607.png)，gradle的配置没有改动，打印tinkerId没有值，


## shwenzhang (26 Sept 2016)

这个是我们主动抛出的错误，因为TinkerID返回获取不到任何数值，请主动打印一下函数返回值


## memorycj (26 Sept 2016)

@shwenzhang tinkerID没有任何数值，什么情况下会导致获取不到呢？


## shwenzhang (26 Sept 2016)

gitSha函数的返回值，如果没有使用git管理，可以使用其他的函数例如svn rev


## memorycj (26 Sept 2016)

使用的是git进行管理，并且已经配置到环境变量，studio中也进行了相关配置 @shwenzhang 


## 15189611 (27 Sept 2016)

 同求·，我也遇到上面的问题了·配置git 系统变量


## memorycj (27 Sept 2016)

@15189611 https://github.com/Tencent/tinker/wiki/Tinker-%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98


## 15189611 (27 Sept 2016)

 你是换成其他的字符串的 ？还是调用getSha()这个函数的？


## memorycj (27 Sept 2016)

@15189611 我是调用的gitSha()函数，你可以先检查git环境变量是否设置成功，然后还不行的话你可以尝试本地commit一次然后就可以了..


## 15189611 (27 Sept 2016)

好我试试·


## 15189611 (27 Sept 2016)

 谢谢 我也是commit一次 成功了· 


## HuuNguyen312 (27 Sept 2016)

I added git environment variable
![capture](https://cloud.githubusercontent.com/assets/9895294/18858987/692182b4-849a-11e6-90e6-38ebcf30265c.PNG)

But still get "TinkerID is not set!!!
Help me.


## memorycj (27 Sept 2016)

@ngochuu90 My solution is to use local git to submit a code, you can try the next ... 


## HuuNguyen312 (28 Sept 2016)

@memorycj:
I have just used Git. Please guide to me use local git. Thanks


## gbdxl (28 Sept 2016)

@ngochuu90 use git clone rather than download the zip


## jp1017 (24 Nov 2016)

直接设置值了，哈哈 :smile: 

```
String gitRev = 'v1.7.5'
```

## luyingchn (16 Jun 2017)

请教，glide开源项目，导入as，报错Error:CreateProcess error=2, 系统找不到指定的文件。 请问怎么解决

## jp1017 (18 Jun 2017)

@luyingchn 多半是环境没配置好,如果其他项目没问题,就是gradle配置问题咯

## Developmc (28 Jul 2017)

commit一次之后就可以跑起来了

## AliceChjy (14 Jan 2019)

是怎么解决  tinkerId is not set!!!  看不太懂你们上面说的啊 @dodola @memorycj @shwenzhang 

