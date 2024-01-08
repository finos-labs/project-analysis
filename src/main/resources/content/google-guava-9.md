#[Patch] Add a target to build a jar file

Owner: google

Repo: guava

Labels: type=defect status=fixed 

## gissuebot (31 Oct 2014)

_[Original issue](https://code.google.com/p/guava-libraries/issues/detail?id=9) created by **blair-ol...@orcaware.com** on 2009-12-12 at 07:17 PM_

---

Here's a small patch to build a jar file from the project.  This would make it
easier for people to use the jar in projects before it gets Mavenized.

```
diff --git a/build.xml b/build.xml
index fb0b0e4..3f43b1a 100644
--- a/build.xml
+++ b/build.xml
@@ -21,6 +21,10 @@
     </javac>
   </target>

+  <target name="jar" depends="compile">
+    <jar destfile="google-guava.jar" basedir="build/classes" />
+  </target>
+
   <target name="clean"
       description="Remove generated files.">
     <delete dir="build"/>
```


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=9#c1) posted by **dennisbijlsma001** on 2010-02-01 at 04:57 PM_

---

Also, not everyone is using Maven, so this would be useful even after it gets "Mavenized". Btw, Google Collections 
does provide a JAR, so I expect it will be added to this project eventually.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=9#c2) posted by **kevinb@google.com** on 2010-04-09 at 04:04 PM_

---

_(No comment entered for this change.)_

---

**Status:** `Fixed`


