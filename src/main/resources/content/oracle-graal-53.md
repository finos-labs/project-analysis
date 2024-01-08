#Source: reimplement text coordinates

Owner: oracle

Repo: graal

Labels: oracle-emp 

## mlvdv (16 Feb 2016)

The `Source` class contains support for describing text segments in several ways, including both 0-based character offsets and 1-based {row,line} coordinates.  Although correctly documented, this support has a design flaw that makes it impossible to create a `SourceSection` that represents the contents of an empty `Source` instance.  JRuby has needed a workaround for this, as likely have others.  The "text map", its method documentation, and detailed tests must be reimplemented.


