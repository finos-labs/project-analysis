#Should filter() be converted from Func<T, Boolean> to Pred<T>

Owner: ReactiveX

Repo: RxJava

Labels: Question 

## benjchristensen (16 Jan 2013)

Should we add a new special predicate object instead of using Func<T, Boolean>?


## benjchristensen (16 Jan 2013)

I am fine with Func<T, Boolean>.

The performance difference for autoboxing from Boolean to boolean should be trivial for this use case and I don't find the <T, Boolean> as enough clutter to warrant a different object.

But I'm not strongly opinionated either way.


## abersnaze (16 Jan 2013)

If Pred<T> could extend Func<T, Boolean> but still have lower case boolean return type maybe we could add it later if we need to.


## benjchristensen (16 Jan 2013)

![Screen Shot 2013-01-16 at 3 12 57 PM](https://f.cloud.github.com/assets/813492/73146/53184e52-6032-11e2-972a-886ee307b5ec.png)


## abersnaze (16 Jan 2013)

sad face


