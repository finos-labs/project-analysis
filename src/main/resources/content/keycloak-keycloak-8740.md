#Push authentication

Owner: keycloak

Repo: keycloak

Labels: kind/feature status/rejected 

## reda-alaoui (09 Nov 2021)

### Description

It would be great if Keycoak could support push authentication / push 2FA. This would simplify 2FA and open a new passwordless authentication method.

### Discussion

_No response_

### Motivation

_No response_

### Details

_No response_

## stianst (09 Nov 2021)

It is unlikely that we will ever add push authentication directly as this is a very complicated feature to provide as it requires a proprietary protocol, ability to send push events to different device types, as well as SDKs or apps for Android, iOS, and Windows?

There's also WebAuthN that delivers similar experiences as push authentication, but using a standard protocol.

## stianst (09 Nov 2021)

I'm going to close this for now. If you need push based authentication there is a wade range of options for exactly that, which can be relatively easily be integrated into Keycloak.

