#ResponseBody of Map<String,String> in  Actuator Not Working

Owner: spring-projects

Repo: spring-boot

Labels: 

## verrol (08 Aug 2013)

In the documentation for Spring Boot - Actuator, the code sample shown for returning a Map<String,String> doesn't work in either Groovy script nor Java app.  The app compiles fine, but when accessed from the browser or curl.  Actuator doc located here: https://github.com/SpringSource/spring-boot/blob/master/spring-boot-actuator/README.md.

Embedded jetty server response:

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<title>Error 406 Not Acceptable</title>
</head>
<body><h2>HTTP ERROR 406</h2>
<p>Problem accessing /. Reason:
<pre>    Not Acceptable</pre></p><hr /><i><small>Powered by Jetty://</small></i><br/>  
<br/>  
<br/>  
<br/>  
<br/>  
<br/>  
<br/>  
<br/>  
<br/>  
<br/>  
<br/>  
<br/>  
<br/>  
<br/>  
<br/>  
<br/>  
<br/>  
<br/>  
<br/>  
<br/>                                                

</body>
</html>


## dsyer (08 Aug 2013)

Works for me (when I fixed the `pom.xml` a bit - changes should show up in README now)

```
$ curl localhost:8080
{"message":"Hello World"}
```


## dsyer (08 Aug 2013)

That was with a Java project.  Interestingly it doesn't work with the Groovy CLI. I'll take a look.


## dsyer (08 Aug 2013)

OK, I get it. You would need to add a @Grab for the actuator to make it work with in Groovy:

```
@Grab("org.springframework.boot:spring-boot-starter-actuator:0.5.0.M1")
```

I guess we could add a "marker annotation" `@EnableActuator` as a short hand for this.


## verrol (08 Aug 2013)

Yes, when i checked the groovy sample, that showed the @Grab. So now I have the groovy one working. 

In my java example, I was using starter-web instead of starter-actuator in my pom.xml. But now, I changed that, but maven is saying it can't find spring-boot-starer-actuator:0.5.0.BUILD-SNAPSHOT.

Looks like it is just my setup, so I will close this issue.


