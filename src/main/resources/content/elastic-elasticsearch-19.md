#flush_index hangs when no indices exist

Owner: elastic

Repo: elasticsearch

Labels: >bug v0.05.0 

## clintongormley (16 Feb 2010)

flush_index hangs when no indices exist, then eventually just closes the connection 


## kimchy (16 Feb 2010)

Yep, its a bug. A fix is traveling on the intertubes as I type...


## kimchy (16 Feb 2010)

flush_index hangs when no indices exist, closed by 1299f203645d1b4b72abfedc1d65991b05042361.


