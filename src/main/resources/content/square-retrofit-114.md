#Content-Type header is being sent twice...

Owner: square

Repo: retrofit

Labels: 

## thorinside (10 Dec 2012)

In a small app I am writing, the Content-Type header is being sent more than once. This is causing a problem with parsing the HTTP request on a node.js/Express server. If I replay the request without the duplication, the request is received properly by the server.

I'll look into why it is happening and see if I can send a push request for the fix.


## thorinside (10 Dec 2012)

See issue #116 for pull request.


