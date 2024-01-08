#ConverterRegistry默认日期转换器导入两次java.sql.date没有java.util.date

Owner: dromara

Repo: hutool

Labels: bug 

## lid1987 (03 Jun 2017)

![image](https://cloud.githubusercontent.com/assets/23111804/26751493/57da0994-486d-11e7-8204-1fcc9770d70d.png)

Date.class导入的是java.sql.Date 而不是java.util.Date

## looly (04 Jun 2017)

十分感谢。确认为bug。未来3.0.7中将被修复

