#Consider introducing a special test scope 

Owner: spring-projects

Repo: spring-framework

Labels: in: test status: duplicate type: enhancement 

## mdeinum (10 Jan 2019)

Currently we can use `@DirtiesContext` to restart the full `ApplicationContext` used by the test class. However sometimes this might be a heavy operation and introduce very lengthy tests. Introducing a special `@TestScope` might reduce the pain a little. 

## mdeinum (10 Apr 2020)

Duplicate of #18606

