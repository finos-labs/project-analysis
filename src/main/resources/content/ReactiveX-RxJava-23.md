#Operator: And

Owner: ReactiveX

Repo: RxJava

Labels: Enhancement 

## benjchristensen (16 Jan 2013)

http://msdn.microsoft.com/en-us/library/hh229153(v=vs.103).aspx
http://msdn.microsoft.com/en-us/library/hh229905(v=vs.103).aspx


## akarnokd (21 Nov 2013)

This operation requires a join-pattern sub-framework to be created (rx.joins). Additionally, due to method arities, this might require Action4...Action9, ActionN classes (I have these in pull #505) if decided.
However, I don't quite understand the join patterns as most of the Rx.NET classes are internal and without documentation; I can convert it to java but no idea what tests to create for them.


