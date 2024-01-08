#Provide a mode of operation to force explicit serialization and deserialization

Owner: google

Repo: gson

Labels: Type-Defect Priority-Medium auto-migrated 

## GoogleCodeExporter (19 Mar 2015)

```
Provide a GsonBuilder setting that forces Gson to ignore all fields except
those marked with a specific annotation like "@Expose"

```

Original issue reported on code.google.com by `inder123` on 27 Apr 2008 at 2:34


## GoogleCodeExporter (19 Mar 2015)

Original comment by `inder123` on 27 Apr 2008 at 2:34


## GoogleCodeExporter (19 Mar 2015)

```
Fixed in r34 by providing an @Expose annotation that can be applied to fields. 
The
explicit serialization and deserialization can be enabled by invoking the
excludeFieldsWithoutExposeAnnotation() method of GsonBuilder. 

See
http://groups.google.com/group/google-gson-codereviews/browse_thread/thread/aa9f
9a8c8cb4a2a4
```

Original comment by `inder123` on 9 May 2008 at 8:48
- Changed state: **Fixed**


