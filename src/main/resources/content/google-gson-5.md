#Support JavaBeans properties in addition to class fields

Owner: google

Repo: gson

Labels: auto-migrated Type-Enhancement Priority-Low 

## GoogleCodeExporter (19 Mar 2015)

```
Gson uses class fields for conversion to/from Json. It would also be great
to have a mode of operation (using GsonBuilder) that forces Gson to use
methods as well. Essentially, a mode where Gson only considers the class
members with @Expose annotation.
```

Original issue reported on code.google.com by `inder123` on 27 Apr 2008 at 2:37


## GoogleCodeExporter (19 Mar 2015)

Original comment by `joel.leitch@gmail.com` on 31 Jul 2008 at 12:53
- Added labels: **Type-Enhancement**, **Priority-Low**
- Removed labels: **Type-Defect**, **Priority-Medium**


## GoogleCodeExporter (19 Mar 2015)

```
This library is primarily designed as a field mapping library and it has served 
the 
purpose well. We decided not to implement this feature for now. 
```

Original comment by `inder123` on 13 Nov 2008 at 1:53
- Changed state: **WontFix**


## GoogleCodeExporter (19 Mar 2015)

```
There have been a few requests for supporting this, so I am reopening this 
issue but 
still not promises on fixing it.
```

Original comment by `inder123` on 2 Jun 2009 at 5:43
- Changed state: **New**


## GoogleCodeExporter (19 Mar 2015)

```
Somehow we can not convince ourselves that this is worth doing, so closing it 
again.
```

Original comment by `inder123` on 1 Oct 2009 at 3:58
- Changed state: **WontFix**


