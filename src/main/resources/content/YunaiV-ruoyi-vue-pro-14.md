#mapstruct convert 部分list对象转换缺少单个对象的映射规则

Owner: YunaiV

Repo: ruoyi-vue-pro

Labels: question 

## lanemy (13 Apr 2021)

艿艿, 把代码拉下来之后编译报错, 发现部分mapstruct convert内缺少单个对象的映射规则, 是不是有代码没有合并进分支?


`[ERROR] /Users/xxx/github/ruoyi-vue-pro/src/main/java/cn/iocoder/dashboard/modules/system/convert/logger/SysLoginLogConvert.java:[25,30] No target bean properties found: can't map Collection element "SysLoginLogDO sysLoginLogDO" to "SysLoginLogExcelVO sysLoginLogExcelVO". Consider to declare/implement a mapping method: "SysLoginLogExcelVO map(SysLoginLogDO value)".
[ERROR] /Users/xxx/github/ruoyi-vue-pro/src/main/java/cn/iocoder/dashboard/modules/system/convert/dict/SysDictTypeConvert.java:[27,35] No target bean properties found: can't map Collection element "SysDictTypeDO sysDictTypeDO" to "SysDictTypeSimpleRespVO sysDictTypeSimpleRespVO". Consider to declare/implement a mapping method: "SysDictTypeSimpleRespVO map(SysDictTypeDO value)".
[ERROR] /Users/xxx/github/ruoyi-vue-pro/src/main/java/cn/iocoder/dashboard/modules/system/convert/auth/SysAuthConvert.java:[32,23] No property named "updateTime" exists in source parameter(s). Did you mean "null"?
[ERROR] /Users/xxx/github/ruoyi-vue-pro/src/main/java/cn/iocoder/dashboard/modules/infra/convert/logger/InfApiAccessLogConvert.java:[31,34] No target bean properties found: can't map Collection element "InfApiAccessLogDO infApiAccessLogDO" to "InfApiAccessLogExcelVO infApiAccessLogExcelVO". Consider to declare/implement a mapping method: "InfApiAccessLogExcelVO map(InfApiAccessLogDO value)".
[ERROR] /Users/xxx/github/ruoyi-vue-pro/src/main/java/cn/iocoder/dashboard/modules/infra/convert/job/InfJobConvert.java:[34,25] No target bean properties found: can't map Collection element "InfJobDO infJobDO" to "InfJobExcelVO infJobExcelVO". Consider to declare/implement a mapping method: "InfJobExcelVO map(InfJobDO value)".`






## YunaiV (13 Apr 2021)

我本地跑了下，是 OK 的。
你的 IDEA 版本是多少呀？

## lanemy (14 Apr 2021)

> 我本地跑了下，是 OK 的。
> 你的 IDEA 版本是多少呀？

2020.3  跑的时候lombok版本过低, 升到了1.18.20

## YunaiV (14 Apr 2021)

> > 我本地跑了下，是 OK 的。
> > 你的 IDEA 版本是多少呀？
> 
> 2020.3 跑的时候lombok版本过低, 升到了1.18.20

噢的，是要把项目里的 lombok 依赖的版本，升级到 1.18.20 么？

## lanemy (15 Apr 2021)

> > > 我本地跑了下，是 OK 的。
> > > 你的 IDEA 版本是多少呀？
> > 
> > 
> > 2020.3 跑的时候lombok版本过低, 升到了1.18.20
> 
> 噢的，是要把项目里的 lombok 依赖的版本，升级到 1.18.20 么？

是的, 应该是lombok和idea兼容有点小问题,我再研究一下~

另外尝试了一下在虚机上编译代码, 可能是因为JDK11的缘故, 会报
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.0:compile (default-compile) on project dashboard: Fatal error compiling: java.lang.ExceptionInInitializerError: com.sun.tools.javac.code.TypeTags -> [Help 1]

我估计我本地也是有JDK版本的影响因素在里面. 我搞定了再来报告哈.

## lanemy (15 Apr 2021)

来报告一下, 找到原因了, lombok 1.18.12 可以正常编译, 1.18.14以上都会出现问题, 需在annotationProcessorPaths中增加
```
<path>
      <groupId>org.projectlombok</groupId>
     <artifactId>lombok-mapstruct-binding</artifactId>
     <version>0.2.0</version>
</path>
```

可以配合高版本的lombok编译mapstruct内容

参考来源: https://github.com/rzwitserloot/lombok/issues/2626 

