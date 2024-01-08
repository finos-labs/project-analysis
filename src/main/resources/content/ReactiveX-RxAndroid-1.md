#RxAndroid Bootstrap

Owner: ReactiveX

Repo: RxAndroid

Labels: 

## benjchristensen (19 Aug 2014)

RxAndroid has been extracted from RxJava prior to 1.0 as per https://github.com/ReactiveX/RxJava/issues/589

I have done this with a simply copy/paste/commit for now. Commit history remains in the 0.20.x branch of RxJava: https://github.com/ReactiveX/RxJava/tree/0.20.x

If anyone wishes to replace the code in this project with files that retain commit history, go ahead and make the changes.

Over the coming weeks the build and release process will be updated. 

This project does not need to release immediately as 1.0. It can continue with 0.x releases until it makes sense to hit 1.0. Its lifecycle is now decoupled from RxJava.


## benjchristensen (19 Aug 2014)

I have recreated the project with history intact but it does not yet include the examples submodule. 

I'm trying to figure out how to pull both modules together without every file ending up with me being the last committer as a result of doing folder moves.

Also, nothing is building right now. Working with @quidryan on a new setup for that.


## mttkay (23 Aug 2014)

Thanks Ben!


