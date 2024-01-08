#Tips: Cannot run without an instance id error log

Owner: xuxueli

Repo: xxl-job

Labels: experience sharing 

## R1hug0 (27 Oct 2016)

org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'quartzScheduler' defined in URL [jar:file:/data/WEB-INF/lib/xxl-job-admin-1.3.1-SNAPSHOT.jar!/applicationcontext-xxl-job.xml]: Invocation of init method failed; nested exception is java.lang.IllegalStateException: Cannot run without an instance id.
Caused by: java.lang.IllegalStateException: Cannot run without an instance id.
如果日志中有这些的错误提示是因为程序运行获取hostname无法解析造成的,把对应的IP加入DNS或者hosts中就可以了.算是踩了一小坑


## xuxueli (28 Oct 2016)

赞一个 ：）


## yzsunlight (22 Oct 2019)

> Cannot run without an instance id

具体怎么配置的，目前我也碰到了这个问题

