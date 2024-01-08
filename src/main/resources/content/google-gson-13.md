#Publish Gson jars in maven2 repository

Owner: google

Repo: gson

Labels: Type-Defect Priority-Medium auto-migrated 

## GoogleCodeExporter (19 Mar 2015)

```
The subject says it all. We should figure out how guice guys are doing it,
and do the same. 
```

Original issue reported on code.google.com by `inder123` on 30 May 2008 at 10:00


## GoogleCodeExporter (19 Mar 2015)

```
Created a JIRA issue in the maven project for this: 
http://jira.codehaus.org/browse/MAVENUPLOAD-2128

```

Original comment by `inder123` on 7 Jul 2008 at 6:18


## GoogleCodeExporter (19 Mar 2015)

```
Gson 1.1 is now available in maven2 central repository. We should figure out a 
way to
update it automatically. 
```

Original comment by `inder123` on 11 Aug 2008 at 9:25
- Changed state: **Started**


## GoogleCodeExporter (19 Mar 2015)

```
Yes please.
```

Original comment by `antony.s...@gmail.com` on 19 Nov 2008 at 10:12


## GoogleCodeExporter (19 Mar 2015)

```
I'm guessing this [1] is where it will now continue to be available and 
up-to-date? I
just wanted to clarify, since this issue is still open.

[1] http://google-gson.googlecode.com/svn/mavenrepo
```

Original comment by `estebis...@gmail.com` on 28 Jan 2009 at 4:31


## GoogleCodeExporter (19 Mar 2015)

```
You are right that http://google-gson.googlecode.com/svn/mavenrepo remains the 
place 
where latest versions will be available. Maven2 central repository has an 
earlier 
version, but it is too much work to upload a new version there. 

Any volunteers?
```

Original comment by `inder123` on 28 Jan 2009 at 5:04


## GoogleCodeExporter (19 Mar 2015)

```
We have what we have in the maven central repository, and we have our own with 
the 
latest and greatest Gson. Not worth keeping this bug open anymore.
```

Original comment by `inder123` on 1 Oct 2009 at 3:59
- Changed state: **Fixed**


## GoogleCodeExporter (19 Mar 2015)

```
inder123: As you asked for in the JIRA entry, automatic updates are possible and
described at
http://maven.apache.org/guides/mini/guide-central-repository-upload.html#Syncing
_your_own_repository_to_the_central_repository_automatically

I'd really like to see google-gson in the official Maven 2 repositories as that 
would
free every Maven 2 user from the need to add the current repository explicitly 
to
`pom.xml`.
```

Original comment by `j...@nwsnet.de` on 12 Oct 2009 at 12:52


