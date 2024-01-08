#Json + schema

Owner: google

Repo: gson

Labels: auto-migrated Type-Enhancement Priority-Low Milestone-Undefined 

## GoogleCodeExporter (19 Mar 2015)

```
http://groups.google.com/group/json-schema/web/json-schema-proposal---second-dra
ft
has a discussion on how to define a Json schema. We should explore how this
can be used for Gson. In Gson, currently, the Java classes define the
schema. May be we can provide a validator that validates the schema against
a set of classes.Or generates the Json schema corresponding to a set of
classes (using the ObjectNavigator). 
```

Original issue reported on code.google.com by `inder123` on 13 Jun 2008 at 8:46


## GoogleCodeExporter (19 Mar 2015)

```
FYI... I've started implementing a JsonValidator class to do just this based on 
top
of the Gson library.  I have only implemented the validation I have needed so 
far,
but I can post it if you are interested.
```

Original comment by `z13g...@gmail.com` on 14 Apr 2009 at 8:33


## GoogleCodeExporter (19 Mar 2015)

```
It would be great if you can either post it in a forum, or create a new 
open-source 
project that we can point people to for this. 
Thanks
```

Original comment by `inder123` on 14 Apr 2009 at 8:41


## GoogleCodeExporter (19 Mar 2015)

Original comment by `inder123` on 3 Nov 2010 at 12:25
- Added labels: **Milestone-Undefined**


## GoogleCodeExporter (19 Mar 2015)

Original comment by `limpbizkit` on 24 Jan 2011 at 10:21
- Changed title: **Json + schema**
- Added labels: **Priority-Low**
- Removed labels: **Priority-Medium**


## GoogleCodeExporter (19 Mar 2015)

```
Joel pointed me to this IETF draft
http://tools.ietf.org/html/draft-zyp-json-schema-03

I don't like it because it is not simple. It has many features that should be 
left up to the application-level: value range checking, hrefs, IDs, enums, 
inheritance, refs, etc.  These all remind me of XML, which is why we all like 
JSON so much.
```

Original comment by `limpbizkit` on 15 Feb 2011 at 12:21


## GoogleCodeExporter (19 Mar 2015)

```
@limpbizkit: I generally agree with your concerns about using a schema language.

However, the big advantage of a schema language is that it is language neutral 
documentation. Currently, the Java Objects used by Gson is the real 
specification. Having a schema would be nice.
```

Original comment by `inder123` on 8 Jul 2011 at 3:31


## GoogleCodeExporter (19 Mar 2015)

```
@inder123: ya, a specification can provide a neutral guide to do interaction 
with other language or platform. But implementing such a specification is a 
time-consuming work. For your experience, how much work it should take to do 
this work? you know, I am really interested in it.
```

Original comment by `alloyer` on 8 Jul 2011 at 3:42


## GoogleCodeExporter (19 Mar 2015)

```
It is not going to be too much work. How about you experiment with generating 
DOM with Gson by using Gson.toJsonTree() method. Currently 
Gson.toJson(JsonElement e) method prints the JSON for the DOM tree. Instead, 
you need to output the schema. Should be as simple as walking through each of 
the DOM nodes and writing schema elements to the output.
```

Original comment by `inder123` on 8 Jul 2011 at 5:11


## GoogleCodeExporter (19 Mar 2015)

```
There are plenty of time, users choose XML, for it's concrete schema validation 
feature. a quick solution on top of my head , may be not so efficient, we can 
abstract. That is 
convert json schema in to xml one (less work I assume)
use xml validation feature to verify data. 

```

Original comment by `estifa...@google.com` on 8 Jul 2011 at 5:37


## GoogleCodeExporter (19 Mar 2015)

```
I am starting the work to describe a Java type in Json schema. But I can't get 
how to describe the generic type of a list? I know I can use "array" to 
represent a List type, but where to set the entity type for the List?
```

Original comment by `alloyer` on 11 Jul 2011 at 8:50


## GoogleCodeExporter (19 Mar 2015)

```
Might be worth asking this question on the JSON Schema group:
https://groups.google.com/forum/#!forum/json-schema

I am actually not sure if this JSON schema is the most popular schema language 
for JSON. Please look around to see if you find a schema language that is 
complete and has gained most traction.
```

Original comment by `inder123` on 11 Jul 2011 at 8:57


## GoogleCodeExporter (19 Mar 2015)

```
Here is a pointer to another schema language for JSON:
http://tools.ietf.org/html/draft-zyp-json-schema-03

It seems like this one is from IETF so is more official. Please help figure out 
which is the most popular schema language.
```

Original comment by `inder123` on 11 Jul 2011 at 9:00


## GoogleCodeExporter (19 Mar 2015)

```
If we ever do this, I'd prefer we do it as an optional extension.
```

Original comment by `limpbizkit` on 29 Dec 2011 at 5:50


## GoogleCodeExporter (19 Mar 2015)

```
It only took me 2 years to get around to posting it, but here is the JSON 
Validation code a wrote a while ago.

Feel free to do with it as you will.  If (within the next few years) I create a 
opensource project for it, I'll post here.  If someone else does, please post a 
note here so that I can follow it.

Thanks.
```

Original comment by `zieg...@amaratech.com` on 6 Feb 2012 at 10:44

Attachments:
- [JSONValidator.java](https://storage.googleapis.com/google-code-attachments/google-gson/issue-17/comment-14/JSONValidator.java)
- [JSONValidationException.java](https://storage.googleapis.com/google-code-attachments/google-gson/issue-17/comment-14/JSONValidationException.java)


## GoogleCodeExporter (19 Mar 2015)

```
FYI, you have a bug on line 118. It should be 
ANY.getTypeString().equals(type)), not ANY.equals(type) -- in the latter case, 
you're using equals() to compare an Enum<Type> and a String.
```

Original comment by `tedpenni...@gmail.com` on 7 Feb 2012 at 2:48


## GoogleCodeExporter (19 Mar 2015)

```
Thanks for pointing that out.

FYI, I use JSON for my web APIs.  I use the schema as an easy way of validating 
input (and output) data.  Since I use the schema file for validation in my 
code, I can publish it and provides a definitive documentation of the API.  For 
this, I find it really useful.
```

Original comment by `zieg...@amaratech.com` on 10 Feb 2012 at 11:50


## GoogleCodeExporter (19 Mar 2015)

```
I don't think we're gonna get to this.
```

Original comment by `limpbizkit` on 16 Apr 2012 at 10:47
- Changed state: **WontFix**


