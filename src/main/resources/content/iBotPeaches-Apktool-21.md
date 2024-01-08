#Phone.apk from MoKee OS (API 10) and JellyBean (API 16) Bluetooth.apk (SGS3) - wrong styles in Manifest.

Owner: iBotPeaches

Repo: Apktool

Labels: 

## BurgerZ (05 Sept 2012)

Hi.
apktool decodes styles in wrong format:
1. Bluetooth.apk from SGS3 JB AOKP ROM
in AndroidManifest.xml we see "@_android:style/AlertDialog.DeviceDefault.Light.ProgressDialog", but we need "@_android:style/AlertDialog.DeviceDefault.Light"
After changing that strings Phone.apk compiles w/o errors.
1. Phone.apk from MoKee OS (API 10, 2.3.7 based)
   We see in manifest "@*android:style/Theme.Phone" but I don't know what value will be right for this theme style.

If you need I could provide all these apks.


## iBotPeaches (05 Sept 2012)

This was an intentional change and shouldn't affect functionality. 

Not a bug, unless the changes in manifest cause problems. I may have to do all the changes except in manifest. 


## BurgerZ (05 Sept 2012)

With such decompiled manifest I can't compile these apks. 
I have errors like: 
Phone.apk\res\values\styles.xml:170: error: Error retrieving parent for item: No resource found that matches the given name '@*android:style/Theme.Phone' etc...


## iBotPeaches (05 Sept 2012)

Ah. I'm sorry. Guess that change can't be made to the manifest. 

Will get this fixed by the end of this week. 

Sent me the framework(s) and that apk please. 


## BurgerZ (05 Sept 2012)

Here you go - https://www.dropbox.com/s/vwbwzsvt6tk8k4u/phone_and_framework.zip


## iBotPeaches (05 Sept 2012)

```

apktool if frpeaches@turf:~/Downloads/apktool/phone_and_framework$ apktool if frpk work-res.ap
I: Framework installed to: /home/peaches/apktool/framework/1.apk
peaches@turf:~/Downloads/apktool/phone_and_framework$ apktool d Phone.apk 
I: Baksmaling...
I: Loading resource table...
W: Skipping "android" package group
I: Loaded.
I: Decoding AndroidManifest.xml with resources...
I: Loading resource table from file: /home/peaches/apktool/framework/1.apk
I: Loaded.
I: Decoding file-resources...
I: Decoding values */* XMLs...
I: Done.
I: Copying assets and libs...
peaches@turf:~/Downloads/apktool/phone_and_framework$ apktool b Phone
I: Checking whether sources has changed...
I: Smaling...
I: Checking whether resources has changed...
I: Building resources...
I: Building apk file...
peaches@turf:~/Downloads/apktool/phone_and_framework$ 
```

Couldn't duplicate :/ Your aapt must be out of date.


## BurgerZ (06 Sept 2012)

"Your aapt must be out of date."
My aapt is 5.8Mb from MIUI (MiCode/patchrom_tools) - https://github.com/MiCode/patchrom_tools/blob/ics/aapt.exe


## BurgerZ (06 Sept 2012)

Oh, please, tell me what theme is in your manifest? Line 170
Or may be you could provide me with your aapt (win, linux). And will it (ur aapt) be good in recompiling MIUI roms?


## iBotPeaches (06 Sept 2012)

Your aapt is out of date. If you read my post on XDA. I made a new version (Linux only) so far. 

http://miui.connortumbleson.com/other/apktool/aapt/linux/aapt

Theme could be different for all APKs. Not important.


## BurgerZ (07 Sept 2012)

OK. Phone.apk is from GB (not important for me).
What about Bluetooth.apk from JB?
Styles there are also wrong decompiled. Aapt - official Google latest aapt.


## iBotPeaches (07 Sept 2012)

styles.xml is correct. There is no problem here.


## BurgerZ (07 Sept 2012)

Ok.
I mean in AndroidManifest.xml we see "@_android:style/AlertDialog.DeviceDefault.Light.ProgressDialog", but we need "@_android:style/AlertDialog.DeviceDefault.Light"
Your 1.5.0 apktool and aapt


## iBotPeaches (07 Sept 2012)

Why do you need that? Its correct.


## BurgerZ (07 Sept 2012)

I wrote the GUI tool to work with android ROMs - translation, decompiling, compiling, deodexing, also (de)compiling single files (apk and jar) in Java. 
Based on apktool.
@_android:style/AlertDialog.DeviceDefault.Light.ProgressDialog - is not correct.
@_android:style/AlertDialog.DeviceDefault.Light - that is correct, but your apktool decompiles it as @*android:style/AlertDialog.DeviceDefault.Light.ProgressDialog


## iBotPeaches (07 Sept 2012)

It's correct. 

That is right. 


## BurgerZ (07 Sept 2012)

May be we do not understand each other.
It's not correct at all.
After decompiling some of JellyBean apks we can't compile them back. The reason is wrong styles recognition in manifest.
There is no standart theme style AlertDialog.DeviceDefault.Light.ProgressDialog which is in decoded manifest, but there is AlertDialog.DeviceDefault.Light (without ProgressDialog). And I cannot understand why we have wrong decoded styles and themes in manifest.


## iBotPeaches (07 Sept 2012)

As I said in my intro post here: http://forum.xda-developers.com/showpost.php?p=31040851&postcount=382

(Read #2) in that post. The attached Phone.apk cannot be read by aapt. aapt is backwards compatible, which means this APK has been modified in the past (maybe by a bad apktool), maybe some multi tools.

Either way, this is not a bug and not apktool's fault.


## BurgerZ (07 Sept 2012)

Thanks for your answer. I understood that.


## BurgerZ (08 Sept 2012)

Can I ask one more question?
It's about your aapt.exe for windows.
An old versions of aapt (even 5.8Mb from miui team) adds files in zip without full path, but your version adds files with the full files path.
Example:
aapt.exe add framework\am.jar framework\am.odex
In your aapt wу will have in am.jar the folder framework and the file am.odex in it.
In google's and miui's aapt we will have the file am.odex in the root of am.jar archive.
Why? And how to prevent full path files writing in archives with this version of aapt?


## iBotPeaches (08 Sept 2012)

I've never heard of this. Can you tell me the commands to run and I will test this out?

I tried 
aapt add framework/am.jar framework/am.odex, and it added am.odex into the .jar file. That isn't correct at all, but it worked.

Your suppose to deodex the odex file and merge that .dex file into the JAR, but either way it worked.


## BurgerZ (10 Sept 2012)

Hi!
I've managed it out. 
I need to use "aapt add -k FULL_PATH_TO_ARCHIVE FULL_PATH_TO_FILEtoADD" - and in that case aapt will add my file in archive without full path (in the root of my zip).
Thanks for your job.


