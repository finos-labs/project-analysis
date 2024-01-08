#Query by multiple key/value pairs

Owner: openzipkin

Repo: zipkin

Labels: 

## johanoskarsson (24 Aug 2012)

Sometimes it's useful to be able to search for multiple key/value pairs. For example in order to find traces with 500 errors for a particular url.

key=http.uri, value=/i/discovery.json
key=http.responsecode, value=500 Internal Server Error


## franklinhu (06 Sept 2012)

Fixed in #137


