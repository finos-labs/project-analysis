#还是构建不出来，请问能不能把构建结果二进制包上传一下

Owner: apache

Repo: dubbo

Labels: 

## zhufeizzz (04 Mar 2014)

Maven里面还是有很多你们内部地址的依赖，能否将2.5.3版本的二进制包上传一下，或者将依赖全部发布到中央仓库，麻烦了


## lishunli (13 Mar 2014)

前期的一些依赖包，下载解压到本地maven仓库（一般在 ~.m2\repository\com\alibaba）
http://usc.googlecode.com/svn/files/package/alibaba-dubbo-dependency.zip
请跳过单元测试，mvn clean install -DskipTests
完整的构建包 via http://pan.baidu.com/s/1D02SY


