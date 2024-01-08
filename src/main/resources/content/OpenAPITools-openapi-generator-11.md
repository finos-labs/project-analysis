#[DefaultCodegen] NullPointerException with ComposedSchema with oneOf

Owner: OpenAPITools

Repo: openapi-generator

Labels: Issue: Bug 

## jmini (13 May 2018)

##### Description

NullPointerException with composed schema having a oneOf

##### openapi-generator version

3.0.0-SNAPSHOT

##### OpenAPI declaration file content or url

```yaml
openapi: 3.0.1
info:
  title: oneOf test
  version: '1.0'
servers:
  - url: 'http://localhost:8000/'
paths:
  /state:
    get:
      operationId: getState
      responses:
        '200':
          description: OK
          content:
            application/json:
              schema:
                oneOf:
                  - $ref: '#/components/schemas/ObjA'
                  - $ref: '#/components/schemas/ObjB'
                discriminator:
                  propertyName: realtype
                  mapping:
                    a-type: '#/components/schemas/ObjA'
                    b-type: '#/components/schemas/ObjB'
    post:
      operationId: create
      requestBody:
        content:
            application/json:
              schema:
                oneOf:
                  - $ref: '#/components/schemas/ObjA'
                  - $ref: '#/components/schemas/ObjB'
                discriminator:
                  propertyName: realtype
                  mapping:
                    a-type: '#/components/schemas/ObjA'
                    b-type: '#/components/schemas/ObjB'
        required: true
      responses:
        '201':
          description: OK
components:
  schemas:
    ObjA:
      type: object
      properties:
        realtype:
          type: string
        message:
          type: string
    ObjB:
      type: object
      properties:
        realtype:
          type: string
        description:
          type: string
        code:
          type: integer
          format: int32
```


## jonschoning (13 May 2018)

I've been interested in oneOf (e.g n templates) for a while, since it could require different a strategy to handle, especially in statically typed langs 

## jmini (13 May 2018)

@jonschoning: Yes, this issue is really about the NPE issue, but if you like to discuss it, I have opened #15 for that.

