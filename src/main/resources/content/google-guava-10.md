#Trie interface(s) and implementation(s)

Owner: google

Repo: guava

Labels: type=addition package=collect status=research P4 

## gissuebot (31 Oct 2014)

_[Original issue](https://code.google.com/p/guava-libraries/issues/detail?id=10) created by **sberlin** on 2007-09-24 at 06:12 PM_

---

This is a contribution of LimeWire's PatriciaTrie, as discussed at: 
http://groups.google.com/group/google-
guice/browse_frm/thread/ffb2a3b3b9e39e79?tvc=1 .

The files can be licensed as necessary (we own the copyright and can 
change/transfer the license).  I'm not sure what license, if any, these 
would need to be for inclusion.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=10#c38) posted by **kevinb@google.com** on 2012-05-30 at 07:43 PM_

---

_(No comment entered for this change.)_

---

**Labels:** -`Type-Enhancement`, `Type-Addition`


## gissuebot (01 Nov 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=10#c39) posted by **Sam.Halliday** on 2012-08-01 at 07:30 PM_

---

+1 for Trie, I find myself wanting a solid Java implementation on occasion, and usually fall back to a TreeMap&lt;String> hack which gets the job done (albeit with a much larger memory footprint).


## gissuebot (01 Nov 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=10#c40) posted by **onlynone** on 2012-08-22 at 02:28 PM_

---

I'd be very interested in a Trie implementation in guava. I use hadoop and need to filter the incoming data by a large set of strings, on the order of 2 million strings. Right now I have a file containing these strings, one per line, that each mapper reads into a hashset to do the filtering. The speed performance is fine, it just takes a ton of memory. I've used the exact same approach with the exact same set of strings in c++ and it never took close to the amount of memory that java is using. The strings I'm filtering by have a good deal of common leading characters, I think our memory savings would be significant with a trie. I'd like to see a TrieMap and a TrieSet.


## gissuebot (01 Nov 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=10#c41) posted by **phraktle** on 2012-11-15 at 11:53 PM_

---

An interesting project for concurrent tries: http://code.google.com/p/concurrent-trees/


## gissuebot (01 Nov 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=10#c42) posted by **Ja...@wetheinter.net** on 2013-01-24 at 01:32 PM_

---

I am presently finding Trie structures very useful in my code generation / dependency injection library.  Scanning classpaths is expensive, and java packages often share long, repetitive package names; couple that with the fact my codegen library descends not just through the package structure, but the class, method, field and annotation structure as well (into a single uber-trie of your module), and a fast, concurrent trie is absolutely vital.  

Compared to the org.reflections classpath scanner (which uses MultiMap and single threaded scanning), my Trie-backed implementation can, if scanning multiple jars with multiple threads, run over seven times faster, and deliver deep iterators, instead of multiple MultiMaps delivering set views.

+1 For Trie


## gissuebot (01 Nov 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=10#c43) posted by **Ja...@wetheinter.net** on 2013-01-24 at 02:22 PM_

---

Sorry for the double post, but one more place I use Trie's for great effect: My StringTrie which uses char[] or CharSequence as keys, internally uses a CharPool that extends the base Trie structure, and translates a char[], start index and end index into singleton char[] instances (which pass == tests).  This changed my memory footprint from double that of TreeMap to less than half (on larger datasets).  

My implementation does not store a node per character; rather, it will store a char[] matching a unique sequence of chars, and using a trie-backed CharPool, it will only create as many arrays as there are unique sequences in your data (and using a shared CharPool shared across instances ensures I never allocate a byte more memory than I need).

In my benchmarks, I found that memory usage on highly-variant, dense data structures (like long sequences of toStringd() random numbers) was quite high (a new char[] per node), but with a CharPool to the rescue, memory usage was based on number of unique sequences + number of unique sequence combinations, instead of unique sequence combinations \* number of sequences per combination.


## gissuebot (01 Nov 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=10#c44) posted by **knut.wannheden** on 2013-01-24 at 02:40 PM_

---

Hi James,

Any chance that you'll share your implementation? I also have a use case where I'm currently using a TreeMap (which allows for a poor man's prefix matching) and I would like to experiment with a Trie to save some memory.


## gissuebot (01 Nov 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=10#c45) posted by **Ja...@wetheinter.net** on 2013-01-24 at 04:59 PM_

---

I'll package it up, put it on github in the near future, and reply back here with a link.  Star the issue, and you'll get emailed.

The concurrency support is tied directly into my own internal library (which does let you inject your own implementations for handling threads or ignoring multithreading), but the whole library itself is Not Safe For Work (tm).

If I can't deploy a new version soon, I can put a cut out standalone of the trie and the charpool in a gist instead; just be warned that for small maps, you won't be saving space.  Large maps, especially ones with common prefixes (or subsequences, using a shared CharPool) WILL save you on memory.

For my purposes, I save lots, because I am working with long, repetitive strings (especially when descending into inner classes, methods, fields, vars, etc).  If you just maintain a big map of mostly-unique strings, trie is probably not for you. 


## gissuebot (01 Nov 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=10#c47) posted by **oliver.schrenk** on 2013-03-15 at 12:04 PM_

---

Hi James,

Did you have time to put your trie implementation up on github? I'm interested because I have a large pool of keys sharing very long prefixes and I'm interested in your approach.


## gissuebot (01 Nov 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=10#c48) posted by **ashwin.jayaprakash** on 2013-03-15 at 05:01 PM_

---

Jetty util pkg has some Trie structures if it helps - http://git.eclipse.org/c/jetty/org.eclipse.jetty.project.git/tree/jetty-util/src/main/java/org/eclipse/jetty/util/ArrayTernaryTrie.java


## noss (04 Nov 2014)

There was a request for use cases. 

I started to use the Trie in apache commons4 collections to use in lookups of longest matching prefix. Its not uncommon with rules based on phone number prefixes.

Next is to do the same but for IP networks, almost like a routing table. Apache commons4 4.0 doesnt allow me to create a patricia tree on other types though. But I guess limewire is almost doing this, but refactoring has made the codebases diverge.

The lookup tables will have around ~100k entries, and there about 100k lookups per second.


## kluever (10 Apr 2015)

We're not working on this anytime soon...and it will require a lot more investigation and studying.


## yogurtearl (31 Jan 2017)

@kluever how about in 2017? :) 

## bobtiernay-okta (14 Aug 2018)

Maybe 2019 is the ticket? :P

## idolizesc (23 Jan 2020)

2020?

## quickiwiki (12 Mar 2021)

2021? Still no movement?

## debashisdeb (09 Feb 2022)

2022? #weNeedTrie 😄 

## evanyang1 (10 Feb 2022)

Given that this issue was opened in 2014, what would it take for this issue to be resolved + closed?

## udit-29 (03 May 2023)

2023?

## kevinb9n (03 May 2023)

I'm emotionally attached to the idea that Guava could one day have this, but I guess it's time to face facts: we just aren't in an active feature mode anymore (even, for something of this scale, if it's externally-driven).

So, I'll probably close this issue in 2033. Just kidding.

