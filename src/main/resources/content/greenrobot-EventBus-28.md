#Feature suggestion: add ordered broadcast equivalent

Owner: greenrobot

Repo: EventBus

Labels: enhancement 

## commonsguy (03 Jun 2013)

A fairly clean pattern for a service to either update the foreground UI or raise a `Notification` is to use an ordered broadcast. It'd be nice to have an in-process-only equivalent of this, and `LocalBroadcastManager` doesn't do ordered broadcasts.

Have you considered a variation on an EventBus event that works like ordered broadcasts?

Thanks!


## greenrobot (14 Nov 2013)

So how could this look like API-wise? Like suggested in #41, subscribers could provide an optional priority.

register(Object subscriber, int priority)

What do you think?


## commonsguy (14 Nov 2013)

That certainly works. No need to be fancy. :-)

If you were concerned about allowing the priority to be an arbitrary value, even a simple `enum`  (high, normal, low) might suffice.

For my specific use case (raising a `Notification` if no UI component responded to an event), addressing issue #31 cover it. That being said, I am sure that there are other use cases for ordered event receivers beyond that.


## greenrobot (17 Nov 2013)

To be released in 2.2.0.

Note, that there's also a new abortEventDelivery to let (higher priority) subscribers to decide if the event should be delivered to other subscribers.


