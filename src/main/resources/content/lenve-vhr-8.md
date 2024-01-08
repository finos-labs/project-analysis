#登录页面如何为/login?

Owner: lenve

Repo: vhr

Labels: question 

## china-fengguan (17 Jan 2018)

按照这样的配置and().formLogin().loginPage("/login_p")
如果URL里面是localhost:8080, 页面将获得如下结果：
{"status":"error","msg":"尚未登录，请登录!"}
这样是否有问题?
真正的登录页面不会显示。

## lenve (17 Jan 2018)

@china-fengguan 
前后端分离后，登录只是一个接口，不需要页面，我之所以配了loginPage，只是为了给用户一个友好的错误提示，你把这个注释掉就行了，这不是核心。

## lenve (17 Jan 2018)

@china-fengguan 
实在难理解，就把loginPage("/login_p")改为loginPage("/index.html")就行了

## china-fengguan (17 Jan 2018)

目前就是比较迷惑单一页面的VUE，是否应该就这样loginPage("/index.html")，另外loginProcessingUrl("/login")，这个返回的response头部类型是text/html,但是一般VUE习惯接收JSON,这个应该如何配置?

## china-fengguan (17 Jan 2018)

在server端代码cotroller并没有配置@RequestMapping("/login“），那么VUE的代码 this.postRequest('/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        }) 就会返回404错误

