#tables_xxl_job.sql 脚本无法执行

Owner: xuxueli

Repo: xxl-job

Labels: 

## ccjjxl (13 Jun 2016)

[SQL]
CREATE TABLE XXL_JOB_QRTZ_JOB_DETAILS
  (
    SCHED_NAME VARCHAR(120) NOT NULL,
    JOB_NAME  VARCHAR(200) NOT NULL,
    JOB_GROUP VARCHAR(200) NOT NULL,
    DESCRIPTION VARCHAR(250) NULL,
    JOB_CLASS_NAME   VARCHAR(250) NOT NULL,
    IS_DURABLE VARCHAR(1) NOT NULL,
    IS_NONCONCURRENT VARCHAR(1) NOT NULL,
    IS_UPDATE_DATA VARCHAR(1) NOT NULL,
    REQUESTS_RECOVERY VARCHAR(1) NOT NULL,
    JOB_DATA BLOB NULL,
    PRIMARY KEY (SCHED_NAME,JOB_NAME,JOB_GROUP)
);
[Err] 1071 - Specified key was too long; max key length is 1000 bytes


## xuxueli (13 Jun 2016)

修改mysql的engine 至 innodb。


## viperasi (18 Dec 2018)

后面的几个表都有`engine=InnoDB`，前面的几个没有

