#Excel 07 读取结束时临时目录没有删除

Owner: alibaba

Repo: easyexcel

Labels: 

## kanjianhaian (21 Mar 2018)

ExcelReader调用finish时，删除临时文件成功，临时目录也应该需要删除吧，
具体代码如下

com.alibaba.excel.util.FileUtil

`
public static void deletefile(String delpath) {
     
        File file = new File(delpath);

        if (!file.isDirectory()) {

            file.delete();

        } else if (file.isDirectory()) {

            String[] filelist = file.list();

            for (int i = 0; i < filelist.length; i++) {

                File delfile = new File(delpath + File.separator + filelist[i]);

                if (!delfile.isDirectory()) {

                    delfile.delete();

                } else if (delfile.isDirectory()) {

                    deletefile(delpath + File.separator + filelist[i]);

                }

            }
         // 这里是否需要加一个
            file.delete
        }

    }
`

## xestyle (11 Apr 2018)

不仅仅目录没删除，文件也是没有删除的；
一个50m，xlsx文件，生成临时文件接近300M；主要是worksheets/sheet1.xml，类似文件太大了；

昨天跑了一下，结果几个JUnit跑下来，临时文件快几个G；

## SwallowGG (30 Jul 2018)

1.0.3已修复

