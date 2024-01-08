#Generate OpenAPI descriptors for the Account REST API

Owner: keycloak

Repo: keycloak

Labels: kind/feature area/account/api 

## yordis (09 Nov 2021)

### Description

Jira Ticket: https://issues.redhat.com/browse/KEYCLOAK-9655

OpenAPI allows us to generate documentation, API clients, and server stubs for several languages based on its descriptor.

### Why?

As part of the efforts to switch to the new Account console documentation must be provided, to improve the developer's experience.

### How

There are several ways of doing this, but the best approach is to use MicroProfile OpenAPI which is available in the latest releases of WildFly

### Acceptance criteria

Have the OpenAPI descriptor generated with all the definitions for the new Account REST 
API in YAML/JSON format for ${basedir}/target/

### Additional Information

- https://quarkus.io/guides/openapi-swaggerui
- https://groups.google.com/g/keycloak-dev/c/_Dmjq57HkS0/m/r00F1EyOBQAJ

### Discussion

_No response_

### Motivation

_No response_

### Details

_No response_

## jonkoops (14 Jan 2022)

We are also interested to have this for the Admin REST API so we can generate the API client we need for the new Admin UI.

## abstractj (09 Mar 2022)

@yordis @jonkoops @tnorimat started a discussion about this last year https://github.com/keycloak/keycloak/discussions/8899#discussioncomment-2079164. You are welcome to join.

## jonkoops (05 Jun 2023)

Duplicate of #10775

