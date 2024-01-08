#synchronous calls

Owner: greenrobot

Repo: EventBus

Labels: 

## jblernout (08 Oct 2012)

Hi,

I like your library but I was wondering if it would be possible for the post method to call the registered onEvent methods without being queued when called from an onEvent method.

I think it could be useful.

JB


## greenrobot (20 Feb 2013)

Can you describe your use case and why it shouldn't be up to the subscriber?


## greenrobot (14 Nov 2013)

See thread modes.


## Akkell (05 Apr 2015)

Thanks for good lib. Don't have a time to describe use case. Fixed issue with the following modification to EventBus.post(Object) method:

```
/** Posts the given event to the event bus. */
public void post(Object event) { 
    PostingThreadState postingState = new PostingThreadState();
    /*List<Object> eventQueue = postingState.eventQueue;*/
    /*eventQueue.add(event);*/

    if (!postingState.isPosting) {
        postingState.isMainThread = Looper.getMainLooper() == Looper.myLooper();
        postingState.isPosting = true;
        if (postingState.canceled) {
            throw new EventBusException("Internal error. Abort state was not reset");
        }
        try {
            /*while (!eventQueue.isEmpty()) {*/
                currentPostingThreadState.set(postingState);
                postSingleEvent(/*eventQueue.remove(0)*/event, postingState);
            /*}*/
        } finally {
            postingState.isPosting = false;
            postingState.isMainThread = false;
        }
    }
}
```

And remove now unnecessary variable eventQueue:

```
/** For ThreadLocal, much faster to set (and get multiple values). */
final static class PostingThreadState {
    /*final List<Object> eventQueue = new ArrayList<Object>();*/
    boolean isPosting;
    boolean isMainThread;
    Subscription subscription;
    Object event;
    boolean canceled;
}
```


