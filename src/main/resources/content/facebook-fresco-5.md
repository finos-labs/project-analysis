#Mac os How to build？

Owner: facebook

Repo: fresco

Labels: 

## kongnanlive (27 Mar 2015)

:imagepipeline:ndk_build_gifimage FAILED

FAILURE: Build failed with an exception.
- What went wrong:
  Execution failed for task ':imagepipeline:ndk_build_gifimage'.
  
  > A problem occurred starting process 'command 'ndk-build''
- Try:
  Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.

BUILD FAILED

Total time: 11.279 secs
kongnandembp:fresco kongnan$ echo $PATH
/usr/bin:/bin:/usr/sbin:/sbin:/usr/local/bin:/opt/X11/bin:/Users/kongnan/Documents/AndroidSDK/android-ndk-r10d
kongnandembp:fresco kongnan$ ndk-build
Android NDK: Could not find application project directory !  
Android NDK: Please define the NDK_PROJECT_PATH variable to point to it.  
/Users/kongnan/Documents/AndroidSDK/android-ndk-r10d/build/core/build-local.mk:148: **\* Android NDK: Aborting    .  Stop.
kongnandembp:fresco kongnan$ 


## ikkyu0319 (29 Mar 2015)

@dp-singh @blackiedm How do you make it work? SOS-~~~


## blackiedm (29 Mar 2015)

@ikkyu0319 find the commandline 'ndk-build' in imagepipeline/build.gradle and replace 
commandLine 'your local ndk dir/ndk-build'


## MarkMjw (30 Mar 2015)

@blackiedm i has replaced <commandLine 'ndk-build'> with <commandLine 'D:/Android/android-ndk-r10d/ndk-build'> , but it's also failed in windows.

Starting process 'command 'D:/Android/android-ndk-r10d/ndk-build''. Working directory: E:\workspace\studio\fresco\imagep
ipeline Command: D:/Android/android-ndk-r10d/ndk-build NDK_APPLICATION_MK=../Application.mk NDK_OUT=E:\workspace\studio\
fresco\imagepipeline\build\tmp\ndk_build_gifimage NDK_LIBS_OUT=E:\workspace\studio\fresco\imagepipeline\build/gifimage -
C E:\workspace\studio\fresco\imagepipeline\src\main\jni\gifimage --jobs 8
:imagepipeline:ndk_build_gifimage FAILED
:imagepipeline:ndk_build_gifimage (Thread[main,5,main]) completed. Took 0.035 secs.

FAILURE: Build failed with an exception.
- What went wrong:
  Execution failed for task ':imagepipeline:ndk_build_gifimage'.
  > A problem occurred starting process 'command 'D:/Android/android-ndk-r10d/ndk-build''


## dp-singh (30 Mar 2015)

@blackiedm @plamenko  @ikkyu0319  I have tried everything but was not able to make it. My main motive was to look at the sample so I have added gradle support repo 

Replace sample build file  dependencies with the below give dependencies

dependencies {
    compile "com.android.support:appcompat-v7:${APPCOMPAT_V7_VERSION}"
    compile "com.github.bumptech.glide:glide:${GLIDE_VERSION}"
    compile "com.mcxiaoke.volley:library:${VOLLEY_VERSION}"
    compile "com.nostra13.universalimageloader:universal-image-loader:${UIL_VERSION}"
    compile "com.squareup.picasso:picasso:${PICASSO_VERSION}"

```
compile 'com.facebook.fresco:drawee-volley:0.1.0+'
compile 'com.facebook.fresco:fresco:0.1.0+'
compile 'com.facebook.fresco:imagepipeline-okhttp:0.1.0+'
```

}


## michalgr (30 Mar 2015)

@dp-singh I would like to fix whatever build issue you are facing. Would you mind letting us know what os (mac / linux / window) you use ? And also, could you try to run ndk-build from imagepipeline directory on its own, so that we can see what is the actual error, provided that it comes from ndk build:
path_to_your_ndk-build NDK_APPLICATION_MK=../Application.mk NDK_OUT=build NDK_LIBS_OUT=build -C src/main/jni/gifimage


## MarkMjw (30 Mar 2015)

@michalgr the message:(mac)
 in ~/Documents/studio/fresco/imagepipeline/src/main/jni on master*
$ ndk-build NDK_APPLICATION_MK=../Application.mk NDK_OUT=build NDK_LIBS_OUT=build -C gifimage
Android NDK: Android.mk: Cannot find module with tag 'giflib' in import path
Android NDK: Are you sure your NDK_MODULE_PATH variable is properly defined ?
Android NDK: The following directories were searched:
Android NDK:
make: Entering directory `/Documents/studio/fresco/imagepipeline/src/main/jni/gifimage'
Android.mk:25: *** Android NDK: Aborting.    .  Stop.
make: Leaving directory`/Documents/studio/fresco/imagepipeline/src/main/jni/gifimage'


## michalgr (30 Mar 2015)

giflib is supposed to be downloaded by imagepipeline/build.gradle. Can you try first running gradlew :assembleDebug (it will fail after downloading dependencies) and then repeating the same once again ? And one more question, what is your ndk version ?

Thanks for helping.


## MarkMjw (30 Mar 2015)

@michalgr thanks, i resolved this problem,  the problem is unable to access 'https://chromium.googlesource.com/webm/libwebp/'.


## KennethYo (31 Mar 2015)

i resolved this problem,in ‘imagepipeline/build.gradle’ file replace like this.
step 1, find `commandLine 'ndk-build'`;
step 2, change 'ndk-build' to your ndk path.

```
 commandLine '/Users/kenneth/Desktop/develop/android-ndk-r10d/ndk-build',
```


## appledong (31 Mar 2015)

windows os replace ndk-build new problem：
  Android NDK: Could not find application project directory !  
E:\android-ndk-r10d\build\core\build-local.mk
Error:(148) **\* Android NDK: Aborting    .  Stop.
Android NDK: Please define the NDK_PROJECT_PATH variable to point to it.  
make.exe: Entering directory `F:/fresco-master/fresco-master/imagepipeline/src/main/jni/gifimage'
make.exe: Leaving directory`F:/fresco-master/fresco-master/imagepipeline/src/main/jni/gifimage'
:imagepipeline:ndk_build_gifimage FAILED
Error:Execution failed for task ':imagepipeline:ndk_build_gifimage'.

> Process 'command 'E:\android-ndk-r10d\ndk-build.cmd'' finished with non-zero exit value 2


## BaronZ (31 Mar 2015)

@appledong  +1
E:\android-ndk-r10d\build\core\build-local.mk
Error:(148) **\* Android NDK: Aborting . Stop.
Android NDK: Please define the NDK_PROJECT_PATH variable to point to it.


## littletanker (31 Mar 2015)

@michalgr in windows, i run the command in  imagepipeline directory as you say, 
ndk-build NDK_APPLICATION_MK=../Ap
plication.mk NDK_OUT=build NDK_LIBS_OUT=build -C src/main/jni/gifimage

but failed and this is the output
Android NDK: Could not find application project directory !
Android NDK: Please define the NDK_PROJECT_PATH variable to point to it.
make.exe: Entering directory `F:/AndroidStudioProjects/fresco/imagepipeline/src/
main/jni/gifimage'
D:\android-ndk-r9d_x86_64\build/core/build-local.mk:148: *** Android NDK: Aborti
ng    .  Stop.
make.exe: Leaving directory`F:/AndroidStudioProjects/fresco/imagepipeline/src/m
ain/jni/gifimage'


## KennethYo (31 Mar 2015)

@ikkyu0319 哥们，首先把ndk配置环境变量，然后看下面我的代码，把我的ndk路径`'/Users/kenneth/Desktop/develop/android-ndk-r10d/ndk-build'`替换成你的就可以了。

```
 task "ndk_build_$name"(type: Exec) {
        inputs.file("src/main/jni/$name")
        outputs.dir("$buildDir/$name")
        commandLine '/Users/kenneth/Desktop/develop/android-ndk-r10d/ndk-build',
            'NDK_APPLICATION_MK=../Application.mk',
            'NDK_OUT=' + temporaryDir,
            "NDK_LIBS_OUT=$buildDir/$name",
            '-C', file("src/main/jni/$name").absolutePath,
            '--jobs', '8'
    }

    task "ndk_clean_$name"(type: Exec) {
        commandLine '/Users/kenneth/Desktop/develop/android-ndk-r10d/ndk-build',
            'NDK_APPLICATION_MK=../Application.mk',
            'NDK_OUT=' + temporaryDir,
            "NDK_LIBS_OUT=$buildDir/$name",
            '-C', file("src/main/jni/$name").absolutePath,
            'clean'
    }
```


## michalgr (31 Mar 2015)

@KennethYo did you add ndk-build to $PATH variable ? If so, then gradle should be able to find ndk-build without specifying full path, unless you run it from Android Studio - we don't support it yet, but this is about to be fixed.

@appledong , @BaronZ, @littletanker - lets keep this issue mac only - as title states. There is similar issue for windows: https://github.com/facebook/fresco/issues/24 lets continue windows related discussion there.


## AU3904 (01 Apr 2015)

@kongnanlive  thinks


## tyronen (01 Apr 2015)

Too many issues are being reported in this thread. Closing it.
- For Windows build issues, please see #24
- For duplicate-giflib issue, see #50 
- For WebP quota issue, see #52 


## yushilong (09 Apr 2015)

@blackiedm thanks,that solved my problem.


## lovely3x (12 Apr 2015)

i solved the problem.
find the commandline 'ndk-build' in imagepipeline/build.gradle and replace 
commandLine 'your local ndk dir/ndk-build.cmd',


## shenzhimeng (21 Apr 2015)

@kongnanlive   @blackiedm  thanks! I solved the problem.


## colarking (29 Aug 2015)

just add one line in file build.gradle task getNdkBuildFullPath;
return your local full ndk-build path.then all done;

def getNdkBuildFullPath() {
    return "/Users/{your_mac_name}/Android/android-ndk-r10e/ndk-build"
    // we allow to provide full path to ndk-build tool
   ......
}


