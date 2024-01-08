#Rx Schedulers

Owner: ReactiveX

Repo: RxJava

Labels: Enhancement 

## benjchristensen (16 Jan 2013)

Implementation of Rx Schedulers (http://msdn.microsoft.com/en-us/library/hh242963(v=vs.103).aspx) ... probably to go into the rx.concurrency package (https://github.com/Netflix/RxJava/tree/master/rxjava-core/src/main/java/rx/concurrency). 


## benjchristensen (23 Jan 2013)

I guess this would mean that things like "toObservable(1, 2, 3, 4)" are a known thing and can be done immediately without a thread, but merging 4 unknown sequences can't be known.

Obviously when Timers are used a Thread of some kind is needed (java.util.Timer or another implementation like this: https://github.com/Netflix/Hystrix/blob/master/hystrix-core/src/main/java/com/netflix/hystrix/util/HystrixTimer.java).

For the majority of cases though where the "cost" of the observable sequence is unknown I don't know that I like automatically spawning them off on threads. It has worked well for the Netflix API to leave that choice to the origin of the observable (to be synchronous or asynchronous, one thread or many, etc).

The introduction of Schedulers makes perfect sense when an app is dealing with data structures, their own synchronous IO operations or CPU bound computations, but it becomes awkward when consuming from a 3rd party API exposing Observables who can and will make their own choice about being synchronous or asynchronous.

In fact, that's been a huge part of the draw to Rx is that the API doesn't need to change when the backend implementations moves between synchronous and asynchronous for whatever reason it may choose to do so.

If the Observable is already asynchronous it would be inefficient to spin up another thread that in turn blocks on an async call.

Other than documentation on the API calls that return Observables is there a better way to handle that scenario?

I can imagine a scenario where some apps (such as the Netflix API) may want to disable any use of Schedulers so the origin retains control since Rx has allowed us to decouple the writing of business logic from the decisions of concurrency.

Before flaming me ... I DO like schedulers, it's very powerful and we will definitely get them added, I just have some questions about balancing that power (and inevitable inefficiencies of making poor scheduler choices) with the elegant simplicity of Rx Observables without them where concurrency is not a thought - everything is just asynchronous.

I'm interested in all of your perspectives so please chime in.


## abersnaze (23 Jan 2013)

The docs say "least amount of concurrency" which I interpreted to mean that if the amount of work is unknown that it would it default to immediate.  We could still manipulate the defaults and probably ignore Schedulers passed in through a strategy.


## benjchristensen (23 Jan 2013)

Yes, I think a strategy pattern will be needed to accomplish the Netflix API use case.


## benjchristensen (13 Mar 2013)

Some thoughts while working on the design of this:
- The Scheduler interface should be capable of supporting different sources of concurrency such as Executors, Threads, Actors, EventLoops
- We should be capable of supporting rxjava-contrib modules with new types of Schedulers such as for Akka/Scala Actors
- We need the ability (via plugins probably) of a system to override or prevent usage of Schedulers where they want. For example, if a system doesn't want client code starting threads they should be able to intercept and ignore or throw UnsupportedOperationException.


## benjchristensen (27 Mar 2013)

Sections 6.9 through 6.12 of the Rx Design Guidelines PDF (http://go.microsoft.com/fwlink/?LinkID=205219) should be read by anyone involved in Schedulers design and implementation.


## jmhofer (28 Mar 2013)

There's also [this video](http://channel9.msdn.com/blogs/j.van.gogh/controlling-concurrency-in-rx) explaining the motivation behind introducing schedulers in Rx.


## benjchristensen (29 Mar 2013)

Good video ... thanks for the link.


## benjchristensen (05 Apr 2013)

First round of Schedulers implementation committed via pull request https://github.com/Netflix/RxJava/pull/225 contribued by @mairbek.

It implements ObserveOn (https://github.com/Netflix/RxJava/issues/11) and SubscribeOn (https://github.com/Netflix/RxJava/issues/12).


## benjchristensen (05 Apr 2013)

Open questions:

### 1) Scheduler Time

We're not using the Scheduler.now value anywhere, should we be? or is that only for the Virtual scheduler used for testing?

### 2) Use of SubscribeOn vs Scheduler.scheduler

I'm trying to understand how operator overloads should use Scheduler.

Here is a potential implementation: https://github.com/Netflix/RxJava/pull/227 and another https://github.com/Netflix/RxJava/pull/226

I have not yet found C# source code or documentation that clarifies this.

I have also had feedback (that I agree with) that it this is clearer:

``` java
merge(o1, o2).subscribeOn(scheduler)
```

than this

``` java
merge(o1, o2, scheduler)
```

So is there anything different between this? 

### 3) Multiple Schedulers in Sequence

I'm trying to understand how a sequence should work when multiple `subscribeOn` operators are applied at different steps of a sequence and it is unclear to me  how the unit test below should behave.

Can someone with an Rx.Net environment setup implement a test similar to this from Java and tell me the output?

``` java
@Test
    public void testMixedSchedulers() throws InterruptedException {
        final String mainThreadName = Thread.currentThread().getName();

        Observable<String> o = Observable.<String> create(new Func1<Observer<String>, Subscription>() {

            @Override
            public Subscription call(Observer<String> observer) {

                System.out.println("Origin observable is running on: " + Thread.currentThread().getName());

                assertFalse(Thread.currentThread().getName().equals(mainThreadName));
                assertTrue("Actually: " + Thread.currentThread().getName(), Thread.currentThread().getName().startsWith("RxIOThreadPool"));

                observer.onNext("one");
                observer.onNext("two");
                observer.onNext("three");
                observer.onCompleted();

                return Subscriptions.empty();
            }
        }).subscribeOn(Schedulers.threadPoolForIO()); // subscribe to the source on the IO thread pool

        // now merge on the CPU threadpool
        o = Observable.<String> merge(o, Observable.<String> from("four", "five"))
                .subscribeOn(Schedulers.threadPoolForComputation())
                .map(new Func1<String, String>() {

                    @Override
                    public String call(String v) {
                        // opportunity to see what thread the merge is running on
                        System.out.println("Merge is running on: " + Thread.currentThread().getName());
                        return v;
                    }

                });

        final CountDownLatch latch = new CountDownLatch(1);

        final AtomicReference<RuntimeException> onError = new AtomicReference<RuntimeException>();

        // subscribe on a new thread
        o.subscribe(new Observer<String>() {

            @Override
            public void onCompleted() {
                System.out.println("==> received onCompleted");
                latch.countDown();
            }

            @Override
            public void onError(Exception e) {
                System.out.println("==> received onError: " + e.getMessage());
                onError.set((RuntimeException) e);
                latch.countDown();
            }

            @Override
            public void onNext(String v) {
                System.out.println("==> Final subscribe is running on: " + Thread.currentThread().getName());
                System.out.println("==> onNext: " + v);

            }
        }, Schedulers.newThread());

        // wait for the above to finish or blow up if it's blocked
        latch.await(5, TimeUnit.SECONDS);
    }
```

Of course Rx.Net doesn't have the IO and CPU thread pools ... those are just helper methods to Executors which would be 2 separate threadpools for different work types so you'll need to adjust that.


## jmhofer (08 Apr 2013)

Concerning 1), I guess it will come in handy when implementing #90 (or clock-like observables in general), at least if I understand this correctly. I'm currently figuring out how working with the schedulers feels by playing around with an implementation for #74, which also requires a "clock", though it doesn't seem to require the current time).


## benjchristensen (08 Apr 2013)

I received the following feedback that will require a breaking change to the Scheduler interface:

---

> It is essential to be able to access the scheduler inside the action to recursively schedule yourself. Just having a Func1<Subscription is not very useful since there is no opportunity to return the subscription before the function terminates.

``` C#
Interface IScheduler
{
Schedule<TState>(TState s, Func<IScheduler, TState, IDisposable> a)          
               Schedule<TState>(TState s, DateTimeOffset d, Func<IScheduler, TState, IDisposable> a).
               Schedule<TState>(TState s, TimeSpan t, Func<IScheduler, TState, IDisposable> a)
}
```

> You want to be able to write something like this

``` C#
void Main()
{
     var repeat = Observable.Create<int>(observer =>
     {
         while(true) observer.OnNext(42);
         return () => {};
     });

     //var dispose = repeat.Subscribe(Console.WriteLine);

     var dispose = ObservableEx.ToObservable(NewThreadScheduler.Default)
                  .Select(_ => 42)
                  .Subscribe(x => Console.WriteLine(x));

     Console.ReadLine();
     dispose.Dispose();
     Console.WriteLine("Bye");
}

static class ObservableEx
{
     public static IObservable<Unit> ToObservable(this IScheduler scheduler)
     {
    return Observable.Create<Unit>(observer =>
         {
              return scheduler.ScheduleAsync(async (_scheduler, token) =>
              {
                  while(!token.IsCancellationRequested)
                  {
                     observer.OnNext(Unit.Default);
                       await _scheduler.Sleep(TimeSpan.FromSeconds(2));
                   }
              });
         });
     }
}
```


## benjchristensen (09 Apr 2013)

Here is another use case:

``` C#
var scheduler = TaskPoolScheduler.Default;

var xs = Observable.Generate
     ( 0
     , i=>true
     , i=>i+1
     , i=>i
     , i=>TimeSpan.FromSeconds(1)
     , scheduler
     );

var ys = Observable.Create<int>(observer =>
{
     return scheduler.ScheduleAsync(async (_scheduler, cancel) =>
     {
         await _scheduler.Yield();
         for(var i = 0; !cancel.IsCancellationRequested; i++)
         {
              observer.OnNext(i);
              await _scheduler.Sleep(TimeSpan.FromSeconds(1));
         }
     });
});

//var dispose = ys.Timestamp().Subscribe(x => Console.WriteLine(x.ToString()));
var dispose = ys.Timestamp().DumpLive().Subscribe();
Console.ReadLine();
dispose.Dispose();
Console.WriteLine("disposed");
Console.ReadLine();
```


## benjchristensen (09 Apr 2013)

Note that I'm unavailable to work on this until the 15th. Anyone else who wants to jump in and determine the changes needed based on this feedback please do. 


## benjchristensen (18 Apr 2013)

Here is some simple code I was playing with to prove out the use of `subscribeOn` from an "Observable API" and it appears to be working as we want and from what I can tell it is conforming to the Rx contract and not injecting concurrency where it shouldn't.

Anyone find faults in this?

``` groovy
import rx.*
import rx.concurrency.Schedulers

/*
 * ******** PRODUCER CODE ******** 
 * This is the "Observable API"
 */

Observable<Video> getVideos() {
    return Observable.create({
        observer ->
        Thread.sleep(200); // simulate network traffic
        // 10 videos are fetched in a batch and emitted
        observer.onNext(new Video(1));
        observer.onNext(new Video(2));
        observer.onNext(new Video(3));
        observer.onNext(new Video(4));
        observer.onNext(new Video(5));
        observer.onNext(new Video(6));
        observer.onNext(new Video(7));
        observer.onNext(new Video(8));
        observer.onNext(new Video(9));
        observer.onNext(new Video(10));
        observer.onCompleted();
    })
}


class Video {
    final int id;
    public Video(int id) {
        this.id = id;
    }


    Observable<Rating> getRating() {
        return Observable.create({
            observer ->
            Thread.sleep(200); // simulate network traffic
            observer.onNext(new Rating(id));
            observer.onCompleted();
        }).subscribeOn(Schedulers.threadPoolForIO())
    }

    Observable<Bookmark> getBookmark() {
        return Observable.create({
            observer ->
            Thread.sleep(200); // simulate network traffic
            observer.onNext(new Bookmark(id));
            observer.onCompleted();
        }).subscribeOn(Schedulers.newThread())
    }
}

class Rating {
    final String value;
    public Rating(int id) {
        this.value = "ratingFor_" + id;
    }
}

class Bookmark {
    final String value;
    public Bookmark(int id) {
        this.value = "bookmarkFor_" + id;
    }
}



/*
 * ******** CONSUMER CODE ********
 * This is a client consuming the "Observable API"
 */
long start = System.currentTimeMillis();
getVideos().mapMany({
    Video video ->
    // fetch and transform bookmark
    Observable ob = video.getBookmark().map({b -> 
        return "transformed-" + b.value;
    })

    // fetch ratings and zip together with bookmark
    return Observable.zip(ob, video.getRating(), {b, r -> return [b.value, r.value]})
    .map({ tuple ->
        // combine all metadata for a single Video
        return ["id" : video.id, "bookmark" : tuple[0], "rating": tuple[1]]
    })
}).forEach({
    videoMap ->
    System.out.println("Video: " + videoMap["id"] + "   bookmark: " + videoMap["bookmark"] + "   rating: " + videoMap["rating"] + " Thread: " + Thread.currentThread());
})

long end = System.currentTimeMillis();

System.out.println("time: " + (end-start))
```

Output is:

```
Video: 5   bookmark: transformed-bookmarkFor_5   rating: ratingFor_5 Thread: Thread[RxIOThreadPool-5,5,main]
Video: 9   bookmark: transformed-bookmarkFor_9   rating: ratingFor_9 Thread: Thread[RxIOThreadPool-9,5,main]
Video: 10   bookmark: transformed-bookmarkFor_10   rating: ratingFor_10 Thread: Thread[RxIOThreadPool-10,5,main]
Video: 2   bookmark: transformed-bookmarkFor_2   rating: ratingFor_2 Thread: Thread[RxIOThreadPool-2,5,main]
Video: 4   bookmark: transformed-bookmarkFor_4   rating: ratingFor_4 Thread: Thread[RxIOThreadPool-4,5,main]
Video: 8   bookmark: transformed-bookmarkFor_8   rating: ratingFor_8 Thread: Thread[RxIOThreadPool-8,5,main]
Video: 6   bookmark: transformed-bookmarkFor_6   rating: ratingFor_6 Thread: Thread[RxIOThreadPool-6,5,main]
Video: 3   bookmark: transformed-bookmarkFor_3   rating: ratingFor_3 Thread: Thread[RxIOThreadPool-3,5,main]
Video: 7   bookmark: transformed-bookmarkFor_7   rating: ratingFor_7 Thread: Thread[RxIOThreadPool-7,5,main]
Video: 1   bookmark: transformed-bookmarkFor_1   rating: ratingFor_1 Thread: Thread[RxIOThreadPool-1,5,main]
time: 659
```


## mttkay (22 Apr 2013)

@benjchristensen I'm left wondering if or how question 3) from your comment (https://github.com/Netflix/RxJava/issues/19#issuecomment-15979582) was addressed or whether this is still an open question?

In our app we haven't quite figured out yet which layer should be responsible for scheduling an observable. If we schedule on the service layer--which would make sense when trying to make client code oblivious as to whether code runs concurrently or not--then what does that mean for reusability of observables? Would, say, service A be able to take an observable from service B which has already been scheduled by B, transform and re-schedule it?

With the pre-0.8 Schedulers, this is not possible, since subscribeOn/observeOn will wrap as many times as you call these methods.


## mttkay (23 Apr 2013)

I think the JavaDocs haven't been updated yet: http://netflix.github.io/RxJava/javadoc/rx/Scheduler.html

Is there any documentation / examples around what the state parameter is used for? Looking at the existing schedulers, I only ever see it being passed through, so I wonder what this accomplishes?


## jmhofer (23 Apr 2013)

Here's an example (by @mairbek) using state: https://github.com/Netflix/RxJava/pull/229#issuecomment-16115941


## benjchristensen (23 Apr 2013)

I forgot to upload the new Javadocs ... will do so once I'm at my laptop. Sorry about that. 


## benjchristensen (23 Apr 2013)

@mttkay I found wifi ... uploaded javadocs for 0.8.0.

Also, the example from @mairbek was incorporated into unit tests here: https://github.com/Netflix/RxJava/blob/master/rxjava-core/src/test/java/rx/concurrency/TestSchedulers.java#L255


## benjchristensen (07 Sept 2013)

I believe we're pretty comfortable with the `Schedulers` implementation and interface as of 0.11/0.12 so closing this out.


