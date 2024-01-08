#QueryStringDecoder should also support ;

Owner: netty

Repo: netty

Labels: improvement 

## ngocdaothanh (22 Nov 2011)

QueryStringDecoder currently does not parse
field1=value1;field2=value2
correctly.

http://en.wikipedia.org/wiki/Query_string says
This convention is a W3C recommendation.[3] W3C recommends that all web servers support semicolon separators in the place of ampersand separators.


## normanmaurer (22 Nov 2011)

We love patches ;)


## ngocdaothanh (22 Nov 2011)

I think we can preprocess by converting all ; to &. Is that OK?


## normanmaurer (22 Nov 2011)

yes sounds right to me


