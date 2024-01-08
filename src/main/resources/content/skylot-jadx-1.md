#Why do ArrayList variables not get their Generic type added?

Owner: skylot

Repo: jadx

Labels: enhancement question 

## peterdk (12 Apr 2013)

Testing this thing out. It works quite ok, although it uses a lot more memory than dex2jar since it doesn't stream the dex file, but loads it into mem.

Wondering about the ArrayList declarations:
Why don't you print out "ArrayList<String>" but "ArrayList //signature ArrayList<String>;" ?
It looks like you do have the data, but don't use it?

Anyway, it looks very promising! I do have it working on a Android device and can decompile classes on device, and the output looks very impressive. 


## skylot (13 Apr 2013)

Thanks Peter,
I am working on this issue right now and I don't yet finish generic parser, it will be ready in next commits.

About memory usage:
Now I use `dx` library from Android project for dex parsing and can't load dex partially, but if memory usage is very important for you please tell me and I will try to reduce it.
If you see OutOfMemory error try to reduce processing threads count (`-j` option) or if you use 64-bit java increase maximum java heap size (command line example for linux):

``` bash
JAVA_OPTS="-Xmx4G" jadx -j 1 some.apk
```


## peterdk (13 Apr 2013)

Hi,

Ok great! 

Memory usgae is important because I use it on device and have only 20MB of ram for an app. However its not that important, because I can hack my way around it.

I dont know how important googles dx is, and if it easily can be changed for something else, like dex2jar. But I will look into that. Or hack around in DX.

Great work!

skylot notifications@github.comschreef:

> Thanks Peter,
> I am working on this issue right now and I don't yet finish generic parser, it will be ready in next commits.
> 
> About memory usage:
> Now I use dx library from Android project for dex parsing and can't load dex partially, but if memory usage is very important for you please tell me and I will try to reduce it.
> If you see OutOfMemory error try to reduce processing threads count (-j option) or if you use 64-bit java increase maximum java heap size (command line example for linux):
> 
> JAVA_OPTS="-Xmx4G" jadx -j 1 some.apk 
> 
> —
> Reply to this email directly or view it on GitHub.￼


## peterdk (15 Apr 2013)

Cool, looking good sofar! Generics work now for arguments but not yet for class variables and class definitions. But I assume you know that. :+1: 


## skylot (15 Apr 2013)

I commit generics for classes and fields, but as soon as signature format don't documented and depends on java compiler there can be parse errors, if you get one please tell me.


