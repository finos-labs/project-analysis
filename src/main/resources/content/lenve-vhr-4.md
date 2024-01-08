#关于loadUserByUsername方法实现

Owner: lenve

Repo: vhr

Labels: question 

## china-fengguan (15 Jan 2018)

loadUserByUsername(String s)的方法实现好像只有根据userName进行查找，password并没有参与，这样会不会有问题？
select * from hr WHERE username=#{username};

## lenve (15 Jan 2018)

@china-fengguan 
密码比对操作由SpringSecurity完成，比对失败会抛出BadCredentialsException异常，处理该异常即可。
可以打断点追踪一下就知道了

