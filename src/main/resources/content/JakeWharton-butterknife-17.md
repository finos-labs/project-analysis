#Injection isn't happening when combined with a Robolectric test suite.

Owner: JakeWharton

Repo: butterknife

Labels: 

## thorinside (27 Mar 2013)

I've added some injection view @InjectView on an Activity under Robolectric test. The views are always null after the Views.inject(this) call after the setContentView() call. I am using a pretty standard Maven build script based off of Android Bootstrap, and have had no luck with the unit test calling activity.onCreate(null) during the test. Tracing through the code, it doesn't seem to be picking up the @InjectView annotations, and simply does the 'no operation' case. What have I done wrong? Is there a trick to getting a unit test running?


## JakeWharton (27 Mar 2013)

Since I recently added the beginnings of a test suite this should be fairly easy to test for...


## JakeWharton (28 Mar 2013)

Can you take a look at https://github.com/JakeWharton/butterknife/commit/fa89384d52b6e664afb8fc96f37d5d6fe052397f ? It seems to be working for me, at least.


## JakeWharton (28 Mar 2013)

Did the activity too: https://github.com/JakeWharton/butterknife/commit/e370252fecaaae68f5054dbeb3d9e7b567e4ce6a


## JakeWharton (28 Mar 2013)

I'm curious as to why you are getting the no operation case. How are you running tests? If it's through an IDE you need to ensure that annotation processing is running and that it's part of the test classpath.


## thorinside (28 Mar 2013)

I was trying to run my unit tests in IntelliJ today. I wonder if it was a compiler issue or something with my environment. I will have a look when I get home. Thanks.


## thorinside (28 Mar 2013)

I have IDEA 12 so I must not have enabled something. Any clues where I should look?


## thorinside (28 Mar 2013)

I looked it up and will try to get it working tonight. I am sure your earlier clue will be sufficient.


## thorinside (28 Mar 2013)

Yeah, if I run the Maven build, my tests run just fine. The problem was I was assuming that IntelliJ was also building things correctly. If I force the tests to run the Maven Compile lifecycle before the tests are run in IntelliJ, I have had success. This is good enough for me for now. Thanks for writing the test code, it will also be instructive.


