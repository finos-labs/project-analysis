#ResultSet problem.

Owner: mybatis

Repo: mybatis-3

Labels: bug wontfix 

## kam09 (22 Mar 2013)

Hi,
I see there is problem with receiveing null value in stored procedure from parameter output: "c_data OUT sys_refcursor". When sys_refcursor is not opened we will get an error:
at apache.ibatis.executor.resultset.FastResultSetHandler.handleRefCursorOutputParameter(FastResultSetHandler.java:122)

I think that similar problem was here : https://issues.apache.org/jira/browse/IBATIS-220?page=comments#action_12357908


## emacarron (28 Mar 2013)

Hi.

Looks a lot like https://code.google.com/p/mybatis/issues/detail?id=290

We did not find a valid solution for this. Can you work in a patch?


