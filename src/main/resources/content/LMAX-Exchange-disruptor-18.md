#Another way to avoid False Sharing [moved]

Owner: LMAX-Exchange

Repo: disruptor

Labels: 

## mikeb01 (26 Sept 2012)

This is [Issue 18](http://code.google.com/p/disruptor/issues/detail?id=18) moved from a Google Code project.
Added by 2012-03-04T19:06:29.000Z by [saintof...@gmail.com](http://code.google.com/u/115816047214705454254/).
Please review that bug for more context and additional comments, but update this bug.
 Closed (Invalid).

Original labels: Type-Defect, Priority-Medium
### Original description

```
I checked out the False Sharing sample, modify the long value wrapper to a nested long value wrapper, seems more efficient:)
The main point just like this:

public final static class LongValue {
        public long value;
}

public final static class VolatileLong {
        public final LongValue value = new LongValue();
}
Some test results show at least 5x better.
If it ok, the Sequence maybe retire:)

The whole code:
public final class FalseSharing2 implements Runnable {
    public final static int NUM_THREADS = 4; // change
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;

    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];
    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharing2(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception {
        final long start = System.nanoTime();
        runTest();
        System.out.printf(&quot;duration %,d \n&quot;, (System.nanoTime() - start));
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing2(i));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value.value = i;
        }
    }

    public final static class LongValue {
        public long value;
    }

    public final static class VolatileLong {
        public final LongValue value = new LongValue();
        // 1,150,255,587 Nest value
        // 1,015,843,430
        // 955,585,491
        // 11,305,826,270 //longs padding
        // 66,421,136,612 // no padding
        // 5,911,999,787 //long[8] paddings
        // 5,716,804,521
        // 5,748,131,849 long[7] padding
        // 5,854,105,701
        // 5,828,358,423
        // 5,797,533,843
        // 5,713,556,973 long[16] padding
        // 5,901,405,576 //long[6] paddings
        // 14,815,210,202 long[1] paddings
        // 11,429,162,478 //longs padding
        // 50,789,779,317 string padding
        // 50,965,117,605
        // 14,669,913,192 string[1] padding
        // 5,672,750,830 byte[56] padding
        // 5,712,467,516
        // 5,847,267,588
        // 5,654,530,766
        // 12,797,977,884 byte[32] long byte[32]
        // 13,928,776,237 byte[63] long byte[63]
        // 10,741,697,472 long[7] long long[7]
        // 12,004,346,192
        // 5,653,926,506
        // 5,797,767,244
        // 5,655,366,476
        // 5,767,881,114
        // 5,944,711,137
        // 5,611,998,754
        // public long p1, p2, p3, p4, p5, p6; // comment out
        // public long[] paddings = new long[7];
        // public byte[] padding2 = new byte[56];
    }
}
```


