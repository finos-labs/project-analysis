#Explore use of backup requests to handle long-tail latency

Owner: Netflix

Repo: Hystrix

Labels: enhancement 

## benjchristensen (30 Nov 2012)

I would like to explore the use of backup requests and adding them to Hystrix as an optional tool for dealing with latency.

It has been discussed in several places (particularly referencing Google) such as these:

http://highscalability.com/blog/2012/3/12/google-taming-the-long-latency-tail-when-more-machines-equal.html
http://highscalability.com/blog/2012/6/18/google-on-latency-tolerant-systems-making-a-predictable-whol.html
http://www.bailis.org/blog/doing-redundant-work-to-speed-up-distributed-queries/
http://static.googleusercontent.com/external_content/untrusted_dlcp/research.google.com/en/us/people/jeff/Stanford-DL-Nov-2010.pdf


## allenxwang (30 Oct 2013)

Example of executing backup requests in Ribbon:

https://github.com/Netflix/ribbon/blob/master/ribbon-examples/src/main/java/com/netflix/ribbon/examples/ExecutionWithBackupRequestExample.java

With 200ms timeout, www.google.com always wins even though sent later. With 2000ms timeout, www.microsoft.com wins.

This example shows executing backup requests with a load balancer:

https://github.com/Netflix/ribbon/blob/master/ribbon-examples/src/main/java/com/netflix/ribbon/examples/AsyncLoadBalancingClientExample.java


## benjchristensen (31 Oct 2013)

Nice @allenxwang! I look forward to seeing how this works in prod to see if our 99th percentile latency drops.


## benjchristensen (31 Oct 2013)

@benschmaus This will be very interesting to try out. We should canary and get data to determine whether this is worth the extra network calls or not to reduce long-tail latency.


## mattrjacobs (03 Aug 2015)

I think this belongs in Ribbon or Ocelli.


