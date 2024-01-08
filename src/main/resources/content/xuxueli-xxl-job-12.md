#log日志问题

Owner: xuxueli

Repo: xxl-job

Labels: 

## longhuasishen (31 Aug 2016)

改造过xxl-job-admin之后，集成了公司的用户单点登录系统，引用公司内部的jar包中有logback依赖，导致logback和log4j冲突，以至于xxl-job-admin的log4j日志设置不起作用，debug日志一直刷
解决方案：pom.xm配置里exclude调logback的jar包依赖即可


