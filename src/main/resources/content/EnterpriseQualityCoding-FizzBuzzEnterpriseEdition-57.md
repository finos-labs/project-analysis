#Code for checking number is a multiple of another number is heavily duplicated

Owner: EnterpriseQualityCoding

Repo: FizzBuzzEnterpriseEdition

Labels: 

## Dmitry-Me (12 Apr 2013)

Here https://github.com/Mikkeren/FizzBuzzEnterpriseEdition/blob/master/src/main/java/com/seriouscompany/business/java/fizzbuzz/packagenamingpackage/impl/strategies/NoFizzNoBuzzStrategy.java and here https://github.com/Mikkeren/FizzBuzzEnterpriseEdition/blob/master/src/main/java/com/seriouscompany/business/java/fizzbuzz/packagenamingpackage/impl/strategies/BuzzStrategy.java and here https://github.com/Mikkeren/FizzBuzzEnterpriseEdition/blob/master/src/main/java/com/seriouscompany/business/java/fizzbuzz/packagenamingpackage/impl/strategies/FizzStrategy.java

there's code that goes like this

if ((((int)(i / FizzStrategyConstants.FIZZ_INTEGER_CONSTANT_VALUE)) \* FizzStrategyConstants.FIZZ_INTEGER_CONSTANT_VALUE == i)) {
   return true;
} else {
   return false;
}

Now that cleverly engineered code for checking (a%b==0) is in effect the same everywhere, just the constant changes. In the meantime there's a chance that this approach to checking (a%b==0) happens to be buggy and needs to be enhanced (perhaps by adding an enterprise version of a crystal ball) - then it needs to be changed everywhere. So that's a program maintenance and extensibility showstopper.

Code for checking (a%b==0) needs to be factored out into some kind of reusable mechanism.


## ultimape (26 Apr 2013)

I found a PHP script that we might be able to port and extend. It seems to be very elegant: https://github.com/Herzult/SimplePHPEasyPlus


## Dmitry-Me (02 Aug 2013)

This has been resolved https://github.com/Mikkeren/FizzBuzzEnterpriseEdition/commit/c83b5d460b19bd199f7ead582997d8e0c42c19d6


