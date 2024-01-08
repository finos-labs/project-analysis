#建议分页封装一下

Owner: elunez

Repo: eladmin

Labels: 

## zenghuabin (22 Jan 2019)

建议使用原生sql，分页一般要很多条件，使用JPA的很麻烦，建议进行封装一下

## zenghuabin (22 Jan 2019)

原生sql查询建议也一起封装一个，使用@Query 对条件比较多的那种不好用

## elunez (22 Jan 2019)

使用原生的sql，就失去了使用jpa的意义了，多条件分页与多表查询目前系统都是用Specification实现的，自行扩展即可，可学习下：Jpa的Specification查询

## zenghuabin (22 Jan 2019)

Jpa的Specification  用这玩意对那种复杂的查询，真的是很难受的，在sql里写好的sql,也没法直接copy过去，简单查询用jpa,复杂的用原生的，建议建议

