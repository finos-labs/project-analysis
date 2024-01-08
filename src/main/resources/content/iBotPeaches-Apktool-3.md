#apktool.yml reports incorrectly.

Owner: iBotPeaches

Repo: Apktool

Labels: Bug 

## iBotPeaches (01 May 2012)

Sometimes when recompiling, it shows Frames used = 1

but on re-compile it fails, because it needs 1,  and either 2 or 3.

When decompiling, not correctly reporting the frames used.

Fixed version works with everything but framework-res

```

version: 1.4.5.5e49d31
apkFileName: AntiSpam.apk
isFrameworkApk: false
usesFramework:
  ids:
  - 1
  - 3

```


## iBotPeaches (04 May 2012)

This is because apktool assumes that a framework file has no other dependencies and skips the check. So I'll move some code around. 

This will fix bugs with 3 framework systems, such as ICS Sense 4.0 and MIUI 4.0.


## iBotPeaches (27 Aug 2012)

Tried to fix this, but most of these fixes are for out of the ordinary things like dummyskins and dummy frameworks. 

Though ill try and add a check to insert those ids. 


