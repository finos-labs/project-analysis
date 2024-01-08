#Clean up Javadoc warning when building the project

Owner: OpenAPITools

Repo: openapi-generator

Labels: Enhancement: Code format help wanted 

## wing328 (16 May 2018)

##### Description

File a PR to clean up the Javadoc warnings when for example building openapi-generator-online:
```
14 warnings
[WARNING] Javadoc Warnings
[WARNING] /Users/williamcheng/Code/3.0/openapi-generator/modules/openapi-generator-online/src/main/java/org/openapitools/codegen/online/api/GenApiDelegate.java:47: warning: no @return
[WARNING] default ResponseEntity<List<String>> clientOptions() {
[WARNING] ^
[WARNING] /Users/williamcheng/Code/3.0/openapi-generator/modules/openapi-generator-online/src/main/java/org/openapitools/codegen/online/api/GenApiDelegate.java:63: warning: no @param for fileId
[WARNING] default ResponseEntity<Resource> downloadFile(String  fileId) {
[WARNING] ^
[WARNING] /Users/williamcheng/Code/3.0/openapi-generator/modules/openapi-generator-online/src/main/java/org/openapitools/codegen/online/api/GenApiDelegate.java:63: warning: no @return
[WARNING] default ResponseEntity<Resource> downloadFile(String  fileId) {
[WARNING] ^
[WARNING] /Users/williamcheng/Code/3.0/openapi-generator/modules/openapi-generator-online/src/main/java/org/openapitools/codegen/online/api/GenApiDelegate.java:71: warning: no @param for language
[WARNING] default ResponseEntity<ResponseCode> generateClient( String  language,
[WARNING] ^
[WARNING] /Users/williamcheng/Code/3.0/openapi-generator/modules/openapi-generator-online/src/main/java/org/openapitools/codegen/online/api/GenApiDelegate.java:71: warning: no @param for generatorInput
[WARNING] default ResponseEntity<ResponseCode> generateClient( String  language,
[WARNING] ^
[WARNING] /Users/williamcheng/Code/3.0/openapi-generator/modules/openapi-generator-online/src/main/java/org/openapitools/codegen/online/api/GenApiDelegate.java:71: warning: no @return
[WARNING] default ResponseEntity<ResponseCode> generateClient( String  language,
[WARNING] ^
[WARNING] /Users/williamcheng/Code/3.0/openapi-generator/modules/openapi-generator-online/src/main/java/org/openapitools/codegen/online/api/GenApiDelegate.java:88: warning: no @param for framework
[WARNING] default ResponseEntity<ResponseCode> generateServerForLanguage( String  framework,
[WARNING] ^
[WARNING] /Users/williamcheng/Code/3.0/openapi-generator/modules/openapi-generator-online/src/main/java/org/openapitools/codegen/online/api/GenApiDelegate.java:88: warning: no @param for generatorInput
[WARNING] default ResponseEntity<ResponseCode> generateServerForLanguage( String  framework,
[WARNING] ^
[WARNING] /Users/williamcheng/Code/3.0/openapi-generator/modules/openapi-generator-online/src/main/java/org/openapitools/codegen/online/api/GenApiDelegate.java:88: warning: no @return
[WARNING] default ResponseEntity<ResponseCode> generateServerForLanguage( String  framework,
[WARNING] ^
[WARNING] /Users/williamcheng/Code/3.0/openapi-generator/modules/openapi-generator-online/src/main/java/org/openapitools/codegen/online/api/GenApiDelegate.java:105: warning: no @param for language
[WARNING] default ResponseEntity<Map<String, CliOption>> getClientOptions(String  language) {
[WARNING] ^
[WARNING] /Users/williamcheng/Code/3.0/openapi-generator/modules/openapi-generator-online/src/main/java/org/openapitools/codegen/online/api/GenApiDelegate.java:105: warning: no @return
[WARNING] default ResponseEntity<Map<String, CliOption>> getClientOptions(String  language) {
[WARNING] ^
[WARNING] /Users/williamcheng/Code/3.0/openapi-generator/modules/openapi-generator-online/src/main/java/org/openapitools/codegen/online/api/GenApiDelegate.java:113: warning: no @param for framework
[WARNING] default ResponseEntity<Map<String, CliOption>> getServerOptions( String  framework) {
[WARNING] ^
[WARNING] /Users/williamcheng/Code/3.0/openapi-generator/modules/openapi-generator-online/src/main/java/org/openapitools/codegen/online/api/GenApiDelegate.java:113: warning: no @return
[WARNING] default ResponseEntity<Map<String, CliOption>> getServerOptions( String  framework) {
[WARNING] ^
[WARNING] /Users/williamcheng/Code/3.0/openapi-generator/modules/openapi-generator-online/src/main/java/org/openapitools/codegen/online/api/GenApiDelegate.java:121: warning: no @return
[WARNING] default ResponseEntity<List<String>> serverOptions() {
[WARNING] ^

```
##### openapi-generator version

Latest master

##### Steps to reproduce

`mvn clean package`

##### Suggest a fix/enhancement

If anyone wants to work on this, please reply to let us know.


## jmini (16 May 2018)

I think that this also apply to other modules, we have some javadoc that is not correct in our code.

## wing328 (16 May 2018)

@jmini I've updated the subject and the details

## jmini (17 May 2018)

In the main `openapi-generator` module:
```
8 warnings
[WARNING] Javadoc Warnings
[WARNING] ___/openapi-generator/modules/openapi-generator/src/main/java/org/openapitools/codegen/DefaultCodegen.java:3980: warning: no description for @param
[WARNING] * @param openAPI
[WARNING] ^
[WARNING] ___/openapi-generator/modules/openapi-generator/src/main/java/org/openapitools/codegen/utils/URLPathUtils.java:75: warning: no description for @param
[WARNING] * @param url
[WARNING] ^
[WARNING] ___/openapi-generator/modules/openapi-generator/src/main/java/org/openapitools/codegen/utils/URLPathUtils.java:77: warning: no description for @return
[WARNING] * @return
[WARNING] ^
[WARNING] ___/openapi-generator/modules/openapi-generator/src/main/java/org/openapitools/codegen/utils/URLPathUtils.java:85: warning: no description for @param
[WARNING] * @param url
[WARNING] ^
[WARNING] ___/openapi-generator/modules/openapi-generator/src/main/java/org/openapitools/codegen/utils/URLPathUtils.java:87: warning: no description for @return
[WARNING] * @return
[WARNING] ^
[WARNING] ___/openapi-generator/modules/openapi-generator/src/main/java/org/openapitools/codegen/utils/URLPathUtils.java:99: warning: no description for @param
[WARNING] * @param url
[WARNING] ^
[WARNING] ___/openapi-generator/modules/openapi-generator/src/main/java/org/openapitools/codegen/utils/URLPathUtils.java:113: warning: no description for @param
[WARNING] * @param url
[WARNING] ^
[WARNING] ___/openapi-generator/modules/openapi-generator/src/main/java/org/openapitools/codegen/utils/URLPathUtils.java:127: warning: no description for @param
[WARNING] * @param openAPI
[WARNING] ^
```

## jimschubert (24 May 2018)

@wing328 @jmini I've updated javadoc in GenApiDelegate in #140.

There are four remaining in the entire repo after my PR, but it sounded like Jeremie may have accounted for this in another PR:

```
[WARNING] Javadoc Warnings
[WARNING] /Users/jim/projects/openapi-generator/modules/openapi-generator/src/main/java/org/openapitools/codegen/CodegenProperty.java:130: warning: no @return
[WARNING] public String getDatatype() {
[WARNING] ^
[WARNING] /Users/jim/projects/openapi-generator/modules/openapi-generator/src/main/java/org/openapitools/codegen/utils/ModelUtils.java:347: warning: no description for @param
[WARNING] * @param openAPI
[WARNING] ^
[WARNING] /Users/jim/projects/openapi-generator/modules/openapi-generator/src/main/java/org/openapitools/codegen/utils/ModelUtils.java:376: warning: no description for @param
[WARNING] * @param openAPI
[WARNING] ^
[WARNING] /Users/jim/projects/openapi-generator/modules/openapi-generator/src/main/java/org/openapitools/codegen/utils/ModelUtils.java:401: warning: no description for @param
[WARNING] * @param openAPI
[WARNING] ^
```

I think in the other, he mentioned `ModelUtils`. There's also `CodegenProperty` missing a return doc in the above.

## jmini (24 May 2018)

I have opened #143 to fix the last issue.

---

Maybe we can now configure the maven build to fail on javadoc warnings? Or maybe also something we can catch during static analysis with Sonarqube (see #33).

