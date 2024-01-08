#Logout takes forever when realm has many clients #8761

Owner: keycloak

Repo: keycloak

Labels: area/oidc kind/bug team/core status/rejected 

## tahergalal (09 Nov 2021)

### Describe the bug

If the realm has 100K clients (every customer in our system has his own client) keycloak seems to take for ever when the user calls logout with a redirect url.

### Version

15.0.1

### Expected behavior

Logout should respond quickly even if the realm has many clients


### Actual behavior

Realm takes a long time

### How to Reproduce?

1. Create a realm
2. Create a large number of clients in the realm
3. Call the logout endpoint with a redirect url and wait for a response

### Anything else?

It seems that keycloak when having a redirect url to validate it it performs it across all clients in the realm could this not be enhanced by specifying that we want to logout from a certain client instead of doing it over all clients?

## mposolda (11 Feb 2022)

This is known issue and Keycloak will take care of this when working on other issue, which will require bigger refactoring of Logout endpoint functionality.

## mposolda (30 Mar 2022)

This is fixed by the work on https://github.com/keycloak/keycloak/issues/10885 and will be available in Keycloak 18

