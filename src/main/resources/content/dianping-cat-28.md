#missing package 'org.unidal.maven.plugins.plexus.profile'?

Owner: dianping

Repo: cat

Labels: 

## kemp-john (10 Apr 2013)

missing package 'org.unidal.maven.plugins.plexus.profile'?

I got some errors while compiling codegen-maven-plugin.

[kemp@ora234 codegen-maven-plugin]$ mvn compile
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building Code Generation Maven Plugin 2.0.5
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- maven-plugin-plugin:2.9:descriptor (default-descriptor) @ codegen-maven-plugin ---
[INFO] Using 'utf-8' encoding to read mojo metadata.
[INFO] Applying mojo extractor for language: java
[INFO] Mojo extractor for language: java found 9 mojo descriptors.
[INFO] Applying mojo extractor for language: bsh
[INFO] Mojo extractor for language: bsh found 0 mojo descriptors.
[INFO]
[INFO] --- maven-resources-plugin:2.5:resources (default-resources) @ codegen-maven-plugin ---
[debug] execute contextualize
[INFO] Using 'utf-8' encoding to copy filtered resources.
[INFO] Copying 6 resources
[INFO]
[INFO] --- maven-compiler-plugin:2.3.2:compile (default-compile) @ codegen-maven-plugin ---
[INFO] Compiling 1 source file to /opt/dianping/maven-plugins/codegen-maven-plugin/target/classes
[INFO] -------------------------------------------------------------
[ERROR] COMPILATION ERROR :
[INFO] -------------------------------------------------------------
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[26,53] package org.unidal.maven.plugins.plexus.profile.entity does not exist
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[27,53] package org.unidal.maven.plugins.plexus.profile.entity does not exist
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[28,53] package org.unidal.maven.plugins.plexus.profile.entity does not exist
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[29,56] package org.unidal.maven.plugins.plexus.profile.transform does not exist
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[71,13] cannot find symbol
symbol : class Profile
location: class org.unidal.maven.plugin.codegen.PlexusMojo
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[26,53] package org.unidal.maven.plugins.plexus.profile.entity does not exist
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[27,53] package org.unidal.maven.plugins.plexus.profile.entity does not exist
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[28,53] package org.unidal.maven.plugins.plexus.profile.entity does not exist
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[29,56] package org.unidal.maven.plugins.plexus.profile.transform does not exist
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[71,13] cannot find symbol
symbol : class Profile
location: class org.unidal.maven.plugin.codegen.PlexusMojo
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[103,9] cannot find symbol
symbol : class Env
location: class org.unidal.maven.plugin.codegen.PlexusMojo
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[112,17] cannot find symbol
symbol : class Property
location: class org.unidal.maven.plugin.codegen.PlexusMojo
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[258,6] cannot find symbol
symbol : class DefaultDomParser
location: class org.unidal.maven.plugin.codegen.PlexusMojo
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[258,36] cannot find symbol
symbol : class DefaultDomParser
location: class org.unidal.maven.plugin.codegen.PlexusMojo
[INFO] 14 errors
[INFO] -------------------------------------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 4.630s
[INFO] Finished at: Tue Apr 09 13:30:42 CST 2013
[INFO] Final Memory: 12M/164M
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:2.3.2:compile (default-compile) on project codegen-maven-plugin: Compilation failure: Compilation failure:
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[26,53] package org.unidal.maven.plugins.plexus.profile.entity does not exist
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[27,53] package org.unidal.maven.plugins.plexus.profile.entity does not exist
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[28,53] package org.unidal.maven.plugins.plexus.profile.entity does not exist
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[29,56] package org.unidal.maven.plugins.plexus.profile.transform does not exist
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[71,13] cannot find symbol
[ERROR] symbol : class Profile
[ERROR] location: class org.unidal.maven.plugin.codegen.PlexusMojo
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[26,53] package org.unidal.maven.plugins.plexus.profile.entity does not exist
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[27,53] package org.unidal.maven.plugins.plexus.profile.entity does not exist
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[28,53] package org.unidal.maven.plugins.plexus.profile.entity does not exist
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[29,56] package org.unidal.maven.plugins.plexus.profile.transform does not exist
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[71,13] cannot find symbol
[ERROR] symbol : class Profile
[ERROR] location: class org.unidal.maven.plugin.codegen.PlexusMojo
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[103,9] cannot find symbol
[ERROR] symbol : class Env
[ERROR] location: class org.unidal.maven.plugin.codegen.PlexusMojo
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[112,17] cannot find symbol
[ERROR] symbol : class Property
[ERROR] location: class org.unidal.maven.plugin.codegen.PlexusMojo
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[258,6] cannot find symbol
[ERROR] symbol : class DefaultDomParser
[ERROR] location: class org.unidal.maven.plugin.codegen.PlexusMojo
[ERROR] /opt/dianping/maven-plugins/codegen-maven-plugin/src/main/java/org/unidal/maven/plugin/codegen/PlexusMojo.java:[258,36] cannot find symbol
[ERROR] symbol : class DefaultDomParser
[ERROR] location: class org.unidal.maven.plugin.codegen.PlexusMojo
[ERROR] -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException


## figoyang (06 Nov 2013)

fixed


