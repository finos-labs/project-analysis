#Document Required IDE Configuration

Owner: JakeWharton

Repo: butterknife

Labels: 

## JakeWharton (10 Apr 2013)

For Eclipse users. IntelliJ should Just Work™.


## bishopmatthew (25 Apr 2013)

This annoyed me enough to write up the docs for it. It's a bit long, so it might make sense to add this as a Wiki page and then link to it from the README and then project site. Let me know if there's a problem or if I should make any changes.


## bishopmatthew (25 Apr 2013)

# Using Butter Knife with Eclipse
1. Download [the latest JAR](http://repository.sonatype.org/service/local/artifact/maven/redirect?r=central-proxy&g=com.jakewharton&a=butterknife&v=LATEST) and add it to the libs/ directory of your application.
2. Right click on your project in the Package Explorer. Head to Java Compiler -> Annotation Processing and check "Enable project specific settings" and make sure the other settings are the same as shown below: 

![Annotation Processing](http://i.imgur.com/e1QzMym.png)
1. Now head to Java Compiler -> Annotation Processing -> Factory Path, check "Enable project specific settings" and then click "Add JARs…" and select the "butterknife-X.X.X.jar" from the libs folder of your project. Now click "Ok" to exit the "Properties" screen. It will ask you to rebuild your project, so go ahead and click "Yes". See: 

![Factory Path](http://i.imgur.com/ElWppQO.png)
1. Make sure that the (hidden, note the dot) ".apt_generated" folder lives in your project root. Make sure it contains something like "YOURACTIVITY$$ViewInjector.java"
2. If it does, you're good to go. If not, try restarting Eclipse, then doing Project -> Clean. Again check for the ".apt_generated" and "YOURACTIVITY$$ViewInjector.java". Once it's there, you should be ready to go.


## bishopmatthew (25 Apr 2013)

```
# Using Butter Knife with Eclipse

1. Download [the latest JAR](http://repository.sonatype.org/service/local/artifact/maven/redirect?r=central-proxy&g=com.jakewharton&a=butterknife&v=LATEST) and add it to the libs/ directory of your application.

2. Right click on your project in the Package Explorer. Head to Java Compiler -> Annotation Processing and check "Enable project specific settings" and make sure the other settings are the same as shown below: 

![Annotation Processing](http://i.imgur.com/e1QzMym.png)

3. Now head to Java Compiler -> Annotation Processing -> Factory Path, check "Enable project specific settings" and then click "Add JARs…" and select the "butterknife-X.X.X.jar" from the libs folder of your project. Now click "Ok" to exit the "Properties" screen. It will ask you to rebuild your project, so go ahead and click "Yes". See: 

![Factory Path](http://i.imgur.com/ElWppQO.png)

4. Make sure that the (hidden, note the dot) ".apt_generated" folder lives in your project root. Make sure it contains something like "YOURACTIVITY$$ViewInjector.java"

5. If it does, you're good to go. If not, try restarting Eclipse, then doing Project -> Clean. Again check for the ".apt_generated" and "YOURACTIVITY$$ViewInjector.java". Once it's there, you should be ready to go.
```


## JakeWharton (25 Apr 2013)

Awesome! I'm going to make Eclipse and IntelliJ-specific pages that are linked to from the site. Will get to this soon, I promise!


## bishopmatthew (26 Apr 2013)

Cool, glad I could help. It was the least I could do, considering the use I've gotten out of your work. Cheers!


