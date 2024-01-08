#UUID生成是否有性能隐患？

Owner: apache

Repo: skywalking

Labels: bug question 

## wu-sheng (14 Dec 2015)

UUID.randomUUID，有时候会过慢，可能出现意想不到的锁。原因来自SecureRadom，等待机器产生新的噪音（如机器上某个文件发生变化）才能生成所技术。


## wu-sheng (16 Dec 2015)

- uuid生成时间: 199254509ms

```
    final AtomicLong count = new AtomicLong(0);
        List<Thread> allThreads = new ArrayList<Thread>();
        for (int k = 0; k < 200; k++) {
            allThreads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    Long before = System.currentTimeMillis();
                    for (int i = 0; i < 1000000; i++) {
                        UUID.randomUUID().toString().replaceAll("-", "");
                    }
                    count.addAndGet(System.currentTimeMillis() - before);
                }
            }));
        }
        for (Thread t : allThreads) {
            t.start();
        }
        for (Thread t : allThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("uuid: " + count.get() + "ms");
```
- logic rule id生成时间: 3257836ms

```
        allThreads = new ArrayList<Thread>();
        final AtomicLong count2 = new AtomicLong(0);
        for (int k = 0; k < 200; k++) {
            allThreads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    Long before = System.currentTimeMillis();
                    String id = System.currentTimeMillis()
                            + BuriedPointMachineUtil.getHostDesc()
                            + BuriedPointMachineUtil.getProcessNo()
                            + Thread.currentThread().getId();
                    int j = 0;
                    for (int i = 0; i < 1000000; i++) {
                        id = System.currentTimeMillis()
                                + BuriedPointMachineUtil.getHostDesc()
                                + BuriedPointMachineUtil.getProcessNo()
                                + Thread.currentThread().getId() + j++;
                    }
                    count2.addAndGet(System.currentTimeMillis() - before);
                }
            }));
        }

        for (Thread t : allThreads) {
            t.start();
        }
        for (Thread t : allThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("logic rule id: " + count2.get() + "ms");
```


## wu-sheng (16 Dec 2015)

建议traced的生成规则如下：
当前时间 + 线程号 + 线程自增序号（4位，int自增到9999则归零）+ 进程级全局UUID（或后6-10位）


## wu-sheng (17 Dec 2015)

使用全新的算法，速度提高30倍。
新生成规则为：
当前时间（毫秒）+ 全局UUID（后7位）+ 进程号 + 线程号 + 当前线程内序号（0-10000内循环）


