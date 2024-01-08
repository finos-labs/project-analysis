#Switch banner to use a Log

Owner: spring-projects

Repo: spring-boot

Labels: 

## mdeinum (16 Aug 2013)

Currently the Banner implementation uses a Printstream to write the log, it would be nice if the Banner would be printen (also) using a Log. That way it would appear in logfiles also, making it quite clear when an application started.


## philwebb (23 Aug 2013)

Unfortunately using the log file for output results in quite a mess due to all the timestamp/thread/class info that gets included.

We currently do log a 'Starting' message but I think I want to keep the banner under our control which unfortunately means leaving it out of the log


## btiernay (17 Sept 2015)

@philwebb now that there is the `Banner` abstraction, would it be possible to make this configurable? I personally would like to have this in the logs as it clearly delineates the process boundary of multiple executions. The default should still be `stdout` however.


## philwebb (21 Sept 2015)

@btiernay It should be possible I think. I'll open a new issue.


## btiernay (21 Sept 2015)

@philwebb Bootiful!


