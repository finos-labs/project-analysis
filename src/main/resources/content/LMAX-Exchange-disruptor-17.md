#BatchEventProcessor un-teriminate [moved]

Owner: LMAX-Exchange

Repo: disruptor

Labels: 

## mikeb01 (26 Sept 2012)

This is [Issue 17](http://code.google.com/p/disruptor/issues/detail?id=17) moved from a Google Code project.
Added by 2012-03-04T14:08:22.000Z by [saintof...@gmail.com](http://code.google.com/u/115816047214705454254/).
Please review that bug for more context and additional comments, but update this bug.
 Closed (WontFix).

Original labels: Type-Defect, Priority-Medium
### Original description

```
AssertionError: Failed to stop thread[BatchEventProcess@...]
d.dsl.stubs.StubExecutor.joinAllThreads
d.dsl.DisruptorTest.tearDown

shouldMakeEntriesAvailableToFirstHandlersImmediately

Test running at Win7 64 with AMD core3, and jdk7 u3
Not often,  the chance in my machine about 10 percent.
```


