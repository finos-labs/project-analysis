#Reset Credentials Flow does not delete existing OTP

Owner: keycloak

Repo: keycloak

Labels: area/authentication kind/enhancement release-notes 

## bodograumann (09 Nov 2021)

### Describe the bug

In the default keycloak config, the “Reset Credentials” flow contains a conditional action to “Reset OTP” when the user has 2FA enabled. That means when the user chooses “Forgot Password?” he will first need to reconfigure the OTP and after that choose a new password.

During the “Reset OTP” action, the existing authenticator should be deleted. This is not what is happening currently. Instead the existing authenticator is kept and an additional authenticator is created.

The user is not aware of this and will generally choose the same name for the authenticator as he used before. Thus he will be unable to differentiate between the old and new authenticators. I.e. after resetting his password the user will have a hard time logging in.

Even if the user was so lucky as to use a new name for the authenticator, he will be confused by having to select which authenticator to use, when one is obsolete.

### Version

15.0.2

### Expected behavior

The existing OTP credential is replaced by the new credential when resetting a password.

### Actual behavior

A new otp credential entry is added to the user.

### How to Reproduce?

Have a user with 2FA enabled and configured, then use the "Forgot Password?" function for the user.

### Anything else?

Originally reported here: https://issues.redhat.com/browse/KEYCLOAK-14640

## thomasmicro (11 Nov 2021)

I suggest asking the user whether he wants to replace OTP, add it as secondary OTP or keep it. If I lost my password I don't want to be forced to reset OTP.

Maybe even seperating Reset OTP as a custom AIA - so that a Admin can choose to seperate reseting E-Mail and reseting OTP. (Meaning that a hacked E-Mail account can't automatically lead to a hacked Account secured with OTP)

## prashantch80 (27 Jul 2022)

Any ETA for this? This is required, if user forgot password for multiple times, then its annoying for him to find the correct one.

## danielFesenmeyer (23 Aug 2022)

What I like to add: The order of the autenticator applications, both in account-console and the OTP login form, currently is ascending by creation timestamp. So if a user "resets OTP", the old one is displayed before the new one and even selected by default. When the user has not selected descriptive labels like "work iphone" or "personal mobile", the user just can't know which one is relevant.

## michalpomykala (15 Mar 2023)

Hi, any update in this subject?. Our users strugling the same problem. Do you see any chances to address the problem? 
![image](https://user-images.githubusercontent.com/109679970/225382276-2d6c1226-366f-4e96-ba1b-c86176f6e324.png)


## artur-baltabayev (25 May 2023)

Hey guys. I just provided a PR for this. It makes the authenticator configurable and let's you choose if you want the user to remove one specific OTP or all of them at once. Feel free to check it out. Any feedback is welcome !

