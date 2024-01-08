#导入tables_xxl_job.sql 表时提示错误

Owner: xuxueli

Repo: xxl-job

Labels: 

## R1hug0 (06 Jun 2016)

ERROR 1071 (42000) at line 2: Specified key was too long; max key length is 767 bytes
mysql 版本5.5.49  ，默认Utf-8字符集


## xuxueli (06 Jun 2016)

可以参考这篇文档：http://blog.csdn.net/cindy9902/article/details/6215769/      


## R1hug0 (06 Jun 2016)

是因为选择成了mb4 utf-8，
问下pom.xml下打包为啥resources的文件没有拷贝到打包后的工程里面。


## xuxueli (06 Jun 2016)

具体哪个子项目的？你是否更改了打包规则。


## R1hug0 (06 Jun 2016)

没有，我就直接用mvn install 打的包，但完成后看admin  和exec项目下的war包都没有把resources的文件拷进去


## xuxueli (06 Jun 2016)

我也是用mvn install打包，但是并没有遇到你所说的问题。你可以到群里反馈下：技术交流群(仅作技术交流)：367260654  


## R1hug0 (06 Jun 2016)

xml等文件都拷贝到了 target/classes/下而不是target/xxl-job-admin-1.3.1-SNAPSHOT/WEB-INF/classes/
，不知道哪里要不要设置，奇怪了


## xuxueli (06 Jun 2016)

确实奇怪，maven build我都是走默认配置。
你和项目pom中build涉及到的几个插件配置成和你本机正常的其他项目一致试试。


## R1hug0 (06 Jun 2016)

找到问题了，具本是不是版本有关不知道，因为用默认的POM文件不行，
在pom.xml文件里org.apache.maven.plugins引入了

 <!webResources>
     <!resource>
         <!directory>src/main/resources</directory>
        <!targetPath>WEB-INF/classes</targetPath>
         <!filtering>true</filtering>
     <!/resource>
 <!/webResources>


## xuxueli (06 Jun 2016)

估计pom的打包规则被覆盖了，解决问题了就好啊。
接下来使用过程中遇到问题，可以到群里讨论啊，里面很活跃。
技术交流群(仅作技术交流)：367260654


