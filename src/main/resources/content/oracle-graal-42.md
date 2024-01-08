#CHANGELOG.md is outdated

Owner: oracle

Repo: graal

Labels: 

## woess (11 Feb 2016)

The changelog hasn't been updated since Oct 2015 and doesn't include the latest releases.


## jtulach (12 Feb 2016)

I plan to remove the manually written changelog and replace it by automatically generated one that is included in [the documentation](http://graalvm.github.io/truffle/javadoc/0.11/). NetBeans is using [arch.xml and apichanges.xml](http://wiki.netbeans.org/APIDevelopment#Writing_the_documentation) for that. I'd rather replace that with annotations processed by an annotation processor. But I need to write that tool first.


## woess (12 Feb 2016)

Manually written, it can give short summary/overview about what happened in a release, without going into too much detail. Feels better than having to dig out announcement emails (which served a similar purpose) from the mailing list archive.


## jtulach (29 Feb 2016)

First of all we need to mark all elements in the API with the version they were introduced at. Here is my current diff: [TruffleAddSinceTag.diff](https://github.com/graalvm/truffle/files/150567/TruffleAddSinceTag.txt)

It assigns version **0.8** to all elements. I have chosen **0.8** as it is the old version I know; probably nobody cares about older versions anymore and it is relatively easy to find out newer elements and change their version of 0.9, 0.10, 0.11.

I plan to apply the `@since` tag change tomorrow. 


## eregon (29 Feb 2016)

I agree that a manually-written changelog is much more useful than an automated generated list, maybe `@since` is overkill here?


## jtulach (29 Feb 2016)

- `@since` tag is a nice way to find out whether an element is or isn't intended to be public.
  - if we had this check, error https://github.com/graalvm/truffle/pull/82/ wouldn't have to be fixed now
- `@since` tag provides well-understood information about origin of particular API element
  - Java core API is using it and people know what it means
  - By looking at latest Javadoc one can understand evolution history of the API


## jtulach (29 Feb 2016)

The desirable rule to achieve in the Truffle repository should be: "_Every element in the API needs to have Javadoc with `@since` tag_":
- Obviously having elements in the API without Javadoc is wrong
- with Javadoc being there, the additional requirement to add one more line `@since X.Y` seems easy


## jtulach (25 Apr 2016)

I have added prosaic description of what happened on 0.13 version into the https://github.com/graalvm/truffle/blob/truffle-0.13/CHANGELOG.md based on the sigtest and @since tag information.


## chumer (18 Jul 2016)

The new policy includes that every API change must be documented in the change log in prosaic form before getting integrated. Changelog descriptions for older versions where added.


