#Set UIStatusBarStyleLightContent

Owner: facebook

Repo: react-native

Labels: Resolution: Locked 

## nick (30 Jan 2015)

Is it possible to set the status bar to light? I'm working on a dark background.

Also, is this the right place to ask questions like this?


## nick (30 Jan 2015)

`StatusBarIOS` sounds great :)


## vjeux (30 Jan 2015)

@nick: want to give a shot at implementing it?


## amccloud (30 Jan 2015)

I also like StatusBarIOS. Trying to implement it now to learn the internals of react-native.


## nick (30 Jan 2015)

@vjeux Unfortunately I have no experience whatsoever with iOS or Objective C otherwise I'd be happy to help. @amccloud good luck - would love to give it a try if you succeed


## vjeux (30 Jan 2015)

@nick: gotta start somewhere? :p


## nick (30 Jan 2015)

@vjeux haha you're right I should dive in. Right now I'm working on a clone of the iOS weather app to see how far I can get it. Looking good so far! My fork is at nick/react-native - when I'm a bit further along perhaps we could use it as another example.

![screen shot 2015-01-30 at 1 21 27 pm](https://cloud.githubusercontent.com/assets/939/5982695/f9cb0d9e-a882-11e4-9871-c297dd3e66f0.png)


## vjeux (30 Jan 2015)

Oh, that's pretty cool already!


## nick (30 Jan 2015)

Yup no prior iOS experience and about 3 hours work I got it to that. It's using https://github.com/erikflowers/weather-icons for the icons. Looking forward to seeing how far I can push it


## jamesgpearce (30 Jan 2015)

@nick no regrets about giving you access :) - demo looks sick!


## subtleGradient (30 Jan 2015)

Hey nick! Long time no see.
FWIW, when I wanted to change the status bar style of my app, I did it in the Info.plist


## amccloud (30 Jan 2015)

I've pushed my initial work for StatusBarIOS. @vjeux the only issue I seem to run into is the type for attributes coming across the bridge. For example my `BOOL` values in `RCTStatusBarManager` are always `NO` or `false`.


## vjeux (30 Jan 2015)

Wow, so fast! Looking


## nick (30 Jan 2015)

I merged in @amccloud 's change to my fork and its working great :-)


## vjeux (31 Jan 2015)

@amccloud do you mind doing a pull request on this repo?


## amccloud (31 Jan 2015)

Yes. That is my intention. I just want to fix the issue with the BOOL values first. 


## vjeux (31 Jan 2015)

@a2 @nicklockwood do you know what's going on with the BOOL value?


## a2 (31 Jan 2015)

Just catching up on email. I [commented](https://github.com/amccloud/react-native/commit/2aae16dbd5a352d7c85022e5c584bf407e0e57f2#commitcomment-9526849) on why that's not working. Hopefully that helps. :) 


## nick (31 Jan 2015)

@amccloud @vjeux do you think I can use the same method to expose a location API? Trying to get location for my Weather app and was thinking of using this code as a template.


## vjeux (31 Jan 2015)

@nick: yeah, integrations with the native apis will follow a similar pattern.

It turns out that we already have the location api implemented internally but haven't yet had the time to put it on the open source version. Give us some time on this (you can expose it for yourself in the meantime, shouldn't be super hard)


## vjeux (07 Feb 2015)

Was added https://github.com/facebook/react-native/commit/9674c99a33b33ad6de6c742a514e2602a521dbfb :)


