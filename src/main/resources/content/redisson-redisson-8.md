#Setting expiry

Owner: redisson

Repo: redisson

Labels: 

## bjdodo (14 Mar 2014)

Hi 
I could not find a way to set expiry for the contents of the cache from within Java. Is this true, or did I miss something?

Thank you
Jozsef


## mrniko (21 Mar 2014)

which redisson object need ability to set expire?


## bjdodo (21 Mar 2014)

If I have this code:

Redisson cli = Redisson.create();
Map<Integer, Date> m = cli.getMap("aaa");
Date d = new Date();
((RMap<Integer, Date>)m).put(1, d);

It would be great to be able to say from the Java code that I want the Date with ID 1 to be evicted after e.g. a minute. Or if this is not possible, it would be great to at least be able to get the map "aaa" evicted after a minute. I think I can do this second version by configuration but could not find a way to do this from java code. 

Using Hazelcast it kind of works (the case when I want the Date with ID 1 to be evicted) like this:

HazelcastInstance cli = HazelcastClient.newHazelcastClient(conf);
IMap<Integer, Date> m = cli.getMap(mapName);

Date ret = m.get(id);
Date d = new Date();
ret = m.put(id, d, 3, TimeUnit.SECONDS);
m.get(id); // returns Date equivalend to d
Thread.sleep(4000);
m.get(id); // returns null

Of course this works across processes. This is what I was looking for. 

Thank you for your answer!


## doggie1989 (03 May 2014)

I think the best way to do it in redisson is that SET a expiry time for the collection...

Redisson redisson = Redisson.create();
Map map = redisson.getMap("testMap",3);

and then in this map, all objects will live 3 seconds..


## mrniko (03 May 2014)

@doggie1989 use RExpirable interface added in 1.0.4


## colinfindlay-nz (05 Aug 2014)

Is there a way for each item in the map to expire seperately - at the moment it looks like the whole map  expires after 3 seconds - not each entry.


## ketankhairnar (09 May 2015)

@mrniko I tried to implement RExpirable interface but there is no way to create connection-manager. I referred RedissonExpirable and other objects extending from it. 

It seems that Connection Manager etc are well encapsulated. Can you point to me to any example where RExpirable is implemented outside Redisson code base.

thanks in advance.


## mrniko (04 Dec 2015)

@silver2k yes, now it's implemented in a new `RCache` object. Try it.


