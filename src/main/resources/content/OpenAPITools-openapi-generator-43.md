#Generated java client: maven-javadoc-plugin error

Owner: OpenAPITools

Repo: openapi-generator

Labels: Client: Java 

## jmini (15 May 2018)

##### Description

Running maven in a generated java client can not be compiled due to javadoc error:

```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-javadoc-plugin:2.10.4:jar (attach-javadocs) on project petstore-openapigen-java: MavenReportException: Error while generating Javadoc: 
[ERROR] Exit code: 1 - /____/petstore-openapigen-java/src/main/java/org/openapitools/client/api/PetApi.java:298: error: unknown tag: String
[ERROR]      * @param status Status values that need to be considered for filter (optional, default to new ArrayList<String>())
[ERROR]                                                                                                             ^
[ERROR] /____/petstore-openapigen-java/src/main/java/org/openapitools/client/api/PetApi.java:359: error: unknown tag: String
[ERROR]      * @param status Status values that need to be considered for filter (optional, default to new ArrayList<String>())
[ERROR]                                                                                                             ^
[ERROR] /____/petstore-openapigen-java/src/main/java/org/openapitools/client/api/PetApi.java:371: error: unknown tag: String
[ERROR]      * @param status Status values that need to be considered for filter (optional, default to new ArrayList<String>())
[ERROR]                                                                                                             ^
[ERROR] /____/petstore-openapigen-java/src/main/java/org/openapitools/client/api/PetApi.java:384: error: unknown tag: String
[ERROR]      * @param status Status values that need to be considered for filter (optional, default to new ArrayList<String>())
[ERROR]                                                                                                             ^
[ERROR] 
[ERROR] Command line was: /Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/jre/../bin/javadoc @options @packages
```

##### openapi-generator version

3.0.0-SNAPSHOT

##### OpenAPI declaration file content or url

Normal [petstore.json](https://github.com/jmini/openapi-experiments/blob/f582a052452c39fab8535c934bd7fe4cfe801d76/openapi-generator/petstore/petstore.json)

##### Command line used for generation

Java code:

```
JavaClientCodegen config = new org.openapitools.codegen.languages.JavaClientCodegen();
config.setArtifactId("some-artifactId");
config.setJava8Mode(true);
config.setHideGenerationTimestamp(true);
config.setOutputDir(outputDir);

final OpenAPI openAPI = new OpenAPIParser().readLocation(folder + "/" + inputSpecName, null, new ParseOptions()).getOpenAPI()

final ClientOptInput opts = new ClientOptInput();
opts.setConfig(config);
opts.setOpenAPI(openAPI);
opts.setOpts(new ClientOpts());
new DefaultGenerator().opts(opts).generate();
```

##### Steps to reproduce

run mvn verify in [`petstore-openapigen-java/`](https://github.com/jmini/openapi-experiments/tree/805ded2b77de89608de971dfb8a88630af0451d5/openapi-generator/petstore/petstore-openapigen-java)


##### Suggest a fix/enhancement

In the javadoc section in the template there is a difference between `{{type}}` and `{{{type}}}` (one is wrong).

I am not sure why we do not catch it in the CI server. Do we run `mvn verify` there?



## jmini (20 May 2018)

Closed with #106

