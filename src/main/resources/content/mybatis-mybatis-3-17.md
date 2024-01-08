#support for two-level model classes generation (abstract base class and stub class)

Owner: mybatis

Repo: mybatis-3

Labels: invalid 

## redguy666 (15 Mar 2013)

I could not find it anywhere if mybatis already supports such scenario or is it possible with plugins... The case is: I want to generate two cvlasses for every model/table, for example for table 'table' I want to have:

abstract class BaseTable {
  //all mybatis generated stuff goes here
}
class Table extends BaseTable {
  //empty
}

no - the point is that subsequent generations can overwrite BaseTable class, but final Table class where developers can put some logic must be left intact,
mappers should by default return always 'Table' class instances and operate on that level.

is it possible with current mybatis, with some plugins, or with custom code modyfications? Or maybe it could be built-in in mybatis next release? :)


## jeffgbutler (15 Mar 2013)

This is an issue for the generator, not MyBatis itself.  Please move it there and we'll discuss.


## redguy666 (15 Mar 2013)

moved to generator: https://github.com/mybatis/generator/issues/1


