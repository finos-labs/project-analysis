#Can we get a 1.x-RCX build in jcenter/maven central? 

Owner: ReactiveX

Repo: RxAndroid

Labels: 

## yogurtearl (29 Sept 2014)

Can we get a 1.x-RCX build in jcenter/maven central? Thanks.

With out that, I can't build against the 1.x-RC version of rxjava-core.


## austynmahoney (30 Sept 2014)

`rxjava-core` is still on `0.20.4` on Maven Central. You have to use `0.20.4` for now, which is okay because these builds are identical except for version information. The `1.0.x` artifacts are under a different [groupId](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22rxjava%22).

Discussion [here](https://github.com/ReactiveX/RxAndroid/issues/2).


## yogurtearl (01 Oct 2014)

1.0.x deprecates more methods, no?

I would like to use 1.0.x RC to make sure we don't add any new usages of
deprecated methods.

On Tuesday, September 30, 2014, Austyn Mahoney notifications@github.com
wrote:

> rxjava-core is still on 0.20.4 on Maven Central. You have to use 0.20.4
> for now, which is okay because these builds are identical except for
> version information. The 1.0.x artifacts are under a different groupId
> http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22rxjava%22.
> 
> Discussion here https://github.com/ReactiveX/RxAndroid/issues/2.
> 
> —
> Reply to this email directly or view it on GitHub
> https://github.com/ReactiveX/RxAndroid/issues/16#issuecomment-57396230.


## mttkay (01 Oct 2014)

I think I removed all deprecated classes and methods on master. Did I miss
any?


## benjchristensen (01 Oct 2014)

RxAndroid 0.21 was released last night and depends on RxJava 1.0


## yogurtearl (01 Oct 2014)

Thanks!

On Wednesday, October 1, 2014, Ben Christensen notifications@github.com
wrote:

> RxAndroid 0.21 was released last night and depends on RxJava 1.0
> 
> —
> Reply to this email directly or view it on GitHub
> https://github.com/ReactiveX/RxAndroid/issues/16#issuecomment-57491632.


