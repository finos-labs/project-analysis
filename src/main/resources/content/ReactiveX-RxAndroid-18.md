#Defining the RxAndroid project scope

Owner: ReactiveX

Repo: RxAndroid

Labels: help wanted question 

## mttkay (01 Oct 2014)

This came out of #12 and #15  

There's two discussions forming around project scope, aka "what should be in the library". I think maybe it's time to carve out a mission statement / goal that people agree on? I'll start.

I'm more on the conservative side I guess, by which I mean I don't think that RxAndroid should become anything framework-y. I think RxAndroid should provide the minimal set of classes required to lift existing building blocks that Android provides for object messaging, concurrency, and error handling into "Rx language". For example: `HandlerThreadScheduler` makes sense, because it is able to take one Android concept (Handler/Message) and adapt it to blend seamlessly into Rx concepts (Schedulers/Observables.) _But it accomplishes the same thing._ The same can be said for things such as Intent/Broadcasts, view listeners, etc.

In short: allow to express Android things in terms of Rx, but don't introduce new concepts or make assumptions about non-Rx questions, such as application structure. That's framework land. I'm not saying that this is not useful; it can be useful, but I would vote to have it in a separate project.

I also have a few things that I'm currently struggling to decide whether they would be a good fit for this library, or whether they should live elsewhere, things we apply to our entire codebase but which go beyond Rx (the two most important ones for us being an event bus based on Subjects, and an operator that supports paging of observables.)

Opinions?


## jdreesen (01 Oct 2014)

:+1: for RxAndroid to be a library serving the basic building blocks for general Rx-ish use cases for the Android plattform. This fits most to what RxJava is. Any more on top of that can be in separate, dedicated repos for more specific use cases. But maybe we can add some links to those in the wiki?


## austynmahoney (01 Oct 2014)

:+1: for keeping it focused on the features that intersect RxJava and Android. We could always create another library for implementation details. An example project could also host these concepts and/or best practices.


## Wavesonics (01 Oct 2014)

Ya I think it makes sense to keep things general and toolkity, and then have a 3rd party lib that layers on top of the base. IMO RxAndroid should layer on top of RxJava, so you include RxJava, RxAndroid, and then maybe this 3rd best practices lib RxAndroidSuper. Instead of how RxAndroid currently contains RxJava.


## austynmahoney (01 Oct 2014)

@Wavesonics RxAndroid does not "contain" RxJava. It includes RxJava as a compile time dependency. This is the correct way to structure it.


## Wavesonics (01 Oct 2014)

@austynmahoney oh got ya, that makes more sense now.


## dlew (02 Oct 2014)

I agree w/ the general sentiment here - keep it conservative. It's always easier to add functionality to a library than to remove it. RxAndroid should be an easy call for Android developers to add without any excess baggage.

The only reason the opposing desire exists is because we are all having to solve the same problems (e.g. lifecycle). But there are so many ways to solve it that depend greatly on how your app is setup. Plus Rx adoption is still in its infancy; I have a feeling that even if we _did_ try to solve these problems, someone would come up with a better solution in a few months.

People can always create their own solutions on top of RxAndroid.


## dlew (02 Oct 2014)

I don't know if this is the right place to discuss it, but should we get rid of deprecated code before 1.0 as well? To make the library as minimal as possible.


## dpsm (02 Oct 2014)

+1 regarding @mttkay points and @dlew that's a good point. @mttkay thoughts?


## jdreesen (02 Oct 2014)

@mttkay already removed the deprecated code in 5eaa15c and 53bc707 so it's not included in [v0.21](https://github.com/ReactiveX/RxAndroid/releases/tag/v0.21.0) which depends on RxJava 1.0 ;)


## mttkay (05 Oct 2014)

Good to see everyone being aligned so far! I'll leave this open for a bit longer but I think it'll help us make decisions on common ground going forward.


## reixa00 (16 Oct 2014)

+1 to @dlew point. There´s always time to create a framework on top of RxAndroid. Also, I think that it should be keept as minimal as possible since it´s not easy to get used to it, at first to gain some adoption between devs.


## mttkay (17 Oct 2014)

It seems there is total alignment on this. I'll close this out.

Anyone late and highly opinionated, feel free to rekindle the discussion :-)


