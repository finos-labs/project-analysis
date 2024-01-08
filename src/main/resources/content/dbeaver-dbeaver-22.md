#Oracle - Wrong multi-line comment handling when executing a query when blank lines are present in the comment

Owner: dbeaver

Repo: dbeaver

Labels: bug 

## steromano87 (26 Oct 2015)

If you write a query preceded by a multi-line comment without blank lines like this one:

``` SQL
/*
Test comment
*/
select 'test' from dual
```

and press Ctrl + Enter to execute query snippet, the query is executed correctly.

But if the comment contains one or more blank lines like this:

``` SQL
/*

Test comment
*/
select 'test' from dual
```

the query execution fails (in particular, Oracle gives a ORA-00900 error of invalid syntax).

Inspecting the _Execution Log_ tab, the query is executes as:

``` SQL
Test comment
*/
select 'test' from dual
```

so the first comment opening tag is not parsed and included in the query, hence the error.


## serge-rider (26 Oct 2015)

By default DBeaver uses blank line as a query delimiter. But it understands SQL blocks (BEGIN .. END) and executes blocks as a single query even with empty lines inside. So this issue is actually a bug, thanks for report.
Will be fixed in 3.5.2.

Temp workaround: select query text and press ctrl+enter.


