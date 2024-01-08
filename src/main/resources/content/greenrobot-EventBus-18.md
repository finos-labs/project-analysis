#EventBusMultithreadedTest failed with "already registered"

Owner: greenrobot

Repo: EventBus

Labels: bug 

## greenrobot (11 Feb 2013)

02-11 18:45:08.750: I/TestRunner(16666): started: testSubscribeUnSubscribeAndPostMixedEventType(de.greenrobot.event.test.EventBusMultithreadedTest)
02-11 18:45:08.800: W/Event(16666): Subscriber to unregister was not registered before: class de.greenrobot.event.test.EventBusMultithreadedTest$SubscribeUnsubscribeThread
02-11 18:45:08.800: W/dalvikvm(16666): threadid=47: thread exiting with uncaught exception (group=0x41d3b2a0)
02-11 18:45:08.840: E/AndroidRuntime(16666): FATAL EXCEPTION: Thread-60258
02-11 18:45:08.840: E/AndroidRuntime(16666): de.greenrobot.event.EventBusException: Subscriber class de.greenrobot.event.test.EventBusMultithreadedTest$SubscribeUnsubscribeThread already registered to event class java.lang.Object
02-11 18:45:08.840: E/AndroidRuntime(16666):    at de.greenrobot.event.EventBus.subscribe(EventBus.java:239)
02-11 18:45:08.840: E/AndroidRuntime(16666):    at de.greenrobot.event.EventBus.register(EventBus.java:175)
02-11 18:45:08.840: E/AndroidRuntime(16666):    at de.greenrobot.event.EventBus.register(EventBus.java:146)
02-11 18:45:08.840: E/AndroidRuntime(16666):    at de.greenrobot.event.test.EventBusMultithreadedTest$SubscribeUnsubscribeThread.run(EventBusMultithreadedTest.java:233)


## greenrobot (16 Nov 2013)

Couldn't reproduce this, could be related to #34 that was jsut fixed.


