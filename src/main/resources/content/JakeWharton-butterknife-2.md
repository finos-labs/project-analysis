#Add Support For Non-Final IDs

Owner: JakeWharton

Repo: butterknife

Labels: 

## JakeWharton (05 Mar 2013)

Android's resource system is disaster and to unbreak library projects last year they made the IDs non-final.

`@InjectView(id = "title") TextView title`

``` java
Context context = activity.getApplicationContext();
String packageName = context.getPackageName();
Resources res = context.getResources();
// ...
int id = res.getIdentifier(packageName, "android:id", annotation.id());
```


## JakeWharton (01 Apr 2013)

Well AA is a framework, not a library.


## imminent (10 Apr 2013)

I might be missing something, but how does Butter Knife give your library project (if it supported non-final "injection") something that you can't do without Butter Knife, @derekbrameyer ? The `@InjectView` just provides an alternative (and I'd say superior) way to write `findViewById(...)`. 


## pyricau (11 Apr 2013)

@imminent Quote: `View injection in the library would make the code just that much more robust`


## imminent (11 Apr 2013)

My question is "how"does it make it more robust than `findViewById(...)`
On Apr 11, 2013 12:59 AM, "Pierre-Yves Ricau" notifications@github.com
wrote:

> @imminent https://github.com/imminent Quote: View injection in the
> library would make the code just that much more robust
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/JakeWharton/butterknife/issues/2#issuecomment-16221393
> .


## derekbrameyer (11 Apr 2013)

"Robust" may not have been the best word to describe it, but the removal of all of the findViewById() calls would certainly increase the maintainability of the library.


## radzio (08 May 2013)

It would be great if you could add support for non final ids. Is it hard to implement?


## dkunzler (18 Jun 2013)

I would also love to see this feature. Eventually I would implement it myself if I know that you would accept a pull request.


## dkunzler (04 Jul 2013)

@stonecs and I implemented a solution using the idName of a view instead of the `R.id.*`. @JakeWharton could you please review and check if you can merge this change? (and release a new version in maven central maybe)

Thanks


## radzio (05 Jul 2013)

@dkunzler I've tested your solution and it seems to work ok but...

http://daniel-codes.blogspot.com/2009/12/dynamically-retrieving-resources-in.html did you thought to use reflection? It could be faster than

```
int viewId = view.getResources().getIdentifier(idName, "id", view.getContext().getPackageName());
return view.findViewById(viewId);
```


## sealskej (20 Sept 2013)

+1


## CumpsD (30 Dec 2013)

Anything ever came out of this?

I'm trying to use ButterKnife in a library project, but can't use it because of this :(


## imminent (30 Dec 2013)

As it is now, don't think it's for library projects. Think that's all to
it. It seems to be more of an issue of how Android is built, dealing with
merging the R files of libraries with the dependent project (which affects
how Android generates the library's R file). And less so an issue of Butter
Knife.

On Monday, December 30, 2013, David Cumps wrote:

> Anything ever came out of this?
> 
> I'm trying to use ButterKnife in a library project, but can't use it
> because of this :(
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/JakeWharton/butterknife/issues/2#issuecomment-31341320
> .

## 

## 

Dandré Allison                                (323) 823-4456
KeepandShare.com http://www.keepandshare.com

```
          Schedule & Share. Simply. Securely.
```


## crossle (27 Apr 2014)

+1


## ertanden (06 Jul 2014)

Library projects support would be great. I'm not using ButterKnife just because of that.


## esfilho (11 Aug 2014)

+1


## rharter (14 Aug 2014)

@radzio I agree this would be great.  Why don't you submit a PR with your solution that you said works?


## thedumbtechguy (19 Jun 2015)

just run into this issue too. guess butterknife is out of my library then. but ooo, it made my code that much cleaner.


## wrozwad (16 Dec 2015)

Sorry guys for the spam but +1 from me too


## jaredsburrows (29 Dec 2015)

+1


## flywheelms (16 Feb 2016)

+1000


