#Does not decompile apk method

Owner: skylot

Repo: jadx

Labels: 

## artenie (19 Sept 2014)

Hello, 

The method: 
api.appgamefree.ftl.Application.attachBaseContext(java.lang.String):java.lang.String
present in the apk below:
https://play.google.com/store/apps/details?id=api.appgamefree.ftl
does not decompile.

The apk uses DexProtector from http://dexprotector.com.

Thank you.

Below is the output of jadx -d:

INFO  - loading ...
DEBUG - processing threads count: 2
INFO  - processing ...
INFO  - processing class api.appgamefree.ftl.Application ...
DEBUG -  Missing block: B:24:0x03d0 in api.appgamefree.ftl.Application.attachBaseContext(java.lang.String):java.lang.String
DEBUG -  Missing block: B:26:0x03db in api.appgamefree.ftl.Application.attachBaseContext(java.lang.String):java.lang.String
DEBUG -  Missing block: B:27:0x03e2 in api.appgamefree.ftl.Application.attachBaseContext(java.lang.String):java.lang.String
DEBUG -  Missing block: B:28:0x03eb in api.appgamefree.ftl.Application.attachBaseContext(java.lang.String):java.lang.String
DEBUG -  Missing block: B:29:0x03f2 in api.appgamefree.ftl.Application.attachBaseContext(java.lang.String):java.lang.String
DEBUG -  Missing block: B:30:0x03fb in api.appgamefree.ftl.Application.attachBaseContext(java.lang.String):java.lang.String
DEBUG -  Missing block: B:31:0x0402 in api.appgamefree.ftl.Application.attachBaseContext(java.lang.String):java.lang.String
DEBUG -  Missing block: B:32:0x040b in api.appgamefree.ftl.Application.attachBaseContext(java.lang.String):java.lang.String
ERROR - Inconsistent code in method: api.appgamefree.ftl.Application.attachBaseContext(java.lang.String):java.lang.String
INFO  - done
ERROR - 1 errors occured in following nodes:
ERROR -   Method: api.appgamefree.ftl.Application.attachBaseContext(java.lang.String):java.lang.String


## skylot (20 Sept 2014)

Thanks, it is very interesting dex, especially protection )
Looks like in last [unstable](https://drone.io/github.com/skylot/jadx/files) version there are a lot of different errors:
- incorrect try/catch in loop processing
- incorrect variable names assign
- incorrect variable types detection
- maybe something else

I will try to fix these issues, however now you can use `--show-bad-code` jadx argument to view decompiled code even if it contains some errors, also `--cfg` argument will generate control-flow graphs for each method and help you to understand what code actually do.


## artenie (24 Sept 2014)

Thank you for the answer. I will check out the options :).


## asashour (26 Mar 2019)

I am afraid the APK is not found anymore.

