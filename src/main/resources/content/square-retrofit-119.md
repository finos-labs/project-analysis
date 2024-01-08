#Support Canceling Requests

Owner: square

Repo: retrofit

Labels: 

## JakeWharton (17 Dec 2012)

Perhaps by a tag (Volley)? Or allowing `Future` return types.


## JakeWharton (24 Jan 2013)

Could also do `@Tag` method param. Or `TagCallback` which accepts a tag in its constructor.


## JakeWharton (27 Mar 2013)

New idea! Return a `RetrofitTask<T>` (extends `FutureTask<T>`) which has a `setTag(Object)` method on it.


## JakeWharton (08 Aug 2013)

Part of #297.


