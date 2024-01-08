#Need a way to query if a line has a tag in the instrumentation API

Owner: oracle

Repo: graal

Labels: feature oracle-emp 

## chrisseaton (20 Feb 2016)

Ruby has a built-in coverage tool. It returns an array of values for each file, one value per line. If that line had code on it but wasn't executed the value is 0. If it didn't have any code on it anyway then the value is `nil`. I'm not sure how to recreate this distinction between no-code and never-executed in the new instrumentation API.

I'm thinking that one solution is to be able to run a query something like this:

``` java
boolean hasCode = instrumenter.doesExist(SourceSectionFilter.newBuilder()
    .sourceIs(source).lineIs(line).tagIs(LINE_TAG).build())
```

But I am of course open to any other idea that achieves the same result. I've got tests that fail until I can solve this.

I know that laziness makes it a tricky problem.


## smarr (20 Feb 2016)

Hm, I only have a partial overview of the system, but I am not aware of something existing.
My naive approach would be custom built.

I already keep a [registry](https://github.com/smarr/SOMns/blob/feature/dynamic-metrics/src/som/compiler/SourcecodeCompiler.java#L46) of source sections for each `Source` object in my parser. Based on that, I could build a map of lines with/without executable or generally code elements.

IMHO, it would be nice if that would be part of the system. I think it would be useful for many tools to track source sections for each `Source` object, even if they don't end up in the AST.

/cc @mlvdv


## chrisseaton (20 Feb 2016)

I gave that a go, and I can keep a `BitSet` of whether or not I've added the coverage tag to a given line. That works and is pretty low overhead (one bit per line), but it's still a duplication of data that the instrumentation API already has. It's also (a tiny bit of) extra work up front, which could be deferred until coverage is actually used if I could query.


## chumer (20 Feb 2016)

@chrisseaton 
I guess a SourceListener won't help you because you would need to store the state in the bitset again, right?

What should trigger "exist" for your use-case. First execution or CallTarget creation?


## chrisseaton (20 Feb 2016)

Yes a `SourceListener` wouldn't help as I'm trying to see if there's way to not store the information in a side data structure when I think it's already being stored by Truffle.

`CallTarget` creation, I'm afraid, which I understand is the more difficult for you to implement.

Is being able to eagerly evaluate a query not something that you want in the API for some particular reason? Does it expose too much, or just you'd rather not add yet another interface?


## chumer (21 Feb 2016)

`CallTarget` creation is not much worse (before CallTarget creation would be quite hard). At the moment I store only executed call targets because we only have execution listeners. I would need to store all CallTargets instead. But I can still just traverse executed ones for execution listeners. So I think performance would not be significantly worse for execution listeners attachement with this additional feature.

No problem with eager evaluation. One has to be careful though it might not be fast.

I'd like to generalize the API a bit. What do you think of:

``` java
List<SourceSection> Instrumenter#querySourceSections(SourceSectionFilter filter, int resultLimit)
```


## chrisseaton (21 Feb 2016)

Very slow is fine. I only need this for when a coverage report is being generated. That API looks good.


## mlvdv (23 Feb 2016)

This sounds about right for the Debugger's requirements in this area, discussed previously.  CallTarget creation is the right time, not execution.  Feedback about whether a breakpoint corresponds to a "known" (i.e. loaded) source location is important before execution.

@smarr Agree that we should add features like the one you have implemented; that's been part of my intention all along:  a library of useful, reusable components for tool builders that are only loaded/used when requested.  My old instrumentation library had a map that, once registered, automatically maintained a map of locations according to some specification.  


## mlvdv (07 Mar 2016)

Reminder that some Debugger functionality is broken until this functionality is restored.


## chumer (18 Jul 2016)

The instrumentation framework now allows to you source listeners: https://github.com/graalvm/truffle/blob/fbb6bb30803df787c07b1c8131789c94acfc2761/truffle/com.oracle.truffle.api.instrumentation/src/com/oracle/truffle/api/instrumentation/Instrumenter.java


