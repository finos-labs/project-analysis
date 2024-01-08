#Frequent heartbeat log prints

Owner: alibaba

Repo: spring-cloud-alibaba

Labels: component bug nacos 

## HaojunRen (17 Sept 2018)

Please refer to log file, you can find a lot of hearbeat logs print in a very short time:

discovery 2018-09-17 20:13:41,923 INFO [com.alibaba.nacos.naming.beat.sender] c.a.n.c.naming [Slf4jLogger.java:107] - [BEAT] [] [] send beat to server: 
discovery 2018-09-17 20:13:41,924 INFO [com.alibaba.nacos.naming.beat.sender] c.a.n.c.naming [Slf4jLogger.java:99] - [] [] [] Request from server: http://localhost:8080/nacos/v1/ns/api/clientBeat?beat=%7B%22cluster%22%3A%22DEFAULT%22%2C%22dom%22%3A%22discovery-springcloud-example-b%22%2C%22ip%22%3A%22192.168.0.107%22%2C%22port%22%3A5201%7D&encoding=UTF-8&dom=discovery-springcloud-example-b&
discovery 2018-09-17 20:13:51,923 INFO [com.alibaba.nacos.naming.beat.sender] c.a.n.c.naming [Slf4jLogger.java:107] - [BEAT] [] [] send beat to server: 
discovery 2018-09-17 20:13:51,925 INFO [com.alibaba.nacos.naming.beat.sender] c.a.n.c.naming [Slf4jLogger.java:99] - [] [] [] Request from server: http://localhost:8080/nacos/v1/ns/api/clientBeat?beat=%7B%22cluster%22%3A%22DEFAULT%22%2C%22dom%22%3A%22discovery-springcloud-example-b%22%2C%22ip%22%3A%22192.168.0.107%22%2C%22port%22%3A5201%7D&encoding=UTF-8&dom=discovery-springcloud-example-b&
discovery 2018-09-17 20:14:01,923 INFO [com.alibaba.nacos.naming.beat.sender] c.a.n.c.naming [Slf4jLogger.java:107] - [BEAT] [] [] send beat to server: 
discovery 2018-09-17 20:14:01,925 INFO [com.alibaba.nacos.naming.beat.sender] c.a.n.c.naming [Slf4jLogger.java:99] - [] [] [] Request from server: http://localhost:8080/nacos/v1/ns/api/clientBeat?beat=%7B%22cluster%22%3A%22DEFAULT%22%2C%22dom%22%3A%22discovery-springcloud-example-b%22%2C%22ip%22%3A%22192.168.0.107%22%2C%22port%22%3A5201%7D&encoding=UTF-8&dom=discovery-springcloud-example-b&
discovery 2018-09-17 20:14:01,994 INFO [com.taobao.vipserver.serverlist.updater] c.a.n.c.naming [Slf4jLogger.java:99] - [] [] [] server list provided by user: [localhost:8080]
discovery 2018-09-17 20:14:11,924 INFO [com.alibaba.nacos.naming.beat.sender] c.a.n.c.naming [Slf4jLogger.java:107] - [BEAT] [] [] send beat to server: 
discovery 2018-09-17 20:14:11,925 INFO [com.alibaba.nacos.naming.beat.sender] c.a.n.c.naming [Slf4jLogger.java:99] - [] [] [] Request from server: http://localhost:8080/nacos/v1/ns/api/clientBeat?beat=%7B%22cluster%22%3A%22DEFAULT%22%2C%22dom%22%3A%22discovery-springcloud-example-b%22%2C%22ip%22%3A%22192.168.0.107%22%2C%22port%22%3A5201%7D&encoding=UTF-8&dom=discovery-springcloud-example-b&
discovery 2018-09-17 20:14:21,923 INFO [com.alibaba.nacos.naming.beat.sender] c.a.n.c.naming [Slf4jLogger.java:107] - [BEAT] [] [] send beat to server: 
discovery 2018-09-17 20:14:21,925 INFO [com.alibaba.nacos.naming.beat.sender] c.a.n.c.naming [Slf4jLogger.java:99] - [] [] [] Request from server: http://localhost:8080/nacos/v1/ns/api/clientBeat?beat=%7B%22cluster%22%3A%22DEFAULT%22%2C%22dom%22%3A%22discovery-springcloud-example-b%22%2C%22ip%22%3A%22192.168.0.107%22%2C%22port%22%3A5201%7D&encoding=UTF-8&dom=discovery-springcloud-example-b&
discovery 2018-09-17 20:14:31,923 INFO [com.alibaba.nacos.naming.beat.sender] c.a.n.c.naming [Slf4jLogger.java:107] - [BEAT] [] [] send beat to server: 
discovery 2018-09-17 20:14:31,924 INFO [com.alibaba.nacos.naming.beat.sender] c.a.n.c.naming [Slf4jLogger.java:99] - [] [] [] Request from server: http://localhost:8080/nacos/v1/ns/api/clientBeat?beat=%7B%22cluster%22%3A%22DEFAULT%22%2C%22dom%22%3A%22discovery-springcloud-example-b%22%2C%22ip%22%3A%22192.168.0.107%22%2C%22port%22%3A5201%7D&encoding=UTF-8&dom=discovery-springcloud-example-b&
discovery 2018-09-17 20:14:31,995 INFO [com.taobao.vipserver.serverlist.updater] c.a.n.c.naming [Slf4jLogger.java:99] - [] [] [] server list provided by user: [localhost:8080]
discovery 2018-09-17 20:14:41,923 INFO [com.alibaba.nacos.naming.beat.sender] c.a.n.c.naming [Slf4jLogger.java:107] - [BEAT] [] [] send beat to server: 
discovery 2018-09-17 20:14:41,924 INFO [com.alibaba.nacos.naming.beat.sender] c.a.n.c.naming [Slf4jLogger.java:99] - [] [] [] Request from server: http://localhost:8080/nacos/v1/ns/api/clientBeat?beat=%7B%22cluster%22%3A%22DEFAULT%22%2C%22dom%22%3A%22discovery-springcloud-example-b%22%2C%22ip%22%3A%22192.168.0.107%22%2C%22port%22%3A5201%7D&encoding=UTF-8&dom=discovery-springcloud-example-b&
discovery 2018-09-17 20:14:51,923 INFO [com.alibaba.nacos.naming.beat.sender] c.a.n.c.naming [Slf4jLogger.java:107] - [BEAT] [] [] send beat to server: 
discovery 2018-09-17 20:14:51,924 INFO [com.alibaba.nacos.naming.beat.sender] c.a.n.c.naming [Slf4jLogger.java:99] - [] [] [] Request from server: http://localhost:8080/nacos/v1/ns/api/clientBeat?beat=%7B%22cluster%22%3A%22DEFAULT%22%2C%22dom%22%3A%22discovery-springcloud-example-b%22%2C%22ip%22%3A%22192.168.0.107%22%2C%22port%22%3A5201%7D&encoding=UTF-8&dom=discovery-springcloud-example-b&

## HaojunRen (17 Sept 2018)

Refer to https://github.com/alibaba/nacos/issues/59

