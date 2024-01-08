#Exception in thread java.lang.OutOfMemoryError: Java heap space

Owner: skylot

Repo: jadx

Labels: 

## Kevin-Hoang (21 Jun 2014)

I got OutOfMemoryError exception messages:

Exception in thread "pool-1-thread-8" java.lang.OutOfMemoryError: Java heap space
ERROR - OutOfMemoryError in pass: CodeShrinker in method: l.a(java.util.ArrayList, java.lang.String):void java.lang.OutOfMemoryError: Java heap space

Exception in thread "pool-1-thread-3" java.lang.OutOfMemoryError: Java heap space
    at java.lang.String.valueOf(Unknown Source)
    at jadx.core.utils.Utils.makeQualifiedObjectName(Utils.java:24)
    at jadx.core.codegen.TypeGen.signature(TypeGen.java:14)
    at jadx.core.dex.info.MethodInfo.<init>(MethodInfo.java:34)
    at jadx.core.dex.info.MethodInfo.fromDex(MethodInfo.java:43)
    at jadx.core.dex.instructions.InsnDecoder.invoke(InsnDecoder.java:661)
    at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:541)
    at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:58)
    at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:96)
    at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:211)
    at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:218)
    at jadx.core.ProcessClass.process(ProcessClass.java:20)
    at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:196)
    at jadx.api.JavaClass.decompile(JavaClass.java:59)
    at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:130)
    at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source)
    at java.lang.Thread.run(Unknown Source)

Sombody help me how to fix this problem!


## marcograss (21 Jun 2014)

Maybe you should be more precise when reporting problems/bugs.

You should specify how to reproduce the bug, for example if you were using the gui or command line tool, and what file you were opening, to start.

You can try augmenting the heap size, you can do it from command line http://stackoverflow.com/questions/1565388/increase-heap-size-in-java , but it's better if you also mention the informations that I told you some lines before


## Kevin-Hoang (21 Jun 2014)

I'm sorry for my posting above!

I'm use command line with args is: -d outputDir apkfile (15MB of size, protect by proguard, Android 4.4 compatible with 2.2). You can check with any apk same as this file.

I have checked with my apk built from my project (including javamail-android, jssoup libraries on it). Too much exception messages so I cannot see all.


## skylot (21 Jun 2014)

You can try to reduce processing threads count (`-j` option) or if you use 64-bit java increase maximum java heap size (command line example for linux):

``` bash
JAVA_OPTS="-Xmx4G" jadx -j 1 some.apk
```

or directly edit 'jadx' script and change heap size to bigger value now its defaults to 1.3G:

``` bash
DEFAULT_JVM_OPTS="-Xmx1300M"
```


## Kevin-Hoang (21 Jun 2014)

Thank skylot and marco-gra!

I close all of other programs, then change heap size, it works fine!


