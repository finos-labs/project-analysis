#graal-core cannot use Accessor

Owner: oracle

Repo: graal

Labels: bug 

## jtulach (29 Feb 2016)

Right now the graal-core uses Truffle class from an implementation package: https://github.com/graalvm/graal-core/blob/6a3900d09cc4aa94196c58a9b136e4d422b6055b/graal/com.oracle.graal.truffle/src/com/oracle/graal/truffle/OptimizedCallTarget.java#L630

Such tight coupling between Truffle and graal-core will force us to release and use Truffle and graal+core in the same time. That is far from ideal. There shall be explicit, more stable API (probably more an SPI) for communication between Truffle and various runtimes.


## chumer (29 Feb 2016)

From my discussion with @jtulach I can derive that the best thing how to tackle this problem would be an SPI using an abstract class (so it can be evolved). This way guest languages could no longer access the runtime implementation directly but with an API. Truffle classes should be able to communicate directly with the runtime SPI so they can establish a private communication channel to implement features like https://github.com/graalvm/truffle/pull/70 without the use of Accessor.


## jtulach (17 Mar 2016)

Pull request created: https://github.com/graalvm/graal-core/pull/135


## jtulach (22 Mar 2016)

Both graalvm/graal-core#135 and #139 are ready to be integrated. Please review.


## chumer (18 Jul 2016)

The TMVCI API got successfully integrated. Closing.


