#LoggingRepository sql需要优化一下

Owner: elunez

Repo: eladmin

Labels: 

## alexyangbjb (02 Jan 2019)

mysql5.7版本以上可能会报only_full_group_by的错误。

select count(*) FROM (select * FROM log where createTime between ?1 and ?2 GROUP BY requestIp) as s

建议优化为

select count(*) FROM (select requestIp FROM log where createTime between ?1 and ?2 GROUP BY requestIp) as s

## elunez (06 Jan 2019)

已修改

