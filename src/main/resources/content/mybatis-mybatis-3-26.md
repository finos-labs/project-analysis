#UnPooledDatasource sets autocommit to false if no autocommit was specified.

Owner: mybatis

Repo: mybatis-3

Labels: bug 

## emacarron (06 Apr 2013)

UnPooledDatasource should not call setAutocommit() over a connection if no autocommit setting was provided.


