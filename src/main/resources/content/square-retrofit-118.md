#Use a default value for a parameter?

Owner: square

Repo: retrofit

Labels: 

## SeanPONeil (14 Dec 2012)

I'm pretty sure Retrofit (or Java for that matter) doesn't allow it, but I figured I would ask.


## JakeWharton (14 Dec 2012)

`@QueryParam` does this. See #103.


## SeanPONeil (14 Dec 2012)

Wow. Not bad.


## CoolMind (27 Aug 2019)

It is used only in GET queries.

## CoolMind (27 Aug 2019)

For POST requests it is still not solved, see https://github.com/square/retrofit/issues/951.

