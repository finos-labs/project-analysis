#导出excel时无法对日期格式化

Owner: alibaba

Repo: easyexcel

Labels: 

## cxiaolng (02 May 2018)

@ExcelProperty(index = 5, value = "提交时间", format = "yyyy-MM-dd HH:mm:ss")

看源码，对日期类型的字段没有做类型转换
![image](https://user-images.githubusercontent.com/3888018/39515251-23845314-4e2c-11e8-8004-a91ab0bffdb0.png)


## cxiaolng (03 May 2018)

![image](https://user-images.githubusercontent.com/3888018/39555575-d0d5200e-4eac-11e8-8dcf-b6721ac38b7a.png)
![image](https://user-images.githubusercontent.com/3888018/39555579-d7da96cc-4eac-11e8-9ec4-1a20bed7f45b.png)
暂时可以这样解决

## cikeJonas (02 Aug 2018)

@cxiaolng 这种方法还是不行  这个ConvertUtils是哪个包的

## SwallowGG (13 Nov 2018)

由于1.0.*版本核心考虑的是怎么解决读excel内存溢出问题，自己重写了读excel的POI的底层，写仍旧使用POI。可以从根本解决OOM问题。但是由于自己对excel的格式认识不全面，有很多坑产生了很多BUG。最新版本以POI为底层。最新版：1.1.2-beat1，虽然不能完全解决OOM但可以解决大部分的OOM问题，完全解决OOM版本会等稳定后再开放，敬请理解。目前稳定版本1.1.2-beat1

