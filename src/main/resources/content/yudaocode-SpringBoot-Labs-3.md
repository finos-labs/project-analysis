#《芋道 Spring Boot Dubbo 入门》细节问题

Owner: yudaocode

Repo: SpringBoot-Labs

Labels: bug 

## yuyong725 (07 Jan 2020)

问题1：4.1.2添加的是 UserAddDTO，但紧接着下文系的是 “创建 UserDTO 类”


## yuyong725 (07 Jan 2020)

问题2：丢失了对SPI文件的引入说明，且源码(仓库lab-30)中使用的是 ‘com.alibaba.dubbo.rpc.Filter’，建议更改为 ‘META-INF/dubbo/org.apache.dubbo.rpc.Filter’。因为当我尝试乱写一个包名时，启动报错说的是在 ‘META-INF/dubbo/org.apache.dubbo.rpc.Filter’ 包下没有找到指定的声明，所以我主观上认为 ‘com.alibaba.dubbo.rpc.Filter’ 只是对旧版本的支持，😂

## YunaiV (11 Jan 2020)

问题一和问题二，都 fix 了。稍后提交上来哈。

