#沙盒浏览器中文件大小显示不正确

Owner: didi

Repo: DoKit

Labels: 

## toss156 (17 Dec 2018)

fileSize 定义成了指针
```
@interface DoraemonUtil : NSObject

@property (nonatomic, assign) NSInteger *fileSize;
```

## yixiangboy (17 Dec 2018)

谢谢 已经看到你的pr了。赞

## toss156 (18 Dec 2018)

fixed  #15 

