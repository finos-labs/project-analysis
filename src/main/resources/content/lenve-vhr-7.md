#IDEA引入工程启动不成功

Owner: lenve

Repo: vhr

Labels: duplicate question 

## JanusHuang (17 Jan 2018)

用IDEA引入工程时启动不成功, 原因是hrserver的pom里定义了resources结点而没有把src/main/resources加在resources结点内, 需手动把resources目录Mark as resource root

建议在resources结点下增加<resource><directory>src/main/resources</directory></resource>

## lenve (17 Jan 2018)

手动修改是对的

## lenve (17 Jan 2018)

嗯，建议可以有

