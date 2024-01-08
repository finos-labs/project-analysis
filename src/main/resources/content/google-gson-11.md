#publish javadocs on the web

Owner: google

Repo: gson

Labels: Type-Defect Priority-Medium auto-migrated 

## GoogleCodeExporter (19 Mar 2015)

```
Probably best to check them in the svn alongwith setting properties to
enable them to be viewed as HTML. 
```

Original issue reported on code.google.com by `inder123` on 29 May 2008 at 4:09


## GoogleCodeExporter (19 Mar 2015)

```
Done and also set the svn properties for mime-types:

find docs -name "*.html" -exec svn propset svn:mime-type text/html {} \;
find docs -name "*.gif" -exec svn propset svn:mime-type image/gif {} \;
```

Original comment by `inder123` on 30 May 2008 at 4:03


## GoogleCodeExporter (19 Mar 2015)

Original comment by `inder123` on 30 May 2008 at 4:03
- Changed state: **Fixed**


