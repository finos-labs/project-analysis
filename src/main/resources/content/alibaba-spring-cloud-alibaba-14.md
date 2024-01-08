#It seems not register local metadata to nacos discovery server

Owner: alibaba

Repo: spring-cloud-alibaba

Labels: bug nacos 

## HaojunRen (17 Sept 2018)

1. define in properties, ex spring.cloud.nacos.discovery.metadata.group=example-service-group
2. define in NacosDiscoveryProperties class, ex 
    Map<String, String> metadata = nacosDiscoveryProperties.getMetadata();
    metadata.put("group", "example-service-group");

