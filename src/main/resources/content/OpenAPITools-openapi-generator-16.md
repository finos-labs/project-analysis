#[java] Enum in array of array

Owner: OpenAPITools

Repo: openapi-generator

Labels: Client: Java Server: Java 

## jmini (13 May 2018)

##### Description

Inner enum classes corresponding to an array of array Schema with Enum are not generated in java.
This was filed as https://github.com/swagger-api/swagger-codegen/issues/7918. I have fixed it in Swagger-Codegen v3 using an handlebars helper. Because we can not do this with JMustache, I think that the Codegen should provide access to something like "inner most items"

##### openapi-generator version

3.0.0-SNAPSHOT

##### OpenAPI declaration file content or url

https://gist.github.com/msjoshi/08f95f35610656ec7d45bacb48f7bd22


## jmini (18 May 2018)

For other languages I have opened #105 

