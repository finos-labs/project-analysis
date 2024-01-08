#Could not dispatch event

Owner: greenrobot

Repo: EventBus

Labels: more info required 

## croemmich (02 Jul 2013)

I'm likely doing something wrong.. but I have been trying to debug this for almost 10 hours now... about ready to give up.

I have a setup like this:

Activity:
  onCreate:
    - mView = findViewById(R.id.view)
    - register subscriber
    - start event poster

  onEventMainThread
    -mView.setVisibility(View.GONE)

Event Poster:
Events are posted inside a runnable that is executed by a cached thread pool.

I get this exception:
Could not dispatch event: class com.example.MyEvent to subscribing class class com.example.MyActivity
        java.lang.NullPointerException
at mView.setVisibility

Somehow mView is null. It's not, as I can call "mView.setVisibility(View.GONE)" in onCreate. Is this somehow a bug with EventBus?


## greenrobot (14 Nov 2013)

How could EventBus post to an object without a registration? Very strange. Any additional information on this? If not, I have to close this issue.


## croemmich (23 Apr 2014)

Somehow I got it working. Don't remember anymore. You can close this issue. Thanks!


## StayInLove (14 Apr 2016)

I hava this question ,in myProject I use butterknife,how can i fix it


