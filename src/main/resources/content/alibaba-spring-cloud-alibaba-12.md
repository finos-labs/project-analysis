#nacos config support sharing configuration with multi Applications

Owner: alibaba

Repo: spring-cloud-alibaba

Labels: enhancement discussion nacos 

## flystar32 (16 Sept 2018)



## HaojunRen (16 Sept 2018)

Support global and partial config, that means duplicated properties set into global config. Users can only change global config to take lots of services effect

## victorydsk (07 Nov 2018)

Nacos can refer to spring cloud, global config affects all applications, and individual config affects single application. Set a global label, like group, every application belong to this group, share the same config.When application starts, register itself to it's group.

## KeRan213539 (21 Nov 2018)

感觉现在的配制方式不是很灵活,如果要多个应用共享配制文件的话,通过指定 spring.cloud.nacos.config.prefix + profile 的方式就有点类似分组了,但是 Nacos 本身就有 group 的概念...
但是如果通过指定group的方式,加载该group下的全部配制,也不合理.
个人建议除了现有的规则指定配制文件的方式外,可以增加单独指定一些配制文件的方式,并且规则指定的配制文件可以不存在,不存在就忽略,或者不管什么方式指定的,不存在都忽略

## pbting (27 Nov 2018)

根据以上讨论，Spring Cloud Alibaba 组成员初步决定：通过 **自命名 dataid 来实现多个应用间可共享配置**。届时SCA Nacos Config 会新增加一个配置，用来列举多个应用共享配制文件的dataid。如下所示：

`spring.cloud.nacos.config.shared-dataids=global.yaml,app-common.yaml,app-local-common.yaml`

经过这么一配置，应用程序再启动的时候依次加载DataId为 **global.yaml**,**app-common.yaml**,**app-local-common.yaml** 的配置。

当使用这种方式来实现应用间的共享配置时，多个共享配置间我们**约定**：
1.  **优先级关系**：按照配置出现的先后顺序，即后面的优先级要高于前面的。
2.  **Nacos 上的Group 命名**：共享配置在Nacos 上的 Group 命名为 **DEFAULT_GROUP**。（_**解释一下这部分为什么没有开放到业务方自己命名**_。因为这个是在多个应用间的共享配置，多个应用的负责人可能是来自不同的项目小组或者部门，那这个时候如果大家都可以对这个Group 自命名的话，那前提必须要协商好命名要一致，否则在没有沟通清楚的前提下必然会导致有些应用获取不到共享配置，这无疑是增加了跨团队沟通协作的成本。）

同时对于共享配置，我们也提供可配置的方式来自由的选择某个共享配置是否支持动态刷新。**在实现的过程中默认是不支持共享配置的动态刷新的**，因为这部分的配置变更有可能会影响另外应用对共享配置的引用。

这种方式的优点在于：
1. dataid的命名方式完全交给业务方本身，不受 SCA Nacos Config Starter 实现的束缚。
1. dataid的命名方式既可以参考第一种方式来命名，又可以充分的发挥主观能动性，结合自己实际的业务给dataid命名。
1. 减少了多个应用间如果file-extension不一致，为每个 file-extension 多加这么一个配置的麻烦。
1. 当使用这种方式时，不会为这些共享配置强制绑定一个 file-extenson，即可以直接在我们暴露出来的一个变量中 dataid以file-extension 结尾。**如果没有显示的说明，这个时候就会以${spring.cloud.nacos.config.file-extension}为准**。

## KeRan213539 (27 Nov 2018)

> 根据以上讨论，Spring Cloud Alibaba 组成员初步决定：通过 **自命名 dataid 来实现多个应用间可共享配置**。届时SCA Nacos Config 会新增加一个配置，用来列举多个应用共享配制文件的dataid。如下所示：
> 
> `spring.cloud.nacos.shared.dataids=global.yaml,app-common.yaml,app-local-common.yaml`
> 
> 经过这么一配置，应用程序再启动的时候依次加载DataId为 **global.yaml**,**app-common.yaml**,**app-local-common.yaml** 的配置。
> 
> 当使用这种方式来实现应用间的共享配置时，**多个共享配置间的一个优先级关系我们约定**：按照配置出现的先后顺序，即后面的优先级要高于前面的。
> 
> 这种方式的优点在于：
> 
> 1. dataid的命名方式完全交给业务方本身，不受 SCA Nacos Config Starter 实现的束缚。
> 2. dataid的命名方式既可以参考第一种方式来命名，又可以充分的发挥主观能动性，结合自己实际的业务给dataid命名。
> 3. 减少了多个应用间如果file-extension不一致，为每个 file-extension 多加这么一个配置的麻烦。
> 4. 当使用这种方式时，不会为这些共享配置强制绑定一个 file-extenson，即可以直接在我们暴露出来的一个变量中 dataid以file-extension 结尾。**如果没有显示的说明，这个时候就会以${spring.cloud.nacos.config.file-extension}为准**。

期待~~~~

## pguo65535 (27 Nov 2018)

group 何处安放呢？

## pbting (27 Nov 2018)

> group 何处安放呢？
共享配置在Nacos 上的 Group 命名为 DEFAULT_GROUP。

## pguo65535 (30 Nov 2018)

关于group这个，如果我想这样（就是想要不同group下的两个配置合并到一起），具体怎么配置？

------|------------dataid------------------ |------   group  ------| 

Middleware Common Configs:
---------------------------------
cfg1:  common_middleware_component.cfg +  middleware_group     //公共组件的公用配置
                        k1 =v1, k2=v2

BU configs:
---------------------------------
cfg2:  business_middleware.cfg  +   BU1_group         //特殊场景的特殊配置
                       k1=v1_spec, k3=v3

cfg3: business_app1.cfg + BU1_group
                      k4=v4

want to merge cfg1 + cfg2 + cfg3

## pbting (04 Dec 2018)

> 关于group这个，如果我想这样（就是想要不同group下的两个配置合并到一起），具体怎么配置？
> 
> ------|------------dataid------------------ |------ group ------|
> 
> ## Middleware Common Configs:
> cfg1: common_middleware_component.cfg + middleware_group //公共组件的公用配置
> k1 =v1, k2=v2
> 
> ## BU configs:
> cfg2: business_middleware.cfg + BU1_group //特殊场景的特殊配置
> k1=v1_spec, k3=v3
> 
> cfg3: business_app1.cfg + BU1_group
> k4=v4
> 
> want to merge cfg1 + cfg2 + cfg3

经过小组内部的进一步讨论，这种情况确实比较常见。我们在原来的设计基础上进行了升级。总体方案大致是：像dataid，group,是否支持刷新等额外配置都以配置的形式开放出来让业务方根据自己的需要做定制化的配置。使用方式如下所示：

spring.cloud.nacos.config.ext-config[0].data-id=data-id-zero.properties

spring.cloud.nacos.config.ext-config[1].data-id=data-id-one.properties
spring.cloud.nacos.config.ext-config[1].group=BU_TAOBAO_GROUP

spring.cloud.nacos.config.ext-config[2].data-id=data-id-three.properties
spring.cloud.nacos.config.ext-config[2].group=BU_ALIWARE_GROUP
spring.cloud.nacos.config.ext-config[2].refresh=true





