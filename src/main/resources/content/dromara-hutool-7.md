#在Validator工具中，如果我的url两端带空格，则调用isUrl方法会返回false

Owner: dromara

Repo: hutool

Labels: bug 

## felayman (17 Sept 2015)

如下：t
rue
http://openapi.baidu.com/public/2.0/bmt/translate?client_id=xGzW7GhPIK3vLjnTeXZ1LOfG&q=today&from=auto&to=auto
 http://openapi.baidu.com/public/2.0/bmt/translate?client_id=xGzW7GhPIK3vLjnTeXZ1LOfG&q=today&from=auto&to=auto
false
如果是不小心复制的时候两侧多了个空格的话，作者最好加上trim验证。


## felayman (17 Sept 2015)

改成如下最好，isByRegex(URL, value.trim());


## looly (18 Sept 2015)

此问题已经在2.11.6中解决（我用了new URL方式来验证）


