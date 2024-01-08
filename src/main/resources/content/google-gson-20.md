#Gson should limit the allowed recursion depth

Owner: google

Repo: gson

Labels: Type-Defect auto-migrated Priority-Low Milestone-Undefined 

## GoogleCodeExporter (19 Mar 2015)

```
(reported by Meder) While parsing untrusted JSON, or even during attempting
to serialize classes, Gson should consider limiting the maximum allowed
depth for recursion. There should also be an option in GsonBuilder to set a
different value.
```

Original issue reported on code.google.com by `inder123` on 8 Jul 2008 at 5:43


## GoogleCodeExporter (19 Mar 2015)

Original comment by `inder123` on 8 Jul 2008 at 5:44


## GoogleCodeExporter (19 Mar 2015)

```
This bug is similar to issue 19 which is fixed in r118, r117, and r116. 
However, I
will leave it open for now to see if we need to provide explicit option to set
recursion depth. One problem is how does anyone know what a good value for the 
depth is? 
```

Original comment by `inder123` on 19 Jul 2008 at 1:09
- Added labels: **Priority-Low**
- Removed labels: **Priority-Medium**


## GoogleCodeExporter (19 Mar 2015)

Original comment by `inder123` on 1 Oct 2009 at 3:59
- Added labels: **Milestone-Release1.5**


## GoogleCodeExporter (19 Mar 2015)

```
[deleted comment]
```


## GoogleCodeExporter (19 Mar 2015)

```
I agree to add param dept, It does not work fine when I use Hibernate

```

Original comment by `weit...@263.net` on 22 Dec 2009 at 8:04


## GoogleCodeExporter (19 Mar 2015)

Original comment by `inder123` on 1 Nov 2010 at 10:28
- Removed labels: **Milestone-Release1.5**


## GoogleCodeExporter (19 Mar 2015)

Original comment by `inder123` on 3 Nov 2010 at 12:25
- Added labels: **Milestone-Undefined**


## GoogleCodeExporter (19 Mar 2015)

```
Why is this needed?
```

Original comment by `michael.hixson@gmail.com` on 19 Apr 2011 at 6:54


## GoogleCodeExporter (19 Mar 2015)

```
I say that recursion should not be used for parsing at all, queues should be 
used instead to avoid code recursion all together.

```

Original comment by `pawel.ve...@gmail.com` on 17 Dec 2011 at 2:25


## GoogleCodeExporter (19 Mar 2015)

```
We don't recurse in the streaming parser.
```

Original comment by `limpbizkit` on 23 Dec 2011 at 5:35
- Changed state: **WontFix**


