#[C#] SelectElement does not give access to base element functionality

Owner: SeleniumHQ

Repo: selenium

Labels: C-dotnet 

## FrankyBoy (19 Mar 2015)

Hi!  
Right now, SelectElement does not give access to the base element's functionality. Eg. even if you need to access simple stuff as the "Displayed" property of the base element, you have to provide them separately in your PageObjects.

```
public IWebElement SelectBase // returns base iwebelement for checks
public SelectElement Select // wraps around SelectBase
```

It would be nice to make the base element accessible either through some property, or by implementing IWebElement on SelectElement (which then passes the calls on to base element essentailly)

I did the 2nd in https://github.com/FrankyBoy/selenium/commit/88ce778e3aebbced514fc522a6eed34dbac7814f to see how hard it would be, and its really just boring boilerplate.


## FrankyBoy (15 Apr 2015)

neat :)


