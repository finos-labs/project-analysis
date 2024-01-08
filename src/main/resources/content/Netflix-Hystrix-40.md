#Code Generation for Existing Libraries

Owner: Netflix

Repo: Hystrix

Labels: enhancement 

## benjchristensen (06 Dec 2012)

A potentially useful hystrix-contrib module would be a standalone utility that could scan public methods on a service class and generate HystrixCommand objects for each of them.

This is the single biggest complaint about adopting an existing library to use Hystrix.

Annotations and AOP have their own issues (https://github.com/Netflix/Hystrix/wiki/FAQ) so this is a possibility – nothing magical but it would reduce tedious labor.

Here is an example service class with a large number of methods that would be really obnoxious to do manually: http://docs.amazonwebservices.com/AWSJavaSDK/latest/javadoc/com/amazonaws/services/ec2/AmazonEC2Client.html


## benjchristensen (12 Dec 2014)

The hystrix-javanica module adds annotation support and in practice the need for code generation has not been significant enough to pursue. Closing. 


