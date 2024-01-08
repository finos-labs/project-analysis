#Question: Why do you have imported lettuce code into your project?

Owner: redisson

Repo: redisson

Labels: 

## mp911de (25 May 2014)

Hi, 
why do you have imported lettuce code into your project instead of referencing it via artifact?

The approach your library works is quite cool providing collections. It's just somehow annoying having no access to the underlying RedisClient. Redisson.create(RedisClient client) or even redisson.getList(RedisClient client, String name) etc. would be a way opener approach. The current way, it's Redisson who manages all the Redis stuff for you. I see Redisson as a sort of valueable add-on.


## mrniko (25 May 2014)

As i wrote: Based on PATCHED version of lettuce Redis. And it's based on netty 4, not netty 3 as original version of lettuce. I planing to rewrite it completely in future, so this will not affect developers who uses only Redisson classes.


## mp911de (25 May 2014)

I forked lettuce and made a couple of changes https://github.com/mp911de/lettuce/compare/wg:master...master

I've tried to contact @wg but received no answer until now, I'd like the idea of maintaining lettuce and having your framework on top would make a great combination.


