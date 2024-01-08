#Provide default support for java.util.Map

Owner: google

Repo: gson

Labels: Type-Defect Priority-Medium auto-migrated 

## GoogleCodeExporter (19 Mar 2015)

```
Should be able to convert the default representation of a Map. 
Should be able to deserialize {{"a":"1"},{"b":2"}} as well as
[{"a":"1"},{"b":2"}] to a map. Consider using a concrete implementation
(linked list) of Map that preserves the order of elements. 
```

Original issue reported on code.google.com by `inder123` on 25 Apr 2008 at 7:16


## GoogleCodeExporter (19 Mar 2015)

```
Added support for serialization and deserialization of all subclasses of
java.util.Map in changelist r13:
http://groups.google.com/group/google-gson-codereviews/browse_thread/thread/6a6c
fc7309918aea
```

Original comment by `inder123` on 29 Apr 2008 at 4:19
- Changed state: **Fixed**


