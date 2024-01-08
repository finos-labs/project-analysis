#ProGuard configuration

Owner: greenrobot

Repo: EventBus

Labels: 

## intrications (10 Oct 2012)

I'd like to know if there's a better way but I had to add this to my proguard-project.txt file to stop the onEvent methods being stripped out:

```
-keepclassmembers class ** {
    public void onEvent(**);
}
```

Whatever the best method is should be added to the documentation.


## intrications (30 Oct 2012)

With version 2, I now also add:

-keepclassmembers class *\* {
   public void onEventMainThread(**);
}

and presumably the same would be needed for the other thread modes.


## greenrobot (16 Dec 2012)

Added to docs:
-keepclassmembers class *\* {
    public void onEvent_(_*);
}


## AndreasZeiser (07 May 2013)

If I add the documented proguard configuration, exporting a signed application fails with the following error:
`Conversion to Dalvik format failed with error 1`
Are you guys using further proguard configuration to get this working? I'm relying almost on Android's default and optimized proguard configuration + documented Actionbarscherlock proguard configuration.


## intrications (08 May 2013)

Have you tried exporting the signed application without Proguard to confirm Proguard is causing the problem?

I think that error can occur if you have multiple versions of the Android support library in your main project and in library projects. This is often due to ActionBarSherlock which is stuck with an old version of the support library.

See this question for an example: http://stackoverflow.com/questions/13430447/using-actionbarsherlock-and-the-support-library


## AndreasZeiser (08 May 2013)

No I did not, but I'm sure proguard is the problem. If I remove the suggested lines

```
-keepclassmembers class ** {
   public void onEvent*(**);
}
```

I'm able to export my signed application.


## wang2bo2 (08 May 2013)

Have you tried downloading the latest proguard?
http://sourceforge.net/projects/proguard/files/
and put the 3 jars from downloaded-folder/lib into android-sdk/tools/proguard/lib replacing the old ones.

This solved this error 1 for me in many cases.


## AndreasZeiser (10 May 2013)

@wang2bo2 thank you for this suggestion, I did not know about new version for proguard. However this did not solved my problem.

I don't know why but the error was related to another external library signpost. After I had excluded signpost of beeing optimized and obfuscated I can export signed applications.


## Steven-Mark-Ford (27 Nov 2014)

It works for me...Thanks. I would suggest you add this too the git project home page info. "if using proguard..."


## smistry-toushay (27 Feb 2015)

@AndreasZeiser's rule from https://github.com/greenrobot/EventBus/issues/6#issuecomment-17593941 worked for me. Same rule can be found in [HOWTO.md](https://github.com/greenrobot/EventBus/blob/master/HOWTO.md#proguard-configuration)


