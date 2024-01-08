#能否支`UrLBlockHandler`实现类的自动装配

Owner: alibaba

Repo: spring-cloud-alibaba

Labels: enhancement sentinel 

## xiejiashuai (08 Sept 2018)

- 目前自定义`UrLBlockHandler`比较麻烦

- 需要用户手动调用`WebCallbackManager#setUrlBlockHandler()`，可否支持自动装配呢？

## flystar32 (12 Sept 2018)

很好的提议，应该支持。
Sentinel 逻辑中 WebCallbackManager 只支持配置一个 UrLBlockHandler ，如果自动装配，那么应该做好说明表示只支持实现一个 UrlBlockHandler。
同理 ，WebCallbackManager 只支持配置一个 UrlCleaner，UrlCleaner 也应该支持支持自动装配。

