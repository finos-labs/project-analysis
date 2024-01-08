#How do I run the packager for a new project?

Owner: facebook

Repo: react-native

Labels: Resolution: Locked 

## jlongster (01 Feb 2015)

I created a brand new project, added the required files for ReactKit, and got all that working. Now I just need to run the packager. In `packager/packager.js` I see:

``` js
if (!options.projectRoot) {
  options.projectRoot = path.resolve(__dirname, '..');
}
```

But options is parsed from the command line arguments and I don't see the `projectRoot` command line arg. How do I run it, setting the path to my new project?

I temporarily fixed it by hardcoding the `projectRoot` to my project in there. A side issue: I was surprised to find that the bundler didn't seem to load files from that `node_modules` where I ran the packager (the error was pretty obscure). I needed to copy the `node_modules` directory into my new project also.


## amasad (01 Feb 2015)

@frantic do you have steps for new project creation?

The packager currently only looks in the project root because our @providesModule format forces us to build a dependency graph ahead of time by reading files. Otherwise we'll end up reading your entire drive. There are probably ways around that. I'll look into this tomorrow make sure it works as expected 


## frantic (02 Feb 2015)

> But options is parsed from the command line arguments and I don't see the projectRoot command line arg.

Oh, I missed that when syncing changes to github repo :)

We don't have a good story for creating new apps yet, but imho would be great if all you needed was `npm install react-native` + packages that your app depends on, and run `npm exec devserver` to start packager


## jlongster (02 Feb 2015)

> We don't have a good story for creating new apps yet, but imho would be great if all you needed was npm install react-native + packages that your app depends on, and run npm exec devserver to start packager

That's definitely the ideal goal. This & showing how to integrate with other bundlers like webpack would be great.


## sjmueller (05 Feb 2015)

@frantic, even if the story for new project creation is not there yet, could you still share the manual steps needed to setup a fresh new project?  It's been harder than expected, and we have fallen back to just copying one of the examples and hacking away within the cloned repo.


## jlongster (05 Feb 2015)

@sjmueller Personally I didn't find it too hard, except for not being able to set the project root of the packager. If I remember right:
- Import ReactKit.xcodeproj into your project
- Link to libReactKit.a in the link phase
- Make the `RCTRootView` in your delegate, like:

```
    RCTRootView *rootView = [[RCTRootView alloc] initWithFrame:[[UIScreen mainScreen] bounds]];
    rootView.scriptURL = jsCodeLocation;
    rootView.moduleName = @"App";
```

And just append it as a subview where you need it
- Make a `js` folder in your project
- Change the root project url in packager/packager.js to your `js` folder
- Copy the package.json from the root of `react-native` into `js` and run `npm install`
- Copy the `Libraries` folder from `react-native` into your project

The last steps are the annoying ones, but I'm sure that'll be fixed soon. I may have forgotten something. (definitely need to write this down in docs)


## frantic (06 Feb 2015)

Thanks @jlongster!  @sjmueller If you don't have iOS experience, it's easier to just copy the example app and change it's name


## sjmueller (06 Feb 2015)

Much appreciated, @jlongster!


## vjeux (07 Feb 2015)

Closing as it's not very actionable. We definitely want to improve this new project creation experience.


## sjmueller (13 Feb 2015)

I went through the process of creating a new Swift v1.2 project from scratch with Xcode 6.3 beta, and preparing it for use with react-native.  I know the end goal is to improve the experience so that this is trivial, but for the time being, here are the steps if anyone is curious:
1. Create a new swift project. I generated mine with Xcode 6.3-beta as _Single View Application_.
2. Copy `packager`, `libraries`, and `reactkit` folders from react-native.  Copy over `.gitignore` as well, or create one yourself.
3. Import ReactKit as a subproject.  The easiest way is to drag `ReactKit/ReactKit.xcodeproj` from finder into the Project navigator in your newly created Xcode project.
4. Link ReactKit library to build with main binary. This is done by selecting the main project in Xcode, choosing the _Build Phases_ tab, and adding a new framework under the _Link Binary With Libraries_ group.  Choose `libReactKit.a`, as Required.
5. Add bridging header to use ReactKit in swift.  You can do this by adding an temporary ObjC file to your project, and Xcode will prompt you asking if you want to create a Bridging Header.  You can then delete the temp file, and continue to use the newly created `[app]-bridging-header.h` file.
6. Add all the headers you plan to use from ReactKit into your [app]-bridging-header.  I added all of them, since there are some interesting errors if you don't get the dependencies right.  I bet there's an easier way to do this, but if not just let me know and I can post my bridging-header.
7. In order for the headers inside ReactKit to be found, you must add the
   project as a search path.  Select main project, choose _Build Settings_
   tab.  Under _Search Paths_, add a new entry _Header Search Paths_:
   `$(PROJECT_DIR)/ReactKit`, recursive.
8. Initialize app with RCTRootView, set js bundle location.  I used the following code in `AppDelegate.swift`:
   
   ```
   func application(application: UIApplication, didFinishLaunchingWithOptions launchOptions: [NSObject: AnyObject]?) -> Bool {
   
       let jsCodeLocation = NSURL(string: "http:localhost:8081/Cards/CardsApp.includeRequire.runModule.bundle"),
       rootView = RCTRootView(),
       rootViewController = UIViewController()
   
       rootView.scriptURL = jsCodeLocation
       rootView.moduleName = "CardsApp"
   
       self.window = UIWindow(frame: UIScreen.mainScreen().bounds)
       rootViewController.view = rootView
       self.window!.rootViewController = rootViewController
       self.window!.makeKeyAndVisible()
   
       return true
   }
   ```
9. Delete unused default storyboard, viewcontroller under your [app] folder, since they aren't being used.
10. Modify `Info.plist` to remove storyboard entry:
    
    ```
    -      <key>UIMainStoryboardFile</key>
    -      <string>Main</string>
    ```
11. Add `-all_load` linker flag to pick up ReactKit objc category methods.  Otherwise you'll receive errors like `unrecognized selector sent to class` when trying to use `RCTRootView`. On your main project, select Build Settings tab, Linking -> Other Linker Flags: Add `-all_load`.  I've read that there are more optimal ways to do this, but good enough for now.
12. Add main entry point to js application as `[AppName]App.js`.  Just for starters you can use something like the code below (check out the tictactoe app for a full example):
    
    ```
    var App = React.createClass({
        render() {
            return (
                <View style={styles.container}>
                    <Text style={styles.title}>My App</Text>
                </View>
            );
        }
    });
    ```

Perhaps there are some steps that are extraneous, and a few obviously won't apply to a pure ObjC project.  Hope this helps.


## boopathi (13 Feb 2015)

@sjmueller Thanks a lot. I was stuck because of not adding all headers to `bridging-header` for quite a while, and I would suggest this to go into the docs as well (or) to `Examples/SwiftApp`

And instead of copying files around, you can open `packager/packager.js` and add your project directory to  `options.projectRoots`. #64 would fix it where you could run 

``` sh
./packager/packager.sh --root ~/workspace/Cards
```

and your `jsCodeLocation` would be `http://localhost:8081/CardsApp.includeRequire.runModule.bundle` where `CardsApp.js` is the entry point.


