#Simplify documentation for first time users

Owner: openzipkin

Repo: zipkin

Labels: enhancement docs 

## franklinhu (13 Sept 2012)

- Set Finagle `sampleRate` to 1
- Set collector sample rate to 1
- Make collector sample rate a top level config option


## johanoskarsson (26 Nov 2012)

From the mailing list discussion we should also update the readme and configs to describe how to set up a minimally useful Zipkin cluster. Without using Scribe for transport (direct to collector) and without ZooKeeper for ServerSet and sample rate.


## eirslett (16 Mar 2016)

This should be fairly well documented by now.


