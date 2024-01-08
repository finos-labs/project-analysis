#springcloud dubbo 启用rest，打成jar，执行报错

Owner: yudaocode

Repo: SpringBoot-Labs

Labels: 

## liuhonglin (03 Sept 2020)

labx-07-sca-dubbo-demo02-provider-rest
在idea里执行没有问题，打成jar, 执行报错
java.lang.IllegalStateException: Failed to load extension class (interface: interface org.apache.dubbo.rpc.Protocol, class line: org.apache.dubbo.rpc.protocol.rest.RestProtocol) in jar:file:/D:/workspace/IdeaProjects/my/SpringBoot-Labs/labx-07-spring-cloud-alibaba-dubbo/labx-07-sca-dubbo-demo02/labx-07-sca-dubbo-demo02-provider-rest/target/labx-07-sca-dubbo-demo02-provider-rest.jar!/BOOT-INF/lib/dubbo-2.7.6.jar!/META-INF/dubbo/internal/org.apache.dubbo.rpc.Protocol, cause: org/jboss/resteasy/client/jaxrs/ClientHttpEngine
        at org.apache.dubbo.common.extension.ExtensionLoader.loadResource(ExtensionLoader.java:828) ~[dubbo-2.7.6.jar!/:2.7.6]
        at org.apache.dubbo.common.extension.ExtensionLoader.loadDirectory(ExtensionLoader.java:796) ~[dubbo-2.7.6.jar!/:2.7.6]
        at org.apache.dubbo.common.extension.ExtensionLoader.loadExtensionClasses(ExtensionLoader.java:737) ~[dubbo-2.7.6.jar!/:2.7.6]
        at org.apache.dubbo.common.extension.ExtensionLoader.getExtensionClasses(ExtensionLoader.java:720) ~[dubbo-2.7.6.jar!/:2.7.6]
        at org.apache.dubbo.common.extension.ExtensionLoader.getAdaptiveExtensionClass(ExtensionLoader.java:985) ~[dubbo-2.7.6.jar!/:2.7.6]
        at org.apache.dubbo.common.extension.ExtensionLoader.createAdaptiveExtension(ExtensionLoader.java:978) ~[dubbo-2.7.6.jar!/:2.7.6]
        at org.apache.dubbo.common.extension.ExtensionLoader.getAdaptiveExtension(ExtensionLoader.java:564) ~[dubbo-2.7.6.jar!/:2.7.6]
        at org.apache.dubbo.config.ServiceConfig.<clinit>(ServiceConfig.java:118) ~[dubbo-2.7.6.jar!/:2.7.6]



java.lang.IllegalStateException: Failed to load extension class (interface: interface org.apache.dubbo.rpc.Protocol, class line: org.apache.dubbo.rpc.protocol.rest.RestProtocol) in jar:file:/D:/workspace/IdeaProjects/my/SpringBoot-Labs/labx-07-spring-cloud-alibaba-dubbo/labx-07-sca-dubbo-demo02/labx-07-sca-dubbo-demo02-provider-rest/target/labx-07-sca-dubbo-demo02-provider-rest.jar!/BOOT-INF/lib/dubbo-2.7.6.jar!/META-INF/dubbo/internal/org.apache.dubbo.rpc.Protocol, cause: org/jboss/resteasy/client/jaxrs/ClientHttpEngine
        at org.apache.dubbo.common.extension.ExtensionLoader.loadResource(ExtensionLoader.java:828) ~[dubbo-2.7.6.jar!/:2.7.6]
        at org.apache.dubbo.common.extension.ExtensionLoader.loadDirectory(ExtensionLoader.java:796) ~[dubbo-2.7.6.jar!/:2.7.6]
        at org.apache.dubbo.common.extension.ExtensionLoader.loadExtensionClasses(ExtensionLoader.java:737) ~[dubbo-2.7.6.jar!/:2.7.6]
        at org.apache.dubbo.common.extension.ExtensionLoader.getExtensionClasses(ExtensionLoader.java:720) ~[dubbo-2.7.6.jar!/:2.7.6]
        at org.apache.dubbo.common.extension.ExtensionLoader.getAdaptiveExtensionClass(ExtensionLoader.java:985) ~[dubbo-2.7.6.jar!/:2.7.6]
        at org.apache.dubbo.common.extension.ExtensionLoader.createAdaptiveExtension(ExtensionLoader.java:978) ~[dubbo-2.7.6.jar!/:2.7.6]
        at org.apache.dubbo.common.extension.ExtensionLoader.getAdaptiveExtension(ExtensionLoader.java:564) ~[dubbo-2.7.6.jar!/:2.7.6]
        at org.apache.dubbo.config.ServiceConfig.<clinit>(ServiceConfig.java:118) ~[dubbo-2.7.6.jar!/:2.7.6]


## liuhonglin (03 Sept 2020)

@YunaiV 
dubbo 里 RestProtocol 这个类：
![image](https://user-images.githubusercontent.com/13495924/92092937-4fd7a080-ee05-11ea-9c8e-83057c375356.png)
应该是 resteasy-netty4 这个jar版本不对，不知道应该用哪个


## liuhonglin (03 Sept 2020)

I got
add dependency:
        <dependency>
            <groupId>io.netty</groupId>
            <artifactId>netty-all</artifactId>
        </dependency>

        <dependency>
            <groupId>org.jboss.resteasy</groupId>
            <artifactId>resteasy-jaxrs</artifactId>
        </dependency>

        <dependency>
            <groupId>org.jboss.resteasy</groupId>
            <artifactId>resteasy-client</artifactId>
        </dependency>

