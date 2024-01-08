#Eval method does not work in RedisAsyncConnection

Owner: redisson

Repo: redisson

Labels: 

## cleyshan (07 May 2014)

The eval method does not seem to work for me.  I traced the cause to this line in theRedisAsyncConnection:eval() method:

args.addValue(script).add(keys.length).addKeys(keys).addValues(values)

It should instead be:

args.add(script).add(keys.length).addKeys(keys).addValues(values)

In its current form, the script is escaped with double quotes and cannot be interpreted by redis.


## mrniko (07 May 2014)

@cleyshan just fixed, please check


## cleyshan (07 May 2014)

Looks good, will test it out tomorrow.  Btw, why is the script argument type V when it needs to be a String?  Thanks for looking into this so quickly!  Really appreciate it


## mrniko (07 May 2014)

@cleyshan oh, it's not my part of code. my part in org.redisson package :)


## cleyshan (08 May 2014)

No problem. I tested the change now and it works for me.  Thanks again for your help!


