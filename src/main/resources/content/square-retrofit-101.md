#More descriptive error when Executors aren't set

Owner: square

Repo: retrofit

Labels: 

## SeanPONeil (24 Nov 2012)

I recently switched my RestAdapter interface to use Callbacks, but threw a NullPointerException because I didn't call setExecutors in RestAdapter.Builder. Took me a couple hours to figure out what was going on, so I feel like it might be nice to have a custom message when a null HttpExecutor or CallbackExecutor is found.

I can make a pull request and implement this if you guys want.


## JakeWharton (24 Nov 2012)

#102


## SeanPONeil (24 Nov 2012)

D'oh


