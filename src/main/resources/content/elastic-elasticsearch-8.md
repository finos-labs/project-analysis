#HTTP: Rest API should support receiving HTTP chunks

Owner: elastic

Repo: elasticsearch

Labels: >enhancement v0.05.0 

## kimchy (13 Feb 2010)

For large messages, certain HTTP clients will chunk the requests. Though it is probably better to disable this if possible on the client side, we should still support chunked HTTP messages.


## kimchy (13 Feb 2010)

HTTP: Rest API should support receiving HTTP. Closed by 5ac51ee93feab6c75fcbe979b9bb338962622c2e.


