#Capture exception from run() and expose getter

Owner: Netflix

Repo: Hystrix

Labels: enhancement 

## benjchristensen (27 Nov 2012)

If a HystrixCommand has a fallback implemented the exception is not thrown. Sometimes there are use cases where application code wants to inspect the exception even though there is a fallback.

It would be something like:

HystrixCommand.getExceptionIfThrown()


## benjchristensen (29 Nov 2012)

Committed in https://github.com/Netflix/Hystrix/commit/2d970d98db86e2bafb91ad487d913a77fc38b288


