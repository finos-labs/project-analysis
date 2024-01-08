#How to include RxAndroid in project?

Owner: ReactiveX

Repo: RxAndroid

Labels: 

## erizet (20 Oct 2014)

I'm using Android Studio. How can I include RxAndroid in my project as a subproject? I want to build it from the sourcecode.
What I've tried:
-Include it as a git submodule.
-included it in my projects settings.gradle file,  "include ':rxandroid', ':app'"

Then I get: Error:Unable to find module with Gradle path ':rxandroid'.


## ronshapiro (20 Oct 2014)

What's the output of `ls $(git rev-parse --show-toplevel)`? That might provide some insight into why `':rxandroid'` isn't the correct module name.

It's possible you need to replace

``` groovy
include ':rxandroid', ':app'
```

With something like

``` groovy
include ':RxAndroid', ':app'
```

due to the spelling of your directory. Assuming that works, in your `app/build.gradle` file, you'll need to add

``` groovy
dependencies {
    // ... any dependencies you already have
    compile project(':RxAndroid') // or whatever the correct module name is
}
```


## erizet (20 Oct 2014)

When doing ls I got rxandroid, all lower case. :( Dispite that I tried to change the module name to RxAndroid but I still got the same error.


## ronshapiro (21 Oct 2014)

I'm not sure what the best way to do this is, since the library has it's own settings.gradle file. May I ask why you want to build from source?


## erizet (21 Oct 2014)

Maybe my question should have been: How do I add new functionality and/or modify the library?
I want to include the library in my project and test some ideas that I have. Change things, add things and then run in the emulator to test these modifications.


## ronshapiro (21 Oct 2014)

One way, which is a little hassle, is to just open the repo in a separate project and make the changes, then install the output .jar to mavenLocal and then pull that into your app. You may be able to get away with removing this project's `settings.gradle` file from the submodule you cloned, just make sure not to actually commit that change.


## erizet (21 Oct 2014)

I understand that I can use the output jar, but it seems a little cumbersome. Is there anything in RxAndroid that makes this library harder to include than other libraries? Have a look here http://www.crowbarsolutions.com/importing-libraries-into-android-studio/


## erizet (21 Oct 2014)

After deleting the settings.gradle in the RxAndroid folder, then my project builds. :) I don't know why it doesn't work when the settings.gradle is available.
I'm curious about the development cycle you're using when developing this library. Do you use a local maven repo to include rxandroid?


## ronshapiro (22 Oct 2014)

I've only done extensions of the project, so I start off by writing the code in my app and then if it works out as planned, I pull it over.

I don't think Gradle is built for submodules that are full projects themselves, which is probably why the settings.gradle file needed to be dropped.

Installing locally to a maven repo isn't that big of a hassle - once you get the command down, you can iterate pretty fast (though it definitely isn't as fast as having it compiled all in one shot like what you are going for).

Here's an example command:

``` sh
mvn install:install-file \
    -Dfile=build/libs/rxandroid-0.22.1-dev.3+copyright-consistency-SNAPSHOT-sources.jar \ 
    -DgroupId=io.reactivex \
    -DartifactId=rx-android \
    -Dversion=0.22.1-SNAPSHOT \
    -Dpackaging=jar
```


## ronshapiro (22 Oct 2014)

@mttkay I think this is ready to be closed


## mttkay (22 Oct 2014)

Just a heads up that the JAR is published to Central, so you don't need to include this as a submodule unless you want to hack on the sources of the library


## erizet (22 Oct 2014)

Thank you. I know it's published to the Central. I was just searching for a simple way to make changes to the library and test those changes. As it turns out it is not that easy I became curious and want to know how you work when developing the lib.


