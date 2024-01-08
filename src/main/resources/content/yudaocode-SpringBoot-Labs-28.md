#windows system Filename too long

Owner: yudaocode

Repo: SpringBoot-Labs

Labels: question 

## hzl7652 (19 Jul 2020)

windows7 
git version 2.17.0.windows.1
`λ git merge origin/master
error: cannot stat 'lab-68-spring-security-oauth/lab-68-demo02-authorization-server-with-resource-owner-password-credentials/src/main/java/cn/iocoder/springboot/lab68/authorizationserverdemo/config/OAuth2AuthorizationServerConfig.java': Filename too long
error: cannot stat 'lab-68-spring-security-oauth/lab-68-demo03-authorization-server-with-resource-owner-password-credentials/src/main/java/cn/iocoder/springboot/lab68/authorizationserverdemo/config/OAuth2AuthorizationServerConfig.java': Filename too long
Updating 85f3f584..67026716`

## tjrbrom (19 Jul 2020)

git config --system core.longpaths true

## hzl7652 (25 Jul 2020)

@tjrbrom 谢谢

## YunaiV (25 Jul 2020)

> @tjrbrom 谢谢

原来还有这个限制 = = 最近在把每个示例对应的框架名字加在目录里 = = 贼长了都~

