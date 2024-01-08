#Allow checking if subscriber is already registered (isRegistered)

Owner: greenrobot

Repo: EventBus

Labels: confirmed enhancement 

## Qubitium (25 Dec 2012)

Should there be a "isRegistered()" method call to use in onResume() so I can check if the bus has been registered before registering?

Reason I ask is because of the following.

1) Assume ActivityFragments is an Activity that holds two panels, left and right, which we call LeftFragment and RightFragment. 

2) ActivityFragments launches and in onCreate, launches both fragments. 

3) In my case, the RightFragment always finish launching even before ActivityFragment has moved to the onResume() state where normally the bus would be registered. 

Because RightFragments completes before ActivityFragment can register the bus in onResume() , ActivityFragment can miss events from RightFragment. 

So, I move register() bus call to ActivityFragment's onCreate() code which fixes the chicken and egg problem but this fails the onPause() and onResume() cycles where Activity is no longer registered to the bus. 

Now I try to fix both cases, and have both bus registering during onCreate() and onResume() but this causes "already registered" exception in onResume(). 

Is there a way to check current registration problem without  resorting to try {} catch {} code?


## apkelly (13 Jan 2013)

If you only have one EventBus for the whole app could you register this in a custom Application class at startup? Or perhaps you could use EventBus.getDefault() whenever you need to reference the bus.


## aleung (13 Jan 2013)

Can it just allow re-register? It simplifies application development, because finding out all possible situation to unregister is trivial.


## greenrobot (16 Nov 2013)

added isRegistered in ac6a0a7a6c39c16ea63e8c61a808b4aa8ade71ba


