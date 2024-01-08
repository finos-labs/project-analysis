#UniqueList<E>

Owner: google

Repo: guava

Labels: type=addition package=collect status=research P4 

## gissuebot (31 Oct 2014)

_[Original issue](https://code.google.com/p/guava-libraries/issues/detail?id=13) created by **kevinb9n** on 2007-10-23 at 04:14 AM_

---

A UniqueList is a List which rejects duplicate elements (recall that the
modification methods on List, add/add/addAll/addAll/set, are permitted
throw IllegalArgumentException if there's anything they don't like about
the offered element(s)). The same goes for the modification methods on the
list's listIterator().

The nice thing about this restriction is that a UniqueList can be viewed as
a Set in a completely "read-through, write-through" fashion.  So the
UniqueList&lt;E> interface would extend List&lt;E> to add this asSet() method. I
don't believe that any subtype of Set is needed for this; AFAIK it just
needs regular Set methods and not much else.

As well, the subList() method could be refined so that it also returns a
UniqueList&lt;E>. Of course, the sublist would throw IAE in response to any
operation that would result in a duplicate element in the _parent_ list.

An AbstractUniqueList&lt;E> class could be provided which only needs the
implementing class to supply a backing List and a backing Set, and
optionally override a few methods for better performance.

Unfortunately, the Collections methods sort(), shuffle(), reverse() and
swap() would fail on a UniqueList. They all make the assumption that the
list being acted upon has no problem with temporarily containing the same
element twice.  There's nothing we can do about that -- it's the price you pay.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=13#c12) posted by **Koyote130708** on 2010-09-13 at 04:18 AM_

---

"There's nothing we can do about that -- it's the price you pay."

Not really.. If the list has elements: A, B, C, D, E and you want to set the element at index 0 to E then you can simply swap their positions.

Result: E, B, C, D, A

And this doesn't break the contract of the Set operation.

"Replaces the element at the specified position in this list with the specified element (optional operation). "

All you have to do is to add more specifications to the set method.

It's a pity that Josh couldn't foresee this problem.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=13#c13) posted by **kevinb@google.com** on 2011-01-27 at 01:54 PM_

---

_(No comment entered for this change.)_

---

**Owner:** kev...@google.com


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=13#c14) posted by **kevinb@google.com** on 2011-01-27 at 01:58 PM_

---

_(No comment entered for this change.)_

---

**Owner:** ---


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=13#c15) posted by **kevinb@google.com** on 2011-02-03 at 06:35 AM_

---

Koyote, I don't understand.  I'm saying there's nothing WE can do to make Collections.sort(uniqueList) work.  You're claiming there is?


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=13#c17) posted by **Koyote130708** on 2011-02-10 at 08:59 AM_

---

Yes if you follow the way I described above most of the Collections methods will work on the list as expected. (the shuffle method doesn't seem to work)

Here is a simple implementation of the UniqueList

import com.google.common.base.Preconditions;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
&nbsp;*
&nbsp;\* @﻿author Michael
&nbsp;*/
public class SimpleUniqueList&lt;E> extends AbstractList&lt;E> {

```
private List<E> data;

public SimpleUniqueList() {
    data = new ArrayList<E>();
}

@Override
public void add(int index, E e) {
    Preconditions.checkArgument(!data.contains(e));
    data.add(index, e);
}

@Override
public E remove(int index) {
    return data.remove(index);
}

@Override
public E set(int index, E e) {
    int i = data.indexOf(e);
    if (i == -1) {
        return data.set(index, e);
    }
    data.set(i, data.get(index));
    return data.set(index, e);
}

@Override
public E get(int index) {
    return data.get(index);
}

@Override
public int size() {
    return data.size();
}

public static void main(String[] args) {
    List<String> ul = new SimpleUniqueList<String>();
    ul.add("A");
    ul.add("B");
    ul.add("C");
    ul.add("D");
    ul.add("E");
    System.out.println(ul);
    Collections.shuffle(ul); // doesn't work
    Collections.sort(ul);
    System.out.println(ul);
    Collections.reverse(ul);
    System.out.println(ul);
    Collections.swap(ul, 0, 4);
    System.out.println(ul);
}
```

}

Running the above program will print out the following output:

[A, B, C, D, E]
[A, B, C, D, E]
[E, D, C, B, A]
[A, D, C, B, E]

It would have been good if Josh put a method for swapping two elements in the List interface. Something like swap(int i, int j)


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=13#c18) posted by **neveue** on 2011-02-10 at 01:09 PM_

---

Koyote13, I think the set() method you propose would be confusing... People would not expect elements to switch positions when they attempt to set an already stored element! For example:

```
@Test
public void test() {
    List<String> ul = new SimpleUniqueList<String>();
    ul.add("D");
    ul.add("C");
    ul.add("B");
    ul.add("A");

    ul.set(0, "A");

    // "D" was switched to the 4th position instead of being replaced by "A" !?
    Assert.assertEquals(ul, ImmutableList.of("A", "C", "B", "D"));
}
```

I think the UniqueList should throw an IllegalArgumentException when attempting to "set" an already stored element. This would stop sort() / reverse() / swap() from working, but it is IMO the only contract that makes sense for the set() method.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=13#c19) posted by **kevinb@google.com** on 2011-02-10 at 02:48 PM_

---

... yep, and that's what it does.  The idea of corrupting other data in the list at will is not even something that should be considered.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=13#c20) posted by **Koyote130708** on 2011-02-12 at 06:15 AM_

---

Yes indeed, it's quite confusing and the fact that the shuffle method doesn't work on the list is quite disappointing.

kevinb I'm not sure if that's "corrupting" other data.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=13#c21) posted by **kevinb@google.com** on 2011-07-13 at 06:18 PM_

---

_(No comment entered for this change.)_

---

**Status:** `Triaged`


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=13#c22) posted by **raymond.rishty** on 2011-11-03 at 12:55 AM_

---

I have a UniqueList implementation that I gives the user the option of how duplicates ought to be handled--ignore, replace, or throw an exception. One obtains a list by something like UniqueList.ignoreDuplicates() or UniqueList.replaceDuplicates(), etc.

That said, more often than not, when someone thinks they want a UniqueList, and LinkedHashSet does just fine. Moreover, given the option, most developers I work with will pick the most tolerant implementation. They don't want non-conforming data to be rejected.

That is to say, to Kevin's point (from 2/10) I'm not certain that my approach is worthwhile. Per the API, List.add is allowed to reject the addition (by throwing an Exception), but for it to remove another entry, or silently ignore the addition is certainly breaking the contract and probably a bit perverse.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=13#c23) posted by **fry@google.com** on 2011-12-10 at 03:12 PM_

---

_(No comment entered for this change.)_

---

**Labels:** `Package-Collect`


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=13#c24) posted by **fry@google.com** on 2012-02-16 at 07:17 PM_

---

_(No comment entered for this change.)_

---

**Status:** `Acknowledged`


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=13#c25) posted by **kevinb@google.com** on 2012-05-30 at 06:07 PM_

---

Raymond: first, "ignore" and "replace" are the same thing. If they ever aren't, that object implemented equals() incorrectly.  Equals Means Interchangeable.

Second, our UniqueList also gives you the choice of either exception or silently-collapse.  Just use either uniqueList.add(e) or uniqueList.asSet().add(e), respectively.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=13#c26) posted by **wasserman.louis** on 2012-05-30 at 07:18 PM_

---

Honestly, I'm inclined to play up the role of ImmutableSet.asList() here, since it satisfies a sensible "unique list" contract, has unambiguous semantics (since it is, of course, immutable), and all the other fun stuff.

Do we have any actual use cases that this doesn't address?


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=13#c27) posted by **kevinb@google.com** on 2012-05-30 at 07:43 PM_

---

_(No comment entered for this change.)_

---

**Labels:** -`Type-Enhancement`, `Type-Addition`


## gissuebot (01 Nov 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=13#c28) posted by **kevinb@google.com** on 2012-06-22 at 06:16 PM_

---

_(No comment entered for this change.)_

---

**Status:** `Research`


## gissuebot (01 Nov 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=13#c31) posted by **linakster** on 2014-01-10 at 09:13 PM_

---

Sorry for the bump. Did this get resolved?

If anything, a unique list should always be an ImmutableList. The user has no knowledge of the contents of the list, so even throwing an exception or returning false is an ideal hair-pulling scenario to debug (in which case it could be an interface not extending Collection to clearly define it's own behavior)

Using static factory for ImmutableList:

ImmutableList&lt;T> ImmutableList.uniqueListOf(Iterable<? extends T> elements,
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Equivalence<? super T> elemEq) {

```
  return  new DefaultUniqueListImpl<>(elements, elemEq).asList();        
```

}

Using a dedicated interface just for UniqueList (like java.util.Map), as it preserves the "Equivalence" instance used for computing equality, . This cannot extend Collection, as it violates the "contains(Object)" contract in using Object.equals; instead uses the provided "Equivalence" :

interface UniqueList&lt;T> {

```
Equivalence<? super T> elementEquivalence();

ImmutableList<T> asList();

int size();

// ....collection + list/random access methods....

// can freely throw exceptions as this interface is concrete and does not extend
// Collection, hence not compelled to adhere to contract


void shuffle();
void sort();
void swap(int i, int j);
// ... etc, all those methods that can trip
```

}

Will also be nice to have simple default operation in Collections2, for quick checks. Something like:

boolean Collections2.containsUniqueElements(Collection<?> c) 
{
&nbsp;&nbsp;&nbsp;if (list instanceof Set) {
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return true;
&nbsp;&nbsp;&nbsp;}

&nbsp;&nbsp;&nbsp;if (c instanceof Multiset) {
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Multiset<?> m = (Multiset<?>) c;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;for (E e : m) {
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if (m.count(e) > 1) {
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return false;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;} 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return true;
&nbsp;&nbsp;&nbsp;}

&nbsp;&nbsp;&nbsp;return containsUniqueElements(c, Equivalence.objectEquals());

}

boolean Collections2.containsUniqueElements(Collection&lt;T> c, 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Equivalence<? super T> elemEq) {

```
  return c.size() == ImmutableList.<T>uniqueListOf(c, elemEq).size();

  // or
  return c.size() == new FastComputingSizeUniqueListImpl<>(c, elemEq).size();
```

}

Comments and/or critiques?


## greg-dennis (09 Jan 2017)

I needed a kind of "unique list" as well and struggled to implement something I was happy with. As pointed out above, a UniqueList breaks Collection utility methods like sort and shuffle. But I also realized that I really didn't need to use indices in these collections much at all. Because their are not duplicates, I could refer to the position I wanted in the list by the element itself.

So with some hesitation, I ultimately gave up on it being a "List" at all and created a new type of Collection. This collection doesn't present the user with index-based access. My collection has methods getFirst() and getLast(), but then methods like getBefore(e) and getAfter(e), to return the elements before and after the specified element. It also has addBefore, addAfter, removeBefore, removeAfter, all of which accept element arguments.

I struggled to name the new kind of Collection. I initially wanted to use "Ordering", but Guava already has something else with that name. I settled on "Chain" for now. I'm open to other name suggestions.

For my primary implementation of the interface, I effectively re-implemented a LinkedList but with the addition of a HashMap mapping elements to its bucket in the list. So I get constant-time contains, insert, and remove. One of the benefitsI get by _not_ implementing List is that I don't worry users might use the slower index-based operations that come from the linked list implementation.

## will-molloy (18 Sept 2019)

Surely you can at least add an ```ImmutableUniqueList```.
It wont have the problem with ```set```,  ```sort```, ```shuffle``` etc. (since immutable)
Or at least provide a ```distinct()``` method to ```ImmutableList.Builder``` such that ```build()``` fails if there are duplicates.

## kevinb9n (03 May 2023)

Well... I think 15 years have proven sufficiently to us that need for this type isn't widespread. This isn't to belittle your use cases; I also *like* UniqueList for them (if it had already existed). But for the most part (by far) people have "enough" collection types that are adaptable "enough" to their needs, and introducing another has costs.

Our internal UniqueList implementation is imported six times in the Google codebase. That divided by the size of the codebase can be effectively estimated as zero.

