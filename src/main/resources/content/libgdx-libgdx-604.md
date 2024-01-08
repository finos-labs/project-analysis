#Box2D collision, broken

Owner: libgdx

Repo: libgdx

Labels: bug wontfix 

## badlogic (14 Sept 2013)

_From [rtaylor205@gmail.com](https://code.google.com/u/rtaylor205@gmail.com/) on June 17, 2010 19:37:14_

Collision is not working, objects simply overlap.

The changes in contactFilter always returns false.

Attached is a hack that makes it work, but I don't know whether this is the correct way. For now it will have to do for me :)

_Original issue: http://code.google.com/p/libgdx/issues/detail?id=16_


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 17, 2010 12:33:30_

Ouch, mea culpa. Fixing. Fixed version will be released this weekend along with other fixes.

**Status:** Accepted  
**Owner:** badlogicgames  


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 17, 2010 12:37:42_

Hm, upon further inspection i'm not sure whether this is the correct fix. I implemented the Java side collision filter based on the original Box2D filter. Here's the corresponding method in the original:

// Return true if contact calculations should be performed between these two shapes.
// If you implement your own collision filter you may want to build from this implementation.
bool b2ContactFilter::ShouldCollide(b2Fixture\* fixtureA, b2Fixture\* fixtureB)
{
        const b2Filter& filterA = fixtureA->GetFilterData();
        const b2Filter& filterB = fixtureB->GetFilterData();

```
    if (filterA.groupIndex == filterB.groupIndex && filterA.groupIndex != 0)
    {
            return filterA.groupIndex > 0;
    }

    bool collide = (filterA.maskBits & filterB.categoryBits) != 0 && (filterA.categoryBits & filterB.maskBits) != 0;
    return collide;
```

}

which is exactly what i have in Java. I haven't played around with maskBits and categoryBits yet. Are you sure you are using them correctly?


## badlogic (14 Sept 2013)

_From [rtaylor205@gmail.com](https://code.google.com/u/rtaylor205@gmail.com/) on June 17, 2010 13:44:15_

I know nothing about Box2D, so wouldn't know what the maskBits and categoryBits refer to - I just made an edit that allowed me to keep coding for the time being ^_^


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 17, 2010 15:08:25_

Well, i can't reproduce the problem. I make this a Won't Fix as i'd rather stick with the original code of Box2D than just guestimating a quick hack :) If you have a test case for me to reproduce i'll reopen it.

**Status:** WontFix  


