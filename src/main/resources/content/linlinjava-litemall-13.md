#我这边爆了一个错误，不知道怎么解决请教一下大家。

Owner: linlinjava

Repo: litemall

Labels: 

## danzi638 (15 Apr 2018)

```
Description:

A component required a bean of type 'org.linlinjava.litemall.db.dao.LitemallAddressMapper' that could not be found.


Action:

Consider defining a bean of type 'org.linlinjava.litemall.db.dao.LitemallAddressMapper' in your configuration.
```

多谢了。

## danzi638 (15 Apr 2018)

运行litemall-db的时候出错的。

## linlinjava (15 Apr 2018)

你好，感谢反馈，这里是一个小bug。你可以看一下litemall-db模块的application类，里面的org.linlinjava.zmall.db.dao包名写错了，这是以前用的名称，你把zmall改成litemall应该就可以了。
我明天才能修正。

## danzi638 (15 Apr 2018)

OK，非常感谢，还有就是建议您给一个mysql的导入脚本，json字段和smallint字段mysql无法识别。非常感谢您的这份源码。

## linlinjava (15 Apr 2018)

不过需要注意的是，其实你不应该运行litmall-db模块，虽然这里有application类，这里是作为测试使用的。litemall-db模块最终是作为java类库形式被使用。

## linlinjava (15 Apr 2018)

你好，mysql脚本会完善提供，但是我非常不建议你依赖导入脚本，你应该是可以使用litemall-db模块里面的两个sql脚本就可以自己创建数据库的。
关于json和smallint的问题，我不是很明白，我这里MySQL完全没有问题，是因为你的版本比较老的原因吗，建议你使用最新MySQL再试一下。

## danzi638 (16 Apr 2018)

嗯嗯，是因为我数据库版本太低的原因，不好意思。

## linlinjava (16 Apr 2018)

修复 b9bdc03ecd3b695343bc0eef4aadc54bd2023b47

