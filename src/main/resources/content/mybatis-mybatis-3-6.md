#Enum name collision when using typeAliases for packages in mybatis 3.2.0

Owner: mybatis

Repo: mybatis-3

Labels: bug 

## gdsowl (06 Mar 2013)

Following situation:

In package 'com.example.beans'
there are two classes:

``` java
package com.example.beans;

class A {
  public static enum State {
    ON,
    OFF
  }
}
```

``` java
package com.example.beans;

class B {
  public static enum State {
    FRESH,
    ROTTEN
  }
}
```

In the mybatis configuration xml file:

``` xml
<typeAliases>
  <package name="com.example.beans" />
</typeAliases>
```

This leads to following exception:

[..]
org.apache.ibatis.type.TypeException: The alias 'State' is already mapped to the value 'com.example.beans.A$State'.
    at org.apache.ibatis.type.TypeAliasRegistry.registerAlias(TypeAliasRegistry.java:146)
[..]

A solution for me (no idea what sideeffects this may cause) is to replace line 127 in TypeAliasRegistry.java

``` java
if (!type.isAnonymousClass() && !type.isInterface()) {
```

with

``` java
if (!type.isAnonymousClass() && !type.isInterface()  && !type.isEnum()) {
```


## emacarron (06 Mar 2013)

Hi.

I am afraid you will get the same result with inner classes:

``` java
package com.example.beans;

class A {
  public static class State {
  }
}

package com.example.beans;

class B {
  public static class State {
  }
}
```

Because the .getSimpleName() will also return "State".

To bypass them it would be better to ignore member classes

``` java
if (!type.isAnonymousClass() && !type.isInterface() && !type.isMemberClass()) {
  registerAlias(type);
}
```

But this is quite an edge case. It is not clearly wrong to search for all classes and I am afraid this change may fix someones problem but break others :)

So, in my opinion we should let this as it is and for these collissions use the @Alias annotation to assign different names to conflicting beans.

WDYT?


## paulkrause88 (07 Mar 2013)

Why not mention @Alias in the error message?  That would give users a head start on fixing the problem.


## gdsowl (07 Mar 2013)

Thumbs up for paulkrause88, @Alias does the job perfectly for me.

Genrally, I think the best way would be to make ignoring member classes an option, like this:

``` xml
<typeAliases>
  <package name="com.example.beans"  mapMemberClasses="false"/>
</typeAliases>
```

And perhaps a depth-parameter to configure recursive mapping of subpackages?
That way one could resemble the behavior of earlier mybatis by configuration:

``` xml
<typeAliases>
  <package name="com.example.beans"  mapMemberClasses="false" depth="0"/>
</typeAliases>
```


## emacarron (07 Mar 2013)

Note that a name collision can also happen when more than one @Alias specifies the same value so that exception message can be misleading.


## emacarron (10 Mar 2013)

There is more information about this issue. See:
https://groups.google.com/group/mybatis-user/browse_thread/thread/cb530f33d259f1d

Looks like you have the same error when scanning over a project that has used the generator because there is a collision with the inner class "GeneratedCriteria"

This is no longer an edge case :) We need a fix. So now I think I would go for skipping the member classes.

The idea of activating it by config is great but personally I would like to have as few config settings as possible. The more settings the more complex the tool is perceived and that is not the right way.


