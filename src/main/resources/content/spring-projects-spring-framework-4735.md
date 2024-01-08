#WebClient.ResponseSpec.onStatus, Unexpected behavior when response body empty

Owner: spring-projects

Repo: spring-framework

Labels: status: superseded 

## danielra (10 Jan 2019)

When I use the WebClient.ResponseSpec.onStatus method and try to create an exception that requires the response body content but the response body is empty, the Flux returned by a subsequent bodyToFlux call actually receives an onComplete instead of the expected onError.

To illustrate the behavior, I've created a sample project which reproduces the unexpected behavior in a test case here: https://github.com/danielra/webclient_response_spec_onstatus_empty_response_body_issue_repro
The project uses spring boot version `2.1.1.RELEASE` as seen [here](https://github.com/danielra/webclient_response_spec_onstatus_empty_response_body_issue_repro/blob/master/build.gradle#L3).

When run via `./gradlew test --debug` the test case ([here](https://github.com/danielra/webclient_response_spec_onstatus_empty_response_body_issue_repro/blob/master/src/test/java/com/example/demo/DemoApplicationTests.java#L52)) which has at least one character of response body content passes as expected - but the test case which has an empty response body ([here](https://github.com/danielra/webclient_response_spec_onstatus_empty_response_body_issue_repro/blob/master/src/test/java/com/example/demo/DemoApplicationTests.java#L38)) unexpectedly fails.

Please let me know if any further information would be useful.

## poutsma (14 Nov 2019)

With the introduction of `ClientResponse::createException` in 5.2, it is no longer necessary to consume the body bytes themselves. Using that method, I was not able to reproduce this issue.

Closing this issue for now, but let us know if there is something to look at.

## rstoyanchev (15 Nov 2019)

Most likely superseded by #22825.

