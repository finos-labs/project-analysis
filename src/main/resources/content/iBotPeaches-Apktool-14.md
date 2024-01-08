#Debug mode

Owner: iBotPeaches

Repo: Apktool

Labels: Bug 

## raziel23x (18 Aug 2012)

Had this been fixed apktool -d  ie debug switch?


## iBotPeaches (30 Aug 2012)

```

peaches@turf:~/Downloads/apktool$ apktool d -d -f SystemUI.apk 
I: Baksmaling...


Error occured while loading boot class path files. Aborting.
org.jf.dexlib.Util.ExceptionWithContext: Could not find definition for class Ljava/lang/Object;
    at org.jf.dexlib.Code.Analysis.ClassPath.getClassDef(ClassPath.java:286)
    at org.jf.dexlib.Code.Analysis.ClassPath.initClassPath(ClassPath.java:163)
    at org.jf.dexlib.Code.Analysis.ClassPath.InitializeClassPath(ClassPath.java:135)
    at org.jf.baksmali.baksmali.disassembleDexFile(baksmali.java:109)
    at brut.androlib.src.SmaliDecoder.decode(SmaliDecoder.java:44)
    at brut.androlib.src.SmaliDecoder.decode(SmaliDecoder.java:33)
    at brut.androlib.Androlib.decodeSourcesSmali(Androlib.java:72)
    at brut.androlib.ApkDecoder.decode(ApkDecoder.java:85)
    at brut.apktool.Main.cmdDecode(Main.java:131)
    at brut.apktool.Main.main(Main.java:68)
Error while loading ClassPath class Ljava/lang/Object;

```

Will figure this out tomorrow. Forgot that Brut made tiny changes to JF project, so when I merged new smali/baksmali it erased his changes.

The bad part is he made his changes nearly a year ago. There has been lots of updates since then. I have to slowly merge this together.


## raziel23x (31 Aug 2012)

Thanks this will help greatly with using a debugger I still have not setup netbean I just got my new PC but right nite it's a paper weight until Dell send me three correct recovery disk. 

I look foreword to testing this out. 

PS do you recommend me dual boot Ubuntu and windows? 


