#TransactionProxyFactoryBean failes if no transactionAttributes were set [SPR-3]

Owner: spring-projects

Repo: spring-framework

Labels: in: data type: bug 

## spring-projects-issues (02 Dec 2003)

**[Lars Hoss](https://jira.spring.io/secure/ViewProfile.jspa?name=woeye)** opened **[SPR-3](https://jira.spring.io/browse/SPR-3?redirect=false)** and commented

Snippet from applicationContext.xml:

\<bean id="mooService" class="org.springframework...TransactionProxyFactoryBean">
\<property name="transactionManager">\<ref local="myTransactionManager"/>\</property>
\<property name="target">\<ref local="mooTarget"/>\</property>
\</bean>
Line 175 in TransactionProxyFactoryBean crashes with a NullPointerException because it tries to iterate over a keySet which is null (in my case).


---

**Affects:** 1.0 M3


## spring-projects-issues (02 Dec 2003)

**[Rod Johnson](https://jira.spring.io/secure/ViewProfile.jspa?name=rod.johnson)** commented

Thanks Lars. I've added a check so that there's a graceful error message about improper usage in this case.


## spring-projects-issues (02 Dec 2003)

**[Lars Hoss](https://jira.spring.io/secure/ViewProfile.jspa?name=woeye)** commented

This implies that there's no default setting, e.g. PROPAGATE_REQUIRED? Wouldn't it be usefull to have a default setting?


## spring-projects-issues (02 Dec 2003)

**[Rod Johnson](https://jira.spring.io/secure/ViewProfile.jspa?name=rod.johnson)** commented

Are you envisaging a default for all methods? I envisaged no attributes as meaning that nothing was transactional, in which case a transactional proxy doesn't make sense.

Perhaps we should raise this discussion on the dev list.


## spring-projects-issues (04 Dec 2003)

**[Dmitriy Kopylenko](https://jira.spring.io/secure/ViewProfile.jspa?name=dmitriy)** commented

Updated to the correct component - SpringTX


## spring-projects-issues (11 Dec 2003)

**[Dmitriy Kopylenko](https://jira.spring.io/secure/ViewProfile.jspa?name=dmitriy)** commented

Closed since it is resolved


## spring-projects-issues (01 Oct 2013)

**[anjaiah methuku](https://jira.spring.io/secure/ViewProfile.jspa?name=anjaiah)** commented

working fine TransactionProxyFactoryBean class


