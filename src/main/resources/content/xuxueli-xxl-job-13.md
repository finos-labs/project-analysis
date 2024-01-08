#集成mycat遇到的问题

Owner: xuxueli

Repo: xxl-job

Labels: 

## longhuasishen (31 Aug 2016)

xxl-job数据源使用mycat的时候会出现任务在暂停之后，在org.quartz.jobStore.misfireThreshold: 60000 这个时间过后，会被自动恢复到normal状态，直连数据库不会出现以上问题，谨记！


