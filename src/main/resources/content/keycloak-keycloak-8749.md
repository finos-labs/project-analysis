#Add an option to control the order of the event query and admin event query

Owner: keycloak

Repo: keycloak

Labels: kind/enhancement 

## leischt (09 Nov 2021)

### Description

To implement a event processing SPI, we would like to get a page of events ordered by ascending value of the field "time". 
Currently it is hard coded, that the events are ordered by descending time. 
We would like to implement an option at "EventQuery" / "JpaEventQuery" and "AdminEventQuery" / "JpaAdminEventQuery" to control the order behaviour.

### Discussion

_No response_

### Motivation

_No response_

### Details

_No response_

## leischt (15 Nov 2021)

PR: https://github.com/keycloak/keycloak/pull/8822

