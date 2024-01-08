#Gson does not handle null values in arrays properly

Owner: google

Repo: gson

Labels: Type-Defect Priority-Medium auto-migrated 

## GoogleCodeExporter (19 Mar 2015)

```
(reported by Ben Littman)
This:
    System.out.println(gson.toJson(new String[]{"foo", "bar"}));
results in:
["foo", "bar]

But should a null value be allowed, as in:
    System.out.println(gson.toJson(new String[]{"foo", null,"bar"}));
which results in:
java.lang.NullPointerException
        at
com.google.gson.JsonTreeNavigator.visitChild(JsonTreeNavigator.java:76)
        at
com.google.gson.JsonTreeNavigator.navigate(JsonTreeNavigator.java:39)
        at
com.google.gson.JsonCompactFormatter.format(JsonCompactFormatter.java:112)
        at com.google.gson.Gson.toJson(Gson.java:270)
        at com.google.gson.Gson.toJson(Gson.java:243)

Also, this:
    String[] stringArray = gson.fromJson("[\"foo\", \"bar\"]", String[].class);
returns the string array one would expect

But an serialized array with a null in it:
    String[] stringArray = gson.fromJson("[\"foo\", null, \"bar\"]",
String[].class);
results in:
java.lang.IllegalState
Exception
        at
com.google.gson.JsonArrayDeserializationVisitor.visitArray(JsonArrayDeserializat
ionVisi
tor.java:73)
        at com.google.gson.ObjectNavigator.accept(ObjectNavigator.java:136)
        at
com.google.gson.JsonDeserializationContextDefault.fromJsonArray(JsonDeserializat
ionCont
extDefault.java:63)
        at
com.google.gson.JsonDeserializationContextDefault.deserialize(JsonDeserializatio
nContex
tDefault.java:45)
        at com.google.gson.Gson.fromJson(Gson.java:319)
        at com.google.gson.Gson.fromJson(Gson.java:292)
```

Original issue reported on code.google.com by `inder123` on 26 Jun 2008 at 7:38


## GoogleCodeExporter (19 Mar 2015)

```
Fixed in r99:
http://groups.google.com/group/google-gson-codereviews/browse_thread/thread/c01b
dc408a4dae95#
```

Original comment by `inder123` on 26 Jun 2008 at 7:46
- Changed state: **Fixed**


