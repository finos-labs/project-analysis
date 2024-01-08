#3.2.x zip bundle includes the .tar.gz file

Owner: mybatis

Repo: mybatis-3

Labels: bug 

## emacarron (11 Mar 2013)

Seems that the .zip bundle deployed to maven is too big compared to the .tar.gz

http://repo1.maven.org/maven2/org/mybatis/mybatis/3.2.1/

Having a look inside it you will see that the .tar.gz has been included in the file.


## simonetripodi (11 Mar 2013)

gosh!!! that means I have to update the @base-bundle-descriptor first, release it then update the parent and release it... then, reference it in all modules...

in few words: it is not a @mybatis-3 specific issue, is an issue that should affect all modules - poor me...


## emacarron (11 Mar 2013)

Take it easy Simo! ;)


## simonetripodi (31 Mar 2013)

Not a core issue - moving that issue in base-bundle-descriptor


