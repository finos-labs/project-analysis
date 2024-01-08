#交替打印奇偶数 TwoThread.java 的几个问题

Owner: crossoverJie

Repo: JCSprout

Labels: question 

## xiaozhenxiao (03 Jul 2018)

## Function: 两个线程交替执行打印 1~100

>其实现TwoThread.java类有可能打印出101,102

## 线程空转，cpu飙升问题 
>去掉奇偶线程中的下面这段代码
                   try {
                        //防止线程空转
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
会造成并不总是输出全部100个数字，会有卡住现象，并伴随CPU飙升，这是什么原因造成的呢

## crossoverJie (03 Jul 2018)

@xiaozhenxiao 

第一个问题我没有复现哈。

第二个问题，其实 debug 看下线程快照就明白了。

![](https://ws3.sinaimg.cn/large/006tNc79ly1fswwaoepkyj31kw0cp78e.jpg)

在第 97 行对这个 flag 进行了判断，必须得为 false 才会执行后面打印奇数的逻辑。

这个 flag 是 t1 线程修改的，由于该 flag 没有用 volatile 修饰所以读取到的还是旧值，也就是 true 导致一直在这里死循环。

之前加的有 sleep 的作用其实就是为了让出时间片（也为了防止空转），可以让 t2 线程读取到最新的值。

## crossoverJie (03 Jul 2018)

@xiaozhenxiao 

其实这里 sleep 的作用取了个巧，一是可以取到新值（但不是立马取到）同时让出了时间片。

在原有的基础上加上 volatile 会比较好。

## gaosai01 (19 Jul 2018)

```java
// 谢谢阅读
public class TwoThread {

    public static void main(String[]   args){
        TwoThread thread = new TwoThread();
        Thread thread1 = new Thread(thread.new Ji());
        Thread thread2 = new Thread(thread.new Ou());
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    private volatile int num = 1;
    class Ji implements Runnable{
        @Override
        public void run() {
            while( num <= 100 ){
                if( num % 2 == 1 ){
                    System.out.println( "ji:"+num );
                    num++;
                }
            }
        }
    }
    class Ou implements Runnable{
        @Override
        public void run() {
            while( num <= 100 ){
                if( num % 2 == 0 ){
                    System.out.println( "ou:"+num );
                    num++;
                }
            }
        }
    }
}
```


## sangyue174 (19 Jul 2018)

@Mr-GaoSai 感觉这个设计很巧妙，利用可见性和奇数偶数的不同特征，省去了加锁的开销。

## crossoverJie (19 Jul 2018)

@Mr-GaoSai 

可以的，相当于利用了 `num` 来区分了不同的线程。

## gaosai01 (20 Jul 2018)

@leejones92  小哥哥，您的代码有些问题，re这个方法开启的线程数不一定是两个，可能4个或者6个。然后你的线程可能会出现输出到100+的情况

## pasake (20 Jul 2018)

增大num至1000最后有时打印1001，volatile无法解决并发问题，可改用乐观锁定义num

`public class TwoThread {

    public static void main(String[] args) {
        TwoThread thread = new TwoThread();
        Thread thread1 = new Thread(thread.new Ji());
        Thread thread2 = new Thread(thread.new Ou());
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private AtomicInteger num = new AtomicInteger(1);

    class Ji implements Runnable {
        @Override
        public void run() {
            while (num.get() <= 1000) {
                if (num.get() % 2 == 1) {
                    System.out.println("ji:" + num);
                    num.incrementAndGet();
                }
            }
        }
    }

    class Ou implements Runnable {
        @Override
        public void run() {
            while (num.get() <= 1000) {
                if (num.get() % 2 == 0) {
                    System.out.println("Ou:" + num);
                    num.incrementAndGet();
                }
            }
        }
    }
}`

## leejones92 (20 Jul 2018)

@Mr-GaoSai 是有点问题 我调整下 再发出来 不能误导人

## leejones92 (20 Jul 2018)

重新设计
public class Print {
    private int count = 1;

    public void out() {
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                print1();
            }
        }, "thread-1").start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                print2();
            }
        }, "thread-2").start();

    }

    public synchronized void print1() {
        while (count % 2 == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getId() + "," + Thread.currentThread().getName() + ":" + count++);
        this.notify();
    }


    public synchronized void print2() {
        while (count % 2 == 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getId() + "," + Thread.currentThread().getName() + ":" + count++);
        this.notify();
    }

    public static void main(String[] args) {
        Print print = new Print();

        print.out();


    }
}


## gaosai01 (20 Jul 2018)

@leejones92  设计巧妙，通过synchorized添加内存屏障刷新count，多线程下他的访问每次都是最新的，减少了设置count为volitale

## crossoverJie (21 Jul 2018)

@pasake @leejones92

都不错，建议贴代码的时候注意下格式，有几行没有用代码块包住。

有兴趣的可以欢迎提 PR.



## TaylorKanper (25 Jul 2018)

@Mr-GaoSai 有时候会打印出101，其实这是因为瞬时为100的时候，进入了奇数线程，偶数线程自增，奇数线程读到101，并输出，大佬可以把while循环的东西改一下

## gaosai01 (25 Jul 2018)

@tydic-kanper  谢谢老哥，很有道理
```
while( num <= 100 ){
        if( num % 2 == 1 ){
                // 两次读取num之间存在时间差多线程下while循环为100，进入循环后，num变成101，这时会打印101，所以线程不安全
                System.out.println( "ji:"+num );
                num++;
        }
}
```

## careyyu (06 Oct 2018)

去掉sleep后，把100放大到10000，flag 没有加volatile的情况下 会出现死循环的情况

## changchangjie (04 Jan 2019)

博主的交替奇偶数代码那个锁是没用的，flag保持可见性就行了

## michaeljqzq (01 Aug 2019)

@gaosai01 老哥，这样改是不是能解决打印出101的问题，我本地测了下没问题
```java
public class Main {
    static volatile int count = 1;

    public static void main(String[] args) throws Throwable {
        Thread t1 = new Thread(() -> {
            while (count <= 100) {
                if(count % 2 == 1) {
                    if(count <= 100) {
                        System.out.println(count);
                        count++;
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (count <= 100) {
                if(count % 2 == 0) {
                    if(count <= 100) {
                        System.out.println(count);
                        count++;
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}
```

