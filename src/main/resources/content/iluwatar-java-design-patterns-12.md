#Not have private constructor in the thread-safe Singleton class ?

Owner: iluwatar

Repo: java-design-patterns

Labels: 

## wokaole (05 Nov 2014)

hello iluwatar

when I read the Singleton  pattern ,the ThreadSafeLazyLoadedIvoryTower class use the default constructor method ,you don't specify it ，should it right ?
I think must add a private constructor as the IvoryTower class.
is it right?


## annemsujan (05 Nov 2014)

Yes wokaole, that is right. Private constructor should be added. Thanks for pointing it out. 


## iluwatar (05 Nov 2014)

Fixed now in master. Thanks for reporting!


