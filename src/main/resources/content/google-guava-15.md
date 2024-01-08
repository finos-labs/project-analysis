#Lists.charactersOf(CharSequence), returning List<Character>

Owner: google

Repo: guava

Labels: status=fixed type=enhancement 

## gissuebot (31 Oct 2014)

_[Original issue](https://code.google.com/p/guava-libraries/issues/detail?id=15) created by **kevinb9n** on 2007-10-23 at 04:31 AM_

---

This is easy to implement (though requires some tricks that are not totally
obvious in order to get it just right) and I'm pretty sure it would come in
handy from time to time.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=15#c1) posted by **gonzaloaune** on 2007-11-19 at 03:37 PM_

---

You want to get a list of characters specified in the CharSequence of a certain List
right?.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=15#c2) posted by **gonzaloaune** on 2007-11-19 at 03:39 PM_

---

Like... List.charactersOf(List&lt;String>, charSequence) ?


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=15#c3) posted by **kevinb9n** on 2007-11-19 at 06:06 PM_

---

No, just view a CharSequence as a List, for example Lists.charactersof("abc") returns
['a', 'b', 'c'].


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=15#c4) posted by **gonzaloaune** on 2007-11-19 at 07:15 PM_

---

You can use something like that (sorry for my english) =), im just trying to help.

Regards!

&nbsp;&nbsp;/**
&nbsp;&nbsp;&nbsp;\* Returns an {@﻿code List} instance given the charSequence.
&nbsp;&nbsp;&nbsp;\* 
&nbsp;&nbsp;&nbsp;\* @﻿param charSequence the elements that the list should contain, in order.
&nbsp;&nbsp;&nbsp;\* @﻿return an {@﻿code List} instance containing those elements.
&nbsp;&nbsp;&nbsp;\* */
&nbsp;&nbsp;public static List&lt;CharSequence> charactersOf(CharSequence charSequence) {
&nbsp;&nbsp;&nbsp;&nbsp;checkNotNull(charSequence);
&nbsp;&nbsp;&nbsp;&nbsp;List&lt;CharSequence> charsList = newArrayList();

```
for (int i = 0; i < charSequence.length(); i++) {
  charsList.add(charSequence.subSequence(i, i+1));
}

return charsList;
```

&nbsp;&nbsp;}


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=15#c5) posted by **gonzaloaune** on 2007-11-20 at 02:20 PM_

---

Also, we could add this validation to avoid the creation of the new list.

if (charSequence.length() == 0) {
&nbsp;&nbsp;return null; //Or whatever you want... (emptyList?)
}

Regards!


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=15#c6) posted by **mohammady.mahdy** on 2009-05-04 at 07:57 AM_

---

I implemented this as a view of the character sequence in the attached patch, I'd
really appreciate any review and comment on it even if its not included as this is
one of my very first open source contributions.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=15#c7) posted by **creswick** on 2009-05-04 at 05:47 PM_

---

I took a quick look over charctersOf.txt, and have a few questions/suggestions.  I
don't have the experience to comment on the overall structure/approach, but hopefully
these comments are helpful:

&nbsp;&nbsp;&nbsp;\* There is no guarantee that a given CharSequence is immutable, and it looks like
subSequence(int, int) returns a copy, rather than a view, so the implementation of
CharSequenceListView.subList(int,int) may not maintain the contract specified in the
List api for subList(int,int).  (This can be seen by using subSequence and delete on
a StringBuffer.)

&nbsp;&nbsp;&nbsp;\* Is it necessary to redefine add/addAll/remove/set given that
CharSequenceListView extends ImmutableCollection&lt;Character>?

&nbsp;&nbsp;&nbsp;\* Should the overridden methods all have @﻿Overrides annotations? (get/add/etc.)

&nbsp;&nbsp;&nbsp;\* I believe all the fields could be final.

&nbsp;&nbsp;&nbsp;\* @﻿NonNull annotation on parameter of charactersOf(CharSequence)?

&nbsp;&nbsp;&nbsp;\* javadoc on charactersOf(CharSequence)

&nbsp;&nbsp;&nbsp;\* Would it make sense to have the ListIterator extend
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;UnmodifiableIterator? (reusing the remove() method defined
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;therin).  It seems like what you want is an
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;UnmodifiableListIterator, which doesn't seem to exist yet.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=15#c8) posted by **mohammady.mahdy** on 2009-05-05 at 06:36 AM_

---

thanks for taking time to read the code and comment :-), ok here goes:
1-you are absolutely right, i will rewrite the sublist function to view the same list
probably with start and end fields limiting the operations and altering the behavior
of the iterators.

2-yes in case i am extending ImmutableCollection, I should've extended ImmutableList
though.

3-you are absolutely right I should do that.

4-you are right.

5-sure I just wrote the code as a draft to get feedback about the approach.

6-ya I looked for an UnmodifiableListIterator but couldn't find any and found myself
asking the same question and decided to do it the direct way and just change it if
there was a good reason.

I also figured out a better way to do the iterators (make them fail by saving the
initial size and checking the current size each time instead of iterate on a copy,
thanks for pointing out that subsequence returns a copy not a view, this won't detect
any set kind of operations though -- any ideas ?).


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=15#c9) posted by **mohammady.mahdy** on 2009-05-05 at 11:18 AM_

---

if i actually viewed the list with start and end fields how would i know if the
sequence has changed and that these fields should be updated?


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=15#c10) posted by **kevinb9n** on 2009-09-17 at 06:02 PM_

---

_(No comment entered for this change.)_

---

**Labels:** `Milestone-Post1.0`


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=15#c11) posted by **finnw1** on 2010-02-01 at 03:03 PM_

---

Wouldn't com.google.common.primitives.Chars be a better place for this than
com.google.common.collect.Lists, e.g. an overload of Chars.asList()?


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=15#c12) posted by **kevinb@google.com** on 2010-07-30 at 03:53 AM_

---

_(No comment entered for this change.)_

---

**Labels:** -`Milestone-Post1.0`


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=15#c13) posted by **kevinb@google.com** on 2010-07-30 at 03:56 AM_

---

_(No comment entered for this change.)_

---

**Labels:** -`Priority-Medium`


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=15#c14) posted by **kevinb@google.com** on 2010-09-14 at 08:49 PM_

---

_(No comment entered for this change.)_

---

**Status:** `Fixed`
**Owner:** kev...@google.com
**Labels:** `Milestone-Release07`


