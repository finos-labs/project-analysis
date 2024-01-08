#`delay` not working for ViewObservable?

Owner: ReactiveX

Repo: RxAndroid

Labels: 

## finian (26 Aug 2014)

Sample codes below:

``` java
EditText editText = ...
ViewObservable.text(editText)
    .delay(1000, TimeUnit.MILLISECONDS)
    .subscribe(new Action1<EditText>() {
      @Override
      public void call(EditText editText) {
        Log.d("TAG", editText.getText().toString());
      }
    });
```

Nothing printed out as I type in the editText. But if I comment out the `delay` line, everything works fine. am I missing something?


## Yarikx (26 Aug 2014)

It's probably the issue with the main thread. action after `delay` is executed on `computation` scheduler (see https://github.com/ReactiveX/RxJava/wiki/Scheduler#default-schedulers-for-rxjava-observable-operators)
So you end up working with view not from main thread.

Try to put `.observeOn(AndroidSchedulers.mainThread())` after `delay` line.

Update: or you can retrieve text value from `EditText` via `map` operator before the `delay`


## finian (26 Aug 2014)

@Yarikx :+1: Thx! Problem solved :-)


