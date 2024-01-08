#Verify @Child fields that use @Instrumentable nodes with an annotation processor.

Owner: oracle

Repo: graal

Labels: feature oracle-emp 

## chumer (19 Feb 2016)

It is a common error in guest languages to use nodes with source sections and that are @Instrumentable with the wrong field type so that the WrapperNode cannot get inserted.

For example: 

``` java
@Instrumentable(InstrumentableNodeWrapper.class)
class InstrumentableNode extends Node {}

class InstrumentableNodeSubclass extends InstrumentableNode {}

class SomeOtherNode {
@Child InstrumentableNodeSubclass child;
}

```

Replaces on SomeOtherNode#child will fail if InstrumentableNodeSubclass is wrapped by the instrumentation framework because the wrapper just extends InstrumentableNode but not as required InstrumentableNodeSubclass. We should check for this error in an annotation processor that gets triggered on @Child annotations.


## chumer (18 Jul 2016)

Closing. Continued elsewhere: GR-1078


