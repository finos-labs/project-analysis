#Predicates.forFunction(Function<T, Boolean>)

Owner: google

Repo: guava

Labels: type=enhancement status=will-not-fix 

## gissuebot (31 Oct 2014)

_[Original issue](https://code.google.com/p/guava-libraries/issues/detail?id=19) created by **jonhnnyweslley** on 2007-10-23 at 07:06 PM_

---

&nbsp;&nbsp;/**
&nbsp;&nbsp;&nbsp;\* Returns a predicate that evaluates to the same result as the
&nbsp;&nbsp;&nbsp;\* given function.
&nbsp;&nbsp;&nbsp;*/
&nbsp;&nbsp;public static &lt;T> Predicate&lt;T> forFunction(
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;final Function&lt;T, Boolean> predicate) {
&nbsp;&nbsp;&nbsp;&nbsp;checkNotNull(predicate);
&nbsp;&nbsp;&nbsp;&nbsp;return new FunctionPredicate&lt;T>(predicate);
&nbsp;&nbsp;}

&nbsp;&nbsp;/*\* @﻿see Predicates#forFunction(Function) */
&nbsp;&nbsp;private static class FunctionPredicate&lt;T>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;implements Predicate&lt;T>, Serializable {
&nbsp;&nbsp;&nbsp;&nbsp;private final Function&lt;T, Boolean> function;

```
private FunctionPredicate(final Function<T, Boolean> function) {
  this.function = function;
}

public boolean apply(final T t) {
  return Boolean.TRUE.equals(function.apply(t));
}

private static final long serialVersionUID = -4940925077935486606L;
```

&nbsp;&nbsp;}

Usage examples:
&nbsp;&nbsp;private static final Predicate&lt;Object> ALWAYS_TRUE =
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;forFunction(Functions.constant(Boolean.TRUE));

&nbsp;&nbsp;private static final Predicate&lt;Object> ALWAYS_FALSE =
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;forFunction(Functions.constant(Boolean.FALSE));


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=19#c1) posted by **kevinb9n** on 2007-10-23 at 07:15 PM_

---

It does seem like a gap that we don't have that.

And do we not have a method that composes a Function and a Predicate into a
Predicate?  I'm surprised, I thought we had that.

---

**Labels:** -`Type-Defect`, `Type-Enhancement`


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=19#c2) posted by **kevinb9n** on 2008-05-27 at 06:29 PM_

---

_(No comment entered for this change.)_

---

**Labels:** `1.0`


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=19#c3) posted by **seantparsons** on 2009-03-12 at 10:24 AM_

---

I built an implementation that mirrors the Functions.forPredicate method:

```
public static <T> Predicate<T> forFunction(Function<T, Boolean> function) {
    return new FunctionPredicate<T>(function);
}

private static class FunctionPredicate<T> implements Predicate<T>, Serializable {
    private final Function<T, Boolean> function;

    private FunctionPredicate(Function<T, Boolean> function) {
        this.function = checkNotNull(function);
    }

    @Override
    public boolean apply(T t) {
        return function.apply(t);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FunctionPredicate) {
            FunctionPredicate<?> that = (FunctionPredicate<?>) obj;
            return function.equals(that.function);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return function.hashCode();
    }

    @Override
    public String toString() {
        return "forFunction(" + function + ")";
    }

    private static final long serialVersionUID = 0;
}
```


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=19#c4) posted by **kevinb9n** on 2009-03-18 at 02:20 AM_

---

_(No comment entered for this change.)_

---

**Labels:** -`1.0`


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=19#c5) posted by **kevinb9n** on 2009-09-17 at 06:02 PM_

---

_(No comment entered for this change.)_

---

**Labels:** `Milestone-Post1.0`


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=19#c6) posted by **kevinb@google.com** on 2010-04-23 at 08:16 PM_

---

Does anyone actually need this, and why?


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=19#c7) posted by **kevinb@google.com** on 2010-07-30 at 03:53 AM_

---

_(No comment entered for this change.)_

---

**Labels:** -`Milestone-Post1.0`


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=19#c8) posted by **kevinb@google.com** on 2010-07-30 at 03:56 AM_

---

_(No comment entered for this change.)_

---

**Labels:** -`Priority-Medium`


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=19#c9) posted by **ray.j.greenwell** on 2010-11-05 at 11:47 PM_

---

Predicates.compose(Predicates.equalTo(true), TtoBooleanFunction);

Voila. It's also guaranteed not to NPE like the impl in comment #﻿3 could if the Function returned null.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=19#c10) posted by **fry@google.com** on 2011-01-26 at 08:47 PM_

---

_(No comment entered for this change.)_

---

**Status:** `Accepted`


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=19#c11) posted by **g.e.dejong** on 2011-03-13 at 10:44 AM_

---

Why is Predicate&lt;T> not a subtype of Function&lt;T, Boolean>? Also, for the next big release, it would be more intuitive to rename Function to Functor.

In my own little Collections library (which is soon going to be replaced with Guava), I created a UnaryFunctor which applies to  the type of the argument and a BinaryFunctor which applies to a UnaryFunctor. Thus, it allows a sort of currying in Java.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=19#c12) posted by **neveue** on 2011-03-13 at 02:08 PM_

---

Predicate returns a "boolean" primitive type, not a Boolean. That's why it cannot subclass Function&lt;T, Boolean>.

You may then ask "why not have Predicate return a Boolean instead?". I see three reasons for this:
1) it would force everyone to handle null return values
2) it would force auto-boxing, which might be bad for performance
3) doing this would break backward compatibility

Finally, I think Predicate and Function are different notions, so it makes sense to have separate interfaces for them. I very rarely need to use a Predicate as a Function: I mainly use predicates when filtering, not when transforming.


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=19#c13) posted by **amertum** on 2011-03-13 at 08:04 PM_

---

see issue #﻿493 : Predicate&lt;T> should extend Function&lt;T, Boolean>

https://github.com/google/guava/issues/493


## gissuebot (31 Oct 2014)

_[Original comment](https://code.google.com/p/guava-libraries/issues/detail?id=19#c14) posted by **kevinb@google.com** on 2011-04-05 at 02:42 AM_

---

I don't think I ever saw an answer to "Does anyone actually need this, and why?"

---

**Status:** `WontFix`


