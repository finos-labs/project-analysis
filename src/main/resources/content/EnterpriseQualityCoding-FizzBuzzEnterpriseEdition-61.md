#com.seriouscompany.business.java.fizzbuzz.packagenamingpackage.impl.FizzBuzz.fizzbuzz(int n) is too big to understand

Owner: EnterpriseQualityCoding

Repo: FizzBuzzEnterpriseEdition

Labels: 

## r00tman (01 May 2013)

function com.seriouscompany.business.java.fizzbuzz.packagenamingpackage.impl.FizzBuzz.fizzbuzz(int n) is too big to understand. 
Also I think that name n for an argument is too simple and it doesn't reveal its real meaning.


## emiln (09 Aug 2013)

In EnterpriseQualityCoding we take great care to express namespaces and methods succinctly. `com.seriouscompany.business.java.fizzbuzz.packagenamingpackage.impl.FizzBuzz.fizzbuzz` adheres to company naming convention standards and is praised as an enterprise grade name by experts in the namespace- and method-naming fields. We are unwilling to change its time-honored name at this time.

However, you will be pleased to learn that #75 addressed the non-descriptive parameter name, `n`, changing it to the much more expressive `nTotalCount`.


## r00tman (15 Aug 2013)

I understand and agree that naming conventions but I care about the size of function called like that.
I think a function that even doesn't fits my monitor size isn't good and needs to be refactored.
It has too much business logic inside.


## Dmitry-Me (15 Aug 2013)

@r00tman, please observe this change https://github.com/EnterpriseQualityCoding/FizzBuzzEnterpriseEdition/commit/dbd2994cca3a5de758ebab8e37d379d9f924c78d - it separates some of that code into several chunks. Is that finely grained enough or do you think we need to go deeper?


## joezeng (15 Aug 2013)

@Dmitry-Me, he's referring to the name of the package, not the length of the code inside.

I agree that the namespace is unnecessarily long; however, I will defer to emiln and his company naming convention.


## Dmitry-Me (15 Aug 2013)

@joezeng, not sure, the claim is "function X is too big to understand" which I interpret as meaning that function X body is too big.


## emiln (15 Aug 2013)

I remain unclear about the particular problem you are experiencing with our Enterprise product, @r00tman. Can I ask you to specify one or more of the following statements as being descriptive for your issue?
- [ ] The namespace is long and/or confusing.
- [ ] The method name is long and/or confusing.
- [ ] The code inside the method is long and/or confusing.

This will help our developers understand your hardship.


## r00tman (17 Aug 2013)

"The code inside the method is long and/or confusing."
Changes made by @Dmitry-Me are good, but still I'm confused by the for loop in fizzbuzz(int nTotalCount).
Can we change it to a while loop so this part would be more readable and flexible?


## Dmitry-Me (19 Aug 2013)

@r00tman, There's an idea of the following improvement: loop state variable manipulations will be extracted into a separate class and the loop will only use an object of that class. This way the loop wiring implementation will be completely isolated from the loop itself and there will hardly be any difference between a for-loop and a while-loop.


## r00tman (19 Aug 2013)

@Dmitry-Me, Agreed with you, but why all the logic should be on one very long string of code?


## Dmitry-Me (19 Aug 2013)

@r00tman I'm sure you're going to extreme here. Suppose some piece of code relies on calling a specific function - like for example calling FizzBuzz.fizzBuzz() which is an entry point. This means that the program logic heavily depends on the line that contains that call. So if you always object against logic being on some line of code it means you can't use function calls at all.


## Dmitry-Me (20 Aug 2013)

@r00tman Please observe this pull request https://github.com/EnterpriseQualityCoding/FizzBuzzEnterpriseEdition/pull/88 - once that is merged the issue is likely be resolved completely. Do you see any more problems?


## r00tman (20 Aug 2013)

I think this part would be ok after merging #88 and we can close that issue. Thanks for fast response and action.


## emiln (21 Aug 2013)

With the merging of #88, I will consider this issue closed.


