#login controller没有配置

Owner: lenve

Repo: vhr

Labels: question 

## china-fengguan (17 Jan 2018)

在server端代码cotroller并没有配置@requestmapping("/login“），那么VUE的代码 this.postRequest('/login', {
username: this.loginForm.username,
password: this.loginForm.password
}) 就会返回404错误

## lenve (17 Jan 2018)

请先学习SpringSecurity的使用

