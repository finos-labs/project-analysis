#按照readme不能成功编译

Owner: apache

Repo: dubbo

Labels: 

## teager (18 Feb 2014)

在执行mvn eclipse：eclipse 命令的时候很多包下载不到
com.alibaba:hessian-lite:jar:3.2.1-fixed-2
 com.alibaba:fastjson:jar:1.1.8
.....
http://code.alibabatech.com 网站关闭了，什么时候会有更新。


## lishunli (23 Feb 2014)

前期的一些依赖包，下载解压到本地maven仓库（一般在 ~.m2\repository\com\alibaba）
http://usc.googlecode.com/svn/files/package/alibaba-dubbo-dependency.zip


## zhufeizzz (24 Feb 2014)

还是不行，mvn里面依赖的地址都是你们的内部仓库地址，能不能把依赖文件都转移到中央仓库


## zhufeizzz (24 Feb 2014)

或者能不能把目前代码的构建结果包上传一下


## beiwei30 (12 Jun 2016)

not an issue any longer on master branch


## zhuoyikang (05 Jul 2016)

最新的tag dubbo-2.5.3 又访问
to {}->http://code.alibabatech.com:80: SOCKS: Network unreachable
了。


## zhuoyikang (05 Jul 2016)

神奇的切换到master又成功了


