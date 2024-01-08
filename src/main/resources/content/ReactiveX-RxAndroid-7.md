#`ViewObservable.text()` does not work with `Observable.delay()`

Owner: ReactiveX

Repo: RxAndroid

Labels: 

## ronshapiro (26 Aug 2014)

I think I was a little too optimistic when I wrote `ViewObservable.text()` to emit the view itself, because it doesn't capture any value in it's original state, which is problematic when using transformations like `.delay()`. Here's one example:

``` java
ViewObservable.text(myTextView)
        .map(TextView::getText)
        .delay(500, TimeUnit.MILLISECONDS)
        .filter(delayedText -> TextUtils.equals(delayedText, myTextView.getText())
        .subscribe(delayedText -> Log.d(TAG, "TextView paused editting at: " + delayedText);
```

This block is attempting to find when the user has not typed a new character in a .5 second window, but it doesn't work because the `.map()` also gets delayed and the call to `TextView.getText()` returns the most recently updated text, not the text .5 seconds ago. Using `ViewObservable.input()` instead of the first two lines does work.

My goal when I wrote https://github.com/ReactiveX/RxJava/pull/1545 was to provide flexibility to get a `CharSequence` as opposed to just a `String` from `ViewObservable.input(TextView)` but I figured just emitting the `TextView` itself was more flexible. This is likely problematic for lots of Rx operations where you want to emit immutable values, especially when they are shared across multiple threads. 

I tried looking at #5 but I'm not sure this works for all cases. I think it's probably good to have a different solution that captures the text change value immediately and also emits a `CharSequence`. Anyone have any suggestions on the best way to ideally avoid breaking changes?


## zsxwing (26 Aug 2014)

`delay(delay, unit, AndroidSchedulers.mainThread())` can solve your problem. It will run the work in the UI thread.


## nsk-mironov (26 Aug 2014)

It was my mistake to make `ViewObservable.input` to return `Observable<String>` instead of `Observable<CharSequence>` and I agree that it should `Observable<CharSequence>`. But I don't like the idea that a `TextView` itself should be emitted. Then reason I made `ViewObservable.clicks` to return `Observable<View>` is that there is no any another value to emit when the `View` is clicked. Of course, it could be `Observable<Void>` but it seems a bit weird for me.

I think we should change the return type of `ViewObservable.input` to `Observable<CharSequence>` (and probably rename to `ViewObservable.text`) and remove the existing `ViewObservable.text` method. I know that it's a breaking change, but it shouldn't be difficult to migrate.


## ronshapiro (01 Sept 2014)

I did a little bit more investigation into this and `delay(delay, unit, AndroidSchedulers.mainThread())` did not work either. The `Scheduler` parameter only schedules the delaying, but the issue is the mutable value being emitted since `.getText()` can return an `Editable`.

If you include an extra map to `.map(CharSequence::toString()` or `.map(SpannableString::new)` before the `.delay()`, everything works as intended. I think the best solution is to return a cloned `CharSequence` via `new SpannableString(CharSequence)`, or to return the instance directly and include a disclaimer that it may be mutable.


