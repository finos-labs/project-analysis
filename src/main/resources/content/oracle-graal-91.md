#Refactor/cleanup and document stack trace API in Truffle

Owner: oracle

Repo: graal

Labels: oracle-emp truffle 

## chumer (03 Mar 2016)

We should revisit and document the Truffle stack walking API.
The stack trace API consists of the following elements:
- class FrameInstance
- class FrameInstance.FrameAccess
- class FrameInstanceVisitor
- method TruffleRuntime#getCurrentFrame()
- method TruffleRuntime#getCallerFrame()
- method TruffleRuntime#iterateFrames(FrameInstanceVisitor)

The following things should be considered:

A) The naming in FrameInstance and FrameInstanceVisitor might be misleading, as they are not just containing a frame but also the CallTarget and the CallNode which is usually called a stack frame in other APIs. So the more established StackFrameElement could be used instead.
A new API could look like:
- T TruffleRuntime#iterateStackFrameElements(int skipElements, StackElementVisitor<T> visitor)
- StackElement TruffleRuntime#peekStackFrameElement(int skipElements)
  Please feel free to comment on that.

B) Add javadoc to the API. ASCII art similar to below could be useful.

C) FrameInstance#isVirtualFrame is currently never true in the default runtime. Its true for the topmost frame in the Graal runtime. @lukasstadler Maybe you can comment what the use-case is for this method?

D) In FrameInstance#getFrame(FrameAccess, boolean slowPath) whats the purpose of slowpath? @lukasstadler Do you want to comment on that? Maybe we can solve this in a different way by checking a CompilerDirective in graal?

E) We should revisit the question of what FrameInstance#getCallNode() should return.
First grouping option (current state):

```
                    ===============
getCurrentFrame(): |  CallTarget   | FrameInstance
                    ===============
getCallerFrame():  |  CallNode     | FrameInstance
                   |  CallTarget   |
                    ===============
                   |  CallNode     | FrameInstance
                   |  CallTarget   |
                    ===============
                         ...
                    ===============
                   |  CallNode     | FrameInstance
Initial call:      |  CallTarget   |
                    ===============
```

Second grouping option:

```
                    ===============
getCurrentFrame(): |  CallTarget   | FrameInstance
                   |  CallNode     | 
                    ===============
getCallerFrame():  |  CallTarget   | FrameInstance
                   |  CallNode     | 
                    ===============
                   |  CallTarget   | FrameInstance
                   |  CallNode     | 
                    ===============
                         ...
                    ===============
Initial call:      |  CallTarget   |
                    ===============
```

As far as I understand the first has the advantage that you can easily find the current line number where execution is currently at for the frame. And the second option would have the advantage that you find out how you were called (some languages tend to print stack traces with a called-as note).


## chumer (03 Mar 2016)

F) Handle the case when FrameInstance instances would be accessed from multiple threads. In other works make the API thread-safe or document where it is not.


## chrisseaton (04 Mar 2016)

@chumer asked me to describe how the frame API is used is Ruby.

#### Backtraces

When we create an exception we use `iterateFrames` to create a Ruby backtrace object. To do this we need the `CallNode` and some information from the frame arguments to correctly form the standard Ruby backtrace. This is all read only.

Our backtraces are (modulo a couple of bugs) perfect recreations of standard Ruby, which is a great feature.

People will do things that throw exceptions in inner loops. In one particular pattern we can statically detect that the backtrace isn't going to be needed so we avoid creating it.

``` ruby
Integer("not an integer") rescue default_value
```

Here rescue is the exception handling part and we can see the exception object isn't used so we can avoid creating the backtrace. We do this by setting a thread-local flag.

https://github.com/jruby/jruby/blob/fd0865a359bfc0f4388238410b72245eac82f88b/truffle/src/main/java/org/jruby/truffle/language/CallStackManager.java#L126-L202
https://github.com/jruby/jruby/blob/4892ade624bbe88bbb33cc694ed98a84c9d28f5b/truffle/src/main/java/org/jruby/truffle/language/backtrace/Activation.java#L15-L36
https://github.com/jruby/jruby/blob/0b79bf17418523b528a8cc84e09821e697266f16/truffle/src/main/java/org/jruby/truffle/language/exceptions/DisablingBacktracesNode.java#L17-L17

#### Bindings

Ruby lets you turn a frame into an object, called a `Binding`, which obviously just contains a `MaterializedFrame`. You can get the current binding, which is just `virtualFrame.materialize()`, and seems to optimise and re-virtualize extremely well.

The tricker case is when we need the binding from the caller frame. Here we need to use `iterateFrames`. Before `iterateFrames` switched behaviour we needed to normally look one frame up - now it's two frames to ignore the current frame.

This is unfortunately complicated by the fact that calling a method via `send` rather than a normal call ignores the `send` method, and so we need to go one further when we iterate to find the caller frame. This is our `getCallerFrameIgnoringSend` method, which tests each frame to see if it should be ignored.

In some cases we've started to manually pass the materialised caller frame, from the calling method, as another argument. This is a bit awkward but seems to re-virtualise well. Ideally, using `iterateFrames` would be as efficient as passing the caller frame manually.

https://github.com/jruby/jruby/blob/fd0865a359bfc0f4388238410b72245eac82f88b/truffle/src/main/java/org/jruby/truffle/language/CallStackManager.java#L43-L67
https://github.com/jruby/jruby/blob/fbf5e53559fa43c3b5b8f10cb6a17058d416da0d/truffle/src/main/java/org/jruby/truffle/core/kernel/KernelNodes.java#L322-L326
https://github.com/jruby/jruby/blob/fbf5e53559fa43c3b5b8f10cb6a17058d416da0d/truffle/src/main/java/org/jruby/truffle/core/kernel/KernelNodes.java#L337-L341

#### Accessing the calling frame

Some Ruby methods return a result, but also set a local variable in the calling frame with the result. For example `gets` returns the string read from the terminal, but also sets the local (it looks like a global but it's really a thread and frame local) variable `$_` in the calling frame.

This is similar to the problem above with bindings, but we don't need to materialise the frame. We currently use the same trick with passing a materialised frame though, as anything else would be a `!TruffleBoundary`.

https://github.com/jruby/jruby/blob/fbf5e53559fa43c3b5b8f10cb6a17058d416da0d/truffle/src/main/java/org/jruby/truffle/core/kernel/KernelNodes.java#L848-L879

#### Finding out who called the current method

Some times we need to know which method called the current method. This is usually just for reporting errors. It's the same process as getting the caller frame, and also needs to ignore `send`.

https://github.com/jruby/jruby/blob/fbf5e53559fa43c3b5b8f10cb6a17058d416da0d/truffle/src/main/java/org/jruby/truffle/core/kernel/KernelNodes.java#L362-L362

#### Other things we do

We have an optional backtrace format which shows the lines of the Java stacktrace interleaved with the Ruby backtrace. It looks like this:

https://gist.github.com/chrisseaton/7d8a648444ddff4cec21

This is a bit of a hack as we have to parse the Java callstack strings and detect the methods that we know are involved in calls in both Truffle and Graal.

We have an option for limiting the size of the backtrace stored when we do things like run specs where you only ever care about a few lines.

#### Crazy ideas

Could we do some kind of lazy backtraces, where the information for them isn't read off the stack until it's actually needed, or until the frames are popped where the information would be lost? That way generating a full-backtrace for an exception that you catch a couple of frames down would never need to consider all the frames below it. This would mean we didn't need the option to limit backtrace sizes.

To elegantly solve all our problems what we'd really like would be non-boundary, re-virtualising, loop-explodable access to the nth frame. But I understand that may not be easy!

Both of these would allow us to drop things like our optimisation to not create backtraces when they aren't needed, as they would escape-analyse away, and the non-boundary access to the nth frame would mean we wouldn't have to pass caller frames as arguments.

@pitr-ch @eregon @nirvdrum have I missed anything out?


## entlicher (07 Mar 2016)

E) For the debugger it's comfortable that the CallNode in every FrameInstance corresponds to the code in the given frame. We show the CallNode's source section as the place where the given FrameInstance "is", therefore the current state is satisfactory for us.


## eregon (07 Mar 2016)

A) StackFrameElement sounds good to me and clearer than FrameInstance.
`peekStackFrameElement(int skipElements)`: seems there are not many uses cases when we know in advance how many `skipElements`, so I think currentFrame + callerFrame + iterate is good enough.
B) Definitely, the doc there is rather old and worth revisiting.
D) If it cannot be inferred, it would be an improvement to make it an `enum`, there have been many confusion about it in the past.
E) Both seem useful, I prefer the second one as it's more natural to me (the CallNode is the call site) since then the `StackFrameElement` can be considered immutable (and not with a mutable CallNode). In my short experience, both approaches have pros/cons to display backtraces. So I guess let's stick to the existing one unless we have a strong motivation to change.


## pniederw (02 May 2016)

`StackFrameElement` does not make sense to me. Did you mean `StackTraceElement`?


## chumer (03 Apr 2018)

The documentation in FrameInstance was updated.
We have introduced TruffleException and TruffleStackTraceElement in 0.27. They are separate representations of the stack trace (the frame is immutable).
We have introduced RootNode#isCaptureFramesForTrace is 0.31 to allow languages to specify whether the frame needs to be captured to produce the right stack trace.
Back traces are no longer constructed eagerly, but they are filled in when they are thrown through a CallTarget. https://github.com/oracle/graal/blob/4c6c1754d7f49dbb2cd28a9f74ebe355a2f2614e/truffle/src/com.oracle.truffle.api/src/com/oracle/truffle/api/TruffleStackTrace.java#L226

Please open a separate issue if you still feel there is something missing.


