#老版本代码更新到2017-03-27版本，zheng-cms maven install报错

Owner: shuzheng

Repo: zheng

Labels: 

## greydesolate (28 Mar 2017)

报错信息大概如下
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.1:compile (default-compile) on project zheng-cms-web: Compilation failure: Compilation failure:
[ERROR] /D:/ideaworkspace/zheng/zheng-cms/zheng-cms-web/src/main/java/com/zheng/cms/web/controller/TagController.java:[70,24] 找不到符号
[ERROR] 符号:   方法 thymeleaf(java.lang.String)
[ERROR] 位置: 类 com.zheng.cms.web.controller.TagController
[ERROR] /D:/ideaworkspace/zheng/zheng-cms/zheng-cms-web/src/main/java/com/zheng/cms/web/controller/QaController.java:[23,16] 找不到符号
[ERROR] 符号:   方法 thymeleaf(java.lang.String)
[ERROR] 位置: 类 com.zheng.cms.web.controller.QaController
[ERROR] /D:/ideaworkspace/zheng/zheng-cms/zheng-cms-web/src/main/java/com/zheng/cms/web/controller/CategoryController.java:[67,24] 找不到符号
[ERROR] 符号:   方法 thymeleaf(java.lang.String)
[ERROR] 位置: 类 com.zheng.cms.web.controller.CategoryController
[ERROR] /D:/ideaworkspace/zheng/zheng-cms/zheng-cms-web/src/main/java/com/zheng/cms/web/controller/BlogController.java:[23,16] 找不到符号
=======================分割线，不确认的解决方法=======================
后来仔细看了下当前项目。之前zheng-ui是个maven项目,现在变为非maven项目。
在zheng-ui是maven项目的版本运行过zheng-ui的maven的install导致本地文件夹还留着target文件夹，git更新后这个target文件夹应该手动将其删除就好。
之后再重新maven install下common ,upms,admin等模块再去install下cms就没有再报错


如果确认是这个问题，解决方法没错麻烦关闭下哈~

## shuzheng (28 Mar 2017)

以前的zheng-ui拆分成了两部分，zheng-admin(后台模板)、zheng-ui(前台模板)。你的报错是因为zheng-common没有更新或者编译。

