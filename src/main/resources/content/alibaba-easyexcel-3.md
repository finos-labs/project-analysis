#关于Excel重复续写

Owner: alibaba

Repo: easyexcel

Labels: 

## yunshang734 (02 Mar 2018)

我看ExcelBuilderImpl.addContent()方法里面有
int rowNum = this.context.getCurrentSheet().getLastRowNum();
这么一个方法，但是我读取已经保存好的Excel，结果rowNum还是从0开始，这个地方可以帮忙看下吗？
我这边的测试方法是，对同一个Excel，执行多次输出。

## SwallowGG (05 Mar 2018)

目前支持的是在文件未关闭的情况下是可以持续读写，不用担心内存溢出问题，因为默认5000条数据是会flush到磁盘的。你所说的保存后再读写以前我没遇到过这种场景，确实没这么写过。

## SwallowGG (05 Mar 2018)

能够把你的场景，我看下合理性是否一定需要文件写已经关闭了再写呢？

## yunshang734 (05 Mar 2018)

public class LocalTest {

    public static void main(String[] args) {
        String exportFilePath = "E:\\LocalTest.xlsx";
        Integer baseIndex = 0;
        for (int index = 0; index < 3; index++) {
            doExport(exportFilePath, baseIndex);
            baseIndex = baseIndex + 10000;
        }
    }

    private static void doExport(String exportFilePath, Integer baseIndex) {
        List<Order> orderList = new ArrayList<>();
        for (int index = 0; index < 10000; index++) {
            Order order = new Order();
            order.setFOrderId("FOrderId" + index + baseIndex);
            order.setFMerchantId("FMerchantId" + index + baseIndex);
            order.setFUserName("FUserName" + index + baseIndex);
            order.setFState(new Random().nextInt(3));
            order.setFAmount(new Random().nextInt(1000000));
            order.setFRefundAmount(new Random().nextInt(1000000));
            order.setFGoodsType("FGoodsType" + index + baseIndex);
            order.setFGoodsName("FGoodsName" + index + baseIndex);
            order.setFGoodsDetail("FGoodsDetail" + index + baseIndex);
            order.setFTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            orderList.add(order);
        }
        EasyExcelUtil.export(exportFilePath, orderList, Order.class, ExcelTypeEnum.XLSX);
    }
}

public class EasyExcelUtil {

    public static <T extends BaseMeta> void export(String exportFilePath, List<T> metaList, Class clazz, ExcelTypeEnum excelTypeEnum) {
        try {
            OutputStream outputStream = new FileOutputStream(exportFilePath);
            ExcelWriter excelWriter = new ExcelWriter(outputStream, excelTypeEnum);
            Sheet sheet1 = new Sheet(1, 0, clazz);
            excelWriter.write(metaList, sheet1);
            excelWriter.finish();
        } catch (FileNotFoundException exception) {
            System.out.println("EasyExcelUtil.export(), FileNotFoundException: " + exception);
        }
    }
}

以上是我的代码，一个简单的测试DEMO，请看下。

## SwallowGG (05 Mar 2018)

OK，和我理解的是一样的，暂时不要在Excel没有写完的时候就去调finish().一个Excel写完再Finish()。内存问题不用担心。不过使用者确实可能会像你这样使用。后续版本优化这个问题

## kud1 (24 Oct 2018)

重新读取文件续写这个功能有加上吗？

