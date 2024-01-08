#com.ai.cloud.skywalking.plugin.spring.TracingEnhanceProcessor支持除标注外的其他使用方式

Owner: apache

Repo: skywalking

Labels: 

## wu-sheng (15 Dec 2015)

支持通过设置包名、类名的方法进行JVM监控
TracingEnhanceProcessor可以通过配置文件中，增加包名和类名列表，代码标注，更好的支持监控。减少对代码的侵入性。


## wu-sheng (21 Dec 2015)

@Tracing标注将从目前版本中移除。


## ascrutae (22 Dec 2015)

**@Tracing**注解以后支持，现在不支持,目前支持自定义标签，示例图如下

``` xml
<skywalking:trace className="*" method="*" packageName="com.ai.cloud.skywalking.example.*" />
```

在自定义标签中，**className**和**packageName**是必须同时出现，**method**可以不出现，且默认值为*(所有)

| packageName | className | method | Describe |
| --- | --- | --- | --- |
| * | * | * | 监控所有包下的所有类下的所有方法 |
| com.ai.cloud | * | * | 监控com.ai.cloud包下的所有的类的所有方法，**不包含com.ai.cloud的子包的类** |
| com.ai.cloud.* | * | * | 监控com.ai.cloud包下的所有的类的所有方法，**包含com.ai.cloud的子包的类** |
| com.ai.cloud | A* | * | 监控com.ai.cloud包下的所有的类名以A为开头的所有方法 |
| com.ai.cloud | A* | A* | 监控com.ai.cloud包下的所有的类名以A为开头的方法名以A为开头的所有方法 |

**目前包名和类名只支持后匹配，不支持前匹配**


## wu-sheng (31 Dec 2015)

skywalking:trace标记实现逻辑发生变更，以上的配置不再生效。
新的配置如下：

``` xml
<skywalking:trace packageExpression="com.ai.cloud.skywalking.plugin.spring.test.*" classExpression="*"/>
```

packageExpression为包名表达式，表达式与Spring的切面within描述相同。（必须以._结尾，如果需要所有下级包以.._结尾）
classExpression为类名，可以前匹配、精确匹配或全匹配。（*号为通配符）


