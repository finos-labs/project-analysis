#load balancer does not work as expected

Owner: redisson

Repo: redisson

Labels: 

## pprun (06 May 2014)

I have test code for the load balance, but only the first instance got the load:

public static void main(String[] args) {
        Config config = new Config();
        //config.setConnectionPoolSize(100); // default 100
        // Redisson will use load balance connections between listed servers
        String[] redisInstances = new String[]{"192.168.9.101:6379", "192.168.9.102:6379"};
        config.addAddress(redisInstances);

```
    Redisson redisson = Redisson.create(config);
    Queue<Integer> q = redisson.getQueue("QUEUE");

    for (int i = 0; i < 1_000_000; i++) {
        q.add(i);
        if (i % 10000 == 0) {
            System.out.println("put: " + i);
        }
    }

    System.out.println("DONE.");
    redisson.shutdown();
}
```

Do I miss any thing?


## mrniko (08 Jun 2014)

@pprun  Fixed in this issue https://github.com/mrniko/redisson/issues/24


