#代码生成 vue前端文件，列表缺失表字段

Owner: jeecgboot

Repo: jeecg-boot

Labels: 

## cnn007 (15 Mar 2019)

代码生成
1.生成的后端代码没有问题，可以正常使用。
2.生成的前端代码 vue ，会有字段省略生成吗？
发现列表字段不全，会缺失字段，
3.使用filter 的时候，必须是create_time,否则报错，
这个可能表设计要遵循规范。

## zhangdaiscott (16 Mar 2019)

create_time 是表设计规范要求，你是想怎么用？

## cnn007 (18 Mar 2019)

create_time,我知道了。
  
第2条.生成的前端代码 vue ，会有表字段省略生成吗？
发现列表字段不全，缺失字段，

## zhangdaiscott (20 Mar 2019)

会有，创建人，创建时间，修改人，修改时间，默认不生成

