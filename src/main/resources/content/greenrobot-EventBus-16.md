#Notifications support

Owner: greenrobot

Repo: EventBus

Labels: 

## DenisMir (18 Jan 2013)

It would be great if EventBus would support some kind of fallback method when the UI can not be reached. In the most cases apps would want other components to do something in the background. The result gets delivered via an EventBus but what if the UI is no longer available. The app would possibly show a notification. After having tested all the different EventBus libraries I came back to the plain old android broadcasts. (one BroadcastReceiver in the activity and another BroadcastReceiver in the background having the lower priority) Depending on the availability of the UI the result gets handled correctly.

Am I missing something or is an event bus not the correct way to reach the above mentioned goal in this case and the broadcast pattern is still the most elegant one?


## doridori (18 Jan 2013)

In what use case do have an advantage when using broadcast receivers?


## DenisMir (18 Jan 2013)

Lets assume one component is checking for news. (just an example) If the broadcast gets caught by the Activity I would show a dialog otherwise the BroadcastReceiver would set a notification. I don't want to show the notification all the time. Using the above mentioned pattern this is pretty easy.


## Leonidos (30 Jan 2013)

IMHO every application should implement such feature by itself if needed. It's easy to do. Notifications is not what event bus should care about.


## doridori (30 Jan 2013)

Ok, well you can register for an [NoSubscriberEvent](https://github.com/greenrobot/EventBus/blob/master/EventBus/src/de/greenrobot/event/NoSubscriberEvent.java) in that case :)


## DenisMir (31 Jan 2013)

That was what I was looking for. Great. :+1: 


