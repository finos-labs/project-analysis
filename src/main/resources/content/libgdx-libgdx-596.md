#UnsatisfiedLinkError: com.badlogic.gdx.physics.box2d.World.newWorld(FFZ)J

Owner: libgdx

Repo: libgdx

Labels: bug 

## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 05, 2010 22:01:23_

What steps will reproduce the problem? 1. Setup basic project as stated in README
2. Setup VM argument to point to lib/
3. add this one line to main() //including needed imports..

World world = new World(new Vector2( 0, -10 ), true); What is the expected output? What do you see instead? I would expect a new World to be created successfully, Instead I get the 
following error

Exception in thread "main" java.lang.UnsatisfiedLinkError: 
com.badlogic.gdx.physics.box2d.World.newWorld(FFZ)J
    at com.badlogic.gdx.physics.box2d.World.newWorld(Native Method)
    at com.badlogic.gdx.physics.box2d.World.<init>(World.java:68)
    at com.dasa.helloworld. HelloWorld .main( HelloWorld .java:17) What version of the product are you using? On what operating system? gdx0.5 on windows 7 in eclipse Please provide any additional information below. Sorry, im a fuckin noob. I dont even know what this means besides there 
being some kind of problem loading the JNI

_Original issue: http://code.google.com/p/libgdx/issues/detail?id=5_


## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 05, 2010 13:44:39_

Also, I dont have any trouble on the android platform. Able to create world and call 
world.step and it doesn't ForceClose. I was having a similar problem running the box2d 
gdx-test on windows 7 in eclipse, but running the test in android worked just fine.


## badlogic (14 Sept 2013)

_From [tonyo%sk...@gtempaccount.com](https://code.google.com/u/102183875732111856184/) on June 06, 2010 13:46:54_

Means that it either cannot find the library that houses
"com.badlogic.gdx.box2d.World.newWorld".  Are you sure you've added the correct
libraries to the project and that the compiler is able to find them?

It may not exist or have been moved for v0.5.  The readmes appear to be for 0.4 and
it took a bit of hacking around and guessing for me to find some of the 3D features
(NOT THAT I'M COMPLAINING, I DIDN'T HAVE TO REINVENT THE WHEEL AND FOR THIS I AM
THANKFUL!).


## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 06, 2010 13:53:11_

well when i remove the vm arg from run config it simply complains that gdx isn't 
available when it calls System.LoadLibrary so I can only guess I am doing this 
correctly. I've been using box2d via libgdx extensively since friday evening 
successfully on my android phone though and I dont know hardly anything about whats 
going on but I can only assume that its finding the .so just fine.

Both the .so and .dll are there in the same directory.

the README is outdated and had some typos. I had to make edits to make it functional. 
And like I've been saying, after those simple edits (bus hell, ive only been using 
java since friday), it ran fine on the Android emu and on my phone. Just not the 
desktop.


## badlogic (14 Sept 2013)

_From [tonyo%sk...@gtempaccount.com](https://code.google.com/u/102183875732111856184/) on June 06, 2010 23:51:58_

I don't write in Java either..I've been writing in C and CPP for about 15 years and
recently just in Java for Android and C# out of curiosity.  

That error does mean, though, that it isn't finding a referenced library.  I couldn't
tell you or help without seeing the project set up.

Could also be "Thrown if the Java Virtual Machine cannot find an appropriate
native-language definition of a method declared native."  How is your JVM being set up?


## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 07, 2010 07:37:27_

So I'm confused, is Box2D working for you with 0.5 running as a Desktop App? As for 
the jvm, Just a sun java 1.6.0_20 with the libraries setup to point to gdx and 
backend-desktop and backend-dependancies. Run-config setup so its finding the libs. 
32-bit computer.

The one guess I can take a stab at is that, looking up JNI stuff, seems like its 
common to have two dll's involved. The JNI wrapper and the actually library dll. I 
dont know if thats the case here, it would make sense that it loads the JNI wrapper 
from the gdx.dll just fine since LoadLibrary doesn't fail. But then the second dll? 
Well all I have here is a libgdx.so file. Maybe i need a libgdx.dll file for windows? 
I dont know alot about it all.

I'd mess around with it some more but ive completely migrated to just developing on 
my android phone via eclipse. I was having some slow-downs from the immediate-
renderer and considering the simplicity of what I'm working on and the fact that I'm 
porting something already mostly written, I just ended up ditching the majority of 
libgdx and making use of the jni-box2d wrapper which works great so far on android.


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 09, 2010 09:43:40_

This is indeed strange. I always test the binaries i send out. To clear up some things: at the moment all the shared libs get compiled to a single shared lib called libgdx.dll/so. This includes the Box2D code which is actually a problem given the license of Box2D afair. This will probably change in the future i just didn't have the time to fix it yet. 

As stated in the comments in the source of the Box2D example the ImmediateModeRenderer is not to be used in production due to it's slowness. It's merely a tool for me to get something close to OpenGL immediate mode for debugging purposes. 

I'll check the thing tomorrow.

**Status:** Accepted  


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 11, 2010 06:33:29_

Zip file on download page contained the wrong dll. Fixed with 0.51 release.

**Status:** Fixed  


## badlogic (14 Sept 2013)

_From [konya....@gmail.com](https://code.google.com/u/113458510119358838769/) on March 22, 2011 22:48:27_

I've got the same exception today with libgdx 0.9,
and found the cause is initializing World in static initialization block.
It is solved after I moved "new World()" into a constructor.


## badlogic (14 Sept 2013)

_From [chu...@gmail.com](https://code.google.com/u/110562730386442276315/) on March 23, 2011 11:14:38_

konya, could I get a bit more clarification?  I'm seeing this same issue in my app and don't quite understand what you mean.  I'm running libgdx .9 in ubuntu.


## badlogic (14 Sept 2013)

_From [konya....@gmail.com](https://code.google.com/u/113458510119358838769/) on March 27, 2011 01:02:21_

If you initialized the world like:

public class A {
    World world = new World(...);
    ...
}

Just move the initialization into a method or constructor:

public class A {
    World world;
    public void reset() {
        world = new World(...);
    }
}

This is how I solved it. Though I do not understand clearly the relation between JNI calls and class initialization.


## badlogic (14 Sept 2013)

_From [ramonmol...@gmail.com](https://code.google.com/u/104044685204758059300/) on June 05, 2011 17:40:45_

Same here!

I tried to instantiate the new World inside a constructor like Andengine asks and like konya told, but 

06-05 21:30:38.764: DEBUG/dalvikvm(13774): +++ not scanning '/system/lib/libwebcore.so' for 'newWorld' (wrong CL)
06-05 21:30:38.764: DEBUG/dalvikvm(13774): +++ not scanning '/system/lib/libmedia_jni.so' for 'newWorld' (wrong CL)
06-05 21:30:38.764: DEBUG/dalvikvm(13774): +++ not scanning '/system/lib/libexif.so' for 'newWorld' (wrong CL)
06-05 21:30:38.764: DEBUG/dalvikvm(13774): +++ not scanning '/system/lib/libtextrendering.so' for 'newWorld' (wrong CL)
06-05 21:30:38.764: DEBUG/dalvikvm(13774): +++ not scanning '/system/lib/libsrec_jni.so' for 'newWorld' (wrong CL)
06-05 21:30:38.764: WARN/dalvikvm(13774): No implementation found for native Lcom/badlogic/gdx/physics/box2d/World;.newWorld (FFZ)J
06-05 21:30:38.764: DEBUG/AndroidRuntime(13774): Shutting down VM
06-05 21:30:38.764: WARN/dalvikvm(13774): threadid=3: thread exiting with uncaught exception (group=0x40026160)
06-05 21:30:38.764: ERROR/AndroidRuntime(13774): Uncaught handler: thread main exiting due to uncaught exception
06-05 21:30:38.784: ERROR/AndroidRuntime(13774): java.lang.UnsatisfiedLinkError: newWorld
06-05 21:30:38.784: ERROR/AndroidRuntime(13774):     at com.badlogic.gdx.physics.box2d.World.newWorld(Native Method)
06-05 21:30:38.784: ERROR/AndroidRuntime(13774):     at com.badlogic.gdx.physics.box2d.World.<init>(World.java:71)
06-05 21:30:38.784: ERROR/AndroidRuntime(13774):     at com.goodidev.porco.Porco.<init>(Porco.java:45)
06-05 21:30:38.784: ERROR/AndroidRuntime(13774):     at java.lang.Class.newInstanceImpl(Native Method)
06-05 21:30:38.784: ERROR/AndroidRuntime(13774):     at java.lang.Class.newInstance(Class.java:1479)
06-05 21:30:38.784: ERROR/AndroidRuntime(13774):     at android.app.Instrumentation.newActivity(Instrumentation.java:1021)
06-05 21:30:38.784: ERROR/AndroidRuntime(13774):     at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2416)
06-05 21:30:38.784: ERROR/AndroidRuntime(13774):     at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2519)
06-05 21:30:38.784: ERROR/AndroidRuntime(13774):     at android.app.ActivityThread.access$2200(ActivityThread.java:123)
06-05 21:30:38.784: ERROR/AndroidRuntime(13774):     at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1870)
06-05 21:30:38.784: ERROR/AndroidRuntime(13774):     at android.os.Handler.dispatchMessage(Handler.java:99)
06-05 21:30:38.784: ERROR/AndroidRuntime(13774):     at android.os.Looper.loop(Looper.java:123)
06-05 21:30:38.784: ERROR/AndroidRuntime(13774):     at android.app.ActivityThread.main(ActivityThread.java:4370)
06-05 21:30:38.784: ERROR/AndroidRuntime(13774):     at java.lang.reflect.Method.invokeNative(Native Method)
06-05 21:30:38.784: ERROR/AndroidRuntime(13774):     at java.lang.reflect.Method.invoke(Method.java:521)
06-05 21:30:38.784: ERROR/AndroidRuntime(13774):     at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:868)
06-05 21:30:38.784: ERROR/AndroidRuntime(13774):     at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:626)
06-05 21:30:38.784: ERROR/AndroidRuntime(13774):     at dalvik.system.NativeStart.main(Native Method)
06-05 21:30:38.814: DEBUG/dalvikvm(1667): GC freed 7715 objects / 441952 bytes in 312ms


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 05, 2011 17:45:09_

Andengine? I assume you don't use the rest of libgdx. You have to load the libgdx.so first before you can touch any libgdx classes. Use System.load("gdx") before you do anything and all should be well.


## badlogic (14 Sept 2013)

_From [jaar...@gmail.com](https://code.google.com/u/116633098305478911110/) on June 07, 2011 05:45:09_

JoglNativesLoader.load();


## badlogic (14 Sept 2013)

_From [sonnyg...@gmail.com](https://code.google.com/u/115258666084706126708/) on January 11, 2012 04:39:41_

import com.badlogic.gdx.utils.GdxNativesLoader;
GdxNativesLoader.load(); 

Use this before you do anything. make sure you have gdx.jar and gdx-natives.jar in your project. Useful if you just want to use the physics library without anything else from libgdx. I use it on the server side.


## badlogic (14 Sept 2013)

_From [616Ve...@gmail.com](https://code.google.com/u/107863821218849954739/) on October 17, 2012 15:19:49_

Assign world within the create() function.  Some of the code I found on-line assigns & declares it with the rest of the class variables.


