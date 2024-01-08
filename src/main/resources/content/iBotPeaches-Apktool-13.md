#"screenSize" value for "configChanges" in AndroidManifest.xml lost

Owner: iBotPeaches

Repo: Apktool

Labels: Bug 

## Joebrave (16 Aug 2012)

```
    <activity android:name="Guide" android:exported="true" android:enabled="true"
        android:configChanges="orientation|keyboardHidden|navigation|screenSize" android:launchMode="singleTop" android:icon="@drawable/icon">
        <intent-filter>
            <action android:name="com.***.Guide"></action>
            <action android:name="android.intent.action.MAIN"></action>
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>
```

After decoded by apktool, the screenSize disapears as bellow:

```
   <activity android:name="Guide" android:icon="@drawable/icon" android:exported="true" android:enabled="true" android:configChanges="keyboardHidden|navigation|orientation" android:launchMode="singleTop">
        <intent-filter>
            <action android:name="com.***.Guide" />
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>
```


## iBotPeaches (26 Aug 2012)

I believe I've fixed this. Do you have an APK I can test with?


## Joebrave (27 Aug 2012)

Good news for me, thanks! But how can I give you the test APK? 


## Joebrave (27 Aug 2012)

Or, could you give me a APKTool build with the fix? My email address: xhcbrave@163.com, thanks!


## Joebrave (27 Aug 2012)

I can test this issue for you. 


## iBotPeaches (27 Aug 2012)

Just send me the apk to my email ibotpeaches (at) gmail (d0t) com.

I'm not giving out early releases of v1.5.0 till its complete.


## Joebrave (13 Sept 2012)

I have tried v1.5.0, but the issue seems to still exist. You could fetch the APK from email xhcbrave@gmail.com.


## iBotPeaches (13 Sept 2012)

Once again. Send me the APK. I'm not chasing people down for APKs.


## Joebrave (14 Sept 2012)

I sent the APK to your gmail yesterday, please check it, thanks! My email is xhcbrave@gmail.com


## iBotPeaches (14 Sept 2012)

ahh. Got it. 

It was in a different folder.


## Joebrave (19 Oct 2012)

How about this issue?


## iBotPeaches (19 Oct 2012)

Still looks open to me.


## BurgerZ (19 Oct 2012)

iBot, could you give me this apk for testing?


## iBotPeaches (19 Oct 2012)

I lost my HDD about 1.5 weeks ago. I don't have anything anymore. 


## BurgerZ (19 Oct 2012)

Oops... Sorry about that. 
Thats not the problem. Apktool will never be lost))


## iBotPeaches (19 Oct 2012)

Yeah, it was all on revision control. So didn't loose any of it. Just taking me awhile to get my Ubuntu computer set back up again.


## iBotPeaches (16 Mar 2015)

Closing before merge of GoogleCode issues.


