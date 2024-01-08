#HystrixPlugins Bootstrapping Problem - Race Conditions

Owner: Netflix

Repo: Hystrix

Labels: bug enhancement 

## benjchristensen (06 Dec 2012)

Currently the HystrixPlugins implementation provides a single globally static mechanism for registering plugins. 

It's simple and easy to grok but also has non-obvious issues.

This is a new concern for Hystrix as internally at Netflix there was only ever the "default behavior" but now anything can be customized.

If a system starts up, uses some HystrixCommands and then registers new plugins, the commands executed before will have already initialized metrics, thread-pools, metrics publishers, properties and other such things expected to live for the live of the JVM. 

They are cached for a given HystrixCommandKey.

The simple answer is to just clear the caches and let everything initialize again.

That isn't quite so simple though because these aren't just objects that get garbage collected. 

We need shutdown hooks then for thread-pools and metrics publishers to unpublish and gracefully cleanup - things that typically don't ever happen.

The possible approaches I've thought of are:

1) Bootstrap with JVM properties

If properties were injected from the command-line (ie -Dhystrix.plugin.???=???) then we could guarantee that all HystrixCommand executions initialize with the correct values on system startup.

2) Lifecycle Hooks

All plugin implementations (including the defaults) could have shutdown hooks that get executed when another plugin is registered and the plugin implementation is responsible for tearing down any resources is allocated (thread-pools, metrics being published, etc).

3) IllegalStateExceptions to lock down

Allow "locking" the plugin config so that no further plugins can be registered and alter behavior.

If something tries it would get an IllegalStateException.

This would at least protect against unexpected race conditions or malicious overrides of behavior (a bad library for example).

Nothing has been done on this yet as none of the solutions are great so I'm thinking on it more and discussing with others.


## benjchristensen (07 Dec 2012)

I've been looking at Guice (and Dagger - thanks @adriancole for the tip) and think they may be useful (Netflix uses Guice internally as well).

I don't really want to make hystrix-core dependent on either of these though.

Also, both of these still require the "module" or config being defined either in the main method for application startup or other properties available before JVM initialization otherwise the first Hystrix invocation will start with defaults rather than overrides.

A possible two-pronged approach is:

1) Use System.getProperties() on first Hystrix invocation to look for overrides and use those if they exist. This would ensure a safe mechanism for apps without using Guice/Dagger etc to configure plugins and avoid race conditions of trying to do it in code before anything uses Hystrix.

2) Use javax.inject annotations in hystrix-core but by default not use Guice/Dagger/etc and let those be contrib modules that can be used by applications/environment who like to use them. Will that approach work?

Does anyone have suggestions for preferred means of bootstrapping override implementations of plugins?


## Randgalt (07 Dec 2012)

I'm not a fan of Dependency Injection frameworks (even though I just wrote an extension to one). I strongly feel that OSS projects should be neutral on this. DI containers can always be added to a project through factories/providers/etc. One possibility here is to make Hystrix core neutral by creating an interface for accessing plugins. You can then provide a hystrix-guice that has a Guice implementation, etc. Governator (https://github.com/Netflix/governator) can help if you want Guice + Lifecycle.


## benjchristensen (07 Dec 2012)

I agree with you on remaining neutral and want to avoid dependencies on any in hystrix-core.

I'll probably take up your offer on help for a hystrix-guice module.

I think I'm going to proceed with hystrix-core having a very low-level default support for System.getProperties to bootstrap plugin initialization and then allow applications to use DI frameworks if they wish to.


## codefromthecrypt (07 Dec 2012)

I'll look more into this over the weekend, but seems like a factory or fluent builder pattern could break this up enough to work with or without DI.  Probably need to get some more experience and show some code that could actually work with your caveats mentioned.


## benjchristensen (08 Dec 2012)

I too will play with it (hopefully over the weekend) and submit a pull request for review and to better communicate the issue.


## benjchristensen (18 Dec 2012)

I have added a pull request for this issue: https://github.com/Netflix/Hystrix/pull/56

It does two things:

1) Enforces once-and-only-once initialization of a plugin so a system can accidentally end up in a mixed state.
2) Adds ability to define plugins through System properties.

This means that a system can either use the HystrixPlugins.register\* methods in the application main() on startup or the system properties to define plugin overrides.

I'm still open to adding hooks to help with tools like Guice if it doesn't require making hystrix-core dependent on any specific DI framework.


## benjchristensen (18 Dec 2012)

I'll wait to merge this until sometime tomorrow afternoon (Pacific Time) to allow for feedback.


## benjchristensen (18 Dec 2012)

Merged commit: https://github.com/Netflix/Hystrix/commit/646bf0dee52b824dae0c1fab194c1f4ced918db6


