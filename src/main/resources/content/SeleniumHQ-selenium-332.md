#ExpectedConditions.not is not working correctly

Owner: SeleniumHQ

Repo: selenium

Labels: C-java I-defect 

## JamesZoft (13 Mar 2015)

Details of the setup used to test this:

Windows 7
ChromeDriver (chrome ver 41)
Java bindings, selenium version 2.44.0

Java and html+js code to reproduce the issue: https://gist.github.com/anonymous/c81bb74d9bbcd94b0398

What should happen: The test should pass as the div with id 'woop' is being removed via assigning the innerHTML.
What happens: The wait times out after waiting for the ExpectedConditions specified (for the element with id 'woop' to not be present).

You can see the same behaviour with visibilityOfElementLocated and using ExpectedConditions.not - here it's even more obvious that this is broken as using invisibilityOfElementLocated works just fine where using ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated) doesn't.


## lukeis (14 Mar 2015)

Ok... so tracked this one down. Basically the ExpectedConditions.not  is unequipped to handle the inversion of Conditions where they throw an exception (that is ignored by the Wait class, in this case NoSuchElementException is the one thrown and ignored).

I don't see a good way to fix this as the ExpectedConditions.not does not have access to the list of Ignored exceptions when it is evaluating it's criteria.

For this particular use case the proper ExpectedCondition to use is ExpectedConditions.invisibilityOfElementLocated  (since it considers elements not found to be 'invisible').

With this 'workaround' I'm going to close this issue, but I've updated the JavaDoc for ExpectedConditions.not to explain this gotcha.


## andreastt (15 Mar 2015)

@lukeis, thanks for making the necessary updates to the API docs.


