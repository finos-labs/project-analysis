#Dex Warnings

Owner: greenrobot

Repo: EventBus

Labels: 

## chrisjenx (03 Nov 2012)

Seems when the device dex's the class files it throws a load of warnings..

[2012-11-03 00:53:00 - Moupp] ActivityManager: Starting: Intent { act=android.intent.action.MAIN cat=[android.intent.category.LAUNCHER] cmp=com.moupp.android/.ui.activities.SplashActivity }
[2012-11-03 00:59:33 - Moupp] Dx 
trouble processing:
[2012-11-03 00:59:33 - Moupp] Dx bad class file magic (cafebabe) or version (0033.0000)
...while parsing de/greenrobot/event/AsyncPoster.class
...while processing de/greenrobot/event/AsyncPoster.class
[2012-11-03 00:59:33 - Moupp] Dx 
trouble processing:
[2012-11-03 00:59:33 - Moupp] Dx bad class file magic (cafebabe) or version (0033.0000)
...while parsing de/greenrobot/event/BackgroundPoster.class
...while processing de/greenrobot/event/BackgroundPoster.class
[2012-11-03 00:59:33 - Moupp] Dx 
trouble processing:
[2012-11-03 00:59:33 - Moupp] Dx bad class file magic (cafebabe) or version (0033.0000)
...while parsing de/greenrobot/event/EventBus$1.class
...while processing de/greenrobot/event/EventBus$1.class
[2012-11-03 00:59:33 - Moupp] Dx 
trouble processing:
[2012-11-03 00:59:33 - Moupp] Dx bad class file magic (cafebabe) or version (0033.0000)
...while parsing de/greenrobot/event/EventBus$2.class
...while processing de/greenrobot/event/EventBus$2.class
[2012-11-03 00:59:33 - Moupp] Dx 
trouble processing:
[2012-11-03 00:59:33 - Moupp] Dx bad class file magic (cafebabe) or version (0033.0000)
...while parsing de/greenrobot/event/EventBus$3.class
...while processing de/greenrobot/event/EventBus$3.class
[2012-11-03 00:59:33 - Moupp] Dx 
trouble processing:
[2012-11-03 00:59:33 - Moupp] Dx bad class file magic (cafebabe) or version (0033.0000)
...while parsing de/greenrobot/event/EventBus$BooleanWrapper.class
...while processing de/greenrobot/event/EventBus$BooleanWrapper.class
[2012-11-03 00:59:33 - Moupp] Dx 
trouble processing:
[2012-11-03 00:59:33 - Moupp] Dx bad class file magic (cafebabe) or version (0033.0000)
...while parsing de/greenrobot/event/EventBus$PostCallback.class
...while processing de/greenrobot/event/EventBus$PostCallback.class
[2012-11-03 00:59:33 - Moupp] Dx 
trouble processing:
[2012-11-03 00:59:33 - Moupp] Dx bad class file magic (cafebabe) or version (0033.0000)
...while parsing de/greenrobot/event/EventBus.class
...while processing de/greenrobot/event/EventBus.class
[2012-11-03 00:59:33 - Moupp] Dx 
trouble processing:
[2012-11-03 00:59:33 - Moupp] Dx bad class file magic (cafebabe) or version (0033.0000)
...while parsing de/greenrobot/event/EventBusException.class
...while processing de/greenrobot/event/EventBusException.class
[2012-11-03 00:59:33 - Moupp] Dx 
trouble processing:
[2012-11-03 00:59:33 - Moupp] Dx bad class file magic (cafebabe) or version (0033.0000)
...while parsing de/greenrobot/event/HandlerPoster.class
...while processing de/greenrobot/event/HandlerPoster.class
[2012-11-03 00:59:33 - Moupp] Dx 
trouble processing:
[2012-11-03 00:59:33 - Moupp] Dx bad class file magic (cafebabe) or version (0033.0000)
...while parsing de/greenrobot/event/NoSubscriberEvent.class
...while processing de/greenrobot/event/NoSubscriberEvent.class
[2012-11-03 00:59:33 - Moupp] Dx 
trouble processing:
[2012-11-03 00:59:33 - Moupp] Dx bad class file magic (cafebabe) or version (0033.0000)
...while parsing de/greenrobot/event/PendingPost.class
...while processing de/greenrobot/event/PendingPost.class
[2012-11-03 00:59:33 - Moupp] Dx 
trouble processing:
[2012-11-03 00:59:33 - Moupp] Dx bad class file magic (cafebabe) or version (0033.0000)
...while parsing de/greenrobot/event/PendingPostQueue.class
...while processing de/greenrobot/event/PendingPostQueue.class
[2012-11-03 00:59:33 - Moupp] Dx 
trouble processing:
[2012-11-03 00:59:33 - Moupp] Dx bad class file magic (cafebabe) or version (0033.0000)
...while parsing de/greenrobot/event/SubscriberExceptionEvent.class
...while processing de/greenrobot/event/SubscriberExceptionEvent.class
[2012-11-03 00:59:33 - Moupp] Dx 
trouble processing:
[2012-11-03 00:59:33 - Moupp] Dx bad class file magic (cafebabe) or version (0033.0000)
...while parsing de/greenrobot/event/SubscriberMethod.class
...while processing de/greenrobot/event/SubscriberMethod.class
[2012-11-03 00:59:33 - Moupp] Dx 
trouble processing:
[2012-11-03 00:59:33 - Moupp] Dx bad class file magic (cafebabe) or version (0033.0000)
...while parsing de/greenrobot/event/SubscriberMethodFinder.class
...while processing de/greenrobot/event/SubscriberMethodFinder.class
[2012-11-03 00:59:33 - Moupp] Dx 
trouble processing:
[2012-11-03 00:59:33 - Moupp] Dx bad class file magic (cafebabe) or version (0033.0000)
...while parsing de/greenrobot/event/Subscription.class
...while processing de/greenrobot/event/Subscription.class
[2012-11-03 00:59:33 - Moupp] Dx 
trouble processing:
[2012-11-03 00:59:33 - Moupp] Dx bad class file magic (cafebabe) or version (0033.0000)
...while parsing de/greenrobot/event/ThreadMode.class
...while processing de/greenrobot/event/ThreadMode.class


## greenrobot (03 Nov 2012)

Did you use the prebuild jar?


## chrisjenx (03 Nov 2012)

Yes 2.0.0, should I be including the project as a library?


## greenrobot (03 Nov 2012)

Jar should be fine. Could you please try the jar for 2.0.1 and let me know if you still get those warning? Thanks. https://github.com/greenrobot/EventBus/downloads


## chrisjenx (03 Nov 2012)

Sorted! Cheers :+1: 


