#Add a realm option to make the email field non mandatory

Owner: keycloak

Repo: keycloak

Labels: kind/enhancement area/user-profile status/rejected 

## reda-alaoui (09 Nov 2021)

### Description

For a particular realm, we want to allow users to optionally provide their email address.

We thought we could do that via a Keycloak plugin. We replaced  `org.keycloak.authentication.forms.RegistrationProfile` with a custom FormAction. The latter is a copy of `org.keycloak.authentication.forms.RegistrationProfile` which does not fail the validation when the email field is blank. We had what we wanted for the registration form.

But we discovered that the account update page requires the email address. Because of that, a user that registered without email, can't update its firstname, lastname or username. We did not find any extension point allowing to modify this behaviour.

Would you accept a pull request which would:

- add new realm flag in the login tab named "Mandatory email"
- the flag would be enabled by default
- if the flag is enabled, the current behaviour would be kept
- if the flag is disabled, passing a blank email in the registration page and the account update page would succeed

![Screenshot from 2020-11-25 19-57-51](https://user-images.githubusercontent.com/2890843/140906386-ed3b4f0e-a1fa-42d8-8fb1-4bdd69020141.png)

### Discussion

_No response_

### Motivation

_No response_

### Details

_No response_

## stianst (09 Nov 2021)

@pedroigor can this be achieved already with user profiles?

## pedroigor (15 Dec 2021)

@stianst @reda-alaoui This should be achievable if you are using user profile.

However, the account page does not have yet the necessary changes to respect the metadata from user profile. So you might need to customize the account console by yourself.

## reda-alaoui (05 Jan 2022)

Well that's exactly what I was expecting to avoid:

> But we discovered that the account update page requires the email address. Because of that, a user that registered without email, can't update its firstname, lastname or username. We did not find any extension point allowing to modify this behaviour.

Regarding:

> However, the account page does not have yet the necessary changes to respect the metadata from user profile

Is there a tracker issue I can follow somewhere about that?


