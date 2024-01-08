#Session timeout is too short on spring-security-login-and-registration

Owner: eugenp

Repo: tutorials

Labels: 

## ovninosa (30 Jul 2015)

When I use the login form the session timeout is too short, even if I change over de web.xml with something like this:

```
<session-config>
    <session-timeout>20</session-timeout>
</session-config>
```

I run the sample code over netbeans and tomcat 8.0.15.0
Do you have any idea about this issue?

Thanks


## dynamoDB (31 Jul 2015)

when I modify web.xml session-timeout param 8 to 30 it is work for me.

If you want to use spring security in you web project, I hope this URL will help you:
     http://docs.spring.io/spring-security/site/docs/3.0.x/reference/ns-config.html
Good Luck


## ovninosa (31 Jul 2015)

Sorry but I cannot say the same. In any case when I change the timeout over the web.xml the timeout still is 30 seconds.

Any idea to fix this? 

Thanks


## ovninosa (31 Jul 2015)

Here is the problem:

On the class MySimpleUrlAuthenticationSuccessHandler.

```
public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
    handle(request, response, authentication);
    HttpSession session = request.getSession(false);
    if (session != null) {
        session.setMaxInactiveInterval(30);
    }
    clearAuthenticationAttributes(request);
}
```

The session.setMaxInactiveInterval(30) is expressed in seconds, not in minutes like the web.xml.


## eugenp (01 Aug 2015)

Hey @javenosa - nice catch. It's fixed. Thanks, 
Eugen. 


## shrirange (08 May 2018)

@eugenp What is fixed here. Spring Security or Tomcat. What should do to get the fix ?
We are facing the same issue

## eugenp (11 Jun 2018)

Well @shrirange - this was about 3 years ago, so as you can imagine - I have no idea :)

