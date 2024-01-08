#Include code snippets in sources Jars

Owner: oracle

Repo: graal

Labels: 

## pniederw (27 Jan 2016)

A very common way to read Javadoc is through the sources (Jars) attached to the IDE project. (It's much more convenient to search/browse sources in an IDE than Javadoc on a web page, and seeing both documentation and source code at the same time is very useful.) However, seeing Javadoc such as `Usage example: {@codesnippet BranchProfileSample}` isn't helpful - instead I'd like to see the code snippet itself. Therefore, please embed code snippets in sources before creating sources Jars. (And please make sure line numbers in class files remain intact.)


## smarr (27 Jan 2016)

Hm, I kind of share the sentiment.
Not sure about the technical solution though. Seems problematic.

@jtulach, you got an idea?
Generally, I like that the code examples are checked by the compiler. With the changes we have this is absolutely necessary.

@pniederw, the relevant project is [codesnippet4javadoc](https://github.com/jtulach/codesnippet4javadoc), pull requests are probably welcome.


## chumer (28 Jan 2016)

I agree. Its also my favorite way of browse external libraries and code in general. While I understand the advantages of codesnippets its lack of IDE support kills it. 


## pniederw (29 Jan 2016)

To be fair, after attaching Javadoc Jars to the IDE project (which at least Gradle doesn't do by default), documentation popup/window does reveal the sample code (in IntelliJ). But it's not the same experience as having it in the source code.

I do understand the need to manage code snippets externally, and it has a clear benefit for users (code is more likely to be correct). Personally I prefer to use [Asciidoclet](https://github.com/asciidoctor/asciidoclet) for this, but it suffers from the same problem.

There are two technical solutions I can think of:
1. Embed snippets in source code before compiling (perhaps only for published builds).
2. Improve IDEs to display Javadoc (e.g. taken from attached Javadoc Jar) in-place.


## jtulach (30 Jan 2016)

I was hoping attaching Javadoc will solve the most critical problem.


## chumer (01 Feb 2016)

In the current situation for me the disadvantages outweigh the advantages. I'd rather live with the redundancy of having the test case defined in a TestClass as well. 


## mlvdv (11 Feb 2016)

I don't have a strong opinion about the tradeoff here, but I disagree with the choice of keywords "BEGIN" and "END".  They appears completely mysterious to the uninformed (human) reader, and we've seen examples in recent code reviews.  Keywords that are more self-explanatory would help.


## woess (11 Feb 2016)

@mlvdv +1


## jtulach (12 Feb 2016)

I don't understand what is wrong on sentences like "begin of sample xyz" and "end of sample xyz", but you are of course allowed to propose alternatives. Contribute to development of the codesnippets plugin by forking https://github.com/jtulach/codesnippet4javadoc ...


## jtulach (12 Feb 2016)

In order to proceed with this inquiry, we need a representative project to demonstrate the issues on. I suggest to use [simplelanguage with truffle@0.11](https://github.com/graalvm/simplelanguage/commit/72755df5c11e4d0e7135f85d7e7c09f2bbe30b5b) as that is the project users of **Truffle API** are supposed to start with and (unlike internal projects) it uses Java de-facto [standard build system](http://maven.apache.org).

After opening it in my IDE and downloading Javadoc for its libraries (one click on node representing project dependencies) I can confirm the code snippet is properly included and displayed when reading documentation in IDE's code completion:
![codesnippet-in-the-ide](https://cloud.githubusercontent.com/assets/1842422/13006694/012b7e7a-d18b-11e5-8f2f-dba16ed6f043.png)
We should verify the same behavior is achievable in the other two supported IDEs when working with the [simplelanguage project](https://github.com/graalvm/simplelanguage/).


## mlvdv (18 Feb 2016)

@jtulach  My comment about keywords "BEGIN" and "END" comes from finding the declarations of those snippets (in code), not in their use (in JavaDoc).  A reader who doesn't know about this mechanism will have no clue about how to figure out why they are there.


## jtulach (26 Feb 2016)

@chrisseaton inspired me in #40 to investigate what it would take to embed the code snippets directly in the API itself. If we do what Chris did and use `@link` instead of `@codesnippet` we'll get the same Javadoc and our IDEs will properly navigate from the Javadoc to the actual code snippet. Implemented as https://github.com/jtulach/codesnippet4javadoc/commit/515fdd141c8caed9d86afce859afb15a81054f7f 


## jtulach (26 Feb 2016)

Here is [Truffle code base rewritten](https://github.com/jtulach/truffle/commit/14221e4ccdacfeb27f4e836e7243b3f085f9881e) to use the new _embedded_ style. As far as I can tell the navigation is now OK. For majority of the cases the "go to definition" jumps to proper place. For the more complex cases (TruffleLanguage referencing PolyglotEngine) "Go to type" dialog works in my IDE.


## jtulach (04 Mar 2016)

Pull request for mx created: https://github.com/graalvm/mx/pull/65


## jtulach (15 Mar 2016)

#95 is merged. Closing. Enjoy [navigable snippets](https://twitter.com/JaroslavTulach/status/709718866109272064)!


