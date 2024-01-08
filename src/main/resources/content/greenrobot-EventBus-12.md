#throws Error question

Owner: greenrobot

Repo: EventBus

Labels: 

## doridori (12 Dec 2012)

Hi, 

Looking at `EventBus` and

```
  private void postSingleEvent(Object event, boolean isMainThread) throws Error
```

and the section in `post(..)`

```
 try {
    while (!eventQueue.isEmpty()) {
        postSingleEvent(eventQueue.remove(0), isMainThread);
    }
 } finally {
    isPosting.value = false;
 }
```

is there a reason for catching all Errors and dropping them (by having no catch)? 

I only came accross this as I was using testing and this resulted in an AssertionError getting lost so I was getting a false positive. I would like to remove this but wondered if I was missing something?

Thanks


## evanova (12 Dec 2012)

The code you are showing is NOT catching any exception since there is no catch block.
All exceptions happening in this block will be propagated to the caller after the finally block is completed.

The only problem I see in this code is the method declaring that it throws an Error, where it should declare throwing an Exception: Error is very very rarely handled for good reasons since it indicates a problem that most code won't be able to recover from (such as OutOfMemoryError).


## doridori (13 Dec 2012)

Thanks for the reply. My mistake - I should've looked that up first as I always use a catch. 

My AssertionError must be getting lost somewhere else then. Poking around the code I can see the call path

`EventBus.Post(Object)` -> 
`EventBus.postToSubscription(subscription, event, isMainThread)` _throws Error_ -> 
`EventBus.postToSubscription(Subscription, Object, boolean)` ->
`EventBus.invokeSubscriber(PendingPost)`  ->
`EventBus.invokeSubscriber(Subscription, Object)` _throws Error_ 

Now the last method in this call stack invokes the subscriber method and catches any `InvocationTargetException` thrown - which from my understanding will happen if the invoked method throws _any_ kind of exception (which in practice seems to include my `AssertionError` even though that does not subclass `Exception`). It then logs a warning log line and calls

```
SubscriberExceptionEvent exEvent = new SubscriberExceptionEvent(this, cause, event, subscription.subscriber);
post(exEvent);
```

and does not propagate the thrown exception anywhere. Im not sure if this is the best practise as it means it hard to run automated tests as my assertions will get gobbled up here as the thrown exception is being converted to an event rather than bubbling up the call stack. Also it seems that some of the methods above declare `throws Error` (which as you mention above is not really required) and some dont - I guess if you were going to change this all the methods in the stack would need to declare `throws Exception` and then the `EventBus.post()` method may throw an exception. You would ideally handle any thrown exceptions in your event receiver methods and not propagate as the posting class should **not** need to know what the implemented classes are doing with the event trigger and then allow the asserts to bubble through. 

What do you think?


## evanova (15 Dec 2012)

Your diagnosis is most likely correct and if `InvocationTargetException` is not forwarded then that's the reason for the behaviour you are seeing. 

In the case of testing, you could mock the EventBus since it is most likely not what you are testing; you can then verify in your tests that `InvocationTargetException` happens and contains your own exception.


## greenrobot (15 Dec 2012)

Unit testing seems like a valid argument for not catching Errors in EventBus. Will consider this in the next major release.


## evanova (15 Dec 2012)

@greenrobot 
I don't think the current behaviour should be changed because of unit testing. Exceptions that can be thrown in a unit test are not limited to Error(s). So this would not really solve the issue in this case. Either mocking or having the unit test handle error events somehow is preferable.

You may want to handle Error(s) that are contained in the `InvocationTargetException` differently though since they are quite critical; but other than that, since this it is likely that the target of the event is misbehaving, I believe it is reasonable to let exceptions be swallowed, a warning logged and an error event generated, or some such.


## doridori (16 Dec 2012)

Im pretty new to Unit testing and so on - with that in mind however I find that when a library swallows errors in most situations this makes the use of the lib a lot less flexible. If the decision is left up to the developer who is using the library that is usually preferable IMHO. If not I would suggest it be part of the documentation.


## greenrobot (09 Nov 2014)

Check master branch, it's all taken of now.


## Yougin (14 Nov 2014)

Just checked with the latest version ( compile 'de.greenrobot:eventbus:2.4.0' ) and it looks like Exceptions are still swallowed in onEvent() methods.


## greenrobot (14 Nov 2014)

Check EventBusBuilder there's a flag to configure.


## Yougin (15 Nov 2014)

@greenrobot My bad, it works if you have this flag configured, cool, thanks a lot.


