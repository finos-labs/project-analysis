#CI Error with ElixirPetstoreClientTests on shippable

Owner: OpenAPITools

Repo: openapi-generator

Labels: Client: Elixir Enhancement: CI/Test 

## jmini (14 May 2018)

https://app.shippable.com/github/OpenAPITools/openapi-generator/runs/94/1/console

```
[INFO] ------------------------------------------------------------------------
[INFO] Building Elixir Petstore Client 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:copy-dependencies (default) @ ElixirPetstoreClientTests ---
[INFO] 
[INFO] --- exec-maven-plugin:1.2.1:exec (dep-install) @ ElixirPetstoreClientTests ---
* creating /root/.mix/archives/hex.ez
[INFO] 
[INFO] --- exec-maven-plugin:1.2.1:exec (compile) @ ElixirPetstoreClientTests ---
Resolving Hex dependencies...
Dependency resolution completed:
New:
  mime 1.2.0
  poison 3.1.0
  tesla 0.10.0
All dependencies up to date
** (Mix) You're trying to run :openapi_petstore on Elixir v1.1.0-dev but it has declared in its mix.exs file it supports only Elixir ~> 1.4
```

---

I have tried locally:
```
mvn verify -f samples/client/petstore/elixir/pom.xml
```
But this requires the `mix` program.

## wing328 (14 May 2018)

I'll have a look later.

## wing328 (14 May 2018)

I didn't do anything and it seems to be fixed. I just merged a PR into master. Let's see how it goes.

## wing328 (14 May 2018)

Latest master is green: https://app.shippable.com/github/OpenAPITools/openapi-generator/runs/98/summary/. I'll fix the shippable badge in another PR.

