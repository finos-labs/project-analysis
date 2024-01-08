#Fresco and OpenCV

Owner: facebook

Repo: fresco

Labels: build 

## leicht-io (27 Mar 2015)

Hello,

I am having some techinical issues with Fresco and OpenCV. 
I am compiling OpenCV from source using the Android NDK, and after including Fresco i get the following exception:
Caused by: java.lang.UnsatisfiedLinkError: dalvik.system.PathClassLoader[DexPathList[[zip file "/data/app/xxx-1/base.apk"],nativeLibraryDirectories=[/data/app/xxx-1/lib/arm, /vendor/lib, /system/lib]]] couldn't find "libopencv_java.so"

It seems that, somehow with Fresco included, the libopencv_java.so doesn't get included in my application.
After removing the Fresco library it seems to work as intended.

In my build.gradle i am compiling OpenCV with the following task:

```
sourceSets.main.jni.srcDirs = []
task ndkBuild(type: Exec, description: 'Compile JNI source via NDK') {
    commandLine "path to ndk-build.cmd",
            'NDK_PROJECT_PATH=build/intermediates/ndk',
            'NDK_LIBS_OUT=src/main/jniLibs',
            'APP_BUILD_SCRIPT=src/main/jni/Android.mk',
            'NDK_APPLICATION_MK=src/main/jni/Application.mk'
}

tasks.withType(JavaCompile) {
    compileTask -> compileTask.dependsOn ndkBuild
}
```

I do not know what's creating this error, but I hope you can help me.
Thank you - Christian.


## michalgr (27 Mar 2015)

Have you tried to verify whether all *.so files are included in the final apk ? This might help establish what is going on.

To check that just unzip your apk and examine content of 'lib' directory


## plamenko (17 Apr 2015)

Closing this as we haven't heard back. Please reopen if this is still an issue.


