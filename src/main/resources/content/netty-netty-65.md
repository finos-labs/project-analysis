#Provide distribution stats in HashedWheelTimer

Owner: netty

Repo: netty

Labels: feature 

## trustin (18 Nov 2011)

A user schedules a lot of timeouts with `HashedWheelTimer`, there is a chance some slot has way too many items comparing to other slots.  It would be very useful if `HashedWheelTimer` exposes the detailed stats about the distribution of the timeouts in the wheel, a user will be able to increase the size of the wheel or choose to use different timer implementation.


## obergner (19 Sept 2012)

I'm interested in contributing this feature. But what are the exact requirements?
- Timeouts per slot?
- Timeouts per slot over time?
- Deviation in milliseconds from scheduled execution time?
- Published via JMX?

Cheers,
Olaf


## trustin (22 Sept 2012)

Yeah something like them.  I would refrain from publishing via JMX by default though.  A user should have choices whether to use JMX or simple objects to retrieve the stats because he or she might want to expose the stats via some other interfaces such as JSON over HTTP.


## trustin (22 Sept 2012)

Probably such statistic numbers and histograms could be reused everywhere in Netty, so we might need to introduce a good common utility classes first or use something pretty good which is adopted widely by the community.


## normanmaurer (22 Sept 2012)

Keep netty dependency free would be really nice ..


## obergner (22 Sept 2012)

Sigh, I was hoping to be able to use [Yammer Metrics](http://metrics.codahale.com/), an excellent library I use extensively. Writing a good statistics library will probably take some time. I'll have a look ...


## normanmaurer (22 Sept 2012)

Yes yammer is great, but not sure if we should force users to have the dependency if they are not interested in statistics at all..

@trustin wdyt?

Sent from my iPhone. Excuse any typos....

Am 22.09.2012 um 15:04 schrieb obergner notifications@github.com:

> Sigh, I was hoping to be able to use Yammer Metrics, an excellent library I use extensively. Writing a good statistics library will probably take some time. I'll have a look ...
> 
> —
> Reply to this email directly or view it on GitHub.


## kimchy (22 Sept 2012)

-1 for yammer metrics, +1 keep netty lean with 0 deps 


## spullara (22 Sept 2012)

I think we could compromise here and provide an implementation of Yammer Metrics that isn't required and not reinvent the wheel while at the same time making it possible to get all the stats we need. They have successfully instrumented many projects at arms-length:

http://metrics.codahale.com/manual/

If it isn't possible to do that with Netty perhaps we don't have the right level of extensibility. I know that Twitter has successfully instrumented our Netty usage (in Finagle) with Ostrich, which is similar. Perhaps that API would be a callback interface that you register?


## normanmaurer (22 Sept 2012)

Sounds right the way to go.. Just have listeners/callbacks which get notified on updates and so naje it possible to hook in.

Sent from my iPhone. Excuse any typos....

Am 22.09.2012 um 21:32 schrieb Sam Pullara notifications@github.com:

> I think we could compromise here and provide an implementation of Yammer Metrics that isn't required and not reinvent the wheel while at the same time making it possible to get all the stats we need. They have successfully instrumented many projects at arms-length:
> 
> http://metrics.codahale.com/manual/
> 
> If it isn't possible to do that with Netty perhaps we don't have the right level of extensibility. I know that Twitter has successfully instrumented our Netty usage (in Finagle) with Ostrich, which is similar. Perhaps that API would be a callback interface that you register?
> 
> —
> Reply to this email directly or view it on GitHub.


## obergner (23 Sept 2012)

Yes, I was thinking about this approach, too. After all, it seems in line with Netty's logging abstraction layer.


## obergner (27 Sept 2012)

I would like to first concentrate on providing distribution stats for the deviation between scheduled and effective timer task execution time:
- Maximum deviation from scheduled timer task execution time in ms
- Minimum deviation from scheduled timer task execution time in ms
- Mean deviation from scheduled timer task execution time in ms
- 99 percentile

I think this - how precise HashedWheelTimer is actually working in his/her specific situation - is what a user would probably be most interested in. Plus it is a relatively low-hanging fruit. Sounds OK?


## trustin (28 Sept 2012)

Sounds good to me.  Looking forward to your contribution and eventually replacing ScheduledThreadPoolExecutor in Netty 4.


## trustin (28 Sept 2012)

Interesting paper wrt histogram - http://infolab.stanford.edu/~manku/papers/98sigmod-quantiles.pdf


## normanmaurer (28 Sept 2012)

Why you want to replace it? I think its a good fit

Sent from my iPhone. Excuse any typos....

Am 28.09.2012 um 05:45 schrieb Trustin Lee notifications@github.com:

> Sounds good to me. Looking forward to your contribution and eventually replacing ScheduledThreadPoolExecutor in Netty 4.
> 
> —
> Reply to this email directly or view it on GitHub.


## trustin (28 Sept 2012)

Because it will be useful to have stats?  It needs more work to make it
support repeated tasks though.


## obergner (28 Sept 2012)

Hi Justin, thanks for the link, sounds interesting and I will surely have a look at it. OTOH that's exactly why I prefer Yammer to any handrolled solution. It uses reservoir sampling to only retain statistically meaningful samples of its measurements, thus balancing precision and memory consumption.


## obergner (30 Sept 2012)

So, the two commits above so conveniently linked to by GitHub contain my first sketch of how I would like to move forward with this issue. They implement a small abstraction layer for metrics providers in netty-common and introduce a new module, netty-metrics-yammer, that implements this abstraction layer/SPI using Yammer Metrics as its metrics provider.

Before I move on I would like to get some feedback:
- Is the basic approach valid? Are there alternatives I should investigate?
- The current implementation is closely modeled after Yammer Metrics, as this is the library I know (and like) best. Do you think it will be able to support other providers?
- Do you know of any other open source monitoring/metrics libraries for Java that I could use to validate my implementation? I searched the internet but couldn't find anything useful.
- Anything else you find worth mentioning. 


## trustin (17 Oct 2012)

Looks pretty good to me.  Please issue a pull request when you are done, then we can do code review more closely. Cheers!


