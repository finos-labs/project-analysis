#mysql问题

Owner: halo-dev

Repo: halo

Labels: 

## seeseasay (05 May 2018)

RROR 13573 --- [eate-1294605731] com.alibaba.druid.pool.DruidDataSource   : create connection SQLException, url: jdbc:mysql://127.0.0.1:3306/halodb?characterEncoding=utf8&useSSL=false, errorCode 1045, state 28000

用的是mariadb这里需要做什么配置吗

## ruibaby (05 May 2018)

mariadb? 抱歉！没有接触过，我先去试试。

## ruibaby (05 May 2018)

刚刚查了下，mariadb虽然是mysql的变种，兼容mysql，但是其驱动包不是一样的，我在pom.xml里面只添加了h2和mysql的驱动包，所以你非要使用mariadb的话，需要添加依赖。

## seeseasay (05 May 2018)

好的

