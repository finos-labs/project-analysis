#easyexcel 导出的excel 导入报错

Owner: alibaba

Repo: easyexcel

Labels: 

## wxqchimei (09 Apr 2018)

用easyexcel导出的excel，再导入时报错：java.io.FileNotFoundException: D:\apache-tomcat-8.0.36\temp\easyexcel\-4014190947417093486\xl\worksheets\sheet3.xml (系统找不到指定的文件。)
明明应该是sheet1的，这里的workbook.xml里面的sheet中的r:id会变成rId3

## wxqchimei (09 Apr 2018)

xlsx有问题，xls测试是好的

## lizheng29 (04 May 2018)

我也遇到了这个问题，请问有没有解决

## jianggujin (04 May 2018)

这个问题我也发现了，在Mac环境下，用easyexcel导出的excel，再次读取就会报形如：....../sheet3.xml (No such file or directory)的错误，然后用同事电脑在win环境下生成了一个空的xlsx文件，读取就会报sharedStrings.xml (No such file or directory)错误，xls的读取正常 @jipengfei-jpf 

## jianggujin (04 May 2018)

已解决，拼接sheet路径时错误，并且sharedStrings.xml不一定有，需要做处理

## ccc1994 (15 May 2018)

@jianggujin 怎么解决的?我用1.0.1按示例写报这个错误

## jianggujin (16 May 2018)

@ccc1994 可以参考我的一篇博客：
[https://blog.csdn.net/jianggujin/article/details/80200400](https://blog.csdn.net/jianggujin/article/details/80200400)
需要重写SaxAnalyserV07类

## ccc1994 (16 May 2018)

@jianggujin 博客404了.
我的暂时是这样解决的,修改SaxAnalyserV07.java
```
if (qName.toLowerCase(Locale.US).equals("sheet")) {
                    String name = null;
                    for (int i = 0; i < attrs.getLength(); i++) {
                        if (attrs.getLocalName(i).toLowerCase(Locale.US).equals("name")) {
                            name = attrs.getValue(i);
                        } else if (attrs.getLocalName(i).toLowerCase(Locale.US).equals("sheetid")) {
                            int id = Integer.parseInt(attrs.getValue(i));
                            try {
                                InputStream inputStream = new FileInputStream(XMLTempFile.getSheetFilePath(path, id));
                                sheetSourceList.add(new SheetSource(id, name, inputStream));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
```

## jianggujin (17 May 2018)

@ccc1994 是的，要取sheetid属性进行拼接，博客404是因为后面的汉字也作为连接的部分了，已修改，另外在这个方法最下面的排序部分也要修改，否则拿到的是倒序的结果，不是正常的sheet顺序

## ccc1994 (17 May 2018)

感谢@jianggujin ,不过我看了一下,排序错误是因为`SheetSource.compareTo`方法错了,应该修改`SheetSource`.
而且你的排序写的有点问题,用减法的结果来排序可能出现int溢出(虽然id理论上应该是正值)的问题.
```
public int compareTo(@Nonnull SheetSource o) {
            if (o.id == this.id) {
                return 0;
            } else if (this.id < o.id) {
                return -1;
            } else {
                return 1;
            }
        }
```

## SwallowGG (30 Jul 2018)

1.0.3已修复

