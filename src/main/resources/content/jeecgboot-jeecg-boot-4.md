#代码生成主键能支持自增ID

Owner: jeecgboot

Repo: jeecg-boot

Labels: 

## theodo123 (28 Feb 2019)

//表主键策略（目前只支持UUID）
  table.setPrimaryKeyPolicy("uuid");
代码生成主键能支持自增ID就好了，这个都应该有需求吧。@TableId(value = "id", type = IdType.AUTO)

## zhangdaiscott (01 Mar 2019)

可以自己改，官方目前只支持UUID，更方便各种数据库迁移问题

