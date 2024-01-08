#SourceSection.getTags()

Owner: oracle

Repo: graal

Labels: bug wontfix oracle-emp 

## mlvdv (05 Mar 2016)

Restore the ability for debugging and other tools to enumerate tags applied at particular code locations.  It can be slow, and should be clearly identified as such.


## chumer (07 Mar 2016)

This limitation is on purpose and to quote @jtulach it would be a "huge mistake" to expose this. Its unfortunate that guest languages cannot iterate tags themselves as easy as before. But tools should not have access to all assigned tags, only those tags that they know/care of. Otherwise guest languages will start to misuse tags interpreting them with additional semantics instead of just identity semantics.

You can still iterate over all known tags and check one-by-one if its contained.


## jtulach (14 Mar 2016)

"huge mistake" means that iterating over all (e.g. also unknown) tags provided for other tools makes no sense from an API perspective.


## mlvdv (14 Mar 2016)

The use case is for features in the REPL debugger that can support us working on the infrastructure. Debugging tools often require special access.  How can we provide this?


## jtulach (15 Mar 2016)

Runtime inspection available only to debugger should be placed into `SuspendedEvent`. Please carefully consider what extras are put there.


