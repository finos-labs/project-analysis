#Token Exchange setting incorrect clientId

Owner: keycloak

Repo: keycloak

Labels: kind/bug area/token-exchange status/triage 

## nickzelei (09 Nov 2021)

### Describe the bug

I'm attempting to do an internal-to-internal token exchange. The exchange returns successfully, but it appears that the `issuedFor` or `azp` claim is set to the incorrect client.

This poses a problem in that the exchanged token is not able to be refreshed with its access token because the refresh token is bound to the wrong client.

I'm exchanging the token so that I can get the scopes, etc from the target client. That works great here, but I then need to be able to use the refresh token to exchange it for a new access token that has those same scopes.
In the current state, I instead have to re-initiate the token exchange all over again.

I'm I understanding token exchange correctly here? I took it that you take a token from client A and are able to completely exchange it for a token from client B. The way this is currently set up, the token is exchanged for another client A token, but that has been enriched with scopes from client B.
Also, why is no identity token returned from the exchange?

### Version

15.0.2

### Expected behavior

The Access Token and Refresh token should have the `azp` set to the target client.

### Actual behavior

The Access Token and Refresh token are set to the starting client.

### How to Reproduce?

To reproduce, simply follow the internal-to-internal token exchange [docs](https://www.keycloak.org/docs/latest/securing_apps/#internal-token-to-internal-token-exchange) and inspect the tokens that are returned. The `azp` will be set to the starting client.

Once the tokens have been exchanged, attempt to issue a refresh against the target-client.

```
POST /realms/:realm/protocol/openid-connect/token

grant_type: refresh_token
refresh_token: <token>
client_id: target-client
```

Response:
```
{
    "error": "invalid_grant",
    "error_description": "Invalid refresh token. Token client and authorized client don't match"
}
```

This makes sense given the `azp` is for the starting-client, not the target.

### Anything else?

* [responseBuilder.getAccessToken().issuedfor(client.getClientId());](https://github.com/keycloak/keycloak/blob/main/services/src/main/java/org/keycloak/protocol/oidc/DefaultTokenExchangeProvider.java#L342)
* [responseBuilder.getRefreshToken().issuedFor(client.getClientId();](https://github.com/keycloak/keycloak/blob/main/services/src/main/java/org/keycloak/protocol/oidc/DefaultTokenExchangeProvider.java#L342)

## keliwath (02 Sept 2022)

Same issue here. If "it does not make sense to exchange a token from a confidential client for a public client", what should we be doing to achieve this? The direct access grant workaround looks like a potential security issue.

## pedroigor (02 Sept 2022)

@keliwath Yeah, the resource-owner password grant should be avoided and it is removed from the latest versions of the OAuth2 spec. Could you elaborate more about your use case and why you want to exchange a token to access a public client?

## keliwath (02 Sept 2022)

@pedroigor Sure. I have an Angular SPA with two authentication workflows: the first one is the standard Keycloak login with a public client, this works fine. In the second one I have to authenticate a user against a legacy system and then impersonate them in order to get a Keycloak token that can be used as normal from the SPA. For this I need a token issued to the public client. 

Up until version 15 I was using exactly the same workaround as moribvndvs in this thread: setting "client_id" to the target public client in a token exchange using a token from a confidential client using the client credentials grant. This produced a token with the azp claim set to the public client, and everything worked fine.  

But now we have upgraded to version 18 and as stated by NeoVG this doesn't work anymore, I get the error `Client is not the holder of the token`. If I modify the exchange token call to use the confidential client then the exchanged token can't be used in the Angular SPA because there is no user session associated with the public client (since the azp is different). 

Using the password grant to impersonate the user works, of course, but I would rather avoid it. Maybe I've misunderstood what token exchange actually is, but Is there any way to get a token issued to the public client in this scenario? 


## pedroigor (02 Sept 2022)

Allowing public clients to exchange tokens opens security holes and that is the reason behind the last updates we did in this area. Conceptually, it is also wrong because a public client is conceptually a client and not a resource server.

You might consider looking at some SPA architectures in this [specification](https://datatracker.ietf.org/doc/html/draft-ietf-oauth-browser-based-apps#page-6). In particular, the first one is related to both SPA and API living within the same domain.

Another possible approach is to provide your own custom `TokenExchangeProvider` implementation so that you can support your legacy system as an additional subject token type.

## dazasa (16 Nov 2022)

I am not really sure if this behavior is correct. 
If i request a new offline token for the audience xyz my expectation ist that the new access token is created for the audience = newClient. azp should be = newClient and not masterClient.
 
POST https://SERVER/auth/realms/CLIENT/protocol/openid-connect/token

     client_id: "masterClient"
     grant_type: "urn:ietf:params:oauth:grant-type:token-exchange"
     requested_subject: "userId"
     audience: "newClient"
     client_secret: "XXX"

If i make this request, the new access token is created for the requestedClient (masterClient = azp). The user session is created for the audience (take a look in db). The new token should be also created for the requested audience. In my Opinion this is a bug. Why should i exchange a token for a another user  but the token is created for the masterClient. That makes no sense for me. 


## pedroigor (25 Nov 2022)

But `masterClient` is the requesting party, right? As such the token is issued to that client and not the `audience`.

The token however is built to be used at the `audience`. And the `audience` must know that the client that obtained the token was the client that did the exchange.

## piabor (12 Jan 2023)

The problem is still there in version 20.0.2. However, as mentioned previously in the thread, changing the client id and secret from starting client to target client in the token exchange request returns an access token and refresh token that works for the target client.

I believe the token exchange request in the Keycloak documentation should be updated (yes, it is still incorrect, but we never know maybe it is a bug, after all, it is a preview feature).

## doflo-dfa (18 Jan 2023)

@pedroigor ... I was curious what's the right way to do this ? ... why is going from a confidential client to a public client generically bad ?

I mean the issue with refresh and id tokens not following audience minting hints certainly seems like a bug and I follow your validation logic but it also seems like a wont-fix-bug because it's not what we should be doing so given a number of people here have use cases that are not possible is there guidance on how these flows should work.

In our scenario we have an SPA which I would assume is a common scenario using a public client and we have multiple server side API's utilizing a confidential client authing with JKWS assertions .. 

we would like to use a TOKEN_EXCHANGE scenario when an existing user attempts to sign up rather than returning that the user is already on our system we would like to send them a OTP to their email to continue the signup (new) or to login them in (existing) to do that we need to TOKEN_EXCHANGE from our API (backend of signup) to our SPA (public client)

I can not use a confidential client in an SPA ... and why would I want to use a public client server to server.




## pedroigor (24 Jan 2023)

Based on this [draft](https://datatracker.ietf.org/doc/html/draft-ietf-oauth-browser-based-apps) you should use the Backend for Frontend Proxy (BFF). If I understood your use case correctly.

This architectural pattern allows you to use a confidential client to act as a mediator between your public client and proxy.

From the AS perspective, the behavior we had before is plain wrong and risky to support. As I mentioned before, we had CVEs related to this behavior.

## doflo-dfa (24 Jan 2023)

So is the thought that eventually the keycloak-JS project would have some type of a BFF proxy project as a companion ?

I only bring it up to make sure I’m not missing something. I’m guessing that the BFF proxy is in fact itself additional code that’s essentially handling that communication hand off between the public and the private clients.


## pedroigor (25 Jan 2023)

Yeah, it is additional code and adds more complexity on the client side: development and deployment-wise.

I don't think we are going to provide a BFF-proxy-like component for the JS adapter.

TBH, I'm almost convinced that we should somehow enable the use case that you are doing as it looks similar to what others are trying to do. As long as we enable it very explicitly through configuration and with the appropriate permissions to allow it, I think we can support it.

Do you mind creating an RFE describing the use case and the requirements? We would then invite others to chime in and give their thoughts.

## doflo-dfa (25 Jan 2023)

Understood .. and honestly as long as there are guardrails anything implemented would have to be more secure than some of the other workaround solutions that are being proposed... 

Even if there was a concept of a hybrid client that allowed for a service account attached to a public client ... or essentially allowing limited public mode (secretless) interactions on a confidential client  

## pedroigor (25 Jan 2023)

> Understood .. and honestly as long as there are guardrails anything implemented would have to be more secure than some of the other workaround solutions that are being proposed...

Exactly. When the token exchange was implemented, it was still a draft in IET. As a result, we ended up not implementing some parts of the spec like (or doing it wrong):

* The standard makes a clear distinction between the `delegation` and `impersonation` semantics and we mix them both. For us, impersonation is what the specs define as delegation. And our "impersonation" (which is a delegation accordingly to the specs) is not based on standards and does not support key params and controls related to the actor (the entity actually impersonating another subject)
* The specs do not define how to support token exchanges for public clients. The focus there is service chaining and securing RS-RS communication. The specs is open to how public clients should be supported and we ended up with some behaviors (like the use case you are proposing) that are at the very minimum contradictory and wrong if you take into account how sensitive it is to allow token exchanges.
* We do not properly implement RT support. For instance, by default, we should not issue RTs at all because the spec is focused on the RS-RS use case. We need to be more strict about issuing RTs and probably introduce permission to explicitly allow it for a particular client/token exchange context.

The use case you are proposing is an example of how contradictory it is to issue a token, through token exchange, from a confidential client (or even from another public client) to a public client. It does not make sense given that access tokens are meant to access protected resources at an RS.

Now, if you use a BFF style as per that spec I mentioned, then you have the flexibility to customize the specs (and implementation) accordingly to your needs and security requirements. That draft around SPAs is very informative about how to properly and securely support SPAs.


## doflo-dfa (26 Jan 2023)

I think the most common use case is simply one of conferring identity ... I mean moving from confidential to public that is all I would assume ... for that scenario you are only protecting what you are comfortable protecting via a very public contract and so simply being able to steer the audience correctly while providing a access refresh and id token ... that probably solves a majority of the use cases at least from what I can glean from the comments here.

Even if it requires a second token exchange using the public client via  the access token that spans from the confidential to public client that would probably be acceptable to most.

Private Client -> [public client azp access_token] -> public client -> [properly based access refresh and (id) ]

? I mean the two problems that were blockers were the azp not including the destination client when setting the audience and then the fact that the refresh token pointed to the original client ... so the above scenario ... while it invokes an extra non requested subject exchange ... still keeps things sane ?

## Ketec (17 Feb 2023)

User Z can impersonate B,C
D can impersonate only E,F.

Or even each group having "group admins" who can only manage and impersonate users within their group. The KC impersonation role does not allow that. It's just a generic superadmin feature that grants access to impersonate everyone.

This is why the confidential -> public is important - you can control who and if gets to impersonate who through your own secure API layer. Or even set up User granted access to impersonate them with a time limit.



## franzmoca (22 Feb 2023)

I am trying to use the external to internal token exchange to import an user from an external idp and then use the generated token in a web application using the JS adapter.

When I try to init the javascript adapter with the generated token I encounter the same issue with the refresh token.

What would be the correct way to implement my use case?

For now as a workaround I am considering implementing a custom TokenExchangeProvider SPI that sets the azp to the targetClient, but I would prefer a more robust and maintenable solution.

## Ketec (22 Feb 2023)

There isn't.  That's the conclusion I reached after two days of trialing potential alternatives.

So today I had to set up a maven project, extend default spi (annoying since you can't access lots of private properties). Build and deploy jar - with default as ID (docker build spi override refused to swap to custom id).

It now needs rebuild and testing after every kc update.

There is also a third line that needs a fix - for refresh token it looks at source client if it's allowed, not target.



## EmDee (15 Mar 2023)

> There is also a third line that needs a fix - for refresh token it looks at source client if it's allowed, not target.

This is what confuses me _a lot_. What is the sense in being able to request a refresh token (via `requested_token_type`: `urn:ietf:params:oauth:token-type:refresh_token`), but _not_ being able to refresh the token?

## gassan (30 Jun 2023)

Use keycloak 17 with  target id and target secret.
They do not even want to introduce a config variable to disable the access_token.auz == client_id check introduced in kk18.

## nschipperbrainsmith (27 Oct 2023)

> ncept of a hybrid client that allowed for a service account attached to a public client ... or essentially allowing limited public mode (secretless) intera

After putting in quite some effort into setting up the token exchange logic, so an admin user could impersonate another user. And working through the hurdles of the fact it is also still semi in preview, I also hit upon this issue. The flow from within Keycloak sadly was and is too difficult for the support staff of the project. And this seemed like a nice, sensible way to go about it. First, I request the API server to exchange the token for the user and then refresh the client with the newly received tokens using the `Keycloak.init()` method. This sadly always results in: 

`<<keycloak url>>/realms/<<realm>>/protocol/openid-connect/token` 400 bad request
`Invalid refresh token. Token client and authorized client don't match`

Meaning, like most others here, I would have to build a BFF-proxy just for one piece of functionality that I would be more than comfortable configuring through a setting. Allowing the confidential client to exchange the token to the public client with which the users on the frontend login. With the correct `AUZ`

**update**:

For now at least till the end of the year we opted to overwrite the TokenExchangeProvider based on the DefaultTokenExchangeProvider. And modify the AUZ directly within it by changing lines:

` responseBuilder.accessToken.issuedFor(targetClient.clientId)`

And 

`responseBuilder.refreshToken.issuedFor(targetClient.clientId)`

Within the `exchangeClientToOIDCClient` method

