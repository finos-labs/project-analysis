#An error has occurred on open latest version of dbeaver for Mac

Owner: dbeaver

Repo: dbeaver

Labels: won't do question 

## phoenixg (25 Oct 2015)

!SESSION 2015-10-25 20:44:28.041 -----------------------------------------------
eclipse.buildId=unknown
java.version=1.6.0_65
java.vendor=Apple Inc.
BootLoader constants: OS=macosx, ARCH=x86_64, WS=cocoa, NL=zh_CN
Framework arguments:  -keyring /Users/phoenix/.eclipse_keyring -showlocation
Command-line arguments:  -os macosx -ws cocoa -arch x86_64 -keyring /Users/phoenix/.eclipse_keyring -showlocation

!ENTRY com.jkiss.dbeaver.ent.application 4 0 2015-10-25 20:44:28.710
!MESSAGE FrameworkEvent ERROR
!STACK 0
org.osgi.framework.BundleException: Could not resolve module: com.jkiss.dbeaver.ent.application [10]
  Unresolved requirement: Require-Capability: osgi.ee; filter:="(&(osgi.ee=JavaSE)(version=1.7))"


## serge-rider (25 Oct 2015)

Since version 3.5+ DBeaver requires Java 1.7+:

Unresolved requirement: Require-Capability: osgi.ee; filter:="(&(osgi.ee=JavaSE)(version=1.7))"


## granteagon (08 Jul 2016)

@serge-rider Thanks for this awesome tool!

Unfortunately, I am also having this problem with the download currently available on the home page in the mac .dmg.  I checked my version of java and it says everything is up to date.

![Sad Beaver](https://s3.amazonaws.com/f.cl.ly/items/0U1e351p0B3x201K3g02/Image%202016.07.08%209%3A58%3A55%20AM.png?v=8fe2d6e9)

![Current Mac System](https://s3.amazonaws.com/f.cl.ly/items/021E1J032l2u050J331X/Image%202016.07.08%2010%3A02%3A06%20AM.png?v=bc0c08fd)

Console log:

```
!SESSION 2016-07-08 09:44:22.089 -----------------------------------------------
eclipse.buildId=unknown
java.version=1.6.0_65
java.vendor=Apple Inc.
BootLoader constants: OS=macosx, ARCH=x86_64, WS=cocoa, NL=en_US
Framework arguments:  -keyring /Users/grant/.eclipse_keyring -showlocation
Command-line arguments:  -os macosx -ws cocoa -arch x86_64 -keyring /Users/grant/.eclipse_keyring -showlocation

!ENTRY org.eclipse.compare.core 4 0 2016-07-08 09:44:26.423
!MESSAGE FrameworkEvent ERROR
!STACK 0
org.osgi.framework.BundleException: Could not resolve module: org.eclipse.compare.core [17]
  Unresolved requirement: Require-Bundle: org.eclipse.core.runtime; bundle-version="[3.2.0,4.0.0)"
    -> Bundle-SymbolicName: org.eclipse.core.runtime; bundle-version="3.11.1.v20150903-1804"; singleton:="true"
       org.eclipse.core.runtime [29]
         Unresolved requirement: Require-Bundle: org.eclipse.core.jobs; bundle-version="[3.2.0,4.0.0)"; visibility:="reexport"
           -> Bundle-SymbolicName: org.eclipse.core.jobs; bundle-version="3.7.0.v20150330-2103"; singleton:="true"
              org.eclipse.core.jobs [27]
                Unresolved requirement: Require-Capability: osgi.ee; filter:="(&(osgi.ee=JavaSE)(version=1.7))"

    at org.eclipse.osgi.container.Module.start(Module.java:434)
    at org.eclipse.osgi.container.ModuleContainer$ContainerStartLevel.incStartLevel(ModuleContainer.java:1582)
    at org.eclipse.osgi.container.ModuleContainer$ContainerStartLevel.incStartLevel(ModuleContainer.java:1561)
    at org.eclipse.osgi.container.ModuleContainer$ContainerStartLevel.doContainerStartLevel(ModuleContainer.java:1533)
    at org.eclipse.osgi.container.ModuleContainer$ContainerStartLevel.dispatchEvent(ModuleContainer.java:1476)
    at org.eclipse.osgi.container.ModuleContainer$ContainerStartLevel.dispatchEvent(ModuleContainer.java:1)
    at org.eclipse.osgi.framework.eventmgr.EventManager.dispatchEvent(EventManager.java:230)
    at org.eclipse.osgi.framework.eventmgr.EventManager$EventThread.run(EventManager.java:340)
```


## serge-rider (08 Jul 2016)

Looks like you need to setup new jdk.
Check #307


## aderchox (26 Mar 2021)

I'm not opening a new issue to avoid duplicate issues, but I hope someone will respond because I'm not even able to open my DBeaver. Also the error is not helpful at all. My issue is similar to the one on this page, but there are **things worth mentioning:**
1. I'm on Windows 10 and not on MacOS, and my OS is up to date.
2. I have tried both JDK 1.8 (Java 8) and JDK 15 without success (downloaded from official Oracle website)
3. Here's the exact error I can see in the generated log:
```
!SESSION 2021-03-26 00:00:00.000 / -----------------------------------------------
eclipse.buildId=unknown
java.version=15.0.2
java.vendor=AdoptOpenJDK
BootLoader constants: OS=win32, ARCH=x86_64, WS=win32, NL=en_US
Command-line arguments:  -os win32 -ws win32 -arch x86_64

!ENTRY org.eclipse.osgi 4 0 2021-03-26 00:00:00.000
!MESSAGE Application error
!STACK 1
java.lang.IllegalStateException: Unable to acquire application service. Ensure that the org.eclipse.core.runtime bundle is resolved and started (see config.ini).
	at org.eclipse.core.runtime.internal.adaptor.EclipseAppLauncher.start(EclipseAppLauncher.java:81)
	at org.eclipse.core.runtime.adaptor.EclipseStarter.run(EclipseStarter.java:401)
	at org.eclipse.core.runtime.adaptor.EclipseStarter.run(EclipseStarter.java:255)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:64)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:564)
	at org.eclipse.equinox.launcher.Main.invokeFramework(Main.java:657)
	at org.eclipse.equinox.launcher.Main.basicRun(Main.java:594)
	at org.eclipse.equinox.launcher.Main.run(Main.java:1447)

```
Also here's the error when trying it with JDK 1.8:
```
!SESSION 2021-03-26 00:00:00.000 -----------------------------------------------
eclipse.buildId=unknown
java.version=1.8.0_281
java.vendor=Oracle Corporation
BootLoader constants: OS=win32, ARCH=x86_64, WS=win32, NL=en
Command-line arguments:  -os win32 -ws win32 -arch x86_64

!ENTRY org.eclipse.osgi 4 0 2021-03-26 00:00:00.000
!MESSAGE Application error
!STACK 1
java.lang.IllegalStateException: Unable to acquire application service. Ensure that the org.eclipse.core.runtime bundle is resolved and started (see config.ini).
	at org.eclipse.core.runtime.internal.adaptor.EclipseAppLauncher.start(EclipseAppLauncher.java:81)
	at org.eclipse.core.runtime.adaptor.EclipseStarter.run(EclipseStarter.java:401)
	at org.eclipse.core.runtime.adaptor.EclipseStarter.run(EclipseStarter.java:255)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.eclipse.equinox.launcher.Main.invokeFramework(Main.java:657)
	at org.eclipse.equinox.launcher.Main.basicRun(Main.java:594)
	at org.eclipse.equinox.launcher.Main.run(Main.java:1447)
```

