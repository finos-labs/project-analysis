#Oracle - Ctrl + left click on table name to open information panels does not work if table name is written in lowercase

Owner: dbeaver

Repo: dbeaver

Labels: bug x:oracle can't reproduce duplicate 

## steromano87 (24 Oct 2015)

Prerequisite: you have an Oracle 10g database with schema and table name(s) declared in uppercase.

If you write a query using that table with the schema/table name in lowercase, the query will work (Oracle is case insensitive when declaring schemas, tables and so on), but if you keep the Ctrl key pressed and try to click on table name to open information panel, the table name does not become a link and cannot be clicked.

If you write schema and table name in uppercase, the Ctrl + Left click does work.


## serge-rider (25 Oct 2015)

Can't reproduce.
DBeaver converts metadata object names into database storage case before search.
Maybe your table name was quoted (i.e. stored in "mixed case")? Then you have to specify it's name exactly as it was specified at creation time.


## steromano87 (26 Oct 2015)

Hi Serge,

the tables are created using uppercase names.

Analyzing this issue deeper, I found a sort of non-consistence when handling table names for information panel opening:
- If you write a table name (without schema) in _UPPPERCASE_ and Ctrl + Click it, the panel opens.
- If you write a table name (without schema) in _lowercase_ and Ctrl + Click it, the panel opens.
- If you write a table name (with schema) in _UPPPERCASE_ and Ctrl + Click it, the panel opens.
- If you write a table name (with schema) in _lowercase_, if you keep the Ctrl key pressed and hover the mouse on the table name, it does not become a link.


## serge-rider (26 Oct 2015)

Yep. The bug is in the schema name case conversion. 
Will be fixed in 3.5.2.


