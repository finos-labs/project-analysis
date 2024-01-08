#JsonParser fails to parse decimal values correctly

Owner: google

Repo: gson

Labels: Type-Defect Priority-Medium auto-migrated 

## GoogleCodeExporter (19 Mar 2015)

```
-122.08 is parsed as -122.0

```

Original issue reported on code.google.com by `inder123` on 8 May 2008 at 5:34


## GoogleCodeExporter (19 Mar 2015)

```
Fixed in r37 by updating the token definition for a digit to support multiple 
digits
after the decimal point. 
This allows numbers with more than one leading zeros as valid numbers even 
though
Json grammar technically disallows it. But I figured that allowing this is a 
little
more permissive without breaking any code, so can't hurt. 

See:
http://groups.google.com/group/google-gson-codereviews/browse_thread/thread/7a22
fa0d485a4a76
```

Original comment by `inder123` on 8 May 2008 at 5:39
- Changed state: **Fixed**


