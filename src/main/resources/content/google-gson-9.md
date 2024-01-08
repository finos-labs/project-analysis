#Gson should provide an ability to customize field name mapping

Owner: google

Repo: gson

Labels: Priority-Medium auto-migrated Type-Enhancement 

## GoogleCodeExporter (19 Mar 2015)

```
Currently Gson just uses the actual name of the field as the Json property
name. This should be customizable. For example, it should be possible to
convert camel-case into hyphenated, or Camel-cased with upper-case first
letter.

so, myFavoriteField in Java gets mapped to my-favorite-field or
MyFavoriteField in Json. 

One approach to achieve this is by providing a name translation policy in
GsonBuilder. 
```

Original issue reported on code.google.com by `inder123` on 22 May 2008 at 5:51


## GoogleCodeExporter (19 Mar 2015)

```
Started implementing this feature.

This bug will be part of the next version cut (tentative date: July 4th).
```

Original comment by `joel.leitch@gmail.com` on 19 Jun 2008 at 1:37
- Added labels: **Type-Enhancement**
- Removed labels: **Type-Defect**


## GoogleCodeExporter (19 Mar 2015)

```
r100 contains the code to support this feature.
```

Original comment by `joel.leitch@gmail.com` on 28 Jun 2008 at 4:46
- Changed state: **Fixed**


