#[C#] SelectElement.SelectedOption slow on big lists

Owner: SeleniumHQ

Repo: selenium

Labels: C-dotnet I-papercut 

## FrankyBoy (20 Mar 2015)

Hi!

The current implementation of the SelectedOption property is quite slow (because it checks each element in turn). Because of this a colleague of mine implemented an extension method which (he claims). The replacement code is simply as such:

```
return _element.FindElement(By.CssSelector("option[selected]"))
```

AllSelectedOptions could be adapted in a similar manner (i.e. FindElements with same selector).
Let me know if that solution actually works or if its just a "common special case" kind of thing.

Cheers!


## lukeis (20 Mar 2015)

it's not valid for us to generically assume that the selected attribute is used.

Here's an example where this methodology wouldn't work:

https://jsfiddle.net/32t5u702/


## FrankyBoy (20 Mar 2015)

Ah, yes, selected attribute does not change if you change the field. However can be trivially fixed by using working css like so ... https://jsfiddle.net/32t5u702/2/


## lukeis (20 Mar 2015)

does that work across all browsers? (you know like IE7). The :selected pseudo-class appears to be html4 and I don't think it was implemented there.


## FrankyBoy (20 Mar 2015)

IE7? Probably not (according to stackoverflow). Don't have a system to try that on. Random remarks/questions on the topic (mostly for curiosity)
- Obviously there is also ways to do it in JS (though talking about poking into a vipers nest ...)
- Would it be possible to detect is browsers support `:checked` without going super overboard?
- Is there some cutoff in market share when Selenium considers a browser for deprecation?

Edit: maybe i should hang out in your IRC channel for a little ;)


