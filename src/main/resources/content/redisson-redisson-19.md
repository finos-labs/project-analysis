#Android Support

Owner: redisson

Repo: redisson

Labels: 

## tigerclawn82 (23 May 2014)

Hi,
   I want to use this inside Android, is it supported?

Thanks


## doggie1989 (23 May 2014)

Hi, 
I think it supports,but too heavyweight.
what is your user case?

Thanks


## mrniko (28 May 2014)

It's not tested, but i guess should work. Reopen this issue if I mistaken


## dzx1994 (20 Jan 2017)

I used it inside Android，but when Compile，there is a erro message ：
Error:Error converting bytecode to dex:
Cause: Dex cannot parse version 52 byte code.
This is caused by library dependencies that have been compiled using Java 8 or above.
If you are using the 'java' gradle plugin in a library submodule add 
targetCompatibility = '1.7'
sourceCompatibility = '1.7'
to that submodule's build.gradle file.

## mrniko (20 Jan 2017)

@dzx1994 which version have you used?

## dzx1994 (20 Jan 2017)

 I used the lastes version，and i used JDK 1.8。

## mrniko (20 Jan 2017)

@dzx1994 Please try to use 2.7.3 version for Android, because only 2.x.x line Android compatible.

## dzx1994 (20 Jan 2017)

OK，thanks，i will try it next tomorrow。

