#我用tomcat 跑这几个项目，好像都不行啊

Owner: linlinjava

Repo: litemall

Labels: 欢迎贡献 

## 122448yang (29 Mar 2018)



## linlinjava (29 Mar 2018)

你好，这个后台模块的配置默认是内置tomcat，所以你开发时采用IDE直接右键Application运行即可，而部署的时候则是当作普通可执行jar包运行。

如果你要部署在独立的tomcat中，是可以的，但是需要做一定的修改，你应该在pom.xml里面把jar改成war，建议你参考spring boot相关文档。

## 122448yang (30 Mar 2018)

恩恩，昨晚折腾了一下，好像懂了。还有一个问题。管理后台前端能不能搞个idea的啊，不想去了解其他编辑器。感觉idea挺屌的。

## linlinjava (30 Mar 2018)

IDEA要做前端，那只能购买商业版才行吧。其实用VSC也可以，只是当个好看的文本编辑器而已。我其实主攻后端，前端也是一般，基于别的的项目定制而已。

