#[Node.js] server doesn't work

Owner: OpenAPITools

Repo: openapi-generator

Labels: Server: Nodejs help wanted 

## ackintosh (14 May 2018)

<!--
Please follow the issue template below for bug reports and feature requests.
Also please indicate in the issue title which language/library is concerned. Eg:  [JAVA] Bug generating foo with bar 
-->

##### Description

The errors on when run `npm start` in nodejs server sample folder:

> Error: Unsupported Swagger version: undefined

```bash
$ bin/openapi3/nodejs-petstore-server.sh
$ cd samples/server/petstore/nodejs
$ npm start

> swagger-petstore@1.0.0 start /Users/akihito1/src/github.com/ackintosh/openapi-generator/samples/server/petstore/nodejs
> node index.js

/Users/akihito1/src/github.com/ackintosh/openapi-generator/samples/server/petstore/nodejs/node_modules/swagger-tools/lib/helpers.js:169
        throw new Error('Unsupported Swagger version: ' + version);
        ^

Error: Unsupported Swagger version: undefined
    at Object.module.exports.getSpec (/Users/akihito1/src/github.com/ackintosh/openapi-generator/samples/server/petstore/nodejs/node_modules/swagger-tools/lib/helpers.js:169:15)
    at Object.initializeMiddleware (/Users/akihito1/src/github.com/ackintosh/openapi-generator/samples/server/petstore/nodejs/node_modules/swagger-tools/index.js:44:18)
    at Object.<anonymous> (/Users/akihito1/src/github.com/ackintosh/openapi-generator/samples/server/petstore/nodejs/index.js:24:14)
    at Module._compile (module.js:570:32)
    at Object.Module._extensions..js (module.js:579:10)
    at Module.load (module.js:487:32)
    at tryModuleLoad (module.js:446:12)
    at Function.Module._load (module.js:438:3)
    at Module.runMain (module.js:604:10)
    at run (bootstrap_node.js:389:7)

npm ERR! Darwin 17.4.0
npm ERR! argv "/Users/akihito1/.nodebrew/node/v6.11.1/bin/node" "/Users/akihito1/.nodebrew/current/bin/npm" "start"
npm ERR! node v6.11.1
npm ERR! npm  v3.10.10
npm ERR! code ELIFECYCLE
npm ERR! swagger-petstore@1.0.0 start: `node index.js`
npm ERR! Exit status 1
npm ERR!
npm ERR! Failed at the swagger-petstore@1.0.0 start script 'node index.js'.
npm ERR! Make sure you have the latest version of node.js and npm installed.
npm ERR! If you do, this is most likely a problem with the swagger-petstore package,
npm ERR! not with npm itself.
npm ERR! Tell the author that this fails on your system:
npm ERR!     node index.js
npm ERR! You can get information on how to open an issue for this project with:
npm ERR!     npm bugs swagger-petstore
npm ERR! Or if that isn't available, you can get their info via:
npm ERR!     npm owner ls swagger-petstore
npm ERR! There is likely additional logging output above.

npm ERR! Please include the following file with any support request:
npm ERR!     /Users/akihito1/src/github.com/ackintosh/openapi-generator/samples/server/petstore/nodejs/npm-debug.log
```


##### openapi-generator version

current master

##### Suggest a fix/enhancement

<!-- if you can't fix the bug yourself, perhaps you can point to what might be
  causing the problem (line of code or commit), or simply make a suggestion -->

The error is due to [the swagger-tools does not support OAS3](https://github.com/apigee-127/swagger-tools#supported-swagger-versions). 
(It is also occurs with OAS2 as `api/openapi.yaml` is always output as OAS 3.)

Users using swagger-tools did ask for it:

https://github.com/apigee-127/swagger-tools/issues/529
https://github.com/apigee-127/swagger-express/issues/20

But no reply from Apigee.

In the medium/long term, we'll need to provide another NodeJS server generator based on other (active) frameworks: https://nordicapis.com/13-node-js-frameworks-to-build-web-apis/



## emilianobonassi (21 May 2018)

@ackintosh, I've looked and I got the same result. 
IMHO, we should write from scratch a library for oas3. I think that should be a good idea to take https://github.com/atlassian/koa-oas3 as an example. 

We could also think to transpile that library to javascript using `tsc` from official npm package [typescript](https://www.npmjs.com/package/typescript).

Anyway, I think the best idea is the first one. What do you think?

## ackintosh (22 May 2018)

The first one looks good to me, too.

However, I can not imagine how difficult it is to write a library from scratch since I'm not so familiar with javascript. 💦 
(I think "transpile the library to javascript" or "new Node.js gen with [koa-oas3](https://github.com/atlassian/koa-oas3)" will be option if the first one is too hard.)

## emilianobonassi (22 May 2018)

@ackintosh it is not so difficult, it can be a good exercise.

We could proceed with both, one of us go for transpilation and the other one for scratch.
What do you prefer?

## viralanomaly (08 Jun 2018)

Is there any update on this?  Would it be possible to document the fact that nodejs-server doesn't work with 3.0 specs somewhere more obvious? I just wasted more time than I'd like to admit because of this.

## ackintosh (30 Jun 2018)

Unfortunately no updates. 😢 

Quick idea: what about displaying notification message on generating the Node.js server like below?

```
$ java -jar ......... -g nodejs-server

Currently, Node.js server doesn't work as its dependency doesn't support OpenAPI Spec3.
For further details, see https://github.com/OpenAPITools/openapi-generator/issues/34

[main] INFO  o.o.codegen.AbstractGenerator - writing file /Users/akihito1/src/github.com/ackintosh/openapi-generator-1/samples/server/petstore/nodejs/controllers/Pet.js
[main] INFO  o.o.codegen.AbstractGenerator - writing file /Users/akihito1/src/github.com/ackintosh/openapi-generator-1/samples/server/petstore/nodejs/service/StoreService.js
[main] INFO  o.o.codegen.AbstractGenerator - writing file /Users/akihito1/src/github.com/ackintosh/openapi-generator-1/samples/server/petstore/nodejs/controllers/Store.js
[main] INFO  o.o.codegen.AbstractGenerator - writing file /Users/akihito1/src/github.com/ackintosh/openapi-generator-1/samples/server/petstore/nodejs/service/UserService.js

...
...
...
```

cc @emilianobonassi @CodeNinjai @frol @cliffano (TechnicalCommittee)

## viralanomaly (30 Jun 2018)

I like that solution.

On Fri, Jun 29, 2018, 9:54 PM Akihito Nakano <notifications@github.com>
wrote:

> Unfortunately no updates. 😢
>
> Quick idea: what about displaying notification message on generating the
> Node.js server like below?
>
> $ java -jar ......... -g nodejs-server
>
> Currently, Node.js server doesn't work as its dependency doesn't support OpenAPI Spec3.
> For further details, see https://github.com/OpenAPITools/openapi-generator/issues/34
>
> [main] INFO  o.o.codegen.AbstractGenerator - writing file /Users/akihito1/src/github.com/ackintosh/openapi-generator-1/samples/server/petstore/nodejs/controllers/Pet.js
> [main] INFO  o.o.codegen.AbstractGenerator - writing file /Users/akihito1/src/github.com/ackintosh/openapi-generator-1/samples/server/petstore/nodejs/service/StoreService.js
> [main] INFO  o.o.codegen.AbstractGenerator - writing file /Users/akihito1/src/github.com/ackintosh/openapi-generator-1/samples/server/petstore/nodejs/controllers/Store.js
> [main] INFO  o.o.codegen.AbstractGenerator - writing file /Users/akihito1/src/github.com/ackintosh/openapi-generator-1/samples/server/petstore/nodejs/service/UserService.js
>
> ...
> ...
> ...
>
> cc @emilianobonassi <https://github.com/emilianobonassi> @CodeNinjai
> <https://github.com/CodeNinjai> @frol <https://github.com/frol> @cliffano
> <https://github.com/cliffano> (TechnicalCommittee)
>
> —
> You are receiving this because you commented.
> Reply to this email directly, view it on GitHub
> <https://github.com/OpenAPITools/openapi-generator/issues/34#issuecomment-401512523>,
> or mute the thread
> <https://github.com/notifications/unsubscribe-auth/AjMQoURnUJ5C8aegbx3tAbPos-8rWGDpks5uBuhVgaJpZM4T9CxP>
> .
>


## tuxedo0801 (04 Sept 2018)

Still no progress. Just used the JAR on command line and got the same output as @ackintosh :-(

cc @emilianobonassi @CodeNinjai @frol @cliffano (TechnicalCommittee)

## saprenitin (05 Dec 2018)

Hello,
When are you guys planning to release support for NodeJS?
Thanks

## tedepstein (16 Jan 2019)

@wing328 , @ackintosh, @emilianobonassi , with regard to an alternative OpenAPI v3 parser and object representation: you might want to look at [KaiZen OpenAPI Parser]( https://github.com/RepreZen/KaiZen-OpenAPI-Parser). 

It's written in Java, but it also includes its own metadata description of an OAS3 object model, with mappings to the JSON/YAML representation of an OAS3 document. The classes and parsing logic are all driven from this declarative configuration file. I believe most of that code generation and runtime serialization/deserialization are handled by a related project called [JSON Overlay](https://github.com/RepreZen/JsonOverlay). 

It might be practical for you to port this to JavaScript/NodeJS, generating code from the same metadata format. Both KaiZen Parser and JSON Overlay were written by @andylowry , who might be able to provide some pointers if you decide to try this approach. 

## philipmarkgreen (14 Feb 2019)

Is this still an issue given that a NodeJs server has been added? Note that I cannot still get the new nodejs server to run.

## danorton (19 Feb 2019)

@philipmarkgreen, yes, this is still an issue as long as it is in state "Open".

I'd like to know which server(s) actually work and are as easy to deploy. I haven't had any luck on the few other types I've tried (e.g. java-inflector)

## danorton (27 Feb 2019)

There are a lot of red herring comments w.r.t. OpenAPI 3.0: This server doesn't work with _any_ version of the API, not even with swagger 2.0.

## cjuenger (14 Mar 2019)

Is there any work in progress regarding this issue? I defined my API (OAS 3.0) in swagger with the assumption that the code generation works as the openapi-generator documentation states.

If a generator is not supported or erroneous, it maybe would help to remove it from the list of supported generators in the documentation. 
Giving hints on the start pages of  swagger.io and/or openapi-generator.tech would help as well. At least a warning which states: "Warning: Check support of target code generator." or similar.

## wing328 (01 Apr 2019)

Hi all,

I've filed https://github.com/OpenAPITools/openapi-generator/pull/2566 to add a new NodeJS (Fastify) server generator as an alternative as the existing NodeJS server generator won't work due to limitations in the dependency (not supporting OpenAPI v3).

It's still work-in-progress but worth sharing here to start collecting feedbacks.

Please check it out when you've time.

Thanks,
William

## torra (07 Apr 2019)

fwiw, i was able to get the pet store example working in nodejs by copying the initial swagger.yaml file to the generated api/openapi.yaml. i'm not sure what other consequences there might be from this but the example at least responds to api calls.

ie
```
openapi-generator generate -g nodejs-server -i api.yaml
cp api.yaml api/openapi.yaml
npm start
```

## RitwikChatterjee (11 Jun 2019)

Again, waiting for implementation of the node.js server. Can this enhancement request be voted on to figure out what to prioritize?


## davidwav (10 Jul 2019)

Interested in seeing this node.js server implemented also. 👍

## ellipsistechnology (11 Jul 2019)

I have forked both openapi-generator and oas3-tools and made just enough changes to get it working for my case. It is relatively untested and I make no guarantees that it will solve others' problems, but you might like to have a look at the changes and/or use it: 
[ellipsistechnology/openapi-generator](https://github.com/ellipsistechnology/openapi-generator)
[ellipsistechnology/oas3-tools](https://github.com/ellipsistechnology/oas3-tools)

## wing328 (25 Jul 2019)

Please check out the branch `nodejs-express-server` (alpha) and let us know if you've any feedback.

Thanks @YishTish for contributing the new generator.

## wing328 (13 Aug 2019)

Added `nodejs-express-server` generator (included in v4.1.0 release).

