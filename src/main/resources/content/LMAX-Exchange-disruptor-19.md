#WorkerPool failed  [moved]

Owner: LMAX-Exchange

Repo: disruptor

Labels: 

## mikeb01 (26 Sept 2012)

This is [Issue 19](http://code.google.com/p/disruptor/issues/detail?id=19) moved from a Google Code project.
Added by 2012-03-07T14:14:42.000Z by [saintof...@gmail.com](http://code.google.com/u/115816047214705454254/).
Please review that bug for more context and additional comments, but update this bug.
 Closed (Invalid).

Original labels: Type-Defect, Priority-Medium
### Original description

```
<b>What steps will reproduce the problem?</b>
1. run OnePublisherToThreeWorkerPoolThroughputTest, repeat with be more patient 
2. in console, the chance of fail less than in IDE, f.e. eclipse
3. the test will hang

<b>What is the expected output? What do you see instead?</b>
when repeat the fail result once, try again in a debug env:
check out the threads, main should loop at YieldWaitStrategy, 
which dependency is right buffer size+1, so it right fail

<b>What version of the product are you using? On what operating system?</b>
svn version, should be 2.8, Win7 on AMD x3  with JDK7 or 8

<b>Please provide any additional information below.</b>
And seems the executor almost verry not fair, so the dependency difference so big and so fail.
The origin code, cpus is 4 wanted, it modified by my system to 3, and the NUM_WORKERS constant changed accordingly.
```


