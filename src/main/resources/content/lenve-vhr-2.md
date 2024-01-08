#Cannot determine embedded database driver class for database type NONE

Owner: lenve

Repo: vhr

Labels: question 

## mlick (10 Jan 2018)

`Error starting ApplicationContext. To display the auto-configuration report re-run your application with 'debug' enabled.
2018-01-10 09:14:51.962 ERROR 49060 --- [           main] o.s.b.d.LoggingFailureAnalysisReporter   : 

***************************
APPLICATION FAILED TO START
***************************

Description:

Cannot determine embedded database driver class for database type NONE

Action:

If you want an embedded database please put a supported one on the classpath. If you have database settings to be loaded from a particular profile you may need to active it (no profiles are currently active).

`
楼主报了这个错，没有run起来

## lenve (10 Jan 2018)

@mlick 参考这个https://github.com/lenve/vhr/issues/1

## mlick (10 Jan 2018)

![image](https://user-images.githubusercontent.com/8747500/34752285-3c9f0724-f5ec-11e7-945f-73e14467e647.png)
这个哥们是对的，解决了，多谢 @lenve 

## gallonyin (08 Aug 2018)


![](https://user-images.githubusercontent.com/8747500/34752285-3c9f0724-f5ec-11e7-945f-73e14467e647.png)
正解

