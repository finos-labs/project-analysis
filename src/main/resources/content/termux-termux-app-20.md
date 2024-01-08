#Cannot write to external SD card

Owner: termux

Repo: termux-app

Labels: 

## yajo (28 Nov 2015)

```
$ touch /storage/extSdCard/test
touch: /storage/extSdCard/test: Permission denied
```

I guess this comes from Android 5's new permissions for SD cards. Any way to fix it?


## Neo-Oli (15 Nov 2016)

I am using Poweramp, which seems to work. But I have seen other apps completely ignore the Android folder, even if explicitly set. I still haven't found a Gallery app that let's me choose the termux folder. 


## terba (15 Nov 2016)

Media library scanning doesn't recognizes termux's external folder, but a simple folder based player works. Thanks!


## wshanks (10 Dec 2016)

@terba I had the same problem with the default Android music player not showing files that I had transferred to the SD card with rsync. I found that using another app to force rescan the SD card allowed the music files to show up in the music player.

## terba (15 Dec 2016)

@Neo-Oli and @willsALMANJ: On my device a .nomedia file gets created at boot time in the external SD cards "Android" folder. That is what prevents media indexing my ...com.termux/files/home/storage/external folder. If I remove that .nomedia file with a file manager and trigger a reindex after each boot, my files show up until the next reboot. But it is useless for me anyway, because Android media indexer can not deal with UTF8 in tags... And this OS is used by the whole world... The solution is to forget the Android's built-in media library, and use a music player with it's own media library as Neo-Oli suggested above.

## lbmn (21 Jan 2017)

+1

## CaptainPackers (15 Feb 2017)

On my Asus ZenPad 10.1 (P023) running Android 5.0.2, I do not see a com.termux folder at /storage/MicroSD/Android/data, so I don't even have the option of writing there. Anyone have any experience with this. I would like to use termux to write to my sd card.
Thanks

I think I found the answer [here](https://termux.com/storage.html). Seems like I needed to run termux-setup-storage for the com.termux folder to be created on Android/data 

## Cypher1 (27 Mar 2017)

Can this be reopened. I would like to be able to use my terminal to edit and run files that I already have, I guess I could move everything into termux's data folder but that seems messy and unnecessary.

## pgaskin (12 Apr 2017)

Finally figured it out for rooted devices running marshmallow or greater!

````
su
pm grant com.termux android.permission.READ_EXTERNAL_STORAGE
pm grant com.termux android.permission.WRITE_EXTERNAL_STORAGE
````

Edit: This only seems to work temporarily.

## HJarausch (15 Apr 2017)

For me, it doesn't work after I leave root with 'exit'.


## insign (06 Jan 2018)

@geek1011 worked like a charm!

## pgaskin (06 Jan 2018)

And without root, you can just run that in adb shell from a computer without the su command, and that should be permanent.

## rogerxxxx (06 Feb 2018)

I tried the prior mentioned "pm" read/write permission within an adb shell, and I still do not have write access on the external storage device.  I do not have a rooted device, but  geek1011 stated should work.  However, most other adb functions work.

I have a Samsung Galaxy S3 Tab.  Maybe the adb shell is limited to certain Android versions or devices?  Most adb functions do appear to work, but this Samsung device's adb functions seems to have restricted push/pull operations.  I believe I was logging adb in through the device's wifi/wireless instead of wired USB.  And still not working after ensuring I was logging adb through USB wired method.

I would really like to see the termux writable folder "$HOME/storage/external-1 -> /storage/3065-3134/Android/data/com.termux/files" to at least show a symlinked folder within the main external storage root folder, else I'm really apt to just accidentally format or delete folders without taking caution of backing-up this hidden folder!

## Quasic (07 Feb 2018)

I wish the SD card supported symlinks, too, or that Android wasn't blocking access to all but that one deep folder, but so far, I haven't seen a better solution. I have seen some problems solved that I thought were harder, though...

## pgaskin (08 Feb 2018)

@Quasic I'm not sure if this will work, but have you tried making a disk image file on the SD card, and loop mounting it?

## Quasic (08 Feb 2018)

@geek1011, I have not, but others have (@sdrausty is one, I think.) As I understand it, it is near impossible without root.

## komi015 (19 May 2018)

For this problème u Can use this comand:
termux-setup-storage ,it fond and u agree the the following android dialogue to allow sdcard permissions.
Thank

## giggls (30 Oct 2018)

> For me, it doesn't work after I leave root with 'exit'.

Same here (Android 8.1 /LineageOS 15.1).

## Chubukov-Aleksey (22 Nov 2018)

I found that AnExplorer can request permission to write on sdcard without need to select sdcard's root folder. 

## QJKX (23 Jan 2019)

> Finally figured it out for rooted devices running marshmallow or greater!
> 
> ```
> su
> pm grant com.termux android.permission.READ_EXTERNAL_STORAGE
> pm grant com.termux android.permission.WRITE_EXTERNAL_STORAGE
> ```
> 
> Edit: This only seems to work temporarily.

This doesn't seem to work (anymore?) on 8.1.
The commands work without an error, but I can't actually write to the external SD card.

WRITE_MEDIA_STORAGE looks like the correct permission (but putting that in the command doesn't work because it's not in the com.termux manifest.) I expect the permission to be restricted to system apps anyway, but that might be a better option for rooted users than running as root?

## bhagwandassony1 (04 Aug 2020)

![Screenshot_20200804-142357](https://user-images.githubusercontent.com/69147279/89274187-18bc7680-d630-11ea-82df-0ebc2e845d45.png)


