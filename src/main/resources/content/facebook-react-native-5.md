#Auto-refresh simulator

Owner: facebook

Repo: react-native

Labels: Resolution: Locked 

## nick (30 Jan 2015)

Is it possible to have the simulator automatically refresh when a file is changed instead of Cmd-R?


## gaearon (31 Jan 2015)

By the way, I'm sorry if this is something you don't plan to consider, I totally understand that.

I'm just saying it's a shame we'll have a different "push code updates" system on React Native because, if it is implemented, I expect it to only support "refresh" signals, whereas an existing Webpack solution supports [Hot Module Replacement](http://webpack.github.io/docs/hot-module-replacement.html). Hot Module Replacement is vastly superior to a "refresh" signal system but supports it as a degraded simple case, so it's usable from day one.

[Many React developers](https://github.com/gaearon/react-hot-loader/stargazers) consider hot replacement useful. It's not trivial to implement, so if we're not reusing Webpack's HMR runtime, we likely won't get it soon..


## amasad (31 Jan 2015)

I think we want to be flexible enough for users to make their own choices and maybe support some of the popular things out of the box. I'm on mobile now but I'll reply later with some of the constraints we had when building this and why we couldn't pick one of the ready made solutions.


## gaearon (31 Jan 2015)

Thank you, appreciated!

It would be awesome if you exposed a way to reload a page and a way to "inject a script" to JS side. Then we'd be able to use any server and any (hot or not) reloading solution.. I think.


## amasad (01 Feb 2015)

In addition to having a proprietary module format, and because we have a large codebase and needed to use watchman for high performance and more reliable file watching, performance was a big concern. We needed the compiler to work in parallel and we [even hand optimized source map generation](https://github.com/facebook/react-native/blob/master/packager/react-packager/src/Packager/Package.js#L66-L108) so we can give error stacks on the client with correct line numbers. Maybe everything can be written as a plugin to webpack  but at what point you're reimplementing the program you're supposed to be plugging into. Finally, our vision to what our server should be doing is beyond packaging. We want to integrate Jest testing, Flow type checking, code optimization (dead code elimination, inlining etc).

That shouldn't mean that external react-native users shouldn't or can't use webpack, or for that matter shouldn't replace any of the things I mentioned. You should be able to use Mocha instead of Jest, or 6to5 instead of jstransform etc.

Anyways things are still pretty much work in progress, and feedback like this helps. Thanks


## ykagan (01 Feb 2015)

I'm curious if you guys think it would help for us to provide support for webpack's loader and plugin API out of the box for our packaging system? 

That would give us access to most of webpack's module ecosystem while still giving us flexibility to implement features that webpack may not be designed for - particularly features around testing, code intelligence, flow integration, etc.


## gaearon (01 Feb 2015)

I'm sure Webpack's plugin API would be very hard (it's very tied to how Webpack processes modules, down to compilation phases) so it's probably entirely out of the question.

Implementing Webpack loader support is easier, but only for trivial things like transforming files. It's a lot more work for complex loader features (pitching loaders, async transformers, marking dependencies, require.resolve, etc). RN packager could implement a subset of those, but it is not worth it in my opinion, as it would only cause fragmentation in Webpack ecosystem (is that particular loader supported by RN?) and I doubt RN team wants to be constrained by Webpack APIs.

A more sustainable alternative, in my opinion, is to let users choose a bundler, and add whatever polyfills/bridges we may need for Webpack to work with RN:
- `WebSocket` API or equivalent
- An equivalent of `window.reload()`
- An equivalent of loading `<script>` tag dynamically

I really wish @sokra was with us in this discussion!


## gaearon (02 Feb 2015)

@sokra I heard you have access to this repo now, care to weigh in?


## sokra (02 Feb 2015)

Hi,

it looks like react-native just expects a URL to the bundle file, which is provided by the packager. Technically the packager could be replaced with webpack.

react-native also uses a non-standard way to append the SourceMap to the source (`RAW_SOURCE_MAP = ...` is appended). This could be implemented as webpack plugin. Or react-native could be changed to use the standard `//#sourceMappingURL` (`errorToString` may need to be changed to be async, because it need to fetch the SourceMap from the server).

I also think it's better to use webpack, because the current packager may only support a subset of npm modules (there are really weird modules that need to be parsed).

So until now, nothing complex...

For Code Splitting and Hot Module Replacement we need something to eval a javascript from a remote URL. Best is when react-native exposes a way to do that. i. e. `importScripts` like in a WebWorker or even better something async.

Hot Module Replacement also need to do a full reload of the app. This need to be accessible from the js side. (Looks like this is currently not possible)

If we got this, Hot Module Replacement would work, but only with polling... For a live experience we need a server to react-native connection, i. e. a WebSocket (Or we could use long polling).

The version from reactconf seem to have at least something for server to react-native notification, because saving causes a reload in the video...


## gaearon (02 Feb 2015)

> The version from reactconf seem to have at least something for server to react-native notification, because saving causes a reload in the video...

I think an earlier comment by @amasad says that:

> The current solution that we have is that the client keeps polling the package and checking for changes.


## amasad (04 Feb 2015)

@sokra @gaearon So initial experimentation looks good. However, like I expected, perf is the biggest concern:
- It takes ~45 seconds to build our bundle.
- It takes ~8 seconds to rebuild anytime a file is changed (with sourcemaps, without it's very fast)

Here is what I want:
- I want loaders to run in parallel so that initial build doesn't take forever. I can do it in the loader code, when I get a request I send it to my worker farm. Is this the best way to do it?
- Combining source maps and inlining it in the file (which is what we need to do) adds too much overhead. What we do in our packager is we hand build the sourcemap after creating the bundle which ends up being incredibly fast. How do we do that in Webpack? is there a way to hook into the last step of the bundle creation?
- Is there a way to cache loader results on disk so on startup we won't have to build the whole thing? We just stat the files to see if they changed.


## jlongster (04 Feb 2015)

Just to chime in, I would <3 to see this collaboration happen, since I've experienced similar perf problems. Makes me so happy to see a big company like facebook drive an existing project to be better :) (though I totally get why you did your own in the first place)


## sokra (04 Feb 2015)

> I want loaders to run in parallel so that initial build doesn't take forever. I can do it in the loader code, when I get a request I send it to my worker farm. Is this the best way to do it?

I had the feature that loaders can run in a separate process in webpack 0.6 or so, but spawning a separate processes was pretty expensive and sending source code between processes too. Now better alternatives envolved in the node.js community like https://github.com/xk/node-threads-a-gogo and we could try it again with threads.

> It takes ~8 seconds to rebuild anytime a file is changed (with sourcemaps, without it's very fast)
> 
> Combining source maps and inlining it in the file (which is what we need to do) adds too much overhead. What we do in our packager is we hand build the sourcemap after creating the bundle which ends up being incredibly fast. How do we do that in Webpack? is there a way to hook into the last step of the bundle creation?

Yeah... (high quality) SourceMaps are pretty slow. They are complex and the `source-map` lib is slow.

The current implementation in react-native is faster, because it only maps lines 1 to 1 (note: the jsx transformer keeps line numbers)... Maybe a similar alternative SourceMap devtool could be added to webpack. (More people would like that)

webpack also use some cools tricks to get faster SourceMaps:
- `devtool: "eval-source-map"` Wraps every module in `eval("... //#sourceMappingURL=data:...")`. So we can create the SourceMap once and cache the result for recompilations.
- `devtool: "eval"` Wrapps every module in `eval("... //#sourceURL=...")`. This doesn't show original sources, but at least give the module a proper name. (That's really fast.)

Of couse this is not supported by react-native currently...


## sokra (04 Feb 2015)

> Is there a way to cache loader results on disk so on startup we won't have to build the whole thing? We just stat the files to see if they changed.

https://github.com/webpack/webpack/issues/250


## amasad (04 Feb 2015)

> Now better alternatives envolved in the node.js community like https://github.com/xk/node-threads-a-gogo and we could try it again with threads.

Doesn't look like this project is actively supported. And looks like there is no windows support. Not that we currently support windows but we want to be able to.

node-worker-farm works really well and super easy to manage. https://github.com/rvagg/node-worker-farm

> Maybe a similar alternative SourceMap devtool could be added to webpack. (More people would like that)

Is source map devtool currently pluggable? Is there documentation on how to write the plugin?

> webpack/webpack#250

Cool. It seems that you want to be able to cache your entire state? But I can say from experience that caching just the transformation (loader results in webpack lingo) should get you a lot of the benefits.


## gaearon (04 Feb 2015)

> Is source map devtool currently pluggable? Is there documentation on how to write the plugin?

Yeah, absolutely, it's pluggable. Here's the default `SourceMapDevToolPlugin`:

https://github.com/webpack/webpack/blob/master/lib/SourceMapDevToolPlugin.js

Here's Webpack plugin API:

http://webpack.github.io/docs/plugins.html

You can remove `devtool` from config and instead put your plugin in `plugins`.


## frantic (06 Feb 2015)

> Or react-native could be changed to use the standard //#sourceMappingURL (errorToString may need to be changed to be async, because it need to fetch the SourceMap from the server).

Actually we started with this approach, but then tried including source maps inline and it turned out to be a better dev experience.


## sokra (07 Apr 2015)

@amasad I used the easter holydays to implement new SourceMaps for webpack. `devtool: "cheap-inline-source-map"` should be much faster (or `devtool: "cheap-module-inline-source-map"` if you want source mappings to jsx). Still experimental, but you can try it.

http://webpack.github.io/docs/configuration.html#devtool

It uses a similar approach as react-native: it only maps lines and avoids recomputing base64vlc numbers.


## pilwon (07 Apr 2015)

Thanks @sokra! :+1::+1::+1:

We are not yet able to process all `react-native` project files with `webpack` because [MessageQueue module uses dynamic require](https://github.com/facebook/react-native/issues/395#issuecomment-87723931) in a way not supported by `webpack` at the moment. The fact that all `react-native` modules internally use flattened module names (ex: `require('ModuleName')`) makes it even trickier (or impossible) to hack with `ContextReplacementPlugin`. A hybrid approach (using `react-native's packager` for `node_modules/react-native/**/*` and `webpack` for the rest) is one way to use `webpack` but we cannot test the new `cheap-inline-source-map` devtool option with that approach because `packager` only cares about the code, not the source map.

I believe we need more powerful way to handle dynamic requires in `webpack` that works with aliased module names in order to process all files with `webpack` unless `react-native` changes its code to support `webpack`.


## amasad (07 Apr 2015)

Thanks @sokra. What @pilwon said about how we're not ready to completely switch to a standard webpack. But I have some ideas on how we can integrate webpack as a frontend and have our packager be plugged in as loader and resolver. Anyways, what you did should come in handy


## vjeux (07 Apr 2015)

The way we currently communicate for Obj-C to JavaScript is by doing `eval('require("ModuleName").methodName(arguments)');`. Fortunately, most of it is abstracted away in only 7 methods. What we need to do is to change the call to `eval('ReactNativeJSExposedMethods.call('ModuleName', 'MethodName', arguments)')` and have all those methods register themselves before being called.

```
RCTEventEmitter.receiveTouches
RCTNativeAppEventEmitter.emit
RCTEventEmitter.receiveEvent
ReactIOS.unmountComponentAtNodeAndRemoveContainer
AppRegistry.runApplication
RCTJSTimers.callTimers
RCTDeviceEventEmitter.emit
```

This is on my todo list but I'm unlikely going to be able to get to it this week if someone wants to pick it up


