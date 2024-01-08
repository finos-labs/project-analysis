#自动生成的 dao 和 BaseServiceImpl 有些情况不匹配

Owner: shuzheng

Repo: zheng

Labels: 

## yangpeng366 (11 Jul 2017)

表字段主键 id（bigint 20）；自动生成的对应属性是Long;
dao方法是selectByPrimaryKey(Long id);执行servce通过Id查询的时候，传递参数int型的，然后
 BaseServiceImpl selectByPrimaryKey 里 220行 Method selectByPrimaryKey = apper.getClass().getDeclaredMethod("selectByPrimaryKey", id.getClass());报错 java.lang.NoSuchMethodError:
后来就没有用BaseServiceImpl ，跳过了这个bug

