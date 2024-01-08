#关于数据安全性方面

Owner: Tencent

Repo: APIJSON

Labels: Enhancement 增强 Question 使用问题 

## moonrailgun (26 Apr 2017)

大概看了一下文档.因为没有java环境所以没有实验不过很好奇的问一下如何解决服务端数据的安全性方面呢?数据请求都放在前台后台似乎没有请求验证这方面的逻辑.如何保证我抓包后伪造数据不会删除/修改数据库的一些重要数据呢?
如果有相关方面的实现的话请不要介意.因为就是大概看了一下文档然后提出一些小小的疑惑.并没有具体实验操作过

## TommyLemon (26 Apr 2017)

@moonrailgun 问题很好。
1.APIJSON会对请求的格式进行校验。
2.APIJSON只有GET,HEAD请求才是明文，其它如POST都是非明文，这个和传统方式是一样的。
3.APIJSON会对非GET、HEAD请求的请求方法、结构、内容进行严格校验。
4.APIJSON对Table默认保护不可访问，需要服务端配置允许的请求与结构才能用指定的请求方法与结构访问。
具体可以看这篇文中的关于安全部分。
http://www.cnblogs.com/tommylemon/p/6573740.html

## moonrailgun (26 Apr 2017)

这个项目让我想到了APICloud的数据云API，同样是由前端控制后端SQL，在后台进行数据表的权限与安全控制。实现方式类似于fb的graphql。可惜对java的印象一般不然真想把代码clone下来研究一下。
最后支持开源，支持国产。star献上

## TommyLemon (26 Apr 2017)

谢谢，graphql有人提过，我也在深入了解，目前感觉还是APIJSON比它简单易用很多。APICloud对我来说又是个新玩意，了解下嘿嘿

## TommyLemon (26 Apr 2017)

话说APIJSONTest提供了自动生成Java请求代码嘿嘿

## TommyLemon (07 Aug 2017)

@moonrailgun 现在可以通过@ MethodAccess注解实现细分到每个角色、每种操作、每张表、每条记录的权限管理了，一般用 Model声明+权限注解+权限注册 3行代码就够了

```
//注册表并添加权限，用默认配置
@MethodAccess
public class Comment {
//内容一般仅供表字段说明及Android App开发使用，服务端不用的可不写。
}

//AccessVerifier内添加权限
accessMap.put(Comment.class.getSimpleName(), getAccessMap(Comment.class.getAnnotation(MethodAccess.class)));
```

另外还有 session管理、表映射 等很多重要的优化
https://github.com/TommyLemon/APIJSON/releases/tag/1.5.0

## TommyLemon (03 Jun 2018)

已使用预编译全面防SQL注入，感谢支持^_^
https://github.com/TommyLemon/APIJSON/releases/tag/2.4.0

