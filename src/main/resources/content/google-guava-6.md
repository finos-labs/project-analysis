#Use javax.annotation.* consistently

Owner: google

Repo: guava

Labels: type=defect status=fixed 

## gissuebot (31 Oct 2014)

_[Original issue](https://code.google.com/p/guava-libraries/issues/detail?id=6) created by **montsean** on 2009-09-25 at 02:39 PM_

---

Any chance of making consistent use of javax.annotation.\* for clarifying
null safety of method params and return values?  FindBugs users would thank
you for it.

Using the concurrency annotations would help as well to document thread safety.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=6#c1) posted by **kevinb@google.com** on 2010-04-23 at 08:15 PM_

---

We are using findbugs now, and do our best to keep it happy.

---

**Status:** `Fixed`


