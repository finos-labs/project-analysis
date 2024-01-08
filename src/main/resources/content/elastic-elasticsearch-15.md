#Optimize API: Add onlyExpungeDeletes, flush and refresh parameters

Owner: elastic

Repo: elasticsearch

Labels: >enhancement v0.05.0 

## kimchy (15 Feb 2010)

- _onlyExpungeDeletes_: Performs lightweight optimization by only expunging pending deltes. Defaults to false.
- _flush_: Should a flush be performed after the optimization. Defaults to false.
- _refresh_: Should a refresh be performed after the optimization. Defaults to false.


## kimchy (15 Feb 2010)

Optimize API: Add onlyExpungeDeletes, flush and refresh parameters. Closed by 66b86a7a034eb9ccc7d391713016f30f9448737e.


