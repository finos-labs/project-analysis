#Running on device should be possible

Owner: facebook

Repo: react-native

Labels: Resolution: Locked 

## subtleGradient (30 Jan 2015)

Currently throws up a red screen complaining that it can't find localhost


## a2 (30 Jan 2015)

You have a few options here, all of which are documented in [_AppDelegate.m_](https://github.com/facebook/react-native/blob/master/Examples/Movies/AppDelegate.m):
1. Run the server on your computer, change `localhost` to `<your computer's IP>`, and do it that way.
2. `cURL` the file and change it so it references the saved file. You'll have to add the downloaded file to the app binary.

Hope that helps. If you have any trouble, just post here and hopefully we can get that sorted for you.


## vjeux (30 Jan 2015)

@a2 is there any plan to streamline the process?


## a2 (31 Jan 2015)

@vjeux Most definitely. We plan on putting in some sort of build step that "compiles" the JS source directly into a resource file in the app bundle. Obviously in production you wouldn't have a server running nearby.


## mfikes (31 Jan 2015)

For App Store submission, perhaps you have no choice but to include the JS in the bundle if you wish to comply with 2.7 ("Apps that download code in any way or form will be rejected").


## sophiebits (31 Jan 2015)

Yes, we're not looking to download code over the internet for exactly the reason you mention. The Facebook Groups app in the store bundles all of the JS locally.


## roman01la (26 Mar 2015)

+1 for better way


## wesleyahall (26 Mar 2015)

This should at least be documented on the website -- I'm sure it's going to confuse a lot of people initally until updated.  (react native rules!)


## freshteapot (27 Mar 2015)

Under option 2).

Where exactly is the 'main.jsbunle' meant to be saved.
Have tried.
- top level.
- inside "AwesomeProject.xcodeproj"

"You'll have to add the downloaded file to the app binary":
Where is this? :)


## brentvatne (27 Mar 2015)

@freshteapot - check this issue out: https://github.com/facebook/react-native/issues/240


## freshteapot (27 Mar 2015)

- Not the best approach, Issue #240 is clearer, however either approach requires you to run this everytime you want to see your changes. A trade off :) *
  To answer my own question:

"You'll have to add the downloaded file to the app binary":
Open Xcode -> Project (I will assume AwesomeProject)
Look in the folder Products, click "once" on 'AwesomeProject.app', look on the rightside and it should display "Full Path".

At this point, jump to the terminal, and navigate to the same directory.
Afterwards.

```
cd FULL_PATH
curl http://localhost:8081/index.ios.bundle -o main.jsbundle
```

Now at this point. You could do

```
curl http://localhost:8081/index.ios.bundle -o FULL_PATH/main.jsbundle
```

At this point, it works. At least on xcode 6.2.


## freshteapot (27 Mar 2015)

@brentvatne thank you!


## gu-fan (27 Mar 2015)

@freshteapot Thanks, It works~


## vjeux (01 Apr 2015)

Now possible


