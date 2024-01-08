#有没有私有方法啊

Owner: didi

Repo: DoKit

Labels: 

## feilongOnMoudu (12 Nov 2018)

大佬，有没有私有方法啊，上架不存在风险吧？

## yixiangboy (12 Nov 2018)

目前没有使用私有方法，但是这个功能不建议上架。用于开发测试阶段。
建议功能包裹在debug宏中使用，
    pod 'DoraemonKit',:configurations => ['Debug']


## feilongOnMoudu (15 Nov 2018)

 我弄了两个target  这样写  one1中会不会打包进去啊  pod中 one1 我也没有加入这个框架

#if One1 //One1Target
//生产环境不加DoraemonKit框架
#elif One2 //One2Target
#if kReleaseModal
//测试环境Release环境不加DoraemonKit框架
#else
[[DoraemonManager shareInstance] install];
#endif
#endif

这样的话 one1 打的生产环境包中会不会有这个框架呢？ 

