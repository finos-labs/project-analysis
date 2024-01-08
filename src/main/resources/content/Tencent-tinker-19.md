#关于AndroidManifest内容动态更新的问题？

Owner: Tencent

Repo: tinker

Labels: 

## qixinmin (26 Sept 2016)

你好，在demo中的配置文件中看到这样的注释
case 2: newly added Android Component in AndroidManifest.xml,
         \*         it must be crash when load.

是不是对AndroidManifest.xml的内容 Android Component变化不支持？　如果支持怎么支持呢

多谢


## dodola (26 Sept 2016)

@qixinmin 热修复是不支持新增组件的，如果想要实现这个功能请考虑插件化的方式


## qixinmin (26 Sept 2016)

＠dodola 好的，多谢


## shwenzhang (26 Sept 2016)

如果想支持可以这样的，预先在Manifest埋好一个代理的组件。然后新增的时候，使用代理的组件


## qixinmin (26 Sept 2016)

@shwenzhang 在改动不频繁的情况下是个折中方案


## diaoling90 (16 Feb 2017)

manifest文件不增加组件，修改版本号之类的支持吗？

