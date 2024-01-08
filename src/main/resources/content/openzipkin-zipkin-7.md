#Start scripts

Owner: openzipkin

Repo: zipkin

Labels: 

## johanoskarsson (07 Jun 2012)

We don't have any easy way of starting up the open source version. Need to add some scripts for that.


## carrino (13 Jun 2012)

in the instructions it simply says: Start the collector and query daemon.

However I am running into some issues.  When I run the following

java -cp zipkin-server_2.9.1-0.2.0-SNAPSHOT.jar com.twitter.zipkin.query.Main

I get the following error:
FAT [20120612-22:27:59.508](root): Error in config file: /etc/unknown.conf
FAT [20120612-22:27:59.508](root): java.io.FileNotFoundException: /etc/unknown.conf (No such file or directory)
FAT [20120612-22:27:59.508](root):     at java.io.FileInputStream.open(Native Method)
FAT [20120612-22:27:59.508](root):     at java.io.FileInputStream.<init>(FileInputStream.java:106)
FAT [20120612-22:27:59.508](root):     at scala.io.Source$.fromFile(Source.scala:91)
FAT [20120612-22:27:59.508](root):     at scala.io.Source$.fromFile(Source.scala:76)
FAT [20120612-22:27:59.508](root):     at com.twitter.util.Eval$$anonfun$1.apply(Eval.scala:144)
FAT [20120612-22:27:59.508](root):     at com.twitter.util.Eval$$anonfun$1.apply(Eval.scala:144)
FAT [20120612-22:27:59.508](root):     at scala.collection.TraversableLike$$anonfun$map$1.apply(TraversableLike.scala:194)
FAT [20120612-22:27:59.508](root):     at scala.collection.TraversableLike$$anonfun$map$1.apply(TraversableLike.scala:194)
FAT [20120612-22:27:59.508](root):     at scala.collection.IndexedSeqOptimized$class.foreach(IndexedSeqOptimized.scala:34)
FAT [20120612-22:27:59.508](root):     at scala.collection.mutable.WrappedArray.foreach(WrappedArray.scala:33)
FAT [20120612-22:27:59.508](root):     at scala.collection.TraversableLike$class.map(TraversableLike.scala:194)
FAT [20120612-22:27:59.508](root):     at scala.collection.mutable.WrappedArray.map(WrappedArray.scala:33)
FAT [20120612-22:27:59.508](root):     at com.twitter.util.Eval.apply(Eval.scala:144)
FAT [20120612-22:27:59.508](root):     at com.twitter.ostrich.admin.RuntimeEnvironment.loadConfig(RuntimeEnvironment.scala:226)
FAT [20120612-22:27:59.508](root):     at com.twitter.ostrich.admin.RuntimeEnvironment.loadRuntimeConfig(RuntimeEnvironment.scala:252)
FAT [20120612-22:27:59.508](root):     at com.twitter.zipkin.query.Main$.main(Main.scala:29)
FAT [20120612-22:27:59.508](root):     at com.twitter.zipkin.query.Main.main(Main.scala)

I have tried a couple of things messing with build.properties and using -f config/query-dev.scala but everything I do i get an error at /etc/unkown.conf


## franklinhu (13 Jun 2012)

Can you try: `-f zipkin-server/config/query-dev.scala`


## carrino (13 Jun 2012)

I could have sworn I tried that.  And I see it in my history. But yes, that seems to work.  :)


## johanoskarsson (14 Jun 2012)

@carrino - if you put together a start script feel free to contribute it back, would be helpful for others trying out Zipkin.


## johanoskarsson (15 Jun 2012)

Fixed in #32


