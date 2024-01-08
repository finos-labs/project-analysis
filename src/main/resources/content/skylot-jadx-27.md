#small dex with UnsupportedOperationException

Owner: skylot

Repo: jadx

Labels: 

## artenie (12 Nov 2014)

First, thank you for this wonderful decompiler. It outputs very nice code.

This seems to me very low priority for the jadx project, but I found this dex which has 2 small methods (only 1-2 instructions). Seems to be a part of a new apk packer. Instructions could be something more exotic. Each method has output for decompiled code (example for method a):

public java.lang.String a() {
throw new UnsupportedOperationException("Method not decompiled: a.a.a.a.a.a():java.lang.String");
        /\* JADX: method processing error _/
/_
        Error: java.lang.NullPointerException
        at jadx.core.dex.visitors.regions.ProcessVariables.addToUsageMap(ProcessVariables.java:286)
        at jadx.core.dex.visitors.regions.ProcessVariables.visit(ProcessVariables.java:184)
        at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:16)
        at jadx.core.ProcessClass.process(ProcessClass.java:22)
        at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:209)
        at jadx.api.JavaClass.decompile(JavaClass.java:59)
        at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:133)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
        at java.lang.Thread.run(Thread.java:745)
_/
        /_
        this = this;
        r44 = (r40 > r32 ? 1 : (r40 == r32 ? 0 : -1));
    L_0x0002:
        if (r11 != r6) goto L_0x0002;
        */

Fallback mode for jadx prints the same, without the stack trace. 

Thank you.


## artenie (12 Nov 2014)

Sorry, I forgot the link to the dex:
http://www36.zippyshare.com/v/97540390/file.html


## skylot (13 Nov 2014)

Hm, this dex is weird :)
Method header states that registers count is 2, but actually used registers with numbers bigger than 2 (for example: 44), so I think this method can't be executed in real Android VM.

```
  Virtual methods   -
    #0              : (in La/a/a/a/a;)
      name          : 'a'
      type          : '()Ljava/lang/String;'
      access        : 0x0001 (PUBLIC)
      code          -
      registers     : 2
      ins           : 1
      outs          : 0
      insns size    : 4 16-bit code units
000190:                                        |[000190] a.a.a.a.a.a:()Ljava/lang/String;
0001a0: 2d2c 2820                              |0000: cmpl-float v44, v40, v32
0001a4: 326b 0000                              |0002: if-eq v11, v6, 0002 // +0000
      catches       : (none)
      positions     : 
        0x0000 line=12
      locals        : 
        0x0000 - 0x0004 reg=1 this La/a/a/a/a; 
```

But this is maybe some workaround for code hiding (like in this article: http://dexlabs.org/blog/bytecode-obfuscation) or just tricks for crush decompiler.

Anyway, thank you for such interesting dex.


## artenie (14 Nov 2014)

Sorry, I created the small dex only from that class with smali. I thought it will be easier for you to isolate the problem in jadx. But here is the full apk (from which I took the class):

http://www44.zippyshare.com/v/32933028/file.html

It seems for each package there is also an accompaning "a" class with 2-3 instructions. 


## bagipro (28 Apr 2020)

APK links don't work anymore, and I didn't notice such errors last time, so I believe it's time to close it

