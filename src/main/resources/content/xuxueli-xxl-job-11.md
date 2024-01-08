#执行任务回调的时候报StringIndexOutOfBoundsException异常

Owner: xuxueli

Repo: xxl-job

Labels: 

## Lid23 (25 Aug 2016)

java.lang.StringIndexOutOfBoundsException: String index out of range: 64
    at java.lang.String.<init>(Unknown Source)
    at com.xxl.job.core.util.ByteHexConverter.hex2Byte(ByteHexConverter.java:30)
    at com.xxl.job.core.util.XxlJobNetCommUtil.parseHexJson2Obj(XxlJobNetCommUtil.java:53)
    at com.xxl.job.core.util.XxlJobNetCommUtil.postHex(XxlJobNetCommUtil.java:113)
    at com.xxl.job.admin.core.jobbean.RemoteHttpJobBean.failoverTrigger(RemoteHttpJobBean.java:126)
    at com.xxl.job.admin.core.jobbean.RemoteHttpJobBean.executeInternal(RemoteHttpJobBean.java:61)
    at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:114)
    at org.quartz.core.JobRunShell.run(JobRunShell.java:202)
    at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)

// i do not know why
 responseHex = responseHex.replace("\n", "");               也需要过滤下 “\r”


## xuxueli (25 Aug 2016)

这个问题已经解决，请拉最新代码确认下。


