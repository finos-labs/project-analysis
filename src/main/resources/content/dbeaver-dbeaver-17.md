#Oracle - Code autocompletion does not work correctly using "With" statements

Owner: dbeaver

Repo: dbeaver

Labels: bug won't do x:oracle 

## steromano87 (23 Oct 2015)

If you try to use the code autocompletion feature in Oracle using a _With_ statement at the very beginning of the query, the code completion does not work.

When hitting Ctrl+Space no prompt is displayed.


## serge-rider (23 Oct 2015)

Could you clarify:
Does WITH breaks code completion for the rest of the query (I can't reproduce that) or just doesn't provide any completion right after the WITH clause (can't figure out what can be autocompleted there)?


## steromano87 (24 Oct 2015)

Hi Serge,

I found this issue at work. On next Monday I'll give you detailed information about this issue.

If my memory does not fail, I found the bug when trying to autocomplete column names in the query _right after_ the with statement.


## steromano87 (26 Oct 2015)

Hi Serge,

I managed to reproduce the issue on our Oracle Database.

This is the query:

``` SQL
with test as (
    select
        *
    from XXX.XXX
)
select
    *
from YYY.YYY
```

If you delete the asterisk in the query after the _with_ statement (the one which refers to the YYY.YYY table) and start typing the name of a column of the YYY table, when you hit Ctrl + Space to autocomplete it no completion popup is displayed.

If you press Ctrl + Space without typing a column name, the generic function completion dialog appears (i.e. the one with a list of available functions: ABS, ABSOLUTE, etc).

The autocompletion works normally inside the _with_ statement.


## serge-rider (09 Dec 2015)

I'm afraid that this issue can't be fixed until DBeaver will be able to make semantical SQL analysis. Currently I use regexps to extract table/column references and table reference is ambiguous for such algorithm.

Workaround: use table aliases:

```
with test as (
    select
        *
    from XXX.XXX x
)
select
    y.*
from YYY.YYY y
```

Autocompletion works fine when you type "y." and press Ctrl+Space.

Semantic analysis is a big task, hopefully I'll find time for it in the next year.


