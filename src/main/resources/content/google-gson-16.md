#Provide annotation to rename an individual field

Owner: google

Repo: gson

Labels: Priority-Medium auto-migrated Type-Enhancement 

## GoogleCodeExporter (19 Mar 2015)

```
I'd like the ability to control the name of a property being serialized on
a per-field basis using an annotation. Something like:

   @SerializeAsName("id")
   String getUniqueName() { ... }

Other alternatives are @Name, @Named, @JsonName, etc.
```

Original issue reported on code.google.com by `viri...@gmail.com` on 4 Jun 2008 at 10:09


## GoogleCodeExporter (19 Mar 2015)

```
This request is similar to Issue 9.

We should develop a generic naming policy that can handle both annotation based 
and
general field renaming (or a combination of naming policies).
```

Original comment by `joel.leitch@gmail.com` on 6 Jun 2008 at 12:30


## GoogleCodeExporter (19 Mar 2015)

```
Generic naming policy infrastructure has been added so now I need to hook in the
annotation based naming strategy.
```

Original comment by `joel.leitch@gmail.com` on 28 Jun 2008 at 4:50
- Changed state: **Accepted**


## GoogleCodeExporter (19 Mar 2015)

```
Feature request submitted in r104.  This change will be rolled out in the 1.1 
release.

See https://sites.google.com/site/gson/gson-roadmap for information on upcoming 
releases.
```

Original comment by `joel.leitch@gmail.com` on 30 Jun 2008 at 2:30
- Changed state: **Fixed**
- Added labels: **Type-Enhancement**
- Removed labels: **Type-Defect**


