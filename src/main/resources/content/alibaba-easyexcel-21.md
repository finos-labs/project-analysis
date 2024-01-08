#导入报数组越界

Owner: alibaba

Repo: easyexcel

Labels: 

## yyn0210 (07 May 2018)

`
com.alibaba.excel.read.exception.ExcelAnalysisException: com.alibaba.excel.read.exception.ExcelAnalysisException: java.lang.IndexOutOfBoundsException: Index: 882, Size: 882
	at com.alibaba.excel.read.SaxAnalyserV07.execute(SaxAnalyserV07.java:85)
	at com.alibaba.excel.read.ExcelAnalyserImpl.analysis(ExcelAnalyserImpl.java:51)
	at com.alibaba.excel.read.ExcelAnalyserImpl.analysis(ExcelAnalyserImpl.java:45)
	at com.alibaba.excel.ExcelReader.read(ExcelReader.java:64)
	at read.v07.Read2007Xlsx.withModelMultipleSheet(Read2007Xlsx.java:151)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
	at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
	at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)
Caused by: com.alibaba.excel.read.exception.ExcelAnalysisException: java.lang.IndexOutOfBoundsException: Index: 882, Size: 882
	at com.alibaba.excel.read.SaxAnalyserV07.parseXmlSource(SaxAnalyserV07.java:117)
	at com.alibaba.excel.read.SaxAnalyserV07.execute(SaxAnalyserV07.java:73)
	... 26 more
Caused by: java.lang.IndexOutOfBoundsException: Index: 882, Size: 882
	at java.util.LinkedList.checkElementIndex(LinkedList.java:555)
	at java.util.LinkedList.get(LinkedList.java:476)
	at com.alibaba.excel.read.v07.RowHandler.endCellValue(RowHandler.java:111)
	at com.alibaba.excel.read.v07.RowHandler.endElement(RowHandler.java:131)
	at com.sun.org.apache.xerces.internal.parsers.AbstractSAXParser.endElement(AbstractSAXParser.java:609)
	at com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.scanEndElement(XMLDocumentFragmentScannerImpl.java:1782)
	at com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl$FragmentContentDriver.next(XMLDocumentFragmentScannerImpl.java:2967)
	at com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl.next(XMLDocumentScannerImpl.java:602)
	at com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.scanDocument(XMLDocumentFragmentScannerImpl.java:505)
	at com.sun.org.apache.xerces.internal.parsers.XML11Configuration.parse(XML11Configuration.java:841)
	at com.sun.org.apache.xerces.internal.parsers.XML11Configuration.parse(XML11Configuration.java:770)
	at com.sun.org.apache.xerces.internal.parsers.XMLParser.parse(XMLParser.java:141)
	at com.sun.org.apache.xerces.internal.parsers.AbstractSAXParser.parse(AbstractSAXParser.java:1213)
	at com.sun.org.apache.xerces.internal.jaxp.SAXParserImpl$JAXPSAXParser.parse(SAXParserImpl.java:643)
	at com.alibaba.excel.read.v07.XmlParserFactory.parse(XmlParserFactory.java:28)
	at com.alibaba.excel.read.SaxAnalyserV07.parseXmlSource(SaxAnalyserV07.java:109)
	... 27 more
`

## ccc1994 (16 May 2018)

我也遇到了类似的问题,是SaxAnalyserV07读取sharedString.xml的时候会忽略空字符串对应的tag,导致这个异常.我是这么解决的,暂时没找到用sax解析的更好的方法,希望作者能解决一下这个问题 @jipengfei-jpf 
```
private void initSharedStringsTable() throws IOException, ParserConfigurationException, SAXException {

        InputStream inputStream = new FileInputStream(this.sharedStringXMLFilePath);
        //this.sharedStringsTable = new SharedStringsTable();
        //this.sharedStringsTable.readFrom(inputStream);

        /*
         * sharedString.xml用空tag来表示空字符串,但是sax的handler.characters方法会跳过空的元素,
         * 所以当发现有跳过某个element时,认为是空字符串(不完全确定),向sharedStringList中添加一个空字符串
         */
        XmlParserFactory.parse(inputStream, new DefaultHandler() {

            int lastElementPosition = -1;

            int lastHandledElementPosition = -1;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                if (hasSkippedEmptySharedString()) {
                    sharedStringList.add("");
                }
                if ("t".equals(qName)) {
                    lastElementPosition++;
                }
            }

            private boolean hasSkippedEmptySharedString() {
                return lastElementPosition > lastHandledElementPosition;
            }

            @Override
            public void characters(char[] ch, int start, int length) {
                sharedStringList.add(new String(ch, start, length));
                lastHandledElementPosition++;
            }

        });
        inputStream.close();
    }
```

## SwallowGG (13 Nov 2018)

由于1.0.*版本核心考虑的是怎么解决读excel内存溢出问题，自己重写了读excel的POI的底层，写仍旧使用POI。可以从根本解决OOM问题。但是由于自己对excel的格式认识不全面，有很多坑产生了很多BUG。最新版本以POI为底层。最新版：1.1.2-beat1，虽然不能完全解决OOM但可以解决大部分的OOM问题，完全解决OOM版本会等稳定后再开放，敬请理解。目前依赖POI版本1.1.2-beat1

