#HeroBuilder NullPointerException

Owner: iluwatar

Repo: java-design-patterns

Labels: 

## ItaloQualisoni (23 Mar 2015)

I was reading the code and  then I wondered why was used the NullPointerException in that case, won't be more readable throw a custom exception? I mean create something like BuilderException and then this can be treated in the caller method ...
*Line 97,98 from HeroBuilder.java for reference


## arosenf (23 Mar 2015)

I would also not throw a NPE programmatically. Tools like PMD recommend throwing an IllegalArgumentException in such a case:
http://pmd.sourceforge.net/pmd-4.3.0/rules/strictexception.html#AvoidThrowingNullPointerException


## iluwatar (23 Mar 2015)

Thank you for pointing that out @ItaloQualisoni and @arosenf.

This is now fixed with commit https://github.com/iluwatar/java-design-patterns/commit/62b871774af5d0f43ec45bb1248738a428cecb7f


