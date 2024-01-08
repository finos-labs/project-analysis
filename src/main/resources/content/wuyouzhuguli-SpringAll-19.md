#Spring Security OAuth2 入门学习，当配置了@EnableResourceServer重启之后不能进行认证授权了

Owner: wuyouzhuguli

Repo: SpringAll

Labels: 

## zhao198627 (29 Aug 2019)

配置了@EnableResourceServer重启后，访问http://localhost:8080/oauth/authorize?response_type=code&client_id=test&redirect_uri=http://www.baidu.com&scope=all 重定向到http://localhost:8080/login  报401错误。页面显示如下：
This XML file does not appear to have any style information associated with it. The document tree is shown below.
<oauth>
<error_description>
Full authentication is required to access this resource
</error_description>
<error>unauthorized</error>
</oauth>

这个是什么原因，在没有添加@EnableResourceServer注解的时候还可以正常获取授权码，可以获取token。希望看到的大佬给点帮助。谢谢大佬了

## zhao198627 (29 Aug 2019)

现在修改EnableResourceServer的配置，可以进行认证了。但是我带着生成的token去访问index还是Unauthorized。以下是我的代码：
@Configuration
@EnableResourceServer
@Lazy
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Bean("resourceServerRequestMatcher")
    public RequestMatcher resources() {
        return new AntPathRequestMatcher("/resources/**");
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
                .requestMatcher(resources()).authorizeRequests()
                .anyRequest().authenticated();
    }
}
重启后，浏览器访问http://localhost:8080/oauth/authorize?response_type=code&client_id=test&redirect_uri=http://www.baidu.com&scope=all。返回
{
    "access_token": "839c8fab-0543-4718-83e9-75f8bd4ecf15",
    "token_type": "bearer",
    "refresh_token": "5a31ae52-8407-4b3c-b6ad-e232b690c876",
    "expires_in": 43199,
    "scope": "all"
}
可是当我访问localhost:8080/index （带着Authorization=Bearer 839c8fab-0543-4718-83e9-75f8bd4ecf15）。返回如下：
{
    "timestamp": "2019-08-29T06:27:10.845+0000",
    "status": 401,
    "error": "Unauthorized",
    "message": "Unauthorized",
    "path": "/index"
}

## wuyouzhuguli (25 Sept 2019)

文章末尾说明原因了，加@Order控制加载顺序就行了

