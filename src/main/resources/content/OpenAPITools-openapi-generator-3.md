#Issue with Javascript Petstore test

Owner: OpenAPITools

Repo: openapi-generator

Labels: Client: JavaScript/Node.js Issue: Bug 

## wing328 (12 May 2018)

##### Description

Got the following errors with JS tests:

```
  161 passing (1s)
  1 failing

  1) PetApi should create and get pet:
     Uncaught Error: expected undefined to be an instance of exports
      at Assertion.assert (node_modules/expect.js/index.js:96:13)
      at Assertion.a.Assertion.an (node_modules/expect.js/index.js:279:12)
      at Function.a (node_modules/expect.js/index.js:499:17)
      at test/api/PetApi.spec.js:72:20
      at src/ApiClient.js:493:9
      at Request.callback (node_modules/superagent/lib/node/index.js:668:14)
      at node_modules/superagent/lib/node/index.js:883:18
      at IncomingMessage.<anonymous> (node_modules/superagent/lib/node/parsers/json.js:16:7)
      at endReadableNT (_stream_readable.js:1056:12)
      at _combinedTickCallback (internal/process/next_tick.js:138:11)
      at process._tickCallback (internal/process/next_tick.js:180:9)
```

https://github.com/OpenAPITools/openapi-generator/blob/master/samples/client/petstore/javascript/test/api/PetApi.spec.js#L72





## wing328 (12 May 2018)

I noticed the issue. Will file a fix shortly.

## wing328 (13 May 2018)

Fixed by #5 

