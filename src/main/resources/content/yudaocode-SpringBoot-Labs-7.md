#定时任务 xxl-job 的配置不对

Owner: yudaocode

Repo: SpringBoot-Labs

Labels: bug 

## yuyong725 (11 Jan 2020)

问题如：https://github.com/xuxueli/xxl-job/issues/1339 ，2.1.1的版本要注释掉bean的描述，不然启动会报错`java.net.BindException: Address already in use`，芋艿细心点可好
```
@Bean //(initMethod = "start", destroyMethod = "destroy")
    public XxlJobSpringExecutor xxlJobExecutor() {
        // 创建 XxlJobSpringExecutor 执行器
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppName(appName);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        // 返回
        return xxlJobSpringExecutor;
    }
```

## YunaiV (11 Jan 2020)

好滴。fix 了。

不影响跑。哈哈哈，我直接贴了以前 onemall  的配置了。

## JeremyJK (12 Mar 2020)

这是因为第一次注册过，而且以后不改配置信息的前提。

