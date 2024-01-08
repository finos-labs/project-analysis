#unrecognized selector `setReactTag`

Owner: facebook

Repo: react-native

Labels: Resolution: Locked 

## jlongster (02 Feb 2015)

I'm trying to integrate this into an existing project. I successfully did this with a project generated from a template from XCode, so not sure what's going on.

I added ReactKit, linked with it, and started the packager server pointing to my JS directory. The app compiles fine, but when it runs it throws an exception: "[RCTRootView setReactTag:] unrecognized selector instance sent to instance". The thing is, when I look at `RCTRootView`, I don't see that method implemented anywhere, but it does have `self.reactTag = ...` in the `setUp` method. See the screenshot.

Any help debugging what I'm doing wrong?

![screen shot 2015-02-01 at 10 12 26 pm](https://cloud.githubusercontent.com/assets/17031/5995396/cbfafc06-aa5f-11e4-81de-dcee57c197d1.png)

EDIT: also, the only thing I added in my `didFinishLaunchingWithOptions` method was `RCTRootView *rootView = [[RCTRootView alloc] initWithFrame:vc.view.bounds];`, which triggers this.


## vjeux (02 Feb 2015)

cc @a2, @nicklockwood


## sophiebits (02 Feb 2015)

It's implemented here as a category on UIView:

https://github.com/facebook/react-native/blob/0512e23ba9984b8f19d9d3932548d64311d30e39/ReactKit/Views/UIView%2BReactKit.m#L16

Perhaps you missed that one when adding files to your project?


## jlongster (02 Feb 2015)

Oh, I didn't know about categories. So that adds it to all UIViews?

I added the entire ReactKit.xcodeproj to my project, which worked in another project! I will take a look closer though and try to figure it out.


## sophiebits (02 Feb 2015)

Yup, it should.


## jlongster (02 Feb 2015)

I got it working. I had to add `@executable_path/Frameworks` to the "Runpath Search Paths". I forgot that I did that on my last project.

There are bunch of small things like that get a fresh project working. I should write them down.


