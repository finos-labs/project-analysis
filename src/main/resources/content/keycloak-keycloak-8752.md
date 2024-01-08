#Remove text based login flows

Owner: keycloak

Repo: keycloak

Labels: kind/eol status/triage 

## stianst (09 Nov 2021)

### Description

Text based login flows was added as an experiment part of kcinit project. As kcinit project is no longer supported, and was deprecated in favour of standard approaches like authorization code flow and device flow, the text based flow can be removed. It was also never supported, or documented, so can be removed without any deprecation period.

### Details

Most of the relevant code can be tracked from:

https://github.com/keycloak/keycloak/blob/13a7f773a9e208124b6cb2235e22a4f8e2adca98/core/src/main/java/org/keycloak/OAuth2Constants.java#L39

https://github.com/keycloak/keycloak/blob/13a7f773a9e208124b6cb2235e22a4f8e2adca98/server-spi-private/src/main/java/org/keycloak/authentication/DisplayTypeAuthenticatorFactory.java#L12

## stianst (21 Jul 2022)

Relevant commits:

https://github.com/keycloak/keycloak/commit/4bba11cd944617852269df5e3b8e086c3fb5bf80
https://github.com/keycloak/keycloak/commit/f4a5e49b635d5e814d9fae6c6f392e28c4dad29d
https://github.com/keycloak/keycloak/commit/d6788a0839eea4811064f6b5af2886af073de7bd

