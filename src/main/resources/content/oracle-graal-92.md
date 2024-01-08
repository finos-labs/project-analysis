#New API to replace a particular specialization instance

Owner: oracle

Repo: graal

Labels: oracle-emp 

## mbrantner (03 Mar 2016)

Currently, the Truffle DSL has the "rewriteOn" annotation that removes the current instance of a specialization. However, according to the javadoc, it also removes all the other instances of that specialization and prevents instances of this specialization to be created again.

This behavior does not allow replacement of an individual instance in the cached specialization chain. Having a cache with no replacement is a serious limitation in general.

Single node replacement could be done similarly to the rewriteOn attribute, for example, by adding a new attribute with a list of exceptions. Throwing one such exception would remove only the current instance.


## chumer (03 Mar 2016)

I think an additional property `removeOn` which specifies the exceptions could do the trick.

The thing I am worried about is that if this feature is not used with care rewrite loops can easily occur, so one of the Truffle properties fitness might be violated easily with this feature. For previous features in the DSL I wanted to keep the source of these kind of errors as small as possible, that's why DSL nodes add to the range of supported input values with each specialization but do not subtract. The Specialization#assumptions feature is the only exception of that rule atm.

@mbrantner Can you provide a complete example which highlights the use-case? So I can verify that I understood the requirement completely and that there is no other option except adding this feature.  Thanks.


## mbrantner (18 Mar 2016)

This was discussed offline. There exist other ways (e.g. using assumptions or profiles) to achieve the desired behavior.


