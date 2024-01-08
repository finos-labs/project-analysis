#[java] enum for int array initialized with strings

Owner: OpenAPITools

Repo: openapi-generator

Labels: Client: Java Enhancement: Feature Server: Java 

## etherealjoy (15 May 2018)

<!--
Please follow the issue template below for bug reports and feature requests.
Also please indicate in the issue title which language/library is concerned. Eg:  [JAVA] Bug generating foo with bar 
-->

##### Description

<!-- describe what is the question, suggestion or issue and why this is a problem for you. -->
The generated code for the enums of int types, in an array of said enums is initialized with a string.
Compilation fails
```java
  public enum TypeEnum {
    _0("0"),
    
    _1("1"),
    
    _2("2"),
    
    _3("3")
```
##### openapi-generator version

<!-- which version of openapi-generator are you using, is it a regression? -->
master

##### OpenAPI declaration file content or url

<!-- if it is a bug, a json or yaml that produces it.
If you post the code inline, please wrap it with
```yaml
  TypeIds:
    type: object
    properties:
      type:
        type: array
        items:
          type: integer
          format: int32
          enum:
          - 0
          - 1
          - 2
          - 3
```
(for YAML code) or
```json
(here your code)
```
(for JSON code), so it becomes more readable. If it is longer than about ten lines,
please create a Gist (https://gist.github.com) or upload it somewhere else and
link it here.
  -->
```yaml
  TypeIds:
    type: object
    properties:
      type:
        type: array
        items:
          type: integer
          format: int32
          enum:
          - 0
          - 1
          - 2
          - 3
```
##### Command line used for generation

<!-- including the language, libraries and various options -->
java -jar ./openapi-generator-cli.jar generate  -i ./Api.yaml  -o ./genjava -l java 

##### Steps to reproduce

<!-- unambiguous set of steps to reproduce the bug.-->

##### Related issues/PRs

<!-- has a similar issue/PR been reported/opened before? Please do a search in https://github.com/wing328/openapi-generator/issues?utf8=%E2%9C%93&q=is%3Aissue%20 -->

##### Suggest a fix/enhancement

<!-- if you can't fix the bug yourself, perhaps you can point to what might be
  causing the problem (line of code or commit), or simply make a suggestion -->

## jmini (16 May 2018)

This is a nice input. We should support this. Thank you for reporting it.

## jmini (27 Jun 2018)

This is fixed for java and kotlin with the latest `3.1.0-SNAPSHOT` version. Can you verify?

## etherealjoy (27 Jun 2018)

I will test it and will close this issue if solved.

## etherealjoy (27 Jun 2018)

Solved with #75 
Generated code looks like this now,
```java
    NUMBER_0(0),
    
    NUMBER_1(1),
    
    NUMBER_2(2),
    
    NUMBER_3(3);
```

