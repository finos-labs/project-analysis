#Observable: abstract or final

Owner: ReactiveX

Repo: RxJava

Labels: Question 

## benjchristensen (16 Jan 2013)

There is discussion about whether Observable should be a "final class" and only be usable via Observable.create(Func) or whether we should leave it as abstract.

Discuss and decide ...


## abersnaze (16 Jan 2013)

I vote not final.
If it were final then it would be impossible to extend Observable to add operators and still interoperate with other code that uses Observable.


## benjchristensen (16 Jan 2013)

Agreed


## benjchristensen (23 Jan 2013)

We have decided to not make it final.

But we also have changed it from being abstract to a normal concrete class.

The 'subscribe' implementation is passed in via a protected constructor or using Observable.create(Func1)


