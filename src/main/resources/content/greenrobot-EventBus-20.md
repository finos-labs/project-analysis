#Multiple Registration

Owner: greenrobot

Repo: EventBus

Labels: more info required 

## saulpower (22 Feb 2013)

I ran into an issue of the same object being able to register multiple times and in turn a single event is calling onEvent() multiple times.  It seems like if I register an already registered object the EventBus should just ignore the register call.


## greenrobot (15 Nov 2013)

If you try register an already registered object you should get an exception. Need more info on this.


