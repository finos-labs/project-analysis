#ProGuard and Retrofit don't enjoy each other...

Owner: square

Repo: retrofit

Labels: 

## thorinside (11 Dec 2012)

I think some documentation on proper proguard configuration for Retrofit would be great. Any idea what lines I would put into progaurd.cfg to avoid the following errors:

```
12-10 17:18:11.523: WARN/RestAdapter(3135): Method not annotated with GET, POST, PUT, or DELETE: public abstract void com.calgaryscientific.oncall.service.OnCallServiceAsync.deleteUser(java.lang.String,retrofit.http.Callback) from https://csi-oncall.herokuapp.com/
        java.lang.IllegalStateException: Method not annotated with GET, POST, PUT, or DELETE: public abstract void com.calgaryscientific.oncall.service.OnCallServiceAsync.deleteUser(java.lang.String,retrofit.http.Callback)
        at retrofit.http.RequestLine.fromMethod(RequestLine.java:64)
        at retrofit.http.HttpRequestBuilder.setMethod(HttpRequestBuilder.java:52)
        at retrofit.http.RestAdapter$RestHandler.invokeRequest(RestAdapter.java:137)
        at retrofit.http.RestAdapter$RestHandler.access$400(RestAdapter.java:102)
        at retrofit.http.RestAdapter$RestHandler$1.obtainResponse(RestAdapter.java:115)
        at retrofit.http.CallbackRunnable.run(CallbackRunnable.java:30)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1080)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:573)
        at retrofit.http.Platform$Android$2$1.run(Platform.java:93)
        at java.lang.Thread.run(Thread.java:856)
```


## dnkoutso (11 Dec 2012)

Can you try `--keep class retrofit.http.** { *; }` ?


## JakeWharton (11 Dec 2012)

Proguard is an advanced, powerful tool. It would be far too hard for us to maintain documentation on all of our libraries for proper usage. We recommend you just keep everything in each library until you are comfortable with tweaking and testing the values yourself. Keeping everything is especially useful in complex libraries like this since we rely heavily on reflection for certain aspects of the configuration which breaks with stripping and obfuscation.


## thorinside (11 Dec 2012)

Thanks Jake, you're right, ProGuard is dangerous. I started this application with the Android Bootstrap initially. Adding Retrofit seems to have required some or all of the following to be added, plus or minus:

```
-keep class com.google.gson.** { *; }
-keep class com.google.inject.** { *; }
-keep class org.apache.http.** { *; }
-keep class org.apache.james.mime4j.** { *; }
-keep class javax.inject.** { *; }
-keep class retrofit.** { *; }
```

I also added a -keep statement for the interface that I used to build my REST interface with Retrofit. All is working now.


## drindt (11 Sept 2014)

I've step tested my rules for retrofit for maximum proguard usage, i could let work my code as expected with the following rules:
<pre><code>
-keep class com.viselabs.aquariummanager.util.seneye.SeneyeService { _; }
-keep class com.viselabs.aquariummanager.util.seneye.model._\* { _; }
-keep class retrofit.http._\* { *; }
</pre></code>

The first line are your interface the second line are your models. This is very minimal to achieve maximum of proguard. It would nice to share experience about this. 

Thank you for reading.
Daniel 


## ralphhm (13 Jan 2015)

A more generic approach is to use

```
-keepclassmembernames interface * {
    @retrofit.http.* <methods>;
}
```

for the service interfaces so that you end up with following:

```
-keep class retrofit.** { *; }
-keep class package.with.model.classes.** { *; }
-keepclassmembernames interface * {
    @retrofit.http.* <methods>;
}
```


## daveztong (26 Feb 2015)

@ralphhm Your approach is quite handy


## ened (14 Mar 2015)

@JakeWharton Maybe publish it on the website http://square.github.io/retrofit/ ?


## JakeWharton (14 Mar 2015)

Not a ProGuard user. Someone can send a PR for it.

On Sat, Mar 14, 2015 at 12:21 AM Sebastian Roth notifications@github.com
wrote:

> @JakeWharton https://github.com/JakeWharton Maybe publish it on the
> website http://square.github.io/retrofit/ ?
> 
> —
> Reply to this email directly or view it on GitHub
> https://github.com/square/retrofit/issues/117#issuecomment-79839705.


## marcin-kozinski (24 Mar 2015)

There are still some missing pieces not mentioned above that my team has found out the hard way. This is our take:

```
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepattributes Signature
-keepattributes Exceptions
```

You need `-keepattributes Signature` if in your rest service interface you use methods with `Callback` argument. This argument is of generic type and Retrofit checks it at runtime. Proguard strips generic type information by default and this flag keeps it.

`-keepattributes Exceptions` is needed if your rest service methods throw custom exceptions, because you've defined an `ErrorHandler` that returns such exceptions. Again, at runtime Retrofit verifies if an exception returned by an `ErrorHandler` was declared on the interface. And again, Proguard by default strips information about exceptions declared on methods and this flag tells it to keep them.

You can find answers for specific problems on Stack Overflow once you come across them and have an exception to paste into Google. But this should help to prevent those problems for all the people who find this issue after searching Google with much more generic search term.

One other thing to note is that by default Retrofit uses GSON to convert server responses and you need some more Proguard configuration for GSON. But that is covered well on Stack Overflow and [by the GSON team](http://google-gson.googlecode.com/svn/trunk/examples/android-proguard-example/proguard.cfg) (though some of their configuration is not need on Android or do not apply to Android).

@JakeWharton I'd love to contribute our configuration to the Retrofit website. Is adding a new section for it between "Download" and "Contributing" all right? If not, it'd be great if you suggested an acceptable place for it.


## divyaansh (24 Mar 2016)

@JakeWharton, you said you are 

> Not a ProGuard user.

Do you use something else for obfuscating code?


## jemshit (22 Nov 2016)

As @ralphhm wrote, you have to keep web service Models from obfuscation, don't know why 
(android 6.0 does not require, android 4.1 requires. 4.1 returns null fields for all web response models in **onNext(LIst<MyModel) list){}** callback:
`-keep class package.with.model.classes.** { *; }`

Also note this, if you have Modules in your project, you have to write 
 `consumerProguardFiles  'proguard-rules.pro'` and no `minifyEnabled` text in `build.gradle` file of Modules

## ascmp (06 Jul 2017)

Following the suggestions here, when we use proguard with Retrofit (3.6.0) then I can see that the rest APIs paths are still kept readable. Any hint or suggestion on how to be able to make them obfuscated as well?

## JakeWharton (06 Jul 2017)

You cannot

On Thu, Jul 6, 2017, 7:09 AM Ale <notifications@github.com> wrote:

> Following the suggestions here, when we use proguard with Retrofit (3.6.0)
> then I can see that the rest APIs paths are still kept readable. Any hint
> or suggestion on how to be able to make them obfuscated as well?
>
> —
> You are receiving this because you were mentioned.
> Reply to this email directly, view it on GitHub
> <https://github.com/square/retrofit/issues/117#issuecomment-313366540>,
> or mute the thread
> <https://github.com/notifications/unsubscribe-auth/AAEEESFkSOoUNilOU30VKhhP7LdRrTmZks5sLMBVgaJpZM4AToWu>
> .
>


