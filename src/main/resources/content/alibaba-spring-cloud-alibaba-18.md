#The port register to nacos discovery server must be server.port

Owner: alibaba

Repo: spring-cloud-alibaba

Labels: bug nacos 

## HaojunRen (17 Sept 2018)

I have set two ports in application.properties as follows:
server.port=1100
management.port=5100

After the service started, visit http://127.0.0.1:8080/nacos/v1/ns/instances?serviceName=[serviceId], you can find the port value is 5100, it must be 1100

## flystar32 (17 Sept 2018)

extend AbstractAutoServiceRegistration instead of implements AutoServiceRegistration

