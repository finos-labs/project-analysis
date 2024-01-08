#feature flags broken for scripts

Owner: keycloak

Repo: keycloak

Labels: kind/bug area/core 

## kfox1111 (09 Nov 2021)

### Describe the bug

I tried making a javascript mapper as described here:

https://www.keycloak.org/docs/latest/server_development/#_script_providers

 

Setting the flag:

-Dkeycloak.profile.feature.scripts=enabled

didn't enable it to work.

Adding:

-Dkeycloak.profile.feature.upload_scripts=enabled

did. Upload_scripts which is deprecated should not be required to have the javascript in a jar based mapper show up in the ui.

### Version

15.0.2

### Expected behavior

-Dkeycloak.profile.feature.scripts=enabled

should work

### Actual behavior

Doesn't work without -Dkeycloak.profile.feature.upload_scripts=enabled


### How to Reproduce?

Set -Dkeycloak.profile.feature.scripts=enabled
 and scripts wont show. Add -Dkeycloak.profile.feature.upload_scripts=enabled too and scripts show up as expected.
 

### Anything else?

_No response_

## BlackKerio (05 Jan 2022)

@kfox1111 when using the switch -Dkeycloak.profile.feature.scripts=enabled

I tried to create a script for authenticators.

When logging in a user is reported error about missing services directory etc.

after adding an empty services directory to the .jar file, the script works fine

https://github.com/keycloak/keycloak-documentation/issues/1357

## mkreis (17 Jan 2022)

I could not reproduce this bug on Keycloak 16.1.0, setting only `-Dkeycloak.profile.feature.scripts=enabled` was sufficient to run JavaScript providers. (The detailed steps I did are described here: https://github.com/keycloak/keycloak/issues/9571) 


## abstractj (22 Mar 2022)

I could not reproduce the issue you mentioned using the latest releases (17.0.0), please give it a try and reopen this issue if still valid.

