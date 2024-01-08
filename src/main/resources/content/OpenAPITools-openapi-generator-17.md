#[Discussion] GitHub labels definition in this project

Owner: OpenAPITools

Repo: openapi-generator

Labels: General: Discussion 

## jmini (13 May 2018)

I think we should define how we will use labels in the project. 
See the current list here: https://github.com/OpenAPITools/openapi-generator/issues/labels

---

What is the difference between `bug` and `invalid` ?

Is `invalid` for an issue that is not correct?

---

I also think we need to do the difference between something broken in the generator (for example a NullPointer when we run the code) and something that is wrong in the generated code (that is often only for a specific OpenAPI sepecification, and for one target language)

---

I also think we need a `discussion` type of issue.

## wing328 (13 May 2018)

To start with, I've cloned the labels using a script.

## jimschubert (13 May 2018)

I've only seen `invalid` refer to the issue being reported, and not to code. I don't particularly like the label, as it sounds too negative.

I'd actually like to see generator labels separated into:

* language
* generator type
* framework

This way, I could easily filter for `c#`, `scala`, `kotlin`, or something like `openapi-generator-ignore`. This would reduce the numbers of labels significantly. That is, `client`, `server`, and `c#` would be their own labels, so `Client: C-Sharp` and `Server: C-Sharp` would be searchable as `client` `c#` and `server` `c#`.

For framework specifics, I could add a label search language specifics or search for all issues across languages for a given framework. As an example, I plan to add Spring support for Kotlin in the coming months, it would be nice to search Spring related issues in general so I don't duplicate efforts done for Java's implementation.

If we have the above options listed in the issue template, I could write a chrome extension to automatically apply these labels.

Other labels I think would be helpful:

* `bug verified`
* `can't reproduce`
* `workaround suggested`
* `feature request`
* `awaiting user response`

We have many labels that are color coded and grouped by prefix (e.g. `Feature:`), but I would find it helpful to be able to search these as that group. According to [GitHub docs](https://help.github.com/articles/searching-issues-and-pull-requests/), there's still no way to search labels by "starts with" or "contains" operators.

The above suggestions would help me, personally. One problem I had previously was that I had no way to track that issues were researched and/or verified. I spent a bit of time filtering issues and going through old issues to see if anything had changed.

There are also common labels such as `Good first issue` to drive community contributions, which I think would be good to adopt.

## jmini (13 May 2018)

I like the idea of splitting: language / generator type / framework

We should decide soon, because when we remove/rename labels it mess up with the existing labels.



## jmini (18 May 2018)

The more I work with label in this project, the more I consider that we should follow @jimschubert proposal for `language`, `generator type`, `framework`.

What is the added value to set `Kotlin: Client` and `Kotlin: Server` on an issue? On the other part, instead of `Java: Client` I think that `Rest-assured` (framework) would be great.

Can we do the proposed change?


## jmini (11 Jun 2018)

From @jimschubert in #144 :

> We don't have a tag for infrastructural code that affects all generators

I agree, we should create such a label.

## jimschubert (20 Jun 2018)

I want to test the core team alias:

/cc @OpenAPITools/generator-core-team

## jimschubert (29 Jan 2020)

We've created a ton of labels, and I created the GitHub App [auto-labeler](https://github.com/jimschubert/auto-labeler) to assist with labeling issues. I think this is good to close.

