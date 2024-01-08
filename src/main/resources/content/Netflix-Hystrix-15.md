#Property to disable percentile calculations

Owner: Netflix

Repo: Hystrix

Labels: enhancement 

## benjchristensen (27 Nov 2012)

If someone does not want to use percentile calculations there is no point in tracking and calculating them so let's have a property to disable.


## pgrinchenko (05 Jan 2013)

This would be a good one. +1


## benjchristensen (07 Jan 2013)

The metrics.rollingPercentile.enabled property controls this: https://github.com/Netflix/Hystrix/wiki/Configuration

It already existed but a modification is coming shortly to make it more efficiently prevent any work done inside RollingPercentile when metrics poll it.


