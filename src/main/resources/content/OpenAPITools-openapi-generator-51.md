#[go] map type object *interface{} convert problem

Owner: OpenAPITools

Repo: openapi-generator

Labels: Client: Go Issue: Bug Server: Go 

## ilk (15 May 2018)

<!--
Please follow the issue template below for bug reports and feature requests.
Also please indicate in the issue title which language/library is concerned. Eg:  [JAVA] Bug generating foo with bar 
-->

##### Description

<!-- describe what is the question, suggestion or issue and why this is a problem for you. -->

Definition property "type": "object" gets converted to *interface{} which returns an error when set to a struct or pointer pointer.

##### openapi-generator version

<!-- which version of openapi-generator are you using, is it a regression? -->
3.0.0
##### OpenAPI declaration file content or url

<!-- if it is a bug, a json or yaml that produces it.
If you post the code inline, please wrap it with
```yaml
(here your code)
```
(for YAML code) or
```json
(here your code)
```
(for JSON code), so it becomes more readable. If it is longer than about ten lines,
please create a Gist (https://gist.github.com) or upload it somewhere else and
link it here.
  -->

##### Command line used for generation

<!-- including the language, libraries and various options -->

##### Steps to reproduce

<!-- unambiguous set of steps to reproduce the bug.-->

##### Related issues/PRs

<!-- has a similar issue/PR been reported/opened before? Please do a search in https://github.com/openapitools/openapi-generator/issues?utf8=%E2%9C%93&q=is%3Aissue%20 -->

##### Suggest a fix/enhancement

<!-- if you can't fix the bug yourself, perhaps you can point to what might be
  causing the problem (line of code or commit), or simply make a suggestion -->

Instead of an *interface{} map the object to map[string]interface{}


## wing328 (15 May 2018)

@ilk thanks for creating the issue. May I know if you can also share a spec to more easily reproduce the issue?

When using `type: object`, are you referring to an arbitrary JSON payload?

## ilk (15 May 2018)

**For example:**
```json
...
"Provider" : {
  "type" : "object",
  "properties" : {
    "Foobar" : {
      "type" : "object",
      "description" : "Foobar-specific configuration object"
    },
...
```

**Results to:**
```go
type Provider struct {
  Foobar *interface{}
  ...
}
```

**Better would be:**
```go
type Provider struct {
  Foobar map[string]interface{}
  ...
}
```

## louis77 (15 May 2018)

@ilk @wing328 I agree with ilk's assessment and would appreciate this change too. It'll make the go client more Go-like:

1. JSON Object type maps properly to Go's `map[string]interface{}`. This way there is no need to type cast.
2. Since map/slice/channel values are already pointer wrapper struct values, there is no need in this case to add an additional pointer to them. Handling an `*interface{}` variable is extremely complex, non Go idiomatic and involves usage of the `reflect` package - which is not easy to grasp.



## wing328 (15 May 2018)

@louis77 thanks for the feedback.

cc @antihax  @bvwells

## antihax (15 May 2018)

Not sure the reason for the string key? Wouldn't the object be a model (one
of many possible structs).

On Tue, May 15, 2018, 11:23 William Cheng <notifications@github.com> wrote:

> @louis77 <https://github.com/louis77> thanks for the feedback.
>
> cc @antihax <https://github.com/antihax> @bvwells
> <https://github.com/bvwells>
>
> —
> You are receiving this because you were mentioned.
> Reply to this email directly, view it on GitHub
> <https://github.com/OpenAPITools/openapi-generator/issues/51#issuecomment-389207718>,
> or mute the thread
> <https://github.com/notifications/unsubscribe-auth/AGqJ24U0kSX0NXn_M1F_Q4P85P6lP4FYks5tyvMHgaJpZM4T_s36>
> .
>


## louis77 (15 May 2018)

@antihax Which works properly if definitions are used in the OpenAPI Spec. 

However, arbitrary inline objects are currently represented as *interface{}'s. To use @ilk 's example, `Foobar` is an arbitrary inline object, most likely there will be no corresponding struct definition in the Go code that will use the go-client.



## antihax (15 May 2018)

This looks OK in this instance. However it would end up as map[string]string as we cannot determine types on an arbitrary structure?

## louis77 (15 May 2018)

The `AbstractGoCodegen.java` implementations plainly maps these objects always to an interface{}:
`typeMapping.put("object", "interface{}");`

This is now changed to:
`typeMapping.put("object", "map[string]interface{}");`

So it'll always end up as a map[string]interface{}, there is no further logic involved. In a next release we could think about generating an `InlineObject` Go type to further abstract the user from low-level accessing of the values inside the map.


## wing328 (16 May 2018)

Is it correct to say that the proposed change will also work with XML payload (since Go client supports the "withXML" option) ?
```
	withXml
	    whether to include support for application/xml content type and include XML annotations in the model (works with libraries that provide support for JSON and XML) (Default: false)
```

