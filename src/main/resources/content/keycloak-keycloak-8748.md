#View group membership ( account console)

Owner: keycloak

Repo: keycloak

Labels: kind/feature release-notes 

## cgeorgilakis (09 Nov 2021)

### Description

In the user account console, an extra menu will exist for user groups in order to be able to view the groups he/she is a member of. View groups ui could be similar to the group tab in the new admin console. Role  'view-groups' , which allows controlling which users can view groups, will be added to the account client.

Transferred issue from Jira: https://issues.redhat.com/browse/KEYCLOAK-17668
Keycloak-dev discussion: https://groups.google.com/g/keycloak-dev/c/4ZXObLaBc7I
PR: https://github.com/keycloak/keycloak/pull/7933 

Although I have done minor changes requested, PR is not accepted yet.

### Discussion

_No response_

### Motivation

Users can not see group information from the account console. Users should at least be able to view the groups that they are a member of. This is essential for knowing their attributes and roles based on group membership.

### Details

![groups](https://user-images.githubusercontent.com/55974447/140930531-928e2c9e-6f1b-4f5f-9fdb-20c7e812deaa.PNG)
Screenshot from ui

## cgeorgilakis (09 Nov 2021)

Could you finish review in PR?

Last @thomasdarimont comment was: 

> Minor formatting nitpick - otherwise this LGTM.

Generally, how I can link a PR with a github issue?

## xianli123 (08 Sept 2022)

Hi @cgeorgilakis , this is Kun, from UXD team and I am working on the new design of Keycloak. 

With regard to this feature, does it only allow users to check the group they belong to, not edit the group? Thanks.

## cgeorgilakis (08 Sept 2022)

@xianli123 Yes, this feature is only for viewing groups that user is associated with.
It will be part of version 20. You can see [my documentation PR](https://github.com/keycloak/keycloak-documentation/pull/1673).


## xianli123 (08 Sept 2022)

Thanks @cgeorgilakis I see. I will update the UI based on your explanation and suggestion. 

## xianli123 (15 Sept 2022)

Hi @cgeorgilakis, I updated the design of the Groups, here is the link: https://marvelapp.com/prototype/ige09a4/screen/88721445. Please check it and feel free to leave your comments if you have some suggestions.

## cgeorgilakis (19 Sept 2022)

@xianli123 It looks good

## xianli123 (20 Sept 2022)

@cgeorgilakis Thanks. I will share the design with UI developer team. If they don't have any comments, we can add this new feature in the new UI. 

## ssilvert (20 Sept 2022)

@cgeorgilakis To be clear, we are not asking you to update anything.  We are rebuilding account console in keycloak/keycloak-ui repo.  So we will use the new design when we do that.  Thanks for your feedback.

