#Can we please have a maven build file if possible?

Owner: oracle

Repo: graal

Labels: wontfix 

## patsimon (15 Feb 2016)

Would be nice and simple to use a standard build system instead of MX


## jtulach (22 Feb 2016)

Historically the Graal/Truffle project tries to be as IDE agnostic as possible. The same applies to build systems. However to achieve such independency the project invented its own build system called MX.

I fully understand that MX is a barrier to adoption to external people, but after nine month of close experience with the tool I can say that it is working well and is certainly faster than Maven. I don't expect there to be enough will to change the current status quo.

Btw. if you want a bit more standards based build system, you can get it by calling:

``` bash
$ mx ideinit
```

then there are going to be `truffle/com.oracle.*/build.xml` files which can be consumed by Ant:

``` bash
$ ant clean jar -f truffle/com.oracle.truffle.api/build.xml
```


## dougxc (22 Feb 2016)

We understand the desire for a standard build system. However, in addition to @jtulach explanation above, there is currently too much investment and dependence on mx by Truffle and all other graalvm projects to consider switching away from it at this point.

Note that if you're only wanting to use Truffle as an external dependency, you can already do so as shown by jruby:

https://github.com/jruby/jruby/blob/master/truffle/pom.xml#L41-L62

If you want to use snapshots you use:

https://github.com/jruby/jruby/blob/truffle-head/truffle/pom.xml#L70-L73


## patsimon (22 Feb 2016)

Thanks for the details explanation folks.


