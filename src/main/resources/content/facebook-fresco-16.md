#can you distribute in a jar?

Owner: facebook

Repo: fresco

Labels: 

## mufumbo (27 Mar 2015)

that would help a lot people like me who only want to test inside intellij. 


## plamenko (28 Mar 2015)

If I am not wrong, jar is not as generic as aar, and we need aar in order to include native code.


## plamenko (28 Mar 2015)

Also, see http://stackoverflow.com/questions/23915619/android-archive-library-aar-vs-standard-jar


## mufumbo (28 Mar 2015)

@plamenko an AAR will definitely suffice. 

What I mean is: can you put a simple link to the AAR anywhere? 

For lazy people who don't like gradle or don't want to learn how to find the AAR in it's repository?

Hope the feedback helps to streamline. I believe there will be more people like me. 


## michalgr (28 Mar 2015)

@mufumbo can you tell us more about your use case so that we know what should we optimize for? How would you use the aars without gradle or maven ?  providing links to Fresco aars is not enough, you probably also need links to Fresco's dependencies. To make it more convenient we could provide zipped bundle of all aars - depends on what people need.


