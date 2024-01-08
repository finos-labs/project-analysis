#解析07版excels文件时，使用getLocalName无法获得实际名称，建议getQName双保险

Owner: alibaba

Repo: easyexcel

Labels: 

## xestyle (11 Apr 2018)

SaxAnalyserV07.java  行，代码行170左右；
if (qName.toLowerCase(Locale.US).equals("sheet")) {
                    String name = null;
                    int id = 0;
                    for (int i = 0; i < attrs.getLength(); i++) {
                        _if (attrs.getLocalName(i).toLowerCase(Locale.US).equals("name")) {_// 此处LocalName.执行失效、getQName 生效；
                            name = attrs.getValue(i);
                        } else if (attrs.getLocalName(i).toLowerCase(Locale.US).equals("r:id")) {
                            id = Integer.parseInt(attrs.getValue(i).replaceAll("rId", ""));
                            try {
                                InputStream inputStream = new FileInputStream(XMLTempFile.getSheetFilePath(path, id));
                                sheetSourceList.add(new SheetSource(id, name, inputStream));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }



## SwallowGG (11 Apr 2018)

感谢建议，下个版本优化

## SwallowGG (13 Nov 2018)

由于1.0.*版本核心考虑的是怎么解决读excel内存溢出问题，自己重写了读excel的POI的底层，写仍旧使用POI。可以从根本解决OOM问题。但是由于自己对excel的格式认识不全面，有很多坑产生了很多BUG。最新版本以POI为底层。最新版：1.1.2-beat1，虽然不能完全解决OOM但可以解决大部分的OOM问题，完全解决OOM版本会等稳定后再开放，敬请理解。目前依赖POI版本1.1.2-beat1

