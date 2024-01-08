#Dashboard: Threshhold for Error Percentage Sorting

Owner: Netflix

Repo: Hystrix

Labels: enhancement dashboard 

## benjchristensen (11 Dec 2012)

The dashboard allows sorting by Error Percentage and Error + Volume.

It can be a little jumpy on circuits with small error rates like 0.1, 0.2 that flip back and forth.

Also, sorting a low volume circuit with 0.1 error rate to the top is generally not useful.

We should pursue having a threshold and better handling of jittery circuits at the decimal level (maybe ignore decimals when sorting) so that the sorting is useful without being noisy and jittery.


## mattrjacobs (13 Nov 2015)

Closing this out due to inactivity.  If someone would like to take ownership of the dashboard roadmap, please re-open.  


