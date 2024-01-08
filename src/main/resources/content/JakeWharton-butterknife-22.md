#java.lang.ClassNotFoundException: com.mjaworski.myQuotes.QuoteFormFragment$$ViewInjector

Owner: JakeWharton

Repo: butterknife

Labels: 

## javera (10 Apr 2013)

I have problems when trying to use Views.inject(Object, View). I identified the exact line where the issue appears.

In Views class, line 109:
        Class<?> injector = Class.forName(targetClass.getName() + InjectViewProcessor.SUFFIX);

Proguard is disabled. I can't pinpoint what the problem is.


## JakeWharton (10 Apr 2013)

Are the files with the generated code present?


## javera (10 Apr 2013)

I actually did some more googling and hopefully the problem is resolved now.

Just a suggestion, it'd be really useful to mention that annotation processing needs to be enabled in IDE. I know that it may be straightforward, but that is actually a requirement to be able to use your lib. Thanks a lot for the reply as it got me on the right path!

for future reference: https://github.com/JakeWharton/butterknife/issues/12
that's where I found the solution


