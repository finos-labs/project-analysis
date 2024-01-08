#bin/sbt script throws an error

Owner: openzipkin

Repo: zipkin

Labels: 

## johanoskarsson (14 Jun 2012)

When running the bin/sbt script I get this error.
bin/sbt: line 16: [: too many arguments

It works as expected though, but we should fix the problem anyway.


## johanoskarsson (22 Aug 2012)

Fixed by @glynd in #116. Thanks!


