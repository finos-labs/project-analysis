#Maven repo

Owner: google

Repo: guava

Labels: type=defect status=fixed 

## gissuebot (31 Oct 2014)

_[Original issue](https://code.google.com/p/guava-libraries/issues/detail?id=1) created by **b.k.oxley** on 2009-09-16 at 04:03 PM_

---

Top issues for me -- when you begin releasing jar files, please provide
some kind of maven support for them.  Ideal would be to include the
binary/source/javadoc triplet jars in the maven central repo.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=1#c2) posted by **gpampara** on 2009-09-18 at 10:32 AM_

---

I'd have to agree :)


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=1#c3) posted by **kevinb9n** on 2009-09-19 at 04:25 PM_

---

We aren't even posting a binary yet, and I don't think we will until we complete the
process of finalizing the google collections code and merging it into this project.
Otherwise there's going to be too much confusion.

I'm sorry that we can't move faster... but what you have right now is basically just
a "teaser" -- we're showing you the code but it's not quite prime-time-ready yet.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=1#c4) posted by **johan...@familieschneider.info** on 2009-10-16 at 04:46 PM_

---

You could just release 0.*-SNAPSHOT versions.
Do you mind mavenizing the project? I am sure there are many people out there that
will help (including me).


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=1#c5) posted by **charlie.knudsen** on 2009-10-28 at 11:53 PM_

---

Until things are official folks can setup their own pom that uses the current directory 
structure.  I setup a basic pom that can be used at:
http://gist.github.com/220861

The group/artifact/version names will probably change when things are actually launched 
but this may help others get a jar in your local repo for now.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=1#c6) posted by **ian.clarke** on 2009-11-14 at 04:02 PM_

---

I would really appreciate it if you guys could provide a Maven repo for this since my 
company won't use any libraries in our code that don't have a repo (as it complicates 
the build and deployment process).


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=1#c7) posted by **francisdb** on 2009-11-14 at 08:03 PM_

---

I see this issue on all Google projects: guice2, guava, android, ... seems to me that
Google is not supporting maven at all...


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=1#c8) posted by **ian.clarke** on 2009-11-14 at 10:10 PM_

---

francisdb, at least they support it for Google Collections.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=1#c9) posted by **ted.dunning** on 2010-02-12 at 11:40 PM_

---

So now that collections is finalized and incorporated, is a binary jar in maven
forthcoming?


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=1#c10) posted by **b2prix21** on 2010-03-15 at 01:43 PM_

---

Collections seam to be in the central Maven repository, but I can't find guava there 
(the superset).


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=1#c11) posted by **ted.dunning** on 2010-03-15 at 11:20 PM_

---

See the pom that at http://gist.github.com/220861

I have successfully used a slight variant on that pom.

There is no word from google about when they plan to publish a maven artifact.  It
would almost be reasonable to fork the entire project just to accomplish that.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=1#c12) posted by **johan...@familieschneider.info** on 2010-04-12 at 06:59 PM_

---

Any hope that there will be any progress? Those basic libraries should really be
available within maven central...


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=1#c13) posted by **rooneyp** on 2010-04-17 at 12:46 PM_

---

Another vote for have all of guava available from the Maven repo's.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=1#c14) posted by **alex.muh** on 2010-04-21 at 10:24 AM_

---

And one more vote for maven support.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=1#c15) posted by **ted.dunning** on 2010-04-28 at 10:08 PM_

---

Looks like r03 is now in maven.

THis works for me:

```
    <dependency>
        <groupId>com.google.guava</groupId>
        <artifactId>guava</artifactId>
        <version>r03</version>
    </dependency>
```


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=1#c16) posted by **mosabua** on 2010-04-28 at 11:09 PM_

---

Awesome. The whole lot is there are http://repo2.maven.org/maven2/com/google/guava/


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=1#c17) posted by **gpampara** on 2010-04-29 at 07:18 AM_

---

Yeah. Seems like it's all good. A big thank you to all involved in getting the 
artifacts deployed :)


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=1#c18) posted by **conny.kreyssel** on 2010-05-17 at 10:13 AM_

---

And who starts the same procedure for r04?


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=1#c19) posted by **japgolly** on 2010-05-26 at 02:08 AM_

---

r04 is still missing from maven central.
Any news as to when it will be uploaded?


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=1#c20) posted by **mathias.bogaert** on 2010-05-26 at 06:00 PM_

---

Please r05 as well. :-)


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=1#c21) posted by **kevinb@google.com** on 2010-06-10 at 05:04 PM_

---

_(No comment entered for this change.)_

---

**Status:** `Fixed`


