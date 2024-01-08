#DefaultGenerator: unusedSchemas contains too many schemas

Owner: OpenAPITools

Repo: openapi-generator

Labels: 

## jmini (15 May 2018)

Some model object are missing when the server is configured to consume both `application/json` and `application/x-www-form-urlencoded`.

With this example the model `SomeObject` is not generated:

```
swagger: '2.0'
info:
  title: Test
  description: Test API
  version: 1.0.0
host: some.example.com
basePath: /v1
schemes:
  - https
  - http
consumes:
  - application/json
  - application/x-www-form-urlencoded
produces:
  - application/json
paths:
  /testMe:
    post:
      tags:
        - db
      operationId: testMeOp
      parameters:
        - in: body
          name: body
          required: false
          schema:
            $ref: '#/definitions/SomeObject'
      responses:
        '200':
          description: Successful Operation
definitions:
  SomeObject:
    type: object
    properties:
      p1:
        type: string
      p2:
        type: integer
        format: int32
```

This is due to `org.openapitools.codegen.utils.ModelUtils.getUnusedSchemas(OpenAPI)` not working as expected.

I am not sure if the dual `consumes` definition is correct or not (it is derived from a spec send by a used), but we could also imagine an other case with 2 path. In one JSON is expected as request body, in the other an `application/x-www-form-urlencoded` request body. In this case, the schema is also used.

In my opinion the logic to compute the unused schema should be to compute all the used schema and subtract them from a set containing all schemas.

## jmini (16 May 2018)

Propose PR #74 

## tonymurphy (31 Dec 2019)

I was trying to use openapi to generate a client for keycloak - and it doesn't work because requestBody of content type `application/x-www-form-urlencoded` don't seem to be supported? it's not clear in the documentation tbh

    openapi: 3.0.0
    info:
      title: Keycloak Open ID Connect API
      version: '1.0'
      description: Open API Definition for Authentication
    servers:
        description: Identity Server
      - url: 'http://localhost:8080'
        description: Localhost
    tags:
      - name: 'Open ID Connect'
    paths:
      /auth/realms/demo/protocol/openid-connect/token:
        post:
          summary: Direct Access Grant
          description: 'https://tools.ietf.org/html/rfc6749#section-10.7'
          tags:
            - 'Open ID Connect'
          operationId: directAccessGrantCode
          requestBody:
            content:
              application/json:
                schema:
                  $ref: '#/components/schemas/AccessTokenRequest'
            required: true
          responses:
            '200':
              description: OK
              content:
                application/json:
                  schema:
                    $ref: '#/components/schemas/AccessTokenResponse'
            '401':
              description: Not authenticated
            '403':
              description: Access denied
      /auth/realms/demo/protocol/openid-connect/logout:
        post:
          summary: Logout
          description: Remove Session from Keycloak
          tags:
            - 'Open ID Connect'
          operationId: logout
          requestBody:
            content:
              application/x-www-form-urlencoded:
                schema:
                  type: object
                  properties:
                    refresh_token:
                      type: string
          responses:
            '204':
              description: OK
            '401':
              description: Not authenticated
            '403':
              description: Access denied
    components:
      schemas:
        AccessTokenRequest:
          type: object
          properties:
            client_id:
              type: string
            client_secret:
              type: string
            grant_type:
              type: string
            scope:
              type: string
            username:
              type: string
            password:
              type: string
          required:
            - client_id
            - client_secret
            - grant_type
            - scope
            - username
            - password
        AccessTokenResponse:
          type: object
          properties:
            access_token:
              type: string
            expires_in:
              type: integer
            refresh_expires_in:
              type: integer
            refresh_token:
              type: string
            token_type:
              type: string
            id_token:
              type: string
            not-before-policy:
              type: integer
              format: int64
            session_state:
              type: string
            scope:
              type: string


the following works
`curl -X POST "https://localhost:8080/auth/realms/demo/protocol/openid-connect/token" -H "accept: */*" -H "Content-Type: application/x-www-form-urlencoded" -d 'client_id=demo-client&client_secret=****&grant_type=password&scope=openid%20offline_access&username=tonymurphy%40demo.com&password=abc123'`

but the following does not..
`curl -X POST "https://localhost:8080/auth/realms/demo/protocol/openid-connect/token?client_id=demo-client&client_secret=****&grant_type=password&scope=openid%20offline_access&username=tonymurphy%40demo.com&password=abc123" -H "accept: application/json" -H "Content-Type: application/x-www-form-urlencoded"`

