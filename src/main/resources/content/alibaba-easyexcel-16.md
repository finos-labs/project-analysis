#使用自定义类接收excel数据报错

Owner: alibaba

Repo: easyexcel

Labels: 

## xjsunup (11 Apr 2018)

AnalysisEventListener 这个类使用泛型 AnalysisEventListener<User>之后就不做处理了, invoke的参数Object回来是个集合

## SwallowGG (13 Nov 2018)

由于1.0.*版本核心考虑的是怎么解决读excel内存溢出问题，自己重写了读excel的POI的底层，写仍旧使用POI。可以从根本解决OOM问题。但是由于自己对excel的格式认识不全面，有很多坑产生了很多BUG。最新版本以POI为底层。最新版：1.1.2-beat1，虽然不能完全解决OOM但可以解决大部分的OOM问题，完全解决OOM版本会等稳定后再开放，敬请理解。目前依赖POI版本1.1.2-beat1

