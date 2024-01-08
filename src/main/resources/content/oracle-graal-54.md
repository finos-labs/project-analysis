#Create a SourceSection "auditor" for ASTs

Owner: oracle

Repo: graal

Labels: oracle-emp 

## mlvdv (16 Feb 2016)

Certain constraints on AST structure and "source attribution" (attached `SourceSection` instances) make the ASTs well behaved for a variety of purposes, especially for tools.  Examples include fast searching for AST nodes corresponding to particular text selections (e.g. point and click in a debugger), and some kinds of source mapping.  The basic idea is that the AST naturally represents a subset (projection) of a parse tree.  Another way to view it is that the `SourceSection` on a node represent exactly the text that was parsed to produce the node. 

Create an auditor to check for this property.  More specifically;
1. The `SourceSection` of a parent node includes the text of every child node's`SourceSection`.
2. The `SourceSection` text of a node contains text that strictly precedes (in the original `Source`) the `SourceSection` of every sibling node to the right.
3. The `SourceSection` text of a node contains text that strictly follows (in the original `Source`) the `SourceSection` of every sibling node to the left.


