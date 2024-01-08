#Should TypeSystem#value have a default value?

Owner: oracle

Repo: graal

Labels: oracle-emp 

## chrisseaton (14 Feb 2016)

Isn't it the case that the list of types used in the DSL type system is largely redundant now? So at least should it be given a default value of `{}` so that we can write:

``` java
@TypeSystem
```

instead of

``` java
@TypeSystem({})
```


## chumer (14 Feb 2016)

Agreed. This is an artifact from a time where types in the type system where mandatory. Feel free to push the change (should not require a change in the processor). Otherwise I can push it with my next Truffle changeset.


## smarr (14 Feb 2016)

Just wondering, what is the concrete use of `@TypeSystem` without a list of times?

From the javadoc, I would assume it helps the DSL to keep specializations of different node hierarchies separated.


## chrisseaton (14 Feb 2016)

`@ImplicitCast`?


## smarr (14 Feb 2016)

You mean the `@TypeSystem` is useful to define `@TypeCast`, and `@TypeCheck` methods? Ok, forgot about that.

But, what was `@ImplicitCast` again? It happens to be neither referenced from the `TypeSystem` JavaDoc nor documented :)


## chrisseaton (14 Feb 2016)

JRuby uses `@ImplicitCast` to promote integer and floating-point types where we don't want specialisation for individual types. I think I've been told it's not ideal for some reason but haven't resolved it yet https://github.com/jruby/jruby/blob/34b32e9c786b916471c1077755340129b1d90d56/truffle/src/main/java/org/jruby/truffle/language/RubyTypes.java.

If something needs more documentation that's a separate issue to adding this default value.


## chumer (14 Feb 2016)

The only use-case for having the types specified in `@TypeSystem` is to have the generated is\* and as\* methods in the generated type system code. For types in `@ImplicitCast`, `@TypeCast` and `@TypeCheck` to work they don't need to be specified in the type system anymore.

I was tempted in the past to deprecate the typesystem types all together because the benefit is rather small. Objections?

Yes documentation also needs to be updated. Will do as part of this issue.


## chrisseaton (14 Feb 2016)

I don't mind it being deprecated as I'm not using it.


