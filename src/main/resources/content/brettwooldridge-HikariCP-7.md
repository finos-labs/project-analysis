#Is it possible to have performance comparison with Tomcat-JDBC

Owner: brettwooldridge

Repo: HikariCP

Labels: question 

## bric3 (09 Dec 2013)

Hi,

Tomcat guys written another pool and that code base is much more recent. It should be interesting to include this in the benchmark.

http://tomcat.apache.org/tomcat-7.0-doc/jdbc-pool.html


## brettwooldridge (09 Dec 2013)

I'll try to get around it to.  You could also fork the code and add the tomcat datasource to the `benchmark1.java` class.  It should be pretty simple to see what needs to be done if you [glance at the code](https://github.com/brettwooldridge/HikariCP/blob/master/core/src/test/java/com/zaxxer/hikari/performance/Benchmark1.java).


## brettwooldridge (10 Dec 2013)

Ok, I ran the benchmark with the tomcat-jdbc library you referred to.  The tests were run on my laptop, a circa 2010 MacBook Pro with Core i5 processor (not my usual benchmarking machine):

Benchmarking Tomcat-JDBC - 400 threads, 50 connections
MixedBench
 Warming up JIT
  max=14059ms, avg=13602ms, med=13919ms
  max=7032ms, avg=6482ms, med=6726ms
 MixedBench Final Timing Runs
  max=4511ms, avg=2921ms, med=4460ms
  max=4519ms, avg=3284ms, med=4386ms
  max=4597ms, avg=2769ms, med=4543ms
  max=4525ms, avg=3544ms, med=4467ms

BoneBench
 Warming up JIT
  max=224421000ns, avg=98337944ns, med=199176000ns
 BoneBench Final Timing Run
  max=211407000ns, avg=137618701ns, med=193142000ns
  max=172927000ns, avg=73365926ns, med=154840000ns
  max=209094000ns, avg=95888768ns, med=191634000ns
  max=192283000ns, avg=85874672ns, med=173455000ns

---

Here is the HikariCP bench result on the same machine:

Benchmarking HikariCP - 400 threads, 50 connections
MixedBench
 Warming up JIT
  max=8969ms, avg=8773ms, med=8866ms
  max=6288ms, avg=5903ms, med=6007ms
 MixedBench Final Timing Runs
  max=2869ms, avg=1407ms, med=2780ms
  max=1191ms, avg=92ms, med=434ms
  max=609ms, avg=38ms, med=69ms
  max=220ms, avg=16ms, med=50ms

BoneBench
 Warming up JIT
  max=2605000ns, avg=120644ns, med=65000ns
 BoneBench Final Timing Run
  max=123000ns, avg=77989ns, med=40000ns
  max=2023000ns, avg=38441ns, med=34000ns
  max=1667000ns, avg=41007ns, med=35000ns
  max=1373000ns, avg=47654ns, med=36000ns

---

### Summary

Best tomcat-jdbc time from run:

  max=4519ms, avg=3284ms, med=4386ms

Best HikariCP time from run:

  max=220ms, avg=16ms, med=50ms

You can see that HikariCP's trend was getting faster, probably there is some small amount of code remaining to be compiled by the JIT, in spite of the warmup run.  Tomcat-jdbc does not seem to be trending in that direction, but seem to be stabilized around 4300-4400ms.


## brettwooldridge (10 Dec 2013)

I re-ran the benchmarks on my main box (Core i7-3770), here is a chart of the results:

![benchmark](https://f.cloud.github.com/assets/1538661/1711645/a5d4d3ce-6156-11e3-97f1-07104e75017e.png)

This was with a constrained pool of 50 connections, like the chart on the main page.  Not a bad showing for tomcat, actually.


## bric3 (10 Dec 2013)

Nice, thanks for the rapid feedback ;)
Interesting numbers !

I think this graphic is really nice and should be shown on the homepage, as it shows more how theses pools are trending in face of HikariCP. 


## brettwooldridge (10 Dec 2013)

I'll try to publish it to the homepage in a day or two.  I still need to add footnotes and run the same tests for Unconstrained Pools.


## bric3 (10 Dec 2013)

OK, Cool ;)

Again thanks for feedback.


## liaofangcai (27 Oct 2016)

very nice


