#Intercepting Filter example needs some improvement

Owner: iluwatar

Repo: java-design-patterns

Labels: 

## iluwatar (04 Apr 2015)

- [FilterChain](https://github.com/iluwatar/java-design-patterns/blob/master/intercepting-filter/src/main/java/com/iluwatar/FilterChain.java) executes the filters sequentially using an array. This should be changed so that the filters form a chain and call each other instead. Like in [Chain of Responsibility](https://github.com/iluwatar/java-design-patterns/tree/master/chain).
- At the same time the feedback mechanism from the filters should be improved. This should be implemented with [Collecting Parameter idiom](http://c2.com/cgi/wiki?CollectingParameter). It should be possible to return multiple errors from the filter chain. The current implementation can handle only one.
- The data passed to the filters should be transformed into value object from string array. This way we can get rid of magic numbers (array index) in filters.


## vehpsr (04 Apr 2015)

If it's not strictly UI related pattern than we should move it to "Behavioral Patterns" section instead of "Presentation Tier".
And if above is true than I suggest to remove all UI (JFrame Targer-Client) code: it "hides" pattern usage in overcomplecated UI setup. Simple chain invocation with some sysout of outcome might do the job for presentation. 


## iluwatar (04 Apr 2015)

You are correct. Intercepting Filter is not presentation tier pattern and should be relocated.


## iluwatar (04 Apr 2015)

To me removing the UI code is secondary, but I don't object if it will be done.


## iluwatar (09 Apr 2015)

The above commits fix this issue.


