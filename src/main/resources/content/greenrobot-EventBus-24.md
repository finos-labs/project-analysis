#StickyEvent not subscribed via BaseEventType

Owner: greenrobot

Repo: EventBus

Labels: confirmed 

## eunjae-lee (15 Apr 2013)

### Current Status
1. I'm using EventBus 2.0.2
2. I have "RefreshEvent" class which is extending "BaseEvent".
3. And I registered current activity to get that sticky event like this.

``` java
EventBus.getDefault().registerSticky(this);
```
### Here's the question.
1. this works.

``` java
public void onEvent(RefreshEvent event) {} 
```
1. but, this doens't work.

``` java
public void onEvent(BaseEvent event) {}
```

I looked into the source code. and here are some lines from EventBus.java @ line 255.

``` java
        if (sticky) {
            Object stickyEvent;
            synchronized (stickyEvents) {
                stickyEvent = stickyEvents.get(eventType);
            }
            if (stickyEvent != null) {
                postToSubscription(newSubscription, stickyEvent, Looper.getMainLooper() == Looper.myLooper());
            }
        }
```

When `public void onEvent(BaseEvent event) {}` is declared, the eventType is "BaseEvent", not "RefreshEvent". So it fails to get sticky event from "stickyEvents" map object.

Is it intended? or a bug?


## eunjae-lee (15 Apr 2013)

fyi, I'm bypassing this problem using codes like this:

``` java

public void register() {
  EventBus.getDefault().register(this, "onEvent");
  EventBus.getDefault().registerSticky(this, "onRefreshEvent");
}

public void onEvent(BaseEvent event) {
  if (event instance of UserEvent) {

  } else if (event instance of PostEvent) {

  }
}

public void onRefreshEvent(RefreshEvent refreshEvent) {

}
```


## asnare (03 Jul 2013)

There does seem to be a bug here:
- `post`/`postSticky` will properly consider the base types and interfaces of the event when locating registered handlers.
- `registerSticky` doesn't: it only looks for existing events with the _exact_ type of the event handler.

Resolving this involves some complications:
- it's not clearly defined what _last_ means: it could be either based on the listeners or the senders. (Current semantics favor senders). The difference here is whether a listener for `Object` events should be invoked once for every concrete type of posted sticky event, or just once for the most recent.
- Assuming stickiness is based on the concrete type of the event, sending all the right events during `registerSticky` may mean iterating through every sticky event.


## carlosefonseca (06 Jan 2014)

I was trying to postSticky an Anonymous class that extended a base class which was the registered one and hit this problem. I see the point that iterating for all sticky events can be troublesome… Ended up wrapping my Anonymous class in a well defined class that works fine with registerSticky… a bit overhead but works.


## mhrst (19 Mar 2014)

Also ran into this issue with registerSticky(). Thanks for the suggestion, carlosefonseca.


## vincenzovitale (25 Jun 2014)

Hi,
any news on this?

Thanks,
V.


## greenrobot (26 Feb 2015)

Solution is tested using new unit test. Let me know if you still have issues.


