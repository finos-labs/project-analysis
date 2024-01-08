#BuzzStrategy, FizzStrategy and NoFizzNoBuzzStrategy re-design

Owner: EnterpriseQualityCoding

Repo: FizzBuzzEnterpriseEdition

Labels: 

## advance512 (18 Mar 2013)

BuzzStrategy, FizzStrategy and NoFizzNoBuzzStrategy have several problems:
1. BuzzStrategy and FizzStrategy are two instances of same class; only difference is an integer value - should be refactored to inherit from a SomethingZzStrategy class.
2. SomethingZzStrategy should received integer value NOT as a hard-coded value in BuzzFactory and FizzFactory. Current implementation states:
       if ((((int)(i / 5)) \* 5 == i)) {
   and is obviously problematic as there are two different places of the hard coded value. If a coder changes one 5 and not the other, the class will break. This is just asking for trouble.
3. Integer value used in SomethingZzStrategy should be read and stored in a configuration file, to allow end-users to configure the tool.
4. NoFizzNoBuzzStrategy should use already implemented behavior of BuzzFactory and FizzFacotry to implement it - smart code reuse


## emiln (10 Aug 2013)

All of these valid concerns for maintainability and succinctness have been addressed.
1. `BuzzStrategy` and `FizzStrategy` are now subclasses of `IsEvenlyDivisibleStrategy`.
2. All subclasses of `IsEvenlyDivisibleStrategy` relay decisions about divisibility to the much more suited class `NumberIsMultipleOfAnotherNumberVerifier`, and use constants such as `BuzzStrategyConstants.BUZZ_INTEGER_CONSTANT_VALUE`. While the merit of using constants is still undergoing heated debate, it is certainly an improvement over the old hard-coding, and is a good first step towards Enterprise quality.
3. This has been addressed with the use of constants, and although your specific idea has not been chosen, I will consider the issue fixed. If you are unhappy with the decision about using constants, you should join the related discussions or create a new issue.
4. `NoFizzNoBuzzStrategy` now features a certain degree of code reuse, and I will consider your concerns moot.

In summary, `{Buzz,Fizz,NoFizzNoBuzz}Strategy` have all changed since this issue was created, and a revamp if not complete abolishment of it seems in order. I will close it, but you are encouraged to create a new issue if you have concerns with the new design.


