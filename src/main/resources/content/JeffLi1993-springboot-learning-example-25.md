#springboot整合mybatis中文搜索无法搜索到数据

Owner: JeffLi1993

Repo: springboot-learning-example

Labels: 

## lomoye (28 Sept 2017)

如果搜索的字段传的是英文的没问题，可以查到数据，如果是中文就无法搜索到数据。用的是案例中的City对象，按cityName查询。

## lomoye (28 Sept 2017)

·解决了。是数据库spring.datasource.url里面的&被我写成转移符的模式了。不能这么写。

