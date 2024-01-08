#工程报错

Owner: jeecgboot

Repo: jeecg-boot

Labels: 

## kingdomty (04 Mar 2019)

你好
问题是这样的，我在github上下载代码以后导入IDEA，发现很多代码报错。一看日志的log变量无法找到和很多vo和dto没有setter和getter方法，请问这是什么问题呢？

## zhangdaiscott (04 Mar 2019)

2、Lombok 的使用
     实体没有get/set方法，因为通过Lombok 简化了代码。
     需要注意： eclipse默认不支持Lombok 需要单独安装，安装参考：          https://blog.csdn.net/qq_25646191/article/details/79639633

