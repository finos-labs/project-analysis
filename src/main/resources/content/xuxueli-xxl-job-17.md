#tables_xxl_job.sql文件 XXL_JOB_QRTZ_TRIGGER_REGISTRY表的update_time类型错误

Owner: xuxueli

Repo: xxl-job

Labels: 

## lucky-abel (13 Oct 2016)

XXL_JOB_QRTZ_TRIGGER_REGISTRY表的update_time 类型应该是timestamp


## xuxueli (13 Oct 2016)

你好，目前使用的 “datetime”，业务支持的。


## lucky-abel (14 Oct 2016)

嗯 昨天跑了一下你们的项目，db文件在数据库里执行有个表创建没成功 改了这个一段类型才通过了

发自我的 iPhone

> 在 2016年10月13日，20:41，雪里 notifications@github.com 写道：
> 
> 你好，目前使用的 “datetime”，业务支持的。
> 
> —
> You are receiving this because you authored the thread.
> Reply to this email directly, view it on GitHub, or mute the thread.


## xuxueli (14 Oct 2016)

你好，应该是你们DBA做了限制。


