#BloomFilter<E>

Owner: google

Repo: guava

Labels: status=fixed type=enhancement package=hash 

## gissuebot (31 Oct 2014)

_[Original issue](https://code.google.com/p/guava-libraries/issues/detail?id=12) created by **kevinb9n** on 2007-10-23 at 04:02 AM_

---

A BloomFilter is a useful data structure.

&nbsp;&nbsp;http://en.wikipedia.org/wiki/Bloom_filter

Proposed straw-man API follows.  It shares only four methods in common with
Collection, and adds two of its own.

/**
&nbsp;\* A probabilistic "shadow" of a set of elements, useful
&nbsp;\* when the set itself would be too expensive to maintain in
&nbsp;\* memory and query directly. A Bloom filter can give false
&nbsp;\* positivites, but never false negatives. That is, adding
&nbsp;\* an element to the filter guarantees that {@﻿link
&nbsp;\* #mightContain} will return {@﻿code true}, but {@﻿link
&nbsp;\* #mightContain} returning {@﻿code true} does not guarantee
&nbsp;\* that this element was ever actually added to the filter.
&nbsp;_/
public final class BloomFilter&lt;E> implements Serializable {
&nbsp;&nbsp;/_*
&nbsp;&nbsp;&nbsp;\* Returns {@﻿code true} if it is <i>possible</i>
&nbsp;&nbsp;&nbsp;\* (probability nonzero) that {@﻿code element} is contained
&nbsp;&nbsp;&nbsp;\* in the set represented by this Bloom filter.  If this
&nbsp;&nbsp;&nbsp;\* method returns {@﻿code false}, this element is
&nbsp;&nbsp;&nbsp;\* <i>definitely</i> not present. If it {@﻿code true}, the
&nbsp;&nbsp;&nbsp;\* probability that this element has <i>not</i> actually
&nbsp;&nbsp;&nbsp;\* been added is given by {@﻿link
&nbsp;&nbsp;&nbsp;\* #getFalsePositiveProbability()}.
&nbsp;&nbsp;&nbsp;*/
&nbsp;&nbsp;public boolean mightContain(@﻿Nullable E element) { ... }

&nbsp;&nbsp;/**
&nbsp;&nbsp;&nbsp;\* Returns the probability that {@﻿link #mightContain} will
&nbsp;&nbsp;&nbsp;\* return {@﻿code true} for an element not actually
&nbsp;&nbsp;&nbsp;\* contained in this set.
&nbsp;&nbsp;&nbsp;*/
&nbsp;&nbsp;public double getFalsePositiveProbability() { ... }

&nbsp;&nbsp;/**
&nbsp;&nbsp;&nbsp;\* Adds {@﻿code newElement} to this Bloom filter, so that
&nbsp;&nbsp;&nbsp;\* {@﻿code mightContain(newElement)} is now guaranteed to
&nbsp;&nbsp;&nbsp;\* return {@﻿code true}.
&nbsp;&nbsp;&nbsp;*
&nbsp;&nbsp;&nbsp;\* @﻿return true if the Bloom filter changed as a result of
&nbsp;&nbsp;&nbsp;\* this call
&nbsp;&nbsp;&nbsp;*/
&nbsp;&nbsp;public boolean add(@﻿Nullable E newElement) { ... }

&nbsp;&nbsp;// self-explanatory methods:
&nbsp;&nbsp;public boolean addAll(Iterable<? extends E> elements) { ... }
&nbsp;&nbsp;public boolean isEmpty() { ... }
&nbsp;&nbsp;public void clear() { ... }
}


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=12#c8) posted by **reachbach** on 2010-08-11 at 11:50 AM_

---

Support for set operations (determining the union, intersection or difference without exposing bitsets) over bloom filters would be useful.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=12#c9) posted by **kevinb@google.com** on 2011-01-27 at 01:54 PM_

---

_(No comment entered for this change.)_

---

**Owner:** kev...@google.com


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=12#c10) posted by **kevinb@google.com** on 2011-01-27 at 01:59 PM_

---

_(No comment entered for this change.)_

---

**Owner:** ---


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=12#c11) posted by **jon.h.clark** on 2011-02-07 at 05:31 PM_

---

A very useful data structure indeed.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=12#c12) posted by **kevinb@google.com** on 2011-07-13 at 06:18 PM_

---

_(No comment entered for this change.)_

---

**Status:** `Triaged`


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=12#c13) posted by **kevinb@google.com** on 2011-07-16 at 08:37 PM_

---

_(No comment entered for this change.)_

---

**Status:** `Accepted`


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=12#c14) posted by **kevinb@google.com** on 2011-07-19 at 02:24 PM_

---

There has been a lot of progress on this and we may see it in release 12, to put a guess out there.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=12#c15) posted by **wasserman.louis** on 2011-12-06 at 04:09 PM_

---

Any chance we could get this into Git, if not exposed, so I can play with it?


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=12#c16) posted by **jim.andreou** on 2011-12-06 at 05:10 PM_

---

Since recently it became more or less feature complete (i.e. serialization), I wouldn't mind exposing it as-is in @﻿Beta. Kevin?


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=12#c17) posted by **jim.andreou** on 2011-12-07 at 02:01 AM_

---

Seems like there is no roadblock to making this public (@﻿Beta of course), along with the new hashing abstractions. Just a matter of someone actually doing it


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=12#c18) posted by **wasserman.louis** on 2011-12-07 at 02:37 AM_

---

I get the impression folks are really busy, though, so I'd settle for open-sourcing it without exposing the API yet.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=12#c19) posted by **fry@google.com** on 2011-12-10 at 03:12 PM_

---

_(No comment entered for this change.)_

---

**Labels:** `Package-Collect`


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=12#c20) posted by **wasserman.louis** on 2011-12-31 at 05:17 PM_

---

_(No comment entered for this change.)_

---

**Status:** `Fixed`
**Labels:** -`Package-Collect`, `Package-Hash`


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=12#c21) posted by **vayasya** on 2012-04-07 at 12:05 AM_

---

Didn't want to create a separate ticket for this question.

'put' has Key-Value association semantics by convention and 'add' has Set-element-addition semantics, (both in Java at least).

I am wondering why the BloomFilter implementation uses 'put' instead of 'add' even though the original API draft at the top of this page says 'add' a possible 'addAll'.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=12#c22) posted by **seharris** on 2012-06-14 at 02:37 PM_

---

The proposed addAll(BloomFilter&lt;T>) method (alternately called "union") is essential for my needs, which involve populating several compatible filters on separate computers and then combining the (deserialized) filters back into one. At present, there's no way build up one filter from one or more other filters (despite the copy() method being provided).


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=12#c23) posted by **wasserman.louis** on 2012-06-14 at 02:39 PM_

---

It's not clear to me that such a method could be defined, depending on the definition of "compatible" filters.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=12#c24) posted by **seharris** on 2012-06-14 at 04:53 PM_

---

Comment #﻿5 above touches on this challenge of deducing that two filters are compatible. I suspect that you won't buy this stance, but I'd be happy enough with trusting the caller. If the caller doesn't understand well enough the invalid consequences of pouring one bag of bits into another, there's not much we can do for him. I'd rather see it become /possible/ to perform this operation than continue to ban it, just because it's vulnerable to misuse.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=12#c25) posted by **wasserman.louis** on 2012-06-14 at 04:58 PM_

---

Hmmm.  How would you feel about throwing an exception if the target filter wasn't initialized with the same parameters?


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=12#c26) posted by **seharris** on 2012-06-14 at 05:44 PM_

---

Throwing an exception (IllegalArgumentException) is a reasonable response to trying to merge in a filter constructed with different parameters. The addAll (or merge() or union()) method would then involve first verifying equivalent configuration of the incoming filter and, upon confirming compatibility, ORin in its underlying bit vector, right?


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=12#c27) posted by **wasserman.louis** on 2012-06-14 at 05:46 PM_

---

Yep, that's what I'd expect.


