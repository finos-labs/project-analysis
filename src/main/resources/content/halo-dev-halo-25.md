#打包成Jar后路径访问问题

Owner: halo-dev

Repo: halo

Labels: kind/bug 

## jizhuozhi (22 Jul 2018)

在Ubuntu18.04部署的过程中，发现ResourceUtil.getURL获取的basePath为`/home/jiuming/halo/target/dist/halo/halo-lastest.jar!/META-INF/...`，`new File(basePath,FileName)`的路径为`/home/jiuming/file:/home/jiuming/halo/target/dist/halo/halo-lastest.jar!/META-INF/...`无法获取文件路径(因为jar包本身是一个文件，无法获得内部目录)，没有找到解决方案，有相似问题的可以考虑在部署的时候使用`mvn spring-boot:run`的方式启动项目(在项目根目录下)

## ruibaby (22 Jul 2018)

@jizhuozhi 这貌似并不是问题，应该是你没仔细看README，maven我设置了两个profile，用prod打包才行，是吧lib，resources和jar分开了的，你试试？

## jizhuozhi (22 Jul 2018)

试过了啊，除了昨天问完github访问慢用的码云以外，其他的都是readme复制粘贴的，这个问题也是提示没有themes的原因

## ruibaby (22 Jul 2018)

@jizhuozhi 好的，我去装个18.04试试，看看是什么错误。

## ruibaby (22 Jul 2018)

![snipaste_2018-07-22_15-15-41](https://user-images.githubusercontent.com/21301288/43043267-6304a52c-8dc2-11e8-8646-e85ffc22b97e.png)
![snipaste_2018-07-22_15-15-55](https://user-images.githubusercontent.com/21301288/43043272-64a7526c-8dc2-11e8-9458-7e111fb5c86b.png)
好吧，经过测试，确实存在这个问题，我想办法解决一下。

## ruibaby (27 Sept 2018)

找到问题所在了，不支持JDK10，不是系统的问题，抱歉回复晚了。

