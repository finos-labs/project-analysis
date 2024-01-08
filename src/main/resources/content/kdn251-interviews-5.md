#bitmasks

Owner: kdn251

Repo: interviews

Labels: 

## mmank (12 Mar 2017)

Toggle kth bit: s ^= ~(1 << k)
should be
Toggle kth bit: s ^= (1 << k)

## kdn251 (12 Mar 2017)

Have it fixed for my next commit, thank you for pointing this out!

## Triang3l (12 Mar 2017)

By the way, I'd also point out that compilers (not sure about the JVM though) seem to be much more likely to compile a `t = x; x = y; y = t;` sequence into a single exchange instruction without the temporary variable at all (or with it, if used with memory rather than local variables likely to be mostly register-only, but only with pure loads/stores), though I'm not sure if it's still true today (but seems like it is - https://gist.github.com/ericherman/9d1a646ad380b27d42b9ff8497e69f32), but either way it also wouldn't work with floating-point numbers and is just harder to read. I'd add an "avoid it" (in favor of `std::swap`) warning to it.

## kdn251 (12 Mar 2017)

I believe you're right. I'll be sure to change it to 3 separate statements. Thank you!

## Triang3l (13 Mar 2017)

I mean the whole idea is not recommended - and if asked in an interview, maybe it would be useful to mention its issues as well. But maybe not in case of JVM (though Android Runtime appears to be based on LLVM, so it's possible that common C++-ish optimizations are done by it).

