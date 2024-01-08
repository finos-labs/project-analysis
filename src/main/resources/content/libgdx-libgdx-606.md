#Contact.getFixtureA() and ...B() generate Long type allocations

Owner: libgdx

Repo: libgdx

Labels: enhancement 

## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 18, 2010 03:18:48_

continual calls to Contact.getFixtureA() and ...B() continually creates Long type allocations which I guess would make the GC kick in here and there eventually.

Dont know if anything can even be done about it, but thought it worth a mention.

_Original issue: http://code.google.com/p/libgdx/issues/detail?id=18_


## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 17, 2010 19:48:29_

Sort of related (not sure if this warrants a seperate issue), I've attached a tracefile which shows what happens when going through alot of different contact points. If you've never launched a tracefile manually, use traceview.bat from android-sdk/tools, if its in your path just pass the file as an arg, if its not in your path, just copy it to the same folder

This is dealing with 40 contacts every box2d step. You'll notice the cumulative calls of .getFixtureA takes longer then the entire box2d step. Likewise, when combined with .getFixtureB, these two calls take twice as long as the box2d step call.

This is mostly coming from the HashMap I guess which is generating the Long type allocations when ?get()? is called and is also taking quite a long time per get call


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 18, 2010 05:00:08_

Hm, the trace is indeed interesting. Here's a small note though: jniWorldUpdate is actually calling into the Java side World class where getFixture is used to decide whether two bodies should collide. So the percentage you can see for getFixtureA/getFixtureB might stem from this.

The boxing of longs is indeed a problem. I could use Trove but that would add a lot of kb to the jar file just to get the long/object hashmap of trove. I'll try to change that asap, maybe i find a proper hashmap implementation out there which fits our needs.

**Status:** Accepted  


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 18, 2010 05:00:15_

**Owner:** badlogicgames  


## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 18, 2010 07:36:06_

Thanks, I reran the test without running my code calling getFixture's from contactList, while the getFixture's where not present in the new trace, it's two children Long.valueOf and hasmap.get were there. Im not sure what to make of it all from the trace except it could all definately benefit from something faster.

I spent the better part of yesterday just learning java map specifics and playing around with libgdx, different mappers and different configurations rebuilding libgdx, but couldn't find anything that made a significant dif, so it'll be enlightening to see how you handle this


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 18, 2010 08:18:35_

Well, the only thing that can be done is using another HashMap implementation (apart from changing the architecture which is as good as i can do it atm). Sun's HashMap is a bit of a bitch as it wraps objects that get stored in it via Entry instances. So this alone adds overhead memory wise and might put pressure on the GC. However, fixtures are mostly static unless you destroy and create new bodies plus fixtures often (which you don't do if you are wise :). So the GC will barely if ever run during normal game play. That was my intuition when using HashMaps in this scenario. Now, the performance hit of calling HashMap.get() is something i don't quiet understand. It might be that the Android implementation is a bit of a bitch (like many other standard lib classes on Android). There are options out there which have far better performance. The one i use at work for highperformance computing stuff is called Trove, http://trove4j.sourceforge.net/ which allows hashing with primitive keys without all the boxing and wrapping. It also uses another mechanism internally to store the entries. The problem is that trove is huge and would add twice the size libgdx currently has. Extracting only the necessary parts is not possible due to license issues. I'll do a bit of research tomorrow and will hopefully find an alternative (Colt's open hashmaps could be of help). Another avenue to improve the performance is to get rid of looking up Fixture instances in the World.contactFilter and use preallocated Fixture instance instead. Don't get your hopes up high though i don't think we'll get any significant boost out of either modification.

Btw, want to get on the team and become the performance master? :)


## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 18, 2010 08:35:30_

haha, sure, im a bit of a speed freak when its called for. Alot of my future involvement with this project hinges on the success of this game I'm writing on the market place (just let it sell a few hundred!) as I already have a full-plate of open-source work. So if it works out even moderately ok, you'll be seeing me here nitpicking and possibly offering patches now that I can build libgdx and learning the ins and outs of java.

Worst-case, maybe I can rip out the parts i need sped up and replace with trove collections, incur the size penalty game-wise instead of library-wise. Doesn't really make since to blow the library up that big. I'll have to read into some of those things you mentioned this weekend


## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 18, 2010 11:21:11_

I want to leave a note for reference, since this affects anything thats calling getFixture, this has a huge impact on things like world.queryAABB where hashmap.get and Long.valueOf account for roughly 50% of the call (in my particular case anyway).

The call to queryAABB is already expensive, with the overhead of .get and .valueOf its the speed of 16 getWorldPosition calls (relative to the case im on). So being able to cut that down by any margin would make queryAABB more viable to use in various situations


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 19, 2010 03:14:29_

**Labels:** -Type-Defect Type-Enhancement  


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on August 17, 2010 05:55:16_

Fixed in SVN by using a silly LongHashMap implementation. Now that took a long time :p I waited for Christoph to get me a nice little hashmap implementation.

**Status:** Fixed  


