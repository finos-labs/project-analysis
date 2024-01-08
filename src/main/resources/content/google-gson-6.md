#Not serializing arrays of string

Owner: google

Repo: gson

Labels: Type-Defect Priority-Medium auto-migrated 

## GoogleCodeExporter (19 Mar 2015)

```
What steps will reproduce the problem?

Try to serialize and array of String

What is the expected output? What do you see instead?

["hello", "world"]
[{},{}]

What version of the product are you using? On what operating system?

Current.  Ubuntu ;)

Please provide any additional information below.

it isn't serializing my array list of strings as it once was, I just get a
list of empty dicts of the same size.

For example the output of :

import com.google.gson.Gson;
import java.util.List;
import java.util.ArrayList;

public class GsonExample {

  public static void main(String args[]) {
    List<String> vals = new ArrayList<String>();
    vals.add("Hello");
    vals.add("World");
    Gson gson = new Gson();
    System.out.println(gson.toJson(vals));
    System.out.println(gson.toJson(vals, List.class));
  }
}

Is :

[{},{}]
[{},{}]

At line 127 of JsonSerializationVisitor the fieldValue of my object is
"ade" for instance and the fieldType is "class java.lang.Object"

At line 131, the childVisitor.getJsonElement() line always returns an empty
dict {}

Regards,

Cameron
```

Original issue reported on code.google.com by `camerong...@gmail.com` on 7 May 2008 at 6:21


## GoogleCodeExporter (19 Mar 2015)

```
added a test, and reproduced the problem. Thanks for the detailed bug report. 
```

Original comment by `inder123` on 8 May 2008 at 3:52
- Changed state: **Accepted**


## GoogleCodeExporter (19 Mar 2015)

```
Fixed in r36. See
http://groups.google.com/group/google-gson-codereviews/browse_thread/thread/f6a1
92c317003180

Added new serialization and deserialization tests for array and collection of 
Strings. 
```

Original comment by `inder123` on 8 May 2008 at 4:10
- Changed state: **Fixed**


