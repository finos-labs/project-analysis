#用mac电脑新建的excel导入读取有问题

Owner: alibaba

Repo: easyexcel

Labels: 

## CodeToSurvive1 (22 Mar 2018)

数据使用mac版本的office新建的excel，读取有问题，拿到windows系统也读取有问题。用windows系统创建的excel，在mac上读取无问题。

[导入数据.xlsx](https://github.com/alibaba/easyexcel/files/1836519/default.xlsx)

```
15:35:09.799 [main] INFO cn.com.service.ExcelService - 当前sheet:1 当前行：0 data:[姓名, xing'ming, 性别, ingbie, 职能, zhi'nneg, 手机, null, null, null, null, null, null, null, null, null, null, null, null, null]
15:35:09.806 [main] INFO cn.com.service.ExcelService - 当前sheet:1 当前行：1 data:[出生日期, shou'ji, 邮箱, you'xiang, chu'shegn'ri'qi, 固定电话, gu'ding'dian'hua, null, null, null, null, null, null, null, null, null, null, null, null, null]
15:35:09.807 [main] INFO cn.service.ExcelService - 当前sheet:1 当前行：2 data:[null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null]


```

## lurunze (15 Jul 2018)

我也遇到了同样的问题。

## zhangyb0404 (06 Aug 2018)

你们是怎么解决的？

## hiliushuo (09 Oct 2018)

同遇到了这个问题，将文件转换为 xlx 格式就没有问题了。

## hiliushuo (09 Oct 2018)

@jipengfei-jpf   求围观

## SwallowGG (05 Nov 2018)

最新版本1.1.0

