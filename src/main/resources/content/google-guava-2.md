#`Predicate` missing

Owner: google

Repo: guava

Labels: type=defect status=will-not-fix 

## gissuebot (31 Oct 2014)

_[Original issue](https://code.google.com/p/guava-libraries/issues/detail?id=2) created by **j...@nwsnet.de** on 2009-09-17 at 02:39 PM_

---

In `com.google.com.base`: The interface `Predicate`&nbsp;exists in Google
Collections, but is missing in Guava. However, it is at least used in Guava
by `CharMatcher`.

Is the idea to keep the interface in Collections for further development
and have Guava in a somewhat incomplete state until integration of the
former or was it just forgotten to copy it?

Regards,
Jochen Kupperschmidt


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=2#c1) posted by **kevinb9n** on 2009-09-18 at 04:57 PM_

---

As soon as we can finish the long-promised release 1.0 of the Google Collections 
Library, which I desperately hope will happen next month, then the entire contents of 
that project will be moved into this project, so that the arbitrary dividing lines 
will be erased.

---

**Status:** `WontFix`


