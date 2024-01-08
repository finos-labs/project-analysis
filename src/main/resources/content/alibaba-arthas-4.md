#arthas的版本号策略

Owner: alibaba

Repo: arthas

Labels: 

## hengyunabc (06 Sept 2018)




采用类似 `3.0.0` 这样子的版本号。为了保证版本比较能正常工作，不得包含字母等。

目前`as-package.sh` 里是定义了一个 `3.0`，然后再取当前时间拼接起来，出来的版本号类似：`3.0.20171103142340`

为了保证后续的新的版本号比较能正常工作，后续发版本时，直接从 `3.0.3` 开始，本地开发时，拼接出来的版本号是 `3.0.3` + date ，即类似 `3.0.3.20171103142340` 

保证本地开发可以用到最新版本。

## hengyunabc (06 Sept 2018)

目前 `as-package.sh` 会创建`/core/src/main/resources/com/taobao/arthas/core/res/version` ，写入当前版本号。

然后在代码里读取 

```java
public class ArthasBanner {
    private static final String VERSION_LOCATION = "/com/taobao/arthas/core/res/version";
```

这里的逻辑改为，先尝试读 `com/taobao/arthas/core/res/version` ，没有就读 java package本身的版本号。

1. 发布到maven仓库里的zip里的jar，不会有 `com/taobao/arthas/core/res/version`，则获取到的是 java package本身的版本号
2. 本地开发时，install到本地 `~/.arthas/lib`下面的，则会有 `com/taobao/arthas/core/res/version`


## hengyunabc (06 Sept 2018)

目前最新版本是通过 `https://alibaba.github.io/arthas/latest_version` 来获取到，后续发布到maven仓库之后，考虑直接用 maven central 的 api 来获取最新版本。

## hengyunabc (14 Sept 2018)

已改用 maven central repository API 来获取最版本号。

