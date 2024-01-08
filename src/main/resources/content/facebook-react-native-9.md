#Provide more descriptive error message when source file is not found

Owner: facebook

Repo: react-native

Labels: Resolution: Locked 

## gaearon (30 Jan 2015)

I required wrong file by mistake and got this message:

![screen shot 2015-01-30 at 23 56 23](https://cloud.githubusercontent.com/assets/810438/5984113/ae355a5c-a8db-11e4-94cc-d0f51e6b7b4e.png)

The actual message was obscured in Xcode console:

```
Error: 
 stack: 
ModuleError@http://localhost:8081/Examples/Movies/MoviesApp.includeRequire.runModule.bundle:105:23
require@http://localhost:8081/Examples/Movies/MoviesApp.includeRequire.runModule.bundle:202:28
http://localhost:8081/Examples/Movies/MoviesApp.includeRequire.runModule.bundle:28141:33
require@http://localhost:8081/Examples/Movies/MoviesApp.includeRequire.runModule.bundle:243:30
http://localhost:8081/Examples/Movies/MoviesApp.includeRequire.runModule.bundle:27651:26
require@http://localhost:8081/Examples/Movies/MoviesApp.includeRequire.runModule.bundle:243:30
http://localhost:8081/Examples/Movies/MoviesApp.includeRequire.runModule.bundle:946:27
require@http://localhost:8081/Examples/Movies/MoviesApp.includeRequire.runModule.bundle:243:30
applyWithGuard@http://localhost:8081/Examples/Movies/MoviesApp.includeRequire.runModule.bundle:871:25
require@http://localhost:8081/Examples/Movies/MoviesApp.includeRequire.runModule.bundle:194:39
global code@http://localhost:8081/Examples/Movies/MoviesApp.includeRequire.runModule.bundle:28295:9
 URL: http://localhost:8081/Examples/Movies/MoviesApp.includeRequire.runModule.bundle
 line: 202
 message: Requiring unknown module "./test/actions/RepoActionCreators". It may not be loaded yet. Did you forget to run arc build?"
```

It would be nice to display the actual error.


## gaearon (30 Jan 2015)

It looks like this message replaces the original message very quickly.


## nick (30 Jan 2015)

I found this too. I ended up commenting out these lines as a short term fix:

https://github.com/facebook/react-native/blob/master/Libraries/Bundler/Bundler.js#L49-L52


## vjeux (30 Jan 2015)

cc @amasad 


## amasad (31 Jan 2015)

@gaearon  in this case what module where you requiring?


## amasad (31 Jan 2015)

ok, it looks like you were requiring `RepoActionCreators`. Did that module ever exist and then was deleted?

The packager should definitely return an error and no code. But it also seems to be a bug that the error message has been replaced with a less useful one. cc @frantic 


## gaearon (31 Jan 2015)

> ok, it looks like you were requiring RepoActionCreators. Did that module ever exist and then was deleted?

No, I mistyped the filename. When I fixed it, the error went away.
It's just unfortunate that the real error is always quickly replaced with a vague one.

In fact if you mistype a filename and keep pressing Cmd+R really really fast, that'll give you time to read the first actual error on the screen (before it gets replaced by the vague one).


## joeyyang (01 Feb 2015)

Replacing one error message with another is also giving me issues -- I have an npm module that's failing to load and having all the error messages persist would be very helpful in attempting to debug. Let me know what other information I can provide!


## frantic (27 Mar 2015)

@nicklockwood fixed this a while ago


