#Difficulties properly importing

Owner: PhilJay

Repo: MPAndroidChart

Labels: 

## michaelryu (01 Aug 2014)

I imported this module to Android Studio and gradle finishes without problem but it isn't actually done right. None of things get properly imported and I can't even call for a LineChart.  None of the classes can be found on layout. 

Is there a special way to import this to Android Studio?

Thanks


## PhilJay (01 Aug 2014)

As I am not an Android Studio user I'm unable to evaluate your exact problem.
Maybe you could check out this post on stackoverflow and the related answers (especially the 2nd answer): http://stackoverflow.com/questions/16588064/how-do-i-add-a-library-project-to-the-android-studio

Please let me know if this helped you out.

Regards,
Phil


## smallzhan (01 Aug 2014)

Today I've imported the whole project ("the MPAndroidChart directory) to Android Studio, and successfully build an apk file of the example as well as the `MPChartLib.aar` library.   The `.aar` is the library similar as the `.jar` file, just add it to the `libs` directory and edit the following lines in the project's `build.gradle`

```
repositories {
    ...
    flatDir {
        dirs 'libs'
    }
}
dependencies {
    ....
    compile (name: 'MPChartLib', ext: 'aar')
}
```

It worked smoothly in my Android Studio project.

BTW, when building the `.aar`,  you'd better remove the support-v4.jar in the  `libs` directory, and add some line in `build.gradle`, or you may see some `Execution failed for task ':app:dexDebug'` when building your own project.


## dodocat (01 Aug 2014)

Tomorrow I'll create a pull request to fix this.


## PhilJay (03 Aug 2014)

I just merged the pull request from [**dodocat**](https://github.com/dodocat)  that should fix the problem and add gradle support.

Please let me know if you are still having trouble, or if it is now working.

Regards,
Phil


## michaelryu (03 Aug 2014)

unfortunately it still does not import  properly in to Android Studio.
Thanks for the help though.

On Sun, Aug 3, 2014 at 8:07 AM, Philipp J. notifications@github.com wrote:

> I just merged the pull request from _dodocat_ https://github.com/dodocat
> that should fix the problem and add gradle support.
> 
> Please let me know if you are still having trouble, or if it is now
> working.
> 
> Regards,
> Phil
> 
> —
> Reply to this email directly or view it on GitHub
> https://github.com/PhilJay/MPAndroidChart/issues/19#issuecomment-50992856
> .


## michaelryu (03 Aug 2014)

can you provide a step by step tutorial on importing the module to a
project?

On Fri, Aug 1, 2014 at 3:11 AM, Guoqiang Jin notifications@github.com
wrote:

> Today I've imported the whole project ("the MPAndroidChart directory) to
> Android Studio, and successfully build an apk file of the example as well
> as the MPChartLib.aar library. The .aar is the library similar as the .jar
> file, just add it to the libs directory and edit the following lines in
> the project's build.gradle
> 
> repositories {
>     ...
>     flatDir {
>         dirs 'libs'
>     }
> }
> dependencies {
>     ....
>     compile (name: 'MPChartLib', ext: 'aar')
> }
> 
> It worked smoothly in my Android Studio project.
> 
> BTW, when building the .aar, you'd better remove the support-v4.jar in
> the libs directory, and add some line in build.gradle, or you may see
> some Execution failed for task ':app:dexDebug' when building your own
> project.
> 
> —
> Reply to this email directly or view it on GitHub
> https://github.com/PhilJay/MPAndroidChart/issues/19#issuecomment-50869086
> .


## dodocat (04 Aug 2014)

No one can solve a problem without any information provided. Befoer submitting a question, review the message window output would be helpful, Maybe solution is just there. if still not, you should attache the output with your issue.

So, after reviewing you error message, if no solution found, check steps below:

As far as I know, you issue could be resoved in these step.
- latest android studio, latest android sdk, required sdk platform(android-19), required build tool (19.1.0), android repository in sdk. Most of  these problem could be automaticly solved  just by one click in the message window.
- when importing you should choose settings.grade.


## PhilJay (05 Aug 2014)

@awareApps: Can you be a bit more specific what the exact problem is you are experiencing?


