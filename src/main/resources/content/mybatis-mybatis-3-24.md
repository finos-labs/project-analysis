#Performance optimization on lang=raw statements.

Owner: mybatis

Repo: mybatis-3

Labels: enhancement 

## harawata (23 Mar 2013)

When 'raw' language is specified, the statement should be parsed only once to provide better performance. Although the main target of this change is batch operations, non-batch operations may get slight performance improvement as well.


