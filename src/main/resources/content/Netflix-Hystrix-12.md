#Use logger.error not logger.debug for fallback failure

Owner: Netflix

Repo: Hystrix

Labels: enhancement 

## benjchristensen (27 Nov 2012)

Change the following line to use logger.error:
https://github.com/Netflix/Hystrix/blob/master/hystrix-core/src/main/java/com/netflix/hystrix/HystrixCommand.java#L1355

Even though the Throwable is captured inside HystrixRuntimeException most code handling the exception is not capable of inspecting it thus it becomes very hard in production operations to determine why a fallback fails.

It should be very rare that a fallback fails (the point of fallback is to do something that can't fail) but when it does it's difficult to track down right now.


## benjchristensen (28 Nov 2012)

Completed in commit https://github.com/Netflix/Hystrix/commit/5cdbf70c431cdf5e93e50616a6fac06a21636480


