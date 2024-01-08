#security 短信验证码登录,验证码不存在

Owner: wuyouzhuguli

Repo: SpringAll

Labels: 

## fengjunzixian (12 Dec 2019)

获取到了验证码后登陆,在校验验证码过滤器时候 


## fengjunzixian (12 Dec 2019)

 SmsCode codeInSession = (SmsCode) sessionStrategy.getAttribute(servletWebRequest, ValidateController.SESSION_KEY_SMS_CODE + mobileInRequest);  
sessionStrategy 获取不到之前设置的  缓存id 

## wuyouzhuguli (12 Dec 2019)

是不是存储的key和获取的key不一致？文章源码我是跑过后才上传的，可以参考下。

## fengjunzixian (12 Dec 2019)

不好意思,  手机号写死了,生成校验码的是177777777,  登录请求的是实际填写的手机号,两者不一致....

