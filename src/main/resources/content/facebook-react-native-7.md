#What are the guidelines for exposing native APIs?

Owner: facebook

Repo: react-native

Labels: Resolution: Locked 

## gaearon (30 Jan 2015)

This is not an issue per se, but something that #4 got me thinking about.

There are often many ways to expose the same native API. How opinionated does React Native want to be? For example, Xamarin mirrors all available iOS SDK but has [a very specific and consistent API guidelines document](http://developer.xamarin.com/guides/ios/under_the_hood/api_design/) that explains how native Cocoa APIs are mapped to be C#-friendly. This means that, if you know how certain API is called in Objective C, you can more or less infer what it will be in Xamarin's world without consulting the docs.

With React Native, this gets even more interesting because we may need both imperative platform-bridging API (like [proposed `StatusBarIOS.setStyle`](https://github.com/facebook/react-native/issues/4#issuecomment-72254022)) as well as declarative React components sitting on top of such APIs, for example [`NavigatorIOS`](https://github.com/facebook/react-native/blob/master/Libraries/Components/Navigation/NavigatorIOS.ios.js).

Are there any guidelines we want to establish for such components? Are we just trying to make the best declarative wrappers possible for every platform's native APIs? How fast are we willing to make breaking changes to them? (Unlike React, we have a lot of public API here.)

I'm sorry if this is premature talk but I'd love to get some sense of the direction of this project.


## vjeux (31 Jan 2015)

> I'm sorry if this is premature talk but I'd love to get some sense of the direction of this project.

It's the best time to talk about the hard questions :)

Bridging APIs one-to-one is not trivial in React native for three big reasons:
- iOS/Android APIs are imperative but React is declarative
- iOS/Android APIs are synchronous but React native has an asynchronous bridge
- iOS/Android APIs are written in Obj-c and Java but React native is in JavaScript

So, it's actually non trivial to map 1 to 1 native apis.

So far, the process was to start with a use case that one of the app we are building needs to support. At this point, we look at all the possible ways to expose the API and try to find the best one. One really important point is that we start from a use case, and not take an existing API and just rush to expose it all.

The stance that we've taken so far is to try to make them as web/React friendly as possible. 

> How fast are we willing to make breaking changes to them? (Unlike React, we have a lot of public API here.)

This is why we want to do this private phase for now. We have never open sourced a project with such a crazy large API surface. 

My 5min thoughts on this is that what's in this github repo is going to lag in the APIs that it supports, but we have great extensibility tools that other people can fill the gap for APIs that we don't support yet.


## glenjamin (01 Feb 2015)

Would it be possible to support some generic FFI-like interface for interacting with the raw imperative native APIs?

I don't know that much about the target platforms, but something like that would reduce pressure for a "proper" react-native supported version of the API and allow userland to experiment with options before committing to a solution in core.

This is roughly how normal React deals with support for DOM stuff not explicitly exposed, and works very well in terms of not constraining the user.


## vjeux (01 Feb 2015)

@glenjamin: this is a good idea. So far we worked close with our few users so we've been able to build high quality custom apis for their use case, but that's unlikely to scale.

Right now my thinking was to allow people to write their own bindings via plugins. So if a feature is not yet supported, they can bridge it themselves. But something automatic may be a useful escape hatch as well.

The best way to know is to try it and see if it solves real use cases


## vjeux (07 Feb 2015)

Closing as this is a discussion and not super actionable :) Feel free to keep talking here


