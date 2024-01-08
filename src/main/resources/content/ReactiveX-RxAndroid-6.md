#Chaining of Observable outputs

Owner: ReactiveX

Repo: RxAndroid

Labels: 

## sagarrao (26 Aug 2014)

Hi,

We were looking to leverage the reactive model using hystrix for one of our use cases. The scenario is that we poll an API, which basically returns a List of hasmaps of events of sports. An event is something which takes place every season, so in case of tennis, a US Open would be an event.

Once we get that list, we iterate over that List of hashmap of events, take an individual event and invoke another API to fetch the matches within that event. So, all the 128 matches to be played in the US Open would be returned here. Eventually, we want to persist the Event object with the List of competitions in our Mongo Datastore/.

Now, we have created a separate HystrixCommand for each of the API calls and also for the persist part. So, we are trying to keep things in as asynchronous a manner as possible.

But, this is where the problem starts.The fixtures API expects an eventId as part of its url. Now, the problem is, that how can I send that List of Competition object back to the original callee event in a non-blocking manner? I found one way : <observable-object>.toBlockingObservable().single(), which basically is able to return the emitted the List<Competition> from this observable to the callee. But, this makes the call blocking which also seems to stall the original iteration of the event for loop.

Is there any non-blocking way of doing this? I read some of the examples and found a groovy based one for Videos using map(). Is that the only way or would some tweak in my original approach suffice?


