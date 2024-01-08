#Please let others reuse the powerful Condition implementations provided with Spring Boot

Owner: spring-projects

Repo: spring-boot

Labels: 

## joshlong (07 Sept 2013)

Would you please those conditions in the DataSourceAutoConfiguration, and indeed all Conditions in Spring Boot, public please? I think a lot of them would be useful as basis for other conditions developers might want to write, and they compose well since u have convenience methods like matchesAny(). 


## dsyer (18 Sept 2013)

They are all public as far as I know. Probably all candidates for moving to Spring Core as well, but we are going to wait until the feature set stabilizes before thinking about that.


