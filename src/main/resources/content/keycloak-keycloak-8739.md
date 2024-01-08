#Allow protocol mapper to fail the token retrieval

Owner: keycloak

Repo: keycloak

Labels: kind/enhancement status/rejected 

## reda-alaoui (09 Nov 2021)

### Description

# Use case
I have a unique Keycloak realm with multiple clients.
Some users can access only some clients.
Some clients are not built by me. They are provided by external vendors. I can't modify their source code.
Some clients are resource provider (app servers) while others are only resource consumers (internal app ui, foreign servers, ...)

When a client X (azp) fetches an access token to access client Y resource (audience), the token should be provided only if the authenticated user is authorized to access client Y.

# Expected solution
The protocol mapper tool tip says:

> Protocol mappers perform transformation on tokens and documents. They can do things like map user data into protocol claims, or just transform any requests going between the client and auth server.

So I created a custom protocol mapper. This mapper fails with a runtime exception if the user is not authorized to access the client designated by the audience. This mapper does nothing if the client is authorized.

This solution works pretty well.

But it has a downside. In case of unauthorized user, the client will encounter an internal server error instead of a user friendly message.

# Considered alternative
I considered using a custom authenticator instead of a protocol mapper.

The problem is that a client is forever bound to an authenticator. The authenticator applies to the authorized party instead of the audience.

The protocol mapper adds flexbility because it can be declared into a client scope which in turn can be dynamically enabled by client request scope parameter. The audience can therefore be configured inside the same client scope.

# Feature request
Given the protocol mapper is expected to be able to:

> just transform any requests going between the client and auth server

I would like ProtocolMapper to be able to gracefully fail the token retrieval. For example, it could be done via a checked exception or via an object provided to the ProtocolMapper.

### Discussion

_No response_

### Motivation

_No response_

### Details

_No response_

## stianst (09 Nov 2021)

Protocol mappers shouldn't be used to control if a user can authenticate to a particular client. Rather, I would use a custom authenticator for this purpose right now, then in the future we would like to introduce an option to control which users can access which apps, probably based on roles, with support to hook in some external authorization decision.

## reda-alaoui (09 Nov 2021)

Hello @stianst,

As explained in the description, custom authenticator is not a solution for our use case.
So right now, I have no other solution than forking or alter Keycloak behaviour with a java agent.

Is there something to track for the `option to control which users can access which apps` ?

