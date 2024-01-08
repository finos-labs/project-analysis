#[DefaultGenerator] NullPointerException when no components defined

Owner: OpenAPITools

Repo: openapi-generator

Labels: 

## jmini (13 May 2018)

##### Description

When there isn't any component defined in the specification, there is a `NullPointerException` in `org.openapitools.codegen.utils.ModelUtils.getUnusedSchemas(OpenAPI)`

```
Exception in thread "main" java.lang.NullPointerException
	at org.openapitools.codegen.utils.ModelUtils.getUnusedSchemas(ModelUtils.java:96)
	at org.openapitools.codegen.DefaultGenerator.generate(DefaultGenerator.java:781)
	at fr.jmini.openapi.openapitools.generator.TestOpenapitoolsCodegenMain.convert(TestOpenapitoolsCodegenMain.java:79)
```

##### openapi-generator version

`3.0.0-SNAPSHOT`

##### OpenAPI declaration file content or url

```yaml
openapi: 3.0.1
info:
  title: ping test
  version: '1.0'
servers:
  - url: 'http://localhost:8000/'
paths:
  /ping:
    get:
      operationId: pingGet
      responses:
        '201':
          description: OK
```

