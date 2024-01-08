#Spring Cloud Api Gateway registered ip address is wrong

Owner: alibaba

Repo: spring-cloud-alibaba

Labels: question wontfix 

## HaojunRen (18 Sept 2018)

In my machine, I have two ip addresses(192.168.0.3 and 10.0,75.1), zuul and all micro-services registers with 10.0.75.1, but api gateway with 192.168.0.3, so route from gateway to micro-service will be failed

## HaojunRen (18 Sept 2018)

If I toggle to eureka, all work fine with 10.0.75.1

## HaojunRen (21 Sept 2018)

It's difficult to reproduce

