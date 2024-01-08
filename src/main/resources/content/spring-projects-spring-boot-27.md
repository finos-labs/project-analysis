#StartupInfoLogger should log version of more Spring project

Owner: spring-projects

Repo: spring-boot

Labels: status: declined 

## mdeinum (16 Aug 2013)

Currently StartupInfoLoggerm only logs information on the Spring Boot and Spring Framework version. Would be nice if the versions of all used Spring portfolio products would be logged (Spring Batch, Spring Data (JPA), Spring Security etc.). 


## dsyer (17 Nov 2013)

I'm really interested in this feature, and I think it would be easy to implement (just ask some well-known packages what their version is, like in `SpringVersion`). Testing it and making sure that it is robust against changes on the classpath is potentially a big job - but maybe not, so if you want to try a pull request, try and make it extensible and provide some tests.


## cdupuis (25 Nov 2013)

Dave, it is probably easier to just iterate over all spring-***.jar jars in the lib folder and read the MANFEST.MF Implementation-Version. Would need to check how that could work if Boot is not being ran from an fat jar.

Do you want me to take a run at that?


## dsyer (25 Nov 2013)

I agree that might be a valid approach (make an assumption about jar file names and locations). As long as it doesn't add anything to startup time I think we could do it. Feel free to contribute.


## bhoomikatg (11 Mar 2015)

Is this still an open issue?


## snicoll (11 Mar 2015)

It is yes. There is an "open" icon on the top left.  If you don't mind my asking, why do you ask?


## wilkinsona (20 Jan 2016)

We discussed this recently. For some people, a VCS tag would be sufficient to tell them all they needed to know about their application. For others, details of all of the dependencies and their versions would be useful. The consensus seemed to be that collecting the dependencies and their versions was best done at build time. The information could, perhaps, then be exposed via a plugin to the /info endpoint. Logging it, as suggested in this issue, is another option.


## philwebb (02 Feb 2017)

After some consideration we don't think this is such a good idea, it's hard to know where we'd stop. The `/info` endpoint + version control is our recommended solution.

