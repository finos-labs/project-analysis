#Mybatis tries to instantiate an interface

Owner: mybatis

Repo: mybatis-3

Labels: duplicate 

## ataverasp (11 Mar 2013)

I have an scenario where we have a Multimap. That Multimap is an interface that extends from java.util.Collection. What it does is that every element that you put in there gets added to an internal, so every call to add method a key is extracted from the element and a put is called in beneath. We have an implementation which is presented to Mybatis in an association:

`<resultMap id="result" type="ObjectId" autoMapping="false">
       <collection property="associationCollection"
                        javaType="CollectionMultiMapImpl"
                ofType="CollectionType"
                column="ObjectId"
                select="AssociationCollection.getByObjectId"/> 
</resultMap>`

It was working fine a couple of times but in one case the association was getting loaded by the **org.apache.ibatis.executor.BaseExecutor.DeferredLoad.DeferredLoad.load()** and looked that the javaType that was specified in the mapping were not provied to the DeferredLoad, trying to create a new instance of the interface Multimap and of course failing. 

I'm using Mybatis 3.1.1 with a DerbyDB under java 6. 


## emacarron (14 Mar 2013)

Could you please provide a test?


## emacarron (26 Mar 2013)

Probably this has been alread fixed, can you try the snapshot?
https://code.google.com/p/mybatis/downloads/detail?name=mybatis-3.2.2-SNAPSHOT.jar


## ataverasp (26 Mar 2013)

I will provide a test but first let me download the latest version and see if it solved


## emacarron (27 Mar 2013)

It looks quite similar to #5 so there are chances that no test is needed :) 

Let us know the results of the testing.


## ataverasp (27 Mar 2013)

I'm not sure that is the same error, of what i've seen so far is that
Mybatis tries to resolve the type in a diferently if the association is
cached and if it isn't; i'm almost done with the test case to show you that

Angel Taveras

Sent by phone...

On Mar 27, 2013, at 1:22 AM, Eduardo Macarron notifications@github.com
wrote:

It looks quite similar to #5
https://github.com/mybatis/mybatis-3/issues/5so there are chances
that no test is needed :)

Let us know the results of the testing.

—
Reply to this email directly or view it on
GitHubhttps://github.com/mybatis/mybatis-3/issues/12#issuecomment-15506116
.


## ataverasp (27 Mar 2013)

you got it man, it doesn't happen on 3.2.2-SNAPSHOT, but how do i attach the tests?


## simonetripodi (27 Mar 2013)

@ataverasp fork the mybatis repository and send a pull request - patches are always welcome! :)


