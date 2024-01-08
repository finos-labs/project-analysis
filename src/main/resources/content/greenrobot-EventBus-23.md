#Register to generic type events

Owner: greenrobot

Repo: EventBus

Labels: 

## romainpiel (08 Mar 2013)

Hi, I'm currently using EventBus in my app. I really like it it makes stuff much simpler.
I'm just facing a small issue which is an edge case I guess.

I would like to subscribe to generic classes, how do I do that:

``` java
public onEvent(MyEvent<A> event) {
// Issue: I receive all events of the class MyEvent, event stuff like MyEvent<B>
}
```


## greenrobot (08 Mar 2013)

Would be cool, just.. It cannot be done because of the way generics work in Java (type erasure). It's impossible for EventBus to determine the generic type of events being posted.


## romainpiel (14 Mar 2013)

Hmmm interesting. I know generics are a pain when it comes to reflection. But I'm pretty sure you can find a workaround to achieve this. I'll try something when I have a moment.


## greenrobot (14 Nov 2013)

I suppose this is still technically impossible.


## ExtinctAmoeba (11 Sept 2018)

Still in 2018?!

