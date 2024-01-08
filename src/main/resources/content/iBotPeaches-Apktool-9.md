#apktool 1.4.9 & 1.4.8 decodes AndroidManifest.xml in not well-formated XML

Owner: iBotPeaches

Repo: Apktool

Labels: 

## huawuxin (31 Jul 2012)

When I use apktool 1.4.9 Release and 1.4.8 Beta to decompile some apks, I got not well-formated AndroidManifest.xml.

For example, I got:
<?xml version="1.0" encoding="utf-8"?>

<manifest android:versionName="3.1.0" android:versionCode="1004" package="com.samsung.inputmethod" android:sharedUserId="android.uid.system" coreApp="true"
  xmlns:android="http://schemas.android.com/apk/res/android"

> ```
> <uses-permission android:name="android.permission.VIBRATE" />
> <uses-permission android:name="android.permission.INJECT_EVENTS" />
> ```

```
<application android:icon="@drawable/app_icon" android:label="@string/ime_name"
```

> ```
>     <service android:name=".SimeDecoderService" android:exported="true"
> 
>         <intent-filter>
>             <action android:name="com.samsung.inputmethod.Decoder_Service" />
>             <category android:name="android.intent.category.DEFAULT" />
>         </intent-filter>
>     </service>
> ```

```
    <service android:name=".SamsungIME" android:label="@string/ime_name" android:permission="android.permission.BIND_INPUT_METHOD"
```

> ```
>         <intent-filter android:priority="1">
>             <action android:name="android.view.InputMethod" />
>         </intent-filter>
>         <meta-data android:name="android.view.im" android:resource="@xml/method" />
>     </service>
> ```
> 
> ...

For the same apk, go back to use apktool 1.4.7 Release to do decompile, the result is good:
<?xml version="1.0" encoding="utf-8"?>
<manifest android:sharedUserId="android.uid.system" android:versionCode="1004" android:versionName="3.1.0" package="com.samsung.inputmethod" coreApp="true"
  xmlns:android="http://schemas.android.com/apk/res/android">
    <uses-permission android:name="android.permission.VIBRATE" />
    <uses-permission android:name="android.permission.INJECT_EVENTS" />
    <application android:label="@string/ime_name" android:icon="@drawable/app_icon">
        <service android:name=".SimeDecoderService" android:exported="true">
            <intent-filter>
                <action android:name="com.samsung.inputmethod.Decoder_Service" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </service>
...

Please investigate and fix it. Thank you.


## iBotPeaches (31 Jul 2012)

Strange. I reverted this change in v1.4.9 as it caused un-needed whitespace. Could you re-confirm with new version otherwise attach affected apk and framework here? 

Thanks 


## huawuxin (31 Jul 2012)

I did tried 1.4.9 before I filed this.

I'm sorry I'm new to github and I don't know how to add attachment here.

It's a 767KB apk, no framework needed.


## huawuxin (31 Jul 2012)

Please try get the apk from https://hotfile.com/dl/165044653/89d7ed3/SamsungChineseIME.apk.html


## xiaolu (08 Aug 2012)

I also found this issue


## iBotPeaches (21 Aug 2012)

fixed and released on v1.9.10


