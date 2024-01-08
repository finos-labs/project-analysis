#traces aren't sorted when displayed in chrome

Owner: openzipkin

Repo: zipkin

Labels: 

## mosesn (10 Aug 2012)

chrome has an [unstable sort](http://code.google.com/p/v8/issues/detail?id=90), so although spans are sorted by start_time when the traces/get_trace.json endpoint gets called, they become mixed up when they're [sorted again](https://github.com/twitter/zipkin/blob/master/zipkin-web/app/assets/javascripts/application.js).


## franklinhu (10 Aug 2012)

Do you have the line number for the "sorted again" in application.js?


## mosesn (10 Aug 2012)

I'm pretty sure [this guy](https://github.com/twitter/zipkin/blob/master/zipkin-web/app/assets/javascripts/application.js#L170) is the offender.


## franklinhu (10 Aug 2012)

I believe that sorts the query results on the index page. Weren't you talking about on the spans in the visualization on the individual trace page?


## mosesn (10 Aug 2012)

I'm pretty sure that's in [this guy](https://github.com/twitter/zipkin/blob/master/zipkin-web/app/assets/javascripts/application-index.js) ,
the one I pointed you to  looks for children, which makes sense in a tree
context, but not in a trace context.  Also, application-index has multiple
sort orders, such as by duration and timestamp that I don't believe the
individual trace page support.

On Fri, Aug 10, 2012 at 4:17 PM, Franklin Hu notifications@github.comwrote:

> I believe that sorts the query results on the index page. Weren't you
> talking about on the spans in the visualization on the individual trace
> page?
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/twitter/zipkin/issues/108#issuecomment-7655447.


## franklinhu (23 Aug 2012)

Do you have any test data that breaks it? I'm trying to reproduce locally. Thanks!


## mosesn (23 Aug 2012)

I can't repro it either, so I'm going to assume we had a mass hallucination in my office, or chrome now uses a stable sort.  Sweet.


