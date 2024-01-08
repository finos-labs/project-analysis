#DSL creating superfluous classes

Owner: oracle

Repo: graal

Labels: oracle-emp 

## wirthi (18 Feb 2016)

Truffle DSL has an optimization for creating Classes from the @Specialization annotation: In cases where there is only one Specialization, it avoids generating the Base_, Uninitialized_ and [SpecializationName]\* classes, but gets along with just the [NodeName]Gen class.

This optimization seems to fail on two occasions:
1. when an @Cached annotation is used in this specialization's argument list
2. when a specific datatype is used as argument, i.e. when there might be a need to throw an UnsupportedSpecializationException.

While the second one should rarely occur due to the imminent exception (but is in fact occurring in internal helper nodes), I would expect the first one to occur a lot in all language implementations, generating lots of unnecessary extra classes. Fixing this would reduce code size and potentially improve startup/warmup time.


## chumer (18 Feb 2016)

Not yet sure I can provide a solution for case 1.
But for 2. it should be possible to avoid generation of specialization classes.


## chumer (18 Jul 2016)

Closing. Continued elsewhere: GR-1079


