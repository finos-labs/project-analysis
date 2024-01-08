#Can not parse null arrays

Owner: google

Repo: gson

Labels: Type-Defect Priority-Medium auto-migrated 

## GoogleCodeExporter (19 Mar 2015)

```
What steps will reproduce the problem?
1. JSON to parse: 
2. Java: class Test { Object[] my_array; }
3. gson.fromJson("{\"my_array\": null}", Test.class);

What is the expected output? What do you see instead?

Expected: A Test object with my_array initialized to null.
Actual: java.lang.NullPointerException
        at
com.google.gson.JsonDeserializationVisitor.visitChildAsArray(JsonDeserialization
Visitor.java:93)
        at
com.google.gson.JsonObjectDeserializationVisitor.visitArrayField(JsonObjectDeser
ializationVisitor.java:114)
        at
com.google.gson.ObjectNavigator.navigateClassFields(ObjectNavigator.java:172)
        at com.google.gson.ObjectNavigator.accept(ObjectNavigator.java:152)
        at
com.google.gson.JsonDeserializationContextDefault.fromJsonObject(JsonDeserializa
tionContextDefault.java:75)
        at
com.google.gson.JsonDeserializationContextDefault.deserialize(JsonDeserializatio
nContextDefault.java:47)
        at com.google.gson.Gson.fromJson(Gson.java:310)
        at com.google.gson.Gson.fromJson(Gson.java:285)

What version of the product are you using? On what operating system?

v1_0 on Linux

```

Original issue reported on code.google.com by `ch...@gmail.com` on 2 Jun 2008 at 11:52


## GoogleCodeExporter (19 Mar 2015)

```
Sorry, the JSON missing in "step 1" is inline in "step 3".
```

Original comment by `ch...@gmail.com` on 2 Jun 2008 at 11:53


## GoogleCodeExporter (19 Mar 2015)

```
Also, null values outside of arrays also fail to parse due to a Precondition 
failure:

java.lang.IllegalArgumentException: condition failed: false
        at com.google.gson.Preconditions.checkArgument(Preconditions.java:39)
        at com.google.gson.Preconditions.checkNotNull(Preconditions.java:34)
        at
com.google.gson.JsonDeserializationVisitor.<init>(JsonDeserializationVisitor.jav
a:46)
        at
com.google.gson.JsonObjectDeserializationVisitor.<init>(JsonObjectDeserializatio
nVisitor.java:37)
        at
com.google.gson.JsonDeserializationVisitor.visitChildAsObject(JsonDeserializatio
nVisitor.java:85)
        at
com.google.gson.JsonObjectDeserializationVisitor.visitObjectField(JsonObjectDese
rializationVisitor.java:72)
        at com.google.gson.ObjectNavigator.navigateClassFields(ObjectNavigator.java:179)
        at com.google.gson.ObjectNavigator.accept(ObjectNavigator.java:152)
        at
com.google.gson.JsonDeserializationContextDefault.fromJsonObject(JsonDeserializa
tionContextDefault.java:75)
        at
com.google.gson.JsonDeserializationContextDefault.deserialize(JsonDeserializatio
nContextDefault.java:47)

I'm pretty sure null is a valid JSON type, but even if it's not, then this 
should
throw a ParseError.
```

Original comment by `ch...@gmail.com` on 2 Jun 2008 at 5:12


## GoogleCodeExporter (19 Mar 2015)

```
null is a valid JSON type as per JSON spec
(http://tools.ietf.org/id/draft-crockford-jsonorg-json-04.txt). I just wrote a 
test
(thanks for providing a good example) and this is indeed failing. We will fix 
this
and put it in our next release. 
```

Original comment by `inder123` on 2 Jun 2008 at 5:51
- Changed state: **Started**


## GoogleCodeExporter (19 Mar 2015)

```
Fixed in r90: 
http://groups.google.com/group/google-gson-codereviews/browse_thread/thread/ec69
ab8bcddfa3da

Actually, well designed Json should just omit the field instead of passing 
nulls.
However, Gson should handle the case as well, and that is why we have now added
support for it. 
```

Original comment by `inder123` on 2 Jun 2008 at 7:37
- Changed state: **Fixed**


## GoogleCodeExporter (19 Mar 2015)

```
Revised the fix. See: 
http://groups.google.com/group/google-gson-codereviews/browse_thread/thread/1507
840c6b401a0c
```

Original comment by `inder123` on 2 Jun 2008 at 8:11


