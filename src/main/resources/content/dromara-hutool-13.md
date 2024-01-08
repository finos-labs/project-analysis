#HttpConnection类initConn方法改进

Owner: dromara

Repo: hutool

Labels: question 

## huo-feng-ding (06 Apr 2017)

希望该方法里面设置default header时 `header(Header.ACCEPT_ENCODING, "gzip", true);`这个不要默认设置,不然输出来的数据还需要手工用gzip去解压.
`header(Header.CONTENT_TYPE, "application/x-www-form-urlencoded", true);` 好像只有post,patch,put 时才有用吧
另外302跳转的支持最好也加上,用一个参数来控制是否跳转

## looly (06 Apr 2017)

感谢你的issue，关于这几个问题我一一解答：
1、`header(Header.ACCEPT_ENCODING, "gzip", true)`加入是因为在HttpResponse中有Gzip解压的逻辑。具体看下：`HttpResponse.readBody`
2、`header(Header.CONTENT_TYPE, "application/x-www-form-urlencoded", true)` 这个Header确实应该是这几个方法用，但是貌似GET等发发并不影响，所以就没有去掉，默认了。之后我会测试，加个判断。
3、302跳转现在可以支持，在`HttpRequest.setFollowRedirects`方法设置为true时可跳转。

## huo-feng-ding (06 Apr 2017)

问题1,3 看到了,谢谢,由于没有相关的测试用例,我直接HttpConnection返回的inputstream抽取解压数据,原来有HttpResponse类可以处理,setFollowRedirects方法也见到了
谢谢

## looly (06 Apr 2017)

单元测试比较简单，HttpConnection只是一个内部使用类，因此没有做测试。后续欢迎提供更多issue~

