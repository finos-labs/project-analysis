#./gradlew build >> :referenceHtmlMulti FAILED  FAILURE: Build failed with an exception.

Owner: spring-projects

Repo: spring-framework

Labels: 

## DouglasAllen (04 Dec 2012)

This is from my PS lines.
spring-framework [master]> ./gradlew build
:spring-core:asmRepackJar UP-TO-DATE
:spring-core:cglibRepackJar UP-TO-DATE
:spring-core:compileJava UP-TO-DATE
:spring-core:processResources UP-TO-DATE
:spring-core:classes UP-TO-DATE
:spring-core:jar UP-TO-DATE
:spring-beans:compileJava UP-TO-DATE
:spring-beans:processResources UP-TO-DATE
:spring-beans:classes UP-TO-DATE
:spring-beans:jar UP-TO-DATE
:spring-aop:compileJava UP-TO-DATE
:spring-aop:processResources UP-TO-DATE
:spring-aop:classes UP-TO-DATE
:spring-aop:jar UP-TO-DATE
:spring-expression:compileJava UP-TO-DATE
:spring-expression:processResources UP-TO-DATE
:spring-expression:classes UP-TO-DATE
:spring-expression:jar UP-TO-DATE
:spring-instrument:compileJava UP-TO-DATE
:spring-instrument:processResources UP-TO-DATE
:spring-instrument:classes UP-TO-DATE
:spring-instrument:jar UP-TO-DATE
:spring-context:compileJava UP-TO-DATE
:spring-context:processResources UP-TO-DATE
:spring-context:classes UP-TO-DATE
:spring-context:jar UP-TO-DATE
:spring-tx:compileJava UP-TO-DATE
:spring-tx:processResources UP-TO-DATE
:spring-tx:classes UP-TO-DATE
:spring-tx:jar UP-TO-DATE
:spring-jdbc:compileJava UP-TO-DATE
:spring-jdbc:processResources UP-TO-DATE
:spring-jdbc:classes UP-TO-DATE
:spring-jdbc:jar UP-TO-DATE
:spring-context-support:compileJava UP-TO-DATE
:spring-context-support:processResources UP-TO-DATE
:spring-context-support:classes UP-TO-DATE
:spring-context-support:jar UP-TO-DATE
:spring-oxm:compileJava UP-TO-DATE
:spring-oxm:processResources UP-TO-DATE
:spring-oxm:classes UP-TO-DATE
:spring-oxm:jar UP-TO-DATE
:spring-web:compileJava UP-TO-DATE
:spring-web:processResources UP-TO-DATE
:spring-web:classes UP-TO-DATE
:spring-web:jar UP-TO-DATE
:spring-orm:compileJava UP-TO-DATE
:spring-orm:processResources UP-TO-DATE
:spring-orm:classes UP-TO-DATE
:spring-orm-hibernate4:compileJava UP-TO-DATE
:spring-orm-hibernate4:processResources UP-TO-DATE
:spring-orm-hibernate4:classes UP-TO-DATE
:spring-orm:jar UP-TO-DATE
:spring-webmvc:compileJava UP-TO-DATE
:spring-webmvc:processResources UP-TO-DATE
:spring-webmvc:classes UP-TO-DATE
:spring-web:compileTestJava UP-TO-DATE
:spring-web:processTestResources UP-TO-DATE
:spring-web:testClasses UP-TO-DATE
:spring-webmvc-tiles3:compileJava UP-TO-DATE
:spring-webmvc-tiles3:processResources UP-TO-DATE
:spring-webmvc-tiles3:classes UP-TO-DATE
:spring-webmvc:jar UP-TO-DATE
:spring-webmvc-portlet:compileJava UP-TO-DATE
:spring-webmvc-portlet:processResources UP-TO-DATE
:spring-webmvc-portlet:classes UP-TO-DATE
:spring-webmvc-portlet:jar UP-TO-DATE
:spring-test:compileJava UP-TO-DATE
:spring-test:processResources UP-TO-DATE
:spring-test:classes UP-TO-DATE
:api UP-TO-DATE
:referenceHtmlMulti FAILED

FAILURE: Build failed with an exception.
- What went wrong:
  Execution failed for task ':referenceHtmlMulti'.
  
  > C:\Documents and Settings\kb9agt\My Documents\GitHub\spring-framework\build\reference-work\xsl\titlepage\spring-html.xsl (Th
  > e system cannot find the file specified)
- Try:
  Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.

BUILD FAILED

Total time: 3 mins 57.407 secs

Need help so I can go on to 
./gradlew install


## edwardbeckett (04 Dec 2012)

Try installing to a location that doesn't have spaces in the path ... IE. c:\base_dir\spring-framework\ 


## philwebb (04 Dec 2012)

Could you run with -s and attache the stack trace please.


## cbeams (04 Dec 2012)

workaround: run with `-x reference`

On Dec 4, 2012, at 6:51 PM, "Douglas G. Allen" notifications@github.com wrote:

> This is from my PS lines.
> spring-framework [master]> ./gradlew build
> :spring-core:asmRepackJar UP-TO-DATE
> :spring-core:cglibRepackJar UP-TO-DATE
> :spring-core:compileJava UP-TO-DATE
> :spring-core:processResources UP-TO-DATE
> :spring-core:classes UP-TO-DATE
> :spring-core:jar UP-TO-DATE
> :spring-beans:compileJava UP-TO-DATE
> :spring-beans:processResources UP-TO-DATE
> :spring-beans:classes UP-TO-DATE
> :spring-beans:jar UP-TO-DATE
> :spring-aop:compileJava UP-TO-DATE
> :spring-aop:processResources UP-TO-DATE
> :spring-aop:classes UP-TO-DATE
> :spring-aop:jar UP-TO-DATE
> :spring-expression:compileJava UP-TO-DATE
> :spring-expression:processResources UP-TO-DATE
> :spring-expression:classes UP-TO-DATE
> :spring-expression:jar UP-TO-DATE
> :spring-instrument:compileJava UP-TO-DATE
> :spring-instrument:processResources UP-TO-DATE
> :spring-instrument:classes UP-TO-DATE
> :spring-instrument:jar UP-TO-DATE
> :spring-context:compileJava UP-TO-DATE
> :spring-context:processResources UP-TO-DATE
> :spring-context:classes UP-TO-DATE
> :spring-context:jar UP-TO-DATE
> :spring-tx:compileJava UP-TO-DATE
> :spring-tx:processResources UP-TO-DATE
> :spring-tx:classes UP-TO-DATE
> :spring-tx:jar UP-TO-DATE
> :spring-jdbc:compileJava UP-TO-DATE
> :spring-jdbc:processResources UP-TO-DATE
> :spring-jdbc:classes UP-TO-DATE
> :spring-jdbc:jar UP-TO-DATE
> :spring-context-support:compileJava UP-TO-DATE
> :spring-context-support:processResources UP-TO-DATE
> :spring-context-support:classes UP-TO-DATE
> :spring-context-support:jar UP-TO-DATE
> :spring-oxm:compileJava UP-TO-DATE
> :spring-oxm:processResources UP-TO-DATE
> :spring-oxm:classes UP-TO-DATE
> :spring-oxm:jar UP-TO-DATE
> :spring-web:compileJava UP-TO-DATE
> :spring-web:processResources UP-TO-DATE
> :spring-web:classes UP-TO-DATE
> :spring-web:jar UP-TO-DATE
> :spring-orm:compileJava UP-TO-DATE
> :spring-orm:processResources UP-TO-DATE
> :spring-orm:classes UP-TO-DATE
> :spring-orm-hibernate4:compileJava UP-TO-DATE
> :spring-orm-hibernate4:processResources UP-TO-DATE
> :spring-orm-hibernate4:classes UP-TO-DATE
> :spring-orm:jar UP-TO-DATE
> :spring-webmvc:compileJava UP-TO-DATE
> :spring-webmvc:processResources UP-TO-DATE
> :spring-webmvc:classes UP-TO-DATE
> :spring-web:compileTestJava UP-TO-DATE
> :spring-web:processTestResources UP-TO-DATE
> :spring-web:testClasses UP-TO-DATE
> :spring-webmvc-tiles3:compileJava UP-TO-DATE
> :spring-webmvc-tiles3:processResources UP-TO-DATE
> :spring-webmvc-tiles3:classes UP-TO-DATE
> :spring-webmvc:jar UP-TO-DATE
> :spring-webmvc-portlet:compileJava UP-TO-DATE
> :spring-webmvc-portlet:processResources UP-TO-DATE
> :spring-webmvc-portlet:classes UP-TO-DATE
> :spring-webmvc-portlet:jar UP-TO-DATE
> :spring-test:compileJava UP-TO-DATE
> :spring-test:processResources UP-TO-DATE
> :spring-test:classes UP-TO-DATE
> :api UP-TO-DATE
> :referenceHtmlMulti FAILED
> 
> FAILURE: Build failed with an exception.
> 
> What went wrong:
> Execution failed for task ':referenceHtmlMulti'.
> 
> C:\Documents and Settings\kb9agt\My Documents\GitHub\spring-framework\build\reference-work\xsl\titlepage\spring-html.xsl (Th
> e system cannot find the file specified)
> 
> Try:
> Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.
> 
> BUILD FAILED
> 
> Total time: 3 mins 57.407 secs
> 
> Need help so I can go on to 
> ./gradlew install
> 
> —
> Reply to this email directly or view it on GitHub.


