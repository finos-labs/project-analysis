#ButterKnife and ProGuard (again)

Owner: JakeWharton

Repo: butterknife

Labels: 

## fabiencheret (28 Mar 2013)

Hey.

I'm trying to run proguard on my application, which use butterknife.
I added 
`-keep class **$$ViewInjector`
to the proguard config file, and I have this error

Android Dex: [ctc-mobile-android] Warning: butterknife.Views$InjectViewProcessor: can't find superclass or interface javax.annotation.processing.AbstractProcessor

I don't know what to do here...
If you guys have a solution...
Thanks.


## JakeWharton (28 Mar 2013)

Add

```
-dontwarn butterknife.Views$InjectViewProcessor
```

Dupe #16.


## smithliu (07 Nov 2017)

Thank you

