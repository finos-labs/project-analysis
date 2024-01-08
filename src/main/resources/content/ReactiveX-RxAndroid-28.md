#OnErrorNotImplementedException is not fatal for CalledFromWrongThreadException

Owner: ReactiveX

Repo: RxAndroid

Labels: 

## dlew (13 Oct 2014)

If `onError()` is called and you don't have an `onError()` defined, `OnErrorNotImplementedException` should get thrown and crash your program. However, there's one case in Android where this doesn't seem to happen, and I'm having a hard time figuring out why that's the case.

Here's some short example code which should cause an app to crash, but doesn't:

```
Observable.just(null)
    .delay(1, TimeUnit.SECONDS)
    .observeOn(Schedulers.io())
    .subscribe(__ -> someView.setBackgroundColor(Color.RED));
```

I've been trying to figure out why this exception gets consumed; normally if the `Subscriber` throws an `Exception` but has no error handling it will crash the app. But in this case, `CalledFromWrongThreadException` gets thrown and ignored. This leads to a lot of sadness because it can cause all sorts of weird interactions (since normally this is a fatal, unrecoverable issue on Android).

It may ultimately be an RxJava based problem, but I can't reproduce it outside of the context of `CalledFromWrongThreadException`. Any ideas on what's going on?


## nsk-mironov (14 Oct 2014)

`observeOn` is the problem here. There is a detailed explanation here https://github.com/ReactiveX/RxJava/issues/1682.


## dlew (14 Oct 2014)

Ah thanks, I'll close this up then. It's definitely the same issue.


## mttkay (15 Oct 2014)

We had similar problems. We use precondition checks in places that raise IllegalArgumentException, and we want these to crash the app at dev time to fail early.

This has been proven difficult, since these checks can fail on background threads. We put terrible hacks in place to still fail early on these kinds of errors.


