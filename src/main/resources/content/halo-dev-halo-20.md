#大部分类没有声明logger对象

Owner: halo-dev

Repo: halo

Labels: 

## jizhuozhi (19 Jul 2018)

比如如下代码

```
/**
 *     FreeMarker配置
 *
 * @author : RYAN0UP
 * @date : 2018/4/26
 */
@Slf4j
@Configuration
public class FreeMarkerConfig {

    @Autowired
    private freemarker.template.Configuration configuration;

    @Autowired
    private OptionsService optionsService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommonTagDirective commonTagDirective;

    @Autowired
    private ArticleTagDirective articleTagDirective;

    @PostConstruct
    public void setSharedVariable() {
        try {
            //自定义标签
            configuration.setSharedVariable("commonTag", commonTagDirective);
            configuration.setSharedVariable("articleTag", articleTagDirective);
            configuration.setSharedVariable("options", optionsService.findAllOptions());
            configuration.setSharedVariable("user", userService.findUser());
        } catch (TemplateModelException e) {
            log.error("自定义标签加载失败：{}", e.getMessage());
        }
    }
}
```

在一些类中没有创建logger对象就直接使用了log，不确定使用的是否为
```
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
```

## jizhuozhi (19 Jul 2018)

可以启动但是idea会一直出现红色浪线

## ruibaby (19 Jul 2018)

额， 仔细看一下README好伐。
> 注意：如使用Idea，Eclipse等IDE运行的话，需要安装Lombok插件。

## jizhuozhi (19 Jul 2018)

啊啊 不好意思啊……忘记了


