#InMemoryMetricRepository isn't thread-safe

Owner: spring-projects

Repo: spring-boot

Labels: 

## wilkinsona (05 Sept 2013)

When incrementing values increments may be lost as the steps to retrieve a metric, increment it, and store it again are not performed atomically. The following test will fail:

```
@Test
public void concurrentIncrements() throws InterruptedException {
    ExecutorService executor = Executors.newFixedThreadPool(25);

    final String metricName = "test";
    final CountDownLatch latch = new CountDownLatch(1000);

    for (int i = 0; i < 1000; i++) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                metricRepository.increment(metricName,  1, new Date());
                latch.countDown();
            }
        });
    }

    latch.await();

    assertEquals(1000, (int)metricRepository.findOne(metricName).getValue());
}
```

Concurrent finds and deletes may fail with an NPE:
1. Thread one calls containsKey to check for the existence of the metric
2. Thread two removes the metric
3. Thread one assumes the metric's still there and calls this.metrics.get(metricName).getMetric(), resulting in an NPE

Due to this problem, the following test will fail:

```
@Test
public void concurrentFindAndDelete() throws InterruptedException {
    ExecutorService executor = Executors.newFixedThreadPool(25);

    final String metricName = "test";
    final CountDownLatch latch = new CountDownLatch(1000);
    final AtomicInteger errors = new AtomicInteger();

    for (int i = 0; i < 1000; i++) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    metricRepository.set(metricName,  10,  new Date());
                    metricRepository.findOne(metricName);
                    metricRepository.delete(metricName);
                } catch (Exception e) {
                    errors.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            }
        });
    }

    latch.await();
    assertEquals(0, errors.incrementAndGet());
}
```

There's also a problem with the use of replace in increment and set when delete's called at the same time – replace may have no effect due to the following sequence of events:
1. Thread 1 calls containsKey to check that the metric's there
2. Thead 2 deletes the metric
3. Thread 1 calls replace which has no effect


## dsyer (18 Sept 2013)

Fixed, thanks. Actually the InMemory\* implementations are not intended for GA (we assume something better will come along, but since it hasn't yet I fixed it anyway).


