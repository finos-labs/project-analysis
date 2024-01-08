#RxAndroid Build

Owner: ReactiveX

Repo: RxAndroid

Labels: 

## benjchristensen (22 Aug 2014)

Should the Jar be named rxjava-android-x.y.z.jar or rxandroid-x.y.z.jar ?


## mttkay (09 Sept 2014)

Push away :-)


## quidryan (09 Sept 2014)

It's primarily for publishing, and I'm assuming that you don't need to
publish the acceptance-tests.

On Tue, Sep 9, 2014 at 8:20 AM, Matthias Käppler notifications@github.com
wrote:

> Oh okay. I assume applying the rxjava project plugin was mandatory for the
> contrib projects? Haven't looked to closely at what it does, but it appears
> to inject config for release builds.
> 
> —
> Reply to this email directly or view it on GitHub
> https://github.com/ReactiveX/RxAndroid/issues/2#issuecomment-54985364.


## mttkay (09 Sept 2014)

:+1: 


## mttkay (09 Sept 2014)

Thanks @quidryan !

For some reason the project import now always fails with an obscure Gradle error (that's in IntelliJ 13)

```
/Users/matthiaskappler/Projects/RxAndroid/acceptance-tests/build.gradle
Error:(6, 0) Cause: com.google.common.io.Closeables.closeQuietly(Ljava/io/Closeable;)V
```

I searched the web for the problem, and there was a similar issue being reported and supposedly fixed in a newer version of IntelliJ. I installed that, but it's still giving me this error. Reading the issue tracker, it seems to be originating in conflicting versions of Guava being used in the Android Gradle plugin and the Gradle tool APIs that ship with IntelliJ? What IDE are you using?


## quidryan (09 Sept 2014)

Pushed and visible on #10. I haven't actually tested with the Android SDK (still downloading). This might also affect publishing, but we can deal with that later.


## mttkay (09 Sept 2014)

Here's the ticket: http://youtrack.jetbrains.com/issue/IDEA-127374

It's the only relevant reference I could find for this error message.


## quidryan (09 Sept 2014)

For this I'm not using an IDE. I typically use IntelliJ. I'll try loading it into an IDE later today.


## mttkay (09 Sept 2014)

Thanks! Really appreciate your help with all this!


## mttkay (09 Sept 2014)

Actually, it fails on the command line too (for me)

```
> gradlew clean build                                                                                                                                        ~/Projects/RxAndroid

FAILURE: Build failed with an exception.

* Where:
Build file '/Users/matthiaskappler/Projects/RxAndroid/acceptance-tests/build.gradle' line: 6

* What went wrong:
A problem occurred evaluating project ':acceptance-tests'.
> com.google.common.io.Closeables.closeQuietly(Ljava/io/Closeable;)V

* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.

BUILD FAILED
```

I'll keep digging around for more info on this problem.


## mttkay (09 Sept 2014)

Yeah, it's most likely a problem with plugin interop. The error disappears as soon as I take out `apply plugin: com.application.android` and reimport the project. The whole Java heritage thing with Android never really worked out to begin with...

Any ideas as to what could be causing the friction? Could it be the same issue as reported against IntelliJ, i.e. a conflicting version of Guava used in both the Nebula plugins and the Android Gradle plugin?


## quidryan (09 Sept 2014)

Once I download the SDK, I get that error. Should be straight-forward to work around, just have to find the right version of guava.


## mttkay (10 Sept 2014)

So, this is getting weird. I tried to exclude the Guava dependency from the Android Gradle plugin, but that didn't help. I then double checked whether it would even depend on Guava, and it seems like it doesn't? I looked at

https://bintray.com/bintray/jcenter/com.android.tools.build:builder

and

https://bintray.com/bintray/jcenter/com.android.tools.build:gradle

but neither depend on Guava. So either one of these pulls Guava in as a 3rd level dependency, or the problem is elsewhere.


## benjchristensen (15 Sept 2014)

> Is the rxjava-android package going to be upgraded to v1.x once the new io.reactivex:rxjava package is out of the RC phase? Will it also be moved under the new namespace at that time?

@austynmahoney yes


## mttkay (18 Sept 2014)

> I'll see what I can do to disable that plugin.

@quidryan Just following up to check if you had time to look into this? I'm kind of stuck.


## mttkay (18 Sept 2014)

@benjchristensen are you okay with me removing the `0.x` branch and switching it to master (and make it the GH default) now that the library is bumped to `1.x`?


## benjchristensen (18 Sept 2014)

If you prefer `master` go ahead.


## benjchristensen (01 Oct 2014)

I was going to publish RxAndroid but see that there is a bunch of stuff going on with the other branch. That branch isn't working, and even when I comment out the "acceptance-tests" project the artifact names for rxandroid seem wrong:

```
ls rxandroid/build/libs/
rxandroid-benchmarks.jar    rxandroid-javadoc.jar       rxandroid-sources.jar       rxandroid.jar
```


## mttkay (01 Oct 2014)

I think we can go ahead with master for now Ben. The branch introduced
acceptance tests, but as I suspected there were issues with compatibility
between the Android Gradle plugin and Nebula.

This shouldn't stop the project from moving forward, however, so feel free
to publish whatever is in master.


## benjchristensen (01 Oct 2014)

Ok. I'll proceed then as RxAndroid 0.21.0 to carry on from rxjava-android 0.20. 


## benjchristensen (01 Oct 2014)

RxAndroid 0.21 has been released. https://github.com/ReactiveX/RxAndroid/releases/tag/v0.21.0


