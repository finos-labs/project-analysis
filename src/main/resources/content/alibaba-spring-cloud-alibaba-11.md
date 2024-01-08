#能否支持预初始化

Owner: alibaba

Repo: spring-cloud-alibaba

Labels: enhancement sentinel 

## xiejiashuai (08 Sept 2018)

- 目前`Sentinel-Starter`不支持心跳连接等预初始化

- 能否在Spring 容器启动时，进行相关预初始化，而不需要用户在第一次访问时在初始化
- 因为这个初始化其实会耗费一定时间，比如要执行`ServiceLoader`进行加载，虽然时间短，但是仍然有时间损耗
- 是否可提供相关配置项 默认为`false` 有用户控制行为
- 目前已经提了一个PR，能否给看下是否可以？

## flystar32 (12 Sept 2018)

很赞的想法

