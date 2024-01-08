#Add Brute Force Detection Lockout Event

Owner: keycloak

Repo: keycloak

Labels: kind/enhancement 

## paulbares (10 Nov 2021)

### Description

Trigger an event when the brute force protection detects that the maximum allowed number of login failures is reached for a specific user.

### Discussion

_No response_

### Motivation

We would like to notify users (via e-mail) whenever their account gets locked up due to excessive failed logins.

Our favoured approach consists of implementing an SPI which listens to a USER_LOCKED event. The event is triggered when the brute force protection detects that the maximum allowed number of login failures is reached for a specific user.

### Details

Original Jira ticket [KEYCLOAK-15985](https://issues.redhat.com/browse/KEYCLOAK-15985)

## paulbares (10 Nov 2021)

PR open a week ago linked to this issue.

## paulbares (10 May 2022)

Any update regarding this issue and the associated PR ? 

## poklakni (21 Jul 2022)

USER_UNLOCKED would be nice as well

