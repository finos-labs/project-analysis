#use HttpUrlConnection for android >= Gingerbread

Owner: square

Repo: retrofit

Labels: 

## kaciula (01 Nov 2012)

Are there any plans to add support for HttpUrlConnection?

According to [1], HttpUrlConnection should be used on android 2.3 and higher. I'm on the fence if I should use this library as it is now because of this. Thoughts?

[1] http://android-developers.blogspot.ro/2011/09/androids-http-clients.html


## pforhan (01 Nov 2012)

https://github.com/square/okhttp/ is another http client available, as well.  We've written an OkHttpClient to use with Retrofit.  I'll see if we can move that to open-source, perhaps.


## JakeWharton (01 Nov 2012)

So I talked with @swankjesse about this a week or two ago and for now we decided to focus on the actual REST implementation rather than supporting multiple clients. The public API is client-agnostic (except, of course, the `setClient` method on `RestAdapter.Builder`) so future support of this will be relatively trivial and have no impact on implementations.

In fact, the consumed API on `HttpClient` is so small that it wouldn't be too hard to write a wrapper around URL connection to support it. Obviously that's not the ideal case, but if you _really_ need to use URL connection it's a possibility.


## kevintanhongann (27 Nov 2012)

I plan to do a fork of this repo, and turning retrofit into an Android library project and make a public repo out of that. Is there any guidelines on how should I do it so that I don't violate any licenses? 


## JakeWharton (27 Nov 2012)

It's Apache 2 licensed so as long as you remain within its guidelines you can do whatever you want.


## kevintanhongann (27 Nov 2012)

Alright. I shall consult you when I need help with conforming with the guidelines. 


## kevintanhongann (27 Nov 2012)

https://github.com/L0rDKadaj/retrofit-android

Repackaging is done, along with the test Android project. Please advise if I did it the wrong way. 

Thanks.


## JakeWharton (27 Nov 2012)

The library is already usable as a .jar. Why do you want it as a library project?


## kevintanhongann (28 Nov 2012)

It's because I'm used to the Android development environment. Plus makes it easier for debugging purposes. It may be an unnecessary step for others though.

Dunno if I can start working on future improvements, eg. using Android classes to improve the project.  


## dnkoutso (28 Nov 2012)

AFAIK you only need a project to be a library project if you are packing
resources and/or manifest entries (activities, services, etc...).

Considering retrofits goal of being platform-agnostic it should end up
being only a simple jar. You will still be able to debug it without a
problem.

On Tue, Nov 27, 2012 at 4:00 PM, L0rDKadaj notifications@github.com wrote:

> It's because I'm used to the Android development environment. Plus makes
> it easier for debugging purposes. It may be an unnecessary for others
> though.
> 
> Dunno if I can start working on future improvements, eg. using Android
> classes to improve the project.
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/square/retrofit/issues/96#issuecomment-10783602.


## JakeWharton (28 Nov 2012)

This is actually going to be a bit of a challenge to implement but it's definitely something I'd like to provide out of the box. The library should just do The Right Thing™, especially on Android. I've added support for providing a different default with #104 so once an abstraction of a client is in place (the hard part) the actual switching of clients will be super trivial.


## JakeWharton (24 Jan 2013)

Coming with #136! It won't land for a bit (week or two, hopefully), but I'm going to close this since the hard work is done. Refer to that pull for status updates.


## JakeWharton (27 Jan 2013)

Just kidding. Will come as a follow-up.


## JakeWharton (27 Feb 2013)

#159 


