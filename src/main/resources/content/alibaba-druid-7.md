#日志输出的类名不对

Owner: alibaba

Repo: druid

Labels: 

## fivesmallq (02 Aug 2012)

故意设置错误的数据库
2012-08-02 17:55:29  [main:SystemContext:0 ] - [ INFO ]  init SystemContext...
2012-08-02 17:55:29  [main:JakartaCommonsLoggingImpl:640 ] - [ ERROR ]  init datasource error
com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown database 'novasecd'
    at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
    at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:39)
    at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:27)
    at java.lang.reflect.Constructor.newInstance(Constructor.java:513)
    at com.mysql.jdbc.Util.handleNewInstance(Util.java:409)
    at com.mysql.jdbc.Util.getInstance(Util.java:384)
    at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:1054)
    at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3566)
    at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3498)
    at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:919)
    at com.mysql.jdbc.MysqlIO.secureAuth411(MysqlIO.java:4004)
    at com.mysql.jdbc.MysqlIO.doHandshake(MysqlIO.java:1284)
    at com.mysql.jdbc.ConnectionImpl.connectOneTryOnly(ConnectionImpl.java:2312)
    at com.mysql.jdbc.ConnectionImpl.createNewIO(ConnectionImpl.java:2122)
    at com.mysql.jdbc.ConnectionImpl.<init>(ConnectionImpl.java:774)
    at com.mysql.jdbc.JDBC4Connection.<init>(JDBC4Connection.java:49)
    at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
    at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:39)
    at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:27)
    at java.lang.reflect.Constructor.newInstance(Constructor.java:513)
    at com.mysql.jdbc.Util.handleNewInstance(Util.java:409)
    at com.mysql.jdbc.ConnectionImpl.getInstance(ConnectionImpl.java:375)
    at com.mysql.jdbc.NonRegisteringDriver.connect(NonRegisteringDriver.java:289)
    at com.alibaba.druid.pool.DruidAbstractDataSource$DruidPoolConnectionFactory.createConnection(DruidAbstractDataSource.java:1276)

这里
2012-08-02 17:55:29  [main:JakartaCommonsLoggingImpl:640 ] - [ ERROR ]  init datasource error
显示的类名应该是DruidPoolConnectionFactory吧。


## wendal (03 Aug 2012)

log打印的是语句所在的类, 所以没有错的


## fivesmallq (03 Aug 2012)

main:JakartaCommonsLoggingImpl
这个是日志输出的类吗？应该是在DruidPoolConnectionFactory调用的log.error啊。类名应该是DruidPoolConnectionFactory，怎么会是当前选择的日志框架的实现呢，我用commons-logging，是没问题的
是这样的。如果我现在有一个类，jdbcutils，获取了log对象，那我现在log.warn("xxx");类名应该是JDBCUtils吧？
结果成了Log4jImpl或者JakartaCommonsLoggingImpl，不对吧。。


## wendal (03 Aug 2012)

看了一下代码,的确是写错了


## wenshao (24 Aug 2012)

这个是为了不依赖Log库而作的去依赖处理，类似ibatis的做法，你有什么好建议么？


## wendal (24 Aug 2012)

实现类里面, 调用的时候,附上参数就可以的
可以参考一下nutz的写法
https://github.com/nutzam/nutz/blob/master/src/org/nutz/log/impl/Log4jLogAdapter.java


