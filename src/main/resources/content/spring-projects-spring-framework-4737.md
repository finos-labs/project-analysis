#testDateTimeElement does not pass!!! [SPR-2]

Owner: spring-projects

Repo: spring-framework

Labels: in: web type: bug 

## spring-projects-issues (01 Dec 2003)

**[Dmitriy Kopylenko](https://jira.spring.io/secure/ViewProfile.jspa?name=dmitriy)** opened **[SPR-2](https://jira.spring.io/browse/SPR-2?redirect=false)** and commented

Imported from sourceforge==================>

---

A fresh checked out CVS copy of spring fails to pass
the test :
org.springframework.web.servlet.view.xslt.FormatHelperTests
testDateTimeElement
Interestingely enough I am in the US using a US locale,
when the test is referring to a UK locale.....
The following asserts do not pass because :
"day-of-week"="Tuesday" not Wednesday
"day-of-month"=23 not 24

el = (Element)
e.getElementsByTagName("day-of-week").item(0);
assertTrue(
"Wednesday".equals(el.getFirstChild().getNodeValue() ));

el = (Element)
e.getElementsByTagName("day-of-month").item(0);
assertTrue(
"24".equals(el.getFirstChild().getNodeValue() ));

I have no idea on how to provide a resolution.
I leave to you .

Please note that the last check-in you made was about
that very same test (see version 1.5 change log)
"mysteriously fails on some installation"
On mine, hours="3", not 12....

BTW, it is not very explicit to use assertTrue for
strings, when assertEquals is much more explicit, and
assertEquals with a description is always preferred.
Philippe O.

---

**Affects:** 1.0 RC1


## spring-projects-issues (02 Dec 2003)

**[Darren Davison](https://jira.spring.io/secure/ViewProfile.jspa?name=darren.davison)** commented

Date: 2003-11-13 17:04
Sender: davison
Logged In: YES
user_id=642995

this is fixed by temporarily setting the system default TZ in
the test method.  Fix should be in CVS from today (13/11/03)


