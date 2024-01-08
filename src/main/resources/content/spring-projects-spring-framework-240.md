#Runtime resolver for @ActiveProfiles

Owner: spring-projects

Repo: spring-framework

Labels: 

## michail-nikolaev (27 Feb 2013)

Hello.
I my current project I often want to resolve test active profiles at the runtime.
For example: I want to put package-level annotation to the use same profiles for bulk of tests without manual annotating them (it is not possible annotate base class because tricky inheritance in test classes).
Currently it is looks impossible to do such trick correctly using any documented way (test execution listeners or inheritance from SpringJUnit4ClassRunner) without marking every test as `@DirtyContext`.

So, I wonder is it possible to extend `@ActiveProfiles` annotation (or add a new one) to do something like this:

```
    @RunWith(SpringJUnit4ClassRunner.class)
    @ActiveProfiles(resolver = SomeActiveProfileResolver.class)
    class SomeClass() {}

    class SomeActiveProfileResolver implement ActiveProfileResolver {
           @Override
           public String[] resolveProfiles(Object testInstance) {
                  // some logic
           }
    }

    interface ActiveProfileResolver {
        String[] resolveProfiles(Object testInstance);
    }
```

I will happy to contribute implementation if you agree with idea.

Thanks a lot.


## sbrannen (27 Feb 2013)

If you need to programmatically configure active profiles for integration tests, the best approach is to create a custom `ApplicationContextInitializer` and declare that via `@ContextConfiguration(..., initializers = MyCustomContextInitializer.class)`.

I will therefore close this issue.

Regards,

Sam


## michail-nikolaev (27 Feb 2013)

Thanks for your advice.

But as I know usage `ApplicationContextInitializer` will lead to disabling caching of Spring context (which is implemented in `TestContextManager`). So it will make testing too slow.

Regards,
Michail.


## sbrannen (27 Feb 2013)

No, that's not true. Initializers are taken into consideration for context caching. See the `equals()` and `hashCode()` implementations in `MergedContextConfiguration` for details:

https://github.com/SpringSource/spring-framework/blob/v3.2.1.RELEASE/spring-test/src/main/java/org/springframework/test/context/MergedContextConfiguration.java

This is also documented in the "Context caching" section of the Testing chapter in the reference manual:

http://static.springsource.org/spring/docs/3.2.x/spring-framework-reference/html/testing.html#testcontext-ctx-management-caching

Regards,

Sam


## michail-nikolaev (27 Feb 2013)

You're right, but only the concrete implementation type of `ApplicationContextInitializer` is used for caching.

Consequently, a given concrete `ApplicationContextInitializer` implementation is semantically not able to first initialize a context using profiles "A, B" and then to later initialize a subsequent context using only profile "A", since the first context will have been cached based on the concrete initializer type.

It is therefore impossible to programmatically select profiles depending on information retrieved from the test class instance (e.g., based on the parent package).

I think an `ActiveProfileResolver` could be very useful in some cases, but you are the judge here. :)

Thanks for you help.


## sbrannen (27 Feb 2013)

I hope you don't mind that I edited your last comment for greater clarity. If I misinterpreted your original intent, please let me know.


## sbrannen (27 Feb 2013)

Note: if an `ActiveProfileResolver` is not required to perform the exact same resolution every time it is invoked, that would mean that the profile resolution algorithm is nondeterministic. Consequently, a context loaded using an `ActiveProfileResolver` would not be able to be stored in the context cache, thereby requiring that each such test class be annotated with `@DirtiesContext`.

So, how do you envision that an `ActiveProfileResolver` would be used for determining the context cache key (i.e., in `MergedContextConfiguration`)?

Or are you suggesting that any context loaded using an `ActiveProfileResolver` simply not be cached at all?


## sbrannen (27 Feb 2013)

Reopening for further discussion...


## michail-nikolaev (27 Feb 2013)

First thanks a lot for attention and for editing my terrible english.

The main idea does not include `ActiveProfileResolver` in `MergedContextConfiguration` hashcode but to include `ActiveProfileResolver.resolve()` invocation result.

Let change `ActiveProfileResolver` interface to this (resolve using class rather than instance - generally it is almost same, because instance will be without `@injects` anyway):

```
interface ActiveProfileResolver {
    String[] resolve(Class<?> testClass);
}
```

In such case to achive correct context caching and dynamic profiles we just need in `ContextLoaderUtils.resolveActiveProfiles` check for `@ActiveProfiles.resolver` and call its `String[] resolve(...)` method (instead of getting profiles from `@ActiveProfiles.profiles`). So, same `String[] resolve(...)` results will lead to same `MergedContextConfiguration` instances (in case of other fields equality) and positive cache matches in `TestContext.getApplicationContext`.


## sbrannen (27 Feb 2013)

OK. That makes a lot more sense.

Thanks for the clarification!

Could you please create a JIRA issue entitled "Introduce ActiveProfilesResolver in the TestContext framework" and assign it to the "TEST" component?

Thanks in advance,

Sam

p.s. I'm now closing this issue since it will be tracked in the JIRA issue you create.


## michail-nikolaev (27 Feb 2013)

Done: https://jira.springsource.org/browse/SPR-10338
Thanks for your help and attention.


