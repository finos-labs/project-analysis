#Introducing Expression Language Support [SPR-4]

Owner: spring-projects

Repo: spring-framework

Labels: in: core type: enhancement 

## spring-projects-issues (05 Dec 2003)

**[Alef Arendsen](https://jira.spring.io/secure/ViewProfile.jspa?name=alef.arendsen)** opened **[SPR-4](https://jira.spring.io/browse/SPR-4?redirect=false)** and commented

Mike's been talking about introducing OGNL support. Seems like a nice idea to me, though not really feasible for the 1.0 release I think. But hey, let's put it in here so we won't forget...

Mike's mail (December 2nd):

---

Or an even better idea... how about supporting OGNL within the Spring config files? (like Xwork does)

This would be _awesome_ and I just found a second use case for it (the very minute Rob's email came in).

My use case - Maps.

The Map syntax is nice, but not very useful in practicality I'm finding as the key and value of the map are usually related, for instance I often want to put a list of referenced beans into a map, with ref.getName() (or some
method) called for the key.

At the moment I have to add a setBeans(List) method to my class, and then in that setter iterate and add to a map - smelly!

If we allowed OGNL expressions, it would be very simple to do this in the config file itself:

\<property value="myMapProp">
\<map>
\<entry>
\<key>$referencedBean.name\</key>
\<value>\<ref bean="referencedBean" />\</value>
\</entry>
... More entries
\</map>
\</property>

I'm sure there are a million other places where OGNL would be useful too, but AFAIK the above can't be done _without_ it?

Or have I just been at this desk far too long?

M

---

**Affects:** 1.0 M4

**Attachments:**
- [patch.zip](https://jira.spring.io/secure/attachment/10439/patch.zip) (_4.40 kB_)

**Issue Links:**
- #5713 Introduce OGNL and OgnlExpressionFactoryBean  (_**"is duplicated by"**_)

**Referenced from:** commits https://github.com/spring-projects/spring-integration/commit/e57103466e24ca97886e8c061b28e31070e24ff8

8 votes, 6 watchers


## spring-projects-issues (06 Dec 2003)

**[Dmitriy Kopylenko](https://jira.spring.io/secure/ViewProfile.jspa?name=dmitriy)** commented

Does it belong in SpringCORE or in SpringWEB?


## spring-projects-issues (06 Dec 2003)

**[Dmitriy Kopylenko](https://jira.spring.io/secure/ViewProfile.jspa?name=dmitriy)** commented

Changed priority to trivial


## spring-projects-issues (06 Dec 2003)

**[Dmitriy Kopylenko](https://jira.spring.io/secure/ViewProfile.jspa?name=dmitriy)** commented

Let's assign priority of "trivial" to new proposed features.


## spring-projects-issues (01 Apr 2004)

**[Matthew Payne](https://jira.spring.io/secure/ViewProfile.jspa?name=matthew.payne)** commented

Seems to go hand and hand with the validator support feature meantioned in RC 1.1.

http://opensource.atlassian.com/projects/spring/browse/SPR-69


## spring-projects-issues (20 Jan 2005)

**[Guillaume Poirier](https://jira.spring.io/secure/ViewProfile.jspa?name=gpoirier)** commented

I sent this patch to the dev list before, but I figure I should upload it to JIRA so that it doesn't get lost.  The patch has been made against an earlier CVS snapshot, but it seems to still work against the latest version.  It does not include the modifications discussed on the mailling list though, such as using \<expr type="ognl"> instead of \<ognl>.  If my contribution is accepted and we can agreed on the syntax, then I'll complete it to include such changes.


## spring-projects-issues (19 Jul 2005)

**[Keith Donald](https://jira.spring.io/secure/ViewProfile.jspa?name=kdonald)** commented

The spring-binding module in spring-projects, used currently by webflow but usable standalone, features an expression parsing and evaluation abstraction that features OGNL.

Keith


## spring-projects-issues (26 Jan 2006)

**[Rob Harrop](https://jira.spring.io/secure/ViewProfile.jspa?name=robh)** commented

I'm moving his to the 2.1 timeframe and renaming it. We **do** want to include expression language support, but since OGNL is no longer actively maintained (last release was August 2004) we will be evaluating other areas. One are that we are working in is common EL across Java and .NET.

Rob


## spring-projects-issues (10 Mar 2006)

**[Rob Harrop](https://jira.spring.io/secure/ViewProfile.jspa?name=robh)** commented

Unscheduling this since I want to spend more time defining what this will actually look like in the ApplicationContext.


