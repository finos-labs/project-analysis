#Add support for constant query string parameters

Owner: square

Repo: retrofit

Labels: 

## dcaunt (26 Nov 2012)

At present the RestAdapter will simply append GET variables to an endpoint, beginning with a ?

It should be possible to detect the presence of existing query string variables and append Named parameters using ampersands.

Currently it's necessary to provide constant GET variables in every service call as a Named parameter.

``` java
// Doesn't quite work
public interface MyServiceAsync {

    // Produces a url like "endpoint?constantVariable=constantValue&page=1".
    @GET("endpoint?constantVariable=constantValue")
    void normalGet(@Named("page") int page,
                   Callback<MyResponse> callback);

}
```


## JakeWharton (26 Nov 2012)

I think this is what `@QueryParam` is for.


## JakeWharton (26 Nov 2012)

It is.

Example:

``` java
@GET("endpoint")
@QueryParam(name="constantVariable", value="constantValue")
void normalGet(@Named("page") int page, Callback<MyResponse> callback);
```


## JakeWharton (26 Nov 2012)

More examples: https://github.com/square/retrofit/blob/master/retrofit/src/test/java/retrofit/http/RestAdapterTest.java#L650-666


## dcaunt (27 Nov 2012)

Thanks guys, my bad. I should have looked at the tests. 

Retrofit is fantastic but could use a little more documentation. I'm happy to contribute an amended readme if it's useful?


## pforhan (27 Nov 2012)

Sure, put together a pull request and we'll be happy to review.


## JakeWharton (27 Nov 2012)

@dcaunt Retrofit will have a full website with documentation once it's useful enough to the general public. It's about 90% there but still has some awful APIs and assumptions before I'm comfortable telling anyone to use it. I'll try to whip up the beginnings of the site next week so that we can at least start getting a place for contributions to docs to go.


## dcaunt (27 Nov 2012)

@JakeWharton Understood. I have a feeling this library is going to be wildly popular when you hit a 1.0 release. 


## Macarse (22 Jun 2013)

`QueryParam` was removed in https://github.com/square/retrofit/commit/2ef7ca064584f60416581b0793557eb4277136a1.

> This removes support for `@QueryParam`/`@QueryParams` in favor of including constants in the declared URL itself.
> e.g.,
> 
>   @GET("/list?foo=bar")


## roideuniverse (12 May 2015)

I have a situation where I need my params to be of contant value and I cannot declare it in the url itself.  Removal of `@QueryParams` makes this not so good. :disappointed:


## JakeWharton (12 May 2015)

Those statements are contradictory.


## roideuniverse (12 May 2015)

My bad, I just realized that weather I do in in the URL itself or in the `@QueryParams`, it makes no difference. 


## snakemouse3d (14 May 2015)

how can i make a request like this:
URL: "/rest/meterdetail/X"
X is a variable that i want to pass a value to.
- answer: Oh, I forgot to use @Path annotation.


## nizamsp (15 Oct 2016)

I don't see this in Retrofit 2. What's the alternative for constant param and its value in Retrofit 2?


## tomblenz (31 Oct 2016)

@nizamsp Encode into the url itself, eg.

```
@GET(".../CheckVersion/?os=android)
```

This still supports using variable `@Query()` params in conjunction.


## kevintanhongann (23 Jan 2017)

The QueryParam annotation is super useful though. When you hardcode the query parameter into a url, it looks like it's gonna smell. 

## tomblenz (23 Jan 2017)

`QueryParam` is hardcoding.  You should use `Query` if you need something variable.

## kevintanhongann (24 Jan 2017)

Since query param annotation is not there anymore, you would hardcode
strings within the GET or POST annotation, and that smells.

On Tue, 24 Jan 2017, 3:11 AM Thomas Norman, <notifications@github.com>
wrote:

> QueryParam is hardcoding.
>
> —
> You are receiving this because you commented.
> Reply to this email directly, view it on GitHub
> <https://github.com/square/retrofit/issues/103#issuecomment-274586255>,
> or mute the thread
> <https://github.com/notifications/unsubscribe-auth/AAV8cbosw6tFtDOam1Ck-gnjDuYXZosLks5rVPtOgaJpZM4ARoWB>
> .
>
-- 

Sincerely,
Kevin H.A. Tan
Polyglot Software Developer, Technopreneur
http://about.me/kevintanhongann

+60182013739


## JakeWharton (24 Jan 2017)

It's literally the same thing, just less indirection.

On Mon, Jan 23, 2017, 8:24 PM Kevin H.A Tan <notifications@github.com>
wrote:

> Since query param annotation is not there anymore, you would hardcode
> strings within the GET or POST annotation, and that smells.
>
> On Tue, 24 Jan 2017, 3:11 AM Thomas Norman, <notifications@github.com>
> wrote:
>
> > QueryParam is hardcoding.
> >
> > —
> > You are receiving this because you commented.
> > Reply to this email directly, view it on GitHub
> > <https://github.com/square/retrofit/issues/103#issuecomment-274586255>,
> > or mute the thread
> > <
> https://github.com/notifications/unsubscribe-auth/AAV8cbosw6tFtDOam1Ck-gnjDuYXZosLks5rVPtOgaJpZM4ARoWB
> >
> > .
> >
> --
>
> Sincerely,
> Kevin H.A. Tan
> Polyglot Software Developer, Technopreneur
> http://about.me/kevintanhongann
>
> +60182013739
>
> —
> You are receiving this because you were mentioned.
> Reply to this email directly, view it on GitHub
> <https://github.com/square/retrofit/issues/103#issuecomment-274672900>,
> or mute the thread
> <https://github.com/notifications/unsubscribe-auth/AAEEEdPmxVDyUifTDnImt11HCKk072loks5rVVLIgaJpZM4ARoWB>
> .
>


## AmmarMohammad (29 Jun 2019)

I think @QueryParam is super useful when you have quite many constant queries and you just want to place each in a single line for the readability of the code. If anything, it looks better organized than string concatenation. Besides, although I doubt this will ever happen, BUT STILL, consider my case:
I am sneaking into an API that I want the server to believe it's by one of its authentic clients. I am really afraid someone looking at the log/debug lines of their server would know a JS script written by them would never specify params the way retrofit does (dynamic/changeable params always coming at the end) which might lead them to make our lives just harder..

## xanscale (03 Mar 2021)

@Macarse @JakeWharton 

using `@get("/list?foo=bar")` how to do url encoding?

i need something like
`@get("/list?foo=aa&bb/cc")`

