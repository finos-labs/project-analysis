#SystemUI.apk and framework-res.apk (4.0.3)

Owner: iBotPeaches

Repo: Apktool

Labels: Bug 

## iBotPeaches (27 Jul 2012)

```

appeaches@turf:~/Downloads$ apktool b SystemUI
I: Checking whether resources has changed...
I: Building resources...
Exception in thread "main" brut.androlib.AndrolibException: brut.common.BrutException: could not exec command: [aapt, p, -F, /tmp/APKTOOL8348634422213290285.tmp, -I, /home/peaches/apktool/framework/1.apk, -S, /home/peaches/Downloads/SystemUI/res, -M, /home/peaches/Downloads/SystemUI/AndroidManifest.xml]
    at brut.androlib.res.AndrolibResources.aaptPackage(AndrolibResources.java:251)
    at brut.androlib.Androlib.buildResourcesFull(Androlib.java:325)
    at brut.androlib.Androlib.buildResources(Androlib.java:270)
    at brut.androlib.Androlib.build(Androlib.java:193)
    at brut.androlib.Androlib.build(Androlib.java:175)
    at brut.apktool.Main.cmdBuild(Main.java:182)
    at brut.apktool.Main.main(Main.java:67)
Caused by: brut.common.BrutException: could not exec command: [aapt, p, -F, /tmp/APKTOOL8348634422213290285.tmp, -I, /home/peaches/apktool/framework/1.apk, -S, /home/peaches/Downloads/SystemUI/res, -M, /home/peaches/Downloads/SystemUI/AndroidManifest.xml]
    at brut.util.OS.exec(OS.java:83)
    at brut.androlib.res.AndrolibResources.aaptPackage(AndrolibResources.java:249)
    ... 6 more

```


## iBotPeaches (27 Aug 2012)

42 bytes of data now instead of 36. Need to fix aapt first though. As it seg faults which is the problem. 


## iBotPeaches (29 Aug 2012)

The (styles.xml) decompiler is wrong.

```
/home/peaches/Downloads/apktool/SystemUI/res/values/styles.xml:8: error: Error retrieving parent for item: No resource found that matches the given name '@android:style/Animation.RecentApplications'.
/home/peaches/Downloads/apktool/SystemUI/res/values/styles.xml:9: error: Error retrieving parent for item: No resource found that matches the given name '@android:style/Animation.RecentApplications'.
/home/peaches/Downloads/apktool/SystemUI/res/values/styles.xml:14: error: Error retrieving parent for item: No resource found that matches the given name '@android:style/Animation.RecentApplications'.
/home/peaches/Downloads/apktool/SystemUI/res/values/styles.xml:46: error: Error retrieving parent for item: No resource found that matches the given name '@android:style/Animation.PopupWindow'.
```

other portions of this bug are fixed.


## iBotPeaches (30 Aug 2012)

The problem that is reads as @android: for all the XML files, but aapt is requiring @*android: as a little hack to get around the private/public resource thing.

Will add a check into apktool and test it.


