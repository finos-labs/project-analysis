#Unknown essential acr claim does not result in an error

Owner: keycloak

Repo: keycloak

Labels: area/authentication area/oidc kind/bug 

## CorneliaLahnsteiner (08 Nov 2021)

### Describe the bug

**Just related to the Step up Authentication:**

Currently, the behavior in the case of an unknown essential acr claim does not comply with the OIDC specification, which states:
_If the acr claim is requested as an Essential Claim for the ID Token with a values parameter requesting specific Authentication Context Class Reference values and the implementation supports the claims parameter, the Authorization Server MUST return an acr Claim Value that matches one of the requested values. **If this is an Essential Claim and the requirement cannot be met, then the Authorization Server MUST treat that outcome as a failed authentication attempt.**_

Behavior has been removed with the implementation of the callback: https://github.com/keycloak/keycloak/commit/6e4fb3a0cd539b3eb9568c3ea7c9de67a755cac6#diff-b72231a1d2641bd3ef2dfbe97433cda0a506e6c4fafa89896222339716cee6faL305

### Version

17.0.0-SNAPSHOT

### Expected behavior

The authentication process should result in an error page instead of an successful authentication.

### Actual behavior

The authentication process results in an successful authentication.

### How to Reproduce?

To reproduce the issue, send an authentication request with an essential request and an unknown or high (e.g. 5) acr value. After finishing the authentication (entering username/password and OTP), you should receive an authentication error. Instead, you are currently authenticating successfully with the highest authentication level.

Request example:
https://{DOMAIN}/auth/realms/{REALMNAME}/protocol/openid-connect/auth?client_id={CLIENT-ID}&redirect_uri={REDIRECT-URI}&scope=openid&response_type=code&response_mode=query&nonce=exg16fxdjcu&claims=%7B%22id_token%22%3A%7B%22acr%22%3A%7B%22essential%22%3Atrue%2C%22values%22%3A%5B%22unkownvalue%22%5D%7D%7D%7D

### Anything else?

Tests for this use case are already commited:
* LevelOfAssuranceTest.java:stepupToUnknownEssentialAcrFails
* LevelOfAssuranceTest.java:essentialClaimNotReachedFails
* LevelOfAssuranceTest.java:essentialUnknownClaimFail

#### Related issue

There is another issue related to the ACR that under some circumstances, the ACR level is not correctly updated in the authentication session after finish of subflow with the ACR condition. This is possible to seen in the `LevelOfAssuranceTest.stepupAuthentication()` , which requires authentication to level 3 (push the button) in case that only the level 2 (gold) was requested.

