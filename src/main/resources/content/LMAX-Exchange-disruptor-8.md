#Add thousand separators to perf test results [moved]

Owner: LMAX-Exchange

Repo: disruptor

Labels: 

## mikeb01 (26 Sept 2012)

This is [Issue 8](http://code.google.com/p/disruptor/issues/detail?id=8) moved from a Google Code project.
Added by 2011-09-06T17:25:01.000Z by [minggf...@gmail.com](http://code.google.com/u/113595387861730769397/).
Please review that bug for more context and additional comments, but update this bug.
 Closed (Fixed).

Original labels: Type-Defect, Priority-Medium
### Original description

```
On line 50 of AbstractPerfTestQueueVsDisruptor.java,
please add a comma to the format to print out thousand separators.

e.g. BlockingQueues=%,d, Disruptor=%,d
```


