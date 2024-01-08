#StackOverflow when using databaseId

Owner: mybatis

Repo: mybatis-3

Labels: bug 

## emacarron (24 Feb 2013)

gcode: Reported by dcendents, Feb 21 (2 days ago)

Hi,

I found a weird problem with mybatis, spring and declaring statements with databaseId that end up in a StackOverflowError (infinite loop).

mybatis version: 3.1.1
mybatis-spring version: 1.1.1

See the attached project with 2 unit tests. One that fails to load the mybatis configuration, and the second one that force the databaseId to null and the mybatis configuration can be loaded.

I've done a bit of debugging to try and understand the problem and here is what I think is the problem:

(Refer to the attached project for files and content)
- Mapping A is loaded
  - XMLMapperBuilder.buildStatementFromContext is executed with requiredDatabaseId = h2
    - 3 statements (delete + updates) without databaseId are skipped
    - same 3 statements (delete + updates) with databaseId=h2 are loaded
    - select without databaseId is skipped
  - XMLMapperBuilder.buildStatementFromContext is executed with requiredDatabaseId = null
    - 3 statements (delete + updates) with databaseId=h2 are skipped
    - same 3 statements (delete + updates) without databaseId are skipped because "skip this statement if there is a previous one with a not null databaseId" (databaseIdMatchesCurrent method)
    - select without databaseId is added to the incomplete statements because it includes sql from mapper C
- Mapping B is loaded, similar to A except:
  - XMLMapperBuilder.buildStatementFromContext is executed with requiredDatabaseId = null
    - 3 statements (delete + updates) without databaseId are parsed, they should be skipped but the following happens instead
      - in databaseIdMatchesCurrent
        - configuration.hasStatement is called with validateIncompleteStatements set to false and returns true
      - configuration.getMappedStatement with default value for validateIncompleteStatements set to true
        - it tries to parse the statement from mapper A (still incomplete) and throws an exception
        - The statement is added to the incomplete statements
- Mapping C is loaded
  - Again in databaseIdMatchesCurrent it tries to resolve incomplete statements, but this time mybatis knows about mapping C, so when processing the select from mapping A, it also enters into the databaseIdMatchesCurrent which tries to resolve incomplete statements, loads select from A again, and gets into an infinite loop.

The good news is that I think this is a very simple fix, XMLStatementBuilder.databaseIdMatchesCurrent line 130 should set validateIncompleteStatements to false: "MappedStatement previous = this.configuration.getMappedStatement(id, false);"

Also what is strange is I could not reproduce the problem without loading mybatis through spring, but I don't see why that would matter.

Hope the fix is as easy as that and it can be fixed for the next version.

My current workaround is to load the mapping in a different order (B, C and then A).
But there might be a time soon where all our mappings will reference sql fragments from other files and we won't be able to use that workaround.

Thanks


