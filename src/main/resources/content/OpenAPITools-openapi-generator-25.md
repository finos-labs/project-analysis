#Add CodegenProperty.nameInSnakeCase

Owner: OpenAPITools

Repo: openapi-generator

Labels: Enhancement: Feature 

## jmini (13 May 2018)

Raised in #22, it would be nice to have `CodegenProperty.nameInSnakeCase`similar to `CodegenProperty.nameInCamelCase`.

Example:

| baseName | nameInSnakeCase |
|---|---|
| `petId` | `PET_ID` |
| `name` | `NAME` |
| `some_value` | `SOME_VALUE` |

When this feature is implemented, #21 can be changed to be conform to the java naming convention.

## jimschubert (13 May 2018)

@jmini It may be good to also include a snake case mustache lambda for usage in templates as well. See https://github.com/OpenAPITools/openapi-generator/tree/master/modules/openapi-generator/src/main/java/org/openapitools/codegen/mustache

There are sometimes differences between assumptions made in base language classes that don't apply to all frameworks (or even all versions of the same framework), and we can work around these differences using the above lambdas. This allows better extension by users with custom templates.

## jmini (13 May 2018)

Thank you a lot for this input. I will have a look at this lambda feature.

## jimschubert (13 May 2018)

@jmini no problem. Example usage in templates: https://github.com/OpenAPITools/openapi-generator/blob/master/modules/openapi-generator/src/main/resources/kotlin-server/libraries/ktor/api.mustache

Here, ktor requires lower case http verbs. In other templates (like user defined templates with annotations), Http verbs may be expected as title cased or camel cased.

## jmini (15 May 2018)

First step: #42
I will look at the mustache lambda in a separated PR.

## jmini (18 May 2018)

Second step (mustache lambda): #91 contributed by @viclovsky. Thank you a lot!

