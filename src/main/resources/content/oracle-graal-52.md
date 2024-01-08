#Redesign SourceSection API and implementation

Owner: oracle

Repo: graal

Labels: oracle-emp 

## mlvdv (16 Feb 2016)

`SourceSection` is old prototype code and is overdue for redesign and reimplementation.  One goal is to reduce memory footprint. There are also open issues around requirements for variants that carry useful information when actual source is either unavailable or nonexistent; this is especially important for tools.  Among others, a representative of each language team should be included in a discussion of requirements for "unavailable" as well as any other requirements that experience has revealed.


## jtulach (16 Feb 2016)

While the origin of the code may be a prototype, I'd like to point out that Javadoc of both truffle@0.10 and truffle@0.11 include the package in _stable_ API category.


