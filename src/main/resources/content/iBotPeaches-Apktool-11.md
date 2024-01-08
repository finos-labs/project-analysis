#Cannot modify/recompile XLMs of Jelly Bean Contacts.apk

Owner: iBotPeaches

Repo: Apktool

Labels: 

## SunnyOK (04 Aug 2012)

I am trying to modify XMLs of Jelly Bean Contacts.apk. Using all apktools (up to 1.4.9) that has been used for ICS and other Jelly Bean apks, I can decompile and then recomplie it without any error. However, the recompiled Contacts.apk would not work in cell phone, showing a message "Unfortunately, Contacts has stopped!". 

To simplify the problem, I simply do decompiling and then recompiling without any modifications. I found that the size of resources.arsc has been changed from 1480 KB to 1525 KB. The new Contacts.apk causes FC!

Note: Files in smali folder can still be modified. After editing, use apktool to recompile and create a new apk. Then use WinRAR or 7zip to drag the newly created classes.dex into the original Contacts.apk. Such a "modified" Contacts.apk works properly. Note here that we did not modify resources.arsc.

This problem is only related to resources.arsc! XMLs cannot be recompiled.

Please fix this problem in apktool 1.4.10.


## SunnyOK (10 Aug 2012)

See my apks and logcat.
I include README.txt.
http://www.mediafire.com/?tfibqtmwvvbjfjj


## iBotPeaches (10 Aug 2012)

Well shit.

```
E/AndroidRuntime(  978): java.lang.NullPointerException

E/AndroidRuntime(  978):    at com.android.contacts.dialpad.T9Search.initT9Map(T9Search.java:223)
```

Looks like we have new resource type. T9Map. Will have to code support for it. Its just loosing it on rebuild.


## SunnyOK (10 Aug 2012)

iBotPeaches,
Glad to know that you found the root cause. This problem has bothered me for almost 10 days. I have exhausted all posibilities and tools that I have, including their combinations. The solution is highly desired.
Thank you for looking at this issue and look forward to hearing from you for solution..


## SunnyOK (14 Aug 2012)

Hi iBotPeaches,
Is there any progress on T9Map? Can you provide apktool that I can test? Thanks.


## iBotPeaches (14 Aug 2012)

Progress will be posted when done. I don't have a phone to test. There is no development until I get a phone back.


## SunnyOK (14 Aug 2012)

If you do not mind, I can test your new code in my phone. 
When can you get your phone back?


## iBotPeaches (15 Aug 2012)

It was sent yesterday. It should arrive in 2-4 days. Then development begins again.


## SunnyOK (19 Aug 2012)

Hi, iBotPeaches,
Any progress on development? Looking forward to testing your new apktool for handling T9Map and rebuilding Contacts.apk.


## iBotPeaches (19 Aug 2012)

GIT is open source. You will see if I do anything. Please don't ask anything until you notice I push changes. 

I'm very busy and do this as a hobby. 


## SunnyOK (19 Aug 2012)

We are appreciated.


## SunnyOK (21 Aug 2012)

I have tried apktool 1.4.10 today. It seems that this version od apktool does not resolve this problem associated with T9Map.


## iBotPeaches (21 Aug 2012)

ugh. 

Did you even read the changelog? Or the posts associated with that release?

Only 2 bugs were fixed.

I've literally setup Java Debuggers and stepped through this code for hours trying to track down SystemUI and T9Map problems. Haven't been able to fix this yet.

I'm a full time student w/ a part time job. I work when I can. Please be patience and wait for a fix and actually read the changelog.


## SunnyOK (21 Aug 2012)

Sorry for bothering you. I did read your changelog and expect a fix. Please ignore my report. Thank you.


## iBotPeaches (27 Aug 2012)

I have fixed this.


## SunnyOK (29 Aug 2012)

Good news.


## SunnyOK (02 Sept 2012)

Liangyz70 from gfan found the root cause of the problem. With his method, now I can recompile Contacts.apk.


## Acid-miuipolskapl (02 Sept 2012)

Can you post a link to this? 


## iBotPeaches (02 Sept 2012)

Apktool v1.5.0 released 20min ago fixes this


## Acid-miuipolskapl (02 Sept 2012)

Haha, many thanks Connor for this :) I am compiling stock JB roms and got this issue today. And here's your new apktool. Amazing ;)

Wiil let you know if this works for me.


## Acid-miuipolskapl (03 Sept 2012)

Confirmed to be working with FC Contacts :) Thanks.


