#Error occurs in sentinel-dashboard frontend

Owner: alibaba

Repo: Sentinel

Labels: kind/bug area/dashboard 

## lonecloud (31 Jul 2018)


## Issue Description
前端页面出现报错信息

### Describe what happened (or what feature you want)
按照正常启动Application后，访问localhost:8080.直接进入到页面中，点击搜索后，出现一个无标题选项，点击展开后报错
### Describe what you expected to happen
如果无应用，则直接不显示就好了。


### How to reproduce it (as minimally and precisely as possible)

1. 不运行任何关联sentinel应用
2. 直接启动dashboard
3. 进入到页面
4. 点击搜索
5. 点击左侧菜单空白框，出现菜单
6. 点击子菜单，无反应，控制台报错

### Tell us your environment
windows7 

### Anything else we need to know?
![图片](https://github.com/lonecloud/issuesImages/raw/master/Sentinel/dashboard-error.png)




## sczyh30 (31 Jul 2018)

Thanks for reporting! Would you like to fix this and send a PR to contribute?

