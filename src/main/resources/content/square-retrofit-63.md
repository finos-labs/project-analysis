#Change Fetcher API To Report Progress as Float

Owner: square

Repo: retrofit

Labels: 

## JakeWharton (16 Oct 2012)

From @swankjesse on #62:

> It would be more conventional for the progress listener to just take a float between 0 and 1. It's sort of weird that a UI decision (progress update granularity) is being decided here.
> 
> In slow transfers (say 1MiB over 3G) using percent is lame because multiple reads may yield the same percentage of progress. (10KiB and 19 KiB are both reported as 1% done).


## JakeWharton (23 Oct 2012)

`Fetcher` removed.


