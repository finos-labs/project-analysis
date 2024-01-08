#Stop using ArrayList in VertexAttributes

Owner: libgdx

Repo: libgdx

Labels: bug 

## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 18, 2010 01:35:46_

I've been doing some profiling with DDMS and trying to kill bottlenecks of my code. I'm a complete noob to java performance, but it seems that ArrayList's cause slowdowns with having to make calls like .get(i)

After changing my ArrayList's to the likes of Object list[] = new Object[15] Im starting to see libgdx take up a larger percentage of the rendering time. A call to VertexAttributes.get(i) in Mesh.render is actually the most time consuming process according to my profile eating up 25&#37; of Mesh.render's processing time.

Would it benefit the speed of Mesh.render to have VertexAttributes stop using ArrayList's?

Line 47 of VertexAttributes reads
ArrayList<VertexAttribute> list = new ArrayList<VertexAttribute>();

But all the information to do this is already there
VertexAttribute list[] = new VertexAttribute[attributes.length];

and change everything else accordingly.

Sorry if this is a stupid recommendation, like I said before I am a complete noob to java performance and am only going by my profiling benchmarks from DDMS.

_Original issue: http://code.google.com/p/libgdx/issues/detail?id=17_


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 17, 2010 16:53:33_

i'd be surprised if that would have any impact on your perfomance. i'm aware of the slightly worse performance of generic collections compared to arrays but in all my tests this was never a problem. what device/android version are you testing this on? 

please don't file something like this as a defect but rather as a request tor enhancement.

**Status:** Accepted  


## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 17, 2010 17:04:09_

Well I can only go by the general percentages in DDMS and changing all my ArrayList's to list[] reduced their overall percentage.

My hardware is a Hero CDMA running android2.1

My use case is storing box2d bodies in an array that I iterate over after every step, in some cases where I have 15-30 bodies, this produces a faster result. The reason I allocate the bodies before world.step'ing is I was seeing better results simply deactivating bodies and transforming-and-reactivating bodies as needed versus creating and destroying bodies. I also make sure to only call various jni bits once like getWorldCenter only and store it in a seperate array for other things that need access to it.

I would love to actually file these requests correctly, and every time I click new issue, im looking for where I can set the type. Yet I never see it. Maybe I'm blind, but its just not there as an end user.


## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 17, 2010 17:10:09_

off subject and on the issue-type, I actually have a blank code project on google-code here, I went over to the issues, clicked "New Issue" and I have a plethora of options to set. Not sure of the specifics, but filing a new issue on libgdx does not present anything more then a title, description, and submit button.


## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 17, 2010 17:30:04_

On subject, to comment on overall speed improvement of methods making .get calls versus array[sub], each one changed contributed about a 2% improvement to overall performance, for a cumulative 5% performance increase at the current state of my project. So maybe it doesn't seem like a big deal, but all these bits add up and I'm not sure what the benefit of using an ArrayList is if one already knows the size of the array to be made thats in question. In the case of ArrayList usage, its needlessly slower (by any margin) and part of a function (Mesh.render) that is liable to get called alot depending on the application.

If there's a pragmatic benefit to ArrayList usage that libgdx is taking advantage of, then maybe its a moot point and a fair trade off


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 18, 2010 05:07:03_

Fixed in SVN to be released. thanks for the suggestion

**Status:** Fixed  
**Owner:** badlogicgames  


