#Language Adaptor: Clojure

Owner: Netflix

Repo: Hystrix

Labels: 

## benjchristensen (06 Dec 2012)

A hystrix-contrib module to provide idiomatic interfaces for Clojure.


## benjchristensen (19 Dec 2012)

@daveray

Perhaps a naming convention such as hystrix-clojure, hystrix-scala, etc would work for language adaptors?

If that's the case then a new module for clojure could be added at:

 /hystrix-contrib/hystrix-clojure


## daveray (19 Dec 2012)

`clj-hystrix` or `hystrix-clj` would be typical for a Clojure lib wrapping a Java lib.


## benjchristensen (19 Dec 2012)

Then let's do `hystrix-clj` and locate it at `/hystrix-contrib/hystrix-clj` 

The hystrix- prefix matches all other contrib modules and how things get published to Maven Central.


## daveray (19 Dec 2012)

Works for me :)


## josephwilk (04 Feb 2013)

Looking myself at either using or creating a Clojure wrapper around Hystrix. 
Before I start all that work whats the current state?

I cannot spot a hystrix-clj in the contrib folder or on github


## daveray (04 Feb 2013)

As you already noticed, there's a start here: https://github.com/daveray/Hystrix/commit/96dae7676f29d765fae7cf9b0f211cb415490fb9


## benjchristensen (09 Feb 2013)

Closing this out as the code is committed and starting to get played with.

It is part of release 1.2.7 and code is here: https://github.com/Netflix/Hystrix/tree/master/hystrix-contrib/hystrix-clj


