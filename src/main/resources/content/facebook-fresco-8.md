#Error:Failed to find: com.facebook.fresco:fresco:0.1.0+

Owner: facebook

Repo: fresco

Labels: 

## JiangDaYa0 (27 Mar 2015)

why failed?


## michalgr (27 Mar 2015)

Maven central knows about that artifact (http://search.maven.org/remotecontent?filepath=com/facebook/fresco/fresco/0.1.0/fresco-0.1.0.aar) and I am able to successfully build a project depending on it. However, I had similar problem earlier today with one of the other artifacts. It seems that clearing local cache helped resolve my issue. Warning though: I removed entire ~/.gradle/caches directory and as a result the very next build was very slow.


