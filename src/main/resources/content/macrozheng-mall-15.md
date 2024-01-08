#@Getter/@Setter在Eclipse/IDEA开发工具下不起作用的问题

Owner: macrozheng

Repo: mall

Labels: 

## zhonghuasheng (24 Dec 2018)

> 关于@Getter/@Setter不起作用的问题的描述
@macrozheng 你好，我使用Eclipse import本项目，在download一些依赖之后，发现一些setXXX/getXXX在我本地环境不起作用，查询后得知是需要安装lombok plugin。我拜读了你的代码，个人认为需要去掉@Getter/@Setter的使用，理由如下：
* 1. 对其他协同开发者带来一定不便，开发者需要额外安装plugin
* 2. 在mall-mbg project中的其他model多数使用 getXXX/setXXX，而不是@Getter/@Setter，Coding Style不一致
你觉得呢？
![image](https://user-images.githubusercontent.com/16185320/50394803-cac86700-079a-11e9-93a0-ccbdc96455fc.png)
![image](https://user-images.githubusercontent.com/16185320/50394811-d9168300-079a-11e9-85db-3d1568fd94f5.png)


## macrozheng (25 Dec 2018)

项目写到一半，发现这个挺好用的，就尝试了下

## zhonghuasheng (25 Dec 2018)

@macrozheng I think this is a good project and can be better, how do you think? I want to do some contributes for it, this is my wechat id: `chenyong_smallant`, please contact me if interested

