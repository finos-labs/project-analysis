#Creating immutable sorted sets from already-ordered data

Owner: google

Repo: guava

Labels: status=fixed type=enhancement 

## gissuebot (31 Oct 2014)

_[Original issue](https://code.google.com/p/guava-libraries/issues/detail?id=14) created by **kevinb9n** on 2007-10-23 at 04:22 AM_

---

Occasionally you have a List of elements which you already know to be in
sorted order. The canonical example is the results from a SQL query with an
ORDER BY clause.

You know the data to be sorted, yet you have no way to offer the niceties
of the SortedSet/NavigableSet APIs to your callers. In order to construct a
TreeSet/etc., you must re-engineer the appropriate comparator that can be
used to sort the data -- but the data is already sorted!

I believe the way out of this is a method Sets.immutablePreSortedSet(List).
&nbsp;This method would copy the elements out of the list, assuming that
whatever order they come out in is the order you want.  It would not demand
a comparator from you (although, if you can provide one, perhaps it should
accept it, as this could speed up some of the operations.  and if you don't
provide one, what should the set's comparator() return?  Should it return a
Comparators.givenOrder()?).

This idea is not fully-formed, but it's a shame to see methods forced to
use List to model data which is often known to be dup-free and is ordered,
not indexed.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=14#c1) posted by **kevinb9n** on 2009-03-17 at 05:09 PM_

---

This idea needs a lot more thought.

Case 1: you can provide a comparator. In this case, you should provide it -- and just
create an ImmutableSortedSet.  Creating that will cause a sort() to happen which will
be a no-op, and that's not great but not terrible.  It's possible we could provide
another way to create an ISS where you declare your data is already increasing, thus
the factory only has to check that each element is higher than the one before it, and
doesn't have to call sort().  Meh.

Case 2: you can't provide a comparator. Some complicated database sort was done, for
instance. So your comparator becomes an Ordering.givenOrder() over the elements you
have. ImmutableSortedSet.inGivenOrder().addAll(list).build(). I kind of like this,
but I haven't heard a loud demand for it. (then again, many people are not loudly
demanding it but are still returning List from APIs that really should be Sets.)


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=14#c2) posted by **jared.l.levy** on 2009-08-13 at 02:06 PM_

---

There's a problem with Case 2. Without a comparator, you can't implement the 
comparator(), headSet(), tailSet(), and subSet() methods. 


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=14#c3) posted by **jim.andreou** on 2009-08-13 at 02:20 PM_

---

Could you elaborate on that a bit? E.g. why Ordering.givenOrder()/.explicit() wouldn't 
do?


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=14#c4) posted by **kevinb9n** on 2009-08-13 at 02:43 PM_

---

Yeah, those methods would only be able to accept values that are elements in the set,
so they'd be a bit crippled, but I'm not sure it's a deal-breaker.

This whole idea still lacks real motivation from users.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=14#c5) posted by **jim.andreou** on 2009-08-13 at 03:05 PM_

---

Hmm, alright. Though it wouldn't be any more "crippled" than Ordering.explicit() 
itself. And you already decided that it doesn't pay off enough to allow defining what 
happens with elements not contained in the list (with something similar to 
nullsFirst()/nullsLast()), with which I agree, so I don't see this as a big issue - but 
the real deal-breaker would be indeed lack of enough demand :)


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=14#c6) posted by **leonidos** on 2009-09-03 at 07:21 PM_

---

ImmutableSortedSet should guarantee that its items are ordered using the comparator() 
order, so it's a good idea to accept the client assumption (that items are already 
ordered). But, I think, an acceptable case when the order is checked at the 
construction phase - so ImmutableSortedSet.copyOfSorted(List&lt;E> list) can check the 
given list to be correct, and accept it if correct or throw an exception if not.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=14#c7) posted by **leonidos** on 2009-09-10 at 01:36 PM_

---

[erratum]
In the previous message - I meant "it's not a good idea accept the client assumption".


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=14#c8) posted by **kevinb9n** on 2009-09-17 at 06:02 PM_

---

_(No comment entered for this change.)_

---

**Labels:** `Milestone-Post1.0`


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=14#c9) posted by **jared.l.levy** on 2010-02-16 at 07:11 PM_

---

I just added ImmutableSortedSet.withExplicitOrder() methods to Google's internal code 
base. It will probably be in a future Guava release.

---

**Status:** `Fixed`
**Owner:** jared.l.levy


## lptr (29 Jan 2018)

The issue says it's fixed, but it doesn't appear to be a possibility in recent versions of Guava. It would be a really useful functionality. Is there a reason not to have this?

