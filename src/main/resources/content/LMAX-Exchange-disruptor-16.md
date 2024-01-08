#waitForFreeSlotAt() uses LockSupport.parkNanos(1L) can take up to 15ms on Windows [moved]

Owner: LMAX-Exchange

Repo: disruptor

Labels: 

## mikeb01 (26 Sept 2012)

This is [Issue 16](http://code.google.com/p/disruptor/issues/detail?id=16) moved from a Google Code project.
Added by 2012-01-10T10:17:07.000Z by [thalmann...@gmail.com](http://code.google.com/u/100616314795013555846/).
Please review that bug for more context and additional comments, but update this bug.

Original labels: Type-Defect, Priority-Medium
### Original description

```
I wrote a simple performance test using the Disruptor to test mostly-empty and mostly-full scenarios in the ring buffer.

The SingleThreadedClaimStrategy() provides waitForFreeSlotAt() to block until a place in the ring buffer is available. Within waitForFreeSlotAt() the thread is parked repeatedly using LockSupport.parkNanos(1L) until a place on the ring buffer is available.

LockSupport.parkNanos() uses the JVM to unsafe.park() the LWP in the OS.

I tested the delay of LockSupport.parkNanos(1L) in 3 environments:

a) Windows 7/32bit with i7 M620 @2.67 GHz with Java 1.6.0_23
b) Solaris 10 5/08 s10s_u5wos_10 SPARC VII with Java 1.6.0_22
c) Linux Redhat 2.6.18-194.el5 with AMD Opteron(tm) Processor 6164 with Java 1.6.0_29

with the following findings:
a) avg park() delay times are around 15 millisenconds
b) avg park() delay times are around 6 microsenconds
c) avg park() delay times are around 8 microsenconds

I am confused by the much longer delay of unsafe.park() in my Windows machine, compared to Linux and Solaris.

Testing the Disruptor with a BusySpinWaitStrategy() for 100000 objects on Windows I find:
a) waitForFreeSlotAt() using LockSupport.parkNanos(1L) (unmodified)
       min   0.384 us
      mean  17.272 us
      max 8488.585 us
   std dev 108.792 us

b) waitForFreeSlotAt() using Thread.yield() instead of LockSupport.parkNanos(1L)
       min   0.000 us
      mean   0.420 us
      max 4692.955 us
   std dev  21.881 us

b) waitForFreeSlotAt() using BusyLoop (just an empty while loop)
       min   0.000 us
      mean   0.259 us
       max 236.745 us
   std dev   0.921 us


If you can reproduce the delays of LockSupport.parkNanos(1L) in your environment, I suggest to change waitForFreeSlotAt() to use Thread.yield() instead of LockSupport.parkNanos(1L)


Fantastic work. Thank you.
```


## jasonk000 (09 Apr 2013)

I don't think this applies to the 3.0.x codeline.

However I do note that SingleProducerSequencer and MultiProducerSequencer have a TODO on switching to use the provided wait strategy, if I get a chance I'll do some comparisons. May be of interest to Windows users as it uses parkNanos at the moment.


## qinxian (14 Jun 2013)

Seems on AMD & windows, the parkNanos work not so well.


## mikeb01 (14 Jun 2013)

It is specifically Windows that is the problem.  I think that this should be the responsibility of the WaitStrategy and I should of made the change before the 3.0 release as it will mean changing a public API.


## mikeb01 (14 Apr 2014)

The workaround in this case is to use one of the try... methods e.g. [tryPublishEvent](https://github.com/LMAX-Exchange/disruptor/blob/master/src/main/java/com/lmax/disruptor/RingBuffer.java#L419) and handle the retry logic within user code, e.g. spin, yield, sleep.

I may address this issue in the next major revision of the Disruptor or drop the blocking publish method entirely.


## bwzhang2011 (16 Oct 2014)

how about this going on ? does disruptor drop the blocking publish method entirely ?


## mikeb01 (16 Oct 2014)

Using the aforementioned work around is still the best option.


