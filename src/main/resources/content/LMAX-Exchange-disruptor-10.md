#several invocations of different RingBuffers from same thread are leading to misbehavior [moved]

Owner: LMAX-Exchange

Repo: disruptor

Labels: 

## mikeb01 (26 Sept 2012)

This is [Issue 10](http://code.google.com/p/disruptor/issues/detail?id=10) moved from a Google Code project.
Added by 2011-09-14T15:59:13.000Z by [sascha.t...@gmail.com](http://code.google.com/u/104130174731018208316/).
Please review that bug for more context and additional comments, but update this bug.
 Closed (Fixed).

Original labels: Type-Defect, Priority-Medium
### Original description

```
<b>What steps will reproduce the problem?</b>

1. create several small (capacity=2) RingBuffers using ClaimStrategy.Option.MULTI_THREADED
2. create a RingBuffer, take all sequences, publish first, take another sequence
3. create another RingBuffer, take all sequences, RingBuffer#hasAvailableCapacity yields true


<b>What is the expected output? What do you see instead?</b>

RingBuffer#hasAvailableCapacity should yield false


<b>Please provide any additional information below.</b>

This issue was introduced with revision 362 and is caused by the static ThreadLocal minGatingSequenceThreadLocal in ClaimStrategy.java line 115. Removing the static modifier
(eg ThreadLocal per instance) solves the issue and gives every RingBuffer its own minGatingSequenceThreadLocal as it should be.


I have attached patch including a TestCase and the fix as mentioned above.
```


