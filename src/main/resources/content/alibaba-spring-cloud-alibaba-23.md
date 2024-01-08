#Support re-register feature

Owner: alibaba

Repo: spring-cloud-alibaba

Labels: enhancement nacos 

## HaojunRen (18 Sept 2018)

When I start services first, then start nacos server, I hope the services will re-register again

## flystar32 (20 Sept 2018)

good advice, We are evaluating where this issue should be implemented,, in Nacos or spring cloud nacos discovery.
好的建议，我们正在评估这一部分工作应该在哪一部分实现，Nacos 或 spring cloud Nacos discovery.

## gurmyCheng (10 Nov 2018)

求助，springcloud中注册中心替换成nacos，用zuul代理。在zuul微服务启动之后，新注册的微服务能在nacos服务列表查看到，但通过zuul无法访问，非得zuul重启后才能访问新注册的服务。。。同样的问题，等待解决方案

## flystar32 (12 Dec 2018)

already done in nacos naming client

