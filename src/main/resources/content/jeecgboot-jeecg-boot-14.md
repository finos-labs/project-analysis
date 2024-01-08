#关于图片访问路径的建议

Owner: jeecgboot

Repo: jeecg-boot

Labels: 

## dangzhenghui1985 (09 Mar 2019)

目前图片的访问路径是人为在前端加前缀来访问的
加了这个前缀 sys/common/view/
http://localhost:3000/jeecg-boot//sys/common/view/user/20190220/e1fe9925bc315c60addea1b98eb1cb1349547719_1550656892940.jpg
数据库里面存储的user/20190220/e1fe9925bc315c60addea1b98eb1cb1349547719_1550656892940.jpg是这个路径
这个导致数据库中存储的文件不能被直接访问 类似'<'img src='value'/'>' 这样的 
其实可以改造成数据库存储的时候用一个img做前缀
img/user/20190220/e1fe9925bc315c60addea1b98eb1cb1349547719_1550656892940.jpg
然后直接访问这个路径的时候就能调用controller 为 img的直接返回 图片流。外部也直接能访问了。这样比较规范
![image](https://user-images.githubusercontent.com/16914422/54072704-76248a80-42b9-11e9-8cdf-29611c5475d9.png)
不会导致想这样比较尴尬的局面产生

## zhangdaiscott (14 Mar 2019)

这个方案可以，考虑到以后图片单独的服务器。
这个前缀配置需要保留

## yuxishua (14 Mar 2019)

考虑到用oss服务器，作者这么做是合理的

