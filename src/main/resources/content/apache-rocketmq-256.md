#Fix Broker and NameServer startup

Owner: apache

Repo: rocketmq

Labels: 

## lindzh (27 Mar 2018)

The issue tracker is **ONLY** used for bug report and feature request. Keep in mind, please check whether there is an existing same report before your raise a new one.

Alternately (especially if your communication is not a bug report), you can send mail to our [mailing lists](http://rocketmq.apache.org/about/contact/). We welcome any friendly suggestions, bug fixes, collaboration and other improvements.

Please ensure that your bug report is clear and that it is complete. Otherwise, we may be unable to understand it or to reproduce it, either of which would prevent us from fixing the bug. We strongly recommend the report(bug report or feature request) could include some hints as the following:

**BUG REPORT**

1. Please describe the issue you observed:
Broker and NameServer start failed because less of slf4j.

- What did you do (The steps to reproduce)?
change branch to develop ,and release as follow:

```
mvn -Prelease-all -DskipTests clean install -U
```

install apache rocketmq.and run with `sh mqnamesrv & `

- What did you expect to see?
Start success.
- What did you see instead?

```
java.lang.NoClassDefFoundError: org/slf4j/LoggerFactory
	at org.apache.rocketmq.namesrv.NamesrvStartup.main0(NamesrvStartup.java:94)
	at org.apache.rocketmq.namesrv.NamesrvStartup.main(NamesrvStartup.java:47)
Caused by: java.lang.ClassNotFoundException: org.slf4j.LoggerFactory
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	... 2 more
```

2. Please tell us about your environment:
Mac pro. jdk8

3. Other information (e.g. detailed explanation, logs, related issues, suggestions how to fix, etc):




