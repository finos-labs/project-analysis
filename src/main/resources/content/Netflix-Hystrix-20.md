#TotalExecutionTime not tracked on queue()

Owner: Netflix

Repo: Hystrix

Labels: bug 

## benjchristensen (29 Nov 2012)

The TotalExecutionTime is tracked on execute() but not on queue().

This is not a problem with the normal executionTime metric around the run() method, but the total end-to-end metric.

For queue() we want to track from the time queue() is invoked until the time the underlying thread completes and a Future.get() could retrieve the value even if it doesn't.

We don't want to track until Future.get() is invoked as that is not the actual processing time of the execution and can be variant depending on what the client code is doing.

This was a known missing feature but I'm marking this as a bug as it should have been there before marking the 1.0 release.


## benjchristensen (22 Dec 2012)

Implemented in 1.1.7

Here is a screenshot showing before (green) and after (red) on a command that is only executed asynchronously so previously did not capture TotalExecutionTime but now does.

![Screen Shot 2012-12-21 at 7 34 43 PM](https://f.cloud.github.com/assets/813492/28282/8fb75690-4be8-11e2-88c9-713a7ebf686d.png)


